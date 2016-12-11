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
<style type="text/css">
#foot{position:fixed;
	_position:absolute;
	bottom:0px;
	_bottom:0px;
	_margin-top:expression(this.style.pixelHeight+document.documentElement.scrollTop);
	width: 100%;
	text-align:center;
	margin-bottom: 2px;
}
</style>
<script type="text/javascript">
  <!--

	function closed(){
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}
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
 	
	function doSave(){
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        var nodes = treeObj.getCheckedNodes(true);
        var permissionIds = "";
        $(nodes).each(function(){
        	permissionIds += this.id + ",";
        });
        var _index = parent.layer.load("请稍候....");
        $.ajax({
        	url:"${path}/role/updatePermissionToRole",
        	data:{'permissionIds': permissionIds, "roleId":$("#roleId").val()},
        	success:function(data){
		        parent.layer.close(_index);//去掉遮罩
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
  //-->
</script>
</head>
<body style="background: #f0f6e4;">
<form id="permissionForm">
	<input type="hidden" id="roleId" name="roleId" value="${roleId}"/>
	<div style="padding:5px; background: #f0f6e4;">
		<div style="margin-bottom: 60px;">
			<ul id="treeDemo" class="ztree"></ul>
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
	</div>
</form>
</body>
</html>
