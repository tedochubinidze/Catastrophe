package webPackage;

import java.util.ArrayList;

import webPackage.Product;

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

	@Override
	public String toString() {
		return products.toString();
	}

}
