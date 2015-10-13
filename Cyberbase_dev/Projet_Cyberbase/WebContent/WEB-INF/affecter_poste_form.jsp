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
     								<c:choose>
     									<c:when test="${empty affectation}">
     										<option value="" disabled selected>Choix du site</option>
     									</c:when>
     								</c:choose>
											<c:forEach items="${users}" var="site">
	     											<c:choose>
	     												<c:when test="${site.key == affectation.usager.site_inscription.nom_site}">
	     													<option value="${affectation.usager.site_inscription.nom_site}" class="${site.key}" selected>${affectation.usager.site_inscription.nom_site}</option>
	     												</c:when>
	     												<c:otherwise>
	     													<option value="${site.key}" class="${site.key}">${site.key}</option>
	     												</c:otherwise>
	     											</c:choose>
	     									</c:forEach> 
    				 				</select>
							</div>
							<div class="conteneur-form">
									<label for="selection-site">Usager :</label>
     								<select name="user" id="user">
     								<c:choose>
     									<c:when test="${empty affectation}">
     										<option value="" disabled selected>Choix de l'usager</option>
     									</c:when>
     								</c:choose>
      										<c:forEach items="${users}" var="site">
      											<c:forEach items="${site.value}" var="user">
      												<c:choose>
	      												<c:when test="${user.id_usager == affectation.usager.id_usager}">
	      													<option value="${affectation.usager.id_usager}" class="${site.key}" selected>${affectation.usager.prenom_usager} ${affectation.usager.nom_usager}</option>
	      												</c:when>
	      												<c:otherwise>
	     													<option value="${user.id_usager}" class="${site.key}">${user.prenom_usager} ${user.nom_usager}</option>
	     												</c:otherwise>
	     											</c:choose>
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
													<c:choose>
														<c:when test="${empty affectation}">
															<option value="" disabled selected>Choix de la salle</option>
														</c:when>
													</c:choose>
													<c:forEach items="${postes}" var="salle">
														<c:choose>
															<c:when test="${affectation.poste.salle.nom_salle == salle.key }">
																<option value="${affectation.poste.salle.nom_salle}" class="${salle.key}" selected>${affectation.poste.salle.nom_salle}</option>
															</c:when>
															<c:otherwise>
																<option value="${salle.key}" class="${salle.key}">${salle.key}</option>
															</c:otherwise>
														</c:choose>
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
													<c:choose>
														<c:when test="${empty affectation}">
															<option value="" disabled selected>Choix du poste</option>
														</c:when>
													</c:choose>
													<c:forEach items="${postes}" var="salle">
      													<c:forEach items="${salle.value}" var="poste">
      														<c:choose>
																<c:when test="${affectation.poste.id_poste == poste.id_poste }">
     																<option value="${affectation.poste.id_poste}" class="${salle.key}" selected>${affectation.poste.nom_poste}</option>
     															</c:when>
     															<c:otherwise>
     																<option value="${poste.id_poste}" class="${salle.key}">${poste.nom_poste}</option>
     															</c:otherwise>
     														</c:choose>
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
     									<c:choose>
											<c:when test="${empty affectation}">
     											<option value="" disabled selected>Choix de la démarche</option>
     										</c:when>
     									</c:choose>
      									<c:forEach items="${demarches}" var="demarche">
      										<c:choose>
												<c:when test="${affectation.demarche.id_demarche == demarche.id_demarche}">
													<option value="${affectation.demarche.id_demarche}" selected>${affectation.demarche.nom_demarche}</option>
												</c:when>
												<c:otherwise>
													<option value="${demarche.id_demarche}">${demarche.nom_demarche}</option>
												</c:otherwise>
											</c:choose>	
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
									<c:choose>
											<c:when test="${not empty affectation}">
     											<span>Fin de l'affectation : <fmt:formatDate value="${affectation.date_fin_affectation}" pattern="HH:mm MM/dd/yyyy"/></span>
     										</c:when>
     								</c:choose>
     								<select name="time" id="time">
     									<c:choose>
											<c:when test="${not empty affectation}">
     											<option value="0" selected>Ne pas prolonger</option>
     										</c:when>
     										<c:otherwise>
     											<option value="" disabled selected>Choix de la durée</option>
     										</c:otherwise>
     									</c:choose>
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
		 				<c:choose>
							<c:when test="${not empty affectation}">
								<input type="submit" name="edit" value="Mettre à jour"/>
								<input type="hidden" value="${affectation.date_debut_affectation}" name="inputDateDebut"/>
								<input type="hidden" value="${affectation.id_affectation}" name="inputIdAffectation"/>
							</c:when>
							<c:otherwise>
								<input type="submit" name="submit" value="Créer"/>
							</c:otherwise>
						</c:choose>
		 				<input type="reset" name="reset" value="Retour"/>
		 		</article>
		 	</section>
		 </form>
</t:main>