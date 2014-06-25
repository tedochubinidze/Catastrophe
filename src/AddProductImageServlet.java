import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import managers.PostManager;
import managers.ProductManager;
import webPackage.Product;
import webPackage.User;

/**
 * Servlet implementation class AddProductServlet
 */
@MultipartConfig
@WebServlet("/AddProductImageServlet")
public class AddProductImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String DATA_DIRECTORY = "C:/Users/Nikoloz/Documents/GitHub/Catastrophe/WebContent/images";
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddProductImageServlet() {
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

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		if (!isMultipart) {
			
			request.setAttribute("message", "Not MultiPart!!!");
			
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

			String fileName;
			
			while (iter.hasNext()) {
			
				
				FileItem item = (FileItem) iter.next();
				
				if (!item.isFormField()) {
					
					if(items.size() < 1){
						fileName = "No" ;
					} else {
						fileName = item.getName();
						String filePath = uploadFolder + "/" + fileName;
						File uploadedFile = new File(filePath);
						request.getSession().setAttribute("fileName", fileName);
						item.write(uploadedFile);
						System.out.println(filePath);
						System.out.println(fileName);
					}
				}
				
			}

		} catch (FileUploadException e) {
			throw new ServletException(e);
		}

		catch (Exception e) {
			request.setAttribute("message", "File Upload Failed To " + e);
		}
		
		request.getRequestDispatcher("/addDescriptions.jsp").forward(request, response);
	}
	

}
