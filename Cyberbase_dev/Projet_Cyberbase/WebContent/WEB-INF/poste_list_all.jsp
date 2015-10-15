<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:main>
	<c:import url="/inc/modules/menu_gestion_salles_postes.jsp" />
	<div>
		<c:forEach items="${sites}" var="site">
			<fieldset>
					<legend >
						${site.nom_site}
					</legend>
				<c:forEach items="${site.salles}" var="salle">
					<article class="colonne-postes-all">
						<table class="table table-striped">
						<thead>
							<th>${salle.nom_salle}</th>
						</thead>
						<tbody>
									<c:forEach items="${salle.postes}" var="poste">
									<tr>
										<td>${poste.nom_poste}</td>
										  <c:choose>
											<c:when test="${poste.disponibilite}">
													<td>Poste disponible</td>
											</c:when>
											<c:otherwise>
													<td>Poste indisponible</td>
											</c:otherwise>
										  </c:choose>
									</tr>
									</c:forEach>
						</tbody>
						</table>
					</article>
				</c:forEach>
				</fieldset>
				<hr>
		</c:forEach>
	</div>
</t:main>