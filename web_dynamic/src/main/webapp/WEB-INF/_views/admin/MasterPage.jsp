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
    <base href="<%=basePath %>" />
<%@include file="linkfile/headerInfo.jsp" %>
     <title>仪表板<c:out value="${app_PageTitle}" default=" -- [未设置应用程序名称]"></c:out></title>
  </head>
  <body class="skin-black">
    <div class="wrapper">
<%@include file="linkfile/headNav.jsp" %>

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
            <li class="active">仪表板</li>
          </ol>
        </section>
        <!-- Main content -->
        <section class="content"> 
            <div class="jumbotron">
              <h1>Hello, world!</h1>
              <p>欢迎回来！</p>
              
            </div>
            
         
         		<!-- AREA CHART -->
              <div class="box box-primary">
                <div class="box-header">
                  <h3 class="box-title">站点访问统计</h3>
                </div>
                <div class="box-body chart-responsive">
                  <div class="chart" id="revenue-chart" style="height: 300px;"></div>
                </div><!-- /.box-body -->
              </div><!-- /.box -->
              
         
        </section><!-- /.content -->

      </div><!-- /.content-wrapper -->
<%@include file="linkfile/foot.jsp" %>
    </div><!-- ./wrapper -->
   
  </body>
<%@include file="linkfile/footInfo.jsp" %>
    <script type="text/javascript"> 
function init(){
     // AREA CHART
        var area = new Morris.Area({
          element: 'revenue-chart',
          resize: true,
          data: [
            {y: '2011 Q1', item1: 2666, item2: 2666},
            {y: '2011 Q2', item1: 2778, item2: 2294},
            {y: '2011 Q3', item1: 4912, item2: 1969},
            {y: '2011 Q4', item1: 3767, item2: 3597},
            {y: '2012 Q1', item1: 6810, item2: 1914},
            {y: '2012 Q2', item1: 5670, item2: 4293},
            {y: '2012 Q3', item1: 4820, item2: 3795},
            {y: '2012 Q4', item1: 15073, item2: 5967},
            {y: '2013 Q1', item1: 10687, item2: 4460},
            {y: '2013 Q2', item1: 8432, item2: 5713}
          ],
          xkey: 'y',
          ykeys: ['item1', 'item2'],
          labels: ['Item 1', 'Item 2'],
          lineColors: ['#a0d0e0', '#3c8dbc'],
          hideHover: 'auto'
        });
}
init();		 
    </script>
</html>