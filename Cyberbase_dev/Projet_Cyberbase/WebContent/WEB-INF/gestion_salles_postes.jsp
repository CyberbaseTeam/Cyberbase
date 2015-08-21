<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:main>
	<c:import url="/inc/modules/menu_gestion_salles_postes.jsp" />
	<div id="statistics">
		<c:forEach items="${sites}" var="site">
				<div class="row">
					<div class="small-6 large-4 columns">${site.nom_site}</div>
				</div>
			</c:forEach>
	</div>
</t:main>