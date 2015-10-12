<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset="utf-8" />
<title>Cyberbase</title>
<meta name="description" content="" />
<meta name="keywords" content="" />
<link rel="stylesheet" href="lib/bootstrap.min.css">
<link rel="stylesheet" href="lib/bootstrap-theme.min.css">
<link rel="stylesheet" href="inc/css/style2.css">

</head>
<body>
	<header>
	<div class="row">
		<div id="header-top" class="col-md-7">
			<div id="header-logo">
				<a href="accueil"><img src="inc/images/logo.jpg" alt="logo" /></a>
			</div>
		</div>
		<div class="col-md-2"></div>
		<div class="col-md-3">
			<div id="info_session" class="" col-md-6>
				<p>
					<span>Nom: </span>Prénom:<span></span><span>Identifiant:</span>
				</p>
			</div>
		</div>
		<c:import url="/inc/modules/menu_principal.jsp" />
	</header>

	<div id="content">
		<div id="section_name">
			<p id="section-name">Nom de la section</p>
		</div>
		<jsp:doBody />

		<c:import url="/inc/modules/footer.jsp" />
		<script src="lib/jquery-2.1.4.min.js"></script>
		<script src="lib/jquery.chained.min.js"></script>
		<script src="inc/js/gestion_salles_postes.js"></script>
</body>
</html>