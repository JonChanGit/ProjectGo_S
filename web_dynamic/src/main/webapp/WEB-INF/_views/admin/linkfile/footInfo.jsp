<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
//String path = request.getContextPath();
//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 <!-- jQuery 2.1.3 -->
    <script src="require/AdminLTE/plugins/jQuery/jQuery-2.1.3.min.js"></script>
    <!-- jQuery UI 1.11.2 -->
    <script src="require/comstomer/js/jquery-ui.min.js" type="text/javascript"></script>
    <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
    <script>
      $.widget.bridge('uibutton', $.ui.button);
    </script>
    <!-- Bootstrap 3.3.2 JS -->
    <script src="require/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- Morris.js charts -->
    <script src="require/comstomer/js/raphael-min.js"></script>
    <script src="require/AdminLTE/plugins/morris/morris.js" type="text/javascript"></script>
    <!-- Sparkline -->
    <script src="require/AdminLTE/plugins/sparkline/jquery.sparkline.min.js" type="text/javascript"></script>
    <!-- jvectormap -->
    <script src="require/AdminLTE/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js" type="text/javascript"></script>
    <script src="require/AdminLTE/plugins/jvectormap/jquery-jvectormap-world-mill-en.js" type="text/javascript"></script>
    <!-- jQuery Knob Chart -->
    <!--<script src="require/AdminLTE/plugins/knob/jquery.knob.js" type="text/javascript"></script>-->
    <!-- daterangepicker -->
    <script src="require/AdminLTE/plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script>
    <script src="require/AdminLTE/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js" type="text/javascript"></script>
    <!-- datepicker -->
    <script src="require/AdminLTE/plugins/datepicker/bootstrap-datepicker.js" type="text/javascript"></script>
    <!-- Bootstrap WYSIHTML5 -->
    <script src="require/AdminLTE/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js" type="text/javascript"></script>
    <!-- iCheck -->
<!--     <script src="require/AdminLTE/plugins/iCheck/icheck.min.js" type="text/javascript"></script> -->
    <!-- Slimscroll -->
    <script src="require/AdminLTE/plugins/slimScroll/jquery.slimscroll.min.js" type="text/javascript"></script>
    <!-- FastClick -->
    <script src="require/AdminLTE/plugins/fastclick/fastclick.min.js"></script>
    <!-- AdminLTE App -->
    <script src="require/AdminLTE/dist/js/app.min.js" type="text/javascript"></script>
    <script src="require/pulgins/layui/layui.js"></script>
    <script src="require/pulgins/mustache.js"></script>

    <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<!--     <script src="require/AdminLTE/dist/js/pages/dashboard.js" type="text/javascript"></script> -->

    <!-- AdminLTE for demo purposes -->
    <!-- <script src="require/AdminLTE/dist/js/demo.js" type="text/javascript"></script> -->

    <!--自定义JS-->
    <script src="require/comstomer/js/def.js" type="text/javascript"></script>
    <!-- 服务JS-->
    <script src="require/comstomer/js/service.js" type="text/javascript"></script>
    <script src="require/comstomer/js/alert.js" type="text/javascript"></script>
    <script type="application/javascript">
    	$(document).ready(function(){
    		appendAlertSuccess("alertDivSID","页面加载完成");
    	});
    </script>
