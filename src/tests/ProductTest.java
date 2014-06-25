package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import webPackage.Product;

public class ProductTest {
	Product p1, p2, p3;

	@Before
	public void setUp() {
		p1 = new Product(1, "title1", 100, "image1", "status1");
		p2 = new Product(1, "title2", 12, "image2", "status2");
		p3 = new Product(2, "title1", 100, "image1", "status1");
	}

	@Test
	// test equals
	public void test() {
		assertTrue(p1.equals(p2));
		assertFalse(p1.equals(p3));
	}

	@Test
	// test getters
	public void test2() {
		assertEquals(1, p1.getID());
		assertEquals("title1", p1.getTitle());
		assertEquals(100, p1.getPrice());
		assertEquals("image1", p1.getImage());
		assertEquals("status1", p1.getDiscription());
	}

	@Test
	// test setter
	public void test3() {
		p1.setID(2);
		assertEquals(2, p1.getID());
		assertFalse(p1.equals(p2));
		assertTrue(p1.equals(p3));
	}
}
