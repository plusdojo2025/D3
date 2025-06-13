package test;

import java.util.List;

import dao.CustomerDAO;
import dto.Customer;

public class CustomerDAOTest {

    public static void showAllData(List<Customer> customerList) {
        for (Customer customer : customerList) {
            System.out.println("顧客ID：" + customer.getCustomer_id());
            System.out.println("名前：" + customer.getCustomer_name());
            System.out.println("メールアドレス：" + customer.getCustomer_email());
            System.out.println("パスワード：" + customer.getCustomer_password());
            System.out.println("生年月日：" + customer.getCustomer_birthday());
            System.out.println();
        }
    }

    public static void main(String[] args) {
        CustomerDAO dao = new CustomerDAO();

        // select()のテスト1：名前で検索
        System.out.println("---------- select()のテスト1 ----------");
        List<Customer> listSel1 = dao.select(new Customer(0, "山田", "", "", ""));
        showAllData(listSel1);

        // select()のテスト2：全件取得
        System.out.println("---------- select()のテスト2 ----------");
        List<Customer> listSel2 = dao.select(new Customer(0, "", "", "", ""));
        showAllData(listSel2);

        // insert()のテスト
        System.out.println("---------- insert()のテスト ----------");
        Customer newCustomer = new Customer(0, "田中太郎", "tanaka@example.com", "pass123", "1990-05-20");
        if (dao.insert(newCustomer)) {
            System.out.println("登録成功！");
            List<Customer> listIns = dao.select(new Customer(0, "田中太郎", "", "", ""));
            showAllData(listIns);
        } else {
            System.out.println("登録失敗！");
        }

        // update()のテスト
        System.out.println("---------- update()のテスト ----------");
        List<Customer> listUpdate = dao.select(new Customer(0, "田中太郎", "", "", ""));
        if (!listUpdate.isEmpty()) {
            Customer updateCustomer = listUpdate.get(0);
            updateCustomer.setCustomer_name("鈴木一郎");
            updateCustomer.setCustomer_email("suzuki@example.com");
            updateCustomer.setCustomer_password("newpass456");
            updateCustomer.setCustomer_birthday("1985-12-01");

            if (dao.update(updateCustomer)) {
                System.out.println("更新成功！");
                List<Customer> listAfterUpdate = dao.select(new Customer(0, "鈴木一郎", "", "", ""));
                showAllData(listAfterUpdate);
            } else {
                System.out.println("更新失敗！");
            }
        } else {
            System.out.println("更新対象が見つかりません！");
        }

        // delete()のテスト
        System.out.println("---------- delete()のテスト ----------");
        List<Customer> listDelete = dao.select(new Customer(0, "鈴木一郎", "", "", ""));
        if (!listDelete.isEmpty()) {
            Customer deleteCustomer = listDelete.get(0);
            if (dao.delete(deleteCustomer)) {
                System.out.println("削除成功！");
                List<Customer> listAfterDelete = dao.select(new Customer(0, "", "", "", ""));
                showAllData(listAfterDelete);
            } else {
                System.out.println("削除失敗！");
            }
        } else {
            System.out.println("削除対象が見つかりません！");
        }
    }
}
