<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:main>
	<c:import url="/inc/modules/menu_gestion_salles_postes.jsp" />
	<section>
		<div id="conteneur-formulaires">
			<form class="affecter-form" method="post">
				<fieldset>
					<legend class="legende-form">
						<h2>Informations salle</h2>
					</legend>
					<div class="ligne-formulaire">
						<div class="label-form">
							<label for="selection-site">Nom de la salle :</label>
						</div>
						<div class="champs-form">
							<input type="text" name="nomSalle" value="${salle.nom_salle}"
								size="20" maxlength="80" /> <input type="hidden"
								value="${sitePro.id_site}" name="idSite">
						</div>
					</div>
				</fieldset>
		</div>
	</section>
	<section>
		<c:choose>
			<c:when test="${empty salle}">
				<input type="submit" name="createSalle" value="Créer" />
			</c:when>
			<c:otherwise>
				<input type="hidden" value="${salle.id_salle}" name="idSalle">
				<input type="submit" name="confirmEditSalle" value="Valider" />
			</c:otherwise>
		</c:choose>
		<input type="reset" name="effacer" value="Retour" />
	  </form>
	</section>
	<c:if test="${not empty salle}">
		<section>
				<fieldset>
					<legend class="legende-form">
						<h2>Postes rattachés à ${salle.nom_salle}</h2>
					</legend>
					<ul>
						<c:forEach items="${salle.postes}" var="poste">
							<form method="post">
								<input type="hidden" value="${salle.id_salle}" name="idSalle">
								<li>${poste.nom_poste} : <input type="submit"
									name="editPoste" value="Modifier poste" /><input type="submit"
									name="deletePoste" value="Supprimer poste" /><input
									type="hidden" value="${poste.id_poste}" name="idPoste">
								</li>
							</form>
						</c:forEach>
					</ul>
				</fieldset>
		</section>
	</c:if>
	</div>
</t:main>