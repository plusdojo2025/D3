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

        request.setCharacterEncoding("UTF-8");

        String userType = request.getParameter("userType"); // "store" または "customer"
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || email.isEmpty() ||
            password == null || password.isEmpty() ||
            userType == null || userType.isEmpty()) {
            // 入力不足 → ログインページにフォワード
            request.setAttribute("errorMsg", "メールアドレス、パスワード、ユーザー種別は必須です。");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession();

        if ("store".equals(userType)) {
            StoreDAO storeDAO = new StoreDAO();
            Store store = storeDAO.login(email, password);

            if (store != null) {
                session.setAttribute("store", store);
                response.sendRedirect("storeHome.jsp");
            } else {
                request.setAttribute("errorMsg", "店舗：メールアドレスまたはパスワードが正しくありません。");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }

        } else if ("customer".equals(userType)) {
            CustomerDAO customerDAO = new CustomerDAO();
            Customer customer = customerDAO.login(email, password);

            if (customer != null) {
                session.setAttribute("customer", customer);
                response.sendRedirect("customerHome.jsp");
            } else {
                request.setAttribute("errorMsg", "顧客：メールアドレスまたはパスワードが正しくありません。");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }

        } else {
            request.setAttribute("errorMsg", "不正なユーザー種別です。");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // GETでアクセスされたらログインページへフォワード
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
