<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" >${sectionName}</a>
		</div>
		<div>
			<ul class="nav navbar-nav">
				
				
				<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Mes requêtes <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<c:forEach items="${requeteList}" var="query" begin="0" varStatus='i'>
							<li><a href="statistiques?action=personalQuery&queryId=${query.id_requete}">${query.nom_requete}</a></li>
						</c:forEach>
					</ul>
				</li>
				<li class="liveStats"><a class="liveStatsLabel">VISITES DU JOUR <span class="badge">${currentStats.todaysVisits }</span></a></li>
 				<li class="liveStats"><a class="liveStatsLabel">VISITES DU MOIS <span class="badge">${currentStats.thisMonthsVisits }</span></a></li>
 				<li class="liveStats"><a class="liveStatsLabel">VISITES DE L'ANNÉE <span class="badge">${currentStats.thisYearsVisits }</span></a></li>
 				<li class="liveStats"><a class="liveStatsLabel">NOUVEAUX INSCRITS DU JOUR <span class="badge">${currentStats.todaysNewCommers }</span></a></li>
 				<li class="liveStats"><a class="liveStatsLabel">NOUVEAUX INSCRITS DU MOIS <span class="badge">${currentStats.thisMonthsNewCommers }</span></a></li>
 				<li class="liveStats"><a class="liveStatsLabel">NOUVEAUX INSCRITS DE L'ANNÉE <span class="badge">${currentStats.thisYearsNewCommers }</span></a></li>
 				<li class="liveStats"><a class="liveStatsLabel">INSCRITS DU SITE <span class="badge">${currentStats.mySiteUsers }</span></a></li>
  			</ul>	
		
		</div>
	</div>
</nav>



