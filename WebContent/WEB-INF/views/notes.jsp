<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="meta/main.inc"%>
<title>Your Notes | SAMT</title>

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
			<a class="navbar-brand" href="/">SAMT</a>
		</div>

		<!-- Top-Navigation --> <%@include file="partials/topnav.jsp"%>

		<!-- Side-Navigation --> <%@include file="partials/sidenav.jsp"%>


		</nav>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">

					<!-- ########## CONTENT ############################################################# -->

					<h1>Your notes</h1>

					<nav class="navbar navbar-default">
					<div class="container-fluid">

						 <a href="<c:url value="/notes/add" />">
							<button type="button" class="btn btn-default success">Add</button>
						</a>

					</div>
					</nav>
					<div id="notewrapper">
						<c:forEach items="${notes}" var="note">
							<div class="note clearfix">
								<h3>${note.name}</h3>
								<a class="edit" href="<c:url value="/notes/edit?id=${note.id}" />"><i class="fa fa-wrench fa-fw"></i></a>
								<a class="delete" href="<c:url value="/notes/delete?id=${note.id}" />"><i class="fa fa-ban fa-fw"></i></a>
								<textarea readonly>${note.content}</textarea>
							</div>
						</c:forEach>
					</div>

					<!-- ########## /CONTENT ############################################################ -->

				</div>
			</div>
		</div>
		<!-- /#wrapper -->

		<%@include file="includes/bottom.jsp"%>
</body>
</html>
