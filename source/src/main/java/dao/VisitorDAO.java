package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dto.Commodity;
import dto.Customer;
import dto.TopicTag;
import dto.Visitor;

public class VisitorDAO {

	public List<Visitor> getVisitorByCustomerId(Timestamp ts) {
		String sql = "SELECT customer.customer_name, customer.customer_id "
				+ "FROM orderList "
				+ "JOIN customer on orderList.customer_id = customer.customer_id"
				+ "WHERE ? < order_datetime";
		try (Connection conn = connectDatabase(); PreparedStatement pStmt = conn.prepareStatement(sql.toString());) {
			pStmt.setTimestamp(1, ts);

			ResultSet rs = pStmt.executeQuery();
			List<Visitor> visitorList = new ArrayList<Visitor>();
			while (rs.next()) {
				int id = rs.getInt("customer.customer_id");
				
				Visitor visitor = new Visitor();
				Customer customer = new Customer();
				customer.setCustomer_name(rs.getString("customer_name"));
				visitor.setCustomer(customer);
				
				Commodity commodity = new Commodity(0, "", 0, 0, "");
				commodity.setCommodity_name(getModeOrderByCustomerId(conn, id));
				visitor.setCommodity(commodity);
				
				TopicTag topic = new TopicTag(0, "");
				topic.setTopic_name(getModeTopicByCustomerId(conn, id));
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
			}
			else {
				throw new SQLException("IDが存在しません" + id);
			}
		}
	}
	private String getModeTopicByCustomerId(Connection conn, int id) throws SQLException {
		String sql = "SELECT topic_tag.topic_name "
				+ "FROM talk "
				+ "JOIN topic_tag on talk.topic_id = topic_tag.topic_id "
				+ "WHERE talk.customer_id = ? "
				+ "GROUP BY topic_tag.topic_id, topic_tag.topic_name "
				+ "ORDER BY COUNT(*) DESC "
				+ "LIMIT 1";
		try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
			pStmt.setInt(1, id);
			
			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				return rs.getString("topic_tag.topic_name");
			}
			else {
				throw new SQLException("IDが存在しません" + id);
			}
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
