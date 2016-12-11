<link href="${path}/resources/css/gis/global.css" rel="stylesheet" type="text/css" />
<%@ page language="java" pageEncoding="UTF-8"%>
<div>
    <div style="width: 100%;border-bottom: solid 3px #0F0F0F;">
    	<div class="logo_sub">
    		<div id="sub_head_title" class="logo_sub_bgTitle"><span>锦州项目地图测试</span><span> ${subTitle } </span></div>
        </div>
        <div class="logo_slide">
        </div>
        <div style="float: left;height: 36px;line-height: 36px;">
            <span id="time"></span> &nbsp;&nbsp;|&nbsp;&nbsp; <c:if test="${not empty currentUser.realname}"><span>${currentUser.realname }</span></c:if>
        </div>
        <div style="clear: both;"></div>
    </div>
</div>

<script type="text/javascript">
$(document).ready(function(){
		//加载javascript
		$.getScript('${path}/resources/js/gis/topnav.js');
		/* $.getScript('${path}/resources/js/rightMenu/rightMenu.js'); */
	});
</script>