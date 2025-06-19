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
import dao.TalkDAO2;
import dao.TopicTagDAO;
import dao.VisitorDAO;
import dto.Customer;
import dto.KeepBottle;
import dto.Talk;
import dto.TopicTag;

@WebServlet("/CustomerListServlet")
public class CustomerListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * // もしもログインしていなかったらログインサーブレットにリダイレクトする HttpSession session =
		 * request.getSession(); if (session.getAttribute("id") == null) {
		 * response.sendRedirect("/D3/LoginServlet"); return; }
		 */

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/StoreStaff.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    /*
	     * // もしもログインしていなかったらログインサーブレットにリダイレクトする
	     * HttpSession session = request.getSession();
	     * if (session.getAttribute("id") == null) {
	     *     response.sendRedirect("/D3/LoginServlet");
	     *     return;
	     * }
	     */

	    request.setCharacterEncoding("UTF-8");
	    String customer_name = request.getParameter("customer_name");
	    VisitorDAO dao = new VisitorDAO();

	    // 顧客基本情報取得
	    CustomerDAO cDao = new CustomerDAO();
	    List<Customer> customerList = cDao.select(new Customer(0, customer_name, "", "", ""));

	 // TalkとTopicTagの取得処理を追加
	    TalkDAO2 talkDao = new TalkDAO2();
	    Map<Integer, List<Talk>> talkMap = new HashMap<>();//顧客数ごとに表示されてしまうのでMAP

	    for (Customer customer : customerList) {
	        List<Talk> talks = talkDao.select(new Talk(customer.getCustomer_id(), null, ""));
	        talkMap.put(customer.getCustomer_id(), talks);
	    }

	    TopicTagDAO tagDao = new TopicTagDAO();
	    List<TopicTag> topicTagList = tagDao.select(new TopicTag());


	    String[] every = new String[customerList.size()];
	    for (int i = 0; i < customerList.size(); i++) {
	        every[i] = dao.getModeOrderByCustomerId(customerList.get(i).getCustomer_id());
	    }

	    // すべてのキープボトル情報取得
	    KeepBottleDAO2 kbDao = new KeepBottleDAO2();
	    List<KeepBottle> keepBottleList = kbDao.selectAll();

	    request.setAttribute("talkMap", talkMap);
	    request.setAttribute("topicTagList", topicTagList);
	    request.setAttribute("every", every);
	    request.setAttribute("customerList", customerList);
	    request.setAttribute("keepBottleList", keepBottleList);

	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/CustomerList.jsp");
	    dispatcher.forward(request, response);
	}

}
