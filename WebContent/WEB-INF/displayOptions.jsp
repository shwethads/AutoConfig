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
	<form action="results" method="post">
	<% String modelSet = (String) request.getAttribute("model");
	%>
	<label><%= modelSet %></label>
		<table border=2>
		<tr>
			<td>Model:</td>
			<td>
			<select name="model" id="model">
				<option><%= modelSet %></option>
			</select>
		</tr>
		<tr>
			<td>Colors:</td>
			<td>
				<select name="color" id="color">
				<%String[] colors = (String[])request.getAttribute("colors");
				for(int i=0; i<colors.length; i++) {
				%>
				<option><%= colors[i] %></option>
				<% } %>
				</select>
			</td>
		</tr>
		<tr>
			<td>Transmission:</td>
			<td>
				<select name="trans" id="trans">
				<%String[] trans = (String[])request.getAttribute("transmissions");
				for(int i=0; i<trans.length; i++) {
				%>
				<option><%= trans[i] %></option>
				<% } %>
				</select>
			</td>
		</tr>
		<tr>
			<td>Breaks:</td>
			<td>
				<select name="breaks" id="breaks">
				<%String[] breaks = (String[])request.getAttribute("breaks");
				for(int i=0; i<breaks.length; i++) {
				%>
				<option><%= breaks[i] %></option>
				<% } %>
				</select>
			</td>
		</tr>
		<tr>
			<td>Air Bags:</td>
			<td>
				<select name="ab" id="ab">
				<%String[] ab = (String[])request.getAttribute("airBags");
				for(int i=0; i<ab.length; i++) {
				%>
				<option><%= ab[i] %></option>
				<% } %>
				</select>
			</td>
		</tr>
		<tr>
			<td>Moon Roof:</td>
			<td>
				<select name="mr" id="mr">
				<%String[] mr = (String[])request.getAttribute("moonRoof");
				for(int i=0; i<mr.length; i++) {
				%>
				<option><%= mr[i] %></option>
				<% } %>
				</select>
			</td>
		</tr>
		</table>
		<input type="submit" value="Submit">
	</form>
	</center>
</body>
</html>