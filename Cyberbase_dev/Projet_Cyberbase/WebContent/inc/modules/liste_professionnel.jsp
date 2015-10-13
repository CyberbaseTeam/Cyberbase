<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

		<table class="table">
							<tr>
								<th>Identifiant</th>								
								<th>Nom</th>
								<th>Prenom</th>								
								<th>Site </th>
								<th>Structure</th>
								
							</tr>


			<c:forEach items="${professionnelList}" var="professionnel" varStatus="loop">
			

				<c:choose>
					<c:when test="${loop.index%2==0}">
						<tr>
					</c:when>
					<c:otherwise>
						<tr class="info">
					</c:otherwise>
				</c:choose>	
				<td>${professionnel.tech_id}</td>		
				<td>${professionnel.nom_professionnel}</td>			
				<td>${professionnel.prenom_professionnel}</td>				
				<td>${professionnel.site_reference.nom_site}</td>
				<td>${professionnel.structure.nom_structure}</td>
				<td>		
						<form method="post" action="administration">
							<input type="hidden" value="${professionnel.tech_id}"name="inputId" />
							<input id="modifier" type="submit" name="actionModifier" value="Modifier" /> 
							<input type="submit" value="Supprimer" name="actionSupprimer">
						</form>
				</td>
				</c:forEach>	

		</table>
			<article id="exporter"> 
				<input type="submit" name="Exporter" value="Exporter" />
			 </article> 
		
