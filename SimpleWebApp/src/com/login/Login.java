package com.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 12L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		int flag = 0;
		String username = request.getParameter("name");
		String password = request.getParameter("password");
		try
		{
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","root");
			Statement ps = con.createStatement();
			ResultSet rs = ps.executeQuery("select name, password from public.login");
			System.out.println("Result Set executed");
			while(rs.next())
			{
				if((rs.getString(1).equals(username)) && (rs.getString(2).equals(password)))
					flag = 1;
			}
			if(flag == 1)
			{
				HttpSession sess = request.getSession(true); 
				sess.setAttribute("username", username);
				System.out.println("valid user");
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("welcome.jsp");
				
				requestDispatcher.forward(request, response);

			}
			else
				out.println("invalid user");
		}
		catch(Exception e)
		{
			out.println(e);
			e.printStackTrace();
		}
		out.close();
	}

}
