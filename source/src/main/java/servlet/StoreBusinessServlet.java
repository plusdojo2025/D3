package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StoreMemoDAO;
import dao.VisitorDAO;
import dto.StoreMemo;
import dto.Visitor;

@WebServlet("/StoreBusinessServlet")
public class StoreBusinessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 来店者表示
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(timestamp);
		// TODO 現時点注文済みの客の情報のみ表示
		VisitorDAO dao = new VisitorDAO();
		List<Visitor> visitor = dao.getVisitorByDate(date);

		request.setAttribute("visitors", visitor);

		// 連絡事項
		int storeId = 1;
		StoreMemoDAO storeMemoDAO = new StoreMemoDAO();
		List<StoreMemo> memoList = storeMemoDAO.getStoreMemoByDate(storeId, date);
		request.setAttribute("memoList", memoList);

		// 業務画面ページにフォワードする
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/StoreBusiness.jsp");
		RequestDispatcher dispatcher = request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/jsp/StoreBusiness.jsp");
		
		dispatcher.forward(request, response);
	}
}
