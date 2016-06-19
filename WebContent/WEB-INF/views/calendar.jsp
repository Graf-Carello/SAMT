<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="meta/main.inc"%>
<title>Calendar | SAMT</title>

<%@include file="includes/top.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/fullcalendar.css" />" />

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

					<div id="calendar"></div>

					<!-- ########## /CONTENT ############################################################ -->

				</div>
			</div>
		</div>
		<!-- /#wrapper -->

		<%@include file="includes/bottom.jsp"%>
		<script type="text/javascript" src="<c:url value="/js/moment.min.js" />"></script>
		<script type="text/javascript" src="<c:url value="/js/fullcalendar.js" />"></script>
		<script>

	$(document).ready(function() {

		$('#calendar').fullCalendar({
			defaultDate: '2016-06-20',
			editable: true,
			eventLimit: true, // allow "more" link when too many events
			events: [
				{
					title: 'All Day Event',
					start: '2016-06-01'
				},
				{
					title: 'Long Event',
					start: '2016-06-07',
					end: '2016-06-10'
				},
				{
					id: 999,
					title: 'Repeating Event',
					start: '2016-06-09T16:00:00'
				},
				{
					id: 999,
					title: 'Repeating Event',
					start: '2016-06-16T16:00:00'
				},
				{
					title: 'Conference',
					start: '2016-06-11',
					end: '2016-06-13'
				},
				{
					title: 'Meeting',
					start: '2016-06-12T10:30:00',
					end: '2016-06-12T12:30:00'
				},
				{
					title: 'Lunch',
					start: '2016-06-12T12:00:00'
				},
				{
					title: 'Meeting',
					start: '2016-06-12T14:30:00'
				},
				{
					title: 'Happy Hour',
					start: '2016-06-12T17:30:00'
				},
				{
					title: 'Dinner',
					start: '2016-06-12T20:00:00'
				},
				{
					title: 'Birthday Party',
					start: '2016-06-13T07:00:00'
				},
				{
					title: 'Click for Google',
					url: 'http://google.com/',
					start: '2016-06-28'
				}
			]
		});
		
	});

</script>
</body>
</html>
