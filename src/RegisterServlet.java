import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.ProfileManager;
import webPackage.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		String ID = request.getParameter("user_login");
		String pw = request.getParameter("user_password");
		String email = request.getParameter("user_email");
		User tmp = new User(ID, pw, email, false, 0);
		String x = tmp.registerUser();
		RequestDispatcher dp;
		if (x.equals(ProfileManager.ADD_SUCCESSFUL)) {
			dp = request.getRequestDispatcher("index.jsp");
			request.getSession().setAttribute("currentUser", tmp);
			request.getSession().setAttribute("createAccountMessage",
					"Please enter proposed Information");
			dp.forward(request, response);
			return;
		} else {
			request.getSession().setAttribute("createAccountMessage", x);
		}
		dp = request.getRequestDispatcher("createAccount.jsp");
		dp.forward(request, response);
	}

}
