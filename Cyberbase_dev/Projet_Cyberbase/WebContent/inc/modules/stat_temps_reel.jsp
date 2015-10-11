<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="current-stats">
	<c:choose>
		<c:when test="${not empty htmlResult }">
			
				
				<div id="query-result">${htmlResult}</div>
			
		</c:when>
		<c:otherwise>

			<h1>Site n°3</h1>
			<table>
				<tr>
					<th><strong></strong></th>
					<th>Visites</th>
					<th>Nouveaux inscrits</th>
					<th>Usagers</th>
				</tr>
				<tr>
					<th>Ce jour(date)</th>
					<td>8</td>
					<td>8</td>
					<td>8</td>
				</tr>
				<tr>
					<th>Ce mois(date)</th>
					<td>8</td>
					<td>8</td>
					<td>8</td>
				</tr>
				<tr>
					<th>Cette année(date)</th>
					<td>755</td>
					<td>8</td>
					<td>8</td>
				</tr>
			</table>


		</c:otherwise>
	</c:choose>
</div>
