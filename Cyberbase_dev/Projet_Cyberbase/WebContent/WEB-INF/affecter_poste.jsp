<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:main>
	<c:import url="/inc/modules/menu_gestion_salles_postes.jsp" />
	<section>
		 		<article class="colonne-affecter">
		 			<form class="affecter-form" method="post">
						<fieldset>
							<legend class="legende-form">
								<h2>Choix usager</h2>
							</legend>
							<div class="conteneur-form">
								<div class="label-form">
									<label for="selection-site">Site:</label>
								</div>
								<div class="champs-form">
     								<select name="selection-site">
     									<option value="" disabled selected>Choix du site</option>
				     					<option value="">Site 1</option>
				        				<option value="">Site 2</option>
				        				<option value="">Site 3</option>
    				 				</select>
    				 			</div>
							</div>
							<div class="conteneur-form">
								<div class="label-form">
									<label for="selection-site">Usager :</label>
								</div>
								<div class="champs-form">
     								<select name="selection-site">
     									<option value="" disabled selected>Choix de l'usager</option>
				     					<option value="">Usager 1</option>
				        				<option value="">Usager 2</option>
				        				<option value="">Usager 3</option>
    				 				</select>
    				 			</div>
							</div>
		 		</article>
		 		<article class="colonne-affecter">
						<fieldset>
							<legend class="legende-form">
								<h2>Choix poste à affecter</h2>
							</legend>
							<div class="conteneur-form">
								<div class="label-form">
									<label for="selection-site">Salle:</label>
								</div>
								<c:choose>
									<c:when test="${! empty poste}">
										<input type="text" value="${poste.salle.nom_salle}" disabled>
									</c:when>
									<c:otherwise>
										<select name="selection-site">
	     									<option value="" disabled selected>Choix de la salle</option>
					     					<option value="">Salle 1</option>
					        				<option value="">Salle 2</option>
					        				<option value="">Salle 3</option>
    				 					</select>
									</c:otherwise>
								</c:choose>
     								
							</div>
							<div class="conteneur-form">
								<div class="label-form">
									<label for="selection-site">Poste:</label>
								</div>
								<c:choose>
									<c:when test="${! empty poste}">
										<input type="text" value="${poste.nom_poste}" disabled>
									</c:when>
									<c:otherwise>
										<select name="selection-site">
     									<option value="" disabled selected>Choix du poste</option>
				     					<option value="">Poste 1</option>
				        				<option value="">Poste 2</option>
				        				<option value="">Poste 3</option>
    				 				</select>
									</c:otherwise>
								</c:choose>
							</div>
		 		</article>
		 		<article class="colonne-affecter">
						<fieldset>
							<legend class="legende-form">
								<h2>Infos affectations</h2>
							</legend>
							<div class="conteneur-form">
								<div class="label-form">
									<label for="selection-site">Objet:</label>
								</div>
     								<select name="selection-site">
     									<option value="" disabled selected>Motif de l'affactation</option>
				     					<option value="">Recherche d'emploi</option>
				        				<option value="">Travail du CV</option>
				        				<option value="">Lettre de motivation</option>
    				 				</select>
							</div>
					</form>
		 		</article>
		 	</section>
		 	<section>
		 		<div class="conteneur-buttons">
		 			<div class="valider-button">
		 				<input type="submit" name="envoyer" value"Envoyer"/>
		 			</div>
		 			<div class="retour-button">
		 				<input type="reset" name="effacer" value"Retour"/>
		 			</div>
		 		</div>
		 	</section>
</t:main>