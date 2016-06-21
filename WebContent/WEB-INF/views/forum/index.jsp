<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../meta/main.inc"%>
<title>${title} | SAMT</title>

<%@include file="../includes/top.jsp"%>

</head>
<body>

	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">

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

						<a href="<c:url value="/forum/add" />">
							<button type="button" class="btn btn-default success">Add</button>
						</a>

					</div>
					</nav>
	
					<div id="forumwrapper" class="container-fluid">
						<c:forEach items="${posts}" var="post" varStatus="i">
							<%@include file="content.jsp"%>
						</c:forEach>
					</div>

					<!-- ########## /CONTENT ############################################################ -->

				</div>
			</div>
		</div>
		<!-- /#wrapper -->

		<%@include file="../includes/bottom.jsp"%>
</body>
</html>
