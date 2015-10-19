<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:main>
	<c:import url="/inc/modules/menu_gestion_salles_postes.jsp" />
	<c:if test="${not empty message}">
		<div class="alert alert-success" role="alert">${message}</div>
	</c:if>
	<div id="container-salle_list">
			<c:forEach items="${sitePro.salles}" var="salle">
				<form method="post">
					<article class="colonne-salles-edit">
						<fieldset>
							<legend >
								${salle.nom_salle}
							</legend>
								<input type="hidden" value="${salle.id_salle}" name="inputIdSalle"/>
								<button type="submit" name="editSalle" class="btn btn-primary">Modifier</button>
								<button type="submit" name="deleteSalle" class="btn btn-danger">Supprimer</button>
					</article>
				</form>
			</c:forEach>	
	</div>
</t:main>