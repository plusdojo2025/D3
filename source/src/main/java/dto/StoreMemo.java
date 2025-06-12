package dto;

import java.io.Serializable;
import java.time.LocalDate;

public class StoreMemo implements Serializable{
		private int id;
		private String store_date;
		private String store_remark;
// コンストラクタ
		public StoreMemo(int storeId, LocalDate messageDate, String note) {
			this.id = id;
			this.store_date = store_date;
			this.store_remark = store_remark;
		}

// ゲッター／セッター
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getStore_date() {
			return store_date;
		}

		public void setStore_date(String storeId) {
			this.store_date = storeId;
		}
		
		public String getStore_remark() {
			return store_remark;
		}

		public void setStore_remark(String note) {
			this.store_remark = store_remark;
		}
	}
