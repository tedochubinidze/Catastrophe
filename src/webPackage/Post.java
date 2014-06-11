package webPackage;

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

	/*
	 * Sets ID for this post
	 */
	public void setID(int ID) {
		this.ID = ID;
	}

	/*
	 * Sets comments for this post
	 */
	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}

	/*
	 * Returns Array of comments on current post
	 */
	public ArrayList<Comment> getComments() {
		return comments;
	}

	/*
	 * Adds a comment
	 */
	public void addComment(Comment comment) {
		manager.addComment(this.ID, comment.user.ID, comment.text,
				comment.timestamp);
		comments.add(comment);
		commentCount++;

	}

	/*
	 * Gets post ID
	 */
	public int getID() {
		return ID;
	}

	/*
	 * Gets post like count
	 */
	public int getLikeCount() {
		return likeCount;
	}

	/*
	 * Gets post dislike count
	 */
	public int getDislikeCount() {
		return dislikeCount;
	}

	/*
	 * Gets post comment count
	 */
	public int getCommentCount() {
		return commentCount;
	}

	/*
	 * Gets post author user ID
	 */
	public String getUserID() {
		return userID;
	}

	/*
	 * Gets post type
	 */
	public String getType() {
		return type;
	}

	/*
	 * Gets post title
	 */
	public String getTitle() {
		return title;
	}

	/*
	 * Gets status
	 */
	public String getStatus() {
		return status;
	}

	/*
	 * Gets attachments name
	 */
	public String getAttachment() {
		return attachment;
	}

	/*
	 * Gets posts posting time
	 */
	public Timestamp getTimesTamp() {
		return timestamp;
	}

	/*
	 * Returns true if post like/dislike count is active
	 */
	public boolean isActive() {
		return active;
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
