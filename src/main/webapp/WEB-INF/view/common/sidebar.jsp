<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="sidebar" class="sidebar responsive">
	<script type="text/javascript">
		try {
			ace.settings.check('sidebar', 'fixed')
		} catch (e) {
		}
	</script>
	<!-- /.sidebar-shortcuts -->
	<ul class="nav nav-list">
		<c:forEach items="${menus}" var="m">
			<c:if test="${m.level == 1}">
				<li id="${m.id }" class="hsub">
			</c:if>
			<c:if test="${m.level == 2}">
				<li id="${m.id }" class="">
			</c:if>
				<a href="#" class="dropdown-toggle">
					<i class="menu-icon fa fa-desktop"></i> 
					<span class="menu-text"> ${m.name } </span> 
					<b class="arrow fa fa-angle-down"></b>
				</a>
				<b class="arrow"></b>
				<c:if test="${fn:length(m.children) > 0}">
					<ul class="submenu">
						<c:forEach items="${m.children}" var="subm">
							<li id="${subm.id }" class="">
								<a data-url="page/tables" href="${path}${subm.uri}" target="iframepage"> 
									<i class="menu-icon fa fa-caret-right"></i>${subm.name }
								</a> 
								<b class="arrow"></b>
							</li>
						</c:forEach>
					</ul>
				</c:if>
			</li>
		</c:forEach>
	</ul>
	<!-- /.nav-list -->

	<!-- #section:basics/sidebar.layout.minimize -->
	<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
		<i class="ace-icon fa fa-angle-double-left"
			data-icon1="ace-icon fa fa-angle-double-left"
			data-icon2="ace-icon fa fa-angle-double-right"></i>
	</div>

	<!-- /section:basics/sidebar.layout.minimize -->
	<script type="text/javascript">
		try {
			ace.settings.check('sidebar', 'collapsed')
		} catch (e) {
		}

// 		function doActive(id) {
// 			$("li").removeClass("active");
// 			$("#"+id).parent().parent().addClass(' open active');
// 			$("#"+id).addClass(' active');
// 		}
		
	</script>
</div>