<h1>${titleProjects}</h1>
<div class="projectcontainer">
	<div class="projcheck">
		<c:if test="${location != 'dashboard'}">
			<input class="form-control checkbox" type="checkbox" name="id"
				value="${project.id}" />
		</c:if>
	</div>
	<div class="projcon">
		<div class="btn-group btn-group-justified projects" role="group"
			aria-label="...">

			<div class="btn-group" role="group">
				<b>Course: </b>${project.course}</div>
			<div class="btn-group" role="group">
				<b>Name: </b>${project.projectName}</div>
			<div class="btn-group" role="group">
				<b>Deadline: </b>
				<fmt:formatDate value="${project.deadline}" pattern="dd.MM.yyyy" />
			</div>
			<div class="btn-group" role="group">
				<b>Progress: </b>${project.progress}%</div>
		</div>
		<c:if test="${location != 'dashboard'}">
			<b>Members: </b>
			<c:forEach items="${memberList[i.index]}" var="member"
				varStatus="loop">${member.firstName} ${member.lastName}<c:if test="${!loop.last}">,</c:if>
			</c:forEach>
		</c:if>
		<div class="progress">
			<c:choose>
			<c:when test="${project.progress < 100}">
				<c:set var="progress">${project.progress}</c:set>
			</c:when>
			<c:otherwise>
				<c:set var="progress">a0</c:set>
			</c:otherwise>
		</c:choose>
			<div class="progress-bar" role="progressbar" aria-valuemin="0"
				aria-valuemax="100" style="background-color:#${progress}0909; width: ${project.progress}%;"></div>
		</div>
	</div>
</div>