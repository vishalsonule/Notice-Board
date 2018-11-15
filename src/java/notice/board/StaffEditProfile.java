package notice.board;

import java.io.IOException;
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


public class StaffEditProfile extends HttpServlet {

  Connection con=null;
 PreparedStatement ps=null;
 HttpSession session=null;
 ResultSet rs=null;
 long mobile;
 String post,pass1;
 int year;
 
 String query="select mobileno,post,pass from staff1 where uname=? and pass=?";

 String uname,pass,name;
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
         session = request.getSession();
         try{
        uname=session.getAttribute("name").toString();
        pass=session.getAttribute("pass").toString();
         }
        catch(NullPointerException npe)
        {
       out.println("<script type=\"text/javascript\">");
		   out.println("alert('please login first');");
		   out.println("location='StaffLogin.html';");
		   out.println("</script>");
        }
       
        
            con=conn.connection.getConnection();
            try{
            ps=con.prepareStatement(query);
             //set query parameter
            ps.setString(1,uname);
            ps.setString(2, pass);
            //execute query
            rs=ps.executeQuery();
            //process resultset
            
        
            if(rs.next())
            {
            mobile = rs.getLong(1);  
             post= rs.getString(2);
              pass1=rs.getString(3);
            }
               }//try
            catch(SQLException se)
            {
            se.printStackTrace();
            }
            
            out.println("<!DOCTYPE html>\n" +
"<html>\n" +
"<head>\n" +
"<link rel=\"stylesheet\" href=\"css/edit.css\">\n" +
"</head>\n" +
"<body>\n" +
"\n" +
"  <div class=\"container\">\n" +
"    <h1>update profile</h1>\n" +
"    <p>you have parmission to change only following content for more detail contact to admin.</p>\n" +
"    <hr>\n" +
"	<div>\n" +
"	\n" +
"	<form action=\"StaffEditPhoto\" style=\"border:1px solid #ccc\" method=\"post\" enctype=\"multipart/form-data\">\n" +
"	<fieldset>\n" +
"	<legend>\n" +
"	update profile photo\n" +
"	</legend>\n" +
"	<label for=\"fileToUpload\"><b>upload here:</b></label>\n" +
"	  <input type=\"file\" name=\"fileToUpload\" >\n" +
"	  <button type=\"submit\" class=\"uploadbtn\">upload</button>\n" +
"	</fieldset>\n" +
"	</form>\n" +
"	</div>\n" +
"	\n" +
"	\n" +
"	<form action=\"StaffUpdateProfile\" style=\"border:1px solid #ccc\" method=\"post\" >\n" +
"	<fieldset>\n" +
"		<legend>personal information</legend>\n" +
"		<label for=\"mobile\"><b>Mobile no</b></label>\n" +
"    	<input type=\"text\" placeholder=\"Enter Mobile No\" name=\"mobile\" value='"+mobile+"' required>\n" +
"	\n" +
"		<label for=\"course\"><b>Post</b></label>\n" +
"    	<input type=\"text\" placeholder=\"Enter Post\" name=\"course\" value='"+post+"'required>\n" +
"\n" +
"    	<label for=\"psw\"><b>Password</b></label>\n" +
"    	<input type=\"password\" placeholder=\"Enter Password\" name=\"psw\" value='"+pass1+"'required>\n" +
"\n" +
"    	<label for=\"psw-repeat\"><b>Repeat Password</b></label>\n" +
"    		<input type=\"password\" placeholder=\"Repeat Password\" name=\"psw-repeat\" required>\n" +
"\n" +
"		<div class=\"clearfix\">\n" +
"     		 <button type=\"button\" class=\"cancelbtn\">Cancel</button>\n" +
"      		<button type=\"submit\" class=\"signupbtn\">update</button>\n" +
"   		 </div>\n" +
"  \n" +
"  </fieldset>\n" +
"</form>\n" +
"</div>\n" +
"</body>\n" +
"</html>");
                    
    }

  
}
