<h1>${titleForum}</h1>
<div class="post row" id="${post.id}">
	<div class="post_left col-lg-1">
		<b class="author">${creator[i.index].userName}</b> <img
			src="<c:url value="/img/profiles/${creator[i.index].profilePicture}" />"
			alt="test">
		<c:if test="${post.user == currentUser}">
			<form method="post" action="editPage">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" /> <input type="hidden" value="${post.id}"
					name="id" />
				<c:if test="${location != 'dashboard'}">
					<input type="submit" value="edit">
				</c:if>
			</form>
		</c:if>
		<c:if test="${location != 'dashboard'}">
			<a href="<c:url value="reply?oPost=${post.id}"/>" class="reply">Reply</a>
		</c:if>
	</div>
	<div class="post_right col-lg-11">
		<c:if test="${location != 'dashboard'}">
			<a href="#${post.id}">#</a>
		</c:if>
		<h3>${post.title}</h3>
		<p>${post.content}</p>
	</div>
</div>