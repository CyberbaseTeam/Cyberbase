<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h3>Formulaires d'ajouts d'éléments à choisir dans les listes
	déroulantes du site</h3>

<div id="addStatus">${ addMessage }</div>
<div id="addSiteForm">
	<form method="post">

		<h4>Ajouter un site :</h4>

		<div>

			<label for="inputSite"" >Nom du site :</label> <input type="text"
				name="inputSite" />
		</div>
		<div>

			<label for="inputAdresse">Adresse du site :</label> <input
				type="text" name="inputAdresse" />
		</div>
		<div>

			<label for="inputVille">Ville du site :</label> <input type="text"
				name="inputVille" />
		</div>
		<div>

			<label for="inputCp">Code postal :</label> <input type="text"
				name="inputCp" />
		</div>



		<div>
			<input class="btn btn-primary " type="submit" value="Ajouter site"
				name="ajoutSite">
		</div>


	</form>

</div>

<div id="addQuartierForm">
	<form method="post">
		<div>

			<h4>Ajouter un quartier :</h4>
		</div>
		<div>

			<label for="inputQuartier">Nom du quartier :</label> <input
				type="text" name="inputQuartier" />
		</div>



		<div>
			<input type="submit" class="btn btn-primary "
				value="Ajouter quartier" name="ajoutQuartier">
		</div>

	</form>


</div>
<div id="addCspForm">
	<form method="post">
		<div>

			<h4>Ajouter un CSP :</h4>
		</div>
		<div>

			<label for="inputCsp">Nom du Csp :</label> <input type="text"
				name="inputCsp" />
		</div>



		<div>
			<input type="submit" class="btn btn-primary  " value="Ajouter csp"
				name="ajoutCsp">
		</div>

	</form>

</div>
<div id="adminSiteEnd">
</div>





