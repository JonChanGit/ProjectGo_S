<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="linkfile/tag.jsp"%>
<%
	// String path = request.getContextPath();
	// String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	// //相对于主站位置
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>" />
<%@include file="linkfile/headerInfo.jsp"%>
<title>${app_WebServiceAppName }<c:out value="${app_PageTitle}" default=" -- [未设置应用程序名称]"></c:out></title>
</head>
<body class="skin-black">
	<div class="wrapper">
		<%@include file="linkfile/headNav.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">

			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					${app_WebServiceAppName } <small>控制中心</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="access/index.do"><i class="fa fa-dashboard"></i>
							Home</a></li>
					<li class="active">仪表板</li>
				</ol>
			</section>
			<!-- Main content -->
			<section class="content">
				<div class="alert alert-warning alert-dismissible" role="alert">
					<strong>Warning!</strong> 
					<h1>NO PERMISSIONS</h1>
					<p>没有访问权限！</p>
				</div>
			</section>
			<!-- /.content -->

		</div>
		<!-- /.content-wrapper -->
		<%@include file="linkfile/foot.jsp"%>
	</div>
	<!-- ./wrapper -->

</body>
<%@include file="linkfile/footInfo.jsp"%>
<script type="text/javascript">
	
</script>
</html>