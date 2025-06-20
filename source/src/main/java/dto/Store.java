package dto;

import java.io.Serializable;

public class Store implements Serializable {
	private int store_id;          //店舗ID（固定）
	private String store_name;     // ID
	private String store_password; // パスワード
	
	//ゲッタとセッタ
	public int getStore_id() {
		return store_id;
	}
	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getStore_password() {
		return store_password;
	}
	public void setStore_password(String store_password) {
		this.store_password = store_password;
	}
	
	//初期化
	public Store(int store_id, String store_name, String store_password) {
		this.store_id = store_id;
		this.store_name = store_name;
		this.store_password = store_password;
	}

	public Store() {
		this.store_id = 0;
		this.store_name = "";
		this.store_password = "";
	}
}