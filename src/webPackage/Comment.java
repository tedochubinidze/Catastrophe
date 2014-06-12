package webPackage;

import java.sql.Timestamp;

public class Comment {
	
	//Instance variables, dzzmaci
	public User user;
	public String text;
	public Timestamp timestamp;
	
	//Public constructor for this type.
	public Comment(User user, String text, Timestamp timestamp) {
		this.user = user;
		this.text = text;
		this.timestamp = timestamp;
	}
	
	//My version of toString for comments.
	@Override
	public String toString() {
		String str = "";
		str += "User: " + user.getID + " text: " + text + " date" + timestamp;
		return str;
	}
	
}
