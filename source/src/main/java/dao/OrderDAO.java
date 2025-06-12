package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import dto.Order;

public class OrderDAO {
	
	// 引数orderで指定されたレコードを登録し、成功したらtrueを返す
			public boolean insert(List<Order> order) {
				Connection conn = null;
				boolean result = false;

				try {
					// JDBCドライバを読み込む
					Class.forName("com.mysql.cj.jdbc.Driver");

					// データベースに接続する
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp2?"
							+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
							"root", "password");

					for(int i=0;i<order.size();i++) {
						
						// SQL文を準備する
						String sql = "INSERT INTO Order VALUES (0, ?, ?, ?)";
						PreparedStatement pStmt = conn.prepareStatement(sql);

						// SQL文を完成させる
						if (order.get(i).getCommodity_id() != null) {
							pStmt.setInt(1, order.get(i).getCommodity_id());
						} else {
							pStmt.setInt(1, 0);
						}
						if (order.get(i).getOrder_datetime() != null) {
							pStmt.setString(2, order.get(i).getOrder_datetime());
						} else {
							pStmt.setString(2, "");
						}
						if (order.get(i).getOrder_quantity() != null) {
							pStmt.setInt(3, order.get(i).getOrder_quantity());
						} else {
							pStmt.setInt(3, 0);
						}
							

						// SQL文を実行する
						if (pStmt.executeUpdate() == 1) {
							result = true;
						}
					
					

					
					
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
