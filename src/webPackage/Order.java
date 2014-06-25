package webPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Order {
	private String userID;
	private String address;
	private ArrayList<Product> products;
	private Timestamp time;
	private int orderID, price;
	private Statement stmt;

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

	public Order(int orderID) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://"
					+ MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME,
					MyDBInfo.MYSQL_PASSWORD);
			stmt = con.createStatement();
			stmt.executeQuery("USE " + MyDBInfo.MYSQL_DATABASE_NAME);
			getOrderByID(orderID);
			this.orderID = orderID;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void getOrderByID(int orderID) {
		ResultSet rs;
		try {
			rs = stmt.executeQuery("select userID, timestamp, address from "
					+ MyDBInfo.ORDER_TABLE + " where orderID = " + orderID
					+ ";");
			while (rs.next()) {
				this.userID = rs.getString(1);
				this.time = rs.getTimestamp(2);
				this.address = rs.getString(3);
			}
			this.products = new ArrayList<Product>();
			rs = stmt.executeQuery("select productID from "
					+ MyDBInfo.ORDER_PRODUCT_TABLE + " where orderID = "
					+ orderID + ";");
			while (rs.next()) {
				this.products.add(new Product(rs.getInt(1)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
