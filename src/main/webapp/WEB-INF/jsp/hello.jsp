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
					<h3>Add news to site</h3>

					<form id="newsForm" name="newsForm">
						<label>Title: <input id="newsTitle" type="text"
							name="title" required />
						</label><br> <label>Text: <textarea id="newsText" name="text"
								required /></textarea></label> <br> <input id="newsImage" type="file"
							name="image" />
						<button class="button green" type="button">Add</button>
						<a href="/passwd">Change password</a>
					</form>

					<div class="status"></div>
				</div>
			</div>
		</div>

		<form action="/logout" method="post">
			<input type="submit" class="button red big" value="Sign Out" /> <input
				type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script src="js/main.js"></script>
</body>
</html>
