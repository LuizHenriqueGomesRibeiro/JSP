<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="includes/head.jsp"></jsp:include>
<jsp:include page="includes/theme-loader.jsp"></jsp:include>
<jsp:include page="includes/javascript.jsp"></jsp:include>
<body>
	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">
			<jsp:include page="includes/nav.jsp"></jsp:include>
			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">
					<jsp:include page="includes/nav_2.jsp"></jsp:include>
					<div class="pcoded-content">		
						<div class="pcoded-inner-content">
							<div class="main-body">
								<div class="page-wrapper">
									<div class="page-body">
										<div class="row">
											<div class="col-sm-12">
												<div class="card">
													<div class="card-header">
														<h5>Página de relatório</h5>
													</div>
													<form 
														class="form-material" name="frmContato" id="formulario"
														action="<%=request.getContextPath()%>/ServletUsuarioController"
														method="get">
														
														<input type="hidden" name="acao" id="acaoRelatorioImprimirTipo" value="printForm">
														
														<div style="margin: 20px;">
														<div class="form-row align-items-center">
															<div class="col-auto">
																<label class="sr-only" for="dataInicial">Data inicial</label>
																<input type="date" class="form-control mb-2" value="${dataInicial}"
																id="dataInicial" placeholder="Data incial" name="dataInicial">
															</div>
															
															<div class="col-auto">
																<label class="sr-only" for="dataFinal">Data final</label>
																<div class="input-group mb-2">
																	<input type="date" class="form-control" value="${dataFinal}"
																	id="dataFinal" placeholder="Data final" name="dataFinal">
																</div>
															</div>
															<div class="col-auto">
																<button type="button" onclick="imprimirHTML();" 
																class="btn btn-primary mb-2">Gerar gráfico</button>
																<button type="button" onclick="gerarGrafico();" 
																class="btn btn-primary mb-2">Imprimir PDF</button>
															</div>
														</div>
														</div>
													</form>
													<c:if test="${modelLogins != null}">
														<div style="overflow-y: scroll; overflow-x: hidden; height: 300px;">
														
														</div>
													</c:if>
													<div class="card-block"></div>
												</div>
											</div>
										</div>
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
	<script type="text/javascript">
	
		function gerarGrafico(){
			
		}	
	
	</script>
</body>
</html>