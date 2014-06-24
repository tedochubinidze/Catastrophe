package webPackage;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Order {
	private String userID;
	private String address;
	private ArrayList<Product> products;
	private Timestamp time;
	
	public Order (String ID, String Address, Timestamp time) {
		products = new ArrayList<Product>();
		userID = ID;
		address = Address;
		this.time = time;
		User user = new User(userID);
		Cart cart = user.getCart();
		products = cart.getProducts();
	}
	
	public String getUserID() {
		return userID;
	}
	
	public ArrayList<Product> getProducts() {
		return products;
	}
	
	public String getAddress() {
		return address;
	}
	
	public Timestamp getTime() {
		return time;
	}
}
