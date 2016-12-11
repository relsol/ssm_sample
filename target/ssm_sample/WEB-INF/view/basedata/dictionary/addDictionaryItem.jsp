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
<!--
$().ready(function(){
	$("#addDictionaryItemForm").validate({
		errorClass: "validate",
		errorElement : 'div',
		rules:{
			name:{
				required: true,
				maxlength:20
			},
			order:{
				number: true,
				maxlength:3
			},
			remark:{
				maxlength:333
			}
		},
		messages:{
			name:{
				required:"提示:属性名称不能为空!",
				maxlength:"提示:最多20个字符！"
			},
			code:{
				number:"提示:排序号应为整数!",
				maxlength:"提示:最多3个字符！"
			},
			remark:{
				maxlength:"提示:最多333个字符！"
			}
		}
	});
});

function closed(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}

function doSaveDictionaryItem(){
	var _index = parent.layer.load("请稍候....");
	var options = {
		type:'post',
		dataType:'json',
		beforeSubmit:function(){
			if($("#addDictionaryItemForm").valid()){
				return true;
			} else {
				parent.layer.close(_index);
				return false;
			}
		},
		success:function(data){
			parent.layer.close(_index);
			if(data.success){
				parent.layer.msg("操作成功!", {icon: 1, time:1000}, function(){
						closed();
					}
				);
			} else {
				parent.layer.msg("操作失败!", {icon: 2});
			}
   		},
   		error:function(){
   			parent.layer.close(_index);
   			parent.layer.msg("请求服务器失败,请检查网络!", {icon: 2});
   		}
	};
	$('#addDictionaryItemForm').ajaxSubmit(options);
}

//-->		
</script>
</head>
<body style="background: #fafafa;">
	<br />
	<form id="addDictionaryItemForm" method="post" action="${path}/dictionary/saveDictionaryItem" onsubmit="return false;">
		<input type="hidden" name="id" value="${dictionaryItem.id}" />
		<input type="hidden" name="dictionaryId" value="${dictionary.id}" />
		<input type="hidden" name="dictionaryCode" value="${dictionary.code}" />
		<div style="margin: 0 10px;">
			<table width="100%" align="center" class="newTable" cellspacing="0" cellpadding="0" border="0">
				<tbody id="employeeContent">
					<tr>
						<td align="right" width="200">属性名称:<font color="red">*</font></td>
						<td align="left" width="300">
							<input type="text" id="name" name="name" value="${dictionaryItem.name }"/>
							<div id="nameMsg" style="color: red"></div>
						</td>
						<td align="right" width="200">排序号:</td>
						<td align="left" width="300">
							<input type="text" id="order" name="order" value="${dictionaryItem.order }" />
							<div id="orderMsg" style="color: red"></div>
						</td>
					</tr>
					<tr>
						<td align="right" width="200">是否启用:</td>
						<td align="left" width="300">   
							<input type="radio" value="1" checked="checked" name="status" />是
							<input type="radio" value="0" ${dictionaryItem.status == 0? 'checked="checked"' : ''} name="status" />否
						</td>
						<td align="right" width="200"></td>
						<td align="left" width="310"></td>
					</tr>
					<tr>
						<td align="right" width="200">备注:</td>
						<td align="left" width="300" colspan="3"><textarea  rows="3" cols="40" id="remark" name="remark" class="form-control">${dictionaryItem.remark }</textarea></td>
					</tr>
				</tbody>
				<tfoot>
		        <tr>
					<td colspan="4" align="center">
						<button class="btn btn-sm btn-info" type="button" onclick="doSaveDictionaryItem()">
							<i class="ace-icon fa fa-check bigger-110"></i>
							保 存
						</button>
							 &nbsp;&nbsp;&nbsp; 
						<button class="btn btn-sm" type="reset" onclick="closed();">
							<i class="ace-icon fa fa-undo bigger-110"></i>
							取 消
						</button>
					</td>
		        </tr>
	        </tfoot>
			</table>
		</div>
	</form>
</body>
</html>
