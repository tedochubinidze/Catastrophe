package managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import webPackage.Cart;
import webPackage.MyDBInfo;
import webPackage.Order;
import webPackage.Product;

public class ProductManager {
	private static String account = MyDBInfo.MYSQL_USERNAME;
	private static String password = MyDBInfo.MYSQL_PASSWORD;
	private static String server = MyDBInfo.MYSQL_DATABASE_SERVER;
	private static String database = MyDBInfo.MYSQL_DATABASE_NAME;
	private Statement stmt;
	private Connection con;

	private static final int MAX_N_POSTS = 20;

	public ProductManager() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void getConnection() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://" + server,
					account, password);
			stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Product> getProducts() {
		getConnection();
		ArrayList<Product> ls = new ArrayList<Product>();
		ResultSet rs;
		try {
			rs = stmt.executeQuery("select * from " + MyDBInfo.PRODUCT_TABLE
					+ ";");
			while (rs.next()) {
				Product product = new Product(rs.getInt(1), rs.getString(2),
						rs.getInt(3), rs.getString(4), rs.getString(5));
				ls.add(product);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}

	public int addProduct(Product product) {
		getConnection();
		try {
			stmt.executeUpdate(
					"insert into " + MyDBInfo.PRODUCT_TABLE
							+ "(title, price, image, description) values('"
							+ product.getTitle() + "', " + product.getPrice()
							+ ", '" + product.getImage() + "', '"
							+ product.getDiscription() + "');",
					stmt.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.getGeneratedKeys();
			int id = 0;
			if (rs != null && rs.next()) {
				id = rs.getInt(1);
			}
			con.close();
			return id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	public void addProductToCart(String userID, Product product) {
		getConnection();
		try {
			int cartID = getCartID(userID);
			stmt.executeUpdate("insert into " + MyDBInfo.CART_PRODUCT_TABLE
					+ " values(" + cartID + ", " + product.getID() + ");");
			stmt.executeUpdate("update " + MyDBInfo.CART_TABLE
					+ " set price = price +" + product.getPrice()
					+ " where cartID = " + cartID + ";");
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeProductFromCart(String userID, Product product) {
		getConnection();
		try {
			int cartID = getCartID(userID);
			stmt.executeUpdate("delete from " + MyDBInfo.CART_PRODUCT_TABLE
					+ " where cartID = " + cartID + " and productID = "
					+ product.getID() + ";");
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private int getCartID(String userID) {
		ResultSet rs;
		int cartID = 0;
		try {
			rs = stmt.executeQuery("select cartID from " + MyDBInfo.CART_TABLE
					+ " where userID = '" + userID + "';");
			while (rs.next()) {
				cartID = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cartID;
	}

	public void makeOrder(Order order) {
		getConnection();
		try {
			stmt.executeUpdate(
					"insert into " + MyDBInfo.ORDER_TABLE
							+ "(userID, timestamp, address) values('"
							+ order.getUserID() + "', now(), '"
							+ order.getAddress() + "');",
					stmt.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.getGeneratedKeys();
			int id = 0;
			if (rs != null && rs.next()) {
				id = rs.getInt(1);
			}
			ArrayList<Product> ls = order.getProducts();
			for (Product p : ls) {
				stmt.executeUpdate("insert into "
						+ MyDBInfo.ORDER_PRODUCT_TABLE
						+ "(orderID, productID) values(" + id + ", "
						+ p.getID() + ");");
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Order> getOrders() {
		getConnection();
		ArrayList<Order> ls = new ArrayList<Order>();
		ResultSet rs;
		try {
			rs = stmt.executeQuery("select * from " + MyDBInfo.ORDER_TABLE
					+ ";");
			while (rs.next()) {
				Order order = new Order(rs.getInt(1), rs.getString(2),
						rs.getString(4), rs.getTimestamp(3));
				ls.add(order);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}

	public void deleteOrder(int orderID) {
		getConnection();
		try {
			stmt.executeUpdate("delete from " + MyDBInfo.ORDER_PRODUCT_TABLE
					+ " where orderID = " + orderID + ";");
			stmt.executeUpdate("delete from " + MyDBInfo.ORDER_TABLE
					+ " where orderID = " + orderID + ";");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addProductsToUser(String userID, Cart cart) {
		getConnection();
		ArrayList<Product> ls = cart.getProducts();
		for (Product p : ls)
			try {
				stmt.executeUpdate("insert into " + MyDBInfo.USER_PRODUCT_TABLE
						+ " values('" + userID + "', " + p.getID() + ");");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public void cleanCart(String userID) {
		getConnection();
		try {
			int cartID = getCartID(userID);
			stmt.executeUpdate("delete from " + MyDBInfo.CART_PRODUCT_TABLE
					+ " where cartID = " + cartID + ";");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Product> getRecentUserProducts(String userID) {
		getConnection();
		ArrayList<Product> ls = new ArrayList<Product>();
		ResultSet rs;
		try {
			rs = stmt.executeQuery("select productID from "
					+ MyDBInfo.USER_PRODUCT_TABLE + " where userID = '"
					+ userID + "' limit 5" + ";");
			while (rs.next()) {
				ls.add(new Product(rs.getInt(1)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ls;
	}
}
