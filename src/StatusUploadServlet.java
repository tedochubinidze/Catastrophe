import java.awt.Image;
import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import managers.PostManager;

import org.apache.catalina.connector.Request;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

import sun.net.www.http.HttpClient;
import webPackage.Post;
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
		System.out.println(fileName);
		System.out.println(fileType);
		
		User user = (User)request.getSession().getAttribute("currentUser");
		String userID = user.getID();
		
		PostManager manager = new PostManager();
		manager.addPost(userID, new Timestamp(System.currentTimeMillis()), title, area, fileName, fileType);
		
		request.setAttribute("message",
				"Post Uploaded Successfully");

		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
