package test;

import java.util.List;

import dao.TalkDAO;
import dto.Talk;

public class TalkDAOTest {
	public static void main(String[] args) {
		TalkDAO dao = new TalkDAO();

		// INSERT テスト
		Talk newTalk = new Talk(1, 101, "最初のトークメモです");
		boolean insertResult = dao.insert(newTalk);
		System.out.println("Insert結果: " + insertResult);

		// SELECT テスト
		Talk searchCondition = new Talk(0, 101, null);
		List<Talk> resultList = dao.select(searchCondition);
		System.out.println("検索結果:");
		if (resultList != null && !resultList.isEmpty()) {
			for (Talk t : resultList) {
				System.out.println("Customer ID: " + t.getCustomer_id() + ", Topic ID: " + t.getTopic_id()
						+ ", Remark: " + t.getTalk_remark());
			}
		} else {
			System.out.println("該当データなし");
		}

		// UPDATE テスト
		Talk updatedTalk = new Talk(1, 102, "更新されたトークメモ");
		boolean updateResult = dao.update(updatedTalk);
		System.out.println("Update結果: " + updateResult);

		// DELETE テスト
		Talk deleteTalk = new Talk(1, 1, ""); // customer_id = 1 を削除対象とする
		boolean deleteResult = dao.delete(deleteTalk);
		System.out.println("Delete結果: " + deleteResult);
	}
}
