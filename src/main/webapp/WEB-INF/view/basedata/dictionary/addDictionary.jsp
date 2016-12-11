<%@ include file="../../common/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="${path}/static/css/easyui/easyui.css" />
<link rel="stylesheet" type="text/css" href="${path}/static/css/easyui/demo.css" />
<link rel="stylesheet" type="text/css" href="${path}/static/css/easyui/easyui_demo/icon.css" />
<script type="text/javascript" src="${path}/static/js/jquery/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="${path}/static/js/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path}/static/js/jquery/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="${path}/static/css/newTable.css" />
<script type="text/javascript">
<!--
function check(){
	var code = $.trim($('#code').val());
	var name = $.trim($('#name').val());
	var flg = true;
	if(!code){
		$('#codeMsg').html('提示:字典编码不能为空!');
		flg = false;
	} else {
		$('#codeMsg').html('');
	}
	if(!name){
		$('#nameMsg').html('提示:字典名称不能为空!');
		flg = false;
	} else {
		$('#nameMsg').html('');
	}
	return flg;
}

function closed(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}

function doSaveDictionary(){
	var params = $("#addDictionaryForm").serialize();
	if(check()){
		var i = parent.layer.load(0,1);//提交加上遮罩
		$.ajax({
			type:'post',
			url:"${path}/dictionary/saveDictionary",
			data:params,
			cache:false,
			dataType:'json',
			success:function(json){
				parent.layer.close(i);//去掉遮罩
				if(json.success){
					parent.layer.msg("操作成功!", {icon: 1, time:1000}, function(){
						var id = $.trim($("#id").val());
						if(id==null || id==""){
							parent.editTreeNode("add", json.data);
						} else {
							parent.editTreeNode("edit", json.data);
						}
						closed();
					});
				} else {
					parent.layer.msg(json.msg, {icon: 2});
				}
			}
		});
	}
}

//-->		
</script>
</head>
<body style="background: #fafafa;">
	<br />
	<form id="addDictionaryForm" method="post" action="${path}/dictionary/saveDictionary" onsubmit="return false;">
		<input type="hidden" id="pid" name="pid" value="${dictionary.pid}" />
		<input type="hidden" id="id" name="id" value="${dictionary.id}" />
		<div style="margin: 0 10px;">
			<table width="100%" align="center" class="newTable" cellspacing="0" cellpadding="0" border="0">
				<tbody id="employeeContent">
					<tr>
						<td align="right" width="200"><font color="red">*</font>字典编码:</td>
						<td align="left" width="300">
							<input type="text" id="code" maxlength="33" name="code" value="${dictionary.code }" ${dictionary.id!=null?'readonly="readonly"':''}/>
							<div id="codeMsg" style="color: red"></div>
						</td>
						<td align="right" width="200"><font color="red">*</font>字典名称:</td>
						<td align="left" width="300">
							<input type="text" id="name" maxlength="33" name="name" value="${dictionary.name }" />
							<div id="nameMsg" style="color: red"></div>
						</td>
					</tr>
					<tr>
						<td align="right" width="200">备注:</td>
						<td align="left" width="300" colspan="3"><textarea onkeyup="if (this.value.length>=333)this.value=this.value.substr(0,333)" rows="3" cols="40" id="remark" name="remark">${dictionary.remark }</textarea></td>
					</tr>
				</tbody>
				<tfoot>
		        <tr>
					<td colspan="4" align="center">
						<button class="btn btn-sm btn-info" type="button" onclick="doSaveDictionary()">
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
