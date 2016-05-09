<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="meta/main.inc"%>
<title>SAMT</title>

<%@include file="includes/top.jsp"%>

</head>
<body>

	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.html">SAMT</a>
		</div>

		<!-- Top-Navigation --> <%@include file="partials/topnav.jsp"%>

		<!-- Side-Navigation --> <%@include file="partials/sidenav.jsp"%>


		</nav>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">

<!-- ########## CONTENT ############################################################# -->

					<h1>User</h1>
					<table data-toggle="table" class="table table-striped">
						<thead>
							<tr>
								<th>ID</th>
								<th>First Name</th>
								<th>Last Name</th>
								<th>DegreeCourse</th>
								<th>Email</th>
								<th>Password</th>
								<th>Profilepicture</th>
								<th>Action <a href="fill"><button type="button"
											class="btn btn-success">Fill List</button></a>
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${users}" var="user">
								<tr>
									<td>${user.id}</td>
									<td>${user.firstName}</td>
									<td>${user.lastName}</td>
									<td>${user.degreeCourse}</td>
									<td>${user.email}</td>
									<td>${user.password}</td>
									<td><img src="img/profiles/${user.profilePicture}"
										alt="${user.firstName} ${user.lastName}"></td>
									<td><a href="delete?id=${user.id}">Delete</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

<!-- ########## /CONTENT ############################################################ -->

				</div>
			</div>
		</div>
		<!-- /#wrapper -->

		<%@include file="includes/bottom.jsp"%>
</body>
</html>
