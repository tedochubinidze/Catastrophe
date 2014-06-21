

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webPackage.Post;
import webPackage.User;

/**
 * Servlet implementation class DislikeServlet
 */
@WebServlet("/DislikeServlet")
public class DislikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DislikeServlet() {
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
		Post post = (Post)request.getServletContext().getAttribute("currentPost");
		User user = (User)request.getSession().getAttribute("currentUser");
		post.dislikePost(user.getID());
		RequestDispatcher dp = request.getRequestDispatcher("post.jsp?id=" + post.getID());
		dp.forward(request, response);
	}

}
