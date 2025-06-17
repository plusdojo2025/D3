package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/accounting")
public class AccountingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// 商品名と価格のパラメータを受け取る
		String[] itemNames = request.getParameterValues("itemName");
		String[] itemPrices = request.getParameterValues("itemPrice");

		// 合計金額を計算
		int total = 0;
		if (itemPrices != null) {
			for (String priceStr : itemPrices) {
				try {
					total += Integer.parseInt(priceStr);
				} catch (NumberFormatException e) {
					// 無効な数値はスキップ
				}
			}
		}

		// request にデータを格納して JSP に渡す
		request.setAttribute("itemNames", itemNames);
		request.setAttribute("itemPrices", itemPrices);
		request.setAttribute("total", total);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/checkout.jsp");
		dispatcher.forward(request, response);
	}
}
