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
		String sql = "SELECT customer.customer_id, customer.customer_name, customer.customer_id " + "FROM orderList "
				+ "JOIN customer on orderList.customer_id = customer.customer_id " + "WHERE order_datetime >= ? "
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
		String sql = "SELECT commodity.commodity_name "
				+ "FROM orderList "
				+ "JOIN commodity ON commodity.commodity_id = orderList.commodity_id "
				+ "WHERE orderList.customer_id = ? "
				+ "GROUP BY commodity.commodity_id, commodity.commodity_name "
				+ "ORDER BY COUNT(*) DESC " + "LIMIT 1";
		try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
			pStmt.setInt(1, id);

			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				return rs.getString("commodity.commodity_name");
			} else {
				return "?";
			}
		}
	}

	public String getModeOrderByCustomerId(int id) {
		String result = "";
		String sql = "SELECT commodity.commodity_name " + "FROM orderList "
				+ "JOIN commodity ON commodity.commodity_id = orderList.commodity_id "
				+ "WHERE orderList.customer_id = ? " + "GROUP BY commodity.commodity_id, commodity.commodity_name "
				+ "ORDER BY COUNT(*) DESC " + "LIMIT 1";
		try (Connection conn = connectDatabase(); PreparedStatement pStmt = conn.prepareStatement(sql.toString());) {
			pStmt.setInt(1, id);

			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				result = rs.getString("commodity.commodity_name");
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
				return "?";
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
		String sql = "SELECT visit_id, customer.customer_id, customer.customer_name, customer.customer_id "
				+ "FROM visitor "
				+ "JOIN customer on visitor.customer_id = customer.customer_id "
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
