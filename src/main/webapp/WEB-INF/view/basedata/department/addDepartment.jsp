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
<script type="text/javascript" src="${path }/static/js/jquery/jquery-validate-methods.js"></script>
<script type="text/javascript">
<!--
$(function(){
	$("#addDepartForm").validate({
		errorClass: "validate",
		errorElement : 'div',
		rules:{
			name:{
				required: true,
				maxlength:30
			},
			order:{
				number: true,
				maxlength:3
			},
			address:{
				maxlength: 100
			},
			remark:{
				maxlength: 100
			}
		},
		messages:{
			name:{
				required: "提示：请输入机构名称！",
				maxlength:"提示：最多30个字符！"
			},
			order:{
				number:"提示：请输入数值类型！",
				maxlength:"提示：最多3个数字！"
			},
			address:{
				maxlength:"提示：最多100个字符！"
			},
			remark:{
				maxlength:"提示：最多100个字符！"
			}
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
		beforeSubmit:function(){
			if($("#addDepartForm").valid()){
				return true;
			} else {
				parent.layer.close(_index);
				return false;
			}
		},
		success:function(json){
			parent.layer.close(_index);//去掉遮罩
//         	var json = jQuery.parseJSON(data);  
			if(json.success){
				parent.layer.msg("操作成功!", {icon: 1, time:1000}, function(){
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
	$('#addDepartForm').ajaxSubmit(options);
}

//-->		
</script>
</head>
<body style="background: #fafafa;">
	<br />
	<form id="addDepartForm" method="post" action="${path}/department/addDepartment" onsubmit="return false;">
		<input type="hidden" id="pid" name="pid" value="${pid}" /> 
		<input type="hidden" id="id" name="id" value="${department.id}" />
		<input type="hidden" value="${department.status}" id="status" name="status"/>
		<div style="margin: 0 10px;">
			<table width="100%" align="center" class="newTable" cellspacing="0" cellpadding="0" border="0">
				<tbody id="employeeContent">
					<tr>
						<td align="right" width="30%">机构名称:</td>
						<td align="left" width="70%">
							<input type="text" id="name" name="name" value="${department.name }"/>
						</td>
					</tr>
					<tr>
						<td align="right" >机构类型:</td>
						<td align="left">
							<select id="type" name="type">
								<option value="-1">请选择</option>
								<c:forEach items="${type_map}" var="obj">
									<option value="${obj.key}" ${obj.key eq department.type?'selected="selected"':''}>${obj.value}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">值班电话:</td>
						<td align="left">
							<input type="text" id="dutyPhone" name="dutyPhone" value="${department.dutyPhone }" />
						</td>
					</tr>
					<tr>
						<td align="right">排序号:</td>
						<td align="left">
							<input type="text" id="order" name="order" value="${department.order }" />
						</td>
					</tr>
					<tr>
						<td align="right">机构地址:</td>
						<td align="left">
							<input type="text" id="address" name="address" value="${department.address }" style="width: 300px"/>
						</td>
					</tr>
					<tr>
						<td align="right">备注:</td>
						<td align="left" colspan="3"><textarea rows="3" cols="40" id="remark" name="remark">${department.remark}</textarea></td>
					</tr>
				</tbody>
				<tfoot>
		        <tr>
					<td colspan="4" align="center">
						<button class="btn btn-sm btn-info" type="button" onclick="doSave()">
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
	        </tfoot>
			</table>
		</div>
	</form>
</body>
</html>
