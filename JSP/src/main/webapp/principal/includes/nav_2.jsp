<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="model.ModelLogin"%>
<nav class="pcoded-navbar">
	<div class="sidebar_toggle">
		<a href="#"><i class="icon-close icons"></i></a>
	</div>
	<div class="pcoded-inner-navbar main-menu">
		<div class="p-15 p-b-0">
			<form class="form-material">
				<div class="form-group form-primary">
					<input type="text" name="footer-email" class="form-control"
						required=""> <span class="form-bar"></span> <label
						class="float-label"><i class="fa fa-search m-r-10"></i>Search
						Friend</label>
				</div>
			</form>
		</div>
		<div class="pcoded-navigation-label"
			data-i18n="nav.category.navigation">Layout</div>
		<ul class="pcoded-item pcoded-left-item">
			<li class="active"><a href="<%=request.getContextPath()%>/ServletUsuarioController?acao=voltar"
				class="waves-effect waves-dark"> <span class="pcoded-micon"><i
						class="ti-home"></i><b>D</b></span> <span class="pcoded-mtext"
					data-i18n="nav.dash.main">Menu principal</span> <span
					class="pcoded-mcaret"></span>
			</a></li>
			<li class="pcoded-hasmenu"><a href="javascript:void(0)"
				class="waves-effect waves-dark"> <span class="pcoded-micon"><i
						class="ti-layout-grid2-alt"></i></span> <span class="pcoded-mtext"
					data-i18n="nav.basic-components.main">Components</span> <span
					class="pcoded-mcaret"></span>
			</a>
				<ul class="pcoded-submenu">
					<c:if test="${perfil == 'ADMIN'}">
						<li class=" ">
							<a href="<%=request.getContextPath()%>/ServletUsuarioController?acao=listarUsuario" class="waves-effect waves-dark"> 
								<span class="pcoded-micon"><i class="ti-angle-right"></i></span> 
								<span class="pcoded-mtext" data-i18n="nav.basic-components.alert">Usuário</span> 
								<span class="pcoded-mcaret"></span>
							</a>
						</li>
					</c:if>
					<li>
						<a href="<%=request.getContextPath()%>/principal/relatorio.jsp"
						class="waves-effect waves-dark"> 
							<span class="pcoded-micon">
								<i class="ti-layers"></i>
								<b>FC</b>
							</span> 
							<span class="pcoded-mtext" data-i18n="nav.form-components.main">Relatório</span> 
							<span class="pcoded-mcaret"></span>
						</a>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/principal/graficoJS.jsp"
						class="waves-effect waves-dark"> 
							<span class="pcoded-micon">
								<i class="ti-layers"></i>
								<b>FC</b>
							</span> 
							<span class="pcoded-mtext" data-i18n="nav.form-components.main">Gráfico</span> 
							<span class="pcoded-mcaret"></span>
						</a>
					</li>
				</ul>
			</li>
		</ul>
		<div class="pcoded-navigation-label" data-i18n="nav.category.other">Outros</div>
		<ul class="pcoded-item pcoded-left-item">
			<li class="pcoded-hasmenu "><a href="javascript:void(0)"
				class="waves-effect waves-dark"> <span class="pcoded-micon"><i
						class="ti-direction-alt"></i><b>M</b></span> <span class="pcoded-mtext"
					data-i18n="nav.menu-levels.main">Créditos</span> <span
					class="pcoded-mcaret"></span>
			</a>
				<ul class="pcoded-submenu">
					<li class=""><a href="javascript:void(0)"
						class="waves-effect waves-dark"> <span class="pcoded-micon"><i
								class="ti-angle-right"></i></span> <span class="pcoded-mtext"
							data-i18n="nav.menu-levels.menu-level-21">Menu Level 2.1</span> <span
							class="pcoded-mcaret"></span>
					</a></li>
					<li class="pcoded-hasmenu "><a href="javascript:void(0)"
						class="waves-effect waves-dark"> <span class="pcoded-micon"><i
								class="ti-direction-alt"></i></span> <span class="pcoded-mtext"
							data-i18n="nav.menu-levels.menu-level-22.main">Menu Level
								2.2</span> <span class="pcoded-mcaret"></span>
					</a>
						<ul class="pcoded-submenu">
							<li class=""><a href="javascript:void(0)"
								class="waves-effect waves-dark"> <span class="pcoded-micon"><i
										class="ti-angle-right"></i></span> <span class="pcoded-mtext"
									data-i18n="nav.menu-levels.menu-level-22.menu-level-31">Menu
										Level 3.1</span> <span class="pcoded-mcaret"></span>
							</a></li>
						</ul></li>
					<li class=""><a href="javascript:void(0)"
						class="waves-effect waves-dark"> <span class="pcoded-micon"><i
								class="ti-angle-right"></i></span> <span class="pcoded-mtext"
							data-i18n="nav.menu-levels.menu-level-23">Menu Level 2.3</span> <span
							class="pcoded-mcaret"></span>
					</a></li>

				</ul></li>
		</ul>
	</div>
</nav>