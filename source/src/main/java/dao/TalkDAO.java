package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Talk;

public class TalkDAO {
	// 引数card指定された項目で検索して、取得されたデータのリストを返す
	public List<Talk> select(Talk card) {
	    Connection conn = null;
	    List<Talk> cardList = new ArrayList<>();

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/d3?"
	                + "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
	                "root", "password");

	        // ★ customer_id を必須条件に加える
	        String sql = "SELECT * FROM Talk WHERE customer_id = ?";

	        PreparedStatement pStmt = conn.prepareStatement(sql);

	        pStmt.setInt(1, card.getCustomer_id());

	        ResultSet rs = pStmt.executeQuery();

	        while (rs.next()) {
	            Talk tk = new Talk(
	                    rs.getInt("customer_id"),
	                    rs.getInt("topic_id"),
	                    rs.getString("talk_remark"));
	            cardList.add(tk);
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	        cardList = null;
	    } finally {
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    return cardList;
	}


	// 引数cardで指定されたレコードを登録し、成功したらtrueを返す
	public boolean insert(Talk card) {
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
			String sql = "INSERT INTO Talk (customer_id, topic_id, talk_remark) VALUES (?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (card.getCustomer_id() != null) {
			    pStmt.setInt(1, card.getCustomer_id());
			} else {
			    pStmt.setNull(1, java.sql.Types.INTEGER);
			}

			if (card.getTopic_id() != null) {
			    pStmt.setInt(2, card.getTopic_id());
			} else {
			    pStmt.setNull(2, java.sql.Types.INTEGER);
			}

			if (card.getTalk_remark() != null) {
			    pStmt.setString(3, card.getTalk_remark());
			} else {
			    pStmt.setString(3, "");
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

	// 引数cardで指定されたレコードを更新し、成功したらtrueを返す
	public boolean update(Talk card) {
		Connection conn = null;
		boolean result = false;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/d3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// topic_idは一意で変わらない想定なので、SETから外して条件に使う
			String sql = "UPDATE Talk SET talk_remark=? WHERE customer_id=? AND topic_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, card.getTalk_remark() != null ? card.getTalk_remark() : "");
			pStmt.setInt(2, card.getCustomer_id());
			pStmt.setInt(3, card.getTopic_id());

			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	// 引数cardで指定された番号のレコードを削除し、成功したらtrueを返す
	public boolean delete(Talk card) {
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
			String sql = "DELETE FROM Talk WHERE customer_id=? AND topic_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, card.getCustomer_id());
			pStmt.setInt(2, card.getTopic_id());

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
