package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dto.OrderList;


public class OrderListDAO {
	
	//注文画面で注文ボタンを押した時のデータを登録する作業
	// 引数orderで指定されたレコードを登録し、成功したらtrueを返す
			public boolean insert(List<OrderList> order) {
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
						String sql = "INSERT INTO OrderList VALUES (0, ?, ?,NOW(), ?)";
						PreparedStatement pStmt = conn.prepareStatement(sql);

						// SQL文を完成させる
						
							pStmt.setInt(1, order.get(i).getCommodity_id());
						
							pStmt.setInt(2, order.get(i).getCustomer_id());
						
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
			
						
			
			//注文履歴を日時新しい順で取得
			// 注文履歴画面用
			public List<OrderList> select_new(OrderList order) {
				
				
					Connection conn = null;
					List<OrderList> orderList = new ArrayList<OrderList>();

					try {
						// JDBCドライバを読み込む
						Class.forName("com.mysql.cj.jdbc.Driver");

						// データベースに接続する
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/d3?"
								+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
								"root", "password");

						// SQL文を準備する
						
						
						
						String sql = "SELECT * FROM OrderList "
								+ "WHERE order_id LIKE ? AND customer_id LIKE ? AND commodity_id LIKE ? AND order_datetime LIKE ? AND order_quantity LIKE ? "
								+ "ORDER BY order_datetime DESC";
						
						PreparedStatement pStmt = conn.prepareStatement(sql);

						
						// SQL文を完成させる
						if (order.getOrder_id() != -1) {//入力がなかった場合を-1として持ってきます
							pStmt.setString(1, String.valueOf(order.getOrder_id()));
						} else {
							pStmt.setString(1, "%");
						}
						if (order.getCustomer_id() != -1) {//入力がなかった場合を-1として持ってきます
							pStmt.setString(2, String.valueOf(order.getCustomer_id()));
						} else {
							pStmt.setString(2, "%");
						}	
						if (order.getCommodity_id() != -1) {//入力がなかった場合を-1として持ってきます
							pStmt.setString(3,  String.valueOf(order.getCommodity_id()) );
						} else {
							pStmt.setString(3, "%");
						}
						if (order.getOrder_datetime() != "") {
							pStmt.setString(4, order.getOrder_datetime());
						} else {
							pStmt.setString(4, "%");
						}
						if (order.getOrder_quantity() != -1) {//入力がなかった場合を-1として持ってきます
							pStmt.setString(5, String.valueOf(order.getOrder_quantity()));
						} else {
							pStmt.setString(5, "%");
						}
							
						// SQL文を実行し、結果表を取得する
						ResultSet rs = pStmt.executeQuery();
						
						
						// 結果表をコレクションにコピーする
							while (rs.next()) {
								OrderList ord = new OrderList(
										rs.getInt("order_id"), 
										rs.getInt("customer_id"), 
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
			
			//
			//指定された商品（日時）の個数を返す。
					public int quantity(int commodity_id,String order_datetime) {
						OrderListDAO dao = new OrderListDAO();
						List<OrderList> list = dao.select_new(new OrderList(-1,-1,commodity_id,order_datetime,-1));
						int sum=0;
						for(OrderList data:list) {	
								sum+=data.getOrder_quantity();							
						}							
						return sum;
					}
					
			//
			//指定された顧客のいつもの商品IDを返す。
			public int customerOrder(int customer_id) {
				OrderListDAO dao = new OrderListDAO();
				List<OrderList> list = dao.select_new(new OrderList(-1,customer_id,-1,"",-1));
				int sum[]=new int[100];
				Arrays.fill(sum, -1);
				int max=0;
				int id=-1;
				for(OrderList data:list) {	
					sum[data.getCommodity_id()]+=data.getOrder_quantity();					
				}
				for(int i=0;i<100;i++) {
					if(sum[i]>max) {
						max=sum[i];
						id=i;
					}
				}
				return id;
			}
					
			
			

}
