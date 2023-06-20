<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Página 'receber'</title>
</head>
<body>
	<%
		String nome = request.getParameter("nome");
		out.print(nome);
		out.println();
		String numero = request.getParameter("numero");
		out.print(numero);
	%>
</body>
</html>