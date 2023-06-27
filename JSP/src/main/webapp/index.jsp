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
<link rel="stylesheet" href="estilos/index.css">
</head>
<body>
	<main>
		<div>
			<form action="ServletLogin" method="post" name="frmContato" id="formulario">
				
				<input type="hidden" value="<%=request.getParameter("url")%>" name="url">

				<p>Login:</p>
				<input name="login" type="text" required>
				
				<p>Senha:</p>
				<input name="senha" type="password" required><br>

				<input class="enviar" type="submit" value="Continuar" onclick="validar()">
			</form>
			<h4>${msg}</h4>
		</div>
	</main>
</body>
</html>
