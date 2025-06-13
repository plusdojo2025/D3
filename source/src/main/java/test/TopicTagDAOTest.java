package test;

import java.util.List;

import dao.TopicTagDAO;
import dto.TopicTag;

public class TopicTagDAOTest {

    // 全データを表示するメソッド
    public static void showAllData(List<TopicTag> tagList) {
        for (TopicTag tag : tagList) {
            System.out.println("話題タグID：" + tag.getTopic_id());
            System.out.println("話題名：" + tag.getTopic_name());
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TopicTagDAO dao = new TopicTagDAO();

        // select()のテスト1
        System.out.println("---------- select()のテスト1 ----------");
        List<TopicTag> tagListSel1 = dao.select(new TopicTag(0, "スポーツ"));
        showAllData(tagListSel1);

        // select()のテスト2
        System.out.println("---------- select()のテスト2 ----------");
        List<TopicTag> tagListSel2 = dao.select(new TopicTag());
        showAllData(tagListSel2);

        // insert()のテスト
        System.out.println("---------- insert()のテスト ----------");
        TopicTag insRec = new TopicTag(0, "旅行");
        if (dao.insert(insRec)) {
            System.out.println("登録成功！");
            List<TopicTag> tagListIns = dao.select(new TopicTag());
            showAllData(tagListIns);
        } else {
            System.out.println("登録失敗！");
        }

        // update()のテスト
        System.out.println("---------- update()のテスト ----------");
        List<TopicTag> tagListUp = dao.select(new TopicTag(0, "旅行"));
        if (!tagListUp.isEmpty()) {
            TopicTag upRec = tagListUp.get(0);
            upRec.setTopic_name("音楽");
            if (dao.update(upRec)) {
                System.out.println("更新成功！");
                List<TopicTag> tagListAfterUpdate = dao.select(new TopicTag());
                showAllData(tagListAfterUpdate);
            } else {
                System.out.println("更新失敗！");
            }
        } else {
            System.out.println("更新対象が見つかりませんでした！");
        }

        // delete()のテスト
        System.out.println("---------- delete()のテスト ----------");
        List<TopicTag> tagListDel = dao.select(new TopicTag(0, "音楽"));
        if (!tagListDel.isEmpty()) {
            TopicTag delRec = tagListDel.get(0);
            if (dao.delete(delRec)) {
                System.out.println("削除成功！");
                List<TopicTag> tagListAfterDel = dao.select(new TopicTag());
                showAllData(tagListAfterDel);
            } else {
                System.out.println("削除失敗！");
            }
        } else {
            System.out.println("削除対象が見つかりませんでした！");
        }
    }
}
