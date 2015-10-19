<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:main>
<div id="usagers">
	<c:import url="/inc/modules/menu_usager.jsp" />
	<div id="container-poste_form">
		<section>
				<form class="affecter-form" method="post">
					<fieldset>
						<legend class="legende-form">
							Informations usager
						</legend>
						<section>
							<label for="civilite">Civilité :</label> 
							<label class="radio-inline">
  								<input type="radio" id="inlineCheckbox1" name="civilite" value="M." <c:if test="${usager.civilite_usager == 'M.'}"> checked="checked"</c:if> /> M.
							</label>
							<label class="radio-inline">
  								<input type="radio" id="inlineCheckbox2" name="civilite" value="Mme" <c:if test="${usager.civilite_usager == 'Mme'}"> checked="checked"</c:if> /> Mme
							</label>
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
							<br><br>
							
							<c:set var="count" value="0" scope="page" />
						<c:forEach items="${exclusions}" var="exclusion">
							<c:if test="${usager.id_usager == exclusion.usager.id_usager}">
								<c:set var="count" value="${count + 1}" scope="page"/>
								<c:set var="statut" value="${exclusion.statut_exclusion}" scope="page"/>
								<c:set var="date" value="${exclusion.date_fin}" scope="page"/>
								<input type="hidden" name="inputIdExclu" value="${exclusion.id_exclusion}" />
							</c:if>
						</c:forEach>
						<c:choose>
							<c:when test="${count > 0}">
								<label for="siteUsager">Exclusion :</label>
								<c:choose>
									<c:when test="${statut eq 'temporaire'}">
										
											<div class="alert alert-warning" role="alert">Exclu jusqu'au <fmt:formatDate value="${date}" pattern="dd/MM/yyyy"/></div>
										
									</c:when>
									<c:otherwise>
										
											<div class="alert alert-danger" role="alert">Exclu définitivement</div>
										
									</c:otherwise>
									
								</c:choose>
								<button type="submit" name="deleteExclu"  class="btn btn-danger">Supprimer exclusion</button>
							</c:when>
								
							</c:choose>
							
						</section>
						<section>
							<br>
							<input type="hidden" name="techIdUsager" value="${usager.tech_id}" />
							<input type="hidden" name="idUsager" value="${usager.id_usager}" />
							<input type="hidden" name="dateInscriptionUsager" value="${usager.date_inscription}" />
							<input type="hidden" name="dateUsager" value="${usager.date_naissance_usager}" />
							<button type="submit" name="edit"  class="btn btn-primary">Valider modifications</button>
						</section>
			</fieldset>
			</form>
	</section>
	</div>
</div>
</t:main>