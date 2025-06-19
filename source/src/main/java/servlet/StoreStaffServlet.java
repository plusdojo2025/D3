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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	String action = request.getParameter("action");

		if ("insertEvent".equals(action)) {
		//イベント登録
		request.setCharacterEncoding("UTF-8");
		
		String eventdate = request.getParameter("event_date");
		String eventname = request.getParameter("event_name");
		String eventremark = request.getParameter("event_remark");
		
		EventDAO eventDao = new EventDAO();
		boolean ins = eventDao.insert(new Event(0,0,eventdate,eventname,eventremark));
		if(ins==true) {
			List<Event>sel = eventDao.select();

			request.setAttribute("eventList",eventDao.select());
		}}
		if ("insertMemo".equals(action)) {

		//業務連絡登録
		String storedate = request.getParameter("store_date");
		String storeremark = request.getParameter("store_remark");
		
		StoreMemoDAO storemDao = new StoreMemoDAO();
		boolean ins1 = storemDao.insert(new StoreMemo(0,storedate,storeremark));
		if(ins1==true) {
			List<Event>sel1 = storemDao.select();
		}
		}
	//顧客一覧		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/StoreStaff.jsp");
		dispatcher.forward(request,response);
	}

	
		
}
