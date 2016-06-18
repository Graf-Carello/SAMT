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
			<c:when test="${not empty project}">
				<c:set var="legend">Change Project ${project.id}</c:set>
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
						value="${project.id }" />

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
								<input class="form-control form_datetime" id="inputDeadline"
									type="text" name="deadline"
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
							<label for="inputMembers" class="col-md-2 control-label">Member</label>
							<div class="col-md-10">

								<c:if test="${type == 'edit'}">
									<b>Current members: </b>
									<c:forEach items="${previousMembers}" var="pmember"
										varStatus="loop">
										${pmember.firstName} ${pmember.lastName}<c:if
											test="${!loop.last}">,</c:if>
									</c:forEach>
								</c:if>
								<select multiple class="tagselect" name="users"
									data-placeholder="Choose one or more project members">
									<c:forEach items="${possibleMembers}" var="member">
										<option value="${member.id}">${member.firstName}
											${member.lastName}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<c:if test="${type == 'edit'}">

							<!-- ----------------  progress ---------------- -->
							<div class="form-group">
								<label for="inputProgress" class="col-md-2 control-label">Progress</label>
								<div class="col-md-10">
									<input class="form-control" id="inputProgress" type="text"
										name="progress" value="${project.progress}">
								</div>
							</div>

							<!-- ----------------  isArchived ---------------- -->
							<div class="form-group">
								<label for="inputIsArchived" class="col-md-2 control-label">Archive</label>
								<div class="col-md-10">
									<input class="form-control checkbox" id="checkArchived"
										type="checkbox" name="isArchived" value="true">
								</div>
							</div>

						</c:if>

						<button class="btn btn-lg btn-primary btn-block" type="submit">Save</button>

					</fieldset>


				</form>
			</div>
		</div>

	</div>

	<%@include file="../includes/bottom.jsp"%>

</body>
</html>
