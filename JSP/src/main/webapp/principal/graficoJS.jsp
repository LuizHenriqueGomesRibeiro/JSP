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
														<h5>Página de gráfico</h5>
													</div>
													<form 
														class="form-material" name="frmContato" id="formulario"
														action="<%=request.getContextPath()%>/ServletUsuarioController"
														method="get">
														
														<input type="hidden" name="acao" id="acaoRelatorioImprimirTipo" value="printForm">
														
														<div style="margin: 20px;">
														<div class="form-row align-items-center">
															<div class="col-auto">
																<input type="hidden" class="form-control mb-2" value="${dataInicial}"
																id="dataInicial" placeholder="Data incial" name="dataInicial">
															</div>
															
															<div class="col-auto">
																<div class="input-group mb-2">
																	<input type="hidden" class="form-control" value="${dataFinal}"
																	id="dataFinal" placeholder="Data final" name="dataFinal">
																</div>
															</div>
															<div class="col-auto">
																<button type="button" onclick="gerarGrafico();" 
																class="btn btn-primary mb-2">Gerar gráfico</button>
															</div>
														</div>
														</div>
													</form>
													<div>
														<canvas id="myChart"></canvas>
													</div>
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
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	<script type="text/javascript">
	
		function gerarGrafico() {
			
			var urlAction = document.getElementById('formulario').action;
			var dataInicial = document.getElementById('dataInicial').value;
			var dataFinal = document.getElementById('dataFinal').value;
			
			jQuery.ajax({

				method: "get",
				url: urlAction,
				data: "dataInicial=" + dataInicial + '&dataFinal=' + dataFinal + '&acao=graficoSalario',
				success: function(json, textStatus, xhr) {
				
				const ctx = document.getElementById('myChart');

							new Chart(ctx, {
								type : 'bar',
								data : {
									labels : json.perfis,
									datasets : [ {
										label : '# of Votes',
										data : json.salarios,
										borderWidth : 1
									} ]
								},
								options : {
									scales : {
										y : {
											beginAtZero : true
										}
									}
								}
							});
						}
					}).fail(function(xhr, status, errorThrown) {
				alert('Erro ao buscar dados de gráfico: ' + xhr.responseText);
			});

		}
	</script>
</body>
</html>