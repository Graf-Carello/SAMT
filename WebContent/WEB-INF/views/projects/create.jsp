<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../meta/main.inc"%>
<title>Edit Project | SAMT</title>

<%@include file="../includes/top.jsp"%>

</head>
<body>


	<div class="container" role="main">

		<!--  add or edit?  ----------------------------------------------------------- -->
		<c:choose>
			<c:when test="${not empty note}">
				<c:set var="legend">Change Project ${note.id}</c:set>
				<c:set var="formAction">edit</c:set>
				<c:set var="readonly">readonly</c:set>
			</c:when>
			<c:otherwise>
				<c:set var="legend">New Project</c:set>
				<c:set var="formAction">add</c:set>
				<c:set var="readonly"></c:set>
			</c:otherwise>
		</c:choose>

		<div class="row">
			<div class="col-md-8 col-md-offset-2">
				<form class="form-horizontal" method="post" action="${formAction}">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" /> <input type="hidden" name="id"
						value="${note.id }" />

					<fieldset>
						<legend>${legend}</legend>


						<!--  projectName, deadline, progress, course, user, isArchived -->



						<!-- ----------------  projectName ---------------- -->
						<div class="form-group">
							<label for="inputProjectName" class="col-md-2 control-label">Projectname</label>
							<div class="col-md-10">
								<input class="form-control" id="inputProjectName" type="text"
									name="projectName"
									value="<c:out value="${project.projectName}"/>">
							</div>
						</div>

						<!-- ----------------  deadline ---------------- -->
						<div class="form-group">
							<label for="inputProjectName" class="col-md-2 control-label">Deadline</label>
							<div class="col-md-10">
								<input class="form-control form_datetime" id="inputDeadline" type="text"
									name="deadline"
									value="<fmt:formatDate value="${project.deadline}" pattern="dd.MM.yyyy"/>">
							</div>
						</div>

						<!-- ----------------  course ---------------- -->
						<div class="form-group">
							<label for="inputCourse" class="col-md-2 control-label">Course</label>
							<div class="col-md-10">
								<input class="form-control" id="inputCourse" type="text"
									name="course" value="<c:out value="${project.course}"/>">
							</div>
						</div>

						<!-- ----------------  user ---------------- -->
						<div class="form-group">
							<label for="inputUser" class="col-md-2 control-label">User</label>
							<div class="col-md-10">
								<select multiple class="tagselect" name="participants" data-placeholder="Choose one or more project members">
								<c:forEach items="${users}" var="user">
									<option value="${user.id}">${user.firstName} ${user.lastName}</option>
								</c:forEach>
								</select>
							</div>
						</div>

						<button class="btn btn-lg btn-primary btn-block" type="submit">Save</button>

					</fieldset>


				</form>
			</div>
		</div>

	</div>

	<%@include file="../includes/bottom.jsp"%>
	
	<script type="text/javascript"
    src="http://www.malot.fr/bootstrap-datetimepicker/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>

  <script>
    $(function() {

      $("#inputDeadline").datetimepicker({
        format : "dd.mm.yyyy",
        autoclose : true,
        todayBtn : true,
        pickerPosition : "bottom-left",
        minView : 2
      });

    });
  </script>
	
</body>
</html>
