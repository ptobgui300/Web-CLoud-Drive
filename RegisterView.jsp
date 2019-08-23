<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
    <div class="container">
    <h1 class="display-3">Register</h1>
    <c:if test="${!empty param.error}">
        <c:if test="${param.error == 0}">
            <p class="text-danger">invalid input: empty field</p>
        </c:if>
        <c:if test="${param.error == 1}">
            <p class="text-danger">invalid input: username already exists</p>
        </c:if>
        <c:if test="${param.error == 2}">
            <p class="text-success">good in the hood</p>
        </c:if>
    </c:if>
    
    <form action="Register" , method="post">
        <div class=form-group>
            <label for="username">User Name</label>
            <input class="form-control" type="text" name="username" id="username" placeholder="Username">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input class="form-control" type="text" name="password" id="password" placeholder="Password">
        </div>
        <div class="form-check">
            <input type="submit" class="btn btn-primary" value="Register"/>
        </div>
    </form>
</div>
</body>
</html>