<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../linkfile/tag.jsp"%>
<%
// String path = request.getContextPath();
// String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
// //相对于主站位置
%>
<!DOCTYPE html>
<html>

	<head>
		<base href="<%=basePath %>" />
		<%@include file="../linkfile/headerInfo.jsp" %>
		<title>仪表板<c:out value="${app_PageTitle}" default=" -- [未设置应用程序名称]"></c:out></title>
	</head>

	<body class="skin-black">
		<div class="wrapper">
			<%@include file="../linkfile/headNav.jsp" %>

			<!-- Content Wrapper. Contains page content -->
			<div class="content-wrapper">

				<!-- Content Header (Page header) -->
				<section class="content-header">
					<h1>
			${app_WebServiceAppName }
			<small>控制中心</small>
		  </h1>
					<ol class="breadcrumb">
						<li><a href="access/index.do"><i class="fa fa-dashboard"></i> Home</a></li>
						<li class="active">仪表板</li>
					</ol>
				</section>
				<!-- Main content -->
				<section class="content">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">操作面板</h3> 
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-12 col-lg-4">
									<button class="btn btn-app" data-toggle="modal" data-target="#myModal" onclick="">
										<i class="fa fa-plus"></i> 添加
									</button>
									<button class="btn btn-app" onclick="location.replace(location.href);">
										<i class="fa fa-refresh"></i> 刷新
									</button>
								</div>
								<div class="col-md-12 col-lg-8">
									<p class="lead text-muted">。。。</p>
								</div>
							</div>
						</div>
					</div>

				</section>
				<!-- /.content -->

			</div>
			<!-- /.content-wrapper -->
			<%@include file="../linkfile/foot.jsp" %>
		</div>
		<!-- ./wrapper -->

	</body>
	<%@include file="../linkfile/footInfo.jsp" %>
	<script type="text/javascript">
		function init() {

		}
		init();
	</script>

</html>