package managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import webPackage.MyDBInfo;
import webPackage.Post;

public class ProfileManager {
	private static String account = MyDBInfo.MYSQL_USERNAME;
	private static String password = MyDBInfo.MYSQL_PASSWORD;
	private static String server = MyDBInfo.MYSQL_DATABASE_SERVER;
	private static String database = MyDBInfo.MYSQL_DATABASE_NAME;
	private static Statement stmt;

	private static final int MIN_PASSWORD_LENGTH = 5;

	public ProfileManager() {
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

	public static final String ADD_SUCCESSFUL = "Account was successfully created";
	public static final String ADD_ID_USED = "ID is already in use";
	public static final String ADD_EMAIL_USED = "e-mail already in use";
	public static final String ADD_SMALL_PASSWORD = "Password is too short";
	public static final String ADD_UNSUCCESSFUL = "Error while creating account";

	// adds user to database, returns particular string depending on success
	public String addUser(String userID, String password, String name,
			String lastname, String email, boolean admin) {
		if (containsUserID(userID)) {
			return ADD_ID_USED;
		} else if (password.length() < MIN_PASSWORD_LENGTH) {
			return ADD_SMALL_PASSWORD;
		} else if (containsEmail(email)) {
			return ADD_EMAIL_USED;
		} else {
			String hashedPw = hashPw(password);
			try {
				stmt.executeUpdate("insert into " + MyDBInfo.USER_TABLE
						+ " values('" + userID + "', '" + hashedPw + "', '"
						+ name + "', '" + lastname + "', '" + email + "', "
						+ admin + ", 0);");
				return ADD_SUCCESSFUL;
			} catch (SQLException e) {
				e.printStackTrace();
				return ADD_UNSUCCESSFUL;
			}
		}
	}

	// checks if userID is already in database
	public boolean containsUserID(String userID) {
		boolean bool = false;
		ResultSet rs;
		int counter = 0;
		try {
			rs = stmt
					.executeQuery("select count(userID) from user where userID = '"
							+ userID + "';");
			while (rs.next())
				counter = rs.getInt("count(userID)");
			if (counter != 0)
				bool = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bool;
	}

	// checks if email is already used by another account
	public boolean containsEmail(String email) {
		boolean bool = false;
		ResultSet rs;
		int counter = 0;
		try {
			rs = stmt
					.executeQuery("select count(email) from user where email = '"
							+ email + "';");
			while (rs.next())
				counter = rs.getInt("count(email)");
			if (counter != 0)
				bool = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bool;
	}

	// returns hashed password of user
	public String getHashedPassword(String userID) {
		String hashedPw = (String) getContentByID("password", userID,
				MyDBInfo.USER_TABLE);
		return hashedPw;
	}

	// returns name of user
	public String getName(String userID) {
		String name = (String) getContentByID("name", userID,
				MyDBInfo.USER_TABLE);
		return name;
	}

	// returns last name of user
	public String getLastName(String userID) {
		String lastname = (String) getContentByID("lastname", userID,
				MyDBInfo.USER_TABLE);
		return lastname;
	}

	// returns email of user
	public String getEmail(String userID) {
		String email = (String) getContentByID("email", userID,
				MyDBInfo.USER_TABLE);
		return email;
	}

	// returns type(Administrator, User) of user
	public boolean getAdmin(String userID) {
		boolean bool = (boolean) getContentByID("admin", userID,
				MyDBInfo.USER_TABLE);
		return bool;
	}

	// returns points that user has
	public Integer getPoints(String userID) {
		Integer points = (Integer) getContentByID("points", userID,
				MyDBInfo.USER_TABLE);
		return points;
	}

	// sets users's CP to points
	public void setPoints(String userID, int points) {
		try {
			stmt.executeUpdate("update " + MyDBInfo.USER_TABLE
					+ " set points = " + points + " where userID = '" + userID
					+ "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// adds some points to user CP
	public void addPoints(String userID, int points) {
		try {
			stmt.executeUpdate("update " + MyDBInfo.USER_TABLE
					+ " set points = points + " + points + " where userID = '"
					+ userID + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// checks if password matches user's real password
	public boolean checkInfo(String userID, String password) {
		ResultSet rs;
		try {
			rs = stmt
					.executeQuery("select password from " + MyDBInfo.USER_TABLE
							+ " where userID = '" + userID + "';");
			String hashedPw = "";
			while (rs.next())
				hashedPw = rs.getString("password");
			if (hashedPw.equals(hashPw(password)))
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	

	// returns different content by parameters: column and id value
	private Object getContentByID(String column, String userID, String table) {
		ResultSet rs;
		Object val = null;
		try {
			rs = stmt.executeQuery("Select " + column + " from " + table
					+ " where userID = '" + userID + "'");
			while (rs.next())
				val = rs.getObject(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return val;
	}

	// hash function for password (dasaweria es!!!!!)
	private String hashPw(String password) {
		return password;
	}

}
