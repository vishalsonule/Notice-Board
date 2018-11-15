
package notice.board;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Profile extends HttpServlet {

 Connection con=null;
 PreparedStatement ps=null;
 HttpSession session=null;
 ResultSet rs=null;
 String uname,pass,name,course;
 InputStream is=null;
OutputStream os=null;
byte[] buffer=null;
int bytesRead=0;
 String query="(select course,full_name from users1 where uname=? and pass=?)";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter(); 
        con=conn.connection.getConnection();
        session = request.getSession();
        try{
        uname=session.getAttribute("name").toString();
        pass=session.getAttribute("pass").toString();
        }
        catch(NullPointerException npe)
        {
            out.println("<script type=\"text/javascript\">");
		   out.println("alert('please login first');");
		   out.println("location='login.html';");
		   out.println("</script>");
        }
        if(uname!=null && pass!=null)
        {
        try
        {
           //create prepared statement object
            ps=con.prepareStatement(query);
            //set parametet
            ps.setString(1,uname);
            ps.setString(2,pass);
            //execute query
            rs=ps.executeQuery();
            //fetch records form result set
            while(rs.next())
            {
            name=rs.getString(2);
            course=rs.getString(1);
            }
        }//try
        catch(SQLException se)
        {
        se.printStackTrace();
        }
        
        
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Profile</title>");   
            out.println("<link rel=\"stylesheet\" href=\"css/profile.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2 style=\"text-align:center\">Student profile</h2>");
            out.println("<div class='card'>");
            out.println(" <img src='Image' alt=\"picture\" style=\"width:100%\">");
             out.println("<div class='notice-student'>");
            out.println("<h1>"+name+"</h1>");
            out.println("<p class=\"title\">"+course+"</p>");
            out.println("<p>SRTM University Nanded</p>");
             out.println("</div>");
            out.println("<div style=\"margin: 24px 0;\">");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }//if
        else
        {
                    out.println("<script type=\"text/javascript\">");
		   out.println("alert('please login first');");
		   out.println("location='login.html';");
		   out.println("</script>");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
