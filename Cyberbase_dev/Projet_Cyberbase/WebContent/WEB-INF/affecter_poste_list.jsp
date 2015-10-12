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
									<input type="submit" name ="onGoing" value="Voir les affectations en cours de mon site">
									<a href="affecter_poste_list?page=past">Voir les affectations passées de mon site</a>
									<input type="submit" name ="onGoingAll" value="Voir toutes les affectations en cours">
									<input type="submit" name ="pastAll" value="Voir toutes les affectations passées">
									${pq}
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
										<table>
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
															<td><input type="submit" name="edit" value="Modifier"/></td>
															<td><input type="submit" name="delete" value="Supprimer"/></td>
														</tr>
													</form>
												</c:forEach>
											</tbody>
										</table>
									</article>
								</section>
							</c:otherwise>
						</c:choose> 		
</t:main>