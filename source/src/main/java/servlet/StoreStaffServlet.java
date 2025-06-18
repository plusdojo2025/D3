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

		
		//イベント登録
		request.setCharacterEncoding("UTF-8");
		//e_actionリクエストパラメータを取得する（insert,update,deleteを区別する）
		String message ="";
		
		String eventdate = request.getParameter("event_date");
		String eventname = request.getParameter("event_name");
		String eventremark = request.getParameter("event_remark");
		
		EventDAO eventDao = new EventDAO();
		boolean ins = eventDao.insert(new Event(0,0,eventdate,eventname,eventremark));
		if(ins==true) {
			List<Event>sel = eventDao.select();
<<<<<<< HEAD
			request.setAttribute("eventList",("新しいイベントを登録しました。"));
			//eventDao.showAllData(sel);
=======
		message = ("新しいイベントを登録しました。");
>>>>>>> e1d7b97728210f17a6c685121d7c631bb2896d46
		}else {
		message = "イベントの登録に失敗しました。";	
		}
		//イベント更新
		/*if (request.getParameter("update").equals("更新")) {
			if(eventDao.update(new Event(0, 0, eventdate,eventname,eventremark))) {
				message = ("イベント内容を更新しました。");
			}
		}*/

		
		//業務連絡登録
		request.setCharacterEncoding("UTF-8");
		String storedate = request.getParameter("store_date");
		String storeremark = request.getParameter("store_remark");
		
		StoreMemoDAO storemDao = new StoreMemoDAO();
		boolean ins1 = storemDao.insert(new StoreMemo(0,storedate,storeremark));
		if(ins1==true) {
			List<Event>sel1 = eventDao.select();
		message = ("新しい業務連絡を登録しました。");
		}else {
		message = "業務連絡の登録に失敗しました。";	
		}
		
	//顧客一覧
		
		
	//注文履歴
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/StoreStaff.jsp");
		dispatcher.forward(request,response);
	}

	
	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/StoreStaff.jsp");
	
}
