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

import dao.EventDAO;
import dao.StoreMemoDAO;
import dao.VisitorDAO;
import dto.Event;
import dto.StoreMemo;
import dto.Visitor;

@WebServlet("/StoreStaffServlet")
public class StoreStaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		
		
		//来店者一覧
		
		Date today = Date.valueOf(LocalDate.now());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(today);
        
        // TODO 現時点注文済みの客の情報のみ表示
     	VisitorDAO dao = new VisitorDAO();
     	List<Visitor> visitor = dao.getVisitorByDate(dateString);

     	request.setAttribute("visitor", visitor);
		
	
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/StoreStaff.jsp");
		dispatcher.forward(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//イベント登録
<<<<<<< HEAD
		
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
=======
				request.setCharacterEncoding("UTF-8");
				//e_actionリクエストパラメータを取得する（insert,update,deleteを区別する）
				String message ="";
				
				String eventdate = request.getParameter("event_date");
				String eventname = request.getParameter("event_name");
				String eventremark = request.getParameter("event_remark");
				
				if(eventdate != null && !eventdate.equals("") && !eventname.equals("") && !eventremark.equals("")) {
					EventDAO eventDao = new EventDAO();
					boolean ins = eventDao.insert(new Event(0,1,eventdate,eventname,eventremark));
					if(ins==true) {

						request.setAttribute("eventList",("新しいイベントを登録しました。"));

						message = ("新しいイベントを登録しました。");

					}
					else {
						message = "イベントの登録に失敗しました。";	
					}
					System.out.println(eventdate);
					System.out.println(message);
				}
				//イベント更新
				/*if (request.getParameter("update").equals("更新")) {
					if(eventDao.update(new Event(0, 0, eventdate,eventname,eventremark))) {
						message = ("イベント内容を更新しました。");
					}
				}*/

				
				//業務連絡登録
				String storedate = request.getParameter("store_date");
				String storeremark = request.getParameter("store_remark");
				
				if(storedate!=null && !storedate.equals("") && !storeremark.equals("")) {
					StoreMemoDAO storemDao = new StoreMemoDAO();
					boolean ins1 = storemDao.insert(new StoreMemo(0,storedate,storeremark));
					if(ins1==true) {
						message = ("新しい業務連絡を登録しました。");
					}
					else {
						message = "業務連絡の登録に失敗しました。";	
					}
					System.out.println(storedate);
					System.out.println(message);
				}
				
				//来店者一覧
				
				Date today = Date.valueOf(LocalDate.now());
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		        String dateString = formatter.format(today);
		        
		        // TODO 現時点注文済みの客の情報のみ表示
		     	VisitorDAO dao = new VisitorDAO();
		     	List<Visitor> visitor = dao.getVisitorByDate(dateString);

		     	request.setAttribute("visitor", visitor);
>>>>>>> e147dce9684f52f6777b3aa2feb500a89e99efa1
		
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

	
<<<<<<< HEAD
		

=======
	
	
}
>>>>>>> e147dce9684f52f6777b3aa2feb500a89e99efa1
