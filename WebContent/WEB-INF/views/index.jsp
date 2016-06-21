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

	<div id="wrapper" class="${location}">
	
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">

		<!-- Top-Navigation --> <%@include file="partials/topnav.jsp"%>
		<!-- Side-Navigation --> <%@include file="partials/sidenav.jsp"%>

		</nav>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Student Administration and Management
						Tool</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>

			<!-- ##################### Content  ##################### -->

			<!-- User -->
			<c:forEach items="${user}" var="user">
				<%@include file="admin/content.jsp"%>
			</c:forEach>
			<!-- /User -->

			<!-- Notes -->
			<div id="notewrapper">
				<c:forEach items="${notes}" var="note" varStatus="i">
					<%@include file="notes/content.jsp"%>
				</c:forEach>
			</div>
			<!-- /Notes -->

			<!-- Homework -->
			<c:forEach items="${homeworks}" var="homework" varStatus="i">
				<%@include file="homework/content.jsp"%>
			</c:forEach>

			<!-- /Homework -->

			<!-- Projects -->
			<c:forEach items="${projects}" var="project" varStatus="i">
				<%@include file="projects/content.jsp"%>
			</c:forEach>

			<!-- /Projects -->

			<!-- Forum -->
			<h1>${titleForum}</h1>
			<div id="forumwrapper" class="container-fluid">
				<c:forEach items="${posts}" var="post" varStatus="i">
					<%@include file="forum/content.jsp"%>
				</c:forEach>
			</div>
			<!-- /Forum -->

			<!-- ##################### /Content  ##################### -->

		</div>
		<!-- /#wrapper -->

		<%@include file="includes/bottom.jsp"%>
</body>
</html>
