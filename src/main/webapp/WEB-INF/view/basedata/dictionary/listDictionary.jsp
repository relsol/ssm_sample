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
<style>
article,aside,figure,footer,header,hgroup,menu,nav,section {
	display: block;
}
.west {
	width: 20%;
	padding: 10px;
}
</style>
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
var oTable1, dictionaryId=0;

$(document).ready(function(){
		//初始化机构树
	$.fn.zTree.init($("#tree"), setting, ${treeNode});
	//初始化表格
	oTable1 = $('#dictionaryTable').DataTable({
		"serverSide" : false,
		"ajax" : {
			"url" : "${path}/dictionary/selectChild",
			"type" : "POST",
			"data" : function(d) {
				//post参数
				d.dictionaryId = dictionaryId;
			}
		},
		"columns" : [ 
			{ "data" : null }, 
	        { "data" : "name" }, 
	        { "data" : "status", "render": function (data,type,full,meta) {
				return data == '1' ? "<span style='color:green'>启用</span>" : "<span style='color:red'>停用</span>";
	           }
	        }, 
	        { "data" : "order" }, 
	        { "data" : "remark" }, 
	        { "data" : null, "render": function (data,type,full,meta) {
	        		var html = '';
		        	if(full.status == 1){
		        		html = '<button type="button" class="btn btn-primary btn-xs ace-icon fa fa-pencil align-top" onclick="changeStatus(\'' + full.id + '\', 0)"> 停用</button>';
		        	} else {
		        		html = '<button type="button" class="btn btn-primary btn-xs ace-icon fa fa-pencil align-top" onclick="changeStatus(\'' + full.id + '\', 1)"> 启用</button>';
		        	}
		        	html += '     <button type="button" class="btn btn-primary btn-xs ace-icon fa fa-pencil align-top" onclick="editDictionaryItem(\'' + full.id + '\')"> 编辑 </button>';
	        		return html;
	           }
	        } 
	    ]
	}).on( 'draw.dt', function() {
		oTable1.column(0).nodes().each(function(cell, i) {
            cell.innerHTML = '<b>' + (i + 1) + '</b>';
        });
    });
});

function treeOnclick(event, treeId, treeNode){
	dictionaryId = treeNode.id;
	oTable1.ajax.reload(null, false);
}


function editTreeNode(type, data){
	if('add' == type){
		var node = $("#tree").tree("getSelected");
		$("#tree").tree('append',{
			parent:node.target,
			data: {
				id: data.id,
				text: data.name
			}
		});
	} else if('edit' == type) {
		var node = $("#tree").tree("find",data.id);
		$("#tree").tree('update',{
			target: node.target,
			text:data.name
		});
	} else if('del' == type) {
		var node = $("#tree").tree("find",data);
		$("#tree").tree('remove', node.target); 
		doSearch();
	}
}
	
function addDictionary(type){
	var treeObj = $.fn.zTree.getZTreeObj("tree");
	var nodes = treeObj.getSelectedNodes();
	var id = 0;
	if('add' == type){//新增字典
		layer.open({
		    type: 2,
		    title: '新增数据字典',
		    shadeClose: true,
		    shade: 0.8,
		    area:['50%' , '60%'],
		    content: "${path}/dictionary/addOrEditDictionary?id="+id+"&type="+type,
		    end : function(){
		    	findPage();
		    }
		}); 
	}else{//编辑字典,编辑必须选中一个字典
		if(nodes[0]){
			id = nodes[0].id;
			layer.open({
			    type: 2,
			    title: '编辑数据字典',
			    shadeClose: true,
			    shade: 0.8,
			    area:['50%' , '60%'],
			    content: "${path}/dictionary/addOrEditDictionary?id="+id+"&type="+type,
			    end : function(){
			    	oTable1.ajax.reload(null, false);
			    }
			}); 
		}else{
			layer.alert("请在左侧选择数据词典!");
		}
	}
	
}

function doSearch(){
	oTable1.ajax.reload();
}

function editDictionaryItem(id) {
	layer.open({
	    type: 2,
	    title: '数据字典属性',
	    shadeClose: true,
	    shade: 0.8,
	    area:['50%' , '50%'],
	    content: "${path}/dictionary/addDictionaryItem?id="+id,
	    end: function(){
	    	doSearch();
	    }
	}); 
}

function addDictionaryItem(type) {
	var treeObj = $.fn.zTree.getZTreeObj("tree");
	var nodes = treeObj.getSelectedNodes();
	var dictionaryId = 0;
	if(nodes[0]){
		dictionaryId = nodes[0].id;
		layer.open({
		    type: 2,
		    title: '数据字典属性',
		    shadeClose: true,
		    shade: 0.8,
		    area:['50%' , '50%'],
		    content: "${path}/dictionary/addDictionaryItem?dictionaryId="+dictionaryId,
		    end: function(){
		    	doSearch();
		    }
		}); 
	}else{
		layer.alert("请在左侧选择数据词典!");
	}
}
	
function changeStatus(id, status){
	var i = layer.load(0,1);//提交加上遮罩
	$.ajax({
		url:"${path}/dictionary/changeStatus",
		data:{"id":id},
		cache:false,
		dataType:'json',
		success:function(data){
			if(data.success){
				layer.close(i);//去掉遮罩
				layer.msg("操作成功!",{icon: 1, time:1000}, function(){
					oTable1.ajax.reload(null, false);
				});
			} else {
				layer.close(i);//去掉遮罩
				layer.msg("操作失败!", {icon: 2, time:2000});
			}
		}
	});
}

function formart_option(value,row,index){
	if(row.status == 1){
		var f = ' <a href="#" onclick="changeStatus('+value+', 0)">停用</a> '; 
	} else {
		var f = ' <a href="#" onclick="changeStatus('+value+', 1)">启用</a> '; 
	}
	return f;
}
function formart_status(value,row,index){
	var rtn = value;
	if(value == 1){
		rtn = "启用";
	} else if(value == 0){
		rtn = "停用";
	}
	return rtn;
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
						<div class="col-xs-3" style="padding:5px; background: #f0f6e4;">
							<ul id="tree" class="ztree"></ul>
						</div>
						<div class="col-xs-9">
							<form class="form-horizontal" role="form">
								<div id="findDiv" class="form-group">
<!-- 									<div class="col-sm-1" style="margin-left: 20px;"> -->
<!-- 										<a class="btn btn-primary btn-xs p310" data-toggle="modal" onclick="addDictionary('edit')"> -->
<!-- 											<i class="ace-icon glyphicon glyphicon-plus"></i> 编辑字典 -->
<!-- 										</a> -->
<!-- 									</div> -->
									<div class="col-sm-1" style="margin-left: 40px;">
										<a class="btn btn-primary btn-xs p310" data-toggle="modal" onclick="addDictionaryItem('add')">
											<i class="ace-icon glyphicon glyphicon-plus"></i> 新增字典属性
										</a>
									</div>
								</div>
							</form>
							<table id="dictionaryTable" width="100%" class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th width="5%">序号</th>
										<th width="10%">属性名称</th>
										<th width="5%">状态</th>
										<th width="5%">排序号</th>
										<th width="15%">备注</th>
										<th width="10%">操作</th>
									</tr>
								<thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
							
</body>
</html>
