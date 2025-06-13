package dto;

import java.io.Serializable;

public class TopicTag implements Serializable {
	// 変数の宣言
	private int topic_id;      /* 話題タグID */
	private String topic_name; /* 話題名 */
	
	//ゲッタとセッタ
	public int getTopic_id() {
		return topic_id;
	}
	public void setTopic_id(int topic_id) {
		this.topic_id = topic_id;
	}
	public String getTopic_name() {
		return topic_name;
	}
	public void setTopic_name(String topic_name) {
		this.topic_name = topic_name;
	}
	
	//コンストラクタ
	public TopicTag(int topic_id, String topic_name) {
		super();
		this.topic_id = topic_id;
		this.topic_name = topic_name;
	}
	
	//初期化
	public TopicTag() {
		super();
		this.topic_id = 0;
		this.topic_name = "";
	}	
}