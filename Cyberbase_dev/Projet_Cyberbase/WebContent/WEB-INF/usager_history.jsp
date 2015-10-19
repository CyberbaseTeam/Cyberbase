<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:main>
<div id="usagers">
	<c:import url="/inc/modules/menu_usager.jsp" />
		<div id="container-usager_history">
		<fieldset>
						<legend class="legende-form">
							Historique de l'usager ${usager.prenom_usager} ${usager.nom_usager} 
						</legend>
		
					<c:choose>
						<c:when test="${empty affectations}">
							<section>
								<article>
									<span>Pas d'historique pour cet usager</span>
								</article>
							</section>
						</c:when>
						<c:otherwise>
								<section>
									<article>
										<table class="table table-striped">
											<thead>
												<th>Date de début</th>
												<th>Date de fin</th>
												<th>Professionnel</th>
												<th>Motif</th>
												<th>Salle</th>
												<th>Poste</th>
											</thead>
											<tbody>
												<c:forEach items="${affectations}" var="aff">
													
														
															
																
																<tr>
																	<td><fmt:formatDate value="${aff.date_debut_affectation}" pattern="dd/MM/yyyy HH:mm"/></td>
																	<td><fmt:formatDate value="${aff.date_fin_affectation}" pattern="dd/MM/yyyy HH:mm"/></td>
																	<td>${aff.professionnel.prenom_professionnel} ${aff.professionnel.nom_professionnel}</td>
																	<td>${aff.demarche.nom_demarche}</td>
																	<td>${aff.poste.salle.nom_salle}</td>
																	<td>${aff.poste.nom_poste}</td>
																</tr>
															
														
													
												</c:forEach>
											</tbody>
										</table>
									</article>
								</section>
							</c:otherwise>
						</c:choose> 	
					</fieldset>
				</div>
			</div>	
</t:main>