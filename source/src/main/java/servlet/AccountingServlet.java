package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Accounting")
public class AccountingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // DB接続情報は適宜書き換えてください
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/d3?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "password";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // 商品IDと数量をPOSTパラメータから取得
        String[] commodityIds = request.getParameterValues("commodity_id");
        String[] orderQuantities = request.getParameterValues("order_quantity");

        if (commodityIds == null || orderQuantities == null || commodityIds.length != orderQuantities.length) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "商品IDまたは数量のパラメータが不正です。");
            return;
        }

        List<Map<String, Object>> orderDetails = new ArrayList<>();
        int total = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
                String sql = "SELECT commodity_name, commodity_price FROM Commodity WHERE commodity_id = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {

                    for (int i = 0; i < commodityIds.length; i++) {
                        int commodityId = Integer.parseInt(commodityIds[i]);
                        int quantity = Integer.parseInt(orderQuantities[i]);

                        ps.setInt(1, commodityId);

                        try (ResultSet rs = ps.executeQuery()) {
                            if (rs.next()) {
                                String name = rs.getString("commodity_name");
                                int price = rs.getInt("commodity_price");

                                // 合計金額計算
                                total += price * quantity;

                                Map<String, Object> item = new HashMap<>();
                                item.put("commodity_id", commodityId);
                                item.put("commodity_name", name);
                                item.put("commodity_price", price);
                                item.put("order_quantity", quantity);
                                orderDetails.add(item);
                            } else {
                                // 商品IDに該当なしのケースは無視 or エラー処理
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new ServletException("データベースエラーが発生しました。", e);
        }

        // JSPにデータを渡す
        request.setAttribute("orderDetails", orderDetails);
        request.setAttribute("total", total);

        // JSPにフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Accounting.jsp");
        dispatcher.forward(request, response);
    }

    // GETリクエストはエラーメッセージ表示のみ（POST専用）
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().println("<h2>このページはフォームからのPOST送信専用です。</h2>");
    }
}
