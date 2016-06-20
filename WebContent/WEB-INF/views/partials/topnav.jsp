<ul class="nav navbar-top-links navbar-right">

	<!-- dropdown.user -->
	<li class="dropdown"><a class="dropdown-toggle"
		data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
			<i class="fa fa-caret-down"></i>
	</a>
		<ul class="dropdown-menu dropdown-user">
			<li><a href="#"><i class="fa fa-user fa-fw"></i> User
					Profile</a></li>
			<li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a></li>
			<li class="divider"></li>
			<li><c:url value="/logout" var="logoutUrl" />
				<form action="${logoutUrl}" method="post">
					<input class="btn btn-xs btn-danger" type="submit" value="Logut" />
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form></li>
		</ul> <!-- /.dropdown-user --></li>
</ul>