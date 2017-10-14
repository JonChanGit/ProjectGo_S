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
     <title>功能管理<c:out value="${app_PageTitle}" default=" -- [未设置应用程序名称]"></c:out></title>
    <link href="/npmdist/static/css/module/tmp.5df445f691afa33d3fd05b20df238cfa.css?2cd1788441387fbb0162" rel="stylesheet">
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
            <li><a href="admin_access/index.do"><i class="fa fa-dashboard"></i> Home</a></li>
            <li class="active">功能管理</li>
          </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="jumbotron" id="app">
            </div>

        </section><!-- /.content -->

      </div><!-- /.content-wrapper -->
<%@include file="../linkfile/foot.jsp" %>
    </div><!-- ./wrapper -->

  </body>
<%@include file="../linkfile/footInfo.jsp" %>
  <script type="text/javascript" src="/npmdist/static/js/manifest.js?0ae93207aadd0dec48bd"></script><script type="text/javascript" src="/npmdist/static/js/vendor.js?0ae93207aadd0dec48bd"></script><script type="text/javascript" src="/npmdist/static/js/module/tmp.js?0ae93207aadd0dec48bd"></script>
  <script type="text/javascript">
function init(){

}
init();
    </script>
</html>
