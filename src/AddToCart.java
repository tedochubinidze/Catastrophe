import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.ProductManager;
import webPackage.Cart;
import webPackage.Product;
import webPackage.User;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddToCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("currentUser");
		Cart cart = user.getCart();
		// this parameter will soon be added to product.jsp
		String ID = request.getParameter("productID");
		Product product = new Product(Integer.parseInt(ID));
		cart.addProduct(product);
		ProductManager manager = (ProductManager) request.getServletContext()
				.getAttribute("productManager");
		manager.addProductToCart(user.getID(), product);
		RequestDispatcher rd = request.getRequestDispatcher("product.jsp?id="
				+ product.getID());
		rd.forward(request, response);
	}

}
