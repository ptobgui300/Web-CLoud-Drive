<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cloud Drive</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>
<style>
	.left {
		float: left;
	}
	.right {
		float: right;
	}
	.center {
		padding: 5%;
	}
	.margin {
		margin: 5pt;
	}
</style>
</head>
<body>
	<nav class="navbar bg-dark navbar-dark">
		<h1 class="left text-light"> Cloud Drive </h1>
		<span class="right">
			<a href="Drive/Upload" data-toggle="tooltip" title="Upload"><i class='fas fa-cloud-upload-alt margin'></i></a>
			<a href="Drive/LogOut" class="margin">Logout</a>
		</span>
	</nav>
	<div class="container">
		<c:if test="${fn:length(fileUploads) == 0 }">
			<div class="center">
				<div class="alert alert-warning">
					<strong>No file found!</strong>
				</div>
			</div>	
		</c:if>
		<c:if test="${fn:length(fileUploads) > 0 }">
			<div class="center">
				<table class="table">
					<tr>
						<th> Files </th>
					</tr>
					<c:forEach items="${fileUploads}" var="file" varStatus="status">
						<tr>
							<td>
								${file.fileName}
								<span class="right"> 
									<a href="Drive/Rename?name=${file.fileName}" data-toggle="tooltip" title="Rename"><i class='fas fa-pen margin'></i></a>
									<a href="Drive/Download?fileName=${file.fileName}" data-toggle="tooltip" title="Download"><i class='fas fa-cloud-download-alt margin'></i></a>
									<a href="Drive/Remove?fileName=${file.fileName}" data-toggle="tooltip" title="Delete"><i class='fas fa-trash margin'></i></a>
								</span>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</c:if>
	</div>
</body>
</html>