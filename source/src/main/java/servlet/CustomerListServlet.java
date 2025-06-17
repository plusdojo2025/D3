package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDAO;
import dao.KeepBottleDAO2;
import dao.TalkDAO;
import dao.VisitorDAO;
import dto.Customer;
import dto.KeepBottle;
import dto.Talk;

@WebServlet("/CustomerListServlet")
public class CustomerListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/D3/LoginServlet");
			return;
		}
		*/

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/StoreStaff.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/D3/LoginServlet");
			return;
		}
		*/
		request.setCharacterEncoding("UTF-8");
		String customer_name = request.getParameter("customer_name");
		VisitorDAO dao = new VisitorDAO();
		String every = dao.getModeOrderByCustomerId(1);
		
		TalkDAO talkDao = new TalkDAO();
		List<Talk> talkList = talkDao.select(new Talk()); // 全件取得または条件を指定して取得

		CustomerDAO cDao = new CustomerDAO();
		List<Customer> customerList = cDao.select(new Customer(0, customer_name, "", "", ""));

		// 顧客ごとにキープボトル情報を取得してMapに格納
		KeepBottleDAO2 kbDao = new KeepBottleDAO2();
		Map<Integer, List<KeepBottle>> keepBottleMap = new HashMap<>();
		for (Customer c : customerList) {
			List<KeepBottle> kbList = kbDao.selectByCustomerId(c.getCustomer_id());
			keepBottleMap.put(c.getCustomer_id(), kbList);
		}

		request.setAttribute("every", every);//いつもの
		request.setAttribute("customerList", customerList);
		request.setAttribute("talkList", talkList);
		request.setAttribute("keepBottleMap", keepBottleMap);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/CustomerList.jsp");
		dispatcher.forward(request, response);
	}
}
