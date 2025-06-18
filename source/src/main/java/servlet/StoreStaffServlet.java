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
import dto.Event;

@WebServlet("/StoreStaffServlet")
public class StoreStaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
	//イベント告知
		//リクエストパラメータ取得
		request.setCharacterEncoding("UTF-8");
		String eventdate = request.getParameter("event_date");
		String eventname = request.getParameter("event_name");
		String eventremark = request.getParameter("event_remark");
		
		EventDAO eventDao = new EventDAO();
		boolean ins1 = eventDao.insert(new Event(0,0,eventdate,eventname,eventremark));
		if(ins1==true) {
			List<Event>sel = eventDao.select();
			request.setAttribute("eventList",("新しいイベントを登録しました。"));
			//eventDao.showAllData(sel);
		}else {
			
		}
		
		
	//顧客一覧
		
		
	//注文履歴
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/StoreStaff.jsp");
		dispatcher.forward(request,response);
	}

}

