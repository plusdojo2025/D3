package test;

import java.util.List;

import dao.CommodityDAO;
import dto.Commodity;

public class CommodityDAOTest {	
	public static void showAllData(List<Commodity> commodityList) {
		for (Commodity commodity : commodityList) {
			
			System.out.println("商品ID" + commodity.getCommodity_id());
			System.out.println("商品名" + commodity.getCommodity_name());
			System.out.println("価格" + commodity.getCommodity_price());
			System.out.println("カテゴリ" + commodity.getCommodity_category());
			System.out.println("イメージ" + commodity.getCommodity_image());
			
			System.out.println();
		}
	}
	public static void main(String[]args) {
		CommodityDAO dao = new CommodityDAO();
		
		//ページング付きのテスト（カテゴリ＝1,ページ1,3件)
		System.out.println("---------- selectByCategory(1) のテスト ----------");
		List<Commodity> listPaged = dao.selectByCategoryWithPage(1,1,3);
		showAllData(listPaged);
	}
}
