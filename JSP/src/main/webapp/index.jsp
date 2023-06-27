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
<script type="text/javascript" src="scripts/validacaoBoot.js"></script>
<link rel="stylesheet" href="estilos/index.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
</head>
<body>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>

	<main>
		<div>
			<form action="ServletLogin" method="post" name="frmContato"
				id="formulario" class="row g-3 needs-validation" novalidate>
				
				<input type="hidden" value="<%=request.getParameter("url")%>"
					name="url">

				<p>Login:</p>
				<input class="text" name="login" type="text" required="required">
				
				<p>Senha:</p>
				<input class="text" name="senha" type="password" required="required"><br>

				<input class="enviar" type="submit" value="Continuar"
					onclick="validar()">
			</form>
			<h4>${msg}</h4>
		</div>
	</main>
</body>
</html>
