<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div id="query">
	<form action="" method="post">	
		<fieldset>
			<legend>Poste :</legend>

			<label for="nom_poste" class="label">Nom du poste:</label>
			<input type="text" name="nom_poste"/><br />
			
			<div>
						<label for="classInput"> Salle : </label>
						<select id="salle_poste" name="class">
							<option value=""></option>
							<c:forEach items="${salles}" var="salle">
								<option value="${salle.id_salle}">${salle.nom_salle}</option>
							</c:forEach>
						</select>
			
			</div>

		<div class="boutons">
			<input class="enregistrer_btn" type="button" value="enregistrer le poste"/>
		</div>

	</form>

</div>