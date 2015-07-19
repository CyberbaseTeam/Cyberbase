<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:main>
	<c:import url="/inc/modules/menu_stat.jsp" />
	<div id="statistics">
		<c:import url="/inc/modules/stat_temps_reel.jsp" />
		<c:import url="/inc/modules/formulaire_requete.jsp" />
	</div>
</t:main>