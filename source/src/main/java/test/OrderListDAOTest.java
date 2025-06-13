package test;

import java.util.ArrayList;
import java.util.List;

import dao.OrderListDAO;
import dto.OrderList;

public class OrderListDAOTest {

	public static void showAllData(List<OrderList> orderList) {
		for (OrderList ord : orderList) {
			
			System.out.println("注文ID ：" + ord.getOrder_id());
			System.out.println("商品ID：" + ord.getCommodity_id());
			System.out.println("日時：" + ord.getOrder_datetime());
			System.out.println("個数：" + ord.getOrder_quantity());
			
			System.out.println();
		
		}
	}
	
	public static void main(String[] args) {
		OrderListDAO dao = new OrderListDAO();

		// insert(Event event)のテスト
		System.out.println("---------- insert(List<OrderList> order)のテスト1 ----------");
		List<OrderList> list= new ArrayList<OrderList>();
		list.add(new OrderList(0,1,null,3));
		boolean ins1 = dao.insert(list);
		System.out.println(ins1);
		
		
		//時間指定あり
		//-1は入力がないことを示す数字とする
		System.out.println("---------- select()のテスト1 ----------");
		List<OrderList> sel1 = dao.select_new(new OrderList(-1,-1,"2025-06-13",-1));
		OrderListDAOTest.showAllData(sel1);
		
		//全件検索
		//-1は入力がないことを示す数字とする
		System.out.println("---------- select()のテスト2 ----------");
		List<OrderList> sel2 = dao.select_new(new OrderList(-1,-1,"",-1));
		OrderListDAOTest.showAllData(sel2);
		
		//商品ID検索
		//-1は入力がないことを示す数字とする
		System.out.println("---------- select()のテスト3 ----------");
		List<OrderList> sel3 = dao.select_new(new OrderList(-1,1,"",-1));
		OrderListDAOTest.showAllData(sel3);
		

	}

}
