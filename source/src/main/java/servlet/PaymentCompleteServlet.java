package servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PaymentComplete")
public class PaymentCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 // GETもPOSTも処理できるように共通化
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");

        // 会計処理が終わったことをシンプルに表示
        response.getWriter().println("<html><head><title>会計完了</title></head><body>");
        response.getWriter().println("<h2>会計処理が完了しました。</h2>");
        response.getWriter().println("<p>ご利用ありがとうございました。</p>");
        response.getWriter().println("</body></html>");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
