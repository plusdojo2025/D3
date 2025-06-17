package servlet;

import java.io.IOException;
import java.util.ArrayList;
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
		
		//
		int category_id = Integer.parseInt(categoryIdStr);

		//選ばれたカテゴリのデータを10個取り出す
		CommodityDAO dao = new CommodityDAO();
		
		List<Commodity> menupage =new ArrayList<Commodity>();
		menupage = dao.selectByCategoryWithPage(category_id, 1, 500);
		int page=(menupage.size()-1)/9+1;//最大ページ数を計算して名前変える!!!ここ考える
		
		List<Commodity> menuList =new ArrayList<Commodity>();
		menuList = dao.selectByCategoryWithPage(category_id, 1, 9);
		
		//ページ指定してデータを送る
		int[]pagenumber=new int[page];
		
		
		//まだ存在しないページが表示されるため途中
		if(request.getParameter("number")!=null) {
			int num=Integer.parseInt(request.getParameter("number"));
			menuList = dao.selectByCategoryWithPage(category_id, num, 9);//何ページ目かと一枚当たりのデータ数
			
				if(num-2<1) {//下のページが足りない時
					if(page<5) {//最大ページが小さいとき
						for(int i=0;i<page;i++) {
							pagenumber[i]=i+1;
						}	
					}
					else {
						
						for(int i=0;i<5;i++) {
							pagenumber[i]=i+1;
						}
					}
				}
				else if(num+2>page) {
					
					if(page<=5) {//最大ページが小さいとき
						for(int i=0;i<page;i++) {
							pagenumber[i]=i+1;
						}	
					}
					else {
						
						for(int i=0;i<5;i++) {
							pagenumber[i]=page-4+i;
						}
					}
				}
				else {

					if(page<=5) {//最大ページが小さいとき
						for(int i=0;i<page;i++) {
						pagenumber[i]=i+1;
						}	
					}
					else {
					
						for(int i=0;i<5;i++) {
							pagenumber[i]=num-2+i;
						}
					}
				}	
		}
		else {
			if(page<=5) {
				for(int i=0;i<page;i++) {
					pagenumber[i]=i+1;
				}
			}else {
				for(int i=0;i<5;i++) {
					pagenumber[i]=i+1;
				}
			}
		}
		
		
		request.setAttribute("menuList", menuList);
		request.setAttribute("pagenumber", pagenumber);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Menu.jsp");
		
		dispatcher.forward(request,response);
		}
	
	
	//POSTでページ遷移、ボタンでｎページ目を表示
}
