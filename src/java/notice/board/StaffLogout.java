package notice.board;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class StaffLogout extends HttpServlet {

  
    

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html");  
            PrintWriter pw=response.getWriter();  
                HttpSession session=request.getSession();  
            session.invalidate();  
              
            pw.println("<script type=\"text/javascript\">");
	   pw.println("alert('your are logout successfully..');");
	   pw.println("location.replace('locahost:8084/Myproject/StaffLogin.html')");
	   pw.println("</script>"); 
              
            pw.close();  
        
    }

    

}
