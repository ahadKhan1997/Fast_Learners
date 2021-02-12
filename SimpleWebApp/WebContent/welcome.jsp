<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
  <link rel="stylesheet" href="home.css">
  <link rel="stylesheet" href="webpage.css">
<title>Home</title>
</head>
<%HttpSession sess = request.getSession(false); //use false to use the existing session
sess.getAttribute("username");%>
<body bgcolor="black">
  
  <ul>
    <li ><a href="welcome.jsp">Home</a></li>
    <li ><a href="http://localhost:8081/SimpleWebApp/productList.jsp">Product List </a></li>
    <li style="float:right"><a href="./Logout">Logout</a>
    <li style="float:right"><a class="active" href="userdetails.jsp">welcome ${username}</a>
    
  </ul>
  <img id="img1" src="logo.png.png" >
</body>
</html>