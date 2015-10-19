<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:main>
	<c:import url="/inc/modules/menu_gestion_salles_postes.jsp" />
		<section>
						<article>
							<div>
								<form method="post">
									<ul class="nav nav-pills">
									  <li role="presentation" <c:if test="${caseList == 2}">class=""</c:if> class="active"><a href="affecter_poste_list?page=1">Voir les affectations en cours</a></li>
									  <li role="presentation" <c:if test="${caseList == 2}">class="active"</c:if>><a href="affecter_poste_list?page=2">Voir les affectations passées</a></li>
									</ul>
								</form>
							</div>
						</article>
		</section>
					<c:choose>
						<c:when test="${empty affectations}">
							<section>
								<article>
									<span>Pas d'affectation(s)</span>
								</article>
							</section>
						</c:when>
						<c:otherwise>
								<section>
									<article>
										<table class="table">
											<thead>
												<th>Date de début</th>
												<th>Date de fin</th>
												<th>Identité usager</th>
												<th>Professionnel</th>
												<th>Motif</th>
												<th>Salle</th>
												<th>Poste</th>
											</thead>
											<tbody>
												<c:forEach items="${affectations}" var="aff">
													<c:choose>
														<c:when test="${aff.poste.salle.site.id_site == idSite}">
															<form method="post">
																<input type="hidden" value="${aff.id_affectation}" name="inputIdAffectation"/>
																<tr>
																	<td><fmt:formatDate value="${aff.date_debut_affectation}" pattern="HH:mm MM/dd/yyyy"/></td>
																	<td><fmt:formatDate value="${aff.date_fin_affectation}" pattern="HH:mm MM/dd/yyyy"/></td>
																	<td>${aff.usager.prenom_usager} ${aff.usager.nom_usager}</td>
																	<td>${aff.professionnel.prenom_professionnel} ${aff.professionnel.nom_professionnel}</td>
																	<td>${aff.demarche.nom_demarche}</td>
																	<td>${aff.poste.salle.nom_salle}</td>
																	<td>${aff.poste.nom_poste}</td>
																	<c:choose>
																		<c:when test="${caseList == 1}">
																			<td><button type="submit" name="edit" class="btn btn-primary">Modifier</button></td>
																			<td><button type="submit" name="delete" class="btn btn-danger">Supprimer</button></td>
																		</c:when>
																	</c:choose>
																</tr>
															</form>
														</c:when>
													</c:choose>
												</c:forEach>
											</tbody>
										</table>
									</article>
								</section>
							</c:otherwise>
						</c:choose> 		
</t:main>