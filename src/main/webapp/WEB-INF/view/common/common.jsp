<%
response.setHeader("Cache-Control", "no-cache,no-store,max-age=0,must-revalidate");
response.setHeader("Expires", "Mon,12 May 2001 00:20:00 GMT");
%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%-- <%@taglib prefix="jzmis" uri="http://nk.jzmis/mytag/core" %> --%>

<link rel="stylesheet" href="${path}/static/assets/css/bootstrap.min.css" />
<link rel="stylesheet" href="${path}/static/assets/css/font-awesome.min.css" />
<link rel="stylesheet" href="${path}/static/assets/css/ace-fonts.css" />
<link rel="stylesheet" href="${path}/static/assets/css/ace.min.css" id="main-ace-style" />
<!--[if lte IE 9]>
	<link rel="stylesheet" href="${path}/static/assets/css/ace-part2.min.css" />
<![endif]-->
<link rel="stylesheet" href="${path}/static/assets/css/datepicker.css" />
<link rel="stylesheet" href="${path}/static/assets/css/bootstrap-datetimepicker.css" />
<link rel="stylesheet" href="${path}/static/js/layer/skin/layer.css" />
<link rel="stylesheet" href="${path}/static/assets/css/ace-skins.min.css" />
<link rel="stylesheet" href="${path}/static/assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="${path}/static/js/jquery-resizable-columns/dist/jquery.resizableColumns.css" />

<!--[if lte IE 9]>
	<link rel="stylesheet" href="${path}/static/assets/css/ace-ie.min.css" />
<![endif]-->
<!-- inline styles related to this page -->
<!-- ace settings handler -->
<script src="${path}/static/assets/js/ace-extra.min.js"></script>
<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->
<!--[if lte IE 8]>
	<script src="${path}/static/assets/js/html5shiv.min.js"></script>
	<script src="${path}/static/assets/js/respond.min.js"></script>
<![endif]-->

<link href="${path}/static/assets/js/jquery.dataTables.extensions/Buttons-1.1.0/css/buttons.dataTables.min.css" type="text/css" rel="stylesheet"></link>

<!-- basic scripts -->

<!--[if !IE]> -->
<script src='${path}/static/js/jquery/jquery-1.9.1.min.js'></script>
<!-- <![endif]-->

<!--[if IE]>
	<script type="text/javascript">
	 window.jQuery || document.write("<script src='${path}/static/assets/js/jquery1x.min.js'>"+"<"+"/script>");
	</script>
<![endif]-->
	<script src="${path}/static/assets/js/bootstrap.min.js"></script>

	<!-- page specific plugin scripts -->
	<script src="${path}/static/assets/js/jquery.dataTables.min.js"></script>
	<script src="${path}/static/assets/js/jquery.dataTables.bootstrap.js"></script> 
	
	<!-- ace scripts -->
	<script src="${path}/static/assets/js/ace-elements.min.js"></script>
	<script src="${path}/static/assets/js/ace.js"></script>
	<script src="${path}/static/assets/js/ace.sidebar.js"></script>
	<script src="${path}/static/assets/js/ace.submenu-1.js"></script>
	<script src="${path}/static/assets/js/date-time/bootstrap-datepicker.min.js"></script>
	<script src="${path}/static/assets/js/date-time/bootstrap-datetimepicker.js"></script>
	<script src="${path}/static/assets/js/jquery.dataTables.extensions/Buttons-1.1.0/js/dataTables.buttons.js"></script>
	<script src="${path}/static/assets/js/jquery.dataTables.extensions/Buttons-1.1.0/js/buttons.colVis.min.js"></script>
	<script src="${path}/static/assets/js/jquery.dataTables.extensions/Buttons-1.1.0/js/buttons.print.min.js"></script>
	
	
	
	<script src="${path}/static/js/jquery/jquery.dataTables-zh-CN.js"></script>
	<script src="${path}/static/assets/js/date-time/locales/bootstrap-datepicker.zh-CN.js"></script>
	<script src="${path}/static/assets/js/date-time/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<script src="${path}/static/js/layer/layer.js"></script>
	<script src="${path}/static/js/layer/extend/layer.ext.js"></script>
	<script src="${path}/static/js/jquery/jquery.form.js"></script>
	
	<script type="text/javascript" src="${path}/static/js/funcs/common.js"></script>
	
	<script src="${path}/static/js/jquery-resizable-columns/dist/jquery.resizableColumns.js"></script>
	<style>
	body{background:#fff;}
	
	div.dt-buttons,.dataTables_info{
		padding-left:5px;
		margin:2px;
	}
	input{
		margin-top:2px;
		height:25px;
	}
	.numControl {
	    width: 100px;
	    overflow: hidden;
	    text-overflow: ellipsis;
	    white-space: nowrap;
	    display:block;
	}
	</style>
<script type="text/javascript">
<!--
 var offsetHeight;
function getLocalTime(nS) {
	var date = new Date(nS);
	var Y = date.getFullYear() + '-';
	var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
	var D = (date.getDate() < 10 ? '0'+(date.getDate()+1) : date.getDate()+1) + '-' + ' ';
	var h = (date.getHours() < 10 ? '0'+date.getHours() : date.getHours()) + ':';
	var m = (date.getMinutes() < 10 ? '0'+date.getMinutes() : date.getMinutes()) + ':';
	var s = date.getSeconds() < 10 ? '0'+date.getSeconds() : date.getSeconds();
	return Y+M+D+h+m+s;
}
//-->
</script>
