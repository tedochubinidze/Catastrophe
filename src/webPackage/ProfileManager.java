public class ProfileManager {

	public ProfileManager() {

	}

	public String addUser(String userID, String password, String name,
			String lastname, String email, boolean admin) {

	}

	// checks if userID is already in database
	public boolean containsUserID(String userID) {

	}

	// checks if email is already used by another account
	public boolean containsEmail(String email) {

	}

	// returns hashed password of user
	public String getHashedPassword(String userID) {

	}

	// returns name of user
	public String getName(String userID) {

	}

	// returns last name of user
	public String getLastName(String userID) {

	}

	// returns email of user
	public String getEmail(String userID) {

	}

	// returns type(Administrator, User) of user
	public String getType(String userID) {

	}

	// returns points that user has
	public Integer getPoints(String userID) {

	}

	// sets users's CP to points
	public void setPoints(String userID, int points) {

	}

	// adds some points to user CP
	public void addPoints(String userID, int points) {

	}

	// checks if password matches user's real password
	public boolean checkInfo(String userID, String password) {

	}

	// returns Arraylist of popular posts by particular user
	public ArrayList<Post> getRecentPosts(String userID) {

	}

	// returns Arraylist of recent posts by particular user
	public ArrayList<Post> getPopularPosts(String userID) {

	}
}
