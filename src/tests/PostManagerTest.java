package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.ArrayList;

import managers.PostManager;
import managers.ProfileManager;

import org.junit.Before;
import org.junit.Test;

import webPackage.Comment;
import webPackage.Post;
import webPackage.User;

public class PostManagerTest {
	private PostManager manager;
	private ProfileManager profManager;
	private final String userID1 = "1";
	private final String userID2 = "2";
	private Post post1, post2, post3;
	private User user1;

	@Before
	public void setUP() {
		manager = new PostManager();
		profManager = new ProfileManager();
		post1 = new Post(1, userID1, 0, 0,
				Timestamp.valueOf("2014-01-01 10:10:10.0"), "title", "status",
				"status", null, true, null);
		post2 = new Post(2, userID2, 0, 0,
				Timestamp.valueOf("2014-01-02 10:10:10.0"), "title", "status",
				"status", null, true, null);
		post3 = new Post(3, userID1, 0, 0,
				Timestamp.valueOf("2014-01-03 10:10:10.0"), "title", "status",
				"status", null, true, null);
		user1 = new User(userID1, "123123", "name1", "lastname1", "email",
				false, 0);
		profManager.addUser(user1.getID(), user1.getPassword(),
				user1.getName(), user1.getLastName(), user1.getEmail(), false);
		profManager.addUser(userID2, "123123", "name2", "lastname2", "email2",
				false);
	}

	@Test
	// test for single post add, check for recent, popular posts
	// check for recent and popular posts for particular user
	public void test1() {
		int tmp = manager.addPost(post1.getUserID(), post1.getTimesTamp(),
				post1.getTitle(), post1.getStatus(), post1.getAttachment(),
				post1.getType());
		assertNotEquals(0, tmp);
		post1.setID(tmp);
		ArrayList<Post> ls = new ArrayList<>();
		ls.add(post1);

		ArrayList<Post> ls2 = manager.getPopularPosts();
		assertEquals(ls, ls2);

		ArrayList<Post> ls3 = manager.getRecentPosts();
		assertEquals(ls, ls3);

		ArrayList<Post> ls4 = manager.getRecentPostsByUser(userID1);
		assertEquals(ls, ls4);

		ArrayList<Post> ls5 = manager.getRecentPostsByUser(userID1);
		assertEquals(ls, ls5);
	}

	@Test
	// check recentPost and recentPostByUser for many posts
	public void test2() {
		int tmp = manager.addPost(post2.getUserID(), post2.getTimesTamp(),
				post2.getTitle(), post2.getStatus(), post2.getAttachment(),
				post2.getType());
		assertNotEquals(0, tmp);
		post2.setID(tmp);

		int tmp2 = manager.addPost(post3.getUserID(), post3.getTimesTamp(),
				post3.getTitle(), post3.getStatus(), post3.getAttachment(),
				post3.getType());
		assertNotEquals(0, tmp2);
		post3.setID(tmp2);
		ArrayList<Post> recents = new ArrayList<>();
		recents.add(post3);
		recents.add(post2);
		recents.add(post1);

		assertEquals(recents, manager.getRecentPosts());

		ArrayList<Post> recentsByUser1 = new ArrayList<Post>();
		recentsByUser1.add(post3);
		recentsByUser1.add(post1);

		assertEquals(recentsByUser1, manager.getRecentPostsByUser(userID1));
	}

	@Test
	// check for likePost
	// check recentPost and recentPostByUser for many posts
	public void test3() {
		manager.likePost(post3.getID(), userID1);
		assertTrue(manager.likesPost(userID1, post3.getID()));

		manager.dislikePost(post2.getID(), userID2);
		assertTrue(manager.dislikesPost(userID2, post2.getID()));

		ArrayList<Post> populars = new ArrayList<Post>();
		populars.add(post3);
		populars.add(post1);
		populars.add(post2);

		assertEquals(populars, manager.getPopularPosts());

		ArrayList<Post> popularsByUser1 = new ArrayList<Post>();
		popularsByUser1.add(post3);
		popularsByUser1.add(post1);

		assertEquals(popularsByUser1, manager.getPopularPostsByUser(userID1));
	}

	@Test
	// check addComments and getComments
	public void test4() {
		ArrayList<Comment> comments = new ArrayList<Comment>();
		Comment com = new Comment(user1, "kaia",
				Timestamp.valueOf("2014-02-05 10:10:10.0"));
		comments.add(com);
		manager.addComment(post1.getID(), com.user.getID(), com.text,
				com.timestamp);
		assertEquals(comments, manager.getComments(post1.getID()));
	}
}
