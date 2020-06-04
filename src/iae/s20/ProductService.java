package iae.s20;

import iae.s20.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
	private static final String ALL_PRODUCTS_QUERY = "select * from products";
	
	public static Product getProductById(int id) {
		Product product = new Product();
		
		try(java.sql.Connection connection = DatabaseConnection.connect()){
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(ALL_PRODUCTS_QUERY + " where id = " + Integer.toString(id) + ";");
			while(rs.next()) {
				product.setCategory(rs.getString(4));
				product.setId(rs.getInt(7));
				product.setName(rs.getString(1));
				product.setThumbnail(rs.getString(3));
				product.setSummary(rs.getString(2));
				product.setDetail(rs.getString(5));
				product.setPrice(rs.getDouble(6));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return product;
	}
	
	public static List<Product> getAllProducts(){
		List<Product> products = new ArrayList<Product>();
		
		try(java.sql.Connection connection = DatabaseConnection.connect()){
			Statement stmt=connection.createStatement(); 
			ResultSet rs = stmt.executeQuery(ALL_PRODUCTS_QUERY);
			while(rs.next()) {
				Product product = new Product();
				product.setCategory(rs.getString(4));
				product.setId(rs.getInt(7));
				product.setName(rs.getString(1));
				product.setThumbnail(rs.getString(3));
				product.setSummary(rs.getString(2));
				product.setDetail(rs.getString(5));
				product.setPrice(rs.getDouble(6));
				products.add(product);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		
		return products;
	}
	
	public static boolean addOrder(Product newProduct) {
		return true;
	}
	
}
