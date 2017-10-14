<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../admin/linkfile/tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>注册 | <c:out value="${app_PageTitle}" default=" -- [未设置应用程序名称]"></c:out></title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <!-- Bootstrap 3.3.2 -->
    <link href="require/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <!-- Font Awesome Icons -->
    <link href="require/comstomer/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href="require/AdminLTE/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <!-- iCheck -->
    <link href="require/AdminLTE/plugins/iCheck/square/blue.css" rel="stylesheet" type="text/css" />

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
  </head>
  <body class="login-page">
    <div class="login-box">
      <div class="login-logo">
        <a href="#"><img src="require/img/logo.png" height="45px"/></a>
      </div><!-- /.login-logo -->
      <div class="login-box-body">
        <p class="login-box-msg">注册新账号</p>
        <c:if test="${!empty message}">
        	<div class="alert alert-warning" role="alert">${message}</div>
        </c:if>

        <form action="register.do" method="post" id="login-form">

			<div class="form-group">
			<label for="exampleInputName2">姓名</label>
			<input type="text" class="form-control" name="userName" placeholder="请输入你的姓名">

			  </div>
			<div class="form-group">
			<label for="exampleInputEmail2">Email</label>
			<input type="email" class="form-control" name="userEmail" placeholder="请输入你的邮箱">
			</div>

			<div class="form-group">
			<label for="exampleInputEmail2">性别</label>
			<label class="radio-inline">
			  <input type="radio" name="uSex"   value="男"> 男
			</label>
			<label class="radio-inline">
			  <input type="radio" name="uSex"  value="女"> 女
			</label>
			</div>

			<div class="form-group">
			<label for="exampleInputPassword1">设置密码</label>
			<input type="password" class="form-control" name="uPassword" placeholder="设置密码">
			</div>

			<div class="form-group">
			<label for="exampleInputName2">学号</label>
			<input type="text" class="form-control" name="uNumber" placeholder="请输入你的学号">
			</div>

			<div class="form-group">
			<label for="exampleInputName2">班级</label>
			<select class="form-control" name="cla">
				<c:forEach items="${list}" var="item">
					<option value="${item.id}">${item.className }</option>
				</c:forEach>
			</select>
			</div>

          <div class="row">
            <div class="col-xs-8">
              <div class="checkbox icheck">
                <label>

                </label>
              </div>
            </div><!-- /.col -->
            <div class="col-xs-4">
              <button type="submit" class="btn btn-primary btn-block btn-flat" id="btn-submit"  >注册</button>
            </div><!-- /.col -->
          </div>
        </form>

        <div class="social-auth-links text-center" id="warningdiv" >
          <!--消息提示预留位-->
        </div><!-- /.social-auth-links -->

        <!-- <a href="#">忘记密码？</a><br>
        <a href="register.html" class="text-center">没有账号？</a> -->

      </div><!-- /.login-box-body -->
    </div><!-- /.login-box -->



    <!-- jQuery 2.1.3 -->
    <script src="require/js/jquery.min.js"></script>
    <!-- Bootstrap 3.3.2 JS -->
    <script src="require/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- iCheck -->
    <script src="require/AdminLTE/plugins/iCheck/icheck.min.js" type="text/javascript"></script>
    <script>


    </script>
  </body>
</html>
