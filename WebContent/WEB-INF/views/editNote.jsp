<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="meta/main.inc"%>
<title>Edit Note | SAMT</title>

<%@include file="includes/top.jsp"%>

</head>
<body>


	<div class="container" role="main">

		<!--  add or edit?  ----------------------------------------------------------- -->
		<c:choose>
			<c:when test="${not empty note}">
				<c:set var="legend">Change Note ${note.id}</c:set>
				<c:set var="formAction">edit</c:set>
				<c:set var="readonly">readonly</c:set>
			</c:when>
			<c:otherwise>
				<c:set var="legend">New Note</c:set>
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

						<!-- ----------------  name ---------------- -->
						<div class="form-group">
							<label for="inputName" class="col-md-2 control-label">Name</label>
							<div class="col-md-10">
								<input class="form-control" id="inputName" type="text"
									name="name" value="<c:out value="${note.name}"/>">
							</div>
						</div>

						<!-- ----------------  content ---------------- -->
						<div class="form-group">
							<label for="inputContent" class="col-md-2 control-label">Content</label>
							<div class="col-md-10">
								<input class="form-control" id="inputContent" type="text"
									name="content" value="<c:out value="${note.content}"/>">
							</div>
						</div>

						<!-- ----------------  public ---------------- -->
						<div class="form-group">
							<label for="isPublic" class="col-md-2 control-label">Public</label>

							<div class="col-md-10">
								<c:choose>
									<c:when test="${note.isPublic}">
										<input checked class="form-control" id="checkPublic"
											type="checkbox" name="isPublic" value="true">
									</c:when>
									<c:otherwise>
										<input class="form-control" id="checkPublic" type="checkbox"
											name="isPublic" value="true">
									</c:otherwise>
								</c:choose>
							</div>
						</div>

						<button class="btn btn-lg btn-primary btn-block" type="submit">Save</button>

					</fieldset>


				</form>
			</div>
		</div>

	</div>

	<%@include file="includes/bottom.jsp"%>
</body>
</html>
