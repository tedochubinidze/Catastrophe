

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.ProductManager;
import webPackage.Product;

/**
 * Servlet implementation class AddProductDetailsServlet
 */
@MultipartConfig
@WebServlet("/AddProductDetailsServlet")
public class AddProductDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductDetailsServlet() {
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
		// TODO Auto-generated method stub
		
		String price = request.getParameter("price");
		String title = request.getParameter("title");
		String description = request.getParameter("textArea");
		
		System.out.println(title);
		System.out.println(description);
		System.out.println(price);
		
		Integer priceInt = Integer.parseInt(price);

		String fileName = (String) request.getSession().getAttribute("fileName");
		
		System.out.println(fileName);

		Product prod = new Product(0, title, priceInt, fileName, description);
		
		ProductManager manager = new ProductManager();
				
		manager.addProduct(prod);
		
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		
	}

}
