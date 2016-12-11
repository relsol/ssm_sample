<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
			<!-- #section:basics/content.breadcrumbs -->
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
					<li class="active">机构管理</li>
				</ul>
			</div>
			<div class="page-content">
				<div class="page-content-area">
					<div class="row">
						<div class="col-xs-3" style="padding:5px; background: #EFF3F8;">
							<ul id="tree" class="ztree"></ul>
						</div>
						<div class="col-xs-9">
							<form class="form-horizontal" role="form">
								<div id="findDiv" class="form-group">
									<div class="col-sm-1 no-padding-right">
										<a onclick="addOne();" class="btn btn-primary btn-xs p310" data-toggle="modal">
											<i class="ace-icon glyphicon glyphicon-plus"></i> 新增下级机构
										</a> 
									</div>
								</div>
							</form>
							<table id="testTable" class="table table-striped table-bordered table-hover">
								<thead> 
									<tr>
										<th>序号</th>
										<th>机构名称</th>
										<th>机构地址</th>
										<th>备注</th>
										<th>状态</th>
										<th>操作</th>
									</tr>
								<thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

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
	
	var oTable1, departmentId=0;
	
 	$(document).ready(function(){
 		//初始化机构树
		$.fn.zTree.init($("#tree"), setting, ${treeNode});
 		
		//初始化表格
		oTable1 = $('#testTable').DataTable({
			paging: false,
			"serverSide" : false,
			"ajax" : {
				"url" : "${path}/department/selectChild",
				"type" : "POST",
				"data" : function(d) {
					//post参数
					d.departmentId = departmentId;
				}
			},
			"columns" : [ 
				{ "data" : null }, 
		        { "data" : "name" }, 
		        { "data" : "address" }, 
		        { "data" : "remark" }, 
		        { "data" : "status", "render": function (data,type,full,meta) {
					return data == '1' ? "启用" : "停用";
		           }}, 
		        { "data" : null, "render": function (data,type,full,meta) {
		               var html = '<button type="button" class="btn btn-primary btn-xs ace-icon fa fa-pencil align-top" onclick="editOne(\'' + full.id + '\')"> 修改</button>';
		               html += '  <button type="button" class="btn btn-danger btn-xs ace-icon fa fa-trash-o" onclick="delOne(\'' + full.id + '\')"> 删除 </button>';
		               return html;
		           }} 
		    ]
		}).on( 'draw.dt', function() {
			oTable1.column(0).nodes().each(function(cell, i) {
	            cell.innerHTML = '<b>' + (i + 1) + '</b>';
	        });
	    });
	});
	
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
	
	function treeOnclick(event, treeId, treeNode){
		departmentId = treeNode.id;
		oTable1.ajax.reload(null, false);
	}
	
	function doSearch(){
		oTable1.ajax.reload(null, false);
	}
		
	function addOne(){
		var treeObj = $.fn.zTree.getZTreeObj("tree");
		var nodes = treeObj.getSelectedNodes();
		var id = 0;
		if(nodes[0]){
			id = nodes[0].id;
			layer.open({
			    type: 2,
			    title: '机构管理',
			    shadeClose: true,
			    shade: 0.8,
			    area:['50%' , '80%'],
			    content: "${path}/department/showDepartment?id="+id+"&type=add",
		    	end: doSearch
			}); 
		} else {
			layer.msg("请在左侧选择父机构!", {icon: 2});
		}
	}
		
	function editOne(id){
		layer.open({
		    type: 2,
		    title: '机构管理',
		    shadeClose: true,
		    shade: 0.8,
		    area:['50%' , '80%'],
		    content: "${path}/department/showDepartment?id="+id+"&type=edit",
	    	end: doSearch
		}); 
	}
		
	function delOne(id){
		var i = layer.load(0,1);//提交加上遮罩
		$.ajax({
			type:'post',
			url:"${path}/department/delDepartment",
			data:{"id":id},
			cache:false,
			dataType:'json',
			success:function(json){
				layer.close(i);//去掉遮罩
				if(json.success){
					layer.msg("操作成功!", {icon: 1, time: 1000}, function(){
						editTreeNode('del', id);
					});
				} else {
					layer.msg(json.msg, {icon: 2});
				}
			}
		});
	}
	
	function changeStatus(id, status){
		$.ajax({
			url:"${path}/department/changeStatus",
			data:{"id":id},
			cache:false,
			dataType:'json',
			success:function(data){
				if(data.success){
					doSearch();
				} else {
					$.messager.alert("信息提示", data.msg, "error");
				}
			}
		});
	}
</script>
</body>
</html>
