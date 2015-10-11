<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="lib/bootstrap.min.css">
<link rel="stylesheet" href="lib/bootstrap-theme.min.css">
<link rel="stylesheet" href="inc/css/style2.css">
</head>
<div class="row" id="banniere">
	<div class="col-md-12">
		<img src="inc/images/logo.jpg">
		<h1>Bienvenue sur la plateforme Cyber-base</h1>
	</div>
</div>
<div id="content">
	<form id="connexion" method="POST">
		<fieldset id="fieldset1">
			<div class="row">
				<div class="col-md-5"></div>
				<div class="col-md-2">
					<h2>Page de connexion</h2>
				</div>
				<div class="col-md-5"></div>
			</div>
			<div class="row">
				<div class="col-md-5"></div>
				<div class="col-md-2">
					<label class="labelconnexion" for="techId">Identifiant : </label>
					<input type="text" name="techId" size="21"/><span class="error_msg"></span>
				</div>
				<div class="col-md-5"></div>
			</div>
			<div class="row">
				<div class="col-md-5"></div>
				<div class="col-md-2">
					<label class="labelconnexion" for="pwd">Mot de passe :</label> <input
						class="inputconnexion" type="password" name="pwd" size="18"/> <span
						class="error_msg"></span>
				</div>
				<div class="col-md-5"></div>
			</div>
			<div class="row">
				<div class="col-md-5"></div>
				<div class="col-md-2">
					<input class="btn btn-primary btn-lg btn-block" type="submit"
						value="Connexion" />
				</div>
				<div class="col-md-5"></div>
			</div>
		</fieldset>
		<c:import url="/inc/modules/footer.jsp" />
	</form>


</div>
</html>