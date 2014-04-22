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

<form action="processModel" method="post">
	<table border="2">
	<tr>
	<td><h4> Select a model:</h4></td>
	</tr>
	<tr>
		<td><select name="models" id="models">
		<option>${models[0]}</option>
		<option>${models[1]}</option>
		<option>${models[2]}</option>
		<option>${models[3]}</option>
		</select></td> 
	</tr>
	</table>
	<input type="submit" name="submit">
</form>
</center>
</body>
</html>