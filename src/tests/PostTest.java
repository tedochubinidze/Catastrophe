package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import webPackage.User;
import org.junit.Before;
import org.junit.Test;

import webPackage.Comment;
import webPackage.Post;

public class PostTest {
	private Post post;
	private String title1 = "Post title N1";
	private int ID1 = 5;
	private String userID1 = "User.123";
	private int likeCount1 = 10;
	private int dislikeCount1 = 0;
	private String status1 = "Hello, this is test N1";
	private String type1 = "VIDEO";
	private String attachment = "Att name directory";
	private boolean isActive = true;
	private ArrayList<Comment> comments;
	private java.sql.Timestamp tmp;
	@Before
	public void setUp(){
		comments = new ArrayList<>();
		tmp = new java.sql.Timestamp(System.currentTimeMillis());
		post = new Post(ID1, userID1, likeCount1, dislikeCount1, tmp , title1, status1, type1, attachment, isActive, comments);
	}
	@Test
	public void testGetters1() {
		assertEquals(ID1, post.getID());
		assertEquals(dislikeCount1, post.getDislikeCount());
		assertEquals(likeCount1, post.getLikeCount());
	}
	
	@Test
	public void testGetters2(){
		assertEquals(title1, post.getTitle());
		assertEquals(userID1, post.getUserID());
		assertEquals(status1, post.getStatus());
		assertEquals(type1, post.getType());
		assertEquals(attachment, post.getAttachment());
		assertEquals(isActive, post.isActive());
		
	}
	
	@Test 
	public void test(){ 
		User user = new User(userID1);
		Comment comment = new Comment(user, "Test number one", tmp);
		post.addComment(comment);
		assertEquals(1, post.getCommentCount());
		
	}

}
