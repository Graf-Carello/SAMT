<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../meta/main.inc"%>
<title>Edit User | SAMT</title>

<%@include file="../includes/top.jsp"%>

</head>
<body>


	<div class="container" role="main">

		<c:choose>
			<c:when test="${not empty user}">
				<c:set var="legend">Change User ${user.id}</c:set>
				<c:set var="formAction">edit</c:set>
				<c:set var="readonly">readonly</c:set>
			</c:when>
			<c:otherwise>
				<c:set var="legend">New User</c:set>
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
						value="${user.id}" />

					<fieldset>
						<legend>${legend}</legend>

						<!-- ----------------  userName ---------------- -->
						<div class="form-group">
							<label for="inputUserName" class="col-md-2 control-label">Username *</label>
							<div class="col-md-10">
								<input required class="form-control" id="inputUserName" type="text"
									name="userName" data-error="required" value="<c:out value="${user.userName}"/>">
								<div class="help-block with-errors"></div>
							</div>
						</div>

						<!-- ----------------  firstName ---------------- -->
						<div class="form-group">
							<label for="inputFirstName" class="col-md-2 control-label">Firstname *</label>
							<div class="col-md-10">
								<input required class="form-control" id="inputFirstName" type="text"
									name="firstName" data-error="required" value="<c:out value="${user.firstName}"/>">
									<div class="help-block with-errors"></div>
							</div>
						</div>

						<!-- ----------------  lastName ---------------- -->
						<div class="form-group">
							<label for="inputLastName" class="col-md-2 control-label">Lastname *</label>
							<div class="col-md-10">
								<input required class="form-control" id="inputLastName" type="text"
									name="lastName" data-error="required" value="<c:out value="${user.lastName}"/>">
									<div class="help-block with-errors"></div>
							</div>
						</div>

						<!-- ----------------  degreeCourse ---------------- -->
						<div class="form-group">
							<label for="inputDegreeCourse" class="col-md-2 control-label">Degreecourse *</label>
							<div class="col-md-10">
								<input required class="form-control" id="inputDegreeCourse" type="text"
									name="degreeCourse" data-error="required" value="<c:out value="${user.degreeCourse}"/>">
									<div class="help-block with-errors"></div>
							</div>
						</div>

						<!-- ----------------  email ---------------- -->
						<div class="form-group">
							<label for="inputEmail" class="col-md-2 control-label">E-Mail *</label>
							<div class="col-md-10">
								<input required class="form-control" id="inputEmail"
									pattern="^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$"
									data-error="not a vaid e-mail" name="email"
									value="<c:out value="${user.email}"/>">
								<div class="help-block with-errors"></div>
							</div>
						</div>

						<!-- ----------------  password ---------------- -->
						<div class="form-group">
							<label for="inputPassword" class="col-md-2 control-label">Password *</label>
							<div class="col-md-10">
								<input required class="form-control" id="inputPassword" type="password"
									name="password" data-minlength="8" data-error="too short" value="<c:out value="${user.password}"/>">
							<div class="help-block with-errors"></div>
							</div>
						</div>
						
						<!-- ----------------  password confirm ---------------- -->
						<div class="form-group">
							<label for="inputPassword" class="col-md-2 control-label">Confirm Password *</label>
							<div class="col-md-10">
								<input required class="form-control" id="inputPassword" type="password"
									name="password_confirm" data-match="#inputPassword" data-error="passwords don't match" value="<c:out value="${user.password}"/>">
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
