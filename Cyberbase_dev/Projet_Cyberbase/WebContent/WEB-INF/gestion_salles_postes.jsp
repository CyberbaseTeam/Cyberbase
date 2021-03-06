<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:main>
	<c:import url="/inc/modules/menu_gestion_salles_postes.jsp" />
	<div>
		<div>${sitePro.nom_site}</div>
		<c:forEach items="${sites}" var="site">
				<div>
					<div>${site.nom_site}</div>
						<c:forEach items="${site.salles}" var="salle">
									<span><c:out value="${salle.nom_salle }"></c:out></span>
						</c:forEach>
				</div>
			</c:forEach>
	</div>
</t:main>