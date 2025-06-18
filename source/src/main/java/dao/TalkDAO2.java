package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Talk;

public class TalkDAO2 {

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

	// 全件取得
	public List<Talk> selectAll() {
		List<Talk> list = new ArrayList<>();
		String sql = "SELECT customer_id, topic_id, talk_remark FROM talk";
		try (Connection conn = connectDatabase();
			 PreparedStatement ps = conn.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Talk t = new Talk(
					rs.getInt("customer_id"),
					rs.getInt("topic_id"),
					rs.getString("talk_remark")
				);
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 新規登録
	public boolean insert(Talk talk) {
		String sql = "INSERT INTO talk (customer_id, topic_id, talk_remark) VALUES (?, ?, ?)";
		try (Connection conn = connectDatabase();
			 PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, talk.getCustomer_id());
			ps.setInt(2, talk.getTopic_id());
			ps.setString(3, talk.getTalk_remark());
			int result = ps.executeUpdate();
			return result == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// 更新
	public boolean update(Talk talk) {
		String sql = "UPDATE talk SET talk_remark = ? WHERE customer_id = ? AND topic_id = ?";
		try (Connection conn = connectDatabase();
			 PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, talk.getTalk_remark());
			ps.setInt(2, talk.getCustomer_id());
			ps.setInt(3, talk.getTopic_id());
			int result = ps.executeUpdate();
			return result == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// 削除
	public boolean delete(Talk talk) {
		String sql = "DELETE FROM talk WHERE customer_id = ? AND topic_id = ?";
		try (Connection conn = connectDatabase();
			 PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, talk.getCustomer_id());
			ps.setInt(2, talk.getTopic_id());
			int result = ps.executeUpdate();
			return result == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
