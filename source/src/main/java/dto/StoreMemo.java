package dto;

import java.io.Serializable;

public class StoreMemo implements Serializable{
		private int store_id;
		private String store_date;
		private String store_remark;
// コンストラクタ
		public StoreMemo(int store_id, String store_date, String store_remark) {
			this.store_id = store_id;
			this.store_date = store_date;
			this.store_remark = store_remark;
		}

// ゲッター／セッター
		public int getStore_id() {
			return store_id;
		}

		public void setStore_id(int Store_id) {
			this.store_id = store_id;
		}

		public String getStore_date() {
			return store_date;
		}

		public void setStore_date(String store_date) {
			this.store_date = store_date;
		}
		
		public String getStore_remark() {
			return store_remark;
		}

		public void setStore_remark(String store_remark) {
			this.store_remark = store_remark;
		}
	}
