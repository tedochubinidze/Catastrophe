package webPackage;

import java.sql.Timestamp;
import java.util.ArrayList;


import managers.PostManager;

public class Post {
	public int ID, likeCount, dislikeCount, commentCount;
	public String userID, type, title, status, attachment;
	public Timestamp timestamp;
	public boolean active;
	public PostManager manager;
	private ArrayList<Comment> comments;
	
	public Post(int ID, String userID, int likeCount, int dislikeCount,
			int commentCount, Timestamp timestamp, String title, String status,
			String type, String attachment, boolean active,
			ArrayList<Comment> ls) {
	}
	
	/* 
	 * Returns Array of comments on current post
	 */
	public ArrayList<Comment> getComments(){
		return null;
	}
	
	/*
	 * Adds a comment 
	 */
	public void addComment(Comment comment){
		
	}
	
	@Override
	public String toString(){
		return "";
		
	}

	
}
