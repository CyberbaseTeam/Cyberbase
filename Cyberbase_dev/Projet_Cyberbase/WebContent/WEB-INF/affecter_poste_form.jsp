<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:main>
	<c:import url="/inc/modules/menu_gestion_salles_postes.jsp" />
	<section>
		 		<article class="colonne-affecter">
		 			<form method="post">
						<fieldset>
							<legend class="legende-form">
								<h2>Choix usager</h2>
							</legend>
							<div class="conteneur-form">
									<label for="selection-site">Site:</label>
     								<select name="site" id="site">
     									<option value="" disabled selected>Choix du site</option>
											<c:forEach items="${users}" var="site">
	     										<option value="${site.key}" class="${site.key}">${site.key}</option>
	     									</c:forEach> 
    				 				</select>
							</div>
							<div class="conteneur-form">
									<label for="selection-site">Usager :</label>
     								<select name="user" id="user">
     									<option value="" disabled selected>Choix de l'usager</option>
      										<c:forEach items="${users}" var="site">
      											<c:forEach items="${site.value}" var="user">
     												<option value="${user.id_usager}" class="${site.key}">${user.prenom_usager} ${user.nom_usager}</option>
     											</c:forEach>
     										</c:forEach>
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
     								<select name="salle" id="salle">
     										<c:choose>
												<c:when test="${empty inputPoste}">
													<option value="" disabled selected>Choix de la salle</option>
													<c:forEach items="${postes}" var="salle">
	     												<option value="${salle.key}" class="${salle.key}">${salle.key}</option>
	     											</c:forEach> 
												</c:when>
												<c:otherwise>
													<option value="${inputPoste.salle.id_salle}" selected>${inputPoste.salle.nom_salle}</option>
												</c:otherwise>
											</c:choose>
    				 				</select>
							</div>
							<div class="conteneur-form">
									<label for="selection-site">Poste :</label>
     								<select name="poste" id="poste">
     									<c:choose>
												<c:when test="${empty inputPoste}">
													<option value="" disabled selected>Choix du poste</option>
													<c:forEach items="${postes}" var="salle">
      													<c:forEach items="${salle.value}" var="poste">
     														<option value="${poste.id_poste}" class="${salle.key}">${poste.nom_poste}</option>
     													</c:forEach>
     												</c:forEach> 
												</c:when>
												<c:otherwise>
													<option value="${inputPoste.id_poste}" selected>${inputPoste.nom_poste}</option>
												</c:otherwise>										
										</c:choose>
    				 				</select>
							</div>   						
						</fieldset>
		 		</article>
		 		<article class="colonne-affecter">
						<fieldset>
							<legend class="legende-form">
								<h2>Infos affectations</h2>
							</legend>
							<div class="conteneur-form">
									<label for="selection-site">Démarche :</label>
     								<select name="demarche" id="demarche">
     									<option value="" disabled selected>Choix de la démarche</option>
      											<c:forEach items="${demarches}" var="demarche">
     												<option value="${demarche.id_demarche}">${demarche.nom_demarche}</option>
     											</c:forEach>
    				 				</select>
							</div>
		 		</article>
		 		<article class="colonne-affecter">
						<fieldset>
							<legend class="legende-form">
								<h2>Durée affectation</h2>
							</legend>
							<div class="conteneur-form">
									<label for="selection-site">Durée :</label>
     								<select name="time" id="time">
     									<option value="" disabled selected>Choix de la durée</option>
      									<option value="15">15 min</option>
      									<option value="30">30 min</option>
      									<option value="45">45 min</option>
      									<option value="60">1 heure</option>
      									<option value="75">1 heure 15 min</option>
      									<option value="90">1 heure 30 min</option>
      									<option value="105">1 heure 45 min</option>
      									<option value="120">2 heures</option>
    				 				</select>
							</div>
		 		</article>
		 	</section>
		 	<section>
		 		<article>
		 				<input type="hidden" value="${idPro}" name="inputIdPro"/>
		 				<input type="submit" name="submit" value="Envoyer"/>
		 				<input type="reset" name="reset" value="Retour"/>
		 		</article>
		 	</section>
		 </form>
</t:main>