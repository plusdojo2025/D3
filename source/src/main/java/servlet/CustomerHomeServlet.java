package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EventDAO;
import dao.KeepBottleDAO;
import dao.OrderListDAO;
import dto.Customer;
import dto.Event;
import dto.KeepBottle;
import dto.OrderList;

@WebServlet("/CustomerHomeServlet")
public class CustomerHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	
	public class Pair<K, V> {
	    private K key;
	    private V value;

	    public Pair(K key, V value) {
	        this.key = key;
	        this.value = value;
	    }

	    public K getKey() { return key; }
	    public V getValue() { return value; }
	}
	
	/**
	 * 顧客ホーム画面表示処理（GET）
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// セッションからログイン中の顧客情報を取得
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer) session.getAttribute("customer");
		// ログインしていない場合はログイン画面へリダイレクト
//		if (loginCustomer == null) {
//			response.sendRedirect("/D3/LoginServlet");
//			return;
//		}
		// お知らせ取得（最新3件）
		EventDAO dao = new EventDAO();
		List<Event> eventList =dao.select();
		request.setAttribute("eventList", eventList);

		
		if(loginCustomer==null) {
			request.setAttribute("nickname", "ゲスト");
		}
		else {
		
		// ニックネーム表示用にセット
		String nickname = loginCustomer.getCustomer_name();
		request.setAttribute("nickname", nickname);
		
		
		
		// キープボトル情報取得
		KeepBottleDAO dao2 = new KeepBottleDAO();
		List<KeepBottle> bottleList = dao2.selectByCustomerId(loginCustomer.getCustomer_id());
		request.setAttribute("bottleList", bottleList);
		}
		
		//ランキング
		// 昨日の日付の取得
		LocalDate today = LocalDate.now();
		String yesterday = today.minusDays(1).toString();
		// 昨日の月
		String yesterdayMonth = yesterday.substring(0, 7);
		
		
		OrderListDAO orderListDAO = new OrderListDAO();
		
		// topCount分のランキング取得
		int topCount = 3;
		List<OrderList> topOrders = orderListDAO.getTopCommoditiesByDate(topCount, yesterdayMonth);
		request.setAttribute("topCommodity", topOrders);
		
		/*
		OrderListDAO ordDao = new OrderListDAO();
		CommodityDAO comDao = new CommodityDAO();	
		
		Date today = Date.valueOf(LocalDate.now());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(today);
        

		//商品取得
        List<Commodity> list2 = comDao.selectByCategoryWithPage(2,1,50);
		List<Commodity> list3 = comDao.selectByCategoryWithPage(3,1,50);
		List<Commodity> list4 = comDao.selectByCategoryWithPage(4,1,50);
		
		int[] list2Sum=new int[list2.size()];
		int[] list3Sum=new int[list3.size()];
		int[] list4Sum=new int[list4.size()];
		
		//商品ごとの合計値を計算！！！！！
				
				for(int i=0;i<list2.size();i++) {		
					list2Sum[i] = ordDao.quantity2(list2.get(i).getCommodity_id());
				}
				
				for(int i=0;i<list3.size();i++) {		
					list3Sum[i] = ordDao.quantity2(list3.get(i).getCommodity_id());
				}
				
				for(int i=0;i<list4.size();i++) {		
					list4Sum[i] = ordDao.quantity2(list4.get(i).getCommodity_id());
				}
				
				
				
		List<Commodity> best3drink = new ArrayList<Commodity>();
		
		//ドリンク
        // 合体後の配列サイズは両方の長さの合計
        int[] merged = new int[list2Sum.length + list3Sum.length +list4Sum.length];
        System.arraycopy(list2Sum, 0, merged, 0, list2Sum.length);
        System.arraycopy(list3Sum, 0, merged, list2Sum.length, list3Sum.length);
        System.arraycopy(list4Sum, 0, merged, list2Sum.length+list3Sum.length, list4Sum.length);
        
        Integer[] best3drinkSum = Arrays.stream(merged).boxed().toArray(Integer[]::new);
        Arrays.sort(best3drinkSum, (a, b) -> b - a);
        
   
        
        for(int i=0;i<list2.size();i++) {		
			if(list2Sum[i] == best3drinkSum[0]) {
				best3drink.add(0, list2.get(i));			
			}
		}
        for(int i=0;i<list3.size();i++) {		
			if(list3Sum[i] == best3drinkSum[0]) {
				best3drink.add(0, list3.get(i));			
			}
		}
        for(int i=0;i<list4.size();i++) {		
			if(list4Sum[i] == best3drinkSum[0]) {
				best3drink.add(0, list4.get(i));			
			}
		}
        
        
        
        for(int i=0;i<list2.size();i++) {		
			if(list2Sum[i] == best3drinkSum[1]&&list2.get(i)!=best3drink.get(0)) {
				best3drink.add(1, list2.get(i));			
			}
		}
        for(int i=0;i<list3.size();i++) {		
			if(list3Sum[i] == best3drinkSum[1]&&list3.get(i)!=best3drink.get(0)) {
				best3drink.add(1, list3.get(i));			
			}
		}
        for(int i=0;i<list4.size();i++) {		
			if(list4Sum[i] == best3drinkSum[1]&&list4.get(i)!=best3drink.get(0)) {
				best3drink.add(1, list4.get(i));			
			}
		}
        
        for(int i=0;i<list2.size();i++) {		
			if(list2Sum[i] == best3drinkSum[2]&&list2.get(i)!=best3drink.get(0)&&list2.get(i)!=best3drink.get(1)) {
				best3drink.add(2, list2.get(i));			
			}
		}
   
        for(int i=0;i<list3.size();i++) {		
			if(list3Sum[i] == best3drinkSum[2]&&list3.get(i)!=best3drink.get(0)&&list3.get(i)!=best3drink.get(1)) {
				best3drink.add(2, list3.get(i));			
			}
		}
        
        for(int i=0;i<list4.size();i++) {		
			if(list4Sum[i] == best3drinkSum[2]&&list4.get(i)!=best3drink.get(0)&&list4.get(i)!=best3drink.get(1)) {
				best3drink.add(2, list4.get(i));			
			}
		}

        request.setAttribute("best3drink", best3drink);
        request.setAttribute("best3drinkSum", best3drinkSum);
        */
		
		// 顧客ホーム画面へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/CustomerHome.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * POSTはGETと同じ処理にする
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}

