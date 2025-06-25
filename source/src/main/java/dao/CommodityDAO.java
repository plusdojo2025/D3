package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.Commodity;

public class CommodityDAO {
	// 商品のカテゴリごとにデータを取り出す
	public List<Commodity> selectByCategoryWithPage(int category_id,int page, int itemsPerPage) {
		Connection conn = null;
		List<Commodity> commodityList = new ArrayList<Commodity>();

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースと接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/d3?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			int offset = (page - 1) * itemsPerPage;

			// SQL文を準備する
			String sql = "SELECT * FROM commodity WHERE commodity_category = ? LIMIT ? OFFSET ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, category_id);
			pStmt.setInt(2, itemsPerPage);//1ぺージの件数
			pStmt.setInt(3, offset);//開始位置
			
			
			ResultSet rs = pStmt.executeQuery();
			
			//結果をコレクションにコピー
			while (rs.next()) {
				Commodity card = new Commodity(rs.getInt("commodity_id"), rs.getString("commodity_name"),
						rs.getInt("commodity_price"), rs.getInt("commodity_category"), rs.getString("commodity_image"));
				commodityList.add(card);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return commodityList;
	}
	
	// 全件取得
	public List<Commodity> selectAll() {
	    List<Commodity> commodityList = new ArrayList<>();

	    try (Connection conn = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/d3?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
	                "root", "password");
	         PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM commodity");
	         ResultSet rs = pStmt.executeQuery()) {

	        while (rs.next()) {
	            Commodity commodity = new Commodity(
	                rs.getInt("commodity_id"),
	                rs.getString("commodity_name"),
	                rs.getInt("commodity_price"),
	                rs.getInt("commodity_category"),
	                rs.getString("commodity_image")
	            );
	            commodityList.add(commodity);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return commodityList;
	}
	
	//キープボトルカテゴリ用
	public List<Commodity> selectKeepBottleCommodities() {
	    List<Commodity> commodityList = new ArrayList<>();
	    int keepBottleCategoryId = 5;

	    try (Connection conn = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/d3?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
	                "root", "password");
	         PreparedStatement pStmt = conn.prepareStatement(
	             "SELECT * FROM commodity WHERE commodity_category = ?")) {

	        pStmt.setInt(1, keepBottleCategoryId);
	        ResultSet rs = pStmt.executeQuery();

	        while (rs.next()) {
	            Commodity commodity = new Commodity(
	                rs.getInt("commodity_id"),
	                rs.getString("commodity_name"),
	                rs.getInt("commodity_price"),
	                rs.getInt("commodity_category"),
	                rs.getString("commodity_image")
	            );
	            commodityList.add(commodity);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return commodityList;
	}
}
