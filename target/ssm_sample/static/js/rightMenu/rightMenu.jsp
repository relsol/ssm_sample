<%@page import="com.nk.nksy.mis.constants.MisConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div id="floatTools" class="float0831">
			<div class="floatL">
				<a title="展开" class="btnOpen" id="aFloatTools_Show" style="display: none;" onclick="javascript:$('#divFloatToolsView').animate({width: 'show', opacity: 'show'}, 'normal',function(){ $('#divFloatToolsView').show(); });$('#aFloatTools_Show').attr('style','display:none');$('#aFloatTools_Hide').attr('style','display:block');" href="javascript:void(0);"></a> 
				<a title="收缩" class="btnCtn" id="aFloatTools_Hide" style="display: block;" onclick="javascript:$('#divFloatToolsView').animate({width: 'hide', opacity: 'hide'}, 'normal',function(){ $('#divFloatToolsView').hide(); });$('#aFloatTools_Show').attr('style','display:block');$('#aFloatTools_Hide').attr('style','display:none');" href="javascript:void(0);"></a>
			</div>
			<div id="divFloatToolsView" class="floatR">
				<div class="tp"></div>
				<div class="cn">
					<ul>
						<li class="zonghe">
							<a href="javaScript:void()" onclick="locationOpen('/nksy')" class="fa fa-sitemap">&nbsp;综合平台</a>
						</li>
					</ul>
					<ul>
						<li class="top">
							<h2 class="titZx">
								第一层
							</h3>
						</li>
						<li>
							<a href="javaScript:void()" onclick="locationOpen('/nksy/mis/index.html')" class="fa fa-home">&nbsp;综合系统</a>
						</li>
					</ul>
					<ul>
						<li class="top">
							<h2 class="titDh">
								第二层
							</h3>
						</li>
						<li>
							<a href="javaScript:void()" onclick="locationOpen('<%=MisConstant.VIDEO_INDEX%>')" class="fa fa-video-camera" >&nbsp;综合视频</a>
						</li>
						<li>
							<a href="javaScript:void()" onclick="winOpenFullScreen('<%=MisConstant.SCADAPATH_INDEX%>')" class="fa fa-bus">&nbsp;SCADA系统</a>
						</li>
						<li>
							<a href="javaScript:void()" onclick="winOpenFullScreen('<%=MisConstant.NKWD_INDEX%>')" class="fa fa-sitemap">&nbsp;维调系统</a>
						</li>
						<li>
							<a href="javaScript:void()" onclick="locationOpen('<%=MisConstant._6C_INDEX%>')" class="fa fa-th">&nbsp;6C系统</a>
						</li>
						<li>
							<a href="javaScript:void()" onclick="locationOpen('/nksy/problem/index.html')" class="fa fa-question">&nbsp;&nbsp;问题库</a>
						</li>
						
					</ul>
				</div>
			</div>
		</div>