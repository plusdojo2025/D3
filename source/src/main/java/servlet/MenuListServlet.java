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
		String categoryIdStr = request.getParameter("category_id");//商品の種類をボタンで管理
		if(categoryIdStr==null) {
			categoryIdStr="1";
		}
		
		//idをint型にする
		int category_id = Integer.parseInt(categoryIdStr);
		int page = 1;//ページ数をボタンで管理
		
		//選ばれたカテゴリのデータを100個取り出す
		CommodityDAO dao = new CommodityDAO();
		List<Commodity> menuList = dao.selectByCategoryWithPage(category_id, page, 10);//何ページ目かと一枚当たりのデータ数
		
		
		//ページ指定してデータを送る
		
		request.setAttribute("menuList", menuList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Menu.jsp");
		
		dispatcher.forward(request,response);
		}
	
	
	//POSTでページ遷移、ボタンでｎページ目を表示
}
