package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import managers.ProductManager;
import managers.ProfileManager;

import org.junit.Before;
import org.junit.Test;

import webPackage.Cart;
import webPackage.Product;
import webPackage.User;

public class ProductManagerTest {
	String userID = "productManagerTester";
	ProductManager manager;
	ProfileManager profileManager;

	@Before
	public void setUp() {
		profileManager = new ProfileManager();
		profileManager.addUser("productManagerTester", "123545",
				"email@email.com", false);
		manager = new ProductManager();
	}

	@Test
	public void test() {
		Product prod1 = new Product(0, "title1", 100, "image1", "description1");
		prod1.setID(manager.addProduct(prod1));
		ArrayList<Product> ls1 = new ArrayList<Product>();
		ls1.add(prod1);
		assertEquals(ls1, manager.getProducts());

		Product prod2 = new Product(0, "title2", 100, "image2", "description2");
		prod2.setID(manager.addProduct(prod2));
		ls1.add(prod2);
		assertEquals(ls1, manager.getProducts());

		Cart cart = new Cart();
		cart.addProduct(prod1);
		manager.addProductToCart(userID, prod1);
		assertEquals(cart, new User(userID).getCart());

		cart.addProduct(prod2);
		manager.addProductToCart(userID, prod2);
		assertEquals(cart, new User(userID).getCart());
		
		cart.removeProduct(prod1);
		manager.removeProductFromCart(userID, prod1);
		assertEquals(cart, new User(userID).getCart());
	}

}
