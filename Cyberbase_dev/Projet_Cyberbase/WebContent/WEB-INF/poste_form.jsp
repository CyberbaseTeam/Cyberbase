<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:main>
	<c:import url="/inc/modules/menu_gestion_salles_postes.jsp" />
	<div id="conteneur-formulaires">
		<section>
			<div id="conteneur-formulaires">
				<form class="affecter-form" method="post">
					<fieldset>
						<legend class="legende-form">
							<h2>Informations poste</h2>
						</legend>
						<section>
							<input type="hidden" name="idPoste" value="${poste.id_poste}" />
							<label for="nomPoste">Nom du poste :</label> <input type="text"
								name="nomPoste" value="${poste.nom_poste}" size="20"
								maxlength="80" /> <br> <label for="disponibilitePoste">Disponibilité
								actuelle du poste :</label>
							<c:choose>
								<c:when test="${poste.disponibilite == true}">
									<input type="hidden" name="disponibilitePoste" value="true" />
									<span>Poste disponible</span>
								</c:when>
								<c:otherwise>
									<input type="hidden" name="disponibilitePoste" value="false" />
									<span>Poste non disponible</span>
								</c:otherwise>
							</c:choose>
							<br> <label for="sitePoste">Poste rattaché au site :</label>
							<input type="text" name="sitePoste" value="${sitePro.nom_site}"
								size="20" maxlength="80" disabled /> <br> <label
								for="salles">Poste rattaché à la salle :</label> <select
								name="salles" id="salles">
								<c:if test="${empty poste}">
									<option selected disabled>Choix de la salle</option>
								</c:if>
								<c:forEach items="${sitePro.salles}" var="salle">
									<c:choose>
										<c:when test="${salle.id_salle == idSalle}">
											<option value="${salle.id_salle}" selected>${salle.nom_salle}</option>
										</c:when>
										<c:otherwise>
											<option value="${salle.id_salle}">${salle.nom_salle}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</section>
						<section>
							<c:choose>
								<c:when test="${empty poste}">
									<input type="submit" name="createPoste" value="Créer" />
								</c:when>
								<c:otherwise>
									<input type="submit" name="confirmEditPoste" value="Valider" />
								</c:otherwise>
							</c:choose>
							<input type="reset" name="effacer" value="Retour" />
						</section>
			</div>
			</fieldset>
			</form>
	</div>
	</section>
	</div>
</t:main>