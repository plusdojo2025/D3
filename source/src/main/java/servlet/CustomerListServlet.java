package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CommodityDAO;
import dao.CustomerDAO;
import dao.KeepBottleDAO;
import dao.TalkDAO;
import dao.TopicTagDAO;
import dao.VisitorDAO;
import dto.Commodity;
import dto.Customer;
import dto.KeepBottle;
import dto.Store;
import dto.Talk;
import dto.TopicTag;

@WebServlet("/CustomerListServlet")
public class CustomerListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 顧客一覧表示：GETで呼ばれたときの処理
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Store loginStore = (Store) session.getAttribute("store");

        // 未ログインならログイン画面へ
        if (loginStore == null) {
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return;
        }

        CustomerDAO cDao = new CustomerDAO();

        // 来店者から飛ぶ用
        String customerIdStr = request.getParameter("customerId");
        List<Customer> customerList;
        int[] pagenumber = new int[0];

        if (customerIdStr != null && !customerIdStr.isEmpty()) {
            try {
                int customerId = Integer.parseInt(customerIdStr);
                Customer customer = cDao.selectById(customerId);
                if (customer != null) {
                    customerList = List.of(customer); // 1件だけのリスト
                } else {
                    customerList = List.of(); // 該当なし
                }
            } catch (NumberFormatException e) {
                customerList = List.of(); // 無効なIDなら空リスト
            }
        } else {
            // 顧客全件からページングで取得
            int pageSize = 1000; // 1ページあたりの件数
            int page = 1; // 初期ページ

            if (request.getParameter("number") != null) {
                try {
                    page = Integer.parseInt(request.getParameter("number"));
                    if (page < 1) page = 1;
                } catch (NumberFormatException e) {
                    page = 1;
                }
            }

            int total = cDao.countByName(null); // 全件数取得
            int maxPage = (total - 1) / pageSize + 1;
            if (page > maxPage) page = maxPage;

            customerList = cDao.selectByPage(null, page, pageSize);

            // ページ番号リストの作成
            if (maxPage <= 5) {
                pagenumber = new int[maxPage];
                for (int i = 0; i < maxPage; i++) {
                    pagenumber[i] = i + 1;
                    
                }
            } else {
                pagenumber = new int[5];
                int start = Math.max(1, Math.min(page - 2, maxPage - 4));
                for (int i = 0; i < 5; i++) {
                    pagenumber[i] = start + i;
                }
            }
        }

        // 会話情報の取得
        TalkDAO talkDao = new TalkDAO();
        Map<Integer, List<Talk>> talkMap = new HashMap<>();
        for (Customer customer : customerList) {
            List<Talk> talks = talkDao.select(new Talk(customer.getCustomer_id(), null, ""));
            talkMap.put(customer.getCustomer_id(), talks);
        }

        // トピックタグ一覧取得
        TopicTagDAO tagDao = new TopicTagDAO();
        List<TopicTag> topicTagList = tagDao.select(new TopicTag());

        // モード取得
        VisitorDAO visitorDao = new VisitorDAO();
        String[] every = new String[customerList.size()];
        for (int i = 0; i < customerList.size(); i++) {
            every[i] = visitorDao.getModeOrderByCustomerId(customerList.get(i).getCustomer_id());
        }

        // キープボトル情報・商品情報取得
        KeepBottleDAO kbDao = new KeepBottleDAO();
        List<KeepBottle> keepBottleList = kbDao.selectAll();

        CommodityDAO commodityDao = new CommodityDAO();
        List<Commodity> commodityList = commodityDao.selectKeepBottleCommodities();

        // JSPへデータを渡す
        request.setAttribute("customerList", customerList);
        request.setAttribute("talkMap", talkMap);
        request.setAttribute("topicTagList", topicTagList);
        request.setAttribute("every", every);
        request.setAttribute("keepBottleList", keepBottleList);
        request.setAttribute("commodityList", commodityList);
        request.setAttribute("pagenumber", pagenumber);

        // 顧客一覧画面へフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/CustomerList.jsp");
        dispatcher.forward(request, response);
    }

    // 検索時：POSTで呼ばれたときの処理
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Store loginStore = (Store) session.getAttribute("store");

        if (loginStore == null) {
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return;
        }

        request.setCharacterEncoding("UTF-8");
        String customer_name = request.getParameter("customer_name");

        CustomerDAO cDao = new CustomerDAO();
        List<Customer> customerList = cDao.select(new Customer(0, customer_name, "", "", ""));

        TalkDAO talkDao = new TalkDAO();
        Map<Integer, List<Talk>> talkMap = new HashMap<>();
        for (Customer customer : customerList) {
            List<Talk> talks = talkDao.select(new Talk(customer.getCustomer_id(), null, ""));
            talkMap.put(customer.getCustomer_id(), talks);
        }

        TopicTagDAO tagDao = new TopicTagDAO();
        List<TopicTag> topicTagList = tagDao.select(new TopicTag());

        VisitorDAO dao = new VisitorDAO();
        String[] every = new String[customerList.size()];
        for (int i = 0; i < customerList.size(); i++) {
            every[i] = dao.getModeOrderByCustomerId(customerList.get(i).getCustomer_id());
        }

        KeepBottleDAO kbDao = new KeepBottleDAO();
        List<KeepBottle> keepBottleList = kbDao.selectAll();

        CommodityDAO commodityDao = new CommodityDAO();
        List<Commodity> commodityList = commodityDao.selectKeepBottleCommodities();

        request.setAttribute("customerList", customerList);
        request.setAttribute("talkMap", talkMap);
        request.setAttribute("topicTagList", topicTagList);
        request.setAttribute("every", every);
        request.setAttribute("keepBottleList", keepBottleList);
        request.setAttribute("commodityList", commodityList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/CustomerList.jsp");
        dispatcher.forward(request, response);
    }
}
