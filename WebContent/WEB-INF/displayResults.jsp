<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Insert title here</title>
</head>
<body>
<center>
<form>
	<table border=2>
	<tr>
		<td>Property</td>
		<td>Value</td>
		<td>Cost</td>
	</tr>
	<tr>
		<td>Color</td>
		<td>${color}</td>
		<td>${colorPrice}</td>
	</tr>
	<tr>
		<td>Transmission</td>
		<td>${transmission}</td>
		<td>${transCost}</td>
	</tr>
	<tr>
		<td>Breaks</td>
		<td>${breaks}</td>
		<td>${breaksCost}</td>
	</tr>	
	<tr>
		<td>Air Bags</td>
		<td>${airBags }</td>
		<td>${abCost }</td>
	</tr>
	<tr>
		<td>Power Moonroof</td>
		<td>${moonRoof }</td>
		<td>${mrCost }</td>
	</tr>
	<tr>
		<td>Total Cost:</td>
		<td></td>
		<td>${total }</td>
	</tr>
	</table>
</form>
</center>
</body>
</html>