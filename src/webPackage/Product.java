package webPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Product {
	private int ID, price;
	private String title, image, description;
	private static Statement stmt;

	public Product(Integer ID, String title, int price, String image,
			String description) {
		this.title = title;
		this.price = price;
		this.image = image;
		this.description = description;
		this.ID = ID;
	}

	public Product(int productID) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://"
					+ MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME,
					MyDBInfo.MYSQL_PASSWORD);
			stmt = con.createStatement();
			stmt.executeQuery("USE " + MyDBInfo.MYSQL_DATABASE_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getProductByID(productID);
	}

	// this method takes User Info from database with given ID
	private void getProductByID(int productID) {
		ResultSet rs;
		try {
			rs = stmt.executeQuery("select * from " + MyDBInfo.PRODUCT_TABLE
					+ " where productID = " + productID + ";");
			while (rs.next()) {
				this.ID = rs.getInt(1);
				this.title = rs.getString(2);
				this.price = rs.getInt(3);
				this.image = rs.getString(4);
				this.description = rs.getString(5);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Sets product ID
	 * 
	 * @param int ID
	 */
	public void setID(int ID) {
		this.ID = ID;
	}

	/**
	 * Returns products price
	 * 
	 * @return int price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * Returns products title
	 * 
	 * @return String title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns products IMG title
	 * 
	 * @return String image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * Returns products description
	 * 
	 * @return String description
	 */
	public String getDiscription() {
		return description;
	}

	/**
	 * Returns products ID
	 * 
	 * @return int ID
	 */
	public int getID() {
		return ID;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || obj.getClass() != getClass()){
			return false;
		}
		Product product = (Product)obj;
		if(product.getID() == this.getID()) return true;
		return false;
	}
	
}
