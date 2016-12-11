<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.apache.commons.lang.StringUtils,com.nk.mis.frame.common.GlobalConfigure" %>
<!DOCTYPE html>
<html>
<head>
 <link rel="stylesheet" href="${path }/resources/js/funcs/pagination/pagination.css" />
<%	
	int pageSize = GlobalConfigure.DEFAULT_PAGE_SIZE;	//每页显示的行数
	int pageIndex = 1;					//当前页数
	int rowSize = 0;					//记录集大小 
	String path = request.getContextPath();
	
	if(!StringUtils.isEmpty(request.getParameter("pageSize"))) {
		pageSize=Integer.parseInt(request.getParameter("pageSize")); //如果传入每页显示的页数，则使用传入值
	}
	if(!StringUtils.isEmpty(request.getParameter("pageIndex"))) {
		pageIndex=Integer.parseInt(request.getParameter("pageIndex")); //获取当前的页数 
	}
	if(!StringUtils.isEmpty(request.getParameter("rowSize"))) {
		rowSize = Integer.parseInt(request.getParameter("rowSize")); //获取记录集大小
	}
	
	int pages = (rowSize % pageSize)==0 ? rowSize/pageSize : rowSize/pageSize+1; //根据记录集大小和页大小，计算出总页数

%>
<script type="text/javascript" src="${path }/resources/js/funcs/pagination/jquery.pagination.js"></script>
<script type="text/javascript">

function show(pageIndex){
	if(pageIndex < 1){
		pageIndex = 1;
	}
	if(pageIndex > <%=pages%>){
		pageIndex = <%=pages%>;
	}
	var form = $("#" + "${param.currentFormId}");
	$("#pageIndex").val(pageIndex);
	form.submit();
}

function go(){
  	var index=$("#pageNo").val();
  
  	//isNaN(index)当index为字符串时返回TRUE
  	if(isNaN(index) || index<1 || index.indexOf(".")>=0){
    	alert("输入错误：跳转页数必须为正整型！");
  	}else if(index > <%=pages%>){
    	alert("输入错误：跳转页数不能超过总页数！");
  	}else if(index == <%=pageIndex%>){
    	alert("输入页码就是当前页！");
  	}else{
   	 	show(index);
  	}
}

function pageselectCallback(page_index, jq){
    var index=page_index+1;
    
  	//isNaN(index)当index为字符串时返回TRUE
  	if(isNaN(index) || index<1){
//     	alert("输入错误：跳转页数必须为正整型！");
  	}else if(index > <%=pages%>){
//     	alert("输入错误：跳转页数不能超过总页数！");
  	}else if(index == <%=pageIndex%>){
//     	alert("输入页码就是当前页！");
  	}else{
   	 	show(index);
  	}
}

//配置
function getOptionsFromForm(){
    var opt = {callback: pageselectCallback};
    //当前页
    opt["current_page"] = <%=pageIndex %>-1 ;
    //边缘显示几个页码块
    opt["num_edge_entries"] = 2 ;
    //一共显示几个页码块
    opt["num_display_entries"] = 10 ;
    //上一页名称
    opt["prev_text"] = "上一页" ;
    //下一页名称
    opt["next_text"] = "下一页" ;
    //页面大小
    opt["items_per_page"] = <%=pageSize %> ;
    return opt;
}

$(document).ready(function(){
    // Create pagination element with options from form
    var optInit = getOptionsFromForm();
    $("#Pagination").pagination(<%=rowSize %>, optInit);
    // Event Handler for for button
    $("#setoptions").click(function(){
        var opt = getOptionsFromForm(); 
        // Re-create pagination content with new parameters
        $("#Pagination").pagination(<%=rowSize %>, opt);
    });

});

</script>
  <title>Pagination</title>
  <style type="text/css">
     <!--
     * {padding: 0; margin: 0;}
     body { background-color: #fff; padding: 0;  height: 100%; font-family: Arial, Helvetica, sans-serif; }
     -->
 </style>
</head>
<body>
  <div id="content" style="margin-top: 7px;float: right;">
            <div id="Pagination" class="pagination" style="float: right;">
            </div>
            <div style="float:right;margin-right:5px;line-height:27px;float: right;">记录<strong><%=rowSize %></strong>条 <b><%=pageSize %></b>条/页</div>
        </div>
 
<div class="pagebar"  style="visibility: hidden;">
		<div style="float:right; margin-left:5px;line-height:32px">
			转到：<input id="pageNo" type='text' style='width:20px;border: 1px #000000 solid; font-size: 9pt;vertical-align:middle;' name='GoToPageNum' size=4 maxlength=10 value="">&nbsp;
                 <input type='button' onclick="go();" style='width:40px;cursor:pointer;border: 1px #000000 solid;  background-color: #EEEEEE; height: 18px; font-size: 9pt;vertical-align:middle' value='转到' id='button' name='button'></div>
       	<div style="float:right;margin-left:5px;line-height:32px">页次：<b><font color=#990000><%=pageIndex%></font>/<%=pages %></b>页 记录<strong><%=rowSize %></strong>条 <b><%=pageSize %></b>条/页</div>
       	<div style="float:right;margin-left:5px;line-height:32px"><a href="javascript:;" onclick="show('<%=pages %>');" style="color:#999966">尾页</a></div>
       	<div style="float:right;margin-left:5px;line-height:32px"><a href="javascript:;" onclick="show('<%=pageIndex+1%>');" style="color:#999966">下一页</a></div>
        <div style="float:right;margin-left:5px;line-height:32px"><a href="javascript:;" onclick="show('<%=pageIndex-1%>');" style="color:#999966">上一页</a></div>
        <div style="float:right;margin-left:5px;line-height:32px"><a href="javascript:;" onclick="show('1');" style="color:#999966">首页</a></div>
        <div style="clear:both"></div>
    <div class="fr page">
    	<input type="hidden" id="pageIndex" name="pageIndex" value="<%=pageIndex%>">
		<input type="hidden" id="pageSize" name="pageSize" value="<%=pageSize%>">
    </div>
</div>
</body>
</html>