<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>YJ Bank | Deposit</title>
</head>
<body>
	<br><br><br>
	<label for="depamt"> Enter amount to deposit: </label> 
	<form action ="Bankdeposit">
	<input type="number" name="depositAmt" min="1" max="1000000" id="depamt">
	<button type="submit" name="deposit">Confirm Deposit</button>
	</form>
</body>
</html>