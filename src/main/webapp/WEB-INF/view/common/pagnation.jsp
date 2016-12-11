<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
											<ul class="pagination">
											
											
											
											<c:choose>
													<c:when test="${pageInfo.hasPreviousPage }">
															<li class="previous">
																<a href="#no=${pageInfo.prePage }">上一页</a>
															</li>
													</c:when>
													<c:otherwise>
														<li class="previous disabled">
																<a href="#no=${pageInfo.prePage }">上一页</a>
														</li>
													</c:otherwise>
												</c:choose>
											
											
											
										
											
											<c:choose>
													<c:when test="${pageInfo.isLastPage }">
														
														<li class="disabled">
															<a href="#no=${pageInfo.pages }">
																<i class="ace-icon fa fa-angle-double-left"></i>
															</a>
														</li>
														
													</c:when>
													<c:otherwise>
														<li>
															<a href="#no=${pageInfo.pages }">
																<i class="ace-icon fa fa-angle-double-left"></i>
															</a>
														</li>
													</c:otherwise>
												</c:choose>
												
												
												<c:choose>
													<c:when test="${pageInfo.pageSize != 0}">
														<fmt:parseNumber var="range" value="${(pageInfo.pageNum-1)/pageInfo.pageSize}" integerOnly="true" />
													</c:when>
													<c:otherwise>
														<fmt:parseNumber var="range" value="0" integerOnly="true" />
													</c:otherwise>
												</c:choose>
												
												<fmt:parseNumber var="start" value="${range*pageInfo.pageSize}" integerOnly="true" />
												<fmt:parseNumber var="end" value="${start+pageInfo.pageSize}" integerOnly="true" />
													<c:set var="end" value="${end>pageInfo.pages?pageInfo.pages:end}"/>
									
												
												
												<c:forEach begin="${start+1 }" end="${end}" varStatus="s">
												<c:choose>
													<c:when test="${s.index == pageInfo.pageNum }">
														<li class="active">
															<a href="#no=${s.index }">${s.index }</a>
														</li>
													</c:when>
													<c:otherwise>
															<li>
																<a href="#no=${s.index }">${s.index }</a>
															</li>
													</c:otherwise>
												</c:choose>
												</c:forEach>	
												
												
													<c:choose>
													<c:when test="${pageInfo.isLastPage }">
														
														<li class="disabled">
															<a href="#no=${pageInfo.pages }">
																<i class="ace-icon fa fa-angle-double-right"></i>
															</a>
														</li>
														
													</c:when>
													<c:otherwise>
														<li>
															<a href="#no=${pageInfo.pages }">
																<i class="ace-icon fa fa-angle-double-right"></i>
															</a>
														</li>
													</c:otherwise>
												</c:choose>
												
												
												<c:choose>
													<c:when test="${pageInfo.hasNextPage }">

														<li class="next">
															<a href="#no=${pageInfo.nextPage }">下一页 </a>
														</li>
														
													</c:when>
													<c:otherwise>
															<li class="disabled">
																<a href="#no=${pageInfo.nextPage }">下一页 </a>
															</li>
													</c:otherwise>
												</c:choose>
												
													
											
											
											</ul>
											<div class="">共${pageInfo.pages }页，共${pageInfo.total }条</div>
											
											
<script type="text/javascript">
<!--
		
function page(){
	if($(this).parent().hasClass('disabled')) return;
	if($(this).parent().hasClass('active')) return;
	
	var href = this.href;
	var no = href.substr(href.indexOf('#')+4);
	$('#pageNo').val(no);
	$('#searchForm').submit();
}
$(document).ready(function(){
	$('.pagination a').click(page);
});
//-->
</script>
											
