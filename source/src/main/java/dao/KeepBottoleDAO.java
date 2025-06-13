package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Commodity;
import dto.KeepBottole;

public class KeepBottoleDAO {
	
	public List<KeepBottole> select(KeepBottole keepBottole) {
		List<KeepBottole> resultList = new ArrayList<KeepBottole>();
		
		String sql = "SELECT commodity.commodity_name, bottole_remaining, bottole_rimit "
				+ "FROM keep_bottole JOIN commondity ON keep_bottole.commodity_id = commodity.commodity_id "
				+ "WHERE customer_id = ?";

		try (Connection conn = connectDatabase(); PreparedStatement pStmt = conn.prepareStatement(sql.toString());) {
			pStmt.setInt(1, keepBottole.getCustomer().getCustomer_id());
			
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				KeepBottole kb = new KeepBottole();
				Commodity commodity = new Commodity(
					0, rs.getString("commodity.commodity_name"), 0, 0, "");
				kb.setCommodity(commodity);
				kb.setBottole_remaining(rs.getInt("bottole_remaining"));
				kb.setBottole_rimit(rs.getTimestamp("bottole_rimit"));
				resultList.add(keepBottole);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			resultList = null;
		}
		
		return resultList;
	}
	
	public boolean insert(KeepBottole keepBottole) {
		String sql = "INSERT INTO keep_bottole (customer_id, commodity_id, bottole_remaining, bottole_rimit) "
				+ "VALUES (?, ?, ?, ?)";
		
		try (Connection conn = connectDatabase(); PreparedStatement pStmt = conn.prepareStatement(sql.toString());) {
			String commodityName = keepBottole.getCommodity().getCommodity_name();
			int commodityId = getCommodityIdByName(conn, commodityName);
		
			pStmt.setInt(1, keepBottole.getCustomer().getCustomer_id());
			pStmt.setInt(2, commodityId);
			pStmt.setInt(3, keepBottole.getBottole_remaining());
			pStmt.setTimestamp(4, keepBottole.getBottole_rimit());
			
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
		String sql = "SELECT id FROM commodity WHERE name = ?";
		try(PreparedStatement pStmt = conn.prepareStatement(sql)) {
			pStmt.setString(1, commodityName);
			
			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("id");
			} else {
				throw new SQLException("商品名が存在しません" + commodityName);
			}
		}
	}
	
	public boolean update(KeepBottole keepBottole) {
		String sql = "UPDATE keep_bottole SET "
				+ "bottole_remaining = ? "
				+ "WHERE bottole_id = ?";
		try (Connection conn = connectDatabase(); PreparedStatement pStmt = conn.prepareStatement(sql.toString());) {
			pStmt.setInt(1, keepBottole.getBottole_remaining());
			pStmt.setInt(2, keepBottole.getBottole_id());
			
			if (pStmt.executeUpdate() == 1) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public boolean delete(KeepBottole keepBottole) {
		String sql = "DELETE FROM keep_bottole WHERE bottole_id = ?";
		try (Connection conn = connectDatabase(); PreparedStatement pStmt = conn.prepareStatement(sql.toString());) {
			pStmt.setInt(1, keepBottole.getBottole_id());
			
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
