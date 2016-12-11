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
<script src="${path}/static/js/jquery-zTree/jquery.ztree.core-3.5.min.js"></script>
<script src="${path}/static/js/jquery-zTree/jquery.ztree.excheck-3.5.min.js"></script>
<link rel="stylesheet" href="${path}/static/css/jquery-zTree/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript">
  <!--
  
    var setting = {
		check: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true
			}
		}
	};
	 
	$(document).ready(function(){
		$.fn.zTree.init($("#treeDemo"), setting, ${treeNode});
	});
	
	function closed(){
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}
	
	function doSave(){
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        var nodes = treeObj.getCheckedNodes(true);
        var roleIds = "";
        $(nodes).each(function(){
        	roleIds += this.id + ",";
        });
        
        var i = parent.layer.load(0,1);//提交加上遮罩
        $.ajax({
        	url:"${path}/role/savaRoleEmployee",
        	data:{'roleIds': roleIds, "employeeId":$("#employeeId").val(), "projectId":$("#projectId").val()},
        	success:function(data){
        		parent.layer.close(i);//去掉遮罩
		        if(data = "success"){
					parent.layer.msg("操作成功!", {icon: 1, time:2000}, function(){
							closed();
						}
					);
				} else {
					parent.layer.msg(data.msg, {icon: 2});
				}
        	}
        });
	}
	
	function project_change(){
		window.location.href = "${path}/role/showRoleTree?employeeId="+$("#employeeId").val()+"&projectId="+$("#projectId").val();
	}
  //-->
</script>
</head>
<body style="background: #f0f6e4;">
<form id="permissionForm">
	<input type="hidden" id="employeeId" name="employeeId" value="${employeeId}"/>
	<div style="padding-bottom: 5px;" align="center">
		<select id="projectId" style="width:150px;" onchange="project_change();">
			<c:forEach items="${proList}" var="data">
				<option value="${data.id}" ${projectId eq data.id?'selected="selected"':''} >${data.sysName}</option>
			</c:forEach>
		</select>
	</div>
	<div style="padding:5px; background: #f0f6e4;height: 280px;">
		<div>
			<ul id="treeDemo" class="ztree"></ul>
		</div>
	</div>
	<table id="foot" align="center">
		<tr align="center">
			<td>
				<button class="btn btn-info btn-xs" type="button"  onclick="doSave();">
					<i class="ace-icon fa fa-check bigger-110"></i>
					提 交
				</button>
					 &nbsp;&nbsp;&nbsp; 
				<button class="btn btn-xs" type="reset" onclick="closed();">
					<i class="ace-icon fa fa-undo bigger-110"></i>
					关 闭
				</button>
			    <div class="clear"></div>
			</td>
		</tr>
	</table>
</form>
</body>
</html>
