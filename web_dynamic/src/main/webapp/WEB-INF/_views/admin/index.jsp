<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="linkfile/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<base href="${baseurl}" />
<%@include file="linkfile/headerInfo.jsp"%>
<link href="dist/AdminLTE/plugins/datepicker/datepicker3.css" rel="stylesheet" type="text/css" />
<title>欢迎<c:out value="${app_PageTitle}" default=" -- [未设置应用程序名称]"></c:out></title>
</head>
<body class="skin-black">
	<div class="wrapper">
		<%@include file="linkfile/headNav.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">

			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					首页 <small>控制中心 </small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="admin_access/index.do">
					<i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">仪表板</li>
				</ol>
			</section>
			<!-- Main content -->
			<section class="content">
				<div class="jumbotron">
					<h1>Hello, world!</h1>
					<p>欢迎回来！</p>
					<p>访问<a target="_blank" href='<c:out value="//${ClientHost}"></c:out>'>客户端</a></p>
					
				</div>

				<div class="row">
					<div class="col-lg-6">
					
						<!-- Calendar -->
						<div class="box box-solid bg-green-gradient">
							<div class="box-header">
								<i class="fa fa-calendar"></i>
								<h3 class="box-title">日历和任务</h3>
								<!-- tools box -->
								<div class="pull-right box-tools">
									
									<button class="btn btn-success btn-sm" data-widget="collapse">
										<i class="fa fa-minus"></i>
									</button>
								</div>
								<!-- /. tools -->
							</div>
							<!-- /.box-header -->
							<div class="box-body no-padding">
								<!--The calendar -->
								<div id="calendar" style="width: 100%"></div>
							</div>
							<!-- /.box-body -->
							<div class="box-footer text-black">
								<div class="row">
									<div class="col-sm-12">
										<!-- Progress bars --> 

										<c:forEach items="${taskList}" var="item">
										
											<div class="clearfix">
												<span class="pull-left" style="color: ${item.highlight}">${item.title}</span> <small
													class="pull-right" style="color: ${item.highlight}">${item.endTime}</small>
											</div>
											<div class="progress xs">
												<div class="progress-bar progress-bar-green"
													style="width: ${item.progress}%;"></div>
											</div>
										
										</c:forEach>
 
									</div>
									<!-- /.col -->
									<!-- /.col -->
								</div>
								<!-- /.row -->
							</div>
						</div>
						<!-- /.box -->
						
					</div>
					<div class="col-lg-6">
						<div class="box">
							<div class="box-header">
								<h3 class="box-title">公告栏</h3>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<c:if test="${announcementList == null || fn:length(announcementList) == 0}">
									<div class="page-header"> <h3>暂时没有内容</h3> </div>
								</c:if>
								<c:forEach  items="${announcementList}" var="item">
									<div class="media">
										<div class="media-left">
											<a href="#"> <img data-holder-rendered="true"
												src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iNjQiIGhlaWdodD0iNjQiIHZpZXdCb3g9IjAgMCA2NCA2NCIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSI+PCEtLQpTb3VyY2UgVVJMOiBob2xkZXIuanMvNjR4NjQKQ3JlYXRlZCB3aXRoIEhvbGRlci5qcyAyLjYuMC4KTGVhcm4gbW9yZSBhdCBodHRwOi8vaG9sZGVyanMuY29tCihjKSAyMDEyLTIwMTUgSXZhbiBNYWxvcGluc2t5IC0gaHR0cDovL2ltc2t5LmNvCi0tPjxkZWZzPjxzdHlsZSB0eXBlPSJ0ZXh0L2NzcyI+PCFbQ0RBVEFbI2hvbGRlcl8xNTVmZTdjOWY2ZCB0ZXh0IHsgZmlsbDojQUFBQUFBO2ZvbnQtd2VpZ2h0OmJvbGQ7Zm9udC1mYW1pbHk6QXJpYWwsIEhlbHZldGljYSwgT3BlbiBTYW5zLCBzYW5zLXNlcmlmLCBtb25vc3BhY2U7Zm9udC1zaXplOjEwcHQgfSBdXT48L3N0eWxlPjwvZGVmcz48ZyBpZD0iaG9sZGVyXzE1NWZlN2M5ZjZkIj48cmVjdCB3aWR0aD0iNjQiIGhlaWdodD0iNjQiIGZpbGw9IiNFRUVFRUUiLz48Zz48dGV4dCB4PSIxMi41IiB5PSIzNi44Ij42NHg2NDwvdGV4dD48L2c+PC9nPjwvc3ZnPg=="
												class="media-object" data-src="holder.js/64x64" alt="64x64">
											</a>
										</div>
										<div class="media-body">
											<h3 class="media-heading" style="color:${item.titleColor};">${item.title}</h3>
											<br>
											<p>${item.content}</p>
										</div>
									</div>
									<div class="progress xs">
										<div class="progress-bar progress-bar-green" style="width: 100%;"></div>
									</div>
								</c:forEach>
								
							</div>
							<!-- /.box-body -->
							<div class="box-footer clearfix">
								
							</div>
						</div>
					</div>
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
<script src="dist/AdminLTE/plugins/datepicker/bootstrap-datepicker.js" type="text/javascript"></script>
<script type="text/javascript">
	function init() {
		$("#calendar").datepicker({language:'zh-CN'});
	}
	init();
</script>
</html>