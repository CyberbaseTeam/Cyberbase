<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>


<section>
	<h2 id="titre-modif_admin">Ajouter un utilisateur</h2>

	<form method="post" action="add_or_update_pro">
		l

		<div class="row">
			<div class="col-sm-3"></div>
			<div class="col-sm-1">
				<label for="name">Nom: </label>
			</div>
			<div class="col-sm-2">
				<input type="text" name="name"
					value="${modifiedProfessional.nom_professionnel}" />
			</div>
			<div class="col-sm-1">
				<label for="firstName">Prénom: </label>
			</div>
			<div class="col-sm-2">
				<input type="text" name="firstName"
					value="${modifiedProfessional.prenom_professionnel}" />
			</div>
			<div class="col-sm-3"></div>
		</div>

		<div class="row">
			<div class="col-sm-3"></div>
			<div class="col-sm-1">
				<label for="site">Site de travail: </label>
			</div>
			<div class="col-sm-2">
				<SELECT name="site">
					<c:forEach items="${sites}" var="site">
						<OPTION value="${site.id_site}">${site.nom_site}</option>
					</c:forEach>
				</SELECT>
			</div>
			<div class="col-sm-1">
				<label for="structure">Structure: </label>
			</div>
			<div class="col-sm-2">
				<SELECT name="structure">
					<c:forEach items="${structures}" var="structure">
						<OPTION value="${structure.id_structure}">${structure.nom_structure}</option>
					</c:forEach>
				</SELECT>
			</div>
			<div class="col-sm-3"></div>
		</div>

		<div class="row">
			<div class="col-sm-3"></div>
			<div class="col-sm-1">
				<label for="password">Mot de passe: </label>
			</div>
			<div class="col-sm-2">
				<input type="password" name="password"
					value="${modifiedProfessional.password}" />
			</div>
			<div class="col-sm-1">
				<label for="passwordConfirm">Confirmation du mot de passe: </label>
			</div>
			<div class="col-sm-2">
				<input type="password" name="passwordConfirm"
					value="${modifiedProfessional.password}" />
			</div>
			<div class="col-sm-3"></div>
		</div>
		<div id="ajout-pro-button" class="row">
			<div class="col-sm-5"></div>
			<div class="col-sm-1">
				<input type="submit" class="btn btn-primary btn-md btn-block" name="retour" value="Retour" />
			</div>
			<div class="col-sm-1">
				<input type="submit" class="btn btn-primary btn-md btn-block" name="create" value="Valider" />
			</div>
		</div>
	</form>


</section>
