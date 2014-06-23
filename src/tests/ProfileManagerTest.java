package tests;
import managers.ProfileManager;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ProfileManagerTest {
	private ProfileManager manager;
	private final String userID = "user1";

	@Before
	public void setUP() {
		manager = new ProfileManager();
	}

	// test for add user. this test must be done only once!!!! (after adding
	// userID to database, it can't add another user with same ID)
	@Test
	public void test() {
		assertEquals(ProfileManager.ADD_SUCCESSFUL, manager.addUser("user1",
				"123123", "email1", false));
	}

	// test for getters
	@Test
	public void test2() {
		assertEquals("123123", manager.getHashedPassword(userID));
		assertEquals("email1", manager.getEmail(userID));
		assertEquals(false, manager.getAdmin(userID));
	}

	// check if return right errors for addUser
	@Test
	public void test3() {
		assertEquals(ProfileManager.ADD_ID_USED, manager.addUser("user1", "123123",
			 "email2", false));
		assertEquals(ProfileManager.ADD_EMAIL_USED, manager.addUser("user2", "123123",
				 "email1", false));
		assertEquals(ProfileManager.ADD_SMALL_PASSWORD, manager.addUser("user3", "3",
				 "email3", false));
	}
	
	// check for account and email existence
	@Test
	public void test4() {
		assertEquals(true, manager.containsUserID("user1"));
		assertEquals(false, manager.containsUserID("user2"));
		assertEquals(true, manager.containsEmail("email1"));
		assertEquals(false, manager.containsUserID("email2"));
	}
	
	// check for add and set points
	@Test
	public void test5() {
		Integer x = 5;
		manager.setPoints(userID, x);
		assertEquals(x, manager.getPoints(userID));
		manager.addPoints(userID, 10);
		x = 15;
		assertEquals(x, manager.getPoints(userID));
	}
	
	// test for checkInfo
	@Test
	public void test6() {
		assertEquals(true, manager.checkInfo(userID, "123123"));
		assertEquals(false, manager.checkInfo(userID, "1"));
		// check for account that doesn't exist
		assertEquals(false, manager.checkInfo("asrfijqeerhjfdnsf", "123123"));
	}
	
	
}
