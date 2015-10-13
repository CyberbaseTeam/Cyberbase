<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" >Nom de section</a>
		</div>
		<div>
			<ul class="nav navbar-nav">
				
				<li><a href="usagers">Liste de tous les usagers</a></li>
				<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Usagers par site <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<c:forEach items="${siteList}" var="site" begin="0" varStatus='i'>
							<li><a href="usagers?site=${site.id_site}">${site.nom_site}</a></li>
						</c:forEach>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</nav>
