package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import webPackage.Cart;
import webPackage.Product;

public class CartTest {
	private Integer ID1 ;
	private Integer ID2 ;
	private Cart cart;
	private Product product1;
	private Product product2;
	private String title;
	private int price1;
	private int price2;
	
	@Before
	public void setUp(){
		ID1 = 1;
		ID2 = 2;
		title = "Test Product";
		price1 = 100;
		price2 = 200;
		
		cart = new Cart();
		product1 = new Product(ID1, title, price1, null, null);
		product2 = new Product(ID2, title, price2, null, null);
		cart.addProduct(product1);
	}
	
	@Test
	public void getTest() {
		assertEquals(100, cart.getCartPrice());
		assertEquals(1, cart.getProducts().size());
	}
	
	@Test 
	public void addProducts(){
		cart.addProduct(product2);
		assertEquals(product1, cart.getProducts().get(0));
		assertEquals(product2, cart.getProducts().get(1));
	}
	
	@Test
	public void removeProducts(){
		ArrayList<Product> array = new ArrayList<>();
		cart.addProduct(product2);
		cart.removeProduct(product1);
		assertEquals(product2, cart.getProducts().get(0));
		cart.removeProduct(product2);
		assertEquals(array, cart.getProducts());
	}

}
