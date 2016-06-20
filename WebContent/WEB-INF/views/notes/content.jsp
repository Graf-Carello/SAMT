<h1>${titleNotes}</h1>
<div class="note clearfix">
	<h3>${note.name}</h3>
	<c:choose>
		<c:when test="${type == 'public'}">
			<a class="globe" />
			<i class="fa fa-globe fa-fw"></i>
			</a>
			<span class="author">by ${authors[i.index]}</span>

		</c:when>
		<c:otherwise>
			<c:if test="${note.isPublic}">
				<a class="globe" />
				<i class="fa fa-globe fa-fw"></i>
				</a>
			</c:if>
			<c:if test="${location != 'dashboard'}">
				<a class="edit" href="<c:url value="/notes/edit?id=${note.id}" />"><i
					class="fa fa-wrench fa-fw"></i></a>
				<a class="delete"
					href="<c:url value="/notes/delete?id=${note.id}" />"><i
					class="fa fa-ban fa-fw"></i></a>
			</c:if>
		</c:otherwise>
	</c:choose>
	<p>${note.content}</p>
</div>