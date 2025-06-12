package dto;

import java.io.Serializable;

public class Event implements Serializable{

	private int event_id;//イベントID
	private int store_id;//店舗ID
	private String event_date;//日付
	private String event_name;//イベント名
	private String event_remark;//備考
	
	
	public Event(int event_id, int store_id, String event_date, String event_name, String event_remark) {
		super();
		this.event_id = event_id;
		this.store_id = store_id;
		this.event_date = event_date;
		this.event_name = event_name;
		this.event_remark = event_remark;
	}
	
	public Event() {
		super();
		this.event_id = 0;
		this.store_id = 0;
		this.event_date = "";
		this.event_name = "";
		this.event_remark = "";
	}
	
	
	
	
	public int getEvent_id() {
		return event_id;
	}
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	public int getStore_id() {
		return store_id;
	}
	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}
	public String getEvent_date() {
		return event_date;
	}
	public void setEvent_date(String event_date) {
		this.event_date = event_date;
	}
	public String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	public String getEvent_remark() {
		return event_remark;
	}
	public void setEvent_remark(String event_remark) {
		this.event_remark = event_remark;
	}
	
	
	
	
}
