<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>南京供电段信息平台</title>
<link rel="shortcut icon" type="image/x-icon"  href="${path }/static/images/favicon.ico"/> 
<meta name="description" content="Static &amp; Dynamic Tables" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<%@ include file="common/common.jsp"%>
</head>

<body class="no-skin">
	<%@ include file="/WEB-INF/view/common/navbar.jsp"%>
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>
		<%@ include file="/WEB-INF/view/common/sidebar.jsp"%>
		<div class="main-content">
			<iframe src="" id="iframepage" name="iframepage" frameBorder=0 width="100%"></iframe>
		</div>
	</div>
	
	<script type="text/javascript">
		$().ready(function(){
			var navbarDiv = window.document.getElementById('navbar');
			var ifm = document.getElementById("iframepage");
			ifm.height = document.documentElement.clientHeight - navbarDiv.offsetHeight - 10;
			var sidebarDiv = window.document.getElementById('sidebar');
			sidebarDiv.style.height = document.body.scrollHeight - navbarDiv.offsetHeight - 10 + 'px';
		});
	</script>

</body>
</html>

