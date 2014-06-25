package webPackage;

import java.util.ArrayList;

public class Cart {
	private int price;
	private ArrayList<Product> products;

	public Cart() {
		products = new ArrayList<Product>();
	}

	public void addProduct(Product product) {
		products.add(product);
		price += product.getPrice();

	}

	public void removeProduct(Product product) {
		for (int i = 0; i < products.size(); i++) {
			if (product.equals(products.get(i))) {
				products.remove(i);
				price -= product.getPrice();
			}
		}
	}

	public int getCartPrice() {
		return price;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void cleanCart() {
		products.clear();
		price = 0;
	}

	public boolean equals(Object obj) {
		boolean result = false;
		if (obj == null || obj.getClass() != getClass()) {
			result = false;
		} else {
			Cart tmp = (Cart) obj;
			if (this.getProducts().equals(tmp.getProducts()))
				result = true;
		}
		return result;

	}

	@Override
	public String toString() {
		return products.toString();
	}

}
