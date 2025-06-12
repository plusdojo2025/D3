package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

import util.DBUtil;

public class StoreDAO {

    /**
     * 店舗ログイン認証（メール＋パスワード）
     */
    public Store login(String email, String password) throws SQLException {
        Store store = null;

        String sql = "SELECT * FROM store WHERE email = ? AND password = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    store = new Store();
                    store.setEmail(rs.getString("email"));
                    store.setPassword(rs.getString("password"));
                    // 必要に応じて他のカラムもセット
                }
            }
        }

        return store;
    }

    /**
     * 顧客ログイン認証（メール＋パスワード）
     */
    public Store loginAsCustomer(String email, String password) throws SQLException {
        Store customer = null;

        String sql = "SELECT * FROM customer WHERE email = ? AND password = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    customer = new Store();
                    customer.setEmail(rs.getString("email"));
                    customer.setPassword(rs.getString("password"));
                    customer.setUsername(rs.getString("name"));
                    customer.setBirthday(rs.getString("birthday"));
                }
            }
        }

        return customer;
    }

    /**
     * 顧客新規登録
     */
    public boolean registerCustomer(Store customer) throws SQLException {
        // メールアドレスの重複チェック
        if (isEmailRegistered(customer.getEmail())) {
            return false; // 登録済み
        }

        String sql = "INSERT INTO customer (email, password, name, birthday) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getEmail());
            stmt.setString(2, customer.getPassword());
            stmt.setString(3, customer.getUsername());
            stmt.setString(4, customer.getBirthday()); // yyyy-MM-dd 形式で送る

            stmt.executeUpdate();
        }

        return true;
    }

    /**
     * 顧客のメールアドレスが既に登録済みかチェック
     */
    public boolean isEmailRegistered(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM customer WHERE email = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }

        return false;
    }

    /**
     * メール形式チェック（@が含まれているか）
     */
    public boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }

    /**
     * 生年月日が未成年かチェック（20歳未満）
     * @param birthday yyyy-MM-dd形式の文字列
     */
    public boolean isUnderage(String birthday) {
        try {
            LocalDate birthDate = LocalDate.parse(birthday); // 入力は yyyy-MM-dd 前提
            LocalDate today = LocalDate.now();
            Period age = Period.between(birthDate, today);
            return age.getYears() < 20;
        } catch (Exception e) {
            // 解析できない形式は未成年扱いにする
            return true;
        }
    }
}

