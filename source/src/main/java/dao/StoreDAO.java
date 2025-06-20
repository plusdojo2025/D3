package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.Store;

public class StoreDAO {
	public Store login(String storeName, String storePassword) {
	    Store store = null;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection conn = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/d3?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
	                "root", "password");

	        String sql = "SELECT * FROM Store WHERE store_name = ? AND store_password = ?";
	        PreparedStatement pStmt = conn.prepareStatement(sql);
	        pStmt.setString(1, storeName);
	        pStmt.setString(2, storePassword);

	        ResultSet rs = pStmt.executeQuery();

	        if (rs.next()) {
	            int store_id = rs.getInt("store_id");
	            String name = rs.getString("store_name");
	            String pw = rs.getString("store_password");
	            store = new Store(store_id, name, pw);
	        }

	        rs.close();
	        pStmt.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return store;
	}

}
