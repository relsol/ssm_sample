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

$(document).ready(function(){
	$("#addPermissionForm").validate({
		errorClass: "validate",
		errorElement : 'div',
		rules:{
			name:{
				required : true,
				maxlength : 20
			},
			uri:"required",
			level:{
				required:true,
				remote:{
					url: "${path }/permission/checkLevel?id=${pid}&type=${type}",
		       	    type: 'POST',
		       	 	async:false,
		       	    dataType: 'json'
				}
			},
			order:"number"
		},
		messages:{
			name : {
				required:"请输入权限名称！",
				maxlength:"最多20字符！"
			},
			uri:"请输入URL!",
			level:{
				required:"请选择级别！",
				remote:"只能选择次一级的级别！"
			},
			order:"请输入合法数字!"
		}
	});
});


function closed(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}

function doSave(){
	var _index = parent.layer.load("请稍候....");
	var options = {
		type:'post',
		dataType:'json',
		beforeSubmit:function(){
			if($("#addPermissionForm").valid()){
				return true;
			} else {
				parent.layer.close(_index);
				return false;
			}
		},
		success:function(json){
			parent.layer.close(_index);//去掉遮罩
			if(json.success){
				parent.layer.msg("操作成功!", {icon: 1, time: 1000}, function(){
					var id = $("#id").val();
					if(id){
						parent.editTreeNode("edit", json.data);
					} else {
						parent.editTreeNode("add", json.data);
					}
					closed();
				});
			} else {
				parent.layer.msg(json.msg, {icon: 2});
			}
   		},
   		error:function(){
   			parent.layer.close(_index);
   			parent.layer.msg("请求服务器失败,请检查网络!", {icon: 2});
   		}
	};
	$('#addPermissionForm').ajaxSubmit(options);
}

</script>
</head>
<body style="background: #fafafa;">
	<br />
	<form id="addPermissionForm" method="post" action="${path}/permission/addPermission" onsubmit="return false;">
		<input type="hidden" id="pid" name="pid" value="${pid}" /> 
		<input type="hidden" id="projectId" name="projectId" value="${projectId }"/>
		<input type="hidden" id="id" name="id" value="${permission.id}" />
		<div style="margin: 0 10px;">
			<table width="100%" align="center" class="newTable" cellspacing="0" cellpadding="0" border="0">
				<tbody id="	">
					<tr>
						<td align="right" width="30%">权限名称:</td>
						<td align="left" width="70%">
							<input type="text" class="easyui-textbox" id="name" name="name" value="${permission.name }"/>
						</td>
					</tr>
					<tr>
						<td align="right">URL:</td>
						<td align="left">
							<input type="text" style="width: 300px" id="uri" name="uri" value="${permission.uri }" />
						</td>
					</tr>
					<tr>
						<td align="right" >级别:</td>
						<td align="left">
							<select id="level" name="level" class="easyui-combobox" panelHeight="auto">
								<option value="-1">请选择</option>
								<c:forEach items="${LEVEL}" var="obj">
									<option value="${obj.key}" ${obj.key == permission.level ? 'selected="selected"' : "" }>${obj.value}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">排序:</td>
						<td align="left">
							<input type="text" id="order" name="order" value="${permission.order }" />
						</td>
					</tr>
					<tr>
						<td align="right">备注:</td>
						<td align="left">
							<input type="text" id="remark" name="remark" value="${permission.remark }" style="width: 300px"/>
						</td>
					</tr>
				</tbody>
				<tfoot>
		        <tr>
					<td colspan="4" align="center">
						<button class="btn btn-info" type="button" onclick="doSave()">
							<i class="ace-icon fa fa-check bigger-110"></i>
							提交
						</button>
							 &nbsp;&nbsp;&nbsp; 
						<button class="btn" type="reset" onclick="closed();">
							<i class="ace-icon fa fa-undo bigger-110"></i>
							关闭
						</button>
					</td>	        
		        </tr>
	        </tfoot>
			</table>
		</div>
	</form>
</body>
</html>
