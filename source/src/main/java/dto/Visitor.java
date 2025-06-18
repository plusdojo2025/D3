package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Visitor implements Serializable{
	private Customer customer;
	private Commodity commodity;
	private TopicTag topic;
	private List<OrderList> orders;
	
	public Visitor() {
		this.setCustomer(new Customer(0, "", "", "", ""));
		this.setCommodity(new Commodity(0, "", 0, 0, ""));
		this.setTopic(new TopicTag(0, ""));
		this.orders = new ArrayList<OrderList>();
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

	public TopicTag getTopic() {
		return topic;
	}

	public void setTopic(TopicTag topic) {
		this.topic = topic;
	}

	public List<OrderList> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderList> orders) {
		this.orders = orders;
	}
}
