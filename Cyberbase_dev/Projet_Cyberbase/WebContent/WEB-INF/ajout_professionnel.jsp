<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:main>
	
	

	<div id="content">
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
		<div id="admin-add-pro"> 
			<section>
					<h2 id="titre-modif_admin">Ajouter un utilisateur</h2>	
												
						<form method="post" action="add_or_update_pro">
							
							
								<label for="name">Nom: </label>
								<input type="text" name="name"/>
							
								<label for="firstName">Prénom: </label>
								<input type="text" name="firstName"/>
									
								<label for="site">Site de travail: </label>
								<SELECT name="site" >									
									<c:forEach items="${sites}" var="site">																		
										<OPTION value="${site.id_site}" >${site.nom_site}</option>											
									</c:forEach>										
								</SELECT>
								
								<label for="structure">Structure: </label>
 								<SELECT name="structure" >									
									<c:forEach items="${structures}" var="structure">											
										<OPTION value="${structure.id_structure}">${structure.nom_structure}</option>	
									</c:forEach>										
								</SELECT>
							
								<label for="password">Mot de passe: </label>
								<input type="password" name="password"/>
									
								<label for="passwordConfirm">Confirmation du mot de passe: </label>
								<input type="password" name="passwordConfirm"/>								
									
								<input type ="submit" name="retour" value="Retour"/>
									
								<input type ="submit" name="create" value="Valider"/>
									
						</form>		
							
						
				</section>
			</div>
		</div>
</t:main>
