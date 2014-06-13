package webPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import managers.PostManager;

public class Post {
	private int ID, likeCount, dislikeCount, commentCount;
	private String userID, type, title, status, attachment;
	private Timestamp timestamp;
	private boolean active;
	private PostManager manager;
	private ArrayList<Comment> comments;
	private static Statement stmt;

	public Post(Integer ID, String userID, int likeCount, int dislikeCount,
			Timestamp timestamp, String title, String status, String type,
			String attachment, boolean active, ArrayList<Comment> comments2) {
		this.ID = ID;
		this.userID = userID;
		this.likeCount = likeCount;
		this.dislikeCount = dislikeCount;
		if (comments2 == null)
			commentCount = 0;
		else
			this.commentCount = comments2.size();
		this.timestamp = timestamp;
		this.title = title;
		this.status = status;
		this.type = type;
		this.attachment = attachment;
		this.active = true;
		this.comments = comments2;
		manager = new PostManager();
	}
	
	public Post(String userID) {
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
		getPostByID(userID);
	}

	// this method takes User Info from database with given ID
	private void getPostByID(String userID) {
		ResultSet rs;
		try {
			rs = stmt.executeQuery("select * from " + MyDBInfo.POST_TABLE
					+ " where userID = '" + userID + "';");
			while (rs.next()) {
				this.ID = rs.getInt(1);
				this.userID = rs.getString(2);
				this.likeCount = rs.getInt(3);
				this.dislikeCount = rs.getInt(4);
				this.commentCount = rs.getInt(5);
				this.timestamp = rs.getTimestamp(6);
				this.title = rs.getString(7);
				this.status = rs.getString(8);
				this.attachment = rs.getString(9);
				this.type = rs.getString(10);
				this.active = rs.getBoolean(7);
			}
			manager = new PostManager();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Sets ID for this post
	 **/
	public void setID(int ID) {
		this.ID = ID;
	}

	/**
	 * Sets comments for this post
	 */
	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}

	/**
	 * Returns Array of comments on current post
	 */
	public ArrayList<Comment> getComments() {
		return comments;
	}

	/**
	 * Adds a comment
	 */
	public void addComment(Comment comment) {
		manager.addComment(this.ID, comment.user.getID(), comment.text,
				comment.timestamp);
		comments.add(comment);
		commentCount++;

	}

	/**
	 * User with ID-userID likes this post
	 */
	public void likePost(String userID) {
		if (!manager.likesPost(userID, this.ID)) {
			if (manager.dislikesPost(userID, this.ID)) {
				dislikeCount--;
			}
			manager.likePost(this.ID, userID);
			likeCount++;
		}

	}

	/**
	 * User with ID-userID dislikes post
	 */
	public void dislikePost(String userID) {
		if (!manager.dislikesPost(userID, this.ID)) {
			if (manager.likesPost(userID, this.ID)) {
				likeCount--;
			}
			manager.dislikePost(this.ID, userID);
			dislikeCount++;
		}

	}

	/**
	 * Gets post ID
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Gets post like count
	 */
	public int getLikeCount() {
		return likeCount;
	}

	/**
	 * Gets post dislike count
	 */
	public int getDislikeCount() {
		return dislikeCount;
	}

	/**
	 * Gets post comment count
	 */
	public int getCommentCount() {
		return commentCount;
	}

	/**
	 * Gets post author user ID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * Gets post type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Gets post title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Gets status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Gets attachments name
	 */
	public String getAttachment() {
		return attachment;
	}

	/**
	 * Gets posts posting time
	 */
	public Timestamp getTimesTamp() {
		return timestamp;
	}

	/**
	 * Returns true if post like/dislike count is active
	 */
	public boolean isActive() {
		return active;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj == null || obj.getClass() != getClass()) {
			result = false;
		} else {
			Post tmp = (Post) obj;
			if (this.getID() == tmp.getID())
				result = true;
		}
		return result;
	}

	@Override
	public String toString() {
		String str = "";
		str += "ID: " + ID + " userID: " + userID + " likeCount: " + likeCount
				+ " dislikeCount: " + dislikeCount + " commentCount: "
				+ commentCount + " Date: " + timestamp + " Active:" + active;
		return str;

	}

}
