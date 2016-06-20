<div class="nav navbar-top-links navbar-right">
	<div class="userinfo">


		<form action="${logoutUrl}" method="post">
			<span class="username">${username}</span> | <input class="btnlink" type="submit"
				value="Logout" /> <input type="hidden"
				name="${_csrf.parameterName}" value="${_csrf.token}" /><br/><span class="userrole">${userrole}</span>
		</form>
	</div>
</div>