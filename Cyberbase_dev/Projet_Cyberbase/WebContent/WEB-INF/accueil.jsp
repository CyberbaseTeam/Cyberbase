<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="lib/bootstrap.min.css">
<link rel="stylesheet" href="lib/bootstrap-theme.min.css">
<link rel="stylesheet" href="inc/css/style2.css">
</head>
<body>
	<div class="row" id="banniere">
		<div class="col-md-12">
			<img src="inc/images/logo.jpg">
			<h1>Bienvenue sur la plateforme Cyber-base</h1>
		</div>
	</div>
	<div class="row" id="contenu">
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-3">
				<h2>Visualiser et gérer l'ensemble des usagers :</h2>
			</div>
			<div class="col-md-3">
				<a href="usagers" role="button" class="btn btn-primary btn-lg btn-block">Gestion des usagers</a>
			</div>
			<div class="col-md-3"></div>
		</div>
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-3">
				<h2>Visualiser et gérer les salles et les postes :</h2>
			</div>
			<div class="col-md-3">
				<a  href="gestion_salles_postes" role="button" class="btn btn-primary btn-lg btn-block">Gestion des salles</a>
			</div>
			<div class="col-md-3"></div>
		</div>
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-3">
				<h2>Statistiques temps réel et requêtes personnalisées   :</h2>
			</div>
			<div class="col-md-3">
				<a href="statistiques" role="button" class="btn btn-primary btn-lg btn-block">Outils statistiques</a>
			</div>
			<div class="col-md-3"></div>
		</div>
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-3">
				<h2>Administration du site et professionnels :</h2>
			</div>
			<div class="col-md-3">
				<a href="administration" role="button" class="btn btn-primary btn-lg btn-block">Administration</a>
			</div>
			<div class="col-md-3"></div>
		</div>
	</div>

	<c:import url="/inc/modules/footer.jsp" />
</body>
</html>