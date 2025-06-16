package test;

import dao.StoreDAO;
import dto.Store;

public class StoreDAOTest {

    // 結果の表示用
    public static void showStoreInfo(Store store) {
        if (store == null) {
            System.out.println("ログインに失敗しました。");
        } else {
            System.out.println("ログイン成功！");
            System.out.println("メールアドレス: " + store.getEmail());
            System.out.println("パスワード: " + store.getPassword());
            System.out.println("店舗名: " + store.getStoreName());
            System.out.println();
        }
    }

    public static void main(String[] args) {
        StoreDAO dao = new StoreDAO();

        //正常ログイン（テスト用アカウントがDBに必要）
        System.out.println("---------- 正常ログインのテスト ----------");
        Store store1 = dao.login("test@store.com", "test123");
        showStoreInfo(store1);

        //パスワード間違い
        System.out.println("---------- パスワード誤りのテスト ----------");
        Store store2 = dao.login("test@store.com", "wrongpass");
        showStoreInfo(store2);

        //メールアドレス間違い
        System.out.println("---------- メールアドレス誤りのテスト ----------");
        Store store3 = dao.login("wrong@store.com", "test123");
        showStoreInfo(store3);

        //両方間違い
        System.out.println("---------- 両方誤りのテスト ----------");
        Store store4 = dao.login("noexist@store.com", "noexistpass");
        showStoreInfo(store4);
    }
}
