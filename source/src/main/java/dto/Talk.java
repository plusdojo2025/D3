package dto;

import java.io.Serializable;

public class Talk implements Serializable {
	private Integer customer_id; // ← int → Integer に変更
	private Integer topic_id;
	private String talk_remark;
	

// コンストラクタ
	public Talk(int customer_id, int topic_id, String talk_remark) {
		this.customer_id = customer_id;
		this.topic_id = topic_id;
		this.talk_remark = talk_remark;
	}
	// null 許容のコンストラクタ
	public Talk(Integer customer_id, Integer topic_id, String talk_remark) {
	    this.customer_id = customer_id;
	    this.topic_id = topic_id;
	    this.talk_remark = talk_remark;
	}


// ゲッター／セッター
	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	public Integer getTopic_id() {
		return topic_id;
	}

	public void setTopic_id(Integer topic_id) {
		this.topic_id = topic_id;
	}
	public String getTalk_remark() {
		return talk_remark;
	}

	public void setTalk_remark(String talk_remark) {
		this.talk_remark = talk_remark;
	}
}
