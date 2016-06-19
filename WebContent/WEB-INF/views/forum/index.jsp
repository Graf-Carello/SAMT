<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../meta/main.inc"%>
<title>${title}|SAMT</title>

<%@include file="../includes/top.jsp"%>

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

		<!-- Top-Navigation --> <%@include file="../partials/topnav.jsp"%>

		<!-- Side-Navigation --> <%@include file="../partials/sidenav.jsp"%>


		</nav>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">

					<!-- ########## CONTENT ############################################################# -->

					<h1>${title}</h1>

					<nav class="navbar navbar-default">
					<div class="container-fluid">

						<a href="<c:url value="/forum/add" />">
							<button type="button" class="btn btn-default success">Add</button>
						</a>

					</div>
					</nav>

					<div id="forumwrapper">
						<c:forEach items="${posts}" var="post" varStatus="i">

							<div class="post row" id="${post.id}">
								<div class="post_left col-lg-1">
									<b class="author">${creator[i.index].userName}</b> <img
										src="<c:url value="/img/profiles/${creator[i.index].profilePicture}" />"
										alt="test">
									<c:if test="${post.user == currentUser}">
									<form method="post" action="editPage">
										<input type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" /> <input type="hidden"
											value="${post.id}" name="id" />
										<input type="submit" value="edit">
									</form>
									</c:if>
									<a href="<c:url value="reply?oPost=${post.id}"/>" class="reply">Reply</a>
								</div>
								<div class="post_right col-lg-11">
								<a href="#${post.id}">#</a> 
									<h3>${post.title}</h3>
									<p>${post.content}</p>
								</div>
							</div>
						</c:forEach>
					</div>

					<!-- ########## /CONTENT ############################################################ -->

				</div>
			</div>
		</div>
		<!-- /#wrapper -->

		<%@include file="../includes/bottom.jsp"%>
</body>
</html>
