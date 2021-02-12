package com.pay;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class Pay
 */
@WebServlet("/Pay")
public class Pay extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession sess = request.getSession(false); //use false to use the existing session
		String data = (String) sess.getAttribute("username");
		String bookname = (String) sess.getAttribute("name");
		String price = (String) sess.getAttribute("price");
		int flag=0;
	    Connection con = null;
	    try {
	      Class.forName("org.postgresql.Driver");
	      con = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/postgres","postgres","root");
	      System.out.println(""+ data);
	      DatabaseMetaData meta = con.getMetaData();
	      ResultSet res = meta.getTables(null, null, null,
	         new String[] {"TABLE"});
	      System.out.println("List of tables: ");
	      while(res.next() && flag == 0) {
	    	  if(res.getString("TABLE_NAME").contentEquals(data))
	    	  {
	    		  PreparedStatement ps=con.prepareStatement(
	    					"insert into public."+data+" values(?,?)");
	    					 ps.setString(1,bookname);  
	    					 ps.setString(2,price);  
	    					 int i=ps.executeUpdate();
	    					 flag = 1;
	    	  }

	      }
	      if(flag==0)
	      {
	    	  //create a new table for user and store purchased items.
	    	  Statement stmt = con.createStatement();
	    	   stmt.executeUpdate("CREATE TABLE PUBLIC."+data+" (product_name VARCHAR, price VARCHAR)");
	            stmt.executeUpdate("INSERT INTO PUBLIC."+data+" VALUES ('"+bookname+"','"+price+"')");
	    	  
	      }
	      RequestDispatcher requestDispatcher = request.getRequestDispatcher("done.jsp");
			
			requestDispatcher.forward(request, response);
	      res.close();
	      con.close();
	    } catch (Exception e) {
	      System.err.println("Exception: "+e.getMessage());
	    }
	  }
	}
