<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../public/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>流程图片</title>
<link href="${path }/resources/css/style.css" rel="stylesheet" type="text/css" />
<link href="${path }/resources/css/progressBar.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	.tdStyle
	{
		text-align: right;
		background-color: #eff4fb;
		font-size: 16px;
		height:50px;
		font-weight: bold;
		width: 125px;
	}
	#process .node ul{z-index:1;width:238px;margin-left:-152px;}
	.section3{width:90%;}
</style>
<script type="text/javascript">
$(document).ready(function(){
	var count = ${hisLength };
	if(count > 0){
		$("#process div.node").each(function(i){
			if(count > i){
				$(this).addClass("ready");
			}
		});
		$("#process div.proce").each(function(i){
			if(count > i+1){ 
				$(this).addClass("ready");
			}
		});
	}else{
		$("#process div.node").each(function(i){
			$(this).addClass("ready");
		});
	}
});
</script>
</head>
<body style="padding-left: 25px;">
	<!--进度条-->
           <div class="section3" id="process">
			<c:if test="${not empty teemWorkProcessList }">
	    	  <c:forEach items="${teemWorkProcessList }" var="process" varStatus="status">
	    	  	<c:if test="${status.count!=1 }">
					<div class="proce wait"  title="${process.remark }">
						<ul>
							<li class="tx1">&nbsp;</li>
						</ul>
					</div>
				</c:if>
				<div class="node wait" title="${process.remark }">
					<ul>
						<li class="tx1">&nbsp;</li>
						<li class="tx2">${process.dealUserName }${process.content }</li>
						<li class="tx3" id="track_time_0">
							<fmt:formatDate value="${process.dealTime}" pattern="yyyy-MM-dd "/>
							<br/>
							<fmt:formatDate value="${process.dealTime}" pattern="HH:mm:ss"/>
						</li>
					</ul>
				</div>
			</c:forEach>
			</c:if>
	    </div>
</body>
</html>