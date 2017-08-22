<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<c:if test="${page.totalElements > 0 }">
	<ul class="pagination pagination-sm no-margin pull-right">
		<li><a href=" ">«</a></li>
		<c:forEach begin="${pageInfo.beginPageIndex }" end="${pageInfo.endPafeIndex }" var="item">
			
			<c:if test="${item== pageInfo.currentPage}">
				<li class="active"><a href="<c:out value="${pageInfo.uri}" default="#"></c:out>page=<c:out value="${item}" default="1"></c:out>"><c:out value="${item}" default="1"></c:out></a></li>
			</c:if>
			<c:if test="${item != pageInfo.currentPage}">
				<li ><a href="<c:out value="${pageInfo.uri}" default="#"></c:out>page=<c:out value="${item}" default="1"></c:out>"><c:out value="${item}" default="1"></c:out></a></li>
			</c:if>
		</c:forEach>
		<li><a href="<c:out value="${pageInfo.uri}" default="#"></c:out>page=${pageInfo.endPafeIndex }">»</a></li>
	</ul>
</c:if>
