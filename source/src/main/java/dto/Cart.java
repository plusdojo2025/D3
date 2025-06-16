package dto;

import java.io.Serializable;

public class Cart implements Serializable{
	private Commodity commodity;
	private int quantity;
	
	public Cart() {};
	public Cart(Commodity commodity, int quantity) {
		this.setCommodity(commodity);
		this.setQuantity(quantity);
	}
	public Commodity getCommodity() {
		return commodity;
	}
	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
