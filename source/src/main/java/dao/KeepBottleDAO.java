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


public class KeepBottleDAO {
	
	public List<KeepBottle> select(KeepBottle keepBottle) {
		List<KeepBottle> resultList = new ArrayList<KeepBottle>();
		String sql = "SELECT commodity.commodity_name, bottle_remaining, bottle_limit "
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
				kb.setBottle_limit(rs.getTimestamp("bottle_limit"));
				resultList.add(keepBottle);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			resultList = null;
		}
		
		return resultList;
	}
	
	public List<KeepBottle> selectByCustomerId(int customerId) {
		List<KeepBottle> resultList = new ArrayList<>();
		String sql = "SELECT k.bottle_id, k.bottle_remaining, k.bottle_limit, "
				+ "c.commodity_id, c.commodity_name "
				+ "FROM keep_bottle k "
				+ "JOIN commodity c ON k.commodity_id = c.commodity_id "
				+ "WHERE k.customer_id = ?";
		try (Connection conn = connectDatabase(); PreparedStatement pStmt = conn.prepareStatement(sql)) {
			pStmt.setInt(1, customerId);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				KeepBottle kb = new KeepBottle();
				kb.setBottle_id(rs.getInt("bottle_id"));
				kb.setBottle_remaining(rs.getInt("bottle_remaining"));
				kb.setBottle_limit(rs.getTimestamp("bottle_limit"));

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
	
	//全件取得
	public List<KeepBottle> selectAll() {
	    List<KeepBottle> resultList = new ArrayList<>();
	    String sql = "SELECT k.bottle_id, k.bottle_remaining, k.bottle_limit, " +
	                 "k.customer_id, c.commodity_id, c.commodity_name " +
	                 "FROM keep_bottle k " +
	                 "JOIN commodity c ON k.commodity_id = c.commodity_id";

	    try (Connection conn = connectDatabase();
	         PreparedStatement pStmt = conn.prepareStatement(sql);
	         ResultSet rs = pStmt.executeQuery()) {

	        while (rs.next()) {
	            KeepBottle kb = new KeepBottle();
	            kb.setBottle_id(rs.getInt("bottle_id"));
	            kb.setBottle_remaining(rs.getInt("bottle_remaining"));
	            kb.setBottle_limit(rs.getTimestamp("bottle_limit"));

	            Commodity commodity = new Commodity();
	            commodity.setCommodity_id(rs.getInt("commodity_id"));
	            commodity.setCommodity_name(rs.getString("commodity_name"));
	            kb.setCommodity(commodity);

	            Customer customer = new Customer();
	            customer.setCustomer_id(rs.getInt("customer_id"));
	            kb.setCustomer(customer);

	            resultList.add(kb);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return resultList;
	}
	
	public void insertBottle(KeepBottle bottle) {
	    String sql = "INSERT INTO keep_bottle (customer_id, commodity_id, bottle_remaining, bottle_limit) VALUES (?, ?, ?, ?)";
	    try (Connection conn = connectDatabase();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, bottle.getCustomer().getCustomer_id());
	        stmt.setInt(2, bottle.getCommodity().getCommodity_id());
	        stmt.setInt(3, bottle.getBottle_remaining());
	        stmt.setTimestamp(4, bottle.getBottle_limit());
	        stmt.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public boolean insert(KeepBottle keepBottle) {
		String sql = "INSERT INTO keep_bottle (customer_id, commodity_id, bottle_remaining, bottle_limit) "
				+ "VALUES (?, ?, ?, ?)";
		
		try (Connection conn = connectDatabase(); PreparedStatement pStmt = conn.prepareStatement(sql.toString());) {
			String commodityName = keepBottle.getCommodity().getCommodity_name();
			int commodityId = getCommodityIdByName(conn, commodityName);
		
			pStmt.setInt(1, keepBottle.getCustomer().getCustomer_id());
			pStmt.setInt(2, commodityId);
			pStmt.setInt(3, keepBottle.getBottle_remaining());
			pStmt.setTimestamp(4, keepBottle.getBottle_limit());
			
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
	
	public void updateBottleRemaining(int bottleId, int remaining) {
	    String sql = "UPDATE keep_bottle SET bottle_remaining = ? WHERE bottle_id = ?";
	    try (Connection conn = connectDatabase(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, remaining);
	        stmt.setInt(2, bottleId);
	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public boolean delete(KeepBottle keepBottle) {
		String sql = "DELETE FROM keep_bottle WHERE bottole_id = ?";
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

	public void deleteBottle(int bottleId) {
	    String sql = "DELETE FROM keep_bottle WHERE bottle_id = ?";
	    try (Connection conn = connectDatabase(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, bottleId);
	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
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
