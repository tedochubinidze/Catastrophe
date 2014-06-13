package webPackage;

public class Product {
	private int ID, price;
	private String title, image, description;
	
	
	public Product(Integer ID, String title, int price, String image, String description) {
		this.title = title;
		this.price = price;
		this.image = image;
		this.description = description;
		this.ID = ID;
	}
	
	/**
	 * Sets product ID
	 * @param int ID
	 */
	public void setID(int ID){
		this.ID = ID;
	}
	
	/**
	 * Returns products price
	 * @return int price
	 */
	public int getPrice(){
		return price;
	}
	
	/**
	 * Returns products title
	 * @return String title
	 */
	public String getTitle(){
		return title;
	}
	
	/**
	 * Returns products IMG title
	 * @return String image
	 */
	public String getImage(){
		return image;
	}
	
	/**
	 * Returns products description
	 * @return String description
	 */
	public String getDiscription(){
		return description;
	}

	/**
	 * Returns products ID
	 * @return int ID
	 */
	public int getID() {
		return ID;
	}
}
