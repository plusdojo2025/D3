package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // セッション取得（存在すれば）
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // セッション破棄
        }

        // ログインページへリダイレクト（またはトップページ）
        response.sendRedirect(request.getContextPath() + "/LoginServlet");
    }
}
