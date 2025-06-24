package test;

import java.util.List;

import dao.EventDAO;
import dto.Event;

public class EventDAOTest {
	
	public static void showAllData(List<Event> eventList) {
		for (Event event : eventList) {
			
			System.out.println("イベントID：" + event.getEvent_id());
			System.out.println("店舗ID：" + event.getStore_id());
			System.out.println("日付：" + event.getEvent_date());
			System.out.println("イベント名：" + event.getEvent_name());
			System.out.println("備考：" + event.getEvent_remark());
			System.out.println();
		
		}
			
			
	}
	public static void main(String[] args) {
		EventDAO dao = new EventDAO();

		// select()のテスト
		System.out.println("---------- select()のテスト1 ----------");
		List<Event> sel1 = dao.select();
		EventDAOTest.showAllData(sel1);
		
		// insert(Event event)のテスト
		System.out.println("---------- insert(Event event)のテスト1 ----------");
		boolean ins1 = dao.insert(new Event(0,1,null,"ハロウィン","仮装したらドリンクプレゼント"));
		System.out.println(ins1);
		List<Event> sel2 = dao.select();
		EventDAOTest.showAllData(sel2);
		
		System.out.println("---------- insert(Event event)のテスト2 ----------");
		boolean ins2 = dao.insert(new Event(0,1,"2002-01-02","正月","くじ引きあり"));
		System.out.println(ins2);
		
	}

}
