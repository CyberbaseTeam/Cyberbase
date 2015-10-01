<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div id="query">
	<fieldset>
		<legend>Requète personnalisée</legend>
		<span class="error"></span>
		<form method="POST">
			<div>
				<label for="searchPanel" class="label">Chercher parmi:</label> 
				<SELECT name="searchPanel">
					<option selected value="all">Tous les inscrits</option>
         			<c:forEach items="${siteList}" var="site">
						<option value="${site.id_site}">${site.nom_site}</option>
					</c:forEach>	    
				</SELECT>
			</div>
			<br />
			
			<label for="displayData[]">Données à afficher: </label><br />
			<input type="checkbox" name="displayData[]" value="displayGender">Civilité
			<input type="checkbox" name="displayData[]" value="displaySurname">Prénom<br />
			<input type="checkbox" name="displayData[]" value="displayName">Nom
			<input type="checkbox" name="displayData[]" value="displayDOB">Date de naissance<br />
			<input type="checkbox" name="displayData[]" value="displayAddress">Adresse
			<input type="checkbox" name="displayData[]" value="displayCity">Ville<br />
			<input type="checkbox" name="displayData[]" value="displayZipCode">Code postal
			<input type="checkbox" name="displayData[]" value="displayEmail">email<br />
			<input type="checkbox" name="displayData[]" value="displayPatronage">Accompagnement
			<input type="checkbox" name="displayData[]" value="displaySite">Site d'inscription<br />
			<input type="checkbox" name="displayData[]" value="displayDistrict">Quartier
			<input type="checkbox" name="displayData[]" value="displayCsp">CSP<br />
			<input type="checkbox" name="displayData[]" value="displayFormation">Formation
			<input type="checkbox" name="displayData[]" value="displayVisitCount"/>Nombre de visites <br />	
			
			<h3>Critères de recherche</h3>

			<div id="gender">
				<label for="gender" class="label">Sexe: </label> 
				<input type="radio" name="gender" value="M"> Masculin 
				<input type="radio" name="gender" value="F"> Féminin<br />
			</div>

			<label for="city" class="label">Ville: </label><input type="text" name="city" /><br /> 
			
			<label for="district" class="label">Quartier:</label> 
			<select name="district"><br />
				<option value="">Critère par quartier</option>
				<c:forEach items="${quartierList}" var="quartier">
					<option value="${quartier.id_quartier}">${quartier.nom_quartier}</option>
				</c:forEach>
			</select><br /> 
			
			<label for="csp" class="label">CSP:</label> 
			<select name="csp"><br />
				<option value="">Critère par csp</option>
				<c:forEach items="${cspList}" var="csp">
					<option value="${csp.id_csp}">${csp.libelle_csp}</option>
				</c:forEach>
			</select><br /> 
			<label for="formation" class="label">Niveau de formation: </label> 
			<select name="formation"><br />
				<option value="">Critère par formation</option>
				<c:forEach items="${formationList}" var="formation">
					<option value="${formation.id_formation}">${formation.nom_formation}</option>
				</c:forEach>
			</select><br />
			<label for="objective" class="label">Objet de la visite: </label>
			<select name="objective"><br />
				<option value="">Critère par démarche</option>
				<c:forEach items="${demarcheList}" var="objet">
					<option value="${objet.id_demarche}">${objet.nom_demarche}</option>
				</c:forEach>
			</select><br />
			<label for="visitMin" class="label">Nombre de venues entre </label>
			<input type="text" name="visitMin"> 
			<label	class="label_centre" for="visitMax"> et </label>
			<input type="text"	name="visitMax"><br /> 
			<label for="dateStart"	class="label">Venues entre </label>
			<input type="date"	name="dateStart">
			<label for="dateEnd" class="label_centre"> et </label>
			<input type="date" name="dateEnd"><br />

			
			
			
			<label>Enregistrer cette requète dans vos requètes favorites: </label>
			<input type="checkbox" name="saveQuery" />
			<label>Sous quel nom: </label>
			<input type="text" name="queryName" /> <br />
			
			<input class="executeQuery" type="submit" value="lancer la requète" />
		</form>
	</fieldset>
</div>