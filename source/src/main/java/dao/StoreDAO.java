package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Store;

public class StoreDAO {

    // 店舗ログイン（メールとパスワードで認証）
    public Store login(String email, String password) {
        Connection conn = null;
        Store store = null;

        try {
            // JDBCドライバを読み込む
            Class.forName("com.mysql.cj.jdbc.Driver");

            // データベースに接続する
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/d3?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
                "root", "password");

            // SQL文を準備する
            String sql = "SELECT * FROM store WHERE store_email = ? AND store_password = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, email);
            pStmt.setString(2, password);

            // SQL実行
            ResultSet rs = pStmt.executeQuery();

            // 結果があればStoreオブジェクトに格納
            if (rs.next()) {
                store = new Store();
               
                store.setStoreName(rs.getString("store_name"));
                store.setEmail(rs.getString("store_email"));
                store.setPassword(rs.getString("store_password"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // 接続解除
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        
        }
        return store; // ログイン成功時はStoreオブジェクト、失敗時はnull
    }
}

