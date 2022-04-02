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
	
	
	 <div class="row justify-content-center">
	   <div class="col-2">
			<form action="/Cargo/signUpSeller" method="GET">
				<button type="submit" class="btn btn-outline-primary"><h3>Become a Seller</h2></button>
	   		</form>
		 </div>
	 </div>
	 <br><br>
	 
	 <div class="row justify-content-center">
	   <div class="col-2">
			<form action="/Cargo/signUpBuyer" method="GET">
				<button type="submit" class="btn btn-outline-primary"><h3>Become a Buyer</h3></button>
	   		</form>
	   	 
	   	  </div>
	 </div>
	


	<script src="../assets/dist/js/bootstrap.bundle.min.js"></script>


</body>
</html>
