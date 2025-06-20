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
import javax.servlet.http.HttpSession;

import dao.CommodityDAO2;
import dao.CustomerDAO;
import dao.KeepBottleDAO;
import dao.TalkDAO;
import dao.TopicTagDAO;
import dao.VisitorDAO;
import dto.Commodity;
import dto.Customer;
import dto.KeepBottle;
import dto.Store;
import dto.Talk;
import dto.TopicTag;

@WebServlet("/CustomerListServlet")
public class CustomerListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		// セッションからログイン中の顧客情報を取得
		HttpSession session = request.getSession();
		Store loginStore = (Store) session.getAttribute("store");

	// ログインしていない場合はログイン画面へリダイレクト
	if (loginStore == null) {
		response.sendRedirect(request.getContextPath() + "/LoginServlet");
		return;
	}

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
	    TalkDAO talkDao = new TalkDAO();
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
	    KeepBottleDAO kbDao = new KeepBottleDAO();
	    List<KeepBottle> keepBottleList = kbDao.selectAll();
	    
	    //キープボトル登録
	    CommodityDAO2 commodityDao = new CommodityDAO2();
	    List<Commodity> commodityList = commodityDao.selectAll();

	    request.setAttribute("talkMap", talkMap);
	    request.setAttribute("topicTagList", topicTagList);
	    request.setAttribute("every", every);
	    request.setAttribute("customerList", customerList);
	    request.setAttribute("keepBottleList", keepBottleList);
	    request.setAttribute("commodityList", commodityList);

	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/CustomerList.jsp");
	    dispatcher.forward(request, response);
	}

}
