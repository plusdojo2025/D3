package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Commodity;
import dto.Customer;
import dto.KeepBottle;

public class KeepBottleDAO2 {

	// 顧客IDでキープボトル一覧を取得
	public List<KeepBottle> selectByCustomerId(int customerId) {
		List<KeepBottle> resultList = new ArrayList<>();
		String sql = "SELECT k.bottole_id, k.bottole_remaining, k.bottole_rimit, " + "c.commodity_id, c.commodity_name "
				+ "FROM keep_bottole k " + "JOIN commodity c ON k.commodity_id = c.commodity_id "
				+ "WHERE k.customer_id = ?";
		try (Connection conn = connectDatabase(); PreparedStatement pStmt = conn.prepareStatement(sql)) {
			pStmt.setInt(1, customerId);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				KeepBottle kb = new KeepBottle();
				kb.setBottle_id(rs.getInt("bottole_id"));
				kb.setBottle_remaining(rs.getInt("bottole_remaining"));
				kb.setBottle_rimit(rs.getTimestamp("bottole_rimit"));

				Commodity commodity = new Commodity();
				commodity.setCommodity_id(rs.getInt("commodity_id"));
				commodity.setCommodity_name(rs.getString("commodity_name"));
				kb.setCommodity(commodity);

				Customer customer = new Customer();
				customer.setCustomer_id(customerId);
				kb.setCustomer(customer);

				resultList.add(kb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultList;
	}

	// update, delete, insert

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private Connection connectDatabase() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/d3?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9";
		String user = "root";
		String password = "password";
		return DriverManager.getConnection(url, user, password);
	}
}
