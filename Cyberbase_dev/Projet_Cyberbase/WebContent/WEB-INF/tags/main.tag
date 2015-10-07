<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8" />	
	<title>Cyberbase</title>
	<meta name="description" content="" />
	<meta name="keywords" content="" />
	<link  rel="stylesheet" type="text/css" href="<c:url value="inc/css/style.css"/>" >
		
</head>
<body>
	<header>
		<div id="header-top">
			<div id="header-logo">
				<a href=""><img src="/inc/images/logo.jpg" alt="logo" /></a>
			</div>
		</div>
		
		<div id="info_session">
			<p>Nom: </p>
			<p>Prénom: </p>
			<p>Identifiant: </p>		
		</div>
		<c:import url="/inc/modules/menu_principal.jsp" />	
		
	
	</header>
		
      <div id="content">
      	<div id="conteneur-arianne">
			<h1>"Nom de la section"</h1>
	  	</div>
		<jsp:doBody/>
	  </div>
	  	<c:import url="/inc/modules/footer.jsp" />
	<script src="lib/jquery-2.1.4.min.js"></script>
	<script src="lib/jquery.chained.min.js"></script>
	<script src="inc/js/gestion_salles_postes.js"></script>
</body>
</html>