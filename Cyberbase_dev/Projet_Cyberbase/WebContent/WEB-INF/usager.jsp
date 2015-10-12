<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:main>
	<div id="usagers">
		<c:import url="/inc/modules/menu_usager.jsp" />
	
	
		<c:import url="/inc/modules/liste_usager.jsp" />	
	</div>
</t:main>