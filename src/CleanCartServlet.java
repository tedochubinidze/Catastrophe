

import java.io.IOException;

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
 * Servlet implementation class CleanCartServlet
 */
@WebServlet("/CleanCartServlet")
public class CleanCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CleanCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductManager manager = (ProductManager) request.getServletContext()
				.getAttribute("productManager");
		User user = (User) request.getSession().getAttribute("currentUser");
		Cart cart = user.getCart();
		cart.cleanCart();
		manager.cleanCart(user.getID());
		request.getRequestDispatcher("cart.jsp").forward(request, response);
	}

}
