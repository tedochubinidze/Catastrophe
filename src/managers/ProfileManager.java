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
	private Statement stmt;
	private Connection con;

	private static final int MIN_PASSWORD_LENGTH = 5;

	public ProfileManager() {
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

	public static final String ADD_SUCCESSFUL = "Account was successfully created";
	public static final String ADD_ID_USED = "ID is already in use";
	public static final String ADD_EMAIL_USED = "e-mail already in use";
	public static final String ADD_SMALL_PASSWORD = "Password is too short";
	public static final String ADD_UNSUCCESSFUL = "Error while creating account";

	/**
	 * adds user to database, returns particular string depending on success
	 * 
	 * @param userID
	 * @param password
	 * @param email
	 * @param admin
	 * @return int success/failed
	 */
	public String addUser(String userID, String password, String email,
			boolean admin) {
		getConnection();
		if (containsUserID(userID)) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return ADD_ID_USED;
		} else if (password.length() < MIN_PASSWORD_LENGTH) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return ADD_SMALL_PASSWORD;
		} else if (containsEmail(email)) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return ADD_EMAIL_USED;
		} else {
			String hashedPw = hashPw(password);
			try {
				stmt.executeUpdate("insert into " + MyDBInfo.USER_TABLE
						+ " values('" + userID + "', '" + hashedPw + "', '"
						+ email + "', " + admin + ", 0);");
				addCart(userID);
				con.close();
				return ADD_SUCCESSFUL;
			} catch (SQLException e) {
				try {
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
				return ADD_UNSUCCESSFUL;
			}
		}
	}

	private void addCart(String userID) {
		try {
			stmt.executeUpdate("insert into " + MyDBInfo.CART_TABLE
					+ "(userID, price) values('" + userID + "', 0);");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * checks if userID is already in database
	 * 
	 * @param userID
	 * @return boolean successful
	 */
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

	/**
	 * checks if email is already used by another account
	 * 
	 * @param String
	 *            email
	 * @return boolean
	 */
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

	/**
	 * returns hashed password of user
	 * 
	 * @param userID
	 * @return String hashedPass
	 */
	public String getHashedPassword(String userID) {
		String hashedPw = (String) getContentByID("password", userID,
				MyDBInfo.USER_TABLE);
		return hashedPw;
	}

	/**
	 * returns email of user
	 * 
	 * @param String
	 *            userID
	 * @return String email
	 */
	public String getEmail(String userID) {
		String email = (String) getContentByID("email", userID,
				MyDBInfo.USER_TABLE);
		return email;
	}

	/**
	 * returns type(Administrator, User) of user
	 * 
	 * @param String
	 *            userID
	 * @return boolean isAdmin
	 */
	public boolean getAdmin(String userID) {
		boolean bool = (boolean) getContentByID("admin", userID,
				MyDBInfo.USER_TABLE);
		return bool;
	}

	/**
	 * returns catastrophe points that user has
	 * 
	 * @param String
	 *            userID
	 * @return Integer points
	 */
	public Integer getPoints(String userID) {
		Integer points = (Integer) getContentByID("points", userID,
				MyDBInfo.USER_TABLE);
		return points;
	}

	/**
	 * sets users's CP to points
	 * 
	 * @param String
	 *            userID
	 * @param Integer
	 *            points
	 */
	public void setPoints(String userID, int points) {
		getConnection();
		try {
			stmt.executeUpdate("update " + MyDBInfo.USER_TABLE
					+ " set points = " + points + " where userID = '" + userID
					+ "';");
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * adds some points to user CP
	 * 
	 * @param String
	 *            userID
	 * @param Integer
	 *            points
	 */
	public void addPoints(String userID, int points) {
		getConnection();
		try {
			stmt.executeUpdate("update " + MyDBInfo.USER_TABLE
					+ " set points = points + " + points + " where userID = '"
					+ userID + "';");
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * checks if password matches user's real password
	 * 
	 * @param String
	 *            userID
	 * @param String
	 *            password
	 * @return boolean
	 */
	public boolean checkInfo(String userID, String password) {
		getConnection();
		ResultSet rs;
		try {
			rs = stmt
					.executeQuery("select password from " + MyDBInfo.USER_TABLE
							+ " where userID = '" + userID + "';");
			String hashedPw = "";
			while (rs.next()) {
				hashedPw = rs.getString("password");
			}
			con.close();
			if (hashedPw.equals(hashPw(password)))
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * returns different content by parameters: column and id value
	 * 
	 * @param String
	 *            column
	 * @param String
	 *            userID
	 * @param String
	 *            table
	 * @return Object content
	 */
	private Object getContentByID(String column, String userID, String table) {
		getConnection();
		ResultSet rs;
		Object val = null;
		try {
			rs = stmt.executeQuery("Select " + column + " from " + table
					+ " where userID = '" + userID + "'");
			while (rs.next())
				val = rs.getObject(1);
			con.close();
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
