package finalProj;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/Drive/Upload")
public class Upload extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
	protected void doGet( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
        // Redirect the user to the upload form
    	request.getRequestDispatcher("/WEB-INF/views/Upload.jsp").forward(request, response);
    }

    @Override
	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Configure a repository (to ensure a secure  location is used)
        ServletContext servletContext = this.getServletConfig().getServletContext();
        
        File repository = (File) servletContext
            .getAttribute( "javax.servlet.context.tempdir" );
        
        factory.setRepository( repository );

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload( factory );
        
       String username=null;
        Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("userName")) {
					username= cookie.getValue();
				}
			}
		}
        String fileDir = getServletContext().getRealPath("/WEB-INF/uploads/" + username);
        

        // Parse the request
        try
        {
            List<FileItem> items = upload.parseRequest( request );
            
            for( FileItem item : items )
            {

                if( !item.isFormField() )
                {
                	
                    String fileName = (new File( item.getName() )).getName();
                    File file = new File( fileDir, fileName );
                    item.write( file );

                }
                
            }

        }
        catch( Exception e )
        {
            throw new IOException( e );
        }
        response.sendRedirect("/CS3220Project/Drive");
    }

}
	
