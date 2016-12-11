<%@ include file="../../common/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="${path}/static/css/newTable.css" />
<style type="text/css">
.foot{
	position:fixed;
	_position:absolute;
	bottom:0px;
	_bottom:0px;
	_margin-top:expression(this.style.pixelHeight+document.documentElement.scrollTop);
	width: 100%;
	text-align:center;
	margin-bottom: 5px;
}
</style>
<script type="text/javascript">
function closed(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}

function doSuer(){
	var employees=[];
	$('input[name="employeeIds"]:checked').each(function(){ 
		var data = {};
		var td = this.parentNode.parentNode;
		var nameSpan = $(td).find("span");
		data.employeeId =  $(this).val();
		data.employeeName = nameSpan[1].innerHTML;
		data.safeLevel = nameSpan[2].innerHTML;
		employees.push(data);
	}); 
	
 	parent.fillEmployee(employees);
 	closed();
}

</script>
</head>
<body>
<div class="page-content">
<div class="page-content-area">
<div class="row">
	<div class="col-xs-12 col-sm-12">
		<div class="control-group">
			<form id="employeeForm" action="${path}/employee/saveOrAddEmployee.html">
				<table width="100%" align="center" class="table table-striped table-bordered table-hover">
					<c:forEach items="${employeeList}" var="obj" varStatus="count">
						<c:if test="${count.index % 3 == 0}">
							<tr align="center">
								<td style="width: 33%">
									<label class="position-relative">
										<input type="checkbox" class="ace" name="employeeIds" value="${obj.id}"/>
										<span class="lbl"></span>
									</label>
									<span tag="name">${obj.userName }</span>
									&nbsp;
									<span tag="safeLevel">${obj.safeLevel }</span>
								</td>
						</c:if>
						<c:if test="${count.index % 3 == 1}">
							<td style="width: 33%">
								<label class="position-relative">
									<input type="checkbox" class="ace" name="employeeIds" value="${obj.id}"/>
									<span class="lbl"></span>
								</label>
								
								<span>${obj.userName }</span>
								&nbsp;
								<span>${obj.safeLevel }</span>
							</td>
						</c:if>
						<c:if test="${count.index % 3 == 2}">
								<td style="width: 33%">
									<label class="position-relative">
										<input type="checkbox" class="ace" name="employeeIds" value="${obj.id}"/>
										<span class="lbl"></span>
									</label>
									
									<span>${obj.userName }</span>
									&nbsp;
									<span>${obj.safeLevel }</span>
								</td>
							</tr>
						</c:if>
						<c:if test="${count.last && count.index % 3 != 2}">
				        	<c:forEach begin="1" end="${ 2-(count.index%3) }" varStatus="innerStatus">
								<td style="width: 33%">&nbsp;</td>
								<c:if test="${innerStatus.last}">
									</tr>
								</c:if>											        	
				        	</c:forEach>
				        </c:if>
					</c:forEach>
				</table>
			</form>

			<!-- /section:custom/checkbox -->
		</div>
	</div>
</div><!-- /.row -->
</div>
</div>
</div>
<div class="foot">
	<button class="btn btn-sm btn-info" type="button" onclick="doSuer()">
		<i class="ace-icon fa fa-check bigger-110"></i>
		保 存
	</button>
		 &nbsp;&nbsp;&nbsp; 
	<button id="cancelBtn" class="btn btn-sm" type="reset" onclick="closed();">
		<i class="ace-icon fa fa-undo bigger-110"></i>
		取 消
	</button>
</div>
</body>