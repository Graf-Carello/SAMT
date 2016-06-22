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
			<%@include file="admin/content.jsp"%>
			<!-- /User -->

			<!-- Homework -->
			<c:if test="${he}">
			<h2>oldest Homework</h2>
			<%@include file="homework/content.jsp"%>
			</c:if>
			<!-- /Homework -->

			<!-- Projects -->
			<c:if test="${pe}">
			<h2>Project with closest deadline</h2>
			<%@include file="projects/content.jsp"%>
			</c:if>
			<!-- /Projects -->

			<!-- Forum -->
			<h2>most recent forum entry</h2>
			<div id="forumwrapper" class="container-fluid">
				<%@include file="forum/content.jsp"%>
			</div>
			<!-- /Forum -->

			<!-- ##################### /Content  ##################### -->

		</div>
		<!-- /#wrapper -->

		<%@include file="includes/bottom.jsp"%>
</body>
</html>
