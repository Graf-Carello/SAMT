<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../meta/main.inc"%>
<title>${title}|SAMT</title>

<%@include file="../includes/top.jsp"%>

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

		<!-- Top-Navigation --> <%@include file="../partials/topnav.jsp"%>

		<!-- Side-Navigation --> <%@include file="../partials/sidenav.jsp"%>


		</nav>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">

					<!-- ########## CONTENT ############################################################# -->

					<h1>${title}</h1>

					<nav class="navbar navbar-default">
					<div class="container-fluid">

						<a href="<c:url value="/projects/add" />">
							<button type="button" class="btn btn-default success">Add</button>
						</a>

						<form method="POST" action="<c:url value="/projects/editPage" />">

							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />

							<button type="submit" class="btn btn-default">Edit</button>
							<!-- <button type="submit" class="btn btn-danger delete clearfix">Delete</button> -->
					</div>
					</nav>





					<br />

					<c:forEach items="${projects}" var="project" varStatus="i">
						<div class="projectcontainer">
							<div class="projcheck">
								<input class="form-control checkbox" type="checkbox" name="id"
									value="${project.id}" />
							</div>
							<div class="projcon">
								<div class="btn-group btn-group-justified projects" role="group"
									aria-label="...">

									<div class="btn-group" role="group">
										<b>Course: </b>${project.course}</div>
									<div class="btn-group" role="group">
										<b>Name: </b>${project.projectName}</div>
									<div class="btn-group" role="group">
										<b>Deadline: </b>${project.deadline}</div>
									<div class="btn-group" role="group">
										<b>Progress: </b>${project.progress}%</div>
								</div>
								<b>Members: </b>
								<c:forEach items="${memberList[i.index]}" var="member"
									varStatus="loop">
							${member.firstName} ${member.lastName}<c:if test="${!loop.last}">,</c:if>
								</c:forEach>
								<div class="progress">
									<div class="progress-bar" role="progressbar" aria-valuemin="0"
										aria-valuemax="100" style="width: ${project.progress}%;"></div>
								</div>
							</div>
						</div>
					</c:forEach>

					</form>

					<!-- ########## /CONTENT ############################################################ -->

				</div>
			</div>
		</div>
		<!-- /#wrapper -->

		<%@include file="../includes/bottom.jsp"%>
</body>
</html>
