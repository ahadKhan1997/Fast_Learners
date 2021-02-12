package com.register;

import java.io.IOException;
import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String n=request.getParameter("name");
		String e=request.getParameter("email");
		String p=request.getParameter("password");
		String m=request.getParameter("mobile");
		try{
			 Class.forName("org.postgresql.Driver");
			 Connection con=DriverManager.getConnection(
			 "jdbc:postgresql://localhost:5432/postgres","postgres","root");
			 System.out.println("connection success");
			 PreparedStatement ps=con.prepareStatement(
			"insert into public.register values(?,?,?,?)");
			 ps.setString(1,p);  
			 ps.setString(2,n);  
			 ps.setString(3,e);  
			 ps.setString(4,m);  
			           
			 int i=ps.executeUpdate();  
			 PreparedStatement ps1=con.prepareStatement(
					 "insert into public.login values(?,?)");
					 ps1.setString(1,n);
					 ps1.setString(2,p);
					 int k=ps1.executeUpdate(); 
			 
					 if(i>0)
					 {
					  out.print("You are successfully registered...");
					  response.sendRedirect("login.html");
					 }
					 if(k>0)
					 {
					 out.println("login database updated updated");
					 } 
			 
		}
		catch(Exception er)
		 {
		 out.println(er);
		 er.printStackTrace();
		 }
		out.close(); 
		
		
	}

}
