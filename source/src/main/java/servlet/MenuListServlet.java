package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommodityDAO;
import dto.Commodity;

@WebServlet("/MenuListServlet")
public class MenuListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		request.setCharacterEncoding("UTF-8");
		String categoryIdStr = request.getParameter("category_id");
		
		//idをint型にする
		int category_id = Integer.parseInt(categoryIdStr);
		
		//選ばれたカテゴリのデータを100個取り出す
		CommodityDAO dao = new CommodityDAO();
		List<Commodity> menuList = dao.selectByCategoryWithPage(category_id, 1, 100);
		
		
		request.setAttribute("menuList", menuList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/menu.jsp");
		
		dispatcher.forward(request,response);
		}
}
