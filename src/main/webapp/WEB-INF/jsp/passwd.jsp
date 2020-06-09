<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="css/app.css" />" rel="stylesheet"
	type="text/css">
<title>Hello World!</title>
</head>
<body class="security-app">

	<div class="lc-block">
		<h1>
			Hello <b><c:out value="${pageContext.request.remoteUser}"></c:out></b>
		</h1>

		<div>
			<div>
				<div>
					<h3>Change password</h3>

					<form id="changePassword" name="changePassword">
						<label>Login: </label><input id="username" type="text"
							name="username" required /> <br> <label>New
							password: </label><input id="newPassword" type="password"
							name="newPassword" required /> <br> <label>Repeat
							password: </label><input id="secondNewPassword" type="password"
							name="secondNewPassword" required /> <br>
						<button class="button green" type="submit">Change</button>
						<a href="/home">Back to home</a>
					</form>

					<div>
						<div id="newsUploadError"></div>
						<div id="newsUploadSuccess"></div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="js/passwd.js"></script>
</body>
</html>
