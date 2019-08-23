package finalProj;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/Drive/Remove")
public class Remove extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		

		String name = request.getParameter("fileName");
		String username = getUserName(request);
        String fileDir = getServletContext().getRealPath("/WEB-INF/uploads/" + username +"/"+name);
        File deleteFile = new File(fileDir) ;
        // check if the file is present or not
        if( deleteFile.exists() ) {
        	deleteFile.delete();
        }
        response.sendRedirect("/CS3220Project/Drive");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

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

}