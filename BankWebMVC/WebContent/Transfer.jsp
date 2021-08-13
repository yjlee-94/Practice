<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>YJ Bank | Transfer</title>
</head>
<body>
	<br><br><br>
	<h1>Enter amount to transfer:</h1> 
	<form action ="Banktransfer">
	Amount to transfer: <input type="number" name="TransferAmt" min="1" max="1000000" id="trfamt"><br>
	Account number of receiver:<input type="number" name="rpaccno" max="999999999" id="rpAccNo"><br>
	<button type="submit" name="transfer">Transfer</button>
	</form>
</body>
</html>