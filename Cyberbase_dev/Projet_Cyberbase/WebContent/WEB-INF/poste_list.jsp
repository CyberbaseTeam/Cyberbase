<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:main>
	<c:import url="/inc/modules/menu_gestion_salles_postes.jsp" />
	<div>
		<form method="post">
			<section id="conteneur-colonnes">
				<c:forEach items="${sitePro.salles}" var="salle">
					<article class="colonne">
						<div>
							<h2 class="title-colonne">${salle.nom_salle}</h2>
							<div class="conteneur-list">
								<ul class="list-postes">
									<c:forEach items="${salle.postes}" var="poste">
										<li>- ${poste.nom_poste} : <c:choose>
												<c:when test="${poste.disponibilite}">
													<span>Poste disponible</span>
													<a href="affecter_poste?id=${poste.id_poste}">Affecter
														poste</a>
												</c:when>
												<c:otherwise>
													<span>Poste indisponible</span>
													<input type="hidden" value="${poste.id_poste}"
														name="inputIdPoste" />
													<input type="submit" value="Libérer poste"
														name="libererPoste">
												</c:otherwise>
											</c:choose>
										</li>
									</c:forEach>
								</ul>
							</div>
							<div>
								<input type="hidden" value="${salle.id_salle}"
									name="inputIdSalle" /> <input type="submit"
									value="Libérer tous les postes de la ${salle.nom_salle}" ®
									name="libererPostesSalle">
							</div>
							<div class="buttons-bas">
								<form method="post">
									<input type="hidden" value="${salle.id_salle}" name="inputIdSalle">
									<input type="submit"
										name="editSalle" value="Modifier salle" /><input
										type="submit" name="deleteSalle" value="Supprimer salle" />
								</form>
							</div>
						</div>
					</article>
				</c:forEach>
			</section>
			<section id="liberer-postes-salle">
				<article>
					<div>
						<input type="hidden" value="${sitePro.id_site}" name="inputIdSite" />
						<input type="submit"
							value="Libérer tous les postes du site ${sitePro.nom_site}"
							name="libererPostesSite">
					</div>
				</article>
			</section>
		</form>
	</div>
</t:main>