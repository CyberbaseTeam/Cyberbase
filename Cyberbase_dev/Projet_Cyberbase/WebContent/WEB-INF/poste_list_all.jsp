<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:main>
	<c:import url="/inc/modules/menu_gestion_salles_postes.jsp" />
	<div>
		<c:forEach items="${sites}" var="site">
			<span>${site.nom_site}</span>
				<c:forEach items="${site.salles}" var="salle">
					<article class="colonne">
						<div>
							<h2 class="title-colonne">${salle.nom_salle}</h2>
							<div class="conteneur-list">
								<ul class="list-postes">
									<c:forEach items="${salle.postes}" var="poste">
										<li>- ${poste.nom_poste} : 
										  <c:choose>
											<c:when test="${poste.disponibilite}">
													<span>Poste disponible</span>
											</c:when>
											<c:otherwise>
													<span>Poste indisponible</span>
											</c:otherwise>
										  </c:choose>
										</li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</article>
				</c:forEach>
				<hr>
		</c:forEach>
	</div>
</t:main>