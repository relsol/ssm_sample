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
					<li class="active">角色管理</li>
				</ul>
			</div>

			<div class="page-content">
				<div class="page-content-area">
					<div class="row">
						<div class="col-xs-12">
							<form class="form-horizontal" role="form">
								<div id="findDiv" class="form-group">
									<label class="col-sm-1 control-label no-padding-right" for="form-field-1"> 角色名称 </label>
									<div class="col-sm-3">
										<input type="text" name="name" id="name"/>
									</div>
									<div class="col-sm-1 no-padding-right">
										<a onclick="findPage();" class="btn btn-primary btn-xs p310" data-toggle="modal">
											<i class="ace-icon glyphicon glyphicon-search"></i> 查询
										</a> 
									</div>
									<div class="col-sm-1 no-padding-right">
										<a onclick="addRole();" class="btn btn-primary btn-xs p310" data-toggle="modal">
											<i class="ace-icon glyphicon glyphicon-plus"></i> 添加角色
										</a> 
									</div>
									<div class="col-sm-1 no-padding-right">
									</div>
								</div>
							</form>
							<table id="testTable" class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>序号</th>
										<th>角色编号</th>
										<th>角色名称</th>
										<th>状态</th>
										<th>子系统</th>
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
	</div>

	

	<!-- inline scripts related to this page -->
	
	
	<script type="text/javascript">
		var oTable1;
		jQuery(function($) {
			oTable1 = $('#testTable').DataTable({
				"serverSide" : true,
				"ajax" : {
					"url" : "${path}/role/findPage",
					"type" : "POST",
					"data" : function(d) {
						//post参数
						d.name = $('#name').val();
					}
				},
				"columns" : [ 
				{
					"data" : null,
		        },
		        { "data" : "code" }, 
		        { "data" : "name" }, 
		        { "data" : "status", "render": function (data,type,full,meta) {
					return data == '1' ? "启用" : "停用";
                }}, 
		        { "data" : "projectName" }, 
		        { "data" : "remark" }, 
		        { "data" : null, "render": function (data,type,full,meta) {
                    var html = '<button type="button" class="btn btn-primary btn-xs ace-icon fa fa-pencil align-top" onclick="edit(\'' + full.id + '\')"> 修改</button>';
                    html += '  <button type="button" class="btn btn-danger btn-xs ace-icon fa fa-trash-o" onclick="del(\'' + full.id + '\')"> 删除 </button>';
                    html += '  <button type="button" class="btn btn-info btn-xs ace-icon fa fa-key " onclick="giveAuth(\'' + full.id + '\')"> 赋权 </button>';
                    return html;
                }} ]
			});
		}).on( 'draw.dt', function() {
			oTable1.column(0).nodes().each(function(cell, i) {
	            cell.innerHTML = '<b>' + (i + 1) + '</b>';
	        });
	    });
		
		function doSearch(resetPaging){
			oTable1.ajax.reload(null,resetPaging);
		}
		
		function addRole(){
			layer.open({
			    type: 2,
			    title: '添加角色',
			    shadeClose: true,
			    shade: 0.8,
			    area: ['60%', '60%'],
			    content: '${path }/role/toAddRole',
		    	end: function(){
		    		doSearch();
		    	} 
			}); 
		}
		
		function edit(id){
			layer.open({
			    type: 2,
			    title: '编辑角色',
			    shadeClose: true,
			    shade: 0.8,
			    area: ['60%', '60%'],
			    content: '${path }/role/toAddRole?id='+id,
		    	end: function(){
		    		oTable1.ajax.reload();
		    	}
			}); 
		}
		
		function giveAuth(id){
			layer.open({
			    type: 2,
			    title: '角色赋权',
			    shadeClose: true,
			    shade: 0.8,
			    area: ['30%', '90%'],
			    content: '${path}/role/toShowPermission?roleId='+id,
			    end : function(){
			    	oTable1.ajax.reload();
			    }
			}); 
		}
		
		function del(id){
			if(id){
				layer.confirm('确认删除？', {icon: 2, title:'提示'}, function(index){
					var _index = layer.load("请稍候....");
					$.ajax({
						url:'${path}/role/delRole',
						data:{"id" : id},
						type:'post',
						success:function(data){
							layer.close(_index);
							if(data = "success"){
								layer.msg("操作成功!", {icon: 1, time:2000}, function(){
									oTable1.ajax.reload();
								});
							} else {
								layer.msg(data.msg, {icon: 2});
							}
						}
					});
				},function(index){});
			}else{
				var str = "";
				$("input[name='checkList']").each(function (i, o) {
					if($(this)[0].checked == true){
					    str += $(this)[0].value;
					    str += ",";
					}
				});
				if (str.length > 0) {
				    var IDS = str.substr(0, str.length - 1);
				    alert("你要删除的数据集id为" + IDS);
				} else {
				    alert("至少选择一条记录操作");
				}
			}
		}
	</script>

</body>
</html>

