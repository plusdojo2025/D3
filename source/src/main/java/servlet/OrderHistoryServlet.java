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
import javax.servlet.http.HttpSession;

import dao.CommodityDAO;
import dao.OrderListDAO;
import dto.Commodity;
import dto.Store;


/**
* Servlet implementation class LoginServlet
*/
@WebServlet("/OrderHistoryServlet")
public class OrderHistoryServlet extends HttpServlet {
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
	* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	*      response)
	*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
    	// セッションからログイン中の店舗情報を取得
		HttpSession session = request.getSession();
		Store loginStore = (Store) session.getAttribute("store");

	// ログインしていない場合はログイン画面へリダイレクト
	if (loginStore == null) {
		response.sendRedirect(request.getContextPath() + "/LoginServlet");
		return;
	}
		
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
		
		
		
		// Step 1: Commodity + 注文数をペアにする
				List<Pair<Commodity, Integer>> ranked1 = new ArrayList<>();
				for (int i = 0; i < list2.size(); i++) {
				    ranked1.add(new Pair<>(list1food.get(i), list1foodSum[i]));
				}

				// Step 2: 注文数の降順でソート
				ranked1.sort((a, b) -> b.getValue() - a.getValue());

				// Step 3: 分離して並び替え済リストに変換
				List<Commodity> sortedList1 = new ArrayList<>();
				int[] sortedSum1 = new int[ranked1.size()];
				for (int i = 0; i < ranked1.size(); i++) {
				    sortedList1.add(ranked1.get(i).getKey());
				    sortedSum1[i] = ranked1.get(i).getValue();
				}
		
		// Step 1: Commodity + 注文数をペアにする
		List<Pair<Commodity, Integer>> ranked2 = new ArrayList<>();
		for (int i = 0; i < list2.size(); i++) {
		    ranked2.add(new Pair<>(list2.get(i), list2Sum[i]));
		}

		// Step 2: 注文数の降順でソート
		ranked2.sort((a, b) -> b.getValue() - a.getValue());

		// Step 3: 分離して並び替え済リストに変換
		List<Commodity> sortedList2 = new ArrayList<>();
		int[] sortedSum2 = new int[ranked2.size()];
		for (int i = 0; i < ranked2.size(); i++) {
		    sortedList2.add(ranked2.get(i).getKey());
		    sortedSum2[i] = ranked2.get(i).getValue();
		}
		
		
		// Step 1: Commodity + 注文数をペアにする
				List<Pair<Commodity, Integer>> ranked3 = new ArrayList<>();
				for (int i = 0; i < list3.size(); i++) {
				    ranked3.add(new Pair<>(list3.get(i), list3Sum[i]));
				}

				// Step 2: 注文数の降順でソート
				ranked3.sort((a, b) -> b.getValue() - a.getValue());

				// Step 3: 分離して並び替え済リストに変換
				List<Commodity> sortedList3 = new ArrayList<>();
				int[] sortedSum3 = new int[ranked3.size()];
				for (int i = 0; i < ranked3.size(); i++) {
				    sortedList3.add(ranked3.get(i).getKey());
				    sortedSum3[i] = ranked3.get(i).getValue();
				}
				
				// Step 1: Commodity + 注文数をペアにする
				List<Pair<Commodity, Integer>> ranked4 = new ArrayList<>();
				for (int i = 0; i < list4.size(); i++) {
				    ranked4.add(new Pair<>(list4.get(i), list4Sum[i]));
				}

				// Step 2: 注文数の降順でソート
				ranked4.sort((a, b) -> b.getValue() - a.getValue());

				// Step 3: 分離して並び替え済リストに変換
				List<Commodity> sortedList4 = new ArrayList<>();
				int[] sortedSum4 = new int[ranked4.size()];
				for (int i = 0; i < ranked4.size(); i++) {
				    sortedList4.add(ranked4.get(i).getKey());
				    sortedSum4[i] = ranked4.get(i).getValue();
				}
				
		

	

		//商品の種類と合計値をセットで送る（上半分）
		request.setAttribute("today", dateString);
		
		request.setAttribute("list1food", sortedList1);
		request.setAttribute("list2", sortedList2);
		request.setAttribute("list3", sortedList3);
		request.setAttribute("list4", sortedList4);
		
		request.setAttribute("list1foodSum", sortedSum1);
		request.setAttribute("list2Sum", sortedSum2);
		request.setAttribute("list3Sum", sortedSum3);
		request.setAttribute("list4Sum", sortedSum4);
		
        
	       
        
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
	
