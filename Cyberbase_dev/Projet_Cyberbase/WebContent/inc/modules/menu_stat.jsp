<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

			
				<p><a href="statistiques">Statistiques temps réel</a></p>
				<ul>
					<lh></lh>
					<li><a href=""><c:out value="${userSession.site}"></c:out></a></li>
					<c:forEach items="${siteList}" var="site" begin="0" varStatus='i'>
						<c:if test="${site.nom_site} != ${siteList}">
							<li><a href="">${site.nom_site}</a></li>
						</c:if>
					</c:forEach>
				</ul>

				
				<ul>
					<c:forEach items="${siteList}" var="site" begin="0" varStatus='i'>
						<c:if test="${site.nom_site} != ${siteList}">
							<li><a href="">${site.nom_site}</a></li>
						</c:if>
					</c:forEach>
				</ul><br />
				
					<p>Mes requètes</p>
				<ul>
					<lh></lh>
					<c:forEach items="${requeteList}" var="query" begin="0" varStatus='i'>
						<li><a href="statistiques?action=personalQuery&queryId=${query.id_requete}">${query.nom_requete}</a></li>	
					</c:forEach>
				</ul>

			
		