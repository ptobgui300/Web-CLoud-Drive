<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<title>Rename</title>
</head>
<body>
<div class="container">
    <h1 class="display-3">Rename</h1>
    <form action="Rename?name=${param.name}", method="post">
        <div class=form-group>
            <label for="name">Name</label>
            <input class="form-control" type="text" name="newName" id="name" placeholder="${param.name}">
        </div>
        <div class="form-check">
            <input type="submit" class="btn btn-primary" value="Enter"/>
        </div>
    </form>
</div>
</body>
</html>