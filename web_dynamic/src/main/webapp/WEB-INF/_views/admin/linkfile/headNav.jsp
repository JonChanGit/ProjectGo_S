<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
	String pathh = request.getContextPath();
	String basePathh = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + pathh + "/";
%>
<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS | skin-blue |
| | skin-black |
| | skin-purple |
| | skin-yellow |
| | skin-red |
| | skin-green |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed |
| | layout-boxed |
| | layout-top-nav |
| | sidebar-collapse |
|---------------------------------------------------------|

-->

<!-- Main Header -->
<header class="main-header">
	<!-- Logo -->
	<a href="javascript:void(0);" class="logo" id="logo"><img src="dist/img/logo.png" height="45px"></a>
	<!-- Header Navbar: style can be found in header.less -->
	<nav class="navbar navbar-static-top" role="navigation">
		<!-- Sidebar toggle button-->
		<a href="javascript:void(0);" class="sidebar-toggle" data-toggle="offcanvas" role="button">
			<span class="sr-only">Toggle navigation</span>
		</a>
		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">

				<!-- User Account: style can be found in dropdown.less -->
				<li class="dropdown user user-menu">
					<a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">
						<img src="dist/img/no_profile.png" class="user-image" alt="User Image"/>
						<span class="hidden-xs">${acUser.userName }</span>
					</a>
					<ul class="dropdown-menu">
						<!-- User image sysUser-->
						<li class="user-header">
							<img src="dist/img/no_profile.png" class="img-circle" alt="User Image"/>
							<p>
								${acUser.userName }
								<small><fmt:formatDate value="${now}" type="both" dateStyle="long"
													   pattern="yyyy-MM-dd"/></small>
							</p>
						</li>
						<!-- Menu Body -->
						<li class="user-body">
							<div class="col-xs-4 text-center">
							</div>
							<div class="col-xs-4 text-center">

							</div>
							<div class="col-xs-4 text-center">
							</div>
						</li>
						<!-- Menu Footer-->
						<li class="user-footer">
							<div class="pull-left">
								<a href="admin_access/user/psd.do" class="btn btn-default btn-flat">修改密码</a>
							</div>
							<div class="pull-right">
								<a href="logout.do" class="btn btn-default btn-flat" ">退出登录</a>
							</div>
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</nav>
</header>


<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="dist/img/no_profile.png" class="img-circle" alt="User Image"/>
			</div>
			<div class="pull-left info">
				<p>${acUser.userName }</p>

				<a href="javascript:void(0);"><i class="fa fa-circle text-success"></i>(${acUser.typeDescription})</a>
			</div>
		</div>
		<!-- search form -->
		<!-- <form action="javascript:void(0);" method="get" class="sidebar-form">
		  <div class="input-group">
			<input type="text" name="q" class="form-control" placeholder="Search..."/>
			<span class="input-group-btn">
			  <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i></button>
			</span>
		  </div>
		</form> -->
		<!-- /.search form -->
		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu">
			<li class="header">功能导航</li>


			<li class="treeview">
				<a href="javascript:void(0);">
					<i class="fa fa-dashboard"></i> <span>用户和权限</span> <i class="fa fa-angle-left pull-right"></i>
				</a>
				<ul class="treeview-menu ">
					<li class=""><a href="/access/user_and_permission/userIndex.do"><i class="fa fa-circle-o"></i>用户管理</a>
					<li class=""><a href="/access/user_and_permission/roleIndex.do"><i class="fa fa-circle-o"></i>角色管理</a>
					<li class=""><a href="/access/user_and_permission/permissionIndex.do"><i class="fa fa-circle-o"></i>功能管理</a>
					</li>
				</ul>
			</li>

			<li class="treeview">
				<a href="javascript:void(0);">
					<i class="fa fa-dashboard"></i> <span>请假系统</span> <i class="fa fa-angle-left pull-right"></i>
				</a>
				<ul class="treeview-menu ">
					<li class=""><a href="admin_access/ex/studentView.do"><i class="fa fa-circle-o"></i>查看请假记录</a></li>

				</ul>
			</li>


		</ul>
	</section>
	<!-- /.sidebar -->
</aside>