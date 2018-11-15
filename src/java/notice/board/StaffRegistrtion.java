package notice.board;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
@MultipartConfig(maxFileSize = 16177215) 
public class StaffRegistrtion extends HttpServlet {

   public static final String Query1="INSERT INTO staff1 VALUES(staff_id.nextval,?,?,?,?,?,?,?,?)";
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	Date sdob;
	java.util.Date	udob;
	SimpleDateFormat sdf=null;
	File file=null;
	InputStream inputstream=null;
	public void init()
	{
		con=conn.connection.getConnection();
		try
		{
		//create prepare statement
		ps=con.prepareStatement(Query1);

		}//try
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fname,pass,repass,tyear,tmobile,gender,uname,dob,post;
	int count=0;
	long mobileno,ms=0;
        //set general settings
		response.setContentType("text/html");
		PrintWriter pw =response.getWriter();
		
		//get parameter from html file
		fname=request.getParameter("Fname");
                gender=request.getParameter("gender");
                dob=request.getParameter("dob");
                sdf=new SimpleDateFormat("yyyy-MM-dd");
                post=request.getParameter("course");
                tmobile=request.getParameter("mobile");
		mobileno=Long.parseLong(tmobile);
		uname=request.getParameter("uname");
		pass=request.getParameter("password");
                repass=request.getParameter("repsw");
                 Part filePart = request.getPart("fileupload");
                  if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());

            // obtains input stream of the upload file
            inputstream = filePart.getInputStream();
        }
                 try
		{
                    //operation on date
                if(sdf!=null)
                    udob=sdf.parse(dob);
                    if(dob!=null)
                       ms=udob.getTime();
                        sdob= new java.sql.Date(ms);
                
               //set query parameter
		ps.setString(1, fname);
                ps.setString(2,gender);
                ps.setDate(3,sdob);
                ps.setString(4,post);
                ps.setLong(5,mobileno);
                ps.setString(6,uname);
                ps.setString(7,pass);
                ps.setBlob(8, inputstream);
		//exceute query
                if(pass.equals(repass))
		count=ps.executeUpdate();
                else
                {
                     pw.println("<script type=\"text/javascript\">");
		   pw.println("alert('both password must be same');");
                    pw.println("location='StaffRegistrtion.html';");
		   pw.println("</script>");
                }
                if(count>0)
                {
                    pw.println("<script type=\"text/javascript\">");
		   pw.println("alert('record is inserted');");
                   pw.println("location='Staff login.html';");
		   pw.println("</script>");
                }
                else
                     pw.println("recored is not inserted");
                }
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
        
    }

   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

   

}
