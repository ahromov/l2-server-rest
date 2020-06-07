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
		<form action="/logout" method="post">
			<input type="submit" class="button red big" value="Sign Out" /> <input
				type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>

		<div>
			<div>
				<div>
					<h3>Add news to site</h3>

					<form id="newsForm" name="newsForm">
						<label>Title: </label><input id="newsTitle" type="text"
							name="title" required /> <br> <label>Text: </label>
						<textarea id="newsText" name="text" required /></textarea>
						<br> <input id="newsImage" type="file" name="image" required />
						<button type="submit">Add</button>
					</form>

					<div>
						<div id="newsUploadError"></div>
						<div id="newsUploadSuccess"></div>
					</div>
				</div>
			</div>
		</div>

		<script src="js/main.js"></script>
	</div>

</body>
</html>
