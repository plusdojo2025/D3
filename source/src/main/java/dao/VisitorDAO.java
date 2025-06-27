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
import dto.TopicTag;
import dto.Visitor;

public class VisitorDAO {

	public List<Visitor> getVisitorByDate(String date) {
		String sql = "SELECT customer.customer_id, customer.customer_name, customer.customer_id " + "FROM orderlist "
				+ "JOIN customer on orderlist.customer_id = customer.customer_id " + "WHERE order_datetime >= ? "
				+ "GROUP BY customer.customer_name, customer.customer_id";
		try (Connection conn = connectDatabase(); PreparedStatement pStmt = conn.prepareStatement(sql.toString());) {
			pStmt.setString(1, date);

			ResultSet rs = pStmt.executeQuery();
			List<Visitor> visitorList = new ArrayList<Visitor>();
			while (rs.next()) {
				int customerId = rs.getInt("customer.customer_id");

				Visitor visitor = new Visitor();

				Customer customer = new Customer();
				customer.setCustomer_id(customerId);
				customer.setCustomer_name(rs.getString("customer_name"));
				visitor.setCustomer(customer);

				Commodity commodity = new Commodity(0, "", 0, 0, "");
				commodity.setCommodity_name(getModeOrderByCustomerId(conn, customerId));
				visitor.setCommodity(commodity);

				TopicTag topic = new TopicTag(0, "");
				topic.setTopic_name(getModeTopicByCustomerId(conn, customerId));
				visitor.setTopic(topic);

				visitorList.add(visitor);
			}

			return visitorList;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private String getModeOrderByCustomerId(Connection conn, int id) throws SQLException {
		String sql = "SELECT c.commodity_name, "
				+ "SUM(o.order_quantity) AS total_quantity "
				+ "FROM orderlist o JOIN commodity c "
				+ "ON c.commodity_id = o.commodity_id "
				+ "WHERE o.customer_id = ? "
				+ "GROUP BY c.commodity_id, c.commodity_name "
				+ "ORDER BY total_quantity "
				+ "DESC LIMIT 1";
		try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
			pStmt.setInt(1, id);

			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				return rs.getString("commodity_name");
			} else {
				return "いつものなし";
			}
		}
	}

	public String getModeOrderByCustomerId(int id) {
		String result = "";
		String sql = "SELECT c.commodity_name, "
				+ "SUM(o.order_quantity) AS total_quantity "
				+ "FROM orderlist o JOIN commodity c "
				+ "ON c.commodity_id = o.commodity_id "
				+ "WHERE o.customer_id = ? "
				+ "GROUP BY c.commodity_id, c.commodity_name "
				+ "ORDER BY total_quantity "
				+ "DESC LIMIT 1";
		try (Connection conn = connectDatabase(); PreparedStatement pStmt = conn.prepareStatement(sql.toString());) {
			pStmt.setInt(1, id);

			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				result = rs.getString("commodity_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	private String getModeTopicByCustomerId(Connection conn, int id) throws SQLException {
		String sql = "SELECT topic_tag.topic_name " + "FROM talk "
				+ "JOIN topic_tag on talk.topic_id = topic_tag.topic_id " + "WHERE talk.customer_id = ? "
				+ "GROUP BY topic_tag.topic_id, topic_tag.topic_name " + "ORDER BY COUNT(*) DESC " + "LIMIT 1";
		try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
			pStmt.setInt(1, id);

			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				return rs.getString("topic_tag.topic_name");
			} else {
				return "トピックなし";
			}
		}
	}
	
	public boolean insertVisitor(int customerId, int storeId) {
		String sql = "INSERT INTO visitor "
				+ "(customer_id, store_id, visit_time) "
				+ "VALUES (?, ?, NOW())";
		try (Connection conn = connectDatabase(); PreparedStatement pStmt = conn.prepareStatement(sql.toString());) {
			pStmt.setInt(1, customerId);
			pStmt.setInt(2, storeId);
			
			if (pStmt.executeUpdate() == 1)
				return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return false;
	}
	public int getVisitorId(int customerId, int storeId) {
		String sql = "SELECT visit_id FROM visitor "
				+ "WHERE customer_id = ? AND store_id = ?"
				+ " AND exit_time IS NULL";
		try (Connection conn = connectDatabase(); PreparedStatement pStmt = conn.prepareStatement(sql.toString());) {
			pStmt.setInt(1, customerId);
			pStmt.setInt(2, storeId);
			
			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("visit_id");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return -1;
	}
	
	public boolean updateVisitorExit(int visitId) {
		String sql = "UPDATE visitor "
				+ "SET exit_time = NOW() "
				+ "WHERE visit_id = ?";
		try (Connection conn = connectDatabase(); PreparedStatement pStmt = conn.prepareStatement(sql.toString());) {
			pStmt.setInt(1, visitId);
			
			if (pStmt.executeUpdate() == 1)
				return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public List<Visitor> getCurrentVisitorByStoreId(int storeId) {
		String sql = "SELECT visitor.visit_id, customer.customer_id, customer.customer_name "
				+ "FROM visitor "
				+ "JOIN customer ON visitor.customer_id = customer.customer_id "
				+ "WHERE visitor.store_id = ? "
				+ "AND visitor.exit_time IS NULL";
		try (Connection conn = connectDatabase(); PreparedStatement pStmt = conn.prepareStatement(sql.toString());) {
			pStmt.setInt(1, storeId);
			
			ResultSet rs = pStmt.executeQuery();
			List<Visitor> visitorList = new ArrayList<Visitor>();
			while (rs.next()) {
				int customerId = rs.getInt("customer.customer_id");

				Visitor visitor = new Visitor();
				visitor.setVisit_id(rs.getInt("visit_id"));

				Customer customer = new Customer();
				customer.setCustomer_id(customerId);
				customer.setCustomer_name(rs.getString("customer_name"));
				visitor.setCustomer(customer);

				Commodity commodity = new Commodity(0, "", 0, 0, "");
				commodity.setCommodity_name(getModeOrderByCustomerId(conn, customerId));
				visitor.setCommodity(commodity);

				TopicTag topic = new TopicTag(0, "");
				topic.setTopic_name(getModeTopicByCustomerId(conn, customerId));
				visitor.setTopic(topic);

				visitorList.add(visitor);
			}

			return visitorList;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean isCurrentVisitByVisitId(int visitId) {
		String sql = "SELECT visit_id FROM visitor "
				+ "WHERE visit_id = ? AND exit_time IS NULL";
		try (Connection conn = connectDatabase(); PreparedStatement pStmt = conn.prepareStatement(sql.toString());) {
			pStmt.setInt(1, visitId);
			
			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return false;
	}
	
	public boolean isCustomerNotVisit(int customerId) {
		String sql = "SELECT visit_id "
				+ "FROM visitor "
				+ "WHERE customer_id = ? "
				+ "AND exit_time IS NULL";
		try (Connection conn = connectDatabase(); PreparedStatement pStmt = conn.prepareStatement(sql.toString());) {
			pStmt.setInt(1, customerId);
			
			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				return false;
			}
			
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
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
