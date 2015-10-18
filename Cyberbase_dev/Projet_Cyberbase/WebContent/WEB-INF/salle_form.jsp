<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:main>
	<c:import url="/inc/modules/menu_gestion_salles_postes.jsp" />
	<div id="container-salle_form">
	<section class ="colonne-salle">
			<form method="post" id="myForm">
				<fieldset>
					<legend >
						Informations salle
					</legend>
							<label for="selection-site">Nom de la salle :</label>
							<input type="text" name="nomSalle" id="nomSalle" value="${salle.nom_salle}"
								size="20" maxlength="80" class="form-control" /> 
							<input type="hidden" value="${sitePro.id_site}" name="idSite">
							<c:choose>
								<c:when test="${empty salle}">
									<br>
									<input type="submit" name="createSalle" value="Créer" />
								</c:when>
								<c:otherwise>
									<br>
									<input type="hidden" value="${salle.id_salle}" name="idSalle">
									<button type="submit" name="confirmEditSalle" class="btn btn-primary">Valider</button>
								</c:otherwise>
							</c:choose>
				</fieldset>
			</form>
	</section>

	<c:if test="${not empty salle}">
		<section class="colonne-salle-postes">
				<fieldset>
					<legend class="legende-form">
						Postes rattachés à ${salle.nom_salle}
					</legend>
					<table class="table table-striped">
						<tbody>
							<c:forEach items="${salle.postes}" var="poste">
							<form method="post">
								<input type="hidden" value="${salle.id_salle}" name="idSalle">
								<tr>
								<td>${poste.nom_poste} :</td> 
								<td>
									<button type="submit" name="editPoste" class="btn btn-primary">Modifier</button>
								</td>
								<td>
									<button type="submit" name="deletePoste" class="btn btn-danger">Supprimer</button>
									<input type="hidden" value="${poste.id_poste}" name="idPoste">
								</td>
								</tr>
							</form>
						</c:forEach>
						</tbody>
					</table>
				</fieldset>
		</section>
	</c:if>
	</div>
</t:main>