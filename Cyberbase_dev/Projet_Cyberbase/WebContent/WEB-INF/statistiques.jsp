<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:main>

	<div id="statistics" class="row">
		
			<div class="col-md-3" id="side-menu">
				<c:import url="/inc/modules/menu_stat.jsp" />
			</div>
			
			<div class="col-md-9" id="stat_data">
				
					<c:import url="/inc/modules/stat_temps_reel.jsp" />
					<c:import url="/inc/modules/formulaire_requete.jsp" />
			
			</div>
		


	</div>
</t:main>