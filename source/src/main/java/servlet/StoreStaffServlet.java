package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EventDAO;
import dao.StoreMemoDAO;
import dto.Event;

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
		boolean ins1 = eventDao.insert(new Event(0,0,eventdate,eventname,eventremark));
		if(ins1==true) {
			List<Event>sel = eventDao.select();
		message = ("新しいイベントを登録しました。");
		}else {
		message = "イベントの登録に失敗しました。";	
		}
		
		//業務連絡登録
		request.setCharacterEncoding("UTF-8");
		String storedate = request.getParameter("store_date");
		String storeremark = request.getParameter("store_remark");
		
		StoreMemoDAO storemDao = new StoreMemoDAO();
		
		

		
	//顧客一覧
		
		
	//注文履歴
	}

}
