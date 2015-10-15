<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:main>

	<div id="admin_list">
		<c:import url="/inc/modules/menu_admin.jsp" />

		<div id="siteAdmin">
			<c:import url="/inc/modules/administrationSite.jsp" />
			
		</div>
	</div>
</t:main>