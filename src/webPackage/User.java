package webPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import managers.PostManager;
import managers.ProfileManager;

public class User {

	public int points;
	public boolean admin;
	public String ID;
	public String password;
	public String email;
	public String name;
	public String lastname;

	private static Statement stmt;
	private ProfileManager profManager;
	private PostManager postManager;

	// This is Constructor of Other kind, which creates User by these parameters
	public User(String userID, String password, String name, String lastname,
			String email, boolean admin, int points) {
		ID = userID;
		this.password = password;
		this.email = email;
		this.name = name;
		this.lastname = lastname;
		this.admin = admin;
		profManager = new ProfileManager();
		postManager = new PostManager();
	}

	// This is Constructor For Class User, which opens connection and takes User
	// from DataBase
	// according to this parameter ID
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
	}

	// this method takes User Info from database with given ID
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

	// Registers current User
	public String registerUser() {
		return profManager.addUser(ID, password, name, lastname, email, admin);
	}

	// takes action, when user uploads given post
	public void addPost(Post post) {
		int id = postManager.addPost(post.getUserID(), post.getTimesTamp(), post.getTitle(),
				post.getStatus(), post.getAttachment(), post.getType());
		post.setID(id);
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

}
