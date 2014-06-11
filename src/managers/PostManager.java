package managers;

import java.sql.Timestamp;
import java.util.ArrayList;

import webPackage.MyDBInfo;
import webPackage.Post;

public class PostManager {

	private static String account = MyDBInfo.MYSQL_USERNAME;
	private static String password = MyDBInfo.MYSQL_PASSWORD;
	private static String server = MyDBInfo.MYSQL_DATABASE_SERVER;
	private static String database = MyDBInfo.MYSQL_DATABASE_NAME;
	
	
	public PostManager(){
		
	}
	
	/*
	 * Returns the array of recent posts(array can be resized)
	 */
	public ArrayList<Post> getRecentPosts() {
		return null;
	}
	
	/*
	 * Returns the array of popular posts(array can be resized)
	 */
	public ArrayList<Post> getPopularPosts() {
		return null;
	}
	
	/*
	 * Adds post into DB
	 */
	public int addPost(String authorID, Timestamp timestamp, String title,
			String status, String attachment, String type) {
		return 0;
	}
	
	/*
	 * User with ID-userID likes post with ID-postID
	 */
	public void likePost(int postID, String userID) {
	}
	
	/*
	 * User with ID-userID dislikes post with ID-postID
	 */
	public void dislikePost(int postID, String userID) {
	}
	
	/*
	 * User with ID-userID comments on post with ID-postID. Time and text included
	 */
	public void addComment(int postID, String userID, String Text,
			Timestamp timestamp) {
	}
}

