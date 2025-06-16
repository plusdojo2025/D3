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

import dto.Cart;


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
			
				List<Cart> orderList=new ArrayList<Cart>();				
				if (request.getAttribute("orderList") != null) {
					orderList = (List<Cart>) request.getAttribute("orderList");
					// 検索結果をリクエストスコープに格納する
					request.setAttribute("orderList", orderList);
					// OrderListServletにリダイレクトする
					RequestDispatcher dispatcher = request.getRequestDispatcher("/OrderListServlet");
					dispatcher.forward(request, response);
				}else {
					// MenuListServletにリダイレクトする
					RequestDispatcher dispatcher = request.getRequestDispatcher("/MenuListServlet");
					dispatcher.forward(request, response);
				}
		
		
			
			
			

			
		}
	}
	
