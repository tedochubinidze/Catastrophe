package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import webPackage.Comment;
import webPackage.User;

public class CommentTest {
	private Comment comment;
	private User user;
	private User user1;
	private String text;
	private String text1;
	private java.sql.Timestamp tmp;
	private java.sql.Timestamp tmp1;
	private Comment comment1;
	private Comment comment2;
	private Comment comment3;
	private Comment realComment;
	

	
	@Before
	public void setUp(){
		user = new User("test", "123", null, false, 0);
		user1 = new User("test another user", "123", null, false, 0);
		text = "Best video";
		text1 = "Worst video";
		tmp = new java.sql.Timestamp(System.currentTimeMillis());
		tmp1 = new java.sql.Timestamp(System.currentTimeMillis());
		comment = new Comment(user, text, tmp);
		comment1 = new Comment(user1, text, tmp);
		comment2 = new Comment(user, text1, tmp);
		comment3 = new Comment(user, text, tmp1);
		realComment = new Comment(user, text, tmp);
	}
	@Test
	public void testGetters() {
		assertEquals(text, comment.getText());
		assertEquals(user.getID(), comment.getUserId());
		assertEquals(tmp, comment.getTimestamp());
		
	}
	
	@Test
	public void testEquals(){
		assertFalse(comment.equals(user));
		assertFalse(comment.equals(comment1));
		assertFalse(comment.equals(comment2));
		assertTrue(comment.equals(comment3));
		assertTrue(comment.equals(realComment));
	}

}
