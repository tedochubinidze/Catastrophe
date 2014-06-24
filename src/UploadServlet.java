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
import webPackage.User;

/**
 * Servlet implementation class UploadServlet
 */
@MultipartConfig
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	///aq shecvale shentan ra foldershic ginda imashi rom chaagdos
	private static final String DATA_DIRECTORY = "C:/Users/Nikoloz/Documents/GitHub/Catastrophe/WebContent/images";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadServlet() {
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
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		
		
//		Enumeration paramaterNames = request.getParameterNames();
//		while(paramaterNames.hasMoreElements() ) {  
//		       System.out.println("elementi: " + paramaterNames.nextElement());  
//		}
//		
//		System.out.println(request.getParameter("textArea"));
//		System.out.println(request.getParameter("title"));
//		
		
		if (!isMultipart) {
			
			request.setAttribute("message", "Not MultiPart!!!");
			request.getRequestDispatcher("/result.jsp").forward(request,
					response);
			return;
		}
		
		
		FileItemFactory factory = new DiskFileItemFactory();
		
		String uploadFolder = DATA_DIRECTORY;
		
		File uploadDir = new File(uploadFolder);
		
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		ServletFileUpload servUpl = new ServletFileUpload(factory);
		
		try {
			
			List items = servUpl.parseRequest(request);
			
			if (items.isEmpty()) {
				request.setAttribute("message", "Empty List Of Items");
				return;
			}
			Iterator iter = items.iterator();
			int lastIDPlusOne = -1;
			PostManager manager = new PostManager();
			
			int count = 1;
		
//			if(request.getParameter("title")==null)
//				System.out.println("modefakaaaaskdjfna;wejfnal;wkefnjsjafrna");
			String fileName;
			String type;
			
			while (iter.hasNext()) {
			
				System.out.println("shemovidaaaaa" + count);
				count++;
				FileItem item = (FileItem) iter.next();
				
				if (!item.isFormField()) {
					
					if(items.size() < 1){
						fileName = "No" ;
						type = "status" ;
						request.getSession().setAttribute("fileName", fileName);
						request.getSession().setAttribute("fileType", type);
					} else {
						System.out.println("axla gavapren");
						//lastIDPlusOne = manager.getLastID() + 1;
						fileName = lastIDPlusOne + "-" + item.getName(); 
						String filePath = uploadFolder + "/" + fileName;
						String context = item.getContentType();
						type = context.substring(0, context.indexOf('/'));
						File uploadedFile = new File(filePath);
						request.getSession().setAttribute("fileName", fileName);
						request.getSession().setAttribute("fileType", type);
						item.write(uploadedFile);
					}
				}

			}

		} catch (FileUploadException e) {
			throw new ServletException(e);
		}

		catch (Exception e) {
			request.setAttribute("message", "File Upload Failed To " + e);
		}
		
		request.getRequestDispatcher("/status.jsp").forward(request, response);
	}

}
