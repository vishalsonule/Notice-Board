/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notice.board;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class StudLogout extends HttpServlet {

  
    

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
	   //pw.println("location='login.html';");
	   pw.println("</script>");
          response.sendRedirect("login.html");
              
            pw.close();  
        
    }

    

}
