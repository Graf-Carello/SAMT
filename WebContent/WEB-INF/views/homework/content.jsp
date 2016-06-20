<h1>${titleHomework}</h1>
<div>
	<h4>${homework.course}
		::
		<fmt:formatDate value="${homework.deadline}" pattern="dd.MM.yyyy" />
	</h4>
	<p>${homework.description}</p>
	<c:if test="${location != 'dashboard'}">
		<a href="<c:url value="/homework/edit?id=${homework.id}" />">edit</a>
	</c:if>
</div>
