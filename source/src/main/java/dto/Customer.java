package dto;

import java.io.Serializable;

public class Customer implements Serializable {
	// 変数の宣言
	private int customer_id;           /* 顧客ID */
	private String customer_name;      /* ユーザーネーム */
	private String customer_email;     /* メールアドレス */
	private String customer_password;  /* パスワード */
	private String customer_birthday;  /* 生年月日 */
	
	//ゲッタとセッタ
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getCustomer_email() {
		return customer_email;
	}
	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}
	public String getCustomer_password() {
		return customer_password;
	}
	public void setCustomer_password(String customer_password) {
		this.customer_password = customer_password;
	}
	public String getCustomer_birthday() {
		return customer_birthday;
	}
	public void setCustomer_birthday(String customer_birthday) {
		this.customer_birthday = customer_birthday;
	}
	
	//コンストラクタ
	public Customer(int customer_id, String customer_name, String customer_email, String customer_password,
			String customer_birthday) {
		super();
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.customer_email = customer_email;
		this.customer_password = customer_password;
		this.customer_birthday = customer_birthday;
	}
	
	//初期化
	public Customer() {
		super();
		this.customer_id = 0;
		this.customer_name = "";
		this.customer_email = "";
		this.customer_password = "";
		this.customer_birthday = "";
	}
}