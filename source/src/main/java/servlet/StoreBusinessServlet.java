package servlet;
	
import java.io.IOException;
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
@WebServlet("/StoreBusiness")
public class StoreBusinessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	*      response)
	*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		OrderListDAO dao = new OrderListDAO();
		List<OrderList> orderData = dao.select_new(new OrderList(-1,-1,"",-1));
//		
		
		//request.setAttribute("連絡事項", data1);
		request.setAttribute("orderData", orderData);
		//request.setAttribute("来店者", data3);
		
		

		// 業務画面ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/StoreBusiness.jsp");
		dispatcher.forward(request, response);
		
		
		
	}

	/**
	* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	*      response)
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		// 業務画面ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/StoreBusiness.jsp");
				dispatcher.forward(request, response);
				
				// ○○ページにフォワードする
				//RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/○○.jsp");
				//dispatcher.forward(request, response);
		}
	}
	
