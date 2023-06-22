<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Index do site</title>
<script type="text/javascript" src="scripts/jquery-3.7.0.js"></script>
<script type="text/javascript" src="scripts/jquery.validate.js"></script>
<script type="text/javascript" src="scripts/validacao.js"></script>
</head>
<body>
	<p>Olá mundo</p>
	
	<form action="ServletLogin" method="post" name="frmContato" id="formulario">
		<input type="hidden" value="<%= request.getParameter("url") %>" name="url">
		<p>Login:<input name="login" type="text"></p>
		<p>Senha:<input name="senha" type="password"></p>
		
		<input type="submit" value="Enviar" onclick="validar()">
	</form>
	<h4>${msg}</h4>
</body>
</html>
