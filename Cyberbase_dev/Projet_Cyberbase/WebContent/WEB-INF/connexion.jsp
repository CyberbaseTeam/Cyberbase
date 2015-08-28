<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>page de connexion</p>
</body>
	<div id="content">	
		<form id="connexion" method="POST">
			<fieldset id="fieldset1">
			<legend>Connexion</legend>
			
			<label class="labelconnexion" for="techId">ID utilisateur :</label>
			<input  type="text" name="techId"/><span class="error_msg"></span>
			
			<label class="labelconnexion" for="psw">Mot de passe :</label>
			<input class="inputconnexion" type="password" name="psw"/>	<span class="error_msg"></span>
		
			<input id="envoyer"type="submit" value="Se connecter">
		
			</fieldset>
		</form>
	
		
	</div>
</html>