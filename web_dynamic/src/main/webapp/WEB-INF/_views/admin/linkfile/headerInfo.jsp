<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
//String pathhi = request.getContextPath();
//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Date date = new Date();
response.setDateHeader("Last-Modified", date.getTime());
response.setDateHeader("Expires",0);
response.setHeader("Cache-Control", "no-cache");
response.addHeader("Cache-Control", "no-store" );//
response.addHeader("Cache-Control", "must-revalidate");
response.setHeader("Refresh","1830");
%>
<script type="text/javascript">
var t=setTimeout("_time_out_refresh_page()",1800000)
function _time_out_refresh_page(){
	window.location="access/index.do";
}
</script>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="shortcut icon" type="image/x-icon" href="favicon.ico" />
    <!-- Bootstrap 3.3.2 -->
    <link href="require/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- FontAwesome 4.3.0 -->
    <link href="require/comstomer/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Ionicons 2.0.0 -->
    <link href="require/comstomer/css/ionicons.min.css" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href="require/AdminLTE/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link href="require/AdminLTE/dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />
    <!-- iCheck -->
<!--     <link href="require/AdminLTE/plugins/iCheck/flat/blue.css" rel="stylesheet" type="text/css" /> -->
    <!-- Morris chart -->
    <link href="require/AdminLTE/plugins/morris/morris.css" rel="stylesheet" type="text/css" />
    <!-- jvectormap -->
    <link href="require/AdminLTE/plugins/jvectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet" type="text/css" />
    <!-- Date Picker -->
    <link href="require/AdminLTE/plugins/datepicker/datepicker3.css" rel="stylesheet" type="text/css" />
    <!-- Daterange picker -->
    <link href="require/AdminLTE/plugins/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
    <!-- bootstrap wysihtml5 - text editor -->
    <link href="require/AdminLTE/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" rel="stylesheet" type="text/css" />
<!--     自定义CSS -->
    <link href="require/comstomer/css/def.css" rel="stylesheet" type="text/css" />

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="js/ie9_maxcdn/html5shiv.js"></script>
        <script src="js/ie9_maxcdn/respond.min.js"></script>
    <![endif]-->
    <script src="require/js/pureJS.js" type="text/javascript"></script>
