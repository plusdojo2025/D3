package servlet;
	
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.OrderList;


/**
* Servlet implementation class LoginServlet
*/
@WebServlet("/OrderSubmitServlet")
public class OrderSubmitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	*      response)
	*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		
		
	}

	/**
	* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	*      response)
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		// リクエストパラメータを取得する
				request.setCharacterEncoding("UTF-8");
				String company = request.getParameter("company");
				String companysub = request.getParameter("companysub");
				
				

		
		
			List<OrderList> orderlist= new ArrayList<OrderList>();
			for(int i=0;i<5;i++) {
				
				orderlist.add(new OrderList(0,1,null,3));
			}
			
			// 検索結果をリクエストスコープに格納する
			request.setAttribute("orderList", orderlist);
			

			// OrderListServletにリダイレクトする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/OrderListServlet");
			dispatcher.forward(request, response);
		}
	}
	
