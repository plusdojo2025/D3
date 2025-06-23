package servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
import dao.OrderListDAO;
import dao.StoreMemoDAO;
import dao.VisitorDAO;
import dto.Event;
import dto.OrderList;
import dto.Store;
import dto.StoreMemo;
import dto.Visitor;


@WebServlet("/StoreStaffServlet")
public class StoreStaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		
		// 来店者一覧

		Date today = Date.valueOf(LocalDate.now());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(today);

		// TODO 現時点注文済みの客の情報のみ表示
		VisitorDAO dao = new VisitorDAO();
		List<Visitor> visitor = dao.getVisitorByDate(dateString);

		request.setAttribute("visitor", visitor);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/StoreStaff.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// イベント登録
		String message = "";

		String eventdate = request.getParameter("event_date");
		String eventname = request.getParameter("event_name");
		String eventremark = request.getParameter("event_remark");

		HttpSession session = request.getSession();
		Store store = (Store)session.getAttribute("store");
		int storeId = store.getStore_id();
		
		if (eventdate != null && !eventdate.equals("") && !eventname.equals("") && !eventremark.equals("")) {
			EventDAO eventDao = new EventDAO();			
			boolean ins = eventDao.insert(new Event(0, storeId, eventdate, eventname, eventremark));
			if (ins == true) {

				request.setAttribute("eventList", ("新しいイベントを登録しました。"));

				message = ("新しいイベントを登録しました。");

			} else {
				message = "イベントの登録に失敗しました。";
			}
			System.out.println(eventdate);
			System.out.println(message);
		}
		// イベント更新
		/*
		 * if (request.getParameter("update").equals("更新")) { if(eventDao.update(new
		 * Event(0, 0, eventdate,eventname,eventremark))) { message =
		 * ("イベント内容を更新しました。"); } }
		 */

		// 業務連絡登録
		String storedate = request.getParameter("store_date");
		String storeremark = request.getParameter("store_remark");

		if (storedate != null && !storedate.equals("") && !storeremark.equals("")) {
			StoreMemoDAO storemDao = new StoreMemoDAO();
			boolean ins1 = storemDao.insert(new StoreMemo(storeId, storedate, storeremark));
			

			if (ins1) {
				// 空のStoreMemoを渡して全件取得（DAO変更なしで実現）
				List<StoreMemo> memoList = storemDao.select(new StoreMemo(storeId, "", ""));
				request.setAttribute("storememoList", memoList);
				request.setAttribute("message", "業務連絡を登録しました。");
			} else {
				request.setAttribute("message", "業務連絡の登録に失敗しました。");
			}

			System.out.println(storedate);
			System.out.println(message);
		}

		// 来店者一覧
		Date today = Date.valueOf(LocalDate.now());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(today);

		// TODO 現時点注文済みの客の情報のみ表示
		VisitorDAO dao = new VisitorDAO();
		List<Visitor> visitor = dao.getVisitorByDate(dateString);
		request.setAttribute("visitor", visitor);
		
		//ニックネーム検索
		String searchName = request.getParameter("customer_name_search");
		if(searchName != null &&!searchName.trim().isEmpty()) {
			
		}

		//注文履歴
		OrderListDAO orderDao = new OrderListDAO();
		List<OrderList> order = orderDao.getTodayOrderByStoreId(storeId, dateString);
		request.setAttribute("OrderList", order);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/StoreStaff.jsp");
		dispatcher.forward(request, response);
	}
}
