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
						<label>Login: <input id="username" type="text"
							name="username" required /></label> <br> <label>Old
							password:<input id="password" type="password" name="password"
							required /> <br>
						</label> New password: <input id="newPassword" type="password"
							name="newPassword" required /></label> <br> <label>Repeat
							password:<input id="newRepeatedPassword" type="password"
							name="newRepeatedPassword" required />
						</label> <br>
						<button class="button green" type="button">Change</button>
						<a href="/home">Back to home</a>
					</form>

					<div>
						<div class="responseStatus" id="newsUploadError"></div>
						<div class="responseStatus" id="newsUploadSuccess"></div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script src="js/passwd.js"></script>
</body>
</html>
