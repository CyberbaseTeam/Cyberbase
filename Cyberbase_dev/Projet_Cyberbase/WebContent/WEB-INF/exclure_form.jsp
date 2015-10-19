<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:main>
<div id="usagers">
	<c:import url="/inc/modules/menu_usager.jsp" />
	<div id="container-exclure_form">
		<section>
				<form class="affecter-form" method="post">
					<fieldset>
						<legend class="legende-form">
							Formulaire d'exclusion pour l'usager : ${usager.prenom_usager} ${usager.nom_usager}
						</legend>
						<section>
							<label for="statut">Statut :</label> 
							<label class="radio-inline">
  								<input type="radio" id="statut" name="statut" value="définitive" /> Définitive
							</label>
							<label class="radio-inline">
  								<input type="radio" id="statut" name="statut" value="temporaire" /> Temporaire
							</label>
	                   		<div id="temporaire">
	                   			<label for="dateExclusion">Expulser l'usager jusqu'à la date :</label>
		                       <input type="date" name="dateExclusion">
	                    	</div>
							<br><br>
						</section>
						<section>
							<br>
							<input type="hidden" name="idUsager" value="${usager.id_usager}" />
							<button type="submit" name="create"  class="btn btn-primary">Valider</button>
						</section>
			</fieldset>
			</form>
	</section>
	</div>
	<script>
        	jQuery(document).ready(function(){
        		/* 1 - Au lancement de la page, on cache le bloc d'éléments du formulaire correspondant aux clients existants */
        		$("div#temporaire").hide();
        		/* 2 - Au clic sur un des deux boutons radio "choixNouveauClient", on affiche le bloc d'éléments correspondant (nouveau ou ancien client) */
                jQuery('input[name=statut]:radio').click(function(){
                	$("div#temporaire").hide();
                	$("div#définitive").hide();
                    var divId = jQuery(this).val();
                    $("div#"+divId).show();
                });
            });
        </script>
 </div>
</t:main>