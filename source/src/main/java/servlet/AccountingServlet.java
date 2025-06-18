package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Accounting")
public class AccountingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
        response.getWriter().println("<h2>このページはフォームからのPOST送信専用です。</h2>");
    }
	 // POSTリクエストの処理（フォーム送信された場合など）
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
		// 商品名と価格のパラメータを受け取る
		String[] commodity_name = request.getParameterValues("commodity_name");//商品の名前
		String[] commodity_price = request.getParameterValues("commodity_price");//商品の値段
		String[] order_quantity = request.getParameterValues("order_quantity");//商品の個数
		
		// 合計金額を計算
		int total = 0;
		if (commodity_price != null && order_quantity != null) {
			for (int i = 0; i < commodity_price.length; i++) {	
				try {
					int price = Integer.parseInt(commodity_price[i].trim());
					int quantity = Integer.parseInt(order_quantity[i].trim());
					total += price * quantity;
				} catch (NumberFormatException e) {
					// 無効な数値はスキップ
				}
			}
		}
			
		// request にデータを格納して JSP に渡す
		request.setAttribute("commodity_name",commodity_name );
		request.setAttribute("commodity_price",commodity_price );
		request.setAttribute("order_quantity",order_quantity );
		request.setAttribute("total", total);
		// メニューページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Accounting.jsp");
		dispatcher.forward(request, response);
		
		
				
	}
}
