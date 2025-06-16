package test;

import java.util.List;

import dao.CommodityDAO;
import dto.Commodity;

public class CommodityDAOTest {	
	public static void showAllData(List<Commodity> commodityList) {
		if (commodityList.isEmpty()) {
			System.out.println("カテゴリに該当する商品がありません。");
			return;}
		
		for (Commodity commodity : commodityList) {
			
			System.out.println("商品ID " + commodity.getCommodity_id());
			System.out.println("商品名 " + commodity.getCommodity_name());
			System.out.println("価格 " + commodity.getCommodity_price());
			System.out.println("カテゴリ " + commodity.getCommodity_category());
			System.out.println("イメージ " + commodity.getCommodity_image());
			
			System.out.println();
		}
	}
	public static void main(String[]args) {
		CommodityDAO dao = new CommodityDAO();
		
		//ページング付きのテスト（カテゴリ＝1,ページ1,3件)
		System.out.println("---------- selectByCategory(1) のテスト ----------");
		List<Commodity> listPaged1 = dao.selectByCategoryWithPage(1,1,3);
		showAllData(listPaged1);
		
		//ページング付きのテスト（カテゴリ＝2,ページ2,3件)
		System.out.println("---------- selectByCategory(2) のテスト ----------");
		List<Commodity> listPaged02 = dao.selectByCategoryWithPage(2,1,3);
		showAllData(listPaged02);
		
		//ページング付きのテスト（カテゴリ＝2,ページ2,3件)
		System.out.println("---------- selectByCategory(2-1) のテスト ----------");
		List<Commodity> listPaged12 = dao.selectByCategoryWithPage(2,2,3);
		showAllData(listPaged12);
		
		//カテゴリがない場合のテスト
		System.out.println("---------- selectByCategory(not)のテスト----------");
		List <Commodity>listPaged999 = dao.selectByCategoryWithPage(999, 1, 1);
		showAllData(listPaged999);
	}
}
