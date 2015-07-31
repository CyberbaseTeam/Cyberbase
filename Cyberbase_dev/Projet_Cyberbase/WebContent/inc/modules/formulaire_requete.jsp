<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div id="query">
	<form action="" method="post">
		<div>
			<label for="panel_recherche" class="label">Chercher parmi:</label>
			<input type="radio" name="panel_recherche" value="tous" checked>Tous les usagers
			<input type="radio" name="panel_recherche" value="nouveaux"> Les nouveaux usagers
		</div><br />	
				
		<fieldset>
			<legend>Critères de recherche</legend>

			<div id="sexe"><label for="sexe" class="label">Sexe: </label>
				<input type="checkbox" name="sexe" value="M"> Masculin
				<input type="checkbox" name="sexe" value="F"> Féminin</br />
			</div>

			<label for="Ville" class="label">Ville:  </label><input type="text" name="ville"/><br />
			<label for="quartier" class="label">Quartier: </label>
			<select name="quartier"><br />
			<c:forEach items="${listeQuartier}" var="quartier" >
				<option value="">${quartier.nom_quartier}</option>
			</c:forEach>
			</select><br />

			<label for="formation" class="label">Niveau de formation: </label>
			<select name="formation"><br />
				<c:forEach items="${listeFormation}" var="formation" >
					<option value="">${formation.nom_formation}</option>
				</c:forEach>
			</select>
			<br />

			<label for="objet_visite" class="label">Objet de la visite: </label>
			<select name="objet_visite"><br />
				<c:forEach items="${listeDemarche}" var="objet" >
					<option value="">${demarche.nom_formation}</option>
				</c:forEach>
			</select>
			<br />
						
			<label for="visit_min" class="label">Nombre de venues entre </label>
			<input type="text" name="visit_min" >
			<label class="label_centre" for="visit_max"> et </label>
			<input type="text" name="visit_max" ><br />
						
			<label for="date_debut" class="label">Venues entre </label>
			<input type="date"	name="date_debut">
			<label for="date_fin" class="label_centre"> et </label>
			<input type="date" name="date_fin"><br />

						

			<label>Afficher le nombre de visite des usagers recherchés: </label><input type="checkbox" name="affichage_visites"/>
		</fieldset>
		<br />

		<div class="boutons">

			<input class="enregistrer_btn" type="button" value="enregistrer la requète"/>
			<input class="valider_btn" type="submit" value="lancer la requète"/>
		</div>

	</form>

</div>