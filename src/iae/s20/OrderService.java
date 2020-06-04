package iae.s20;

import iae.s20.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

	private static final String INSERTION = "insert into orders values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
	
	public static boolean addOrder(Order order) {
		int idInt = order.getId();
		String id = Integer.toString(idInt);
		String email = order.getEmail();
		String phone = order.getPhone();
		String address = order.getAddress();
		String city = order.getCity();
		String state = order.getState();
		int zipInt = order.getZip();
		String zip = Integer.toString(zipInt);
		String method = order.getMethod();
		String cardname = order.getCardname();
		String cardnumber = order.getCardnumber();
		int expmonthInt = order.getExpmonth();
		String expmonth = Integer.toString(expmonthInt);
		int expyearInt = order.getExpyear();
		String expyear = Integer.toString(expyearInt);
		int cvvInt = order.getCvv();
		String cvv = Integer.toString(cvvInt);
		String name = order.getName();
		Double priceDouble = order.getTotalPrice();
		String price = Double.toString(priceDouble);
		String[] params = {id, email, phone, address, city, state, zip, method, cardname, cardnumber, expmonth, expyear, cvv, cvv, name, price};
		
		try(Connection connection = DatabaseConnection.connect()){
			PreparedStatement ps = connection.prepareStatement(INSERTION);
			int i = 1;
			for(String param : params) {
				ps.setString(i++, param);
			}
			return ps.executeUpdate() > 0;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
}
