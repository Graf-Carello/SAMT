<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../meta/main.inc"%>
<title>Calendar | SAMT</title>

<%@include file="../includes/top.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/fullcalendar.css" />" />

</head>
<body>

	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">

		<!-- Top-Navigation --> <%@include file="../partials/topnav.jsp"%>
		<!-- Side-Navigation --> <%@include file="../partials/sidenav.jsp"%>

		</nav>



		<!-- ########## CONTENT ############################################################# -->

		<div id="page-wrapper">
			<div class="graphs">
				</a> <a href="add">
					<button type="button" class="btn btn-primary">
						<span aria-hidden="true"></span>Add Event
					</button>
				</a>
				<div id="calendar"></div>
			</div>
		</div>
	</div>

	<!-- ########## /CONTENT ############################################################ -->

	<%@include file="../includes/bottom.jsp"%>
	<script type="text/javascript"
		src="<c:url value="/js/moment.min.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/js/fullcalendar.js" />"></script>
	<script>
		
	</script>
</body>
</html>
