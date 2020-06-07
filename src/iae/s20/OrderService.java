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
	private static final String SELECT_ORDER = "select * from orders where id=";
	private static final String PRODUCT_QUANTITY = "select * from orderproducts where orderid=";
	
	public static Order GetOrder(int id) {
		Order order = new Order();
		
		try(java.sql.Connection connection = DatabaseConnection.connect()){
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ORDER  + Integer.toString(id) + ";");
			while(rs.next()) {
				order.setId(rs.getInt(1));
				order.setEmail(rs.getString(2));
				order.setPhone(rs.getString(3));
				order.setAddress(rs.getString(4));
				order.setCity(rs.getString(5));
				order.setState(rs.getString(6));
				order.setZip(rs.getInt(7));
				order.setMethod(rs.getString(8));
				order.setCardname(rs.getString(9));
				order.setCardnumber(rs.getString(10));
				order.setExpmonth(rs.getInt(11));
				order.setExpyear(rs.getInt(12));
				order.setCvv(rs.getInt(13));
				order.setName(rs.getString(14));
				order.setTotalPrice(rs.getDouble(15));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return order;
	}
	
	public static ProductOrder GetProductQuantity(int orderId, int productId) {
		ProductOrder po = new ProductOrder(orderId, productId, 0);
		
		try(java.sql.Connection connection = DatabaseConnection.connect()){
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(PRODUCT_QUANTITY + Integer.toString(orderId) + " and productid=" + Integer.toString(productId) + ";");
			while(rs.next()) {
				po.setQuantity(rs.getInt(3));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return po;
	}
	
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
