<div class="navbar-default sidebar" role="navigation">
	<div class="sidebar-nav navbar-collapse">
		<ul class="nav" id="side-menu">
			<li class="sidebar-search">
				<div class="input-group custom-search-form">
					<input type="text" class="form-control" placeholder="Search...">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button">
							<i class="fa fa-search"></i>
						</button>
					</span>
				</div> <!-- /input-group -->
			</li>
			
			<li><a href="<c:url value="/" />"><i class="fa fa-dashboard fa-fw"></i> Dashboard</a></li>
			<li><a href="#"><i class="fa fa-book fa-fw"></i> Forum<span class="fa arrow"></span></a>
				<ul class="nav nav-second-level">
					<li><a href="<c:url value="/forum" />"> View all</a></li>
					<li><a href="<c:url value="/forum/tracked" />"> View tracked</a></li>
					<li><a href="<c:url value="/forum/add" />"> Add entry</a></li>
				</ul>
			</li>
			<li><a href="#"><i class="fa fa-tasks fa-fw"></i> Projects<span class="fa arrow"></span></a>
				<ul class="nav nav-second-level">
					<li><a href="<c:url value="/projects" />"> View</a></li>
					<li><a href="<c:url value="/projects/add" />"> Add new</a></li>
				</ul>
			</li>
			<li><a href="#"><i class="fa fa-paperclip fa-fw"></i> Homework<span class="fa arrow"></span></a>
				<ul class="nav nav-second-level">
					<li><a href="<c:url value="/homework" />"> View all</a></li>
					<li><a href="<c:url value="/homework/add" />"> Add new</a></li>
				</ul>
			</li>
			<li><a href="#"><i class="fa fa-file-o fa-fw"></i> Notes<span class="fa arrow"></span></a>
				<ul class="nav nav-second-level">
					<li><a href="<c:url value="/notes" />"> View own</a></li>
					<li><a href="<c:url value="/notes/public" />"> View public</a></li>
					<li><a href="<c:url value="/notes/add" />"> Add new</a></li>
				</ul>
			</li>
			<li><a href="<c:url value="/admin" />"><i class="fa fa-users fa-fw"></i> Edit Users</a></li>
			
		</ul>
	</div>
	<!-- /.sidebar-collapse -->
</div>