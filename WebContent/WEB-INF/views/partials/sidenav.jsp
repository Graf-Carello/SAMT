<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div class="navbar-default sidebar" role="navigation">
	<div class="sidebar-nav navbar-collapse">
		<ul class="nav" id="side-menu">

			<li><a href="<c:url value="/" />"><i
					class="fa fa-dashboard fa-fw"></i> Dashboard</a></li>
			<li><a href="<c:url value="#" />"><i
					class="fa fa-book fa-fw"></i> Forum<span class="fa arrow"></span></a>
				<ul class="nav nav-second-level">
					<li><a href="<c:url value="/forum/" />"> View all</a></li>
					<li><a href="<c:url value="/forum/tracked/" />"> View
							tracked</a></li>
					<li><a href="<c:url value="/forum/add/" />"> Add entry</a></li>
				</ul></li>
			<li><a href="<c:url value="#" />"><i
					class="fa fa-tasks fa-fw"></i> Projects<span class="fa arrow"></span></a>
				<ul class="nav nav-second-level">
					<li><a href="<c:url value="/projects/active/" />"> Active</a></li>
					<li><a href="<c:url value="/projects/archived/" />">
							Archived</a></li>
					<li><a href="<c:url value="/projects/add/" />"> Add new</a></li>
				</ul></li>
			<li><a href="<c:url value="#" />"><i
					class="fa fa-paperclip fa-fw"></i> Homework<span class="fa arrow"></span></a>
				<ul class="nav nav-second-level">
					<li><a href="<c:url value="/homework/" />"> View all</a></li>
					<li><a href="<c:url value="/homework/add/" />"> Add new</a></li>
				</ul></li>
			<li><a href="<c:url value="#" />"><i
					class="fa fa-file-o fa-fw"></i> Notes<span class="fa arrow"></span></a>
				<ul class="nav nav-second-level">
					<li><a href="<c:url value="/notes/own/" />"> View own</a></li>
					<li><a href="<c:url value="/notes/public/" />"> View
							public</a></li>
					<li><a href="<c:url value="/notes/add/" />"> Add new</a></li>
				</ul></li>

			<sec:authorize url="/admin">
				<li><a href="<c:url value="/admin/" />"><i
						class="fa fa-users fa-fw"></i> Edit Users</a></li>
			</sec:authorize>


		</ul>
	</div>
	<!-- /.sidebar-collapse -->
</div>