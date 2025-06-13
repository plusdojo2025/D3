package dto;

import java.io.Serializable;

public class Cart implements Serializable{
	private int commodityId;
	private int quantity;
	
	public Cart() {};
	public Cart(int id, int quantity) {
		this.setCommodityId(id);
		this.setQuantity(quantity);
	}
	public int getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(int commodityId) {
		this.commodityId = commodityId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
