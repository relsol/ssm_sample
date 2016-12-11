<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${path }/static/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path }/static/js/plugins/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="${path }/static/js/plugins/jquery.form.js"></script>
<script type="text/javascript">
<!--
function check(){
	var type = $.trim($("#type").val());
	var fid = $.trim($("#fid").val());
	var flg = true;
	if(!type || type=='-1'){
		$('#typeMsg').html('提示:请选择类型!');
		flg = false;
	} else {
		$('#typeMsg').html('');
	}
	if (!fid || fid=='-1') {
		$('#fidMsg').html('提示:请选择管辖区间!');
		flg = false;
	} else {
		$('#fidMsg').html('');
	}
	return flg;
}

function closed(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}

function doSave(){
	if(check()){
		var i = parent.layer.load(0,1);//提交加上遮罩
		var options = {
			type:'post',
			success:function(data){
				parent.layer.close(i);//去掉遮罩
				if(data == 'success'){
					parent.layer.msg("操作成功!",1,9,function(){
						parent.returnSearch();
						parent.layer.closeAll();
					});
				} else {
					parent.layer.msg(data,1,8);
				}
    		},
    		error:function(){
    			parent.layer.close(i);//去掉遮罩
    			parent.layer.msg("请求服务器失败,请检查网络!",2,8);
    		}
		};
		$('#form').ajaxSubmit(options);
	}
}

$(document).ready(function(){
	$("#type").change(function(){
		if(this.value=="-1"){
			return;
		}
		$.ajax({
			url:'${path}/department/showJurisdictList',
			data:{'departmentId':$("#departmentId").val(), 'type':$("#type").val()},
			async:false,
			cache:false,
			dataType:'json',
			success:function(data){
				$("#fid").empty();
				$("#fid").append("<option value='-1'>请选择</option>");
				for(var i=0; i<data.data.length; i++){
					$("#fid").append("<option value='"+data.data[i].id+"'>"+data.data[i].name+"</option>");
				}
			}
		});
	});
	
	$("#fid").change(function(){
		if(this.value=="-1" || $("#type").val()=="1"){
			return;
		}
		$.ajax({
			url:'${path}/section/showSectionById',
			data:{'sectionId':this.value},
			async:false,
			cache:false,
			dataType:'json',
			success:function(data){
				$("#startKilo").val(data.startKiloShow);
				$("#endKilo").val(data.endKiloShow);
			}
		});
	});
	
	$("#type").change(function(){
		if(this.value != -1){
			$('#typeMsg').html('');
		}
	});
	$("#fid").change(function(){
		if(this.value != -1){
			$('#fidMsg').html('');
		}
	});
});
//-->		
</script>
</head>
<body>
	<br />
	<form id="form" method="post" action="${path}/department/saveJurisdict" onsubmit="return false;">
		<input type="hidden" id="departmentId" name="departmentId" value="${department.id}" />
		<div style="margin: 0 10px;">
			<table width="100%" align="center" class="newTable" cellspacing="0" cellpadding="0" border="0">
					<tr>
						<td align="right">机构名称:</td>
						<td align="left">
							${department.name }
						</td>
						<td align="right"></td>
						<td align="left">
						</td>
					</tr>
					<tr>
						<td align="right">类型:<font color="red">*</font></td>
						<td align="left">
							<select id="type" name="type">
								<option value="-1">请选择</option>
								<c:forEach items="${JURISDICT_MAP}" var="map">
									<option value="${map.key}">${map.value}</option>
								</c:forEach>
							</select>
							<div id="typeMsg" style="color: red"></div>
						</td>
						<td align="right" width="200">管辖区间:<font color="red">*</font></td>
						<td align="left" width="310">
							<select id="fid" name="fid">
								<option value="-1">请选择</option>
							</select>
							<div id="fidMsg" style="color: red"></div>
						</td>
					</tr>
					<tr>
						<td align="right" width="200">起始公里标:</td>
						<td align="left" width="300">
							<input type="text" id="startKilo" name="startKilo" value="" />
						</td>
						<td align="right" width="200">结束公里标:</td>
						<td align="left" width="310">
							<input type="text" id="endKilo" name="endKilo" value="" />
						</td>
					</tr>
				<tfoot>
		        <tr>
					<td colspan="4" align="center">
						<a href="javascript:;" onclick="doSave();" style="width: 40px;" class="Button_small">保 存</a>
						&nbsp;
			        	<a href="###" onclick="closed();" style="width: 40px;" class="Button_small">关 闭</a>
					    <div class="clear"></div>
					</td>	        
		        </tr>
	        </tfoot>
			</table>
		</div>
	</form>
</body>
</html>
