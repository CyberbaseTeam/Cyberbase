<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:main>
	<c:import url="/inc/modules/menu_gestion_salles_postes.jsp" />
	<c:if test="${not empty message}">
		<div class="alert alert-success" role="alert">${message}</div>
	</c:if>
			<span id="titre-console">Console ${sitePro.nom_site}</span>
			<section >
				<c:forEach items="${sitePro.salles}" var="salle">
					<article class="colonne-postes">
					<table class="table table-striped">
						<thead>
							<th>${salle.nom_salle}</th>
						</thead>
						<tbody>
							<c:forEach items="${salle.postes}" var="poste">
										<tr>
											<td>${poste.nom_poste}</td> 
											<form method="post">
												<c:choose>
													<c:when test="${poste.disponibilite}">
														<td>Poste disponible</td>
														<td><a href="affecter_poste_form?id=${poste.id_poste}">Affecter
														poste</a></td>
													</c:when>
													<c:otherwise>
														<td>Poste indisponible</td>
														<td><input type="hidden" value="${poste.id_poste}"
														name="inputIdPoste" /></td>
														<td><button type="submit" name="libererPoste" class="btn btn-warning">Libérer poste</button></td>
												</c:otherwise>
											  </c:choose>
										    </form>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="3">
											<form method="post">
												<input type="hidden" value="${salle.id_salle}" name="inputIdSalle" />
												<button type="submit" name="editSalle" class="btn btn-primary">Modifier salle</button>
												<button type="submit" name="deleteSalle" class="btn btn-primary">Supprimer salle</button>
											</form>
										</td>
									</tr>
									<tr>
										<td>
											<form method="post">
												<input type="hidden" value="${salle.id_salle}" name="inputIdSalle" /> 
												<button type="submit" class="btn btn-danger" name="libererPostesSalle">Libérer tous les postes de la ${salle.nom_salle}</button>
											</form>
										</td>
									</tr>
						</tbody>
					</table>
						
							
							
						
					</article>
				</c:forEach>
			</section>
			<section class ="liberer-site">
				<article>
					<div>
					  <form method="post">
						<input type="hidden" value="${sitePro.id_site}" name="inputIdSite" />
						<button type="submit" name="libererPostesSite" class="btn btn-danger">Libérer tous les postes du site ${sitePro.nom_site}</button>
					  </form>
					</div>
				</article>
			</section>
</t:main>