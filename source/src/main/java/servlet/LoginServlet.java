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

    public LoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 文字コード設定（POSTの日本語対応）
        request.setCharacterEncoding("UTF-8");

        // フォームから取得（hiddenやボタン名などでユーザー種別判別）
        String userType = request.getParameter("userType");  // store or customer
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || email.isEmpty() || password == null || password.isEmpty() || userType == null) {
            request.setAttribute("errorMsg", "メールアドレス、パスワード、ユーザー種別は必須です。");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        if ("store".equals(userType)) {
            // 店舗ログイン処理
            StoreDAO storeDAO = new StoreDAO();
            Store store = storeDAO.login(email, password);

            if (store != null) {
                // ログイン成功時、セッションに保存して店舗画面へリダイレクト
                HttpSession session = request.getSession();
                session.setAttribute("store", store);
                response.sendRedirect("storeHome.jsp");  // 店舗事務トップページ
            } else {
                request.setAttribute("errorMsg", "メールアドレスまたはパスワードが正しくありません。（店舗）");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } else if ("customer".equals(userType)) {
            // 顧客ログイン処理
            CustomerDAO customerDAO = new CustomerDAO();
            Customer customer = customerDAO.login(email, password);

            if (customer != null) {
                HttpSession session = request.getSession();
                session.setAttribute("customer", customer);
                response.sendRedirect("customerHome.jsp");  // 顧客ホーム画面
            } else {
                request.setAttribute("errorMsg", "メールアドレスまたはパスワードが正しくありません。（顧客）");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } else {
            // userType不正
            request.setAttribute("errorMsg", "ユーザー種別が不正です。");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    // GETはログイン画面へリダイレクト（もしくはdoGetは不要なら省略可）
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }
}
