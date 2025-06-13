package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Order;

public class OrderListDAO {
	
	//注文画面で注文ボタンを押した時のデータを登録する作業
	// 引数orderで指定されたレコードを登録し、成功したらtrueを返す
			public boolean insert(List<Order> order) {
				Connection conn = null;
				boolean result = false;

				try {
					// JDBCドライバを読み込む
					Class.forName("com.mysql.cj.jdbc.Driver");

					// データベースに接続する
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/d3?"
							+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
							"root", "password");

					for(int i=0;i<order.size();i++) {
						
						// SQL文を準備する
						String sql = "INSERT INTO Order VALUES (0, ?, ?, ?)";
						PreparedStatement pStmt = conn.prepareStatement(sql);

						// SQL文を完成させる
						
							pStmt.setInt(1, order.get(i).getCommodity_id());
						
						if (order.get(i).getOrder_datetime() != null) {
							pStmt.setString(2, order.get(i).getOrder_datetime());
						} else {
							pStmt.setString(2, "");
						}
						
							pStmt.setInt(3, order.get(i).getOrder_quantity());
						
							

						// SQL文を実行する
						if (pStmt.executeUpdate() == 1) {
							result = true;
						}
					
					}//for文の終了
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
			
			
			
			
			//注文履歴を日時古い順で取得
			// 取得されたデータのリストを返す
			public List<Order> select_old(Order order) {
				
				
					Connection conn = null;
					List<Order> orderList = new ArrayList<Order>();

					try {
						// JDBCドライバを読み込む
						Class.forName("com.mysql.cj.jdbc.Driver");

						// データベースに接続する
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/d3?"
								+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
								"root", "password");

						// SQL文を準備する
						
						
						
						String sql = "SELECT * FROM Order "
								+ "WHERE order_id LIKE ? AND commodity_id LIKE ? AND order_datetime LIKE ? AND order_quantity LIKE ? "
								+ "ORDER BY order_datetime ASC";
						
						PreparedStatement pStmt = conn.prepareStatement(sql);

						
						// SQL文を完成させる
						if (order.getOrder_id() != -1) {//入力がなかった場合を-1として持ってきます
							pStmt.setString(1, String.valueOf(order.getOrder_id()));
						} else {
							pStmt.setString(1, "%");
						}
						if (order.getCommodity_id() != -1) {//入力がなかった場合を-1として持ってきます
							pStmt.setString(2,  String.valueOf(order.getCommodity_id()) );
						} else {
							pStmt.setString(2, "%");
						}
						if (order.getOrder_datetime() != "") {
							pStmt.setString(3, order.getOrder_datetime());
						} else {
							pStmt.setString(3, "%");
						}
						if (order.getOrder_quantity() != -1) {//入力がなかった場合を-1として持ってきます
							pStmt.setString(4, String.valueOf(order.getOrder_quantity()));
						} else {
							pStmt.setString(4, "%");
						}
							
						// SQL文を実行し、結果表を取得する
						ResultSet rs = pStmt.executeQuery();
						
						
						// 結果表をコレクションにコピーする
							while (rs.next()) {
								Order ord = new Order(
										rs.getInt("order_id"), 
										rs.getInt("commodity_id"),
										rs.getString("order_datetime"),
										rs.getInt("order_quantity")													
										);
								orderList.add(ord);			
							}//while終了

					} catch (SQLException e) {
						e.printStackTrace();
						orderList = null;
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
						orderList = null;
					} finally {
						// データベースを切断
						if (conn != null) {
							try {
								conn.close();
							} catch (SQLException e) {
								e.printStackTrace();
								orderList = null;
							}
						}
					}

					// 結果を返す
					return orderList;
			}
			
			//注文履歴を日時新しい順で取得
			// 取得されたデータのリストを返す
			public List<Order> select_new(Order order) {
				
				
					Connection conn = null;
					List<Order> orderList = new ArrayList<Order>();

					try {
						// JDBCドライバを読み込む
						Class.forName("com.mysql.cj.jdbc.Driver");

						// データベースに接続する
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/d3?"
								+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
								"root", "password");

						// SQL文を準備する
						
						
						
						String sql = "SELECT * FROM Order "
								+ "WHERE order_id LIKE ? AND commodity_id LIKE ? AND order_datetime LIKE ? AND order_quantity LIKE ? "
								+ "ORDER BY order_datetime DESC";
						
						PreparedStatement pStmt = conn.prepareStatement(sql);

						
						// SQL文を完成させる
						if (order.getOrder_id() != -1) {//入力がなかった場合を-1として持ってきます
							pStmt.setString(1, String.valueOf(order.getOrder_id()));
						} else {
							pStmt.setString(1, "%");
						}
						if (order.getCommodity_id() != -1) {//入力がなかった場合を-1として持ってきます
							pStmt.setString(2,  String.valueOf(order.getCommodity_id()) );
						} else {
							pStmt.setString(2, "%");
						}
						if (order.getOrder_datetime() != "") {
							pStmt.setString(3, order.getOrder_datetime());
						} else {
							pStmt.setString(3, "%");
						}
						if (order.getOrder_quantity() != -1) {//入力がなかった場合を-1として持ってきます
							pStmt.setString(4, String.valueOf(order.getOrder_quantity()));
						} else {
							pStmt.setString(4, "%");
						}
							
						// SQL文を実行し、結果表を取得する
						ResultSet rs = pStmt.executeQuery();
						
						
						// 結果表をコレクションにコピーする
							while (rs.next()) {
								Order ord = new Order(
										rs.getInt("order_id"), 
										rs.getInt("commodity_id"),
										rs.getString("order_datetime"),
										rs.getInt("order_quantity")													
										);
								orderList.add(ord);			
							}//while終了

					} catch (SQLException e) {
						e.printStackTrace();
						orderList = null;
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
						orderList = null;
					} finally {
						// データベースを切断
						if (conn != null) {
							try {
								conn.close();
							} catch (SQLException e) {
								e.printStackTrace();
								orderList = null;
							}
						}
					}

					// 結果を返す
					return orderList;
			}
			

			
			
			

}
