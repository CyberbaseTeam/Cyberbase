<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="postes">
<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" >${section}</a>
		</div>
		<div>
			<ul class="nav navbar-nav">
				<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Gestion des salles <span class="caret"></span></a>
					<ul class="dropdown-menu">
							<li><a href="salle_form">Créer une salle</a></li>
							<li><a href="salle_list">Modifier une salle</a></li>
					</ul>
				</li>
				<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Gestion des postes <span class="caret"></span></a>
					<ul class="dropdown-menu">
							<li><a href="poste_list">Visualiser les postes disponibles de : ${sitePro.nom_site}</li>
							<li><a href="poste_list_all">Visualiser les postes disponibles d'un autre site</a></li>
							<li><a href="poste_form">Créer un poste</a></li>
					</ul>
				</li>
				<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Gestion des affectations <span class="caret"></span></a>
					<ul class="dropdown-menu">
							<li><a href="affecter_poste_form">Affecter un poste à un usager</a></li>
							<li><a href="affecter_poste_list">Liste des affectations</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</nav>
</div>
