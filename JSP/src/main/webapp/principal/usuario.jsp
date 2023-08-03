<%@page import="model.ModelLogin"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<jsp:include page="includes/head.jsp"></jsp:include>
<jsp:include page="includes/theme-loader.jsp"></jsp:include>
<jsp:include page="includes/javascript.jsp"></jsp:include>
<body>
	<!-- Pre-loader end -->
	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">
			<jsp:include page="includes/nav.jsp"></jsp:include>
			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">
					<jsp:include page="includes/nav_2.jsp"></jsp:include>
					<div class="pcoded-content">
						<!-- Page-header start -->
						<jsp:include page="includes/page-header.jsp"></jsp:include>
						<!-- Page-header end -->
						<div class="pcoded-inner-content">
							<!-- Main-body start -->
							<div class="main-body">
								<div class="page-wrapper">
									<!-- Page-body start -->
									<div class="page-body">
										<div class="caixa">
											<h1>Página do usuário</h1>
										</div>
										<div class="row">
											<div class="col-sm-12">
												<div class="card">
													<div class="card-header">
														<h5>Inputs básicos de cadastro</h5>
													</div>
													<div class="card-block">
														<h4 class="sub-title">Preencha as suas informações:</h4>
														<form class="form-material"	
															id="formulario_usuario" enctype="multipart/form-data"
															action="<%=request.getContextPath()%>/ServletUsuarioController"
															method="post">

															<input type="hidden" name="acao" id="acao" value="">

															<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id"
																	class="form-control" readonly="readonly"
																	value="${modelLogin.id}"> <span class="form-bar"></span> <label class="float-label">ID:</label>
															</div>

															<div class="form-group form-default input-group mb-4"
																style="height: 80px;">
																<div class="input-group-prepend">
																	<c:if test="${modelLogin.fotoUser != '' && modelLogin.fotoUser != null}">
																		<a
																			href="<%= request.getContextPath()%>/ServletUsuarioController?acao=download&id=${modelLogin.id}">
																			<img alt="Imagem User" id="fotoembase64"
																			src="${modelLogin.fotoUser}" height="70px">
																		</a>
																	</c:if>
																	<c:if
																		test="${modelLogin.fotoUser == '' || modelLogin.fotoUser == null}">
																		<img alt="Imagem User" id="fotoembase64"
																			src="<%=request.getContextPath() %>/assets/images/1077114.png" height="70px">
																	</c:if>
																</div>
																<input style="margin-top: 20px; margin-left: 10px;"
																	type="file" id="fileFoto" name="fileFoto"
																	accept="image/*"
																	onchange="visualizarImg('fotoembase64','fileFoto');"
																	class="form-control-file">
															</div>
															<div class="form-group form-default">
																<input type="text" class="form-control" name="nome"
																	id="nome" autocomplete="none"
																	value="${modelLogin.nome}"> <span
																	class="form-bar"></span> <label class="float-label">Nome</label>
															</div>

															<div class="form-group form-default">
																<input type="text" class="form-control" name="email"
																	value="${modelLogin.email}" id="email"
																	autocomplete="none"> <span class="form-bar"></span>
																<label class="float-label">E-mail</label>
															</div>
															<p>${msg_email}</p>
															<div class="form-group form-default form-static-label">
																<select class="form-control" multiple
																	aria-label="multiple select example" name="perfil"
																	style="height: 65px; overflow: hidden;">

																	<option value="ADMIN"
																		<%ModelLogin modelLogin = (ModelLogin) request.getAttribute("modelLogin");

																	if (modelLogin != null && modelLogin.getPerfil().equals("ADMIN")) {

																		out.println("selected=\"selected\"");

																	}%>>Admin</option>

																	<option value="SECRETARIA"
																		<%modelLogin = (ModelLogin) request.getAttribute("modelLogin");

																	if (modelLogin != null && modelLogin.getPerfil().equals("SECRETARIA")) {

																		out.println("selected=\"selected\"");

																	}%>>Secretaria</option>

																	<option value="AUXILIAR"
																		<%modelLogin = (ModelLogin) request.getAttribute("modelLogin");

																	if (modelLogin != null && modelLogin.getPerfil().equals("AUXILIAR")) {

																		out.println("selected=\"selected\"");

																	}%>>Auxiliar</option>

																</select> <span class="form-bar"></span> <label
																	class="float-label">Perfil</label>
															</div>
															<div class="form-control form-group form-default"
																style="margin-top: 5px;">
																<input type="radio" name="sexo" value="MASCULINO"
																	checked="checked"
																	<%modelLogin = (ModelLogin) request.getAttribute("modelLogin");

																	if (modelLogin != null && modelLogin.getSexo().equals("MASCULINO")) {
																		out.println("checked=\"checked\"");
																	}%>>Masculino</>
																<input type="radio" name="sexo" value="FEMININO"
																	<%modelLogin = (ModelLogin) request.getAttribute("modelLogin");

																	if (modelLogin != null && modelLogin.getSexo().equals("FEMININO")) {
																		out.println("checked=\"checked\"");
																	}%>>Feminino</>
															</div>
															<div class="form-group form-default form-static-label">
																<input onblur="pesquisarCEP();" type="text" name="cep"
																	id="cep" class="form-control" required="required"
																	autocomplete="off" placeholder=""
																	value="<%out.print(request.getAttribute("cep"));%>">
																<span class="form-bar"></span> <label
																	class="float-label">cep</label>
															</div>
															<div class="form-group form-default">
																<input type="text" class="form-control" name="rua"
																	value="${modelLogin.rua}" id="rua" autocomplete="none">
																<span class="form-bar"></span> <label
																	class="float-label">rua</label>
															</div>
															<div class="form-group form-default">
																<input type="text" class="form-control" name="bairro"
																	value="${modelLogin.bairro}" id="bairro"
																	autocomplete="none"> <span class="form-bar"></span>
																<label class="float-label">bairro</label>
															</div>
															<div class="form-group form-default">
																<input type="text" class="form-control"
																	name="localidade" value="${modelLogin.localidade}"
																	id="localidade" autocomplete="none"> <span
																	class="form-bar"></span> <label class="float-label">localidade</label>
															</div>
															<div class="form-group form-default">
																<input type="text" class="form-control" name="uf"
																	value="${modelLogin.uf}" id="uf" autocomplete="none">
																<span class="form-bar"></span> <label
																	class="float-label">uf</label>
															</div>
															<div class="form-group form-default">
																<input type="text" class="form-control" name="numero"
																	value="${modelLogin.numero}" id="numero"
																	autocomplete="none"> <span class="form-bar"></span>
																<label class="float-label">número</label>
															</div>
															<div class="form-group form-default">
																<input type="text" class="form-control" name="login"
																	value="${modelLogin.login}" id="login"
																	autocomplete="none"> <span class="form-bar"></span>
																<label class="float-label">login</label>
															</div>
															<p>${msg_login}</p>
															<div class="form-group form-default">
																<input type="password" name="senha" class="form-control"
																	value="${modelLogin.senha}" id="senha"> <span
																	class="form-bar"></span> <label class="float-label">Senha</label>
															</div>
															<div class="confirmacao">
																<div class="card-header">
																	<h4>Você deseja mesmo excluir este cadastro?</h4>
																</div>
																<button type="button"
																	class="btn btn-primary waves-effect waves-light"
																	id="deletar">Excluir</button>
																<button type="button"
																	class="btn btn-primary waves-effect waves-light"
																	id="cancelar">Cancelar</button>
															</div>
															<button type="button"
																class="btn btn-primary waves-effect waves-light"
																id="limpar">Novo</button>
															<button type="submit" class="btn btn-primary waves-effect waves-light">Salvar</button>
															<c:if test="${modelLogin.id > 0}">
																<button type="button"
																	style="background-color: red; border: 1px solid red;"
																	class="btn btn-primary waves-effect waves-light"
																	id="caixa">Excluir</button>
															</c:if>
															<c:if test="${modelLogin.id > 0}">
																<a id="telefoneEsconder" href="<%= request.getContextPath() %>/serverTelefone?iduser=${modelLogin.id}&acao=listarTelefones" class="btn btn-primary waves-effect waves-light">Telefone</a>
															</c:if>
															<button type="button" class="btn btn-success"
																data-toggle="modal" data-target="#exampleModal">
																Pesquisar</button>
														</form>
													</div>
												</div>
											</div>
										</div>
									</div>
									<span>
										<h4 style="margin-bottom: 28px;">${msg}</h4>
									</span>
									<div class="card">
										<div class="card-header">
											<h5>Lista de usuários cadastrados</h5>
										</div>
										<div>
											<table class="table" id="tabelaResultados">
												<thead>
													<tr>
														<th style="width: 27%; left: 40px; position: relative;">ID</th>
														<th style="width: 27%;">Nome</th>
														<th style="width: 30%;">Login</th>
														<th style="width: 25%;">Selecionar</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items='${modelLogins}' var='ml'>
														<tr>
															<td style="left: 40px; top: 11px; position: relative;"><c:out
																	value="${ml.id}"></c:out></td>
															<td style="top: 11px; position: relative;"><c:out
																	value="${ml.nome}"></c:out></td>
															<td style="top: 11px; position: relative;"><c:out
																	value="${ml.login}"></c:out></td>
															<td><a class="btn btn-success"
																href="<%=request.getContextPath()%>/ServletUsuarioController?acao=buscar&id=${ml.id}">Ver</a></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
										<nav aria-label="Page navigation example" 
											style="margin: auto; position: relative;">
											<ul class="pagination">
												<%
												int totalPagina = (int) request.getAttribute("totalPagina");
												for (int p = 0; p < totalPagina; p++) {
													String url = request.getContextPath() + "/ServletUsuarioController?acao=paginar&pagina=" + (p * 5);
													out.print("<li class=\"page-item\"><a class=\"page-link\" href=\"" + url + "\">" + (p + 1) + "</a></li>");
												}
												%>
											</ul>
										</nav>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#exampleModal">Pesquisar</button>
									</div>
								</div>
								<div id="styleSelector"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="exampleModal" tabindex="1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Pesquisar</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="input-group mb-3">
						<input type="text" class="form-control" id="nomeBusca">
						<div class="input-group-append">
							<button class="btn btn-success" type="button" id="buscar">Buscar</button>
							<button class="btn btn-danger" type="button" id="limpar_pesquisa">Limpar</button>
						</div>
					</div>
					<div class="caixa_de_dialogo">
						<h7>Digite algum nome</h7>
					</div>
				</div>
				<table class="table" id="tabelaresultados">
					<thead>
						<tr>
							<th>email</th>
							<th>ID</th>
							<th>Nome</th>
							<th>Selecionar</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
					<button type="button" class="btn btn-primary">Salvar mudanças</button>
				</div>
				<nav aria-label="Page navigation example"
					style="margin: auto; position: relative;">
					<ul class="pagination" id="ulPaginacaoUserAjax">
						
					</ul>
				</nav>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/buscar.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/limpar.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.validate.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/editar.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/imagem.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/pesquisarCEP.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/buscaPagAjax.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/scripts/validacao.js"></script>
</body>
</html>