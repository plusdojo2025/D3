package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Commodity;

public class CartDAO {

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
