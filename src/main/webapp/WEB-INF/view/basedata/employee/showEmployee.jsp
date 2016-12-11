<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>锦州供电段信息平台</title>
<meta name="description" content="Static &amp; Dynamic Tables" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<%@ include file="../../common/common.jsp"%>
<link rel="stylesheet" href="${path}/static/css/newTable.css" />
<style type="text/css">
.validate{
	color: red;
}
</style>
<script type="text/javascript">
<!--

$(function(){
	$('input').attr("readonly","readonly");
})

function closed(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}
//-->
</script>
</head>
<body style="background: #fafafa;">
<div style="background: #fafafa; width: 100%; height: 100%">
<form id="employeeForm" action="${path}/employee/saveOrAddEmployee" style="margin-left: 10px;margin-right: 10px;margin-top: 15px;">
	<input type="hidden" id="id" name="id" value="${user.id}" />
	<table width="100%" align="center" class="newTable" cellspacing="0" bcellpadding="0" border="0">
		<tr>
			<td align="right" width="160">姓名：</td>
			<td align="left" width="300">
				<input type="text" id="userName" maxlength="33" name="userName" value="${user.userName }" />
			</td>
			<td align="right" width="160">登录帐号：</td>
			<td align="left" width="310">
				<input type="text" id="loginName" maxlength="33" name="loginName" value="${user.loginName }"  ${type eq null?'':'disabled="disabled"'}/>
			</td>
		</tr>
		<tr>
			<td align="right" width="160">职员工号：</td>
			<td align="left" width="300">
				<input type="text" id="jobNum" name="jobNum" maxlength="33" value="${user.jobNum }" />
			</td>
			<td align="right" width="160">IP地址</td>
			<td align="left" width="310">
				<input type="text" id="ipAddress" name="ipAddress" maxlength="33" value="${user.ipAddress }" />
			</td>
		</tr>
		<tr>
			<td align="right">性别：</td>
			<td align="left">
				${user.sex=='1'?'男':'女'}
			</td>
			<td align="right">是否是系统用户：</td>
			<td align="left">
				${user.systemUser=='0'?'否':'是'}
			</td>
		</tr>
		<tr>
			<td align="right">电话：</td>
			<td align="left">
				<input type="text" id="phone" maxlength="33" ame="phone" value="${user.phone }" />
			</td>
			<td align="right">手机号：</td>
			<td align="left">
				<input type="text" id="mobile" maxlength="33" name="mobile" value="${user.mobile }" />
			</td>
		</tr>
		<tr>
			<td align="right">家庭住址：</td>
			<td align="left" colspan="3">
				<input type="text" style="width: 70%" maxlength="333" id="address" name="address"
				value="${user.address }" />
			</td>
		</tr>
		<tr>
			<td align="right">组织机构：</td>
			<td align="left" colspan="3">
				<input type="text" style="width: 80%;" id="departmentName" name="departmentName" value="${user.departmentName}" /> 
			</td>
		</tr>
		<tr align="center">
			<td colspan="4">
				<button id="cancelBtn" class="btn  btn-sm" type="reset" onclick="closed();">
					<i class="ace-icon fa fa-undo bigger-110"></i>
					取 消
				</button>
			</td>
		</tr>
	</table>
</form>
</div>
</body>