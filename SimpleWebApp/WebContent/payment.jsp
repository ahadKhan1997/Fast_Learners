<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <script type = "text/javascript">
    function validate() {
      
      if( document.RegForm.card.value == "" ) {
         alert( "Please provide valid card number!" );
         document.RegForm.card.focus() ;
         return false;
      }
      var roll = new RegExp('/^[0-9]{4,6}$/');
      elseif(roll.equals(document.getElementById("abc").value))
      {
        alert("no alphabets from a-z and A-Z are allowed");
        return false;
      }     
      if( document.RegForm.cardholdername.value == "" ) {
         alert( "Please provide the card holder name" );
         document.RegForm.cardholdername.focus() ;
         return false;
      }
      if( document.RegForm.valid.value == "" ) {
         alert( "Please provide valid expiry date" );
         document.RegForm.valid.focus() ;
         return false;
      }
      return true;
   }
   

  </script>
  <link rel="stylesheet" href="webpage.css">
  <link rel="stylesheet" href="register.css">
<title>Payment</title>
</head>
<%HttpSession sess = request.getSession(false); //use false to use the existing session
sess.getAttribute("username");%>
<body bgcolor="grey">
<div class="container">
    <form name="RegForm" method="post" action="./Pay">
      <div class="row">
        <div class="col-25">
          <label for="name">Book being purchased by you, Mr ${username} :</label>
        </div>
        <div class="col-75">
          <label for="name">${name}</label>
        </div>
      </div>
      <div class="row">
        <div class="col-25">
          <label for="price">Total Amount : </label>
        </div>
        <div class="col-75">
          <label for="price"> ${price} </label>
        </div>
      </div>
      <div class="row">
        <div class="col-25">
          <label for="cardDetails">Card Details</label>
        </div>
        <div class="col-75">
          <input type="text" name="card" placeholder="card no. goes here" required>
          <input type="text" name="cardholdername" placeholder="card holder name" required>
        </div>
      </div>
            <div class="row">
        <div class="col-25">
          <label for="exp">Valid through</label>
        </div>
        <div class="col-75">
          <input type="text" name="valid" required>
        </div>
      </div>
      <div class="row">
        <div class="submit">
          <input type="submit" value="pay" onclick="validate()"/>  
        </div>
        </div>
        </form>
        <div class="login">
          <button class="button"><a href="productList.jsp">cancel ?</a></button>
      </div>
  </div>
</body>
</html>