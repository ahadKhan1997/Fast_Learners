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
<title>Product List</title>
</head>
<%HttpSession sess = request.getSession(false); //use false to use the existing session
sess.getAttribute("username");%>
<body bgcolor="grey">
<form method="post" action="./Buy">
<ul>
    <li ><a href="welcome.jsp">Home</a></li>
    <li ><a href="http://localhost:8081/SimpleWebApp/productList.jsp">Product List </a></li>
    <li style="float:right"><a class="active" href="./Logout">Logout</a>
    <li style="float:right"><a class="active">Hello ${username}</a>
    
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
<td>ID</td>
<td>NAME</td>
<td>PRICE</td>
</tr>
<%
try
{
Class.forName("org.postgresql.Driver");
String query="select * from public.product";
Connection conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","root");
Statement stmt=conn.createStatement();
ResultSet rs=stmt.executeQuery(query);
while(rs.next())
{

%>
    <tr><td><%=rs.getInt("code") %></td>
    <td><%=rs.getString("name") %></td>
    <td><%=rs.getInt("price") %></td>
    <td><input type="text" name="id" placeholder="enter here!!!">
<input type="submit" value="buy now"></td></tr>
        <%

}
%>

  </table>
    <%
    rs.close();
    stmt.close();
    conn.close();
    }
catch(Exception e)
{
    e.printStackTrace();
    }
%>

</form>
</body>
</html>