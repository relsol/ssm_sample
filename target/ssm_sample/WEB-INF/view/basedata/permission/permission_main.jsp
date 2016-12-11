<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
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

var setting = {
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			onClick: treeOnclick
		}
	};


var oTable1, projectId=0,pid=0;
$(function(){
	
	<c:if test="${not empty treeNode}">
	
		//初始化树形结构
		$.fn.zTree.init($("#tree"), setting, ${treeNode});
		
		projectId=${projectId},pid=0
		//初始化表格
		oTable1 = $('#permissionTable').DataTable({
			"serverSide" : false,
			"ajax" : {
				"url" : "${path}/permission/findList",
				"type" : "POST",
				"data" : function(d) {
					//post参数
					d.projectId = projectId;
					d.pid = pid;
				}
			},
			"columns" : [ 
		        { "data" : "name" }, 
		        { "data" : "uri" }, 
		        { "data" : "level", "render": function (data,type,full,meta) {
					return data == '1' ? "导航" : (data == '2' ? "菜单" : "按钮");
		           }},  
		        { "data" : "order" }, 
		        { "data" : "remark" }, 
		        { "data" : null, "render": function (data,type,full,meta) {
		               var html = '<button type="button" class="btn btn-primary btn-xs ace-icon fa fa-pencil align-top" onclick="editOne(\'' + full.id + '\')"> 修改</button>';
		               return html;
		           }} 
		    ]
		});
	</c:if>
});

//重新查询
function doSearch(){
	oTable1.ajax.reload();
}

//单机树
function treeOnclick(event, treeId, treeNode){
	pid = treeNode.id;
	oTable1.ajax.reload();
}

//切换系统
function project_change(){
	var projectId = $("#projectId").val();
	if(projectId != -1){
		window.location.href = "${path}/permission/main.html?projectId="+projectId;
	}
}

//修改树节点方法
function editTreeNode(type, data){
	var treeObj = $.fn.zTree.getZTreeObj("tree");
	var node = treeObj.getSelectedNodes()[0];
	if('add' == type){
		var newNode = {id: data.id, name:data.name};
		treeObj.addNodes(node, newNode);
	} else if('edit' == type) {
		var nodes = treeObj.getNodesByParam("id", data.id, null);
		nodes[0].name = data.name;
		treeObj.updateNode(nodes[0]);
	} else if('del' == type) {
		var nodes = treeObj.getNodesByParam("id", data, null);
		treeObj.removeNode(nodes[0]);
		doSearch();
	}
}

//新增
function addOne(){
	if($.fn.zTree.getZTreeObj("tree") != null){
		var treeObj = $.fn.zTree.getZTreeObj("tree");
		var nodes = treeObj.getSelectedNodes();
		var id = 0;
		var projectId=0;
		<c:if test="${not empty projectId}">
			projectId=${projectId};
		</c:if>
		if(nodes[0]){
			id = nodes[0].id;
			layer.open({
			    type: 2,
			    title: '权限管理',
			    shadeClose: true,
			    shade: 0.8,
			    area:['50%' , '80%'],
			    content: "${path}/permission/showPermission?id="+id+"&projectId="+projectId+"&type=add",
		    	end: doSearch
			}); 
		} else {
			id = 0;
			layer.open({
			    type: 2,
			    title: '权限管理',
			    shadeClose: true,
			    shade: 0.8,
			    area:['50%' , '80%'],
			    content: "${path}/permission/showPermission?id="+id+"&projectId="+projectId+"&type=add",
		    	end: doSearch
			}); 
		}
	}else{
		layer.msg("请先选择子系统!", {icon: 2});
	}
}

//修改
function editOne(id){
	layer.open({
	    type: 2,
	    title: '权限管理',
	    shadeClose: true,
	    shade: 0.8,
	    area:['50%' , '80%'],
	    content: "${path}/permission/showPermission.html?id="+id+"&type=edit",
    	end: doSearch
	}); 
}
</script>
</head>
<body class="no-skin">
<div class="main-container" id="main-container">
	<script type="text/javascript">
		try {
			ace.settings.check('main-container', 'fixed')
		} catch (e) {
		}
	</script>
	<div class="main-content">
		<div class="breadcrumbs" id="breadcrumbs">
			<script type="text/javascript">
				try {
					ace.settings.check('breadcrumbs', 'fixed')
				} catch (e) {
				}
			</script>
			<ul class="breadcrumb">
				<li>
					<i class="ace-icon fa fa-home home-icon"></i> 
				</li>
				<li>系统管理</li>
				<li class="active">权限管理</li>
			</ul>
		</div>
	</div>
	<div class="page-content">
		<div class="page-content-area">
			<div class="row">
				<div class="col-xs-3" style="padding:5px;">
					<select id="projectId"  name="projectId" style="width:280px;" onchange="project_change();">
						<option value="-1">请选择子系统</option>
						<c:forEach items="${proList}" var="data">
							<option value="${data.id}" ${projectId eq data.id?'selected="selected"':''} >${data.sysName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-xs-9">
					<form class="form-horizontal" role="form">
						<div id="findDiv" class="form-group">
							<div class="col-sm-1 no-padding-right">
								<a onclick="addOne();" class="btn btn-primary btn-xs p310" data-toggle="modal">
									<i class="ace-icon glyphicon glyphicon-plus"></i> 新增
								</a> 
							</div>
						</div>
					</form>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-3" style="padding:5px; background: #f0f6e4;">
					<ul id="tree" class="ztree"></ul>
				</div>
				<div class="col-xs-9">
					<table id="permissionTable" class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th>权限名称</th>
								<th>url</th>
								<th>级别</th> 
								<th>排序</th>
								<th>备注</th>
								<th>操作</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
