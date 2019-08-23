package finalProj;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Drive/Login")

public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {

		super.init(config);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/views/LoginView.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userName = request.getParameter("username");
		String password = request.getParameter("password");

		Connection c = null;

		try {

			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu76";
			String username = "cs3220stu76";
			String _password = "yXyLUyer";

			c = DriverManager.getConnection(url, username, _password);

			Statement test = c.createStatement();

			ResultSet rs = test.executeQuery("select * from users where username = \"" + userName
					+ "\"  and password = sha1('" + password + "');");

			if (rs.next()) {

				Cookie user = new Cookie("userName", userName);
				user.setMaxAge(5 * 60);
				response.addCookie(user);
				response.sendRedirect("/CS3220Project/Drive");
				//request.getRequestDispatcher("/Drive").forward(request, response);

			} else {
				response.sendRedirect("/CS3220Project/Drive");
			}

		}

		catch (SQLException e) {

			throw new ServletException(e);

		} finally {

			try {

				if (c != null)

					c.close();

			} catch (SQLException e) {

				throw new ServletException(e);

			}

		}

	}

}
