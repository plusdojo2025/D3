package dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dto.Event;

public class EventDAO {
		
		//イベント取得用
		// 日付の新しい順で並び替えして、取得されたデータのリストを返す
		public List<Event> select() {
			
			
				Connection conn = null;
				List<Event> eveList = new ArrayList<Event>();

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");

					// データベースに接続する
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/d3?"
							+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
							"root", "password");

					// SQL文を準備する
					
					String sql = "SELECT * FROM event WHERE event_date >= CURDATE() ORDER BY event_date ASC";
					PreparedStatement pStmt = conn.prepareStatement(sql);

					// SQL文を実行し、結果表を取得する
					ResultSet rs = pStmt.executeQuery();
					
					
					// 結果表をコレクションにコピーする
						while (rs.next()) {
							Event eve = new Event(
									rs.getInt("event_id"), 
									rs.getInt("store_id"),
									rs.getString("event_date"),
									rs.getString("event_name"),
									rs.getString("event_remark")					
									);
							eveList.add(eve);			
						}//while終了

				} catch (SQLException e) {
					e.printStackTrace();
					eveList = null;
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					eveList = null;
				} finally {
					// データベースを切断
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
							eveList = null;
						}
					}
				}

				// 結果を返す
				return eveList;
		}
		
		
		//イベント登録用
		// 引数eventで指定されたレコードを登録し、成功したらtrueを返す
		//古いデータは削除したかったら追加します
		public boolean insert(Event event) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/d3?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");

				// SQL文を準備する
				String sql = "INSERT INTO event VALUES (0, ?, ?, ?, ?)";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				
				pStmt.setInt(1, event.getStore_id());
				if (event.getEvent_date() != null) {
					pStmt.setString(2, event.getEvent_date());
				} else {
					pStmt.setDate(2, Date.valueOf(LocalDate.now()));
				}
				//Date today = Date.valueOf(LocalDate.now());
				//pStmt.setDate(2, today);
				
				if (event.getEvent_name() != null) {
					pStmt.setString(3, event.getEvent_name());
				} else {
					pStmt.setString(3, "");
				}
				if (event.getEvent_remark() != null) {
					pStmt.setString(4, event.getEvent_remark());
				} else {
					pStmt.setString(4, "");
				}
				

				// SQL文を実行する
				if (pStmt.executeUpdate() == 1) {
					result = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

			// 結果を返す
			return result;
		}
		

	}


