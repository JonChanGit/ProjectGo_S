<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="baseurl" value="${pageContext.request.contextPath}/"></c:set>
<c:set var="navID_sess" value="${(empty param.navID)?navID_sess:param.navID}" scope="session"></c:set>
<jsp:useBean id="now" class="java.util.Date" /><%-- 可以使用该对象获取到时间 --%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//相对于主站位置
%>
