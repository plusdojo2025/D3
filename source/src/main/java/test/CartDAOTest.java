package test;

import dao.CartDAO;
import dto.Commodity;

public class CartDAOTest {
	public static void main(String[] args) {
		CartDAO dao = new CartDAO();
		
		// getCommodityById
		Commodity commodity = dao.getCommodityById(1);
		System.out.println(commodity.getCommodity_name());
	}

}