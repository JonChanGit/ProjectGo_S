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
				<small>角色管理</small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="access/index.do"><i class="fa fa-dashboard"></i> Home</a></li>
				<li>用户与权限</li>
				<li class="active">角色管理</li>
			</ol>
		</section>
		<!-- Main content -->
		<section class="content">

			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">操作面板</h3></div>
				<div class="panel-body">
					<button class="btn btn-app layerDemo" id="addRolePanel" onclick="addRolePanelBtnClick();">
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
						</div><!-- /.box-header -->
						<div class="box-body">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
								<tr>
									<th>id</th>
									<th>是否可用</th>
									<th>Role</th>
									<th>操作</th>
								</tr>
								</thead>
								<tbody>
								<c:forEach items="${roleUser}" var="item">
									<tr>
										<td>${item.id}</td>
										<td>
											<c:if test="${item.available == 0}">
												<span class="label label-default">不可用</span>
											</c:if>
											<c:if test="${item.available == 1}">
												<span class="label label-info">可用</span>
											</c:if>
										</td>
										<td>${item.name}</td>
										<td>
											<div class="btn-group" role="group" aria-label="${item.name}的操作">


												<button type="button" class="btn btn-primary"
														aria-label="编辑${item.name}"
														onclick="showUXDialogModify(${item.id})">编辑
												</button>

												<c:if test="${item.available == 1}">
													<button onclick="submitModifyState(${item.id})" type="button"
															class="btn btn-warning" aria-label="禁用${item.name}">禁用
													</button>
												</c:if>
												<c:if test="${item.available == 0}">
													<button onclick="submitModifyState(${item.id})" type="button"
															class="btn btn-success" aria-label="禁用${item.name}">启用
													</button>
												</c:if>
												<button onclick="submitDeleteState(${item.id})" type="button"
														class="btn btn-danger" aria-label="删除${item.name}">删除
												</button>
												<button onclick="assignMenu(${item.id})" type="button"
														class="btn btn-danger" aria-label="功能管理${item.name}">功能管理
												</button>
											</div>
										</td>
									</tr>
								</c:forEach>
								</tbody>
								<tfoot>
								<tr>
									<th>id</th>
									<th>是否可用</th>
									<th>Role</th>
									<th>操作</th>
								</tr>
								</tfoot>
							</table>
						</div><!-- /.box-body -->
					</div><!-- /.box -->

				</div>
			</div>

		</section><!-- /.content -->


	</div><!-- /.content-wrapper -->
	<%@include file="../linkfile/foot.jsp" %>
</div><!-- ./wrapper -->

</body>
<%@include file="../linkfile/footInfo.jsp" %>

<template id="myformDiv">
  <h4>新角色名称</h4>
  <div class="input-group">
    <input type="hidden"  id="roleID" value="{{id}}">
    <span class="input-group-addon">
                      <input type="checkbox" checked="checked" id="formCheckBox">
                    </span>
    <input id="roleDes" class="form-control" type="text" placeholder="角色名称"/>
  </div>
</template>

<script type="text/javascript">
  function addRolePanelBtnClick () {
    layer.open({
      title:'添加角色',
      content: $('#myformDiv').html(),
      yes: function(index){
        if($('#roleDes').val() == ''){
          layer.msg('请输入角色名称', {icon: 7});
          return false;
        }
        var cd = 0;
        if(document.getElementById('formCheckBox').checked){
          cd = 1;
        }
        miniAjax('access/user_and_permission/roleAdd.do',
          {
            'name': $('#roleDes').val(),
            'available': cd
          }, function (data) {
            appendAlertInfo('alertDivSID', data.flag + ' ' + data.message);
          }
        );
        layer.close(index);
      }
    });
  }

	/**
	 * 提交新角色
	 */
	function submitNew() {
		if($("#roleDes").val() == ""){
			layer.msg('请输入角色名称', {icon: 7});
			return false;
		}
		var cd = 0;
		if(document.getElementById("formCheckBox").checked){
			cd = 1;
		}
		miniAjax('access/user_and_permission/roleAdd.do',
			{
				'name': $("#roleDes").val(),
				'available': cd
			}, function (data) {
				appendAlertInfo("alertDivSID", data.flag + " " + data.message);
			}
		);
	}

	function modifyCof(userID, checked_lock) {
		layer.open({
			title: '修改用户(不填写为不更改)'
			, content: myformDiv,
			yes: function () {
				submitModify(userID);
			}
		});
		//更改按钮状态
		if (checked_lock == 0) {
			document.getElementById("userLocked").checked = true;
		} else {
			document.getElementById("userLocked").checked = false;
		}
	}

	function showUXDialogModify(roleId) {
		var html = Mustache.render($('#myformDiv').html(), {'id':roleId});
		layer.open({
			title: '${app_PageTitle}'
			, content: html,
			yes: submitModify
		});
	}


	/**
	 * 修改角色
	 */
	function submitModify() {

		if($("#roleDes").val() == ""){
			alert("请输入角色名称");
			$("#myModalSubmit").removeAttr("disabled");
			return false;
		}

		miniAjax('access/user_and_permission/roleModify.do',
			{
				'name': $("#roleDes").val(),
				'id': $("#roleID").val()
			}, function (data) {
				appendAlertInfo("alertDivSID", data.flag + " " + data.message);
			}
		);
	}

	/**
	 * 更改状态
	 */
	function submitModifyState(id) {
		miniAjax('access/user_and_permission/roleChangeState.do',
			{
				'id': id
			}, function (data) {
				appendAlertInfo("alertDivSID", data.flag + " " + data.message);
			}
		);
	}
	/**
	 * 删除
	 * @param id
	 */
	function submitDeleteState(id) {
		miniAjax('access/user_and_permission/roleDelete.do',
			{
				'id': id
			}, function (data) {
				appendAlertInfo("alertDivSID", data.flag + " " + data.message);
			}
		);
	}

	function assignMenu(id) {
    layer.open({
      type: 2,
      id:'assignIframe',
      'area': ['540px', '350px'],
      yes: function (index, layero) {
        var iframeWin = window[layero.find('iframe')[0]['name']];
        var my_vue = iframeWin.my_vue;

      },
      btn :'确定',
      content: ['access/user_and_permission/assignIndex.do', 'no'],
    });
  }

	function init() {

	}
	init();
</script>
</html>
