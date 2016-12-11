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
</head>
<body>
	<div class="page-content">
		<div class="page-content-area">
			<div class="row">
				<form action="${path }/role/addRole" id="myForm" role="form" class="col-xs-12">
					<input type="hidden" id="id" name="id" value="${role.id}" /> 
					<input type="hidden" id="status" name="status" value="${role.status}" />
					<table width="100%" align="center" class="newTable" cellspacing="0" cellpadding="0" border="0">
						<tr>
							<td align="right" width="30%">所属系统：</td>
							<td width="70%">
								<select id="projectId" name="projectId">
					 				<option value="-1">请选择</option>
					 				<c:forEach items="${proList}" var="data">
					 					<c:if test="${data.id eq role.projectId}">
						 					<option value="${data.id}" selected="selected">${data.sysName}</option>
					 					</c:if>
					 					<c:if test="${data.id ne role.projectId}">
						 					<option value="${data.id}">${data.sysName}</option>
					 					</c:if>
					 				</c:forEach>
					 			</select>
							</td>
						</tr>
						<tr>
							<td align="right"><font color="red">*</font>角色编号：</td>
							<td align="left">
								<input id="code" name="code"  type="text" value="${role.code}" ${role.id == null?'':'disabled="disabled"'}  />
							</td>
						</tr>
						<tr>
							<td align="right"><font color="red">*</font>角色名称：</td>
							<td align="left">
								<input id="name" name="name"  type="text" value="${role.name}" />
							</td>
						</tr>
						<tr>
							<td align="right" valign="top">备注：</td>
							<td><textarea id="remark" name="remark" class="form-control" >${role.remark}</textarea></td>
						</tr>
						<tr>
							<td align="center" colspan="2" valign="top">
								<button class="btn btn-sm btn-info" type="button" onclick="saveOrUpdate()">
									<i class="ace-icon fa fa-check bigger-110"></i>
									提交
								</button>
									 &nbsp;&nbsp;&nbsp; 
								<button class="btn btn-sm" type="reset" onclick="closed();">
									<i class="ace-icon fa fa-undo bigger-110"></i>
									关闭
								</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	$().ready(function(){
		$("#myForm").validate({
			errorClass: "validate",
			errorElement : 'div',
			rules:{
				name:{
					required: true,
					maxlength:20
				},
				code:{
					required: true,
					maxlength:20,
				/* 	remote:{
						url: "${path }/role/checkCode.html",
			       	    type: 'POST',
			       	 	async:false,
			       	    dataType: 'json'
					} */
				}
			},
			messages:{
				name:{
					required:"请输入角色名称！",
					maxlength:"最多20个字符！"
				},
				code:{
					required:"请输入角色编号！",
					maxlength:"最多20个字符！",
				/* 	remote:"角色编号已存在" */
				}
			}
		});
	});

		function saveOrUpdate() {
			var _index = parent.layer.load("请稍候....");
			var options = {
				type:'post',
				beforeSubmit:function(){
					if($("#myForm").valid()){
						return true;
					} else {
						parent.layer.close(_index);
						return false;
					}
				},
				success:function(data){
					parent.layer.close(_index);
					if(data = "success"){
						parent.layer.msg("操作成功!", {icon: 1, time:1000}, function(){
								closed();
							}
						);
					} else {
						parent.layer.msg(data, {icon: 2});
					}
		   		},
		   		error:function(){
		   			parent.layer.close(_index);
		   			parent.layer.msg("请求服务器失败,请检查网络!", {icon: 2});
		   		}
			};
			$('#myForm').ajaxSubmit(options);
		}

		function closed() {
			var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
		}
	</script>
</body>
</html>
