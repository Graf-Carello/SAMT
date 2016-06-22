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

		<c:choose>
			<c:when test="${not empty post}">
				<c:set var="legend">Change Post ${post.id}</c:set>
				<c:set var="formAction">edit</c:set>
				<c:set var="readonly">readonly</c:set>
			</c:when>
			<c:otherwise>
				<c:set var="legend">New Post</c:set>
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
						value="${post.id}" />

					<fieldset>
						<legend>${legend}</legend>

						<!-- ----------------  title ---------------- -->
						<div class="form-group">
							<label for="inputTitle" class="col-md-2 control-label">Title
								*</label>
							<div class="col-md-10">
								<c:choose>
									<c:when test="${type == 'reply'}">
										<input readonly required class="form-control" id="inputTitle"
											type="text" name="title" data-error="required"
											value="@${op} | ${oPost.title}" />
										<div class="help-block with-errors"></div>
									</c:when>
									<c:otherwise>
										<input required class="form-control" id="inputTitle"
											type="text" name="title" data-error="required"
											value="<c:out value="${post.title}"/>">
										<div class="help-block with-errors"></div>
									</c:otherwise>
								</c:choose>
							</div>
						</div>

						<!-- ----------------  content ---------------- -->
						<div class="form-group">
							<label for="inputContent" class="col-md-2 control-label">Content
								*</label>
							<div class="col-md-10">
								<c:if test="${type == 'reply'}">
									<p>
										<b>original message: </b>${oPost.content}</p>
								</c:if>
								<textarea required class="form-control textarea" rows="10"
									id="inputContent" type="text" name="content"
									data-error="required"><c:out value="${post.content}" /></textarea>
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
	<script type="text/javascript"
		src="<c:url value="/js/jquery.tinymce.min.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/js/tinymce.min.js" />"></script>
	<script type="text/javascript">
		tinymce.init({
			selector : 'textarea'
		});
	</script>
</body>
</html>
