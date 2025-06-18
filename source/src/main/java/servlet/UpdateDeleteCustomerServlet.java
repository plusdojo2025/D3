package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDAO;
import dao.KeepBottleDAO2;
import dao.TalkDAO;
import dto.Customer;
import dto.Result;

@WebServlet("/UpdateDeleteCustomerServlet")
public class UpdateDeleteCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int customer_id = Integer.parseInt(request.getParameter("customer_id"));
		String customer_name = request.getParameter("customer_name");
		String customer_email = request.getParameter("customer_email");
		String customer_password = request.getParameter("customer_password");
		String customer_birthday = request.getParameter("customer_birthday");

		CustomerDAO cDao = new CustomerDAO();
		KeepBottleDAO2 kbDao = new KeepBottleDAO2();
		TalkDAO talkDao = new TalkDAO();

		String action = request.getParameter("action");

		try {
			if (action != null) {
				if (action.startsWith("update_bottle_")) {
					int bottleId = Integer.parseInt(action.replace("update_bottle_", ""));
					int remaining = Integer.parseInt(request.getParameter("bottle_remaining_" + bottleId));
					kbDao.updateBottleRemaining(bottleId, remaining);
					request.setAttribute("result", new Result("ボトル残量を更新しました。", "/D3/CustomerListServlet"));
				} else if (action.startsWith("delete_bottle_")) {
					int bottleId = Integer.parseInt(action.replace("delete_bottle_", ""));
					kbDao.deleteBottle(bottleId);
					request.setAttribute("result", new Result("ボトルを削除しました。", "/D3/CustomerListServlet"));
				/*} else if (action.startsWith("update_talk_")) {
					int topicId = Integer.parseInt(action.replace("update_talk_", ""));
					int selectedTopicId = Integer.parseInt(request.getParameter("talk_topic_id_" + topicId));
					String remark = request.getParameter("talk_remark_" + topicId);
					talkDao.update(new Talk(customer_id, selectedTopicId, remark));
					request.setAttribute("result", new Result("会話情報を更新しました。", "/D3/CustomerListServlet"));
				} else if (action.startsWith("delete_talk_")) {
					int topicId = Integer.parseInt(action.replace("delete_talk_", ""));
					talkDao.delete(customer_id, topicId);
					request.setAttribute("result", new Result("会話情報を削除しました。", "/D3/CustomerListServlet"));
				} else if (action.equals("insert_talk")) {
					int topicId = Integer.parseInt(request.getParameter("new_topic_id"));
					String remark = request.getParameter("new_talk_remark");
					talkDao.insert(new Talk(customer_id, topicId, remark));
					request.setAttribute("result", new Result("会話情報を追加しました。", "/D3/CustomerListServlet"));*/
				}
			} else {
				// 顧客更新・削除
				if ("顧客情報更新".equals(request.getParameter("submit"))) {
					if (cDao.update(new Customer(customer_id, customer_name, customer_email, customer_password,
							customer_birthday))) {
						request.setAttribute("result", new Result("顧客情報を更新しました。", "/D3/CustomerListServlet"));
					} else {
						request.setAttribute("result", new Result("顧客情報を更新できませんでした。", "/D3/CustomerListServlet"));
					}
				} else {
					if (cDao.delete(new Customer(customer_id, customer_name, customer_email, customer_password,
							customer_birthday))) {
						request.setAttribute("result", new Result("顧客情報を削除しました。", "/D3/CustomerListServlet"));
					} else {
						request.setAttribute("result", new Result("顧客情報を削除できませんでした。", "/D3/CustomerListServlet"));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", new Result("エラーが発生しました。", "/D3/CustomerListServlet"));
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Result.jsp");
		dispatcher.forward(request, response);
	}
}
