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
				<th>Date de naissance</th>
				<th>Adresse</th>
				<th>Code postal</th>
				<th>Ville</th>
				<th>Email</th>
				<th>Accompagnement</th>
				<th>Site d'inscription</th>
				<th>Niveau de formation</th>
				<th>CSP</th>
				<th>Quartier</th>
				<th>action</th>
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
				<td>${usager.date_naissance_usager}</td>
				<td>${usager.adresse_usager}</td>
				<td>${usager.code_postal_usager}</td>
				<td>${usager.ville_usager}</td>
				<td>${usager.email_usager}</td>
				<td>${usager.accompagnement}</td>
				<td>${usager.site_inscription.nom_site}</td>
				<td>${usager.niveau_formation.nom_formation}</td>
				<td>${usager.csp.libelle_csp}</td>
				<td>${usager.quartier.nom_quartier}</td>
				<td>
					<form method="post">
						<input type="hidden" value="${usager.id_usager}" name="inputId" />
						<input type="submit" value="Modifier" name="editUsager" />
						<input type="submit" value="Supprimer" name="deleteUsager" />
						<input type="submit" value="Voir historique" name="viewHistory" />
					</form>
				</td>
				</tr>
			</c:forEach>

		</table>
	</div>

