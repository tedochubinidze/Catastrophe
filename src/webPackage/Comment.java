package webPackage;

import java.sql.Timestamp;

public class Comment {

	// Instance variables
	private User user;
	private String text;
	private Timestamp timestamp;

	// Public constructor for this type.
	public Comment(User user, String text, Timestamp timestamp) {
		this.user = user;
		this.text = text;
		this.timestamp = timestamp;
	}
	
	public String getUserId(){
		return user.getID();
		
	}
	
	public String getText(){
		return text;
	}
	
	public Timestamp getTimestamp(){
		return timestamp;
	}

	// My version of toString for comments.

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj == null || obj.getClass() != getClass()) {
			result = false;
		} else {
			Comment tmp = (Comment) obj;
			if (this.user.equals(tmp.user) && this.text.equals(tmp.text)
					&& this.timestamp.equals(tmp.timestamp))
				result = true;
		}
		return result;
	}

	@Override
	public String toString() {
		String str = "";
		str += "User: " + user.getID() + " text: " + text + " date" + timestamp;
		return str;
	}

}
