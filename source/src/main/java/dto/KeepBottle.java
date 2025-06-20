package dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class KeepBottle implements Serializable{
	private int bottle_id;				// キープボトルID
	private Customer customer;			// 顧客ID
	private Commodity commodity;		// 商品ID
	private int bottle_remaining;		// 残量
	private Timestamp bottle_limit;	// 期限
	
	
	
	public KeepBottle() {};
	public KeepBottle(int bottleId, Customer customer, Commodity commodity, int bottleRemaining, Timestamp bottleLimit) {
		this.bottle_id = bottleId;
		this.customer = customer;
		this.commodity = commodity;
		this.bottle_limit = bottleLimit;
	}
	public int getBottle_id() {
		return this.bottle_id;
	}
	public void setBottle_id(int bottle_id) {
		this.bottle_id = bottle_id;
	}
	public Customer getCustomer() {
		return this.customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Commodity getCommodity() {
		return this.commodity;
	}
	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}
	public int getBottle_remaining() {
		return this.bottle_remaining;
	}
	public void setBottle_remaining(int bottle_remaining) {
		this.bottle_remaining = bottle_remaining;
	}
	public Timestamp getBottle_limit() {
		return this.bottle_limit;
	}
	public void setBottle_limit(Timestamp bottle_limit) {
		this.bottle_limit = bottle_limit;
	}
}
