<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   <h3>Formaulaires d'ajouts d'éléments à choisir dans les listes déroulantes du site</h3> 
    
    
    <form method="post">
		<h4>Ajouter un site :</h4>
		<label for="inputSite">Nom du site :</label>
		<input type="text" name="inputSite" />
		<label for="inputAdresse">Adresse du site :</label>
		<input type="text" name="inputAdresse" />
		<label for="inputVille">Ville du site :</label>
		<input type="text" name="inputVille" />		
		<label for="inputCP">Code postal :</label>
		<input type="text" name="inputCP" />
		<input type="submit" value="Ajouter" name="ajoutSite">				
	</form>
	
	<form method="post">
		<h4>Ajouter un Quartier :</h4>
		<label for="inputQuartier">Nom du quartier :</label><input type="text" name="inputQuartier" />
		<input type="submit" value="Ajouter" name="ajoutQuartier">
	</form>
	<form method="post">
		<h4>Ajouter un CSP :</h4>
		<label for="inputCsp">Nom CSP :</label>
		<input type="text" name="inputCSP" />
		<input type="submit" value="Ajouter" name="ajoutCSP">
	</form>
	
	<div >
		<form method="post">
			<input type="submit" class="btn btn-primary btn-md btn-block" name="retour" value="Retour" />
		</form>
	</div>