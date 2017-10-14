<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../admin/linkfile/tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>登录 | <c:out value="${app_PageTitle}" default=" -- [未设置应用程序名称]"></c:out></title>
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
        <a href="#"><img src="require/img/logo.png" height="60px"/></a>
      </div><!-- /.login-logo -->
      <div class="login-box-body">
        <p class="login-box-msg">登陆</p>

        <c:if test="${!empty msg}">
        	<div class="alert alert-warning" role="alert">${msg}</div>
        </c:if>

        <form action="login.do" method="post" id="login-form">
          <div class="form-group has-feedback">
            <input autofocus type="email" class="form-control" placeholder="请输入邮箱" name="username" id="uemail" required="required"/>
            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <input  type="password" class="form-control" placeholder="请输入密码" name="password" id="upassword" required="required"/>
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
          <div class="input-group input-group-lg">
          		<input type="text" class="form-control" placeholder="请输入验证码" aria-describedby="sizing-addon1" name="randomcode" required="required">
          		<span class="input-group-btn">
          			<button onclick='reloadImg();' class="btn btn-default" type="button">
          				<img  id="randomcode_img" src="validatecode.jsp" alt="图像无法显示，请刷新页面" width="56" height="20" align='absMiddle' />
       				</button>
        		</span>
          </div>
          <div class="row">
            <div class="col-xs-4">
              <div class="checkbox icheck">
                <label>
                  <input type="checkbox" name="rememberMe"> 记住我
                </label>
              </div>
            </div><!-- /.col -->
            <div class="col-xs-4">
              <div class="checkbox icheck">
                <label>
                	<a href="register.do">没有账号?</a>
                	<!--<a href="#">修改密码</a>  -->
                </label>
              </div>
            </div><!-- /.col -->
            <div class="col-xs-4">
              <button type="submit" class="btn btn-primary btn-block btn-flat" id="btn-submit"  >登录</button>
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
      $(function () {
        $('input').iCheck({
          checkboxClass: 'icheckbox_square-blue',
          radioClass: 'iradio_square-blue',
          increaseArea: '20%' // optional
        });
      });

      function reloadImg(){
    		document.getElementById("randomcode_img").src='validatecode.jsp?'+ new Date().getTime();
			console.log("click");
    	  //location.replace(location.href);
    	  //$("#randomcode_img").attr("src","validatecode.jsp&tp="+Math.random());
      }

    </script>
  </body>
</html>
