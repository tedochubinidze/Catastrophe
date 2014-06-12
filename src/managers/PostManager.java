package managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import webPackage.Comment;
import webPackage.MyDBInfo;
import webPackage.Post;
import webPackage.User;

public class PostManager {

	private static String account = MyDBInfo.MYSQL_USERNAME;
	private static String password = MyDBInfo.MYSQL_PASSWORD;
	private static String server = MyDBInfo.MYSQL_DATABASE_SERVER;
	private static String database = MyDBInfo.MYSQL_DATABASE_NAME;
	private static Statement stmt, stmt2;

	private static final int MAX_N_POSTS = 20;

	public PostManager() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://"
					+ server, account, password);
			stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			stmt2 = con.createStatement();
			stmt2.executeQuery("USE " + database);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Returns the array of recent posts(array can be resized)
	 */
	public ArrayList<Post> getRecentPosts() {
		ArrayList<Post> ls = new ArrayList<Post>();
		ResultSet rs;
		try {
			rs = stmt.executeQuery("select * from " + MyDBInfo.POST_TABLE
					+ " order by timestamp desc limit " + MAX_N_POSTS + ";");
			while (rs.next()) {
				int postID = rs.getInt(1);
				ArrayList<Comment> ls2 = getComments(postID);
				Post post = new Post(postID, rs.getString(2), rs.getInt(3),
						rs.getInt(4), rs.getTimestamp(5), rs.getString(6),
						rs.getString(7), rs.getString(8), rs.getString(9),
						rs.getBoolean(10), ls2);
				ls.add(post);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}

	/*
	 * Returns the array of popular posts(array can be resized)
	 */
	public ArrayList<Post> getPopularPosts() {
		ArrayList<Post> ls = new ArrayList<Post>();
		ResultSet rs;
		try {
			rs = stmt.executeQuery("select * from " + MyDBInfo.POST_TABLE
					+ " order by likeCount - dislikeCount desc limit "
					+ MAX_N_POSTS + ";");
			while (rs.next()) {
				int postID = rs.getInt(1);
				ArrayList<Comment> ls2 = getComments(postID);
				Post post = new Post(postID, rs.getString(2), rs.getInt(3),
						rs.getInt(4), rs.getTimestamp(5), rs.getString(6),
						rs.getString(7), rs.getString(8), rs.getString(9),
						rs.getBoolean(10), ls2);
				ls.add(post);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}

	public ArrayList<Comment> getComments(int postID) {
		ArrayList<Comment> ls = new ArrayList<Comment>();
		ResultSet tmp;
		try {
			tmp = stmt2.executeQuery("select * from " + MyDBInfo.COMMENT_TABLE
					+ " where postID = " + postID + ";");
			while (tmp.next()) {
				Comment cm = new Comment(new User(tmp.getString(2)),
						tmp.getString(3), tmp.getTimestamp(4));
				ls.add(cm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}

	// returns Arraylist of popular posts by particular user
	public ArrayList<Post> getRecentPostsByUser(String userID) {
		ArrayList<Post> ls = new ArrayList<Post>();
		ResultSet rs;
		try {
			rs = stmt.executeQuery("select * from " + MyDBInfo.POST_TABLE
					+ " where authorID='" + userID
					+ "' order by timestamp desc limit 20;");
			while (rs.next()) {
				// es shesacvleli iqneba constructori tu shevucvalet posts
				int postID = rs.getInt(1);
				ArrayList<Comment> ls2 = getComments(postID);
				Post posts = new Post(postID, rs.getString(2), rs.getInt(3),
						rs.getInt(4), rs.getTimestamp(6), rs.getString(7),
						rs.getString(8), rs.getString(9), rs.getString(10),
						rs.getBoolean(11), ls2);
				ls.add(posts);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}

	// returns Arraylist of recent posts by particular user
	public ArrayList<Post> getPopularPostsByUser(String userID) {
		ArrayList<Post> ls = new ArrayList<Post>();
		ResultSet rs;
		try {
			rs = stmt.executeQuery("select * from " + MyDBInfo.POST_TABLE
					+ " where authorID='" + userID
					+ "' order by likeCount - dislikeCount desc limit 20;");
			while (rs.next()) {
				// es shesacvleli iqneba constructori tu shevucvalet posts
				int postID = rs.getInt(1);
				ArrayList<Comment> ls2 = getComments(postID);
				Post posts = new Post(postID, rs.getString(2), rs.getInt(3),
						rs.getInt(4), rs.getTimestamp(6), rs.getString(7),
						rs.getString(8), rs.getString(9), rs.getString(10),
						rs.getBoolean(11), ls2);
				ls.add(posts);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}

	/*
	 * Adds post into DB
	 */
	public int addPost(String authorID, Timestamp timestamp, String title,
			String status, String attachment, String type) {
		try {
			stmt.executeUpdate(
					"insert into "
							+ MyDBInfo.POST_TABLE
							+ "(AuthorID, LikeCount, DislikeCount, CommentCount, timeStamp, Title, Status, Attachment, Type, Active) values('"
							+ authorID + "', 0, 0, 0, '" + timestamp + "', '"
							+ title + "', '" + status + "', '" + attachment
							+ "', '" + type + "', true);",
					stmt.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.getGeneratedKeys();
			int id = 0;
			if (rs != null && rs.next()) {
				id = rs.getInt(1);
			}
			;
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/*
	 * User with ID-userID likes post with ID-postID
	 */
	public void likePost(int postID, String userID) {
		try {
			if (dislikesPost(userID, postID)) {
				stmt.executeUpdate("delete from disliked_post where userID = '"
						+ userID + "';");
				stmt.executeUpdate("update post set dislikeCount = dislikeCount - 1 where postID = "
						+ postID + ";");
			}
			if (!likesPost(userID, postID)) {
				stmt.executeUpdate("insert into liked_post values (" + postID
						+ ", '" + userID + "');");
				stmt.executeUpdate("update post set likeCount = likeCount + 1 where postID = "
						+ postID + ";");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Checks whether user with ID-userId likes post with ID-postID
	 */
	public boolean likesPost(String userID, int postID) {
		return getLikeInfo(userID, postID, MyDBInfo.LIKE_TABLE);
	}

	/*
	 * Checks whether user with ID-userId dislikes post with ID-postID
	 */
	public boolean dislikesPost(String userID, int postID) {
		return getLikeInfo(userID, postID, MyDBInfo.DISLIKE_TABLE);
	}

	/*
	 * User with ID-userID dislikes post with ID-postID
	 */
	public void dislikePost(int postID, String userID) {
		try {
			if (likesPost(userID, postID)) {
				stmt.executeUpdate("delete from liked_post where userID = '"
						+ userID + "';");
				stmt.executeUpdate("update post set likeCount = likeCount - 1 where postID = "
						+ postID + ";");
			}
			if (!dislikesPost(userID, postID)) {
				stmt.executeUpdate("insert into " + MyDBInfo.DISLIKE_TABLE
						+ " values (" + postID + ", '" + userID + "');");
				stmt.executeUpdate("update "
						+ MyDBInfo.POST_TABLE
						+ " set dislikeCount = dislikeCount + 1 where postID = "
						+ postID + ";");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * User with ID-userID comments on post with ID-postID. Time and text
	 * included
	 */
	public void addComment(int postID, String userID, String Text,
			Timestamp timestamp) {
		try {
			stmt.executeUpdate("insert into " + MyDBInfo.COMMENT_TABLE
					+ " values (" + postID + ", '" + userID + "', '" + Text
					+ "', '" + timestamp + "');");
			stmt.executeUpdate("update " + MyDBInfo.POST_TABLE
					+ " set commentCount = commentCount + 1 where postID = "
					+ postID + ";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private boolean getLikeInfo(String userID, int postID, String table) {
		ResultSet rs;
		int x = 0;
		try {
			rs = stmt.executeQuery("select count(postID) from " + table
					+ " where userID = '" + userID + "' and postID = " + postID
					+ ";");
			while (rs.next())
				x = rs.getInt("count(postID)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x != 0;
	}

}
