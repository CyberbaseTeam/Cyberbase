<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:main>
	<c:import url="/inc/modules/menu_usager.jsp" />
	<div id="container-exclure_form">
		<section>
				<form class="affecter-form" method="post">
					<fieldset>
						<legend class="legende-form">
							Formulaire d'exclusion pour l'usager : ${usager.prenom_usager} ${usager.nom_usager}
						</legend>
						<section>
							<label for="civilite">Statut :</label> 
							<label class="radio-inline">
  								<input type="radio" id="statut" name="statut" value="définitive" /> Définitive
							</label>
							<label class="radio-inline">
  								<input type="radio" id="statut" name="statut" value="temporaire" /> Temporaire
							</label>
							<label for="choixNouveauClient">Nouveau client ? <span class="requis">*</span></label>
                        <input type="radio" id="choixNouveauClient" name="choixNouveauClient" value="nouveauClient" /> Oui
                        <input type="radio" id="choixNouveauClient" name="choixNouveauClient" value="ancienClient" /> Non

                   		<div id="temporaire">
	                       <input type="date" name="dateExclusion">
                    	</div>
							<br><br>
							<label for="nomUsager">Nom :</label> 
							<input type="text" name="nomUsager" value="${usager.nom_usager}" size="20" maxlength="50" class="form-control" /> 
							<br>
							<label for="prenomUsager">Prénom :</label> 
							<input type="text" name="prenomUsager" value="${usager.prenom_usager}" size="20" maxlength="50" class="form-control" /> 
							<br>
							<label for="cpUsager">Code postal :</label> 
							<input type="text" name="cpUsager" value="${usager.code_postal_usager}" size="20" maxlength="50" class="form-control" /> 
							<br>
							<label for="villeUsager">Ville :</label>
							<input type="text" name="villeUsager" value="${usager.ville_usager}" size="20" maxlength="50" class="form-control" />
							<br>
							<label for="adresseUsager">Adresse :</label>
							<input type="text" name="adresseUsager" value="${usager.adresse_usager}" size="20" maxlength="50" class="form-control" />
							<br>
							<label for="emailUsager">Email :</label>
							<input type="text" name="emailUsager" value="${usager.email_usager}" size="20" maxlength="50" class="form-control" />
							<br>
							<label for="accompagnement">Accompagnement:</label>
									<label class="radio-inline">
		  								<input type="radio" id="inlineCheckbox1" name="accompagnement" value="true" <c:if test="${usager.accompagnement}"> checked="checked"</c:if> /> Oui
									</label>
									<label class="radio-inline">
		  								<input type="radio" id="inlineCheckbox1" name="accompagnement" value="false" <c:if test="${!usager.accompagnement}"> checked="checked"</c:if> /> Non
									</label>
							<br><br>
							<label for="siteUsager">Site d'inscription :</label>
							<select class="form-control" name="siteUsager" id="siteUsager">
								<c:forEach items="${sites}" var="site">
									<c:choose>
										<c:when test="${site.id_site == usager.site_inscription.id_site}">
											<option value="${site.id_site}" selected>${site.nom_site}</option>
										</c:when>
										<c:otherwise>
											<option value="${site.id_site}" >${site.nom_site}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
							<br>
							<label for="quartierUsager">Quartier :</label>
							<select class="form-control" name="quartierUsager" id="quartierUsager">
								<c:forEach items="${quartiers}" var="quartier">
									<c:choose>
										<c:when test="${quartier.id_quartier == usager.quartier.id_quartier}">
											<option value="${quartier.id_quartier}" selected>${quartier.nom_quartier}</option>
										</c:when>
										<c:otherwise>
											<option value="${quartier.id_quartier}" >${quartier.nom_quartier}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
							<br>
							<label for="cspUsager">CSP :</label>
							<select class="form-control" name="cspUsager" id="cspUsager">
								<c:forEach items="${csps}" var="csp">
									<c:choose>
										<c:when test="${csp.id_csp == usager.csp.id_csp}">
											<option value="${csp.id_csp}" selected>${csp.libelle_csp}</option>
										</c:when>
										<c:otherwise>
											<option value="${csp.id_csp}" >${csp.libelle_csp}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
							<br>
							<label for="formationUsager">Formation :</label>
							<select class="form-control" name="formationUsager" id="formationUsager">
								<c:forEach items="${formations}" var="formation">
									<c:choose>
										<c:when test="${formation.id_formation == usager.niveau_formation.id_formation}">
											<option value="${formation.id_formation}" selected>${formation.nom_formation}</option>
										</c:when>
										<c:otherwise>
											<option value="${formation.id_formation}" >${formation.nom_formation}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</section>
						<section>
							<br>
							<input type="hidden" name="techIdUsager" value="${usager.tech_id}" />
							<input type="hidden" name="idUsager" value="${usager.id_usager}" />
							<input type="hidden" name="dateInscriptionUsager" value="${usager.date_inscription}" />
							<input type="hidden" name="dateUsager" value="${usager.date_naissance_usager}" />
							<button type="submit" name="edit"  class="btn btn-primary">Valider</button>
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
</t:main>