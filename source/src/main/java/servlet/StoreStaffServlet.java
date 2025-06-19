package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EventDAO;
import dao.StoreMemoDAO;
import dto.Event;
import dto.StoreMemo;

@WebServlet("/StoreStaffServlet")
public class StoreStaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	request.setCharacterEncoding("UTF-8");
	String action = request.getParameter("action");

		if ("insertEvent".equals(action)) {
		//イベント登録
		
		String eventdate = request.getParameter("event_date");
		String eventname = request.getParameter("event_name");
		String eventremark = request.getParameter("event_remark");
		
		EventDAO eventDao = new EventDAO();
		boolean ins = eventDao.insert(new Event(0,0,eventdate,eventname,eventremark));
		
		if(ins==true) {
			List<Event>sel = eventDao.select();
			request.setAttribute("eventList",sel);
			request.setAttribute("message", "イベントを登録しました。");
		}else {
		    request.setAttribute("message", "イベントの登録に失敗しました。");
		}}
		
		//業務連絡登録
		else if ("insertMemo".equals(action)) {
		String storedate = request.getParameter("store_date");
		String storeremark = request.getParameter("store_remark");
		
		StoreMemoDAO storememoDao = new StoreMemoDAO();
		boolean ins1 = storememoDao.insert(new StoreMemo(0,storedate,storeremark));
		
		if (ins1) {
			// 空のStoreMemoを渡して全件取得（DAO変更なしで実現）
			List<StoreMemo> memoList = storememoDao.select(new StoreMemo(0, "", ""));
			request.setAttribute("storememoList", memoList);
			request.setAttribute("message", "業務連絡を登録しました。");
		} else {
			request.setAttribute("message", "業務連絡の登録に失敗しました。");
		}}
		
		
	//顧客一覧		
		else {
			
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/StoreStaff.jsp");
		dispatcher.forward(request,response);
	}
}

	
		

