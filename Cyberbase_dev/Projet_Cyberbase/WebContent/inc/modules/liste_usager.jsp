<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

	

	<div>
		<table class="table">
			<tr>
				<th>Identifiant</th>
				<th>Civilité</th>
				<th>Nom</th>
				<th>Prenom</th>
				<th>Date naissance</th>
				<th>Adresse</th>
				<th>Code postal</th>
				<th>Ville</th>
				<th>Email</th>
				<th>Tuteur</th>
				<th>Site inscription</th>
				<th>Formation</th>
				<th>CSP</th>
				<th>Quartier</th>
				<th colspan="4">action</th>
			</tr>

			<c:forEach items="${usagers}" var="usager" varStatus="loop">

				<c:choose>
					<c:when test="${loop.index%2==0}">
						<tr>
					</c:when>
					<c:otherwise>
						<tr class="info">
					</c:otherwise>
				</c:choose>



				<td>${usager.tech_id}</td>
				<td>${usager.civilite_usager}</td>
				<td>${usager.nom_usager}</td>
				<td>${usager.prenom_usager}</td>
				<td><fmt:formatDate value="${usager.date_naissance_usager}" pattern="dd/MM/yyyy"/></td>
				<td>${usager.adresse_usager}</td>
				<td>${usager.code_postal_usager}</td>
				<td>${usager.ville_usager}</td>
				<td>${usager.email_usager}</td>
				<td>
					<c:choose>
						<c:when test="${usager.accompagnement}">
								OUI		
						</c:when>
						<c:otherwise>
								NON			
						</c:otherwise>
					</c:choose>
				</td>
				<td>${usager.site_inscription.nom_site}</td>
				<td>${usager.niveau_formation.nom_formation}</td>
				<td>${usager.csp.libelle_csp}</td>
				<td>${usager.quartier.nom_quartier}</td>
					<form method="post">
						<input type="hidden" value="${usager.id_usager}" name="inputId" />
						<td>
							<button type="submit" name="editUsager" class="btn btn-primary">Modifier</button>
						</td>
						<td>
							<button type="submit" name="deleteUsager" class="btn btn-danger">Supprimer</button>
						</td>
						<td>
							<button type="submit" name="viewHistory" class="btn btn-info">Historique</button>
						</td>
						<c:set var="count" value="0" scope="page" />
						<c:forEach items="${exclusions}" var="exclusion">
							<c:if test="${usager.id_usager == exclusion.usager.id_usager}">
								<c:set var="count" value="${count + 1}" scope="page"/>
								<c:set var="statut" value="${exclusion.statut_exclusion}" scope="page"/>
								<c:set var="date" value="${exclusion.date_fin}" scope="page"/>
							</c:if>
						</c:forEach>
						<c:choose>
							<c:when test="${count > 0}">
								<c:choose>
									<c:when test="${statut eq 'temporaire'}">
										<td>
											<div class="alert alert-warning" role="alert">Exclu jusqu'au <fmt:formatDate value="${date}" pattern="dd/MM/yyyy"/></div>
										</td>
									</c:when>
									<c:otherwise>
										<td>
											<div class="alert alert-danger" role="alert">Exclu définitivement</div>
										</td>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<td>
									<button type="submit" name="excludeUsager" class="btn btn-warning">Exclure</button>
								</td>
							</c:otherwise>
						</c:choose>
						
					</form>
				</tr>
			</c:forEach>

		</table>
	</div>

