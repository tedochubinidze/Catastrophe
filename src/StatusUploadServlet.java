import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.PostManager;
import webPackage.User;

/**
 * Servlet implementation class UploadServlet
 */
@MultipartConfig
@WebServlet("/StatusUploadServlet")
public class StatusUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StatusUploadServlet() {
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
		// TODO Auto-generated method stub
	
		String title = request.getParameter("title");
		String area = request.getParameter("textArea");
		String fileName = (String) request.getSession().getAttribute("fileName");
		String fileType = (String) request.getSession().getAttribute("fileType");
		
		User user = (User)request.getSession().getAttribute("currentUser");
		String userID = user.getID();
		
		PostManager manager = new PostManager();
		manager.addPost(userID, new Timestamp(System.currentTimeMillis()), title, area, fileName, fileType);
		
		request.setAttribute("message",
				"Post Uploaded Successfully");

		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
