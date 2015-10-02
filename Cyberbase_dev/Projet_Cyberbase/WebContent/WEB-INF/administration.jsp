
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title>Cyberbase</title>
<meta name='description' content="" />
<meta name='keywords' content='' />

<link href='inc/css/style.css' rel='stylesheet' type='text/css'
	media='screen' />

</head>
<body>
	<header>
	<div id='header-top'>
		<div id='header-logo'>
			<a href='index.php'><img src='images/logo.jpg' alt='logo' /></a>
		</div>
	</div>

	<div id="info_session">
		<p>Nom:</p>
		<p>Prénom:</p>
		<p>Identifiant:</p>
	</div>


	<nav id="menu-principal">

	<ul id="menu-horizontal">
		<li><a href="">Accueil</a></li>
		<li><a href="">Usagers</a></li>
		<li><a href="">Salles et postes</a></li>
		<li><a href="">Statistiques</a></li>
		<li><a href="administration">Administration</a></li>
		<li class="deconnexion"><a href="">Déconnexion</a></li>
	</ul>
	</nav>
	<div class="arianne">
		<p>OUTILS D'ADMINISTRATION</p>
	</div>
	</header>
	<div id="containt1">
		<div id="side-menu">
			<nav>
			<p>Gérer professionnels</p>
			<ul>
				<li><a href="ajout_professionnel">Créer professionnel</a></li>
				<li><a href="administration">Liste des professionnels</a></li>
			</ul>
			<c:forEach items="${sites}" var="site">
				<form method="get">
					<input type="hidden" value="${site.id_site}" name="inputIdSite" />

					<input type="submit" name="site" value="${site.nom_site}" />
				</form>
			</c:forEach> 
			</nav>

		</div>
		<div id="menu-admin">
			<c:forEach items="${professionnels}" var="professionnel">
				<section> <article id="pro1">
				${professionnel.nom_professionnel}
				${professionnel.prenom_professionnel}
				<p>
				Identifiant : ${professionnel.tech_id}
				<p>
				Site : ${professionnel.site_reference.nom_site}
				<p>
				Structure : ${professionnel.structure.nom_structure}

				<form method="post">
					<input type="hidden" value="${professionnel.id_professionnel}"
						name="inputId" /> <input id="modifier" type="submit"
						name="actionModifier" value="Modifier" /> <input type="submit"
						value="Supprimer" name="actionSupprimer">
				</form>
				</article> </section>
			</c:forEach>
		</div>
		<section> <article id="exporter"> <input
			type="submit" name="Exporter" value="Exporter" /> </article> </section>
	</div>
</body>
</html>
