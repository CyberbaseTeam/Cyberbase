<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:main>
	<c:import url="/inc/modules/menu_gestion_salles_postes.jsp" />
	<div id="container-affecter_form">
	<section>
		 		<article class="colonne-affecter">
		 			<form method="post">
						<fieldset>
							<legend>
								Choix usager
							</legend>
									<label for="selection-site">Site:</label>
     								<select name="site" id="site" class="form-control">
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
    				 				<br>
									<label for="selection-site">Usager :</label>
     								<select name="user" id="user" class="form-control">
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
    				 				<br><br>								    													
		 		</article>
		 		<article class="colonne-affecter">
						<fieldset>
							<legend >
								Choix poste à affecter
							</legend>
									<label for="selection-site">Salle:</label>
     								<select name="salle" id="salle" class="form-control">
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
    				 				<br>
									<label for="selection-site">Poste :</label>
     								<select name="poste" id="poste" class="form-control">
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
						</fieldset>
		 		</article>
		 		<br><br>
		 		<article class="colonne-affecter">
						<fieldset>
							<legend>
								Infos affectations
							</legend>
									<label for="selection-site">Démarche :</label>
     								<select name="demarche" id="demarche" class="form-control">
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
		 		</article>
		 		<br><br>
		 		<article class="colonne-affecter">
						<fieldset>
							<legend>
								Durée affectation
							</legend>
									<label for="selection-site">Durée :</label>
									<c:choose>
											<c:when test="${not empty affectation}">
     											<span>Fin de l'affectation : <fmt:formatDate value="${affectation.date_fin_affectation}" pattern="HH:mm MM/dd/yyyy"/></span>
     										</c:when>
     								</c:choose>
     								<select name="time" id="time" class="form-control">
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
		 		</article>
		 	</section>
		 	<br>
		 	<section>
		 		<article>
		 				<input type="hidden" value="${idPro}" name="inputIdPro"/>
		 				<c:choose>
							<c:when test="${not empty affectation}">
								<button type="submit" name="edit" class="btn btn-primary">Mettre à jour</button>
								<input type="hidden" value="${affectation.date_debut_affectation}" name="inputDateDebut"/>
								<input type="hidden" value="${affectation.id_affectation}" name="inputIdAffectation"/>
							</c:when>
							<c:otherwise>
								<button type="submit" name="create" class="btn btn-primary">Créer</button>
							</c:otherwise>
						</c:choose>
		 		</article>
		 	</section>
		 </form>
		 </div>
</t:main>