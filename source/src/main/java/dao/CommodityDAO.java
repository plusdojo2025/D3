package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.Commodity;

public class CommodityDAO {
	// 引数Commodityで指定された項目で検索して、取得されたデータのリストを返す
	public List<Commodity> selectAll() {
		Connection conn = null;
		List<Commodity> commodityList = new ArrayList<Commodity>();

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースと接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "SELECT * FROM Commodity";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();

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
}
