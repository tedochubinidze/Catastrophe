package webPackage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import managers.PostManager;
import managers.ProfileManager;

public class User {

	private int points;
	private boolean admin;
	private String ID;
	private String password;
	private String email;
	private Cart cart;

	private static Statement stmt;
	private ProfileManager profManager;
	private PostManager postManager;
	private static MessageDigest mg;

	// This is Constructor of Other kind, which creates User by these parameters
	public User(String userID, String password, String email, boolean admin,
			int points) {
		ID = userID;
		this.password = password;
		try {
			hashPassword(this.password);
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		this.email = email;
		this.admin = admin;
		this.cart = new Cart();
		profManager = new ProfileManager();
		postManager = new PostManager();
	}

	/**
	 * This is Constructor For Class User, which opens connection and takes User
	 * from DataBase according to this parameter ID
	 * 
	 * @param String
	 *            userID
	 **/
	public User(String userID) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://"
					+ MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME,
					MyDBInfo.MYSQL_PASSWORD);
			stmt = con.createStatement();
			stmt.executeQuery("USE " + MyDBInfo.MYSQL_DATABASE_NAME);
			// hashPassword(password);
			getUserByID(userID);
			getCartOfUser(userID);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void getCartOfUser(String userID) {
		ResultSet rs;
		this.cart = new Cart();
		try {
			int id = 0;
			rs = stmt.executeQuery("select cartID from " + MyDBInfo.CART_TABLE
					+ " where userID = '" + userID + "';");
			while (rs.next()) {
				id = rs.getInt(1);
			}
			rs = stmt.executeQuery("select productID from "
					+ MyDBInfo.CART_PRODUCT_TABLE + " where cartID = " + id
					+ ";");
			while (rs.next()) {
				Product prod = new Product(rs.getInt(1));
				this.cart.addProduct(prod);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Hashes Password
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String hashPassword(String str)
			throws NoSuchAlgorithmException {
		mg = MessageDigest.getInstance("SHA");
		mg.reset();
		str = hexToString(mg.digest(str.getBytes()));
		return str;
	}

	/**
	 * This method takes User Info from database with given ID
	 * 
	 * @param String
	 *            userID
	 */
	private void getUserByID(String userID) {
		ResultSet rs;
		try {
			rs = stmt.executeQuery("select * from " + MyDBInfo.USER_TABLE
					+ " where userID = '" + userID + "';");
			while (rs.next()) {
				this.ID = rs.getString(1);
				this.password = rs.getString(2);
				this.email = rs.getString(3);
				this.admin = rs.getBoolean(4);
				this.points = rs.getInt(5);
			}
			profManager = new ProfileManager();
			postManager = new PostManager();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Registers current User
	 * 
	 * @return Boolean register success
	 */
	public String registerUser() {
		return profManager.addUser(ID, password, email, admin);
	}

	/**
	 * takes action, when user uploads given post
	 * 
	 * @param Post
	 */
	public void addPost(Post post) {
		int id = postManager.addPost(post.getUserID(), post.getTimesTamp(),
				post.getTitle(), post.getStatus(), post.getAttachment(),
				post.getType());
		post.setID(id);
	}

	public String getID() {
		return this.ID;
	}

	public String getPassword() {
		return this.password;
	}

	public String getEmail() {
		return this.email;
	}

	public boolean isAdmin() {
		return this.admin;
	}

	public Integer getPoints() {
		return this.points;
	}

	/**
	 * returns 20 recent posts of user
	 * 
	 * @return
	 */
	public ArrayList<Post> getRecentPosts() {
		return postManager.getRecentPostsByUser(this.ID);
	}

	public Cart getCart() {
		return cart;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj == null || obj.getClass() != getClass()) {
			result = false;
		} else {
			User tmp = (User) obj;
			if (this.ID.equals(tmp.ID))
				result = true;
		}
		return result;
	}

	@Override
	public String toString() {
		String str = "";
		str += "ID: " + ID + " Password: " + password + " e-mail: " + email
				+ " admin: " + admin;
		return str;
	}

	/*
	 * Takes in array of bytes and returns hashed String equivalent to this
	 * array
	 */
	private static String hexToString(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			int val = bytes[i];
			val = val & 0xff; // remove higher bits, sign
			if (val < 16)
				buff.append('0'); // leading 0
			buff.append(Integer.toString(val, 16));
		}
		return buff.toString();
	}

}
