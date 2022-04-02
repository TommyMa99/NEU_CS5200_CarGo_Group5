<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<link rel="shortcut icon" href="assets/logo.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New</title>
</head>
<body>

	<header>
	<div class="container">
		<header
			class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
		<a href="/Cargo/search"
			class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
			<img class="mb-4" src="assets/logo.svg" alt="" width="72" height="57">
			<span class="fs-4">CarGo</span>
		</a>

		<ul class="nav nav-pills">
			<li class="nav-item">
				<form action="/Cargo/new" method="GET">
					<button class="btn btn-outline-primary" type="submit">New</button>
				</form>
			</li>
		</ul>
		</header>
	</div>
	</header>

	<main> 

	<div class="album py-5 bg-light">
		<div class="container">
			<form action="/Cargo/signUpSeller" method="POST">
				<div class="row row-cols-2">
					<div class="col">
						<label for="InputVin" class="form-label">Email</label> 
						<input type="text" class="form-control" name="email" value="${email}">
					</div>
					<div class="col">
						<label for="InputYear" class="form-label">Password</label> 
						<input type="text" class="form-control" name="password" value="${password}">
					</div>
					<div class="col">
						<label for="InputMake" class="form-label">FirstName</label> 
						<input type="text" class="form-control" name="firstName" value="${firstName}">
					</div>
					<div class="col">
						<label for="InputModel" class="form-label">LastName</label> 
						<input type="text" class="form-control" name="lastName" value="${lastName}">
					</div>
					<div class="col">
						<label for="InputTrim" class="form-label">CompanyId</label> 
						<input type="number" class="form-control" name="companyId" value="${companyId}">
					</div>
					<div class="col">
						<label for="InputBody" class="form-label">ZIP</label> 
						<input type="number" class="form-control" name="zip" value="${zip}">
					</div>
					<div class="col">
						<label for="InputTransmission" class="form-label">Address</label> 
						<input type="text" class="form-control" name="address" value="${address}">
					</div>
					<div class="col">
						<label for="InputState" class="form-label">Introduction</label> 
						<input type="text" class="form-control" name="instroduction" value="${instroduction}">
					</div>
				</div>
				
				<button type="submit" class="btn btn-primary">Sign Up</button>
			</form>



		</div>
	</div>
	</div>

	</main>

	<div class="alert alert-success" role="alert">
	${messages.success}
	</div>

	<footer class="text-muted py-5">
	<div class="container">
		<p class="float-end mb-1">
			<a href="#">Back to top</a>
		</p>
	</div>
	</footer>


	<script src="../assets/dist/js/bootstrap.bundle.min.js"></script>


</body>
</html>
