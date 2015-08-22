<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div id="query">
	<fieldset>
		<legend>Requète personnalisée</legend>
		<span class="error"></span>
		<form action="/statistiques" method="POST">
			<div>
				<label for="search_panel" class="label">Chercher parmi:</label> 
				<input type="radio" name="search_panel" value="tous" checked>Tous les usagers 
				<input type="radio" name="search_panel" value="nouveaux"> Les nouveaux usagers
			</div>
			<br />
			
			<label for="displayData">Données à afficher: </label><br />
			<input type="checkbox" name="displayData" value="displayGender">Civilité
			<input type="checkbox" name="displayData" value="displaySurname">Nom<br />
			<input type="checkbox" name="displayData" value="displayName">Prénom
			<input type="checkbox" name="displayData" value="displayDOB">Date de naissance<br />
			<input type="checkbox" name="displayData" value="diplayAddress">Adresse
			<input type="checkbox" name="displayData" value="displayCity">Ville<br />
			<input type="checkbox" name="displayData" value="displayZipCode">Code postal
			<input type="checkbox" name="displayData" value="displayEmail">email<br />
			<input type="checkbox" name="displayData" value="displayPatronage">Accompagnement
			<input type="checkbox" name="displayData" value="displaySite">Site d'inscription<br />
			<input type="checkbox" name="displayData" value="displayDistrict">Quartier
			<input type="checkbox" name="displayData" value="displayCSP">CSP<br />
			<input type="checkbox" name="displayData" value="displayFormation">Formation
				
			<h3>Critères de recherche</h3>

			<div id="gender">
				<label for="gender" class="label">Sexe: </label> 
				<input type="radio" name="gender" value="M"> Masculin 
				<input type="radio" name="gender" value="F"> Féminin<br />
			</div>

			<label for="city" class="label">Ville: </label><input type="text" name="city" /><br /> 
			<label for="district" class="label">Quartier:</label> 
			<select name="district"><br />
				<c:forEach items="${listeQuartier}" var="quartier">
					<option value="">${quartier.nom_quartier}</option>
				</c:forEach>
			</select><br /> 
			<label for="formation" class="label">Niveau de formation: </label> 
			<select name="formation"><br />
				<c:forEach items="${listeFormation}" var="formation">
					<option value="">${formation.nom_formation}</option>
				</c:forEach>
			</select><br />
			<label for="objective" class="label">Objet de la visite: </label>
			<select name="objective"><br />
				<c:forEach items="${listeDemarche}" var="objet">
					<option value="">${demarche.nom_formation}</option>
				</c:forEach>
			</select><br />
			<label for="visit_min" class="label">Nombre de venues entre </label>
			<input type="text" name="visit_min"> 
			<label	class="label_centre" for="visit_max"> et </label>
			<input type="text"	name="visit_max"><br /> 
			<label for="date_start"	class="label">Venues entre </label>
			<input type="date"	name="date_start">
			<label for="date_end" class="label_centre"> et </label>
			<input type="date" name="date_end"><br />

			<label>Afficher le nombre de visite des usagers recherchés: </label><input
				type="checkbox" name="displayVisits" /> <br />
			
			<label>Enregistrer cette requète dans vos requètes favorites: </label>
			<input type="checkbox" name="saveQuery" /> <br />
			
			<input class="executeQuery" type="submit" value="lancer la requète" />
		</form>
	</fieldset>
</div>