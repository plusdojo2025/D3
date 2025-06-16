package test;

import java.util.List;

import javaDrill.plusdojo2025.D3.source.src.main.java.dao.StoreMemoDAO;

public class StoreMemoDAOTest {
    public static void main(String[] args) {
        StoreMemoDAO dao = new StoreMemoDAO();

        // insertテスト
        StoreMemo newMemo = new StoreMemo(0, "2025-06-16", "これはテストメモです。");
        boolean insertResult = dao.insert(newMemo);
        System.out.println("Insert結果: " + insertResult);

        // selectテスト
        StoreMemo searchCriteria = new StoreMemo(0, "2025", "テスト");
        List<StoreMemo> results = dao.select(searchCriteria);
        System.out.println("検索結果:");
        for (StoreMemo memo : results) {
            System.out.println("ID: " + memo.getStore_id() +
                               ", 日付: " + memo.getStore_date() +
                               ", メモ: " + memo.getStore_remark());
        }

        // updateテスト
        if (!results.isEmpty()) {
            StoreMemo toUpdate = results.get(0);
            toUpdate.setStore_remark("メモ内容を更新しました。");
            boolean updateResult = dao.update(toUpdate);
            System.out.println("Update結果: " + updateResult);
        }

        // deleteテスト
        if (!results.isEmpty()) {
            StoreMemo toDelete = results.get(0);
            boolean deleteResult = dao.delete(toDelete);
            System.out.println("Delete結果: " + deleteResult);
        }
    }
}
