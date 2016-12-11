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
<link rel="stylesheet" href="${path}/static/css/jquery-zTree/zTreeStyle/zTreeStyle.css"/>
<link rel="stylesheet" href="${path}/static/css/smartMenu.css"/>
<script src="${path}/static/js/jquery/jquery-smartMenu.js"></script>

<script type="text/javascript">
	var oTable1;
	jQuery(function($) {
		
		oTable1 = $('#myTable').DataTable({
			"serverSide" : true,
			"ajax" : {
				"url" : "${path}/employee/findPage",
				"type" : "POST",
				"data" : function(d) {
					d.userName = $('#userName').val();
					d.departmentId = $("#departmentId").val();
				}
			},
			"columns" : [ 
			    { "data" : null},
				{ "data" : "userName"},
		        { "data" : "loginName" }, 
		        { "data" : "sex", render:function(data,type,full,meta){
		        	var rtn = data;
		        	if(data == 1){
		        		rtn = "男";
		        	} else if(data == 0){
		        		rtn = "女";
		        	}
		        	return rtn;
		        }}, 
		        { "data" : "phone" }, 
		        { "data" : "mobile" }, 
		        { "data" : "departmentName", render:function(data,type,full,meta){
		        	if(data){
			        	return "<span class='numControl' title='"+data+"' style='text-align:center;'>"+data+"</span>";	
		        	} else {
		        		return "";
		        	}
		        }}, 
		        { "data" : "cancel", render:function(data,type,full,meta){
		        	if(data == 1){
		        		return '注销';
		        	} else {
		        		return '正常';
		        	}
		        }}, 
		        { "data" : "systemUser", render:function(data,type,full,meta){
		        	return full.systemUser == '1'?'系统用户':'非系统用户';
		        }}, 
		        { "data" : "id", "render": function (data,type,full,meta) {
		        	var s = '<button type="button" class="btn btn-primary btn-xs ace-icon fa fa-pencil align-top" onclick="showEmployee('+data+')">查看</button> ';
		        	var e = '<button type="button" class="btn btn-primary btn-xs ace-icon fa fa-pencil align-top" onclick="editEmployee('+data+')">编辑</button> '; 
		        	var d = '<button type="button" class="btn btn-info btn-xs ace-icon fa fa-key " onclick="giveAuth('+data+')">赋权</button> '; 
		    		return e + d; 
                }} 
	        ]
		}).on( 'draw.dt', function() {
			oTable1.column(0).nodes().each(function(cell, i) {
	            cell.innerHTML = '<b>' + (i + 1) + '</b>';
	        });
// 			setTableStyle();
	    });
	});
	
/*	 function setTableStyle() { 
	      
	    $("#myTable tbody tr").unbind("mouserover")
	    $("#myTable tbody tr").bind("mousedown", (function (e) {
	        if (e.which == 3) {
	            var opertionn = {
	                name: "",
	                offsetX: 2,
	                offsetY: 2,
	                textLimit: 30,
	                beforeShow: $.noop,
	                afterShow: $.noop
	            };
	            var cells = this.getElementsByTagName("td"); // 获取到右键tr里的所有td
	            var trHtml = cells[2].innerHTML;// 获取第三个td的内容
	            var id = $(this).find('input[name=id]').val(); // 获取id，前提是在tr里写入隐藏域<input type='hidden' name='id' value='12321' />
	           // 展示右键菜单
	            var menudata = getRightMenu(e.target.cellIndex, e.target.innerHTML);
	            
	            oTable1.$('td.td_selected').removeClass('td_selected');
	            if ($(e.target).hasClass('td_selected')) {
	                $(e.target).removeClass('td_selected');
	            } else {
	                $(e.target).addClass('td_selected');
	            }
	            $.smartMenu.remove();
	            $(this).smartMenu(menudata, opertionn);
	        }
	         
	    }));
// 	    $("#myTable tbody tr").mouseover(function (e) { 
// 	    	oTable1.$('td.td_selected').removeClass('td_selected');
// 	        if ($(e.target).hasClass('td_selected')) {
// 	            $(e.target).removeClass('td_selected');
// 	        } else {
// 	            $(e.target).addClass('td_selected');
// 	        }
// 	        if ($(this).hasClass('row_selected')) {
// 	            $(this).removeClass('row_selected');
// 	        }
// 	        else {
// 	        	oTable1.$('tr.row_selected').removeClass('row_selected');
// 	            $(this).addClass('row_selected');
// 	        }
// 	    });
	}
	
	function getRightMenu(index,value) { 
	  
	    var imageMenuData = new Array();
	    var imageMenuData2 = new Array(); 
	    imageMenuData.push(
			{
        		"text": "添加到", "func": function () {
	            	var href = "?" + dd + "&Mobile=" + value;
	            	OpenPageByTab('添加', href);
	        	}
	    	}
		);
	  
		imageMenuData.push({
			"text": "复制", "func": function () {
			window.clipboardData.setData('text', value); 
				alert("成功到剪贴板");
			}
	    }); 
	    
		imageMenuData2.push(imageMenuData);
	       
	       
		return imageMenuData2;
	}
*/
	function doSearch(){
		oTable1.ajax.reload();
	}

	function showEmployee(id){
		layer.open({
		    type: 2,
		    title: '查看用户',
		    shadeClose: true,
		    shade: 0.8,
		    area: ['60%', '70%'],
		    content: "${path}/employee/showEmployee?id="+id,
		   	end : function(){
		   		oTable1.ajax.reload(null, false);
		   	}
		}); 
	}

	function addEmployee(){
		layer.open({
		    type: 2,
		    title: '新增用户',
		    shadeClose: true,
		    shade: 0.8,
		    area: ['60%', '70%'],
		    content: "${path}/employee/toSaveOrAddEmployee",
	    	end: function(){
	    		doSearch();
	    	}
		}); 
	}

	function editEmployee(id){
		layer.open({
		    type: 2,
		    title: '修改用户',
		    shadeClose: true,
		    shade: 0.8,
		    area: ['60%', '70%'],
		    content: "${path}/employee/toSaveOrAddEmployee?id="+id,
	    	end: function(){
	    		oTable1.ajax.reload(null, false);
	    	}
		}); 
	}

	function giveAuth(id){
		layer.open({
		    type: 2,
		    title: '用户赋权',
		    shadeClose: true,
		    shade: 0.8,
		    area: ['25%', '80%'],
		    content: "${path}/role/showRoleTree?employeeId="+id,
		    end : function(){
		    	oTable1.ajax.reload(null, false);
		    }
		}); 
	}
	
	var setting = {
			view: {
				dblClickExpand: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onClick: onClick
			}
		};
	
	function onClick(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getSelectedNodes(),
		v = "",
		departmentId = "";
		nodes.sort(function compare(a,b){return a.id-b.id;});
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].name + ",";
			departmentId += nodes[i].id + ",";
		}
		if (v.length > 0 ) v = v.substring(0, v.length-1);
		if (departmentId.length > 0 ) departmentId = departmentId.substring(0, departmentId.length-1);
		var cityObj = $("#departmentName");
		cityObj.attr("value", v);
		$("#departmentId").val(departmentId);
	}

	function showMenu() {
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
		if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
			hideMenu();
		}
	}

	$(document).ready(function(){
		$.fn.zTree.init($("#treeDemo"), setting, ${departmentTreeData});
	});

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
					<li class="active">用户管理</li>
				</ul>
			</div>
			<div class="page-content">
				<div class="page-content-area">
					<div class="row">
						<div class="col-md-12">
							<form class="form-horizontal" role="form">
								<div id="findDiv" class="form-group">
									<label class="col-md-1 control-label no-padding-right" for="form-field-1">用户名：</label>
									<div class="col-md-2">
										<input type="text" name="userName" id="userName">
									</div>
									<label class="col-md-1 control-label no-padding-right" for="form-field-1">机构：</label>
									<div class="col-md-3">
										<input type="hidden" name="departmentId" id="departmentId">
										<input type="text" name="departmentName" id="departmentName" readonly="readonly" onclick="showMenu(); return false;">
										&nbsp;<a id="menuBtn" href="#" onclick="showMenu(); return false;">查询</a></li>
									</div>
									<div class="col-md-1 no-padding-right">
										<a onclick="doSearch();" class="btn btn-primary btn-xs p310" data-toggle="modal">
											<i class="ace-icon glyphicon glyphicon-search"></i> 查询
										</a> 
									</div>
									<div class="col-md-1 no-padding-right">
										<a onclick="addEmployee();" class="btn btn-primary btn-xs p310" data-toggle="modal">
											<i class="ace-icon glyphicon glyphicon-plus"></i> 添加
										</a> 
									</div>
									<div class="col-md-3 no-padding-right">
									</div>
								</div>
							</form>
							<table id="myTable" class="table table-striped table-bordered table-hover" style="width: 100%">
								<thead>
									<tr>
										<th>序号</th>
										<th>姓名</th>
										<th>登陆账号</th>
										<th>性别</th>
										<th>电话</th>
										<th>手机号</th>
										<th width="15%">机构</th>
										<th>状态</th>
										<th>系统用户</th>
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
<div id="menuContent" class="menuContent" style="display:none; position: absolute;">
	<ul id="treeDemo" class="ztree" style="margin-top:0; border: 1px solid #617775;background: #f0f6e4; "></ul>
</div>
</body>
</html>

