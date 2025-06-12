package dto;

import java.io.Serializable;

public class Event implements Serializable{

	private int event_id;//イベントID
	private int store_id;//店舗ID
	private String event_date;//日付
	private String event_name;//イベント名
	private String event_remark;//備考
}
