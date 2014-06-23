import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webPackage.Comment;
import webPackage.Post;
import webPackage.User;

/**
 * Servlet implementation class AddCommentServlet
 */
@WebServlet("/AddCommentServlet")
public class AddCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCommentServlet() {
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
		String text = request.getParameter("comment");
		String id = request.getParameter("postID");
		System.out.println(id);
		String page = "post.jsp?id=" + id;
		Comment comment = new Comment(user, text, new Timestamp(
				System.currentTimeMillis()));
		Post post = new Post(Integer.parseInt(id));
		post.addComment(comment);
		RequestDispatcher dp;
		dp = request.getRequestDispatcher(page);
		dp.forward(request, response);
	}
}
