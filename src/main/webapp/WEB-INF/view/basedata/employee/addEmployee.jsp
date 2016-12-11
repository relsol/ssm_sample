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
<script src="${path}/static/js/jquery-zTree/jquery.ztree.core-3.5.min.js"></script>
<script src="${path}/static/js/jquery-zTree/jquery.ztree.excheck-3.5.min.js"></script>
<link rel="stylesheet" href="${path}/static/css/jquery-zTree/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript">
<!--
$().ready(function(){
	jQuery.validator.addMethod("isCadreRequire", function(value, element) {
		var isCadre = $('input[name="isCadre"]:checked').val();
		if(isCadre == '0'){
			return value=='' ? false : true;
		} else {
			return true;
		}
	}, "提示：非干部请填写职员工号!");
	
	$("#employeeForm").validate({
		errorClass: "validate",
		errorElement : 'div',
		rules:{
			departmentName:{
				required: true,
			},
			userName:{
				required: true,
				maxlength:20
			},
			loginName:{
				required: true,
				maxlength:20,
				remote:{
					url: "${path}/employee/checkLoginName",
		       	    type: 'POST',
		       	 	async: false,
		       	    dataType: 'json'
				}
			},
			jobNum:{
				isCadreRequire:true,
				remote:{
					url: "${path }/personnel/checkJobNum",
		       	    type: 'POST',
		       	 	async:false,
		       	    dataType: 'json'
				}
			},
			mobile:{
				isMobile:true
			}
		},
		messages:{
			departmentName:{
				required:"提示：请至少选择一个机构！",
			},
			userName:{
				required:"提示：请输入姓名！",
				maxlength:"提示：最多20个字符！"
			},
			loginName:{
				required:"提示：请输入登陆账号！",
				maxlength:"提示：最多20个字符！",
				remote:"提示：登陆账号已存在"
			},
			jobNum:{
				remote:"提示：工号已存在!"
			},
			mobile:{
				isMobile:"提示：请输入正确的手机号码!"
			}
		}
	});
	
	$("#cancelBtn").click(function(){
		closed();
	});
	
	var type = "${type}";
	if("show" == type){
		$("input").addClass("hideInput");
		$("input").attr("readonly", "readonly");
		$("textarea").addClass("hideInput");
		$("textarea").attr("readonly", "readonly");
		$("#saveBtn").hide();
		$("#cancelBtn").html("关闭");
	}
});

function closed(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}

function resetPassword(id) {
	$.ajax({
		url : "${path }/employee/resetPassword",
		data : {
			"id" : id
		},
		cache : false,
		async : false,
		type : 'post',
		success : function(data) {
			if (data == 'success') {
				parent.layer.msg("操作成功!", {icon: 1, time:1000});
			} else {
				parent.layer.msg("操作失败!", {icon: 2});
			}
		}
	});
}
	
function doSaveEmployee(){
	var _index = parent.layer.load("请稍候....");
	var options = {
		type:'post',
		beforeSubmit:function(){
			if($("#employeeForm").valid()){
				return true;
			} else {
				parent.layer.close(_index);
				return false;
			}
		},
		success:function(data){
			parent.layer.close(_index);
			if(data == 'success'){
        		parent.layer.msg("操作成功!", {icon: 1, time:1000}, function(){
					closed();
				});
        	} else {
        		parent.layer.msg(data, {icon: 2});
        	}
   		},
   		error:function(){
   			parent.layer.close(_index);
   			parent.layer.msg("请求服务器失败,请检查网络!", {icon: 2});
   		}
	};
	$('#employeeForm').ajaxSubmit(options);
}	
	
	var setting = {
		check: {
			enable: true,
			chkboxType: {"Y":"", "N":""}
		},
		view: {
			dblClickExpand: true
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			onCheck: onCheck
		}
	};
	
	function onCheck(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("departmentTree"),
		nodes = zTree.getCheckedNodes(true),
		v = "", departmentId = "";
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].name + ",";
			departmentId += nodes[i].id + ",";
		}
		if (v.length > 0 ) v = v.substring(0, v.length-1);
		var cityObj = $("#departmentName");
		cityObj.attr("value", v);
		$("#departmentIds").val(departmentId);
	}

	function showTree() {
		var cityObj = $("#departmentName");
		var cityOffset = $("#departmentName").offset();
		$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

		$("body").bind("mousedown", onBodyDown);
	}
	function hideMenu() {
		$("#menuContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "departmentName" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
			hideMenu();
		}
	}

	$(document).ready(function(){
		$.fn.zTree.init($("#departmentTree"), setting, ${departmentTreeData});
	});
//-->
</script>
</head>
<body style="background: #fafafa;">
<div style="width: 100%; height: 100%; margin-top: 20px;">
<form id="employeeForm" action="${path}/employee/saveOrAddEmployee">
	<input type="hidden" id="id" name="id" value="${user.id}" />
	<table width="90%" align="center" class="newTable" cellspacing="0" bcellpadding="0" border="0">
		<tr>
			<td align="right" width="160">姓名：<font color="red">*</font></td>
			<td align="left" width="300">
				<input type="text" id="userName" maxlength="33" name="userName" value="${user.userName }" />
			</td>
			<td align="right" width="160">登录帐号：<font color="red">*</font></td>
			<td align="left" width="310">
				<input type="text" id="loginName" maxlength="33" name="loginName" value="${user.loginName }"  ${type eq null?'':'disabled="disabled"'}/>
			</td>
		</tr>
		<%-- <tr>
			<td align="right" width="160">干部工人标识:</td>
			<td align="left" width="310">
				<c:if test="${(empty user.isCadre)}">
					<input type="radio" name="isCadre" value="1" />干部
					<input type="radio" name="isCadre" value="0" checked="checked"/>工人
				</c:if>
				<c:if test="${(not empty user.isCadre)}">
					<input type="radio" name="isCadre" value="1" <c:if test="${user.isCadre=='1' }">checked="checked"</c:if>/>干部
					<input type="radio" name="isCadre" value="0" <c:if test="${user.isCadre=='0' }">checked="checked"</c:if>/>工人
				</c:if>
			</td>
			<td align="right" width="160">职员工号：</td>
			<td align="left" width="300">
				<input type="text" id="jobNum" name="jobNum" maxlength="33" value="${user.jobNum }" ${user.jobNum eq null?'':'disabled="disabled"'}/>
			</td>
		</tr> --%>
		<tr>
			<td align="right">性别：</td>
			<td align="left">
				<input type="radio" name="sex" value="1" checked="checked"/> 男
				<input type="radio" name="sex" value="0" ${user.sex=='1'?'':'checked="checked"'}/> 女
			</td>
			<td align="right">是否系统用户：</td>
			<td align="left">
				<input type="radio" name="systemUser" value='1' checked="checked"/> 是
				<input type="radio" name="systemUser" value='0' ${user.systemUser=='0'?'checked="checked"':''}/> 否
			</td>
		</tr>
		<tr>
			<td align="right">电话：</td>
			<td align="left">
				<input type="text" id="phone" maxlength="33" name="phone" value="${user.phone }" />
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
			<td align="right">组织机构：<font color="red">*</font></td>
			<td align="left">
				<input type="hidden" id="departmentIds" name="departmentIds" value="${user.departmentIds}" /> 
				<input type="text" id="departmentName" name="departmentName" value="${user.departmentName}" onclick="showTree(); return false;" readonly="readonly"/> 
				&nbsp;<a id="menuBtn" href="#" onclick="showTree(); return false;">查询</a></li>
			</td>
			<td align="left" colspan="2">
				&nbsp;&nbsp;&nbsp; 
				<a href="javascript:;" onclick="resetPassword('${user.id}');" class="Button_small">
					重置密码
				</a> 
				<span style="font-size: 11px;">默认密码：000000</span>
			</td>
		</tr>
		<tr>
			<td align="right" width="160">IP地址</td>
			<td align="left" width="310">
				<input type="text" id="ipAddress" name="ipAddress" maxlength="33" value="${user.ipAddress }" />
			</td>
			<td align="right" width="160">安全等级</td>
			<td align="left" width="300">
				<input type="hidden" id="safeLevel" name="safeLevel">
				<select id="safeLevelId" name="safeLevelId" onchange="safeLevelChange()">
					<option value="">请选择</option>
					<c:forEach items="${safeLevelMap}" var="obj">
						<option value="${obj.key}" ${obj.key == user.safeLevelId?'selected="selected"':''}>${obj.value}</option>
					</c:forEach>
				</select>
				
				<script type="text/javascript">
					function safeLevelChange(){
						$("#safeLevel").val($("#safeLevelId option:selected").text());
					}
				</script>
			</td>
		</tr>
		<tr align="center">
			<td colspan="4">
				<button class="btn btn-sm btn-info" type="button" onclick="doSaveEmployee()">
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
	<div id="menuContent" class="menuContent" style="display:none; position: absolute;">
		<ul id="departmentTree" class="ztree" style="margin-top:0; border: 1px solid #617775;background: #f0f6e4; "></ul>
	</div>
</form>
</div>
</body>