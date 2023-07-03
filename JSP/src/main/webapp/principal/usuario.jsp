<%@page import="model.ModelLogin"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
															<div class="form-group form-default form-static-label">
                                                                <input type="text" name="id" id="id" class="form-control"  readonly="readonly" value="${modelLogin.id}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">ID:</label>
                                                            </div>
															<div class="form-group form-default">
																<input type="text" class="form-control" name="nome"
																	id="nome" autocomplete="none" value="${modelLogin.nome}"> <span
																	class="form-bar"></span> <label class="float-label">Nome</label>														
															</div>
															<div class="form-group form-default">
																<input type="text" class="form-control" name="email" value="${modelLogin.email}"
																	id="email" autocomplete="none"> <span
																	class="form-bar"></span> <label class="float-label">E-mail</label>
															</div>
															<p>${msg_email}</p>
															<div class="form-group form-default">
																<input type="text" class="form-control" name="login" value="${modelLogin.login}"
																	id="login" autocomplete="none"> <span
																	class="form-bar"></span> <label class="float-label">login</label>
															</div>
															<p>${msg_login}</p>
															<div class="form-group form-default">
																<input type="password" name="senha" class="form-control" value="${modelLogin.senha}">
																<span class="form-bar"></span> <label
																	class="float-label">Senha</label>
															</div>
															<button type="submit" onclick="validar()" class="btn btn-primary waves-effect waves-light">Novo</button>
															<button type="submit" onclick="validar()" class="btn btn-primary waves-effect waves-light">Salvar</button>
															<button type="submit" onclick="validar()" class="btn btn-primary waves-effect waves-light">Excluir</button><br>															
														</form>
													</div>
												</div>
											</div>
										</div>
									</div>
									<span>
										<h4>${msg}</h4>																
									</span>
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.validate.js"></script>
</body>
</html>