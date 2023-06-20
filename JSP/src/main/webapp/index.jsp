<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Index do site</title>
</head>
<body>
	<p>Olá mundo</p>
	<p>Eu gosto de feijão</p>
	<% out.println("E arroz."); %>
	
	<form action="receber.jsp">
		<input name="nome">
		<input name="numero">
	</form>
</body>
</html>