<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:main>
	<c:import url="/inc/modules/menu_gestion_salles_postes.jsp" />
	<div>
		<div>${sitePro.nom_site}</div>
		<form method="post">
		<section id="conteneur-colonnes">
			<c:forEach items="${sitePro.salles}" var="salle">
					<article class="colonne">
						<div >
							<h2 class="title-colonne">${salle.nom_salle}</h2>
							<div>
								<input type="hidden" value="${salle.id_salle}" name="inputIdSalle"/>
								<input type="submit" value="Libérer tous les postes de la ${salle.nom_salle}" name="libererPostesSalle">
							</div>
							<div class="buttons-bas">
								<a href="">
									<div class="modifier-button">
										<p>Modifier salle</p>
									</div>
								</a>
								<a href="">
									<div class="supprimer-button">
										<p>Supprimer salle</p>
									</div>
								</a>
							</div>
						</div>
					</article>
				 </c:forEach>	
				</section>
				<section id="liberer-postes-salle">
					<article>
						<div>
							<input type="hidden" value="${sitePro.id_site}" name="inputIdSite"/>
							<input type="submit" value="Libérer tous les postes du site ${sitePro.nom_site}" name="libererPostesSite">
						</div>
					</article>
				</section>
			</form>
	</div>
</t:main>