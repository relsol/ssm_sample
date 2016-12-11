<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<table class="newTable datagrid" width="100%" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<th width="10%">序号</th>
		<th width="15%">机构名称</th>
		<th width="15%">值班电话</th>
		<th>机构地址</th>
		<th width="15%">类别</th>
		<th width="15%">操作</th>
	</tr>
	<c:if test="${empty departmentList}">
		<tr><td colspan="6">没有记录</td></tr>
	</c:if>
  <c:forEach items="${departmentList}" var="data" varStatus="count">
  	<tr align="center">
  		<td>${count.count}</td>
  		<td>${data.name}</td>
  		<td>${data.dutyPhone}</td>
  		<td>${data.address}</td>
  		<td>${typeMap[data.address]}</td>
  		<td>
			<a href="###" onclick="editOne('${data.id}')">修改</a>
			&nbsp;&nbsp;&nbsp;
			<a href="###" onclick="delOne('${data.id}')">删除</a>
		</td>
  	</tr>
     </c:forEach>
</table>
