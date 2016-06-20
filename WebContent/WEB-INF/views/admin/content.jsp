<table data-toggle="table" class="table table-striped users">
	<tbody>
		<tr>
			<td rowspan="6" class="profilepic"><img
				src="<c:url value="/img/profiles/${user.profilePicture}" />"
				alt="test"></td>
		</tr>
		<c:if test="${location != 'dashboard'}">
			<tr>
				<td class="name"><h2>${user.firstName}${user.lastName}</h2></td>
				<td><a class="delete"
					href="<c:url value="/admin/delete?id=${user.id}" />"><button
							type="button" class="btn btn-danger">Delete User
							#${user.id}</button></a></td>
				<td><a class="edit"
					href="<c:url value="/admin/edit?id=${user.id}" />"><button
							type="button" class="btn btn-danger">Edit User
							#${user.id}</button></a></td>
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
		<c:if test="${location != 'dashboard'}">
			<tr>
				<td colspan="2" class="tabledata"><b>Password:</b>
					${user.password}</td>
			</tr>
		</c:if>
		<tr>
			<td colspan="2"></td>
		</tr>
	</tbody>
</table>