<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>




			<c:forEach items="${professionnelList}" var="professionnel">
				<section> 
					<article id="pro1">
						${professionnel.nom_professionnel}
						${professionnel.prenom_professionnel}
						<p>
							Identifiant : ${professionnel.tech_id}
						</p>
						<p>
							Site : ${professionnel.site_reference.nom_site}
						</p>
						<p>
							Structure : ${professionnel.structure.nom_structure}
						</p>
						<form method="get">
							<input type="hidden" value="${professionnel.tech_id}"name="inputId" />
							<input id="modifier" type="submit" name="actionModifier" value="Modifier" /> 
							<input type="submit" value="Supprimer" name="actionSupprimer">
						</form>
					</article> 
				</section>
			</c:forEach>

		<section> 
			<article id="exporter"> 
				<input type="submit" name="Exporter" value="Exporter" />
			 </article> 
		</section>
</body>
</html>