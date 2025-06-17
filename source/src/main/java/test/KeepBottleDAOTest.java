package test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dao.KeepBottoleDAO;
import dto.Commodity;
import dto.Customer;
import dto.KeepBottole;

public class KeepBottoleDAOTest {

	public static void showAllData(List<KeepBottole> keepBottole) {
		for (KeepBottole kb : keepBottole) {
			System.out.println(kb.getBottole_id());
			System.out.println(kb.getBottole_remaining());
			System.out.println(kb.getBottole_rimit());
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		KeepBottoleDAO dao = new KeepBottoleDAO();
		KeepBottole kb = new KeepBottole();
		Customer customer = new Customer();
		customer.setCustomer_id(1);
		Commodity commodity = new Commodity(1, "ビール中", 100, 1, "");
		kb.setCustomer(customer);

		// select
		System.out.println("select test");
		List<KeepBottole> list= new ArrayList<KeepBottole>();
		list = dao.select(kb);
		System.out.println(list);
		
		// insert
		System.out.println("insert test");
		kb.setCustomer(customer);
		kb.setCommodity(commodity);
		kb.setBottole_remaining(100);
		kb.setBottole_rimit(Timestamp.valueOf(LocalDateTime.now()));
		boolean result = dao.insert(kb);
		System.out.println(result);
		
		// update
		System.out.println("update test");
		kb.setBottole_id(1);
		kb.setBottole_remaining(50);
		result = dao.update(kb);
		System.out.println(result);
		
		// delete
		System.out.println("delete");
		result = dao.delete(kb);
		System.out.println(result);
	}

}
