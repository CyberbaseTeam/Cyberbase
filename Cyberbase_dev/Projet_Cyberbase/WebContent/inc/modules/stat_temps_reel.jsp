<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="current-stats">
	<c:choose>
		<c:when test="${not empty htmlResult }">
			
				
				<div id="query-result">${htmlResult}</div>
			
		</c:when>
		<c:otherwise>

		</c:otherwise>
	</c:choose>
</div>
