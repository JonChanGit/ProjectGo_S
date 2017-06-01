<%@page import="com.mz.util.StackTraceUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" errorPage="true"
    pageEncoding="UTF-8"%>
<%@ include file="../admin/linkfile/tag.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Error!<c:out value="${app_PageTitle}" default=" -- [未设置应用程序名称]"></c:out></title>
     <base href="<%=basePath %>" />
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <!-- Bootstrap 3.3.2 -->
    <link href="dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- Font Awesome Icons -->
    <link href="dist/comstomer/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Ionicons -->
    <link href="dist/comstomer/css/ionicons.min.css" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href="dist/AdminLTE/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <!-- AdminLTE Skins. Choose a skin from the css/skins 
         folder instead of downloading all of them to reduce the load. -->
    <link href="dist/AdminLTE/dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
  </head>
  <body class="skin-blue sidebar-collapse">
    <div class="wrapper">
      
      
      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        

        <!-- Main content -->
        <section class="content">

          <div class="error-page">
            <h2 class="headline text-yellow">拒绝<br/>服务</h2>
            <div class="error-content">
              <h3><i class="fa fa-warning text-yellow"></i> Oops! 发生了一些错误..</h3>
              <p>
              	<%Exception ex =  (Exception)request.getAttribute("exception"); %>
              	<h4><strong>	<%=ex.getMessage() %></strong></h4> 发生了一些意料之外或意料之中的事
                如果错误循环发生，请 <a href='//www.jonpad.com.cn' target="_blank">反馈/联系</a> 给开发者.
                <p>可能原因如下：</p>
                <ol>
                	<li>没有访问权限</li>
                	<li>没有登录</li>
                	<li>服务器内部错误</li>
                </ol>
              </p>
			<div class="btn-group" role="group" style="width: 100%;">
				<button type="button" class="btn btn-default" style="width: 50%;" onclick="history.go(-1)"  >返回重试</button>
				<button type="button" class="btn btn-default" style="width: 50%;" onclick="window.location='//${ServiceHost}'"  >返回首页</button>
			</div>
<!--               <button class="btn btn-default btn-block" onclick="history.go(-1)">返回重试</button> -->
            </div><!-- /.error-content -->
            <br/>
            <div class="box box-danger collapsed-box">
                <div class="box-header with-border">
                  <h3 class="box-title">错误堆栈</h3>
                  <div class="box-tools pull-right">
                    <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>
                  </div>
                </div><!-- /.box-header -->
                <div class="box-body no-padding" style="display: none;">
                  <div class="row">
                    <div class="col-md-12 col-sm-12">
                     <p class="lead">
                     	不给予显示<br/>
                     	 <%=StackTraceUtils.getStackTrace(ex) %>
                     </p>
                      
                    </div>
                  </div><!-- /.col -->
                </div><!-- /.row -->
             </div><!-- /.box-body -->
            </div>
          </div><!-- /.error-page -->
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
      <footer class="main-footer">
        <div class="pull-right hidden-xs">
          <b>Version</b> 2.0
        </div>
        <strong>Copyright &copy; 2014-2015 <a href="http://almsaeedstudio.com">Almsaeed Studio</a>.</strong> All rights reserved.
      </footer>
    </div><!-- ./wrapper -->

    <!-- jQuery 2.1.3 -->
    <script src="dist/AdminLTE/plugins/jQuery/jQuery-2.1.3.min.js"></script>
    <!-- Bootstrap 3.3.2 JS -->
    <script src="dist/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- FastClick -->
    <script src='dist/AdminLTE/plugins/fastclick/fastclick.min.js'></script>
    <!-- AdminLTE App -->
    <script src="dist/AdminLTE/dist/js/app.min.js" type="text/javascript"></script>
    <!-- AdminLTE for demo purposes -->
  </body>
</html>
