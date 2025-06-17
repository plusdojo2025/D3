package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Commodity;
import dto.KeepBottle;


public class KeepBottleDAO {
	
	public List<KeepBottle> select(KeepBottle keepBottle) {
		List<KeepBottle> resultList = new ArrayList<KeepBottle>();
		String sql = "SELECT commodity.commodity_name, bottle_remaining, bottle_rimit "
				+ "FROM keep_bottle JOIN commodity ON keep_bottle.commodity_id = commodity.commodity_id "
				+ "WHERE customer_id = ?";

		try (Connection conn = connectDatabase(); PreparedStatement pStmt = conn.prepareStatement(sql.toString());) {
			pStmt.setInt(1, keepBottle.getCustomer().getCustomer_id());
			
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				KeepBottle kb = new KeepBottle();
				Commodity commodity = new Commodity(
					0, rs.getString("commodity.commodity_name"), 0, 0, "");
				kb.setCommodity(commodity);
				kb.setBottle_remaining(rs.getInt("bottle_remaining"));
				kb.setBottle_rimit(rs.getTimestamp("bottle_rimit"));
				resultList.add(keepBottle);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			resultList = null;
		}
		
		return resultList;
	}
	
	public boolean insert(KeepBottle keepBottle) {
		String sql = "INSERT INTO keep_bottle (customer_id, commodity_id, bottle_remaining, bottle_rimit) "
				+ "VALUES (?, ?, ?, ?)";
		
		try (Connection conn = connectDatabase(); PreparedStatement pStmt = conn.prepareStatement(sql.toString());) {
			String commodityName = keepBottle.getCommodity().getCommodity_name();
			int commodityId = getCommodityIdByName(conn, commodityName);
		
			pStmt.setInt(1, keepBottle.getCustomer().getCustomer_id());
			pStmt.setInt(2, commodityId);
			pStmt.setInt(3, keepBottle.getBottle_remaining());
			pStmt.setTimestamp(4, keepBottle.getBottle_rimit());
			
			if (pStmt.executeUpdate() == 1) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	private int getCommodityIdByName(Connection  conn, String commodityName) throws SQLException{
		String sql = "SELECT commodity_id FROM commodity WHERE commodity_name = ?";
		try(PreparedStatement pStmt = conn.prepareStatement(sql)) {
			pStmt.setString(1, commodityName);
			
			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("commodity_id");
			} else {
				throw new SQLException("商品名が存在しません" + commodityName);
			}
		}
	}
	
	public boolean update(KeepBottle keepBottle) {
		String sql = "UPDATE keep_bottle SET "
				+ "bottle_remaining = ? "
				+ "WHERE bottle_id = ?";
		try (Connection conn = connectDatabase(); PreparedStatement pStmt = conn.prepareStatement(sql.toString());) {
			pStmt.setInt(1, keepBottle.getBottle_remaining());
			pStmt.setInt(2, keepBottle.getBottle_id());
			
			if (pStmt.executeUpdate() == 1) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public boolean delete(KeepBottle keepBottle) {
		String sql = "DELETE FROM keep_bottole WHERE bottole_id = ?";
		try (Connection conn = connectDatabase(); PreparedStatement pStmt = conn.prepareStatement(sql.toString());) {
			pStmt.setInt(1, keepBottle.getBottle_id());
			
			if (pStmt.executeUpdate() == 1) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return false;
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
