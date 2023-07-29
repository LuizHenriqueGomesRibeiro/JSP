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
											<h1 style="margin-bottom: 35px;">Página de telefones</h1>
										</div>
										<div class="row">
											<div class="col-sm-12">
												<div class="card">
													<div class="card-header">
														<h5>Inputs básicos de cadastro</h5>
													</div>
													<div class="card-block">
														<h4 class="sub-title">Preencha as suas informações:</h4>
														<form class="form-material" id="formulario"
															action="<%=request.getContextPath()%>/servletTelefone"
															method="post">

															<input type="hidden" name="acao" id="acao" value="">

															<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id"
																	class="form-control" readonly="readonly"
																	value="${usuario.id}"> <span
																	class="form-bar"></span> <label class="float-label">ID:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" class="form-control" name="nome"
																	id="nome" autocomplete="none"
																	value="${usuario.nome}" readonly="readonly"><span
																	class="form-bar"></span> <label class="float-label">Nome</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" class="form-control" name="telefone"
																	id="telefone" autocomplete="none"><span
																	class="form-bar"></span> <label class="float-label">telefone</label>
															</div>
															<button type="submit" class="btn btn-primary waves-effect waves-light">Salvar</button>
														</form>
														<span>
															<h4 style="margin-top: 25px;">${msg_sucesso}</h4>
														</span>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
<<<<<<< master
<<<<<<< master
<<<<<<< master
								<div class="card">
									<div class="card-header">
										<h5>Lista de telefones cadastrados para ${usuario.nome}</h5>
									</div>
									<div>
										<table class="table" id="tabelaResultados">
											<thead>
												<tr>
													<th style="width: 70%; left: 40px; position: relative;">id</th>
													<th style="width: 50%; position: relative;">Numero</th>
													<th style="width: 25%; position: relative;">Selecionar</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items='${telefones}' var='f'>
													<tr>
														<td style="left: 40px; top: 11px;"><c:out
																value="${f.id}"></c:out></td>
														<td style="top: 11px;"><c:out
																value="${f.numero}"></c:out></td>
														<td><a class="btn btn-success"
															href="<%= request.getContextPath() %>/servletTelefone?acao=excluir&id=${f.id}&userpai=${usuario.id}">Excluir</a></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
									<button type="button" class="btn btn-success"
										data-toggle="modal" data-target="#exampleModal">Pesquisar</button>
								</div>
							</div>
							<div id="styleSelector"></div>
=======
								<div id="styleSelector"></div>
>>>>>>> 3109e73 comitar criação de tabela de telefones para cada usuário
=======
								<div id="styleSelector"></div>
>>>>>>> 3109e73 comitar criação de tabela de telefones para cada usuário
=======
								<div id="styleSelector"></div>
>>>>>>> 3109e73 comitar criação de tabela de telefones para cada usuário
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<<<<<<< master
<<<<<<< master
<<<<<<< master
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/buscar.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/limpar.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.validate.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/editar.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/imagem.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/pesquisarCEP.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/buscaPagAjax.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/scripts/validacao.js"></script>
=======
=======
>>>>>>> 3109e73 comitar criação de tabela de telefones para cada usuário
=======
>>>>>>> 3109e73 comitar criação de tabela de telefones para cada usuário
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
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/scripts/buscar.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/scripts/limpar.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/scripts/jquery.validate.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/scripts/editar.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/scripts/imagem.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/scripts/pesquisarCEP.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/scripts/buscaPagAjax.js"></script>
>>>>>>> 3109e73 comitar criação de tabela de telefones para cada usuário
</body>
</html>