
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.html">SAMT</a>
		</div>

<div class="nav navbar-top-links navbar-right">
	<div class="userinfo">

		<c:url value="/logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post">
			<span class="username">${username}</span> | <input class="btnlink" type="submit"
				value="Logout" /> <input type="hidden"
				name="${_csrf.parameterName}" value="${_csrf.token}" /><br/><span class="userrole">${userrole}</span>
		</form>
	</div>
</div>