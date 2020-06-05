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
	private static final String INSERTION_PRODUCT = "insert into orderproducts values(?, ?, ?);";
	
	public static boolean AddOrder(Order order) {
		int id = order.getId();
		int idInt = id;
		String email = order.getEmail();
		String phone = order.getPhone();
		String address = order.getAddress();
		String city = order.getCity();
		String state = order.getState();
		int zip = order.getZip();
		int zipInt = zip;
		String method = order.getMethod();
		String cardname = order.getCardname();
		String cardnumber = order.getCardnumber();
		int expmonth = order.getExpmonth();
		int expmonthInt = expmonth;
		int expyear = order.getExpyear();
		int expyearInt = expyear;
		int cvv = order.getCvv();
		int cvvInt = cvv;
		String name = order.getName();
		Double priceDouble = order.getTotalPrice();
		String price = Double.toString(priceDouble);
		Object[] params = {idInt, email, phone, address, city, state, zipInt, method, cardname, cardnumber, expmonthInt, expyearInt, cvvInt, name, priceDouble};
		
		try(Connection connection = DatabaseConnection.connect()){
			PreparedStatement ps = connection.prepareStatement(INSERTION);
			int i = 1;
			for(Object param : params) {
				ps.setObject(i++, param);
			}
			return ps.executeUpdate() > 0;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static boolean AddOrderProduct(int orderId, int productId, int quantity) {
		Integer[] params = {orderId, productId, quantity};
		try(Connection connection = DatabaseConnection.connect()){
			PreparedStatement ps = connection.prepareStatement(INSERTION_PRODUCT);
			int i = 1;
			for(Integer param: params) {
				ps.setInt(i++, param);
			}
			
			return ps.executeUpdate() > 0;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
