package finalProj;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.FileUpload;
/**
 * Servlet implementation class Rename
 */
@WebServlet("/Drive/Rename")
public class Rename extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/RenameView.jsp?name=" + request.getParameter("name")).forward(request, response);
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newName = request.getParameter("newName");
        String oldName = request.getParameter("name");
        
        String user = getUserName(request);
        String fileDir = getServletContext().getRealPath("/WEB-INF/uploads/" + user);
        File folder = new File(fileDir);
        
        if(!newName.contains(".")) {
            newName += oldName.substring(oldName.indexOf("."));
        }
        
        ArrayList<FileUpload> files = (ArrayList<FileUpload>) this.getServletContext().getAttribute("fileUploads");
        if(newNameExists(newName, files))
        {
            doGet(request, response);
        }
        
        for(File file : folder.listFiles())
        {
            if(file.getName().equals(oldName))
            {
                File newFile = new File(folder + "/" + newName);
                
                file.renameTo(newFile);
            
                response.sendRedirect("/CS3220Project/Drive");
            }
        }
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
    
    private boolean newNameExists(String newName, ArrayList<FileUpload> files)
    {
        for(FileUpload file : files) {
            if(file.getFileName().equals(newName))
                return true;
        }
        
        return false;
    }
}
