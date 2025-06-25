package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.Category;

public class CategoryDAO {
	//カテゴリのデータを全て取り出す
	public List<Category>selectAll(){
		Connection conn = null;
		List<Category> categoryList = new ArrayList<Category>();
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースと接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/d3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			//SQL
			String sql = "SELECT * FROM category";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//SQL実行
			ResultSet rs = pStmt.executeQuery();
			
			//結果をリストにコピー
			while(rs.next()) {
				Category category = new Category(
						rs.getInt("category_id"),
						rs.getString("category_name")
						);
				categoryList.add(category);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return categoryList;
	}
	
}