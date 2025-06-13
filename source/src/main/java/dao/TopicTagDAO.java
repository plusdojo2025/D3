package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.TopicTag;

public class TopicTagDAO {

    // 全件検索
    public List<TopicTag> select() {
        Connection conn = null;
        List<TopicTag> topicList = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/d3?"
                    + "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
                    "root", "password");

            String sql = "SELECT * FROM topic_tag ORDER BY topic_id";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                TopicTag tag = new TopicTag(
                    rs.getInt("topic_id"),
                    rs.getString("topic_name")
                );
                topicList.add(tag);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            topicList = null;
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return topicList;
    }

    // 登録
    public boolean insert(TopicTag tag) {
        Connection conn = null;
        boolean result = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/d3?"
                    + "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
                    "root", "password");

            String sql = "INSERT INTO topic_tag (topic_name) VALUES (?)";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, tag.getTopic_name());

            if (pStmt.executeUpdate() == 1) {
                result = true;
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    // 更新
    public boolean update(TopicTag tag) {
        Connection conn = null;
        boolean result = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/d3?"
                    + "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
                    "root", "password");

            String sql = "UPDATE topic_tag SET topic_name = ? WHERE topic_id = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, tag.getTopic_name());
            pStmt.setInt(2, tag.getTopic_id());

            if (pStmt.executeUpdate() == 1) {
                result = true;
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    // 削除
    public boolean delete(int topicId) {
        Connection conn = null;
        boolean result = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/d3?"
                    + "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
                    "root", "password");

            String sql = "DELETE FROM topic_tag WHERE topic_id = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, topicId);

            if (pStmt.executeUpdate() == 1) {
                result = true;
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
