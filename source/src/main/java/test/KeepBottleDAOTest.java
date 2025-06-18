package test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dao.KeepBottleDAO;
import dto.Commodity;
import dto.Customer;
import dto.KeepBottle;

public class KeepBottleDAOTest {

	public static void showAllData(List<KeepBottle> keepBottle) {
		for (KeepBottle kb : keepBottle) {
			System.out.println(kb.getBottle_id());
			System.out.println(kb.getBottle_remaining());
			System.out.println(kb.getBottle_rimit());
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		KeepBottleDAO dao = new KeepBottleDAO();
		KeepBottle kb = new KeepBottle();
		Customer customer = new Customer();
		customer.setCustomer_id(1);
		Commodity commodity = new Commodity(1, "ビール中", 100, 1, "");
		kb.setCustomer(customer);

		// select
		System.out.println("select test");
		List<KeepBottle> list= new ArrayList<KeepBottle>();
		list = dao.select(kb);
		System.out.println(list);
		
		// insert
		System.out.println("insert test");
		kb.setCustomer(customer);
		kb.setCommodity(commodity);
		kb.setBottle_remaining(100);
		kb.setBottle_rimit(Timestamp.valueOf(LocalDateTime.now()));
		boolean result = dao.insert(kb);
		System.out.println(result);
		
		// update
		System.out.println("update test");
		kb.setBottle_id(1);
		kb.setBottle_remaining(50);
		result = dao.update(kb);
		System.out.println(result);
		
		// delete
		System.out.println("delete");
		result = dao.delete(kb);
		System.out.println(result);
	}

}
