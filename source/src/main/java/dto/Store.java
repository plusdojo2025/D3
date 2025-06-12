package dto;
 import java.time.LocalDateTime;
 
public class Store {

	//1 ユーザー情報(customer)
	private String username; //ユーザーネーム
	private String feature; //特徴
	private String usualItem; //いつもの(※テーブル未定)
	
	//2 注文情報(order_item)
	private String orderItemName; // 注文商品名
	private int quantity; //個数
	
	//3 予約情報(reservation)
	private LocalDateTime reservationDate; // 日時
	private int numberofPeople; // 人数
	
	//4 話題タグ(topic_tag)
	private String topicName; //話題名
	
	//5検索キーワード
	private String storeKeyword; //店舗名検索
	private String recipeKeyword; //メニュー名検索
	private String categoryKeyword; //レシピカテゴリ検索
	private String ingredientKeyword; //材料検索
	
	// ⑥ 状態・アクション（ボタン押下に関係する情報）
    private boolean deleteProvided;    // 提供済みボタン押下状態
    private boolean goToCheckout;      // 会計画面へ遷移
    private boolean goToRecipeList;    // レシピ一覧へ遷移
    private boolean goToWorking;       // 業務中画面へ遷移
    private boolean goToOffice;        // 事務作業画面へ遷移
    private boolean logout;            // ログアウトボタン押下状態
	
}

	
	
	
	
	
	
	