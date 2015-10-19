<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


	<div id="query">

		<h3>Requête personnalisée</h3>
		<p class="formError">${errorMessages.city }</p><p class="formError">${errorMessages.visitMin }</p><p class="formError">${errorMessages.dateStart }</p>
		<form method="POST">
			<div class="row">
				<div class="col-md-1"></div>
				<label class="col-md-2" for="searchPanel">Chercher parmi:</label>
				 <select name="searchPanel">
					<option selected value="all">Tous les inscrits</option>
					<c:forEach items="${siteList}" var="site">
						<option value="${site.id_site}">${site.nom_site}</option>
					</c:forEach>
				</select>
			</div>
			
			<div class="row">
				<div class="col-md-1"></div>
				<label class="col-md-2" for="displayData[]">Données à afficher: </label>
				
				<div class="col-md-2">
					<p>
						<input type="checkbox" name="displayData[]" value="displayGender">
						Civilité
					</p>
				</div>
				<div class="col-md-2">
					<p>
						<input type="checkbox" name="displayData[]" value="displaySurname">
						Prénom
					</p>
				</div>
				<div class="col-md-2">
					<p>
						<input type="checkbox" name="displayData[]" value="displayName">
						Nom
					</p>
				</div>
				<div class="col-md-2">
					<p>
						<input type="checkbox" name="displayData[]" value="displayDOB">
						Date de naissance
					</p>
				</div>
				
							
			</div>
			<div class="row">
				<div class="col-md-3"></div>
				
				<div class="col-md-2">
					<p>
						<input type="checkbox" name="displayData[]" value="displayAddress">Adresse
					</p>
				</div>
				<div class="col-md-2">
					<p>
						<input type="checkbox" name="displayData[]" value="displayCity">
						Ville
					</p>
				</div>
				<div class="col-md-2">
					<p>
						<input type="checkbox" name="displayData[]" value="displayZipCode">Code
						postal
					</p>
				</div>
				<div class="col-md-2">
					<p>
						<input type="checkbox" name="displayData[]" value="displayEmail">
						Email
					</p>
				</div>
							
			</div>
			<div class="row">
				<div class="col-md-3"></div>
				

				
				<div class="col-md-2">
					<p>
						<input type="checkbox" name="displayData[]"
							value="displayPatronage">Accompagnement
					</p>
				</div>
				<div class="col-md-2">
					<p>
						<input type="checkbox" name="displayData[]" value="displaySite">
						Site d'inscription
					</p>
				</div>
				<div class="col-md-2">
					<p>
						<input type="checkbox" name="displayData[]"
							value="displayDistrict">Quartier
					</p>
				</div>
				<div class="col-md-2">
					<p>
						<input type="checkbox" name="displayData[]" value="displayCsp">CSP
					</p>
				</div>
				
				
			</div>
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-2">
					<p>
						<input type="checkbox" name="displayData[]"
							value="displayFormation">Formation
					</p>
				</div>
				<div class="col-md-2">
					<p>
						<input type="checkbox" name="displayData[]"
							value="displayVisitCount" /> Nombre de visites
					</p>
				</div>
				
				
				
		
		
				
				
			</div>
			
			<div class="row">
				<div class="col-md-1"></div>
				<label id="criteriaLabel" class="col-md-2 ">Critères de recherche:</label>
				<div id="gender">
					
					<div class="col-md-1">
						<label for="gender">Sexe: </label>
					</div>
					<div class="col-md-1">
						<input type="radio" name="gender" value="M">
					</div>
					<div class="col-md-1">
						<p>Masculin</p>
					</div>
					<div class="col-md-1">
						<input type="radio" name="gender" value="F">
					</div>
					<div class="col-md-1">
						<p>Féminin</p>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-1">
					<label for="city">Ville: </label>
				</div>
				<div class="col-md-2">
					<input type="text" name="city" />
				</div>
				<div class="col-md-1">
					<label for="district">Quartier:</label>
				</div>
				<div class="col-md-2">
					<select name="district">
						<option value="">Critère par quartier</option>
						<c:forEach items="${quartierList}" var="quartier">
							<option value="${quartier.id_quartier}">${quartier.nom_quartier}</option>
						</c:forEach>
					</select>
				</div>
				
			</div>
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-1">
					<label for="csp">CSP:</label>
				</div>
				<div class="col-md-2">
					<select name="csp">
						<option value="">Critère par csp</option>
						<c:forEach items="${cspList}" var="csp">
							<option value="${csp.id_csp}">${csp.libelle_csp}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-md-1">
					<label for="formation">Niveau de formation: </label>
				</div>

				<div class="col-md-2">
					<select name="formation">
						<option value="">Critère par formation</option>
						<c:forEach items="${formationList}" var="formation">
							<option value="${formation.id_formation}">${formation.nom_formation}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-md-1">
					<label for="objective">Objet de la visite: </label>
				</div>
				<div class="col-md-2">
					<select name="objective">
						<option value="">Critère par démarche</option>
						<c:forEach items="${demarcheList}" var="objet">
							<option value="${objet.id_demarche}">${objet.nom_demarche}</option>
						</c:forEach>
					</select>
				</div>
				
				
			</div>
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-1">
					<label for="visitMin">Nombre de venues entre </label>
				</div>
				<div class="col-md-2">
					<input type="text" name="visitMin">
				</div>
				<div class="col-md-1">
					<label for="visitMax"> et </label>
				</div>
				<div class="col-md-2">
					<input type="text" name="visitMax">
				</div>
						
				
			</div>
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-1">
					<label for="dateStart">Personnes venues entre </label>
				</div>
				<div class="col-md-2">
					<input type="date" name="dateStart">
				</div>
				<div class="col-md-1">
					<label for="dateEnd"> et </label>
				</div>
				<div class="col-md-2">
					<input type="date" name="dateEnd">
				</div>
			</div>
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-3">
					<label>Enregistrer cette requête dans vos requêtes	favorites: </label>
					<input id="saveQueryCheck" type="checkbox" name="saveQuery" />
				</div>
				<div id="saveQueryNameForm">
					<div class="col-md-1">
						<label>Sous ce nom: </label>
					</div>
					<div class="col-md-2">
						<input type="text" name="queryName" />
					</div>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-4"></div>
				<div class="col-md-3">
					<input class="btn btn-primary btn-lg btn-block" type="submit"
						value="Lancer la requête" />
				</div>
				<div class="col-md-4"></div>
			</div>
		</form>

	</div>
