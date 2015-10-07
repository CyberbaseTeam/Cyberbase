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
							<c:forEach items="${users}" var="site">
     							${site.key}
     								<c:forEach items="${site.value}" var="usager">
        								<c:out value="${usager.nom_usager}" /> 
    								</c:forEach>
     						</c:forEach>                     
							<div class="conteneur-form">
									<label for="selection-site">Site:</label>
     								<select name="selection-site">
     									<option value="" disabled selected>${site.nom_site}</option>
 
    				 				</select>
							</div>
							<div class="conteneur-form">
									<label for="selection-site">Usager :</label>
     								<select name="selection-site">
     									<option value="" disabled selected>Choix de l'usager</option>
<%--      										<c:forEach items="${users}" var="user"> --%>
<%--      											<option value="${user.tech_id}">${user.prenom_usager} ${user.nom_usager}</option> --%>
<%--      										</c:forEach> --%>
    				 				</select>
							</div>
		 		</article>
		 		<article class="colonne-affecter">
						<fieldset>
							<legend class="legende-form">
								<h2>Choix poste à affecter</h2>
							</legend>
							<div class="conteneur-form">
									<label for="selection-site">Salle:</label>
								<c:choose>
									<c:when test="${! empty poste}">
										<input type="text" value="${poste.salle.nom_salle}" disabled>
									</c:when>
									<c:otherwise>
										<select id="dropdown1">
										    <c:forEach items="${salles}" var="salle">
										        <option value="${salle.id_salle}">${salle.nom_salle}</option>
										    </c:forEach>
										</select>
										<select id="dropdown2">
										    <option>Please select dropdown1</option>
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
    				 					<select id="mark" name="mark">
										  <option value="">--</option>
										  <option value="bmw">BMW</option>
										  <option value="audi">Audi</option>
										</select>
										<select id="series" name="series">
										  <option value="">--</option>
										  <option value="series-3" class="bmw">3 series</option>
										  <option value="series-5" class="bmw">5 series</option>
										  <option value="series-6" class="bmw">6 series</option>
										  <option value="a3" class="audi">A3</option>
										  <option value="a4" class="audi">A4</option>
										  <option value="a5" class="audi">A5</option>
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