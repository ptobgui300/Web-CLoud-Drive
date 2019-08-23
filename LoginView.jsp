<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<sql:setDataSource driver="com.mysql.jdbc.Driver"
	url="jdbc:mysql://cs3.calstatela.edu/cs3220stu76" user="cs3220stu76"
	password="yXyLUyer" />

<!DOCTYPE html>
<html lang=\"en\">
<head>

<meta charset="ISO-8859-1">
<title>Login</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">

</head>
<body>

	<script>
		function validate() {
			var userName = document.form.username.value;
			var password = document.form.password.value;

			if (username == null || userName == "") {
				alert("Username cannot be blank");
				return false;
			} else if (password == null || password == "") {
				alert("Password cannot be blank");
				return false;
			}
		}
	</script>

<body>
	<h3 class="display-3">Login</h3>

	<form action="Drive/Login" , method="post">

		<div class=form-group>
			<label for="username">User Name</label> <input class="form-control"
				type="login" name="username" id="username" placeholder="EnterUsername">
		</div>
		<div class="form-group">
			<label for="password">Password</label> <input class="form-control"
				type="password" name="password" id="password"
				placeholder="Enteryourpassword">
		</div>
		<div class="form-check">
			<button type="submit" class="btn btn-primary">Login</button>
		</div>
	</form>

	<br>
	<a href="Drive/Register" class="bootstrap-link">Sign up?</a>

</body>
</html>