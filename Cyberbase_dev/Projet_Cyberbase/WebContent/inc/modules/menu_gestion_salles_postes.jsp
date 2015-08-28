<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="side-menu">
			<nav id="menu-stat">
				<ul>
					<lh>Gérer les postes</lh>
					<li><a href="<c:url value="/poste_list"/>" style="text-transform:none;line-height:120%">Visualiser les postes disponibles de mon site : ${sitePro.nom_site}</a></li>
					<li><a href="" style="text-transform:none;line-height:120%">Visualiser les postes disponibles d'un autre site</a></li>
						<ul>
							
						</ul>
					<li><a href="" style="text-transform:none;">Affecter un poste à un usager</a></li>
					<li><a href="" style="text-transform:none;">Modifier un poste</a></li>
				</ul>
				<ul>
					<lh>Gérer les salles</lh>
					<li><a href="" style="text-transform:none;">Créer une salle</a></li>
					<li><a href="" style="text-transform:none;">Modifier une salle</a></li>
				</ul>
			</nav>
		</div>