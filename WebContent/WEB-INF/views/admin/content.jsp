<table data-toggle="table" class="table table-striped users">
	<tbody>
		<tr>
			<td rowspan="5" class="profilepic"><img
				src="<c:url value="/img/profiles/${user.profilePicture}" />"
				alt="test"></td>
		</tr>
		<c:if test="${location != 'dashboard'}">
			<tr>
				<td class="name">
					<h2>${user.firstName} ${user.lastName}</h2>
				</td>
				<td class="editdelete">
					<c:choose>
					<c:when test="${user.enabled == true}">
						<a class="status"
							href="<c:url value="/admin/disable?id=${user.id}" />"><button
							type="button" class="btn btn-primary"><i
							class="fa fa-remove fa-fw"></i> disable ${user.userName}</button>
						</a>
					</c:when>
					<c:otherwise>
						<a class="status"
							href="<c:url value="/admin/enable?id=${user.id}" />"><button
							type="button" class="btn btn-primary"><i
							class="fa fa-check fa-fw"></i> enable ${user.userName}</button>
						</a>
					</c:otherwise>
					</c:choose>
					<a class="edit"
					href="<c:url value="/admin/edit?id=${user.id}" />"><button
							type="button" class="btn btn-primary"><i
					class="fa fa-wrench fa-fw"></i> edit ${user.userName}</button></a>
				</td>

			</tr>
		</c:if>

		<tr>
			<td colspan="2" class="tabledata"><b>User name:</b>
				${user.userName}</td>
		</tr>
		<tr>
			<td colspan="2" class="tabledata"><b>Degree Course:</b>
				${user.degreeCourse}</td>
		</tr>
		<tr>
			<td colspan="2" class="tabledata"><b>E-Mail:</b> ${user.email}</td>
		</tr>
	</tbody>
</table>