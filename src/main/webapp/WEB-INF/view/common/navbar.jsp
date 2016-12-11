<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="navbar" class="navbar navbar-default">
		<script type="text/javascript">
			try {
				ace.settings.check('navbar', 'fixed');
			} catch (e) {
			}
		</script>

		<div class="navbar-container" id="navbar-container">
			<!-- #section:basics/sidebar.mobile.toggle -->
			<button type="button" class="navbar-toggle menu-toggler pull-left"
				id="menu-toggler">
				<span class="sr-only">Toggle sidebar</span> <span class="icon-bar"></span>

				<span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>

			<!-- /section:basics/sidebar.mobile.toggle -->
			<div class="navbar-header pull-left">
				<!-- #section:basics/navbar.layout.brand -->
				<a href="#" class="navbar-brand"> <small> <i
						class="fa fa-leaf"></i> 锦州供电段信息平台
				</small>
				</a>
			</div>
			<div class="navbar-buttons navbar-header">
				<ul class="nav navbar-nav">
					<li>
						<a href="${cnntPath}" target="_blank">接触网管理子系统</a>
					</li>
				</ul>
				<ul class="nav navbar-nav">
					<li>
						<a href="${tranPath}" target="_blank">变电所管理子系统</a>
					</li>
				</ul>
				<ul class="nav navbar-nav">
					<li>
						<a href="${powerPath}" target="_blank">电力管理子系统</a>
					</li>
				</ul>
			</div>
			
			<!-- #section:basics/navbar.dropdown -->
			<div class="navbar-buttons navbar-header pull-right" role="navigation">
				<ul class="nav ace-nav">
					<li class="grey">
						<a data-toggle="dropdown" class="dropdown-toggle" href="#"> 
							<i class="ace-icon fa fa-tasks"></i><span class="badge badge-grey" name="perChangeListCount">0</span>
						</a>
						<ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
							<li class="dropdown-header">
								<i class="ace-icon fa fa-check fa-exclamation-triangle"></i>
								<span name="perChangeListCount">0</span> 消息
							</li>
							<li class="dropdown-content">
								<ul class="dropdown-menu dropdown-navbar" id="perChangeDiv">
								</ul>
							</li>
						</ul>
					</li>


					<!-- #section:basics/navbar.user_menu -->
					<li class="light-blue">
						<a data-toggle="dropdown" href="#" class="dropdown-toggle"> 
							<img class="nav-user-photo" src="${employeePhoto}" width="40" height="40"/> 
							<span > ${employee.userName} </span> <i class="ace-icon fa fa-caret-down"></i>
						</a>
						<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
							<li><a href="#" onclick="toModifyPwd()"><i class="ace-icon fa fa-cog"></i>修改密码</a></li>
							<li><a href="#" onclick="showEmployee('${employee.id}')"><i class="ace-icon fa fa-user"></i> 个人资料 </a></li>
							<li class="divider"></li><li><a href="${casServerPath}/logout?service=${basePath }dologout"><i class="ace-icon fa fa-power-off"></i>退出</a></li>
						</ul>
					</li>
					<!-- /section:basics/navbar.user_menu -->
				</ul>
			</div>

			<!-- /section:basics/navbar.dropdown -->
		</div>
		<!-- /.navbar-container -->
	</div>
	<script type="text/javascript">
	
	$(function(){
		/* findByPersonnelId();
		isLoginNameModifyVisible(); */
	});
	
	function findByPersonnelId(){
		$.ajax({
			url:"${path}/personnelChange/findByPersonnelId.html",
			success:function(data){
				if(data){
					var json = eval('(' + data + ')');
					var html = '';
					for(var i=0; i<json.length; i++){
						html += '<li onclick="doRemove(\' '+json[i].id+' \')" id="perChange'+json[i].id+'">';
						html += '<a href="#">';
						html += '	<div class="clearfix">';
						html += '		<span class="pull-left"> ';
						if(json[i].status == '1'){
							html += '				<i class="btn btn-xs no-hover btn-pink fa fa-comment"></i> ';
							html += '				修改档案审核中';
						} else if(json[i].status == '3'){
							html += '			<i class="btn btn-xs no-hover btn-pink fa fa-comment"></i> ';
							html += '			修改档案通过';
						} else if(json[i].status == '5'){
							html += '		<i class="btn btn-xs no-hover btn-pink fa fa-comment"></i> ';
							html += '			修改档案驳回';
						}
						html += '		<span title="${data.note}">${data.note}</span>';
						html += '	</span> ';
						html += '</div>';
						html += '</a>';
						html += '	</li>';
					}
					$("#perChangeDiv").html(html);
					$("span[name='perChangeListCount']").html(json.length);
				}
			}
		});
	}
	
	function isLoginNameModifyVisible(){
		var loginName = '${employee.loginName}';
		if (isIdCardNo(loginName) == false) {
			$('#modifyLoginNameLi').hide();
		}
	}
	
	function doRemove(id){
		$.ajax({
			url:"${path}/personnelChange/toChangeCheckStatus.html",
			data:{"id" : id},
			type: "post",
			success:function(){
				findByPersonnelId();
			}
		});
	}
	
	function showEmployee(id){
		layer.open({
		    type: 2,
		    title: '查看用户',
		    shadeClose: true,
		    shade: 0.8,
		    area: ['45%', '55%'],
		    content: "${path}/employee/showEmployee.html?id="+id
		}); 
	}
	
	function toModifyPwd(){
		layer.open({
		    type: 2,
		    title: '修改密码',
		    shadeClose: true,
		    shade: 0.8,
		    area: ['40%', '40%'],
		    content: "${path}/employee/toModifyPwd.html"
		}); 
	}
	
	function toDoAdd(){
		layer.open({
		    type: 2,
		    title: '档案变更',
		    shadeClose: true,
		    shade: 0.8,
		    area: ['50%', '60%'],
		    content: "${path }/personnelChange/toAdd.html"
		}); 
	}
	
	function toModifyLoginName(){
		layer.open({
		    type: 2,
		    title: '登录账号变更',
		    shadeClose: true,
		    shade: 0.8,
		    area: ['50%', '60%'],
		    content: "${path }/employee/toModifyLoginName.html"
		}); 
	}
	</script>