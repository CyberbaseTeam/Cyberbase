<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />	
	<title>Cyberbase</title>
	<meta name='description' content="" />
	<meta name='keywords' content='' />
	
	<link href='inc/css/style.css' rel='stylesheet' type='text/css' media='screen' />
		
</head>
<body>
	<header>
		<div id='header-top'>
			<div id='header-logo'>
				<a href='index.php'><img src='images/logo.jpg' alt='logo' /></a>
			</div>
		</div>
		
		<div id="info_session">
			<p>Nom: </p>
			<p>PrÃ©nom: </p>
			<p>Identifiant: </p>		
		</div>
				
		
		<nav id="menu-principal">
			
				<ul id="menu-horizontal">
					<li><a href="">Accueil</a></li>
					<li><a href="">Usagers</a></li>
					<li><a href="">Salles et postes</a></li>
					<li><a href="">Statistiques</a></li>
					<li><a href="administration">Administration</a></li>
					<li class="deconnexion"><a href="" >DÃ©connexion</a></li>
				</ul>
				
		</nav>
		<div class="arianne">
			<p>OUTILS D'ADMINISTRATION</p>
		</div>
	</header>
	<div id="containt1">
		<div id="side-menu">
			<nav>
				<p>GÃ©rer professionnels</p>
				<ul>					
					<li><a href="ajout_professionnel">CrÃ©er professionnel</a></li>
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
			<div id="admin-modif-crea"> 
			<section>
					<div id="titre-modif_admin">Modifier utilisateur</div>	
					
						<c:forEach items="${professionnels}" var="professionnel">
								
						<form method="post">
							<div id="formulaire">
							
								<div id="idform">
									Nom :
								</div>
								<div id="valeurform">
									<input type="text" name="inputNom" value="${professionnel.nom_professionnel}"  size="40"/>
								</div>
								<div id="idform">
									PrÃ©nom :
								</div>
								<div id="valeurform">
									<input type="text" name="inputPrenom" value="${professionnel.prenom_professionnel}" size="40"/>
								</div>
								<div id="idform">
									Site de travail :
								</div>
 								<div id="valeurform"> 
									<SELECT name="site" >
											<OPTION value="${professionnel.site_reference.id_site}"disabled selected>${professionnel.site_reference.nom_site}</option>
										<c:forEach items="${sites}" var="site">	
								
											<OPTION value="${site.id_site}">${site.nom_site}</option>	
										</c:forEach>										
									</SELECT>
								</div> 
								<div id="idform">
									Structure :
								</div>
 								<div id="valeurform"> 
									<SELECT name="structure" >
										<OPTION value="${professionnel.structure.id_structure}"disabled selected>${professionnel.structure.nom_structure}</option>
										<c:forEach items="${structures}" var="structure">	
										
											<OPTION value="${structure.id_structure}">${structure.nom_structure}</option>	
										</c:forEach>										
									</SELECT>
								</div>
								<div id="idform">
									Mot de passe :
								</div>
								<div id="valeurform">
									<input type="password" name="inputPassword" value="${professionnel.password}" size="40"/>
								</div>
								<div id="idform">
								Confirmer mot de passe :
								</div>
								<div id="valeurform">
									<input type="password" name="mdptech" placeholder="Veuillez confirmer le mot de passe" size="40"/>
								</div>
							
							</div>
										
								<section>
									<article id="retour">
										<input type ="submit" name="retour" value="Retour"/>
									</article>
									<article id="valider">
									<input type="hidden" value="${professionnel.tech_id}" name="inputId" />
										<input type ="submit" name="modifier" value="Valider"/>
									</article>
								</section>	
						</form>		
						</c:forEach>		
						
				</section>
			</div>
		</div>
</body>
</html>
