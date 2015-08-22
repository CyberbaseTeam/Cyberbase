<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="row">
		<div class="small-12 large-4 columns">
			<form method="post" class="custom">
				<div class="row">
					<div class="small-5 large-12 columns">
						<label>nom <input type="text" id="inputNom"
							name="inputNom" value="${param.inputNom}">
						</label>
					</div>
					<div class="small-5 large-12 columns">
						<label>prenom <input type="text" id="inputPrenom"
							name="inputPrenom" value="${param.inputPrenom}">
						</label>

					</div>
					<div class="small-2 large-12 columns">
						<input type="submit" value="rechercher" name="actionRechercher">
					</div>
					<div class="small-12 large-12 columns">
						<input type="submit" value="créer" name="actionCreer">
					</div>
				</div>
			</form>
		</div>
		<div class="small-12 large-8 columns">
			<table>
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

				<c:forEach items="${usagers}" var="usager">
					<tr>
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
						<td>${usager.site_inscription_id}</td>
						<td>${usager.id_quartier}</td>
						<td>${usager.id_csp}</td>
						<td>${usager.id_formation}</td>
						<td><a href="fiche_usager?id=${usager.id_usager}">form</a>
							<form method="post">
								<input type="hidden" value="${usager.id_usager}" name="inputId" />
								<input type="submit" value="delete" name="actionSupprimer">
							</form></td>
					</tr>
				</c:forEach>

			</table>
		</div>
	</div>
</body>
</html>>