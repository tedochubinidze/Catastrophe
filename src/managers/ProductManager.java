package managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import webPackage.Comment;
import webPackage.MyDBInfo;
import webPackage.Post;
import webPackage.Product;

public class ProductManager {
	private static String account = MyDBInfo.MYSQL_USERNAME;
	private static String password = MyDBInfo.MYSQL_PASSWORD;
	private static String server = MyDBInfo.MYSQL_DATABASE_SERVER;
	private static String database = MyDBInfo.MYSQL_DATABASE_NAME;
	private static Statement stmt;

	private static final int MAX_N_POSTS = 20;

	public ProductManager() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://"
					+ server, account, password);
			stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Product> getProducts() {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}

	public int addProduct(Product product) {
		try {
			stmt.executeUpdate("insert into " + MyDBInfo.PRODUCT_TABLE
					+ "(title, price, image, description) values('"
					+ product.getTitle() + "', " + product.getPrice() + ", '"
					+ product.getImage() + "', '" + product.getDiscription() + "');",
					stmt.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.getGeneratedKeys();
			int id = 0;
			if (rs != null && rs.next()) {
				id = rs.getInt(1);
			}
			;
			return id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

}
