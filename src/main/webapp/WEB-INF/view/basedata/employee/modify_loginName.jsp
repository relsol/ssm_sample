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
<script type="text/javascript" src="${path }/static/assets/js/jquery.validate.min.js"></script>
<script type="text/javascript">
	
	var _flg = false;
	$(document).ready(function(){
		$("#listEmployeeFrom").validate({
			errorClass: "validate",
			errorElement : 'span',
			rules:{
				loginName:{
					required: true,
					maxlength:20,
					remote:{
						url: "${path}/employee/checkLoginName",
			       	    type: 'POST',
			       	 	async: false,
			       	    dataType: 'json',
				       	data: {
							id : function(){return $("#employeeId").val();},
							loginName : function(){return $("#loginName").val();}
						},
					},
					
				},
				oldPassword:{
					required: true,
					remote:{
						url: "${path}/employee/checkPwd",
			       	    type: 'POST',
			       	 	async: false,
			       	    dataType: 'json'
					}
				},
			},
			messages:{
				loginName:{
					required:"提示：请输入新登录名！",
					maxlength:"提示：最多20个字符！",
					remote:"提示：此用户名已被占用！"
				},
				oldPassword:{
					required:"提示：请输入密码！",
					remote:"提示：密码错误！"
				},
			
			}
		});
		
		$("#doSave").click(function(){
			var i = layer.load(0,1);//提交加上遮罩
			var loginName = $("#loginName").val();
			$.ajax({
				url:"${path}/employee/modifyLoginName",
				data:{"loginName": loginName, "employeeId":$("#employeeId").val()},
				type:"post",
				cache: false,
				beforeSend:function(XMLHttpRequest){
					if($("#listEmployeeFrom").valid()){
						return true;
					} else {
						layer.close(i);
						return false;
					}
				},
				success: function(data){
					layer.close(i);//去掉遮罩
					if(data == "success"){
						parent.layer.msg("操作成功!", {icon: 1, time:1000}, function(){
							parent.$('#modifyLoginNameLi').hide();
							closed();
						});
					} else {
						parent.layer.msg(data, {icon: 2});
					}
				},
				error: function(){
					layer.close(i);//去掉遮罩
					alert('请求服务器失败,请检查网络!');
				}
			});
		});
	});	
	
	function closed(){
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}
</script>
</head>
<body style="background: #fafafa;">
	<!-- TipBar End -->
	<form action="${path}/employee/findAll" id="listEmployeeFrom" method="post">
		<input type="hidden" id="employeeId" value="${employeeId}"/>
		<div style="margin: 10px">
			<table style="margin-top: 20px; width: 90%" align="center" class="newTable" >
				<tr>
					<td align="right" width="25%">姓名：</td>
					<td>${employee.userName}</td>
				</tr>
				<tr>
					<td align="right">原登录名：</td>
					<td>${employee.loginName}</td>
				</tr>
				<tr>
					<td align="right">新登录名：<font color="red">*</font></td>
					<td><input type="text" id="loginName" name="loginName"/></td>
				</tr>
				<tr>
					<td align="right">请输入密码：<font color="red">*</font></td>
					<td><input type="text" id="oldPassword" name="oldPassword"/></td>
				</tr>
				<tr align="center">
					<td colspan="2">
						<button class="btn btn-sm btn-info" type="button" id="doSave">
							<i class="ace-icon fa fa-check bigger-110"></i>
							保 存
						</button>
							 &nbsp;&nbsp;&nbsp; 
						<button id="cancelBtn" class="btn btn-sm" type="reset" onclick="closed();">
							<i class="ace-icon fa fa-undo bigger-110"></i>
							取 消
						</button>
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>
