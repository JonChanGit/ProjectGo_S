<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="../linkfile/tag.jsp" %>
<%
	// String path = request.getContextPath();
// String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
// //相对于主站位置
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath %>"/>
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
				<small>用户管理</small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="admin_access/index.do"><i class="fa fa-dashboard"></i> Home</a></li>
				<li>用户与权限</li>
				<li class="active">用户管理1</li>
			</ol>
		</section>
		<!-- Main content -->
		<section class="content">

			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">操作面板</h3></div>
				<div class="panel-body">
					<button class="btn btn-app layerDemo" data-toggle="modal" data-target="#myModal" data-type="add">
						<i class="fa fa-plus"></i> 添加
					</button>
					<button class="btn btn-app" onclick="location.replace(location.href);">
						<i class="fa fa-refresh"></i> 刷新
					</button>
				</div>
			</div>


			<div class="row">
				<div class="col-xs-12">

					<div class="box">
						<div class="box-header">
							<h3 class="box-title">角色管理</h3>
							<div class="box-tools">
								<form action="user_and_permission/userIndex.do" method="get">
									<div class="input-group">
										<input name="searchKey" class="form-control input-sm pull-right"
											   style="width: 200px;" placeholder=" 搜索 用户名 和 邮箱" type="text">
										<div class="input-group-btn">
											<button class="btn btn-sm btn-default" type="submit"><i
													class="fa fa-search"></i></button>
										</div>
									</div>
								</form>
							</div>
						</div>
						<!-- /.box-header -->
						<div class="box-body table-responsive">
							<table id="" class="table table-bordered table-striped">
								<thead>
								<tr>
									<th>id</th>
									<th>email</th>
									<th>locked</th>
									<th>头像</th>
									<th>用户标识</th>
									<th>username</th>
									<th>操作</th>

								</tr>
								</thead>
								<tbody>
								<c:forEach items="${page.content}" var="item">
									<tr>
										<th>${item.id}</th>
										<th>${item.email}</th>
										<th>
											<c:if test="${item.locked == 0}">
												<span class="label label-info">启用</span>
											</c:if>
											<c:if test="${item.locked == 1}">
												<span class="label label-default">锁定</span>
											</c:if>
										</th>
										<th>
											<img src="${empty item.head?'':'//' }${empty item.head?'':ClientHost }${empty item.head?'dist/img/no_profile.png':item.head }"
												 width="50px" height="50px" class="img-circle">
										</th>
										<th>${item.usercode}</th>
										<th>${item.username}</th>
										<th>
											<div class="btn-group" role="group" aria-label="对${item.username}用户的操作">
												<button type="button" class="btn btn-primary"
														aria-label="编辑${item.username}" data-toggle="modal"
														data-target="#myModal"
														onclick="modifyCof(${item.id},${item.locked})">编辑
												</button>


												<c:if test="${item.locked == 0}">
													<button onclick="submitModifyState(${item.id})" type="button"
															class="btn btn-warning" aria-label="解锁${item.username}">
														锁定
													</button>
												</c:if>
												<c:if test="${item.locked == 1}">
													<button onclick="submitModifyState(${item.id})" type="button"
															class="btn btn-success" aria-label="解锁${item.username}">
														解锁
													</button>
												</c:if>


													<%-- <button onclick="submitDeleteState(${item.id})" type="button" class="btn btn-danger" aria-label="删除${item.username}">删除</button> --%>


											</div>

										</th>
									</tr>
								</c:forEach>
								</tbody>
								<tfoot>
								<tr>
									<th>id</th>
									<th>email</th>
									<th>locked</th>
									<th>用户标识</th>
									<th>username</th>
									<th>操作</th>
								</tr>
								</tfoot>
							</table>

							<div class="box-footer clearfix">
								<%--@ include file="../linkfile/pagination.jsp" --%>
							</div>

						</div>
						<!-- /.box-body -->
					</div>
					<!-- /.box -->

				</div>
			</div>

		</section><!-- /.content -->


	</div><!-- /.content-wrapper -->
	<%@include file="../linkfile/foot.jsp" %>
</div><!-- ./wrapper -->

</body>
<%@include file="../linkfile/footInfo.jsp" %>
<script>
	var myformDiv = `
			<input type="hidden" id="userID">

			<div class="checkbox">
				<label>
					<input type="checkbox" value="" id="userLocked">启用账户（默认禁用）
				</label>
			</div>

			<div class="input-group">
				<span class="input-group-addon">@</span>
				<input class="form-control" placeholder="用户名" type="text" id="userUsername">
			</div>

			<div class="input-group">
				<span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
				<input class="form-control" placeholder="EMail" type="email" id="userEmail">
			</div>

			<div class="input-group">
				<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
				<input class="form-control" placeholder="用户标识" type="text" id="userUsercode">
			</div>`;
</script>
<script type="text/javascript">
	layui.use('layer', function () {
		var $ = layui.jquery, layer = layui.layer;
		var active = {
			add: function () {
				layer.open({
					title: '${app_PageTitle}'
					, content: myformDiv,
					yes: submitNew
				});
			}
		}
		$('.layerDemo').on('click', function () {
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});
	});

	/**
	 * 提交新用户
	 */
	function submitNew() {

		$("#myModalSubmit").attr("disabled", "disabled");
		var cd = 1;
		//alert(document.getElementById("userLocked").checked);
		if (document.getElementById("userLocked").checked) {
			cd = 0;//true 锁定
		} else {
			cd = 1;//false 未锁定
		}
		//alert(cd);

		var testRex = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;

		var testStr = $("#userEmail").val();
		if (testRex.test(testStr)) {

		} else {
			alert("邮箱格式不正确，请重新输入。");
			$("#myModalSubmit").removeAttr("disabled");
			return false;
		}
		miniAjax('access/user_and_permission/userModify.do',
			{
				'email': $("#userEmail").val(),
				'locked': cd,
				'usercode': $("#userUsercode").val(),
				'username': $("#userUsername").val()
			},function (data) {
				$("#myModalSubmit").removeAttr("disabled");
				$("#myModal").modal('hide');
				appendAlertInfo("alertDivSID", data.flag + " " + data.message);
			}
		);
	}

	function modifyCof(userID, checked_lock) {
		layer.open({
			title: '修改用户(不填写为不更改)'
			, content: myformDiv,
			yes: function(){submitModify(userID);}
		});
		//更改按钮状态
		if (checked_lock == 0) {
			document.getElementById("userLocked").checked = true;
		} else {
			document.getElementById("userLocked").checked = false;
		}
	}
	/**
	 * 修改用户
	 */
	function submitModify(userID) {

		var cd = 0;
		if (document.getElementById("userLocked").checked) {
			cd = 0;//true 锁定
		} else {
			cd = 1;//false 未锁定
		}

		var testRex = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;

		var testStr = $("#userEmail").val();
		if (testStr == "") {
		}
		else {
			if (testRex.test(testStr)) {

			} else {
				layer.msg('邮箱格式不正确，请重新输入。', {icon: 7});
				return false;
			}
		}
		miniAjax('access/user_and_permission/userModify.do',
			{
				'email': $("#userEmail").val(),
				'locked': cd,
				'usercode': $("#userUsercode").val(),
				'username': $("#userUsername").val(),
				'id': userID
			},function (data) {
				$("#myModalSubmit").removeAttr("disabled");
				$("#myModal").modal('hide');
				appendAlertInfo("alertDivSID", data.flag + " " + data.message);
			}
		);
	}

	/**
	 * 更改状态
	 */
	function submitModifyState(id) {
		miniAjax('access/user_and_permission/userChangeState.do',
			{
				'userId': id
			},function (data) {
				appendAlertInfo("alertDivSID", data.flag + " " + data.message);
			}
		);
	}

	function init() {

	}
	init();
</script>
</html>