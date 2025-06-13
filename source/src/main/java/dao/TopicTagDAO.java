package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.TopicTag;

public class TopicTagDAO {
	//顧客一覧で検索したときにデータを取得する作業
	// 引数topictagで指定された項目で検索して、取得されたデータのリストを返す
		public List<TopicTag> select(TopicTag topictag) {
			Connection conn = null;
			List<TopicTag> topictagList = new ArrayList<TopicTag>();

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/d3?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");

				// SQL文を準備する
				String sql = "SELECT * FROM TopicTag WHERE topic_name LIKE ? ORDER BY topic_id";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				// SQL文を完成させる
				if (topictag.getTopic_name() != null) {
					pStmt.setString(1, "%" + topictag.getTopic_name() + "%");
				} else {
					pStmt.setString(1, "%");
				}

				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// 結果表をコレクションにコピーする
				while (rs.next()) {
					TopicTag tt = new TopicTag(rs.getInt("topic_id"), rs.getString("topic_name"));
					topictagList.add(tt);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				topictagList = null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				topictagList = null;
			} finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						topictagList = null;
					}
				}
			}

			// 結果を返す
			return topictagList;
		}
}
