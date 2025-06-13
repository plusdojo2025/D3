package dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class KeepBottole implements Serializable{
	private int bottole_id;				// キープボトルID
	private Customer customer;			// 顧客ID
	private Commodity commodity;		// 商品ID
	private int bottole_remaining;		// 残量
	private Timestamp bottole_rimit;	// 期限
	
	
	
	public KeepBottole() {};
	public KeepBottole(int bottoleId, Customer customer, Commodity commodity, int bottoleRemaining, Timestamp bottoleRimit) {
		this.bottole_id = bottoleId;
		this.customer = customer;
		this.commodity = commodity;
		this.bottole_rimit = bottoleRimit;
	}
	
	public int getBottole_id() {
		return this.bottole_id;
	}
	public void setBottole_id(int bottole_id) {
		this.bottole_id = bottole_id;
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
	public int getBottole_remaining() {
		return this.bottole_remaining;
	}
	public void setBottole_remaining(int bottole_remaining) {
		this.bottole_remaining = bottole_remaining;
	}
	public Timestamp getBottole_rimit() {
		return this.bottole_rimit;
	}
	public void setBottole_rimit(Timestamp bottole_rimit) {
		this.bottole_rimit = bottole_rimit;
	}
}
