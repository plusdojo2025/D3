package dto;

import java.io.Serializable;

public class OrderList implements Serializable{

	private int order_id;//注文ID 
	private int commodity_id;//商品ID
	private String order_datetime;//日時
	private int order_quantity;//個数
	
	
	public OrderList(int order_id, int commodity_id, String order_datetime, int order_quantity) {
		super();
		this.order_id = order_id;
		this.commodity_id = commodity_id;
		this.order_datetime = order_datetime;
		this.order_quantity = order_quantity;
	}
	
	public OrderList() {
		super();
		this.order_id = 0;
		this.commodity_id = 0;
		this.order_datetime = "";
		this.order_quantity = 0;
	}
	
	
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getCommodity_id() {
		return commodity_id;
	}
	public void setCommodity_id(int commodity_id) {
		this.commodity_id = commodity_id;
	}
	public String getOrder_datetime() {
		return order_datetime;
	}
	public void setOrder_datetime(String order_datetime) {
		this.order_datetime = order_datetime;
	}
	public int getOrder_quantity() {
		return order_quantity;
	}
	public void setOrder_quantity(int order_quantity) {
		this.order_quantity = order_quantity;
	}
	
	
	
	
}
