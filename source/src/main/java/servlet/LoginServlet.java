package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CustomerDAO;
import dao.StoreDAO;
import dto.Customer;
import dto.Store;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String userType = request.getParameter("userType");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // 入力チェック
        if (userType == null || email == null || password == null ||
            email.isEmpty() || password.isEmpty()) {
            request.setAttribute("errorMsg", "すべての項目を入力してください。");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession();

        if ("store".equals(userType)) {
            Store store = new StoreDAO().login(email, password);
            if (store != null) {
                session.setAttribute("store", store);
                response.sendRedirect("storeHome.jsp");
            } else {
                request.setAttribute("errorMsg", "店舗ログイン失敗。メールアドレスまたはパスワードが間違っています。");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else if ("customer".equals(userType)) {
            Customer customer = new CustomerDAO().login(email, password);
            if (customer != null) {
                session.setAttribute("customer", customer);
                response.sendRedirect("customerHome.jsp");
            } else {
                request.setAttribute("errorMsg", "顧客ログイン失敗。メールアドレスまたはパスワードが間違っています。");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMsg", "不正なユーザー種別です。");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // GETリクエストはログインページに転送
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
