package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dto.Commodity;
import dto.Customer;
import dto.OrderList;

public class OrderListDAO {

	// 注文画面で注文ボタンを押した時のデータを登録する作業
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

			for (int i = 0; i < order.size(); i++) {

				// SQL文を準備する
				String sql = "INSERT INTO OrderList VALUES (0, ?, ?,NOW(), ?,?)";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる

				pStmt.setInt(1, order.get(i).getCustomer().getCustomer_id());

				pStmt.setInt(2, order.get(i).getCommodity().getCommodity_id());

				pStmt.setInt(3, order.get(i).getOrder_quantity());
				pStmt.setInt(4, order.get(i).getVisit_id());


				// SQL文を実行する
				if (pStmt.executeUpdate() == 1) {
					result = true;
				}

			} // for文の終了
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

	// 注文履歴を日時新しい順で取得
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
			if (order.getOrder_id() != -1) {// 入力がなかった場合を-1として持ってきます
				pStmt.setString(1, String.valueOf(order.getOrder_id()));
			} else {
				pStmt.setString(1, "%");
			}
			if (order.getCustomer().getCustomer_id() != -1) {// 入力がなかった場合を-1として持ってきます
				pStmt.setString(2, String.valueOf(order.getCustomer().getCustomer_id()));
			} else {
				pStmt.setString(2, "%");
			}
			if (order.getCommodity().getCommodity_id() != -1) {// 入力がなかった場合を-1として持ってきます
				pStmt.setString(3, String.valueOf(order.getCommodity().getCommodity_id()));
			} else {
				pStmt.setString(3, "%");
			}
			if (order.getOrder_datetime() != "") {
				pStmt.setString(4, order.getOrder_datetime());
			} else {
				pStmt.setString(4, "%");
			}
			if (order.getOrder_quantity() != -1) {// 入力がなかった場合を-1として持ってきます
				pStmt.setString(5, String.valueOf(order.getOrder_quantity()));
			} else {
				pStmt.setString(5, "%");
			}

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				OrderList ord = new OrderList(rs.getInt("order_id"),
						new Customer(rs.getInt("customer_id"), "", "", "", ""),
						new Commodity(rs.getInt("commodity_id"), "", 0, 0, ""), rs.getString("order_datetime"),
						rs.getInt("order_quantity"));
				orderList.add(ord);
			} // while終了

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
	// 指定された商品（日時）の個数を返す。
	public int quantity(int commodity_id, String order_datetime) {
		
		
		Connection conn = null;
		int sum =0;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/d3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する

			String sql = "SELECT order_quantity FROM OrderList "
					+ "WHERE commodity_id = ? AND order_datetime = ? ";
			

			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, String.valueOf(commodity_id));
			pStmt.setString(2, order_datetime);
			
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			
			int i=0;
			// 結果表をコレクションにコピーする
			while (rs.next()) {
				
						
				sum+=(rs.getInt("order_quantity"));
				
			} // while終了

		} catch (SQLException e) {
			e.printStackTrace();
			sum = 0;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			sum = 0;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					sum = 0;
				}
			}
		}

		
		return sum;
	}

	//
	// 指定された顧客のいつもの商品IDを返す。
	public int customerOrder(int customer_id) {
		OrderListDAO dao = new OrderListDAO();
		OrderList orderList = new OrderList();
		orderList.setCustomer(new Customer(customer_id, "", "", "", ""));
		List<OrderList> list = dao.select_new(orderList);
		int sum[] = new int[100];
		Arrays.fill(sum, -1);
		int max = 0;
		int id = -1;
		for (OrderList data : list) {
			sum[data.getCommodity().getCommodity_id()] += data.getOrder_quantity();
		}
		for (int i = 0; i < 100; i++) {
			if (sum[i] > max) {
				max = sum[i];
				id = i;
			}
		}
		return id;
	}
	
	// 商品IDから商品データを取得
	public Commodity getCommodityById(int id) {
		String sql = "SELECT * FROM commodity " + "WHERE commodity_id = ?";
		try (Connection conn = connectDatabase(); PreparedStatement pStmt = conn.prepareStatement(sql.toString());) {
			pStmt.setInt(1, id);

			ResultSet rs = pStmt.executeQuery();
			Commodity commodity = new Commodity(0, "", 0, 0, "");
			if (rs.next()) {
				commodity.setCommodity_id(rs.getInt("commodity_id"));
				commodity.setCommodity_name(rs.getString("commodity_name"));
				commodity.setCommodity_price(rs.getInt("commodity_price"));
				commodity.setCommodity_category(rs.getInt("commodity_category"));
				commodity.setCommodity_image(rs.getString("commodity_image"));
			}

			return commodity;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// 顧客IDから顧客データを取得
	public String getCustomerNameById(int id) {
		String sql = "SELECT customer_name FROM customer " + "WHERE customer_id = ?";
		try (Connection conn = connectDatabase(); PreparedStatement pStmt = conn.prepareStatement(sql.toString());) {
			pStmt.setInt(1, id);

			ResultSet rs = pStmt.executeQuery();
			String name = "";
			if (rs.next()) {
				name = rs.getString("customer_name");
			}

			return name;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<OrderList> getTodayOrderByCustomerId(int id, String date) {
		String sql = "SELECT order_id, order_datetime, order_quantity, customer.customer_name, commodity.commodity_name "
				+ "FROM orderList "
				+ "JOIN customer ON orderList.customer_id = customer.customer_id "
				+ "JOIN commodity ON orderList.commodity_id = commodity.commodity_id "
				+ "WHERE orderList.customer_id = ? AND order_datetime >= ? "
				+ "ORDER BY order_datetime DESC";
		List<OrderList> orderList = new ArrayList<OrderList>();
		
		try (Connection conn = connectDatabase(); PreparedStatement pStmt = conn.prepareStatement(sql.toString());) {
			pStmt.setInt(1, id);
			pStmt.setString(2, date);
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				OrderList order = new OrderList();
				
				order.setOrder_id(rs.getInt("order_id"));
				order.setOrder_datetime(rs.getString("order_datetime"));
				order.setOrder_quantity(rs.getInt("order_quantity"));

				Customer customer = new Customer();
				customer.setCustomer_name(rs.getString("customer.customer_name"));
				order.setCustomer(customer);
				
				Commodity commodity = new Commodity();
				commodity.setCommodity_name(rs.getString("commodity.commodity_name"));
				order.setCommodity(commodity);
				
				orderList.add(order);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return orderList;
	}
	public List<OrderList> getTodayOrderByStoreId(int id, String date) {
		String sql = "SELECT order_id, order_datetime, order_quantity, customer.customer_name, commodity.commodity_name "
				+ "FROM orderList "
				+ "JOIN customer ON orderList.customer_id = customer.customer_id "
				+ "JOIN commodity ON orderList.commodity_id = commodity.commodity_id "
				+ "WHERE orderList.store_id = ? AND order_datetime >= ? "
				+ "ORDER BY order_datetime DESC";
		List<OrderList> orderList = new ArrayList<OrderList>();
		
		try (Connection conn = connectDatabase(); PreparedStatement pStmt = conn.prepareStatement(sql.toString());) {
			pStmt.setInt(1, id);
			pStmt.setString(2, date);
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				OrderList order = new OrderList();
				
				order.setOrder_id(rs.getInt("order_id"));
				order.setOrder_datetime(rs.getString("order_datetime"));
				order.setOrder_quantity(rs.getInt("order_quantity"));

				Customer customer = new Customer();
				customer.setCustomer_name(rs.getString("customer.customer_name"));
				order.setCustomer(customer);
				
				Commodity commodity = new Commodity();
				commodity.setCommodity_name(rs.getString("commodity.commodity_name"));
				order.setCommodity(commodity);
				
				orderList.add(order);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return orderList;
	}
	
	public List<OrderList> getOrderByVisitId(int id) {
		String sql = "SELECT orderList.order_quantity, commodity.commodity_name, commodity.commodity_price"
				+ " FROM orderList "
				+ "JOIN commodity ON orderList.commodity_id = commodity.commodity_id "
				+ "WHERE orderList.visit_id = ?";
		try (Connection conn = connectDatabase(); PreparedStatement pStmt = conn.prepareStatement(sql.toString());) {
			pStmt.setInt(1, id);

			ResultSet rs = pStmt.executeQuery();
			List<OrderList> orderList = new ArrayList<OrderList>();
			while(rs.next()) {
				OrderList order = new OrderList();
				Commodity commodity = new Commodity();
				commodity.setCommodity_name(rs.getString("commodity.commodity_name"));
				commodity.setCommodity_price(rs.getInt("commodity.commodity_price"));
				order.setCommodity(commodity);
				
				order.setOrder_quantity(rs.getInt("orderList.order_quantity"));
				order.setOrder_id(id);
				
				orderList.add(order);
			}

			return orderList;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private Connection connectDatabase() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/d3?"
				+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true";
		String user = "root";
		String password = "password";

		return DriverManager.getConnection(url, user, password);
	}
}
