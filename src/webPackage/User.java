package webPackage;

public class User {
	
	public String ID, password, name, lastname, email;
	public int points;
	public boolean admin;
	
	//This is Constructor For Class User, which takes User from DataBase
	//according to this parameters
	public User(String userID, String password, String name, String lastname,
			String email, boolean admin, int points) {
		
	}
	
	//This is Constructor of Other kind, which creates User
	public User(String userID) {
		
	}

	//Registers current User
	public String registerUser() {
		return ID;
		
	}
	
	//takes action, when current user likes this post
	public void likePost(Post post) {
		
	}

	//takes action, when current user dislikes this post
	public void dislikePost(Post post) {
		
	}

	//takes action, when user uploads given post
	public void addPost(Post post) {
		
	}

}
