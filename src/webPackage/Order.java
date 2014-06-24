package webPackage;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Order {
	private String userID;
	private String address;
	private ArrayList<Product> products;
	private Timestamp time;
	private int orderID, price;

	public Order(int orderID, String ID, String Address, Timestamp time) {
		products = new ArrayList<Product>();
		userID = ID;
		address = Address;
		this.time = time;
		this.orderID = orderID;
		User user = new User(userID);
		Cart cart = user.getCart();
		price = cart.getCartPrice();
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

	public int getPrice() {
		return price;
	}

	public int getID() {
		return orderID;
	}

	public void setID(int id) {
		this.orderID = id;
	}
}
