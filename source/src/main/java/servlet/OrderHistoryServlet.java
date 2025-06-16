package servlet;
	
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommodityDAO;
import dao.OrderListDAO;
import dto.Commodity;


/**
* Servlet implementation class LoginServlet
*/
@WebServlet("/OrderHistoryServlet")
public class OrderHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	*      response)
	*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		

		
		OrderListDAO ordDao = new OrderListDAO();
		CommodityDAO comDao = new CommodityDAO();	
		
		Date today = Date.valueOf(LocalDate.now());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(today);
        
        //カテゴリnの商品リストの取得
        
		//（カテゴリ＝1,ページ1,3件)
		List<Commodity> list1food = comDao.selectByCategoryWithPage(1,1,50);
		List<Commodity> list2 = comDao.selectByCategoryWithPage(2,1,50);
		List<Commodity> list3 = comDao.selectByCategoryWithPage(3,1,50);
		List<Commodity> list4 = comDao.selectByCategoryWithPage(4,1,50);
		
		int[] list1foodSum=new int[list1food.size()];
		int[] list2Sum=new int[list2.size()];
		int[] list3Sum=new int[list3.size()];
		int[] list4Sum=new int[list4.size()];
		
		//商品ごとの合計値を計算
		for(int i=0;i<list1food.size();i++) {		
			list1foodSum[i] = ordDao.quantity(list1food.get(i).getCommodity_id(),dateString);
		}
		
		for(int i=0;i<list2.size();i++) {		
			list2Sum[i] = ordDao.quantity(list2.get(i).getCommodity_id(),dateString);
		}
		
		for(int i=0;i<list3.size();i++) {		
			list3Sum[i] = ordDao.quantity(list3.get(i).getCommodity_id(),dateString);
		}
		
		for(int i=0;i<list4.size();i++) {		
			list4Sum[i] = ordDao.quantity(list4.get(i).getCommodity_id(),dateString);
		}
		
		

		//商品の種類と合計値をセットで送る（上半分）
		request.setAttribute("today", dateString);
		
		request.setAttribute("list1food", list1food);
		request.setAttribute("list2", list2);
		request.setAttribute("list3", list3);
		request.setAttribute("list4", list4);
		
		request.setAttribute("list1foodSum", list1foodSum);
		request.setAttribute("list2Sum", list2Sum);
		request.setAttribute("list3Sum", list3Sum);
		request.setAttribute("list4Sum", list4Sum);
		
        
	       
        
        //フード・ドリンクのランキング
		List<Commodity> best3food = new ArrayList<Commodity>();
		List<Commodity> best3drink = new ArrayList<Commodity>();
		

		// 合計値配列を大きい順にソート
		//フード
        Integer[] best3foodSum = Arrays.stream(list1foodSum).boxed().toArray(Integer[]::new);
        Arrays.sort(best3foodSum, (a, b) -> b - a);
        //同じ数があると同じ項目が出てしまう
		for(int i=0;i<list1food.size();i++) {		
			if(list1foodSum[i] == best3foodSum[0]) {
					best3food.add(0, list1food.get(i));			
			}
		}
		
		for(int i=0;i<list1food.size();i++) {		
			if(list1foodSum[i] == best3foodSum[1]&&list1food.get(i)!=best3food.get(0)) {
					best3food.add(1, list1food.get(i));			
			}
		}
		
		for(int i=0;i<list1food.size();i++) {		
			if(list1foodSum[i] == best3foodSum[2]&&list1food.get(i)!=best3food.get(0)&&list1food.get(i)!=best3food.get(1)) {
					best3food.add(2, list1food.get(i));			
			}
		}
		
        
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
        
        
        
        
        
        request.setAttribute("best3food", best3food);
        request.setAttribute("best3drink", best3drink);
        request.setAttribute("best3foodSum", best3foodSum);
        request.setAttribute("best3drinkSum", best3drinkSum);
		
		// 注文履歴ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/OrderHistory.jsp");
		dispatcher.forward(request, response);
		
		
		
	}

	/**
	* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	*      response)
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

				// ○○ページにフォワードする
				//RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/○○.jsp");
				//dispatcher.forward(request, response);
		}
	}
	
