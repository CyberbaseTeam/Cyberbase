<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:main>
	<c:import url="/inc/modules/menu_gestion_salles_postes.jsp" />
	<div>
		<div>${sitePro.nom_site}</div>
		<section id="conteneur-colonnes">
			<c:forEach items="${sitePro.salles}" var="salle">
				<form method="post">
					<article class="colonne">
						<div >
							<h2 class="title-colonne">${salle.nom_salle}</h2>
							<div>
								<input type="hidden" value="${salle.id_salle}" name="idSalle"/>
							</div>
							<div class="buttons-bas">
								<input type="submit" name="editSalle" value="Modifier"/>
								<input type="submit" name="deleteSalle" value="Supprimer"/>
							</div>
						</div>
					</article>
				   </form>
				 </c:forEach>	
				</section>
	</div>
</t:main>