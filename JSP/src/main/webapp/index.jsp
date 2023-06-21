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
	
	<form action="ServletLogin" method="post">
		<input name="login" type="text">
		<input name="senha" type="password">
		
		<input type="submit" value="Enviar">
	</form>
</body>
</html>
