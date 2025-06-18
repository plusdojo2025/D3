package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Customer;

public class CustomerDAO {

    // 顧客ログイン（メールとパスワードで認証）
    public Customer login(String email, String password) {
        Connection conn = null;
        Customer customer = null;

        try {
            // JDBCドライバ読み込み
            Class.forName("com.mysql.cj.jdbc.Driver");

            // DB接続情報は環境に合わせて変更してください
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/d3?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
                "root", "password");

            // SQL文準備
            String sql = "SELECT * FROM customer WHERE customer_email = ? AND customer_password = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, email);
            pStmt.setString(2, password);

            // SQL実行
            ResultSet rs = pStmt.executeQuery();

            // 結果があればCustomerオブジェクトにセット
            if (rs.next()) {
                customer = new Customer();
                customer.setCustomer_id(rs.getInt("customer_id"));
                customer.setCustomer_name(rs.getString("customer_name"));
                customer.setCustomer_email(rs.getString("customer_email"));
                customer.setCustomer_password(rs.getString("customer_password"));
                customer.setCustomer_birthday(rs.getString("customer_birthday"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // 接続解除
            if (conn != null) {
                try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }

        return customer;  // nullならログイン失敗
    }
}
