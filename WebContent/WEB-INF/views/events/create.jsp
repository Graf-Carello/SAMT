<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../meta/main.inc"%>
<title>Edit Event | SAMT</title>

<%@include file="../includes/top.jsp"%>

</head>
<body>

	<!--  add or edit?  ----------------------------------------------------------- -->
	<c:choose>
		<c:when test="${not empty event}">
			<c:set var="legend">Edit Event ${event.id}</c:set>
			<c:set var="formAction">edit</c:set>
			<c:set var="readonly">readonly</c:set>
		</c:when>
		<c:otherwise>
			<c:set var="legend">New Event</c:set>
			<c:set var="formAction">add</c:set>
			<c:set var="readonly"></c:set>
		</c:otherwise>
	</c:choose>
	<!--  add or edit?  ----------------------------------------------------------- -->

	<div id="page-wrapper">
		<div class="graphs">
			<h3 class="blank1">${legend}</h3>
			<div class="xs tabls">
				<div class="bs-example4" data-example-id="contextual-table">

					<table class="table">
						<form class="form-horizontal" method="post" action="${formAction}">
							<fieldset>


								<!-- ----------------  name ---------------- -->
								<div class="form-group">
									<label for="inputTitle" class="col-md-2 control-label">Title</label>
									<div class="col-md-10">
										<input class="form-control" id="inputTitle" type="text"
											name="title" value="<c:out value="${event.title}"/>">
									</div>
								</div>



								<!-- ----------------  startDate ---------------- -->
								<div class="form-group">
									<label for="inputStartDate" class="col-md-2 control-label">Start
										date</label>
									<div class="col-md-10">
										<input class="form_datetime" id="inputStartDate"
											placeholder="Start Date" type="text" name="startDate"
											value="<fmt:formatDate value="${event.startDate}" pattern="dd.MM.yyyy HH:mm"/>">
									</div>
								</div>

								<!--  ----------------  endDate ---------------- -->
								<div class="form-group">
									<label for="inputEndDate" class="col-md-2 control-label">End
										date</label>
									<div class="col-md-10">
										<input class="form_datetime" id="inputEndDate"
											placeholder="Start Date" type="text" name="endDate"
											value="<fmt:formatDate value="${event.endDate}" pattern="dd.MM.yyyy HH:mm"/>">
									</div>
								</div>


								<!-- ----------------  buttons ---------------- -->
								<div class="form-group">
									<div class="col-md-10 col-md-offset-2">
										<button type="submit" class="btn btn-primary">Submit</button>
										<a href="task">
											<button type="button" class="btn btn-default">Cancel</button>
										</a>
									</div>
								</div>
							</fieldset>
						</form>
					</table>
				</div>

			</div>
		</div>
	</div>
	<!-- JS for Datetime picker -->

	<script>
		$(function() {

			$(".form_datetime").datetimepicker({
				format : "dd.mm.yyyy hh:ii",
				autoclose : true,
				todayBtn : true,
				pickerPosition : "bottom-left",
				minuteStep : 5
			});

		});
	</script>

	<%@include file="../includes/bottom.jsp"%>
</body>
</html>