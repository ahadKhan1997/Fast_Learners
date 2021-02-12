<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
 <%@ page import = "java.io.*" %>
  <%@ page import = "java.sql.*" %>
<% Class.forName("org.postgresql.Driver"); %>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="home.css">
  <link rel="stylesheet" href="webpage.css">
<title>recent orders</title>
</head>
<%HttpSession sess = request.getSession(false); //use false to use the existing session
String username=(String) sess.getAttribute("username");%>
<body bgcolor="grey">
<ul>
    <li ><a href="welcome.jsp">Home</a></li>
    <li ><a href="http://localhost:8081/SimpleWebApp/productList.jsp">Product List </a></li>
    <li style="float:right"><a class="active">${username}'s recent orders</a>
    
  </ul>
  
<table style="width:300px;
    height:300px;
    background-color:#d9d9d9;
    position:fixed;
    margin-left:-150px; /* half of width */
    margin-top:-150px;  /* half of height */
    top:50%;
    left:50%;">

	<tr>
<td>NAME</td>
<td>PRICE</td>
</tr>
<%
PrintWriter outt = response.getWriter();
int flag =0;
try
{
Class.forName("org.postgresql.Driver");
String query="select * from public."+username+"";
Connection conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","root");
Statement stmt=conn.createStatement();
ResultSet rs=stmt.executeQuery(query);
while(rs.next())
{

%>
    <tr><td><%=rs.getString("product_name") %></td>
    <td><%=rs.getInt("price") %></td></tr>
        <%

}
%>

  </table>
    <%
    if(flag==0)
    	outt.println("no purchases made in along time!!");
    rs.close();
    stmt.close();
    conn.close();
    }
catch(Exception e)
{
    e.printStackTrace();
    }
%>
</body>
</html>