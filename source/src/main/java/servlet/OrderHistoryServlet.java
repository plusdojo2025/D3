package servlet;
	
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderListDAO;
import dto.OrderList;


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
		
		Date today = Date.valueOf(LocalDate.now());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(today);
        
        List<List<OrderList>> list = new ArrayList<List<OrderList>>();
        //カテゴリnの商品リストの取得
		//商品ごとの合計値を計算
        //商品の種類と合計値をセットで送る（日時どうする？）
       
        //フードリストの商品コードを指定して合計値を計算
        //ドリンクリストの商品コードを指定して合計値を計算
        
        
        
		//request.setAttribute("sum", sum[]);

		
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
	
