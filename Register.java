package finalProj;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class Register
 */
@WebServlet("/Drive/Register")
public class Register extends HttpServlet {
	   private static final long serialVersionUID = 1L;
	    
	    private int error = -1;
	    /**
	     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	     */
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        request.getRequestDispatcher("/WEB-INF/views/RegisterView.jsp?error=" + error).forward(request, response);
	    }
	    /**
	     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	     */
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String username = request.getParameter("username");
	        String password = request.getParameter("password");
	        Connection c = null;
	        
	        try
	        {
	            if(!username.equals("") && !password.equals("")) {
	                String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu76";
	                String usernameDB = "cs3220stu76";
	                String passwordDB = "yXyLUyer";
	                c = DriverManager.getConnection( url, usernameDB, passwordDB );
	                
	                Statement test = c.createStatement();
	                
	                ResultSet rs = test.executeQuery( "select * from users where username = \"" + username + "\";");
	                if(!rs.next()) {
	                    Statement stmt = c.createStatement();
	                    stmt.executeUpdate("INSERT INTO `users` (`username`, `password`) VALUES ('" + username + "', sha1('" + password + "'));" );
	    
	                    String fileDir = getServletContext().getRealPath("/WEB-INF/uploads/" + username);
	                    File folder = new File(fileDir);
	                    folder.mkdir();
	                    
	                    Cookie user = new Cookie("userName", username);
	                    user.setMaxAge(5*60);
	                    response.addCookie(user);
	                    response.sendRedirect("/CS3220Project/Drive");
	                }
	                else {
	                    error = 1;
	                    doGet(request, response);
	                }
	            }
	            else {
	                error=0;
	                doGet(request, response);
	            }
	            
	        }
	        catch( SQLException e )
	        {
	            throw new ServletException( e );
	        }
    }
}