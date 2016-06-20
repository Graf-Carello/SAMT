<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../meta/main.inc"%>
<title>Forumposting | SAMT</title>

<%@include file="../includes/top.jsp"%>

</head>
<body>


	<div class="container" role="main">

		<!--  add or edit?  ----------------------------------------------------------- -->
		<c:choose>
			<c:when test="${not empty homework}">
				<c:set var="legend">Change Homework ${homework.id}</c:set>
				<c:set var="formAction">edit</c:set>
				<c:set var="readonly">readonly</c:set>
			</c:when>
			<c:otherwise>
				<c:set var="legend">New Homework</c:set>
				<c:set var="formAction">add</c:set>
				<c:set var="readonly"></c:set>
			</c:otherwise>
		</c:choose>

		<div class="row">
			<div class="createForm col-md-8 col-md-offset-2">

				<c:if test="${not empty errorMessage}">

					<div class="error alert alert-danger fade in">
						<a href="#" class="close" data-dismiss="alert" aria-label="close"
							title="close">×</a> ${errorMessage}
					</div>

				</c:if>
				<form class="form-horizontal" method="post" action="${formAction}">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" /> <input type="hidden" name="id"
						value="${homework.id }" />

					<fieldset>
						<legend>${legend}</legend>

						<!-- ----------------  course ---------------- -->
						<div class="form-group">
							<label for="inputCourse" class="col-md-2 control-label">Course</label>
							<div class="col-md-10">
								<input class="form-control" id="inputTitle" type="text"
											name="course" value="<c:out value="${homework.course}"/>">
							</div>
						</div>

						<!-- ----------------  description ---------------- -->
						<div class="form-group">
							<label for="inputDescription" class="col-md-2 control-label">Description</label>
							<div class="col-md-10">							
								<textarea class="form-control textarea" rows="10"
									id="inputDescription" type="text" name="description"><c:out
										value="${homework.description}" /></textarea>
							</div>
						</div>
						
						<!-- ----------------  deadline ---------------- -->
						<div class="form-group">
							<label for="inputDeadline" class="col-md-2 control-label">Deadline</label>
							<div class="col-md-10">
								<input class="form-control form_datetime" id="inputDeadline"
									type="text" name="deadline"
									value="<fmt:formatDate value="${homework.deadline}" pattern="dd.MM.yyyy"/>">
							</div>
						</div>

						<input class="btn btn-lg btn-primary btn-block" type="submit" value="Save" \>

					</fieldset>


				</form>
			</div>
		</div>

	</div>

	<%@include file="../includes/bottom.jsp"%>
</body>
</html>
