<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1><%out.println("Welcome "+ session.getAttribute("name"));%></h1><br>
<h2><% out.println("Account no: "+session.getAttribute("account_num"));%></h2><br>
<h2><% out.println("Balance: "+session.getAttribute("bankbal"));%></h2><br><br>
<h4><% out.println("System msg: "+ session.getAttribute("update")); %></h4>
<br><br><br><br>

<form action="/BankWebMVC/Deposit.jsp"><button type="submit" name="toDeposit" >     Deposit Into Own Account    </button></form> <br>
<form action="/BankWebMVC/Transfer.jsp"><button type="submit" name="toTransfer">   Transfer Money   </button></form> 
<br><br>
<a href="logout">Logout</a>
</body>
</html>