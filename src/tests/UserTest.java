package tests;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;

import webPackage.Comment;
import webPackage.Post;
import webPackage.User;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
	private User user1;
	private User user2;
	private Post post1;
	private String title1 = "Post title N1";
	private int ID1 = 5;
	private String userID1 = "User.123";
	private String password = "password";
	private int likeCount1 = 10;
	private int dislikeCount1 = 0;
	private String status1 = "Hello, this is test N1";
	private String type1 = "VIDEO";
	private String attachment = "Att name directory";
	private boolean isActive = true;
	private ArrayList<Comment> comments;
	private String email = "email123";

	@Before
	public void setUp() {
		user1 = new User(userID1, password, email, true, 69);
		user2 = new User(userID1);
		post1 = new Post(ID1, userID1, likeCount1, dislikeCount1, null, title1,
				status1, type1, attachment, isActive, comments);
		user1.addPost(post1);
		try {
			user1.hashPassword(password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testConstructor() {
		assertTrue(user1.equals(user2));
		assertTrue(user1.getPassword().equals(user2.getPassword()));
		assertTrue(user1.getEmail().equals(user2.getEmail()));
		assertTrue(user1.getID().equals(user2.getID()));
		assertTrue(user1.getPoints() == user2.getPoints());
		assertTrue(user1.getRecentPosts()
				.equals(user2.getRecentPosts()));
	}

	@Test
	public void testGetters() {
		assertEquals(userID1, user1.getID());
		assertEquals(password, user1.getPassword());
		assertEquals(email, user1.getEmail());
		assertTrue(user1.isAdmin());
	}
	
	@Test
	public void testPosts(){ 
		assertEquals(1, user1.getRecentPosts().size());
		assertTrue(user1.getRecentPosts().get(0).equals(post1));
	}
	
	@Test 
	public void testPosts2(){
		ArrayList<Post> posts = new ArrayList<>();
		for(int i = 0 ; i < 40; i++){
			java.sql.Timestamp tmp = new java.sql.Timestamp(System.currentTimeMillis());
			Post post = new Post(i, userID1, likeCount1, dislikeCount1, tmp, title1,
					status1, type1, attachment, isActive, comments);
			user1.addPost(post);
			if(i < 20 ){
			posts.add(post);
			}
		}
		for(int i = 0 ; i < user1.getRecentPosts().size(); i++){
			assertTrue( posts.get(i).getTimesTamp().equals(user1.getRecentPosts().get(i).getTimesTamp()));
		}
	}
	
	@Test 
	public void testEquals(){ 
		User user = new User("1", "1", "1", true, 1);
		assertTrue(user2.equals(user1));
		assertFalse(user2.equals(user));
		
	}
	
}
