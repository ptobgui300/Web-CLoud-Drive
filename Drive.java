package finalProj;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.FileUpload;

/**
 * Servlet implementation class Drive
 */
@WebServlet("/Drive")
public class Drive extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ArrayList<FileUpload> uploads = new ArrayList<FileUpload>();
		
		
		ServletContext context = this.getServletContext();
		context.setAttribute("fileUploads", uploads);
	}
	private String getUserName(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("userName")) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<FileUpload> uploads = (ArrayList<FileUpload>) this.getServletContext().getAttribute("fileUploads");
		
		uploads.clear();
		String user = getUserName(request);
		if(user != null) {
			String fileDir = getServletContext().getRealPath("/WEB-INF/uploads/" + user);
			File folder = new File(fileDir);
			
			try {
				for(File file : folder.listFiles()) {
					uploads.add(new FileUpload(fileDir + "/" + file.getName(), file.getName()));
				}
			} catch(Exception e) {
				folder.mkdir();
			}
			
			
			request.getRequestDispatcher("/WEB-INF/views/ViewFiles.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("/Drive/Login").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
