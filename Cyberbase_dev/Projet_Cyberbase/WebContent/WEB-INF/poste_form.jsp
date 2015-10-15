<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:main>
	<c:import url="/inc/modules/menu_gestion_salles_postes.jsp" />
	<div id="container-poste_form">
		<section>
				<form class="affecter-form" method="post">
					<fieldset>
						<legend class="legende-form">
							Informations poste
						</legend>
						<section>
							<label for="disponibilitePoste">Disponibilité
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
							<br><br>
							<input type="hidden" name="idPoste" value="${poste.id_poste}" />
							<label for="nomPoste">Nom du poste :</label> <input type="text"
								name="nomPoste" value="${poste.nom_poste}" size="20"
								maxlength="80" class="form-control" /> 
							<br> <label for="sitePoste">Poste rattaché au site :</label>
							<input type="text" name="sitePoste" value="${sitePro.nom_site}"
								size="20" maxlength="80" disabled class="form-control" /> <br> <label
								for="salles">Poste rattaché à la salle :</label> <select
								name="salles" id="salles" class="form-control">
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
									<br>
									<button type="submit" name="createPoste" class="btn btn-primary">Créer</button>
								</c:when>
								<c:otherwise>
									<br>
									<button type="submit" name="confirmEditPoste"  class="btn btn-primary">Modifier</button>
								</c:otherwise>
							</c:choose>
						</section>
			</fieldset>
			</form>
	</section>
	</div>
</t:main>