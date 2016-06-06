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

	<c:choose>
		<c:otherwise>
			<c:set var="legend">New Note</c:set>
			<c:set var="formAction">add</c:set>
			<c:set var="readonly"></c:set>
		</c:otherwise>
	</c:choose>

	<div class="container" role="main">
		<div class="row">
			<div class="col-md-8 col-md-offset-2">
				<form class="form-horizontal" method="post" action="${formAction}">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<fieldset>
						<legend>${legend}</legend>

						<! ----------------  name ---------------- -->
						<div class="form-group">
							<label for="inputName" class="col-md-2 control-label">Name</label>
							<div class="col-md-10">
								<input class="form-control" id="inputName" type="text"
									name="name" value="<c:out value="${note.name}"/>">
							</div>
						</div>

						<! ----------------  content ---------------- -->
						<div class="form-group">
							<label for="inputContent" class="col-md-2 control-label">Content</label>
							<div class="col-md-10">
								<input class="form-control" id="inputContent" type="text"
									name="content" value="<c:out value="${note.content}"/>">
							</div>
						</div>

					</fieldset>
				</form>
			</div>
		</div>

	</div>

	<%@include file="includes/bottom.jsp"%>
</body>
</html>
