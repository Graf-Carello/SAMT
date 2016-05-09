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

					<h1>Users</h1>
					<a href="fill" style="position: absolute; right: 20px; top: 10px;"><button
							type="button" class="btn btn-success">Fill List</button></a> <br />

					<c:forEach items="${users}" var="user">
						<table data-toggle="table" class="table table-striped users">
							<tbody>
								<tr>
									<td rowspan="6" class="profilepic"><img
										src="img/profiles/${user.profilePicture}"
										alt="${user.firstName} ${user.lastName}"></td>
								</tr>
								<tr>
									<td class="name"><h2>${user.firstName} ${user.lastName}</h2></td>
									<td class="action"><a
										href="delete?id=${user.id}"><button type="button"
												class="btn btn-danger">Delete User #${user.id}</button></a></td>
								</tr>
								<tr>
									<td colspan="2" class="tabledata"><b>Degree Course:</b>
										${user.degreeCourse}</td>
								</tr>
								<tr>
									<td colspan="2" class="tabledata"><b>E-Mail:</b>
										${user.email}</td>
								</tr>
								<tr>
									<td colspan="2" class="tabledata"><b>Password:</b>
										${user.password}</td>
								</tr>
								<tr>
									<td colspan="2"></td>
								</tr>
							</tbody>
						</table>
					</c:forEach>


					<!-- ########## /CONTENT ############################################################ -->

				</div>
			</div>
		</div>
		<!-- /#wrapper -->

		<%@include file="includes/bottom.jsp"%>
</body>
</html>
