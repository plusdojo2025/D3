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
			String sql = "SELECT * FROM Customer "
					+ "WHERE customer_name LIKE ? AND customer_birthday LIKE ? ORDER BY customer_id";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (customer.getCustomer_name() != null) {
				pStmt.setString(1, "%" + customer.getCustomer_name() + "%");
			} else {
				pStmt.setString(1, "%");
			}
			if (customer.getCustomer_birthday() != null) {
				pStmt.setString(2, "%" + customer.getCustomer_birthday() + "%");
			} else {
				pStmt.setString(2, "%");
			}

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
			String sql = "INSERT INTO Customer (customer_name, customer_email, customer_password, customer_birthday)"
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
			String sql = "UPDATE Customer SET customer_name=?, customer_email=?, customer_password=?, customer_birthday=? where customer_id=?";
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
			pStmt.setInt(15, customer.getCustomer_id());

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
			String sql = "DELETE FROM Customer WHERE customer_id=?";
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

}
