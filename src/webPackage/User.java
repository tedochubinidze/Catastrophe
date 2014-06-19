package webPackage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import managers.PostManager;
import managers.ProfileManager;

public class User {

	private int points;
	private boolean admin;
	private String ID;
	private String password;
	private String email;
	private String name;
	private String lastname;

	private static Statement stmt;
	private ProfileManager profManager;
	private PostManager postManager;
	private MessageDigest mg;

	// This is Constructor of Other kind, which creates User by these parameters
	public User(String userID, String password, String name, String lastname,
			String email, boolean admin, int points) {
		ID = userID;
		this.password = password;
		try {
			hashPassword(this.password);
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}
		this.email = email;
		this.name = name;
		this.lastname = lastname;
		this.admin = admin;
		profManager = new ProfileManager();
		postManager = new PostManager();
	}

	/** This is Constructor For Class User, which opens connection and takes User
	 * from DataBase
	 * according to this parameter ID
	 * @param String userID
	**/
	public User(String userID) {
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
		getUserByID(userID);
		try {
			hashPassword(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Hashes Password
	 * @throws NoSuchAlgorithmException
	 */
	public void hashPassword(String str) throws NoSuchAlgorithmException {
		mg = MessageDigest.getInstance("SHA");
		mg.reset();
		str = hexToString(mg.digest(str.getBytes()));
	}

	/**
	 *  This method takes User Info from database with given ID
	 * @param String userID
	 */
	private void getUserByID(String userID) {
		ResultSet rs;
		try {
			rs = stmt.executeQuery("select * from " + MyDBInfo.USER_TABLE
					+ " where userID = '" + userID + "';");
			while (rs.next()) {
				this.ID = rs.getString(1);
				this.password = rs.getString(2);
				this.name = rs.getString(3);
				this.lastname = rs.getString(4);
				this.email = rs.getString(5);
				this.admin = rs.getBoolean(6);
				this.points = rs.getInt(7);
			}
			profManager = new ProfileManager();
			postManager = new PostManager();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 *  Registers current User
	 * @return Boolean register success
	 */
	public String registerUser() {
		return profManager.addUser(ID, password, name, lastname, email, admin);
	}

	/**
	 *  takes action, when user uploads given post
	 * @param Post
	 */
	public void addPost(Post post) {
		int id = postManager.addPost(post.getUserID(), post.getTimesTamp(), post.getTitle(),
				post.getStatus(), post.getAttachment(), post.getType());
		post.setID(id);
	}

	public String getID(){
		return this.ID;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public String getName(){
		return this.name;
	}

	public String getLastName(){
		return this.lastname;
	}
	
	public boolean isAdmin(){
		return this.admin;
	}
	
	public Integer getPoints(){
		return this.points;
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
		str += "ID: " + ID + " Password: " + password + " Name: " + name
				+ " LastName: " + lastname + " e-mail: " + email + " admin: "
				+ admin;
		return str;
	}
	
	/*
	 * Takes in array of bytes and returns hashed String equivalent to this array
	 */
	private String hexToString(byte[] bytes) {
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
