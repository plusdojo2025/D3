package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Customer;

public class CustomerDAO {

	// 引数customer指定された項目で検索して、取得されたデータのリストを返す
	public List<Customer> select(Customer customer) {
		Connection conn = null;
		List<Customer> customerList = new ArrayList<Customer>();

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/d3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "SELECT * FROM customer WHERE customer_name LIKE ? AND customer_email NOT LIKE ? ORDER BY customer_id";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			if (customer.getCustomer_name() != null) {
			    pStmt.setString(1, "%" + customer.getCustomer_name() + "%");
			} else {
			    pStmt.setString(1, "%");
			}
			// 「@example.com」を除外
			pStmt.setString(2, "%@example.com");


			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Customer ctm = new Customer(rs.getInt("customer_id"), rs.getString("customer_name"),
						rs.getString("customer_email"), rs.getString("customer_password"),
						rs.getString("customer_birthday"));
				customerList.add(ctm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			customerList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			customerList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					customerList = null;
				}
			}
		}

		// 結果を返す
		return customerList;
	}

	// 引数customerで指定されたレコードを登録し、成功したらtrueを返す
	public boolean insert(Customer customer) {
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
			String sql = "INSERT INTO customer (customer_name, customer_email, customer_password, customer_birthday)"
					+ " VALUES (?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, customer.getCustomer_name());
			pStmt.setString(2, customer.getCustomer_email());
			pStmt.setString(3, customer.getCustomer_password());
			pStmt.setString(4, customer.getCustomer_birthday());

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

	// 引数customerで指定されたレコードを更新し、成功したらtrueを返す
	public boolean update(Customer customer) {
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
			String sql = "UPDATE customer SET customer_name=?, customer_email=?, customer_password=?, customer_birthday=? where customer_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (customer.getCustomer_name() != null) {
				pStmt.setString(1, customer.getCustomer_name());
			} else {
				pStmt.setString(1, "");
			}
			if (customer.getCustomer_email() != null) {
				pStmt.setString(2, customer.getCustomer_email());
			} else {
				pStmt.setString(2, "");
			}
			if (customer.getCustomer_password() != null) {
				pStmt.setString(3, customer.getCustomer_password());
			} else {
				pStmt.setString(3, "");
			}
			if (customer.getCustomer_birthday() != null) {
				pStmt.setString(4, customer.getCustomer_birthday());
			} else {
				pStmt.setString(4, "");
			}
			pStmt.setInt(5, customer.getCustomer_id());

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

	// 引数customerで指定された番号のレコードを削除し、成功したらtrueを返す
	public boolean delete(Customer customer) {
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
			String sql = "DELETE FROM customer WHERE customer_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, customer.getCustomer_id());

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

	// 全件取得
	public List<Customer> selectAll() {
		return select(new Customer()); // 初期化されたCustomerは全件検索と同義
	}
	
	// IDで顧客を1件取得する
	public Customer selectById(int customerId) {
	    Connection conn = null;
	    Customer customer = null;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/d3?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
	            "root", "password");

	        String sql = "SELECT * FROM customer WHERE customer_id = ?";
	        PreparedStatement pStmt = conn.prepareStatement(sql);
	        pStmt.setInt(1, customerId);

	        ResultSet rs = pStmt.executeQuery();
	        if (rs.next()) {
	            customer = new Customer(
	                rs.getInt("customer_id"),
	                rs.getString("customer_name"),
	                rs.getString("customer_email"),
	                rs.getString("customer_password"),
	                rs.getString("customer_birthday")
	            );
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

	    return customer;
	}

	// 顧客ログイン（メールとパスワードで認証）
	public Customer login(String email, String password) {
		Connection conn = null;
		Customer customer = null;

		try {
			// JDBCドライバ読み込み
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/d3?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9", "root",
					"password");

			// SQL文準備
			String sql = "SELECT * FROM customer WHERE customer_email = ? AND customer_password = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, email);
			pStmt.setString(2, password);

			// SQL実行
			ResultSet rs = pStmt.executeQuery();

			// 結果があればCustomerオブジェクトにセット
			if (rs.next()) {
				customer = new Customer();
				customer.setCustomer_id(rs.getInt("customer_id"));
				customer.setCustomer_name(rs.getString("customer_name"));
				customer.setCustomer_email(rs.getString("customer_email"));
				customer.setCustomer_password(rs.getString("customer_password"));
				customer.setCustomer_birthday(rs.getString("customer_birthday"));
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 接続解除
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return customer; // nullならログイン失敗
	}

	// ページング付きで顧客一覧取得
	public List<Customer> selectByPage(String name, int page, int limit) {
		Connection conn = null;
		List<Customer> customerList = new ArrayList<>();
		int offset = (page - 1) * limit;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/d3?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9", "root",
					"password");

			String sql = "SELECT * FROM customer WHERE customer_name LIKE ? AND customer_email NOT LIKE ? ORDER BY customer_id LIMIT ? OFFSET ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, "%" + (name == null ? "" : name) + "%");
			// ゲスト排除
			pStmt.setString(2, "%@example.com");
			pStmt.setInt(3, limit);
			pStmt.setInt(4, offset);


			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				Customer ctm = new Customer(rs.getInt("customer_id"), rs.getString("customer_name"),
						rs.getString("customer_email"), rs.getString("customer_password"),
						rs.getString("customer_birthday"));
				customerList.add(ctm);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return customerList;
	}

	// 総件数取得
	public int countByName(String name) {
		Connection conn = null;
		int count = 0;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/d3?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9", "root",
					"password");

			String sql = "SELECT COUNT(*) FROM customer WHERE customer_name LIKE ? AND customer_email NOT LIKE ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, "%" + (name == null ? "" : name) + "%");
			// ゲスト排除
			pStmt.setString(2, "%@example.com");

			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return count;
	}
	
	public int countGuest() {
		Connection conn = null;
		int count = 0;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/d3?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9", "root",
					"password");

			String sql = "SELECT COUNT(*) FROM customer "
					+ "WHERE customer_email LIKE 'guest%' AND customer_email LIKE '%@example.com'";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return count;
	}
	
	public int getCustomerIdByCustomerEmail(String email) {
		Connection conn = null;
		int customerId = -1;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/d3?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9", "root",
					"password");

			String sql = "SELECT customer_id FROM customer "
					+ "WHERE customer_email = ?";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, email);

			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				customerId = rs.getInt("customer_id");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return customerId;
	}

}
