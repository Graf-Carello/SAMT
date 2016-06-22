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

	<div class="container" role="main">

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

		<div class="row">
			<div class="createForm col-md-8 col-md-offset-2">

				<form data-toggle="validator" class="form-horizontal" method="post"
					action="${formAction}">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" /> <input type="hidden" name="id"
						value="${event.id}" />

					<fieldset>
						<legend>${legend}</legend>

						<!-- ----------------  name ---------------- -->
						<div class="form-group">
							<label for="inputTitle" class="col-md-2 control-label">Title
								*</label>
							<div class="col-md-10">
								<input required class="form-control" id="inputTitle" type="text"
									name="title" data-error="required"
									value="<c:out value="${event.title}"/>">
								<div class="help-block with-errors"></div>
							</div>
						</div>

						<!-- ----------------  start ---------------- -->
						<div class="form-group">
							<label for="inputStart" class="col-md-2 control-label">Start *</label>
							<div class="col-md-10">
								<input required class="form-control form_datetime"
									id="inputStart" type="text" name="start"
									data-error="required"
									value="<fmt:formatDate value="${event.start}" pattern="dd.MM.yyyy HH:mm"/>">
								<div class="help-block with-errors"></div>
							</div>
						</div>

						<!-- ----------------  start ---------------- -->
						<div class="form-group">
							<label for="inputEnd" class="col-md-2 control-label">End *</label>
							<div class="col-md-10">
								<input required class="form-control form_datetime"
									id="inputEnd" type="text" name="end"
									data-error="required"
									value="<fmt:formatDate value="${event.end}" pattern="dd.MM.yyyy HH:mm"/>">
								<div class="help-block with-errors"></div>
							</div>
						</div>

						<span class="info">Fields labeled with an * are required!</span>

						<button class="btn btn-lg btn-primary btn-block" type="submit">Save</button>

					</fieldset>


				</form>
			</div>
		</div>

	</div>

	<%@include file="../includes/bottom.jsp"%>
</body>
</html>
