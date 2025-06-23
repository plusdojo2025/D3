package dto;

import java.io.Serializable;

public class OrderList implements Serializable{

	private int order_id;//注文ID 
	private Customer customer;//顧客ID
	private Commodity commodity;//商品ID
	private String order_datetime;//日時
	private int order_quantity;//個数
	private int visit_id;//来店ID
	
	
	public OrderList(int order_id, Customer customer, Commodity commodity, String order_datetime, int order_quantity) {
		super();
		this.order_id = order_id;
		this.customer = customer;
		this.commodity = commodity;
		this.order_datetime = order_datetime;
		this.order_quantity = order_quantity;
	}
	
	public OrderList() {
		super();
		this.order_id = 0;
		this.customer = new Customer();
		this.commodity = new Commodity(0, "", 0, 0, "");
		this.order_datetime = "";
		this.order_quantity = 0;
		this.visit_id = 0;
	}
	
	
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	
	
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Commodity getCommodity() {
		return commodity;
	}
	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
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

	public int getVisit_id() {
		return visit_id;
	}

	public void setVisit_id(int visit_id) {
		this.visit_id = visit_id;
	}
	
	
	
	
}
