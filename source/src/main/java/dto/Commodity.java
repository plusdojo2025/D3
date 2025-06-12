package dto;

import java.io.Serializable;

public class Commodity implements Serializable{
	
	private int commodity_id;		/*商品ID*/
	private String commodity_name;	/*商品名*/
	private int commodity_price;	/*価格*/
	private int commodity_category;	/*カテゴリ*/
	private String commodity_image;	/*イメージ*/
	
	

	public Commodity(int commodity_id, String commodity_name, int commodity_price, int commodity_category,
			String commodity_image) {
		super();
		this.commodity_id = commodity_id;
		this.commodity_name = commodity_name;
		this.commodity_price = commodity_price;
		this.commodity_category = commodity_category;
		this.commodity_image = commodity_image;
	}
	
	
	public int getCommodity_id() {
		return commodity_id;
	}

	public void setCommodity_id(int commodity_id) {
		this.commodity_id = commodity_id;
	}

	public String getCommodity_name() {
		return commodity_name;
	}

	public void setCommodity_name(String commodity_name) {
		this.commodity_name = commodity_name;
	}

	public int getCommodity_price() {
		return commodity_price;
	}

	public void setCommodity_price(int commodity_price) {
		this.commodity_price = commodity_price;
	}

	public int getCommodity_category() {
		return commodity_category;
	}

	public void setCommodity_category(int commodity_category) {
		this.commodity_category = commodity_category;
	}

	public String getCommodity_image() {
		return commodity_image;
	}

	public void setCommodity_image(String commodity_image) {
		this.commodity_image = commodity_image;
	}
}