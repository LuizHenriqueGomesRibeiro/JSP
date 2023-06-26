<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Erros</title>
</head>
<body>
	<h1>Mensagem de erro. Entre em contato com a equipe de suporte.</h1>
	<div>
		<p><% out.println(request.getAttribute("msg")); %></p>
	</div>
</body>
</html>