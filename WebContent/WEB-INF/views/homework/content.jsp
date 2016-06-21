<h1>${titleHomework}</h1>
<div>

	<h4 style="display: inline">
		<c:if test="${location != 'dashboard'}">
			<a href="<c:url value="/homework/edit?id=${homework.id}"/>" title="edit"><i
				class="fa fa-wrench fa-fw"></i></a>
		</c:if>
		&raquo; <b>${homework.course}</b> bis
		<fmt:formatDate value="${homework.deadline}" pattern="dd.MM.yyyy" />
		&raquo;
	</h4>
	${homework.description}

	<hr />
</div>
