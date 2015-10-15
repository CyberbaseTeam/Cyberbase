<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:main>
	<c:import url="/inc/modules/menu_usager.jsp" />
	<div id="usager_form">
		<span class="error"></span>
		<form method="post">
			<fieldset>
						<legend class="legende-form">
							<h2>Fiche usager</h2>
						</legend>
						<section>
						<div class="conteneur-form">
									<label for="civilite_usager">Civilité:</label>
     								<select name="civilite" id="civilite">
	     								<c:choose>
	     									<c:when test="${empty usager}">
	     										<option value="" disabled selected>Choix de la civilité</option>
	     									</c:when>
	     								</c:choose>
	     										<option value="M.">M.</option>
	     										<option value="Mme" >Mme</option>
    				 				</select>
						</div>
						
					
			</div>
			</fieldset>
		</form>

	</div>
	
</t:main>