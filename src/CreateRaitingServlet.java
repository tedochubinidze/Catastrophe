

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webPackage.Post;
import webPackage.User;
import managers.PostManager;

/**
 * Servlet implementation class CreateReitingServlet
 */
@WebServlet("/CreateReitingServlet")
public class CreateRaitingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateRaitingServlet() {
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
		PostManager manager = (PostManager)request.getServletContext().getAttribute("postManager");
		ArrayList<Post> popular = manager.getPopularActivePosts();
		int points = 1000;
		for(int i=0; i<20; i++) {
			if (i == popular.size()) break;
			Post current = popular.get(i);
			User user = new User(current.getUserID());
			user.addPoints(points);
			points = points - i*25;
		}
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}

}