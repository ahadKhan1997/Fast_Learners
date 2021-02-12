package com.buy;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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
 * Servlet implementation class Buy
 */
@WebServlet("/Buy")
public class Buy extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("please wait while we process!!");
		String code = request.getParameter("id");
		try{
			Class.forName("org.postgresql.Driver");
		Connection conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","root");
		System.out.println("connection estabilished");
		Statement stmt=conn.createStatement();
		ResultSet rs = stmt.executeQuery("select code, name, price from public.product");
		System.out.println("you are in result set now");
		while(rs.next())
		{
			if(rs.getString(1).equals(code))
			{
				System.out.println("book found");
				String book = rs.getString("name");
				String price = rs.getString("price");
				HttpSession sess = request.getSession(); 
				sess.setAttribute("name",book);
				sess.setAttribute("price",price);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("payment.jsp");
				
				requestDispatcher.forward(request, response);
				
			}
			
		}
		out.println("wrong ID...\n Try entering 0 followed by ID number..");
	
	
		rs.close();
	    stmt.close();
	    conn.close();
	    }
	catch(Exception e)
	{
	    e.printStackTrace();
	    }
	}
}