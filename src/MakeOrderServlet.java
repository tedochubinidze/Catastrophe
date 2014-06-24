import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.ProductManager;
import webPackage.Cart;
import webPackage.Order;
import webPackage.User;

/**
 * Servlet implementation class MakeOrderServlet
 */
@WebServlet("/MakeOrderServlet")
public class MakeOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MakeOrderServlet() {
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
		String address = request.getParameter("address");
		ProductManager manager = (ProductManager) request.getServletContext()
				.getAttribute("productManager");
		if (user.getPoints() >= cart.getCartPrice()) {
			Order order = new Order(0, user.getID(), address, new Timestamp(
					System.currentTimeMillis()));
			manager.makeOrder(order);
			user.addPoints(-cart.getCartPrice());
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request
					.getRequestDispatcher("notEnoughn.jsp");
			rd.forward(request, response);
		}
	}

}
