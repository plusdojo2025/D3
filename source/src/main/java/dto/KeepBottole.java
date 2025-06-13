package dto;

import java.io.Serializable;
import java.sql.Date;

public class KeepBottole implements Serializable{
	private int bottole_id;			// キープボトルID
	private int customer_id;		// 顧客ID
	private int commodity_id;		// 商品ID
	private int bottole_remaining;	// 残量
	private Date bottole_rimit;		// 期限
	
	
	
	public KeepBottole(int bottoleId, int customerId, int commodityId, int bottoleRemaining, Date bottoleRimit) {
		bottole_id = bottoleId;
		customer_id = customerId;
		commodity_id = commodityId;
		bottole_rimit = bottoleRimit;
	}
	
	public int getBottole_id() {
		return bottole_id;
	}
	public void setBottole_id(int bottole_id) {
		this.bottole_id = bottole_id;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public int getCommodity_id() {
		return commodity_id;
	}
	public void setCommodity_id(int commodity_id) {
		this.commodity_id = commodity_id;
	}
	public int getBottole_remaining() {
		return bottole_remaining;
	}
	public void setBottole_remaining(int bottole_remaining) {
		this.bottole_remaining = bottole_remaining;
	}
	public Date getBottole_rimit() {
		return bottole_rimit;
	}
	public void setBottole_rimit(Date bottole_rimit) {
		this.bottole_rimit = bottole_rimit;
	}
}
