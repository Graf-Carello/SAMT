<div class="post row" id="${post.id}">
	<div class="post_left clearfix">
		<b class="author">${creator[i.index].userName}</b> <img
			src="<c:url value="/img/profiles/${creator[i.index].profilePicture}" />"
			alt="${creater[i.index].userName}">

		<c:if test="${location != 'dashboard'}">
			<div class="buttons">
				<a href="<c:url value="reply?oPost=${post.id}"/>" class="reply"><button
						class="btn btn-primary">reply</button></a>

				<c:if test="${post.user == currentUser}">

					<form method="post" action="editPage">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" /> <input type="hidden"
							value="${post.id}" name="id" /> <input type="submit" class="btn btn-primary"
							value="edit">

					</form>

				</c:if>
			</div>
		</c:if>

	</div>
	<div class="post_right col-lg-11">
		<c:if test="${location != 'dashboard'}">
			<a class="anchor" href="#${post.id}">#</a>
		</c:if>
		<h3>${post.title}</h3>
		<p>${post.content}</p>
	</div>
</div>