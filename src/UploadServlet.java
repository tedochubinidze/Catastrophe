import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.PostManager;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UploadServlet
 */
@MultipartConfig
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// /aq shecvale shentan ra foldershic ginda imashi rom chaagdos
	private static final String DATA_DIRECTORY = "C:/images";

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
		String name = request.getParameter("name");
		// your image servlet code here

		response.setContentType("image/jpeg");

		// Set content size
		String path = "C:\\images\\";
		File file = new File(path + name);
		response.setContentLength((int) file.length());

		// Open the file and output streams
		FileInputStream in = new FileInputStream(file);
		OutputStream out = response.getOutputStream();

		// Copy the contents of the file to the output stream
		byte[] buf = new byte[1024];
		int count = 0;
		while ((count = in.read(buf)) >= 0) {
			out.write(buf, 0, count);
		}
		in.close();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		// Enumeration paramaterNames = request.getParameterNames();
		// while(paramaterNames.hasMoreElements() ) {
		// System.out.println("elementi: " + paramaterNames.nextElement());
		// }
		//
		// System.out.println(request.getParameter("textArea"));
		// System.out.println(request.getParameter("title"));
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

			String fileName;
			String type;

			while (iter.hasNext()) {

				count++;
				FileItem item = (FileItem) iter.next();

				if (!item.isFormField()) {

					if (items.size() < 1) {
						fileName = "No";
						type = "status";
						request.getSession().setAttribute("fileName", fileName);
						request.getSession().setAttribute("fileType", type);
					} else {
						lastIDPlusOne = manager.getLastID() + 1;
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
