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
														<form class="form-material" name="frmContato"
															id="formulario"
															action="<%=request.getContextPath()%>/ServletUsuarioController"
															method="post">

															<input type="hidden" name="acao" id="acao" value="">

															<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id"
																	class="form-control" readonly="readonly"
																	value="${modelLogin.id}"> <span
																	class="form-bar"></span> <label class="float-label">ID:</label>
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
															<button type="submit"
																class="btn btn-primary waves-effect waves-light">Salvar</button>
															<button type="button"
																style="background-color: red; border: 1px solid red;"
																class="btn btn-primary waves-effect waves-light"
																id="caixa">Excluir</button>
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
										<h4>${msg}</h4>
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
															<td style="left: 40px; top: 11px; position: relative;"><c:out value="${ml.id}"></c:out></td>
															<td style="top: 11px; position: relative;"><c:out value="${ml.nome}"></c:out></td>
															<td style="top: 11px; position: relative;"><c:out value="${ml.login}"></c:out></td>
															<td><a class="btn btn-success" href="<%=request.getContextPath()%>/ServletUsuarioController?acao=buscar&id=${ml.id}">Ver</a></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
									<!-- Page-body end -->
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
						<input type="text" class="form-control" id='nomeBusca'>
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
							<th>ID</th>
							<th>Nome</th>
							<th>Selecionar</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Fechar</button>
					<button type="button" class="btn btn-primary">Salvar
						mudanças</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/scripts/buscar.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/scripts/limpar.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/scripts/jquery.validate.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/scripts/editar.js"></script>
</body>
</html>