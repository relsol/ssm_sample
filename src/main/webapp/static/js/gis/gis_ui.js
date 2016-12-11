/**
 * 切换tab页效果的方法
 */
$("#leftside li").click(function() {
	//清空上次查询的内容
	$("#searchContent").empty();
	//清除计数结果
	$("#counttips").text("0");
	
	changeTabs($(this).attr("id"));
});

/**
 * 标签变换效果
 * @param tagFlag
 */
function changeTabs(tagFlag){
	
	if (tagFlag == "tags_cnnt") {
		// 去除所有的li标签中的select样式
		$("#leftside li").removeClass("select");
		$("#tags_cnnt").addClass("select");
		$("#tab_sp0").addClass("select");
		//显示普通查询form，隐藏高级查询form
		$("#search_form").show();
		$("#search_mobile_form").hide();
		//设置隐藏域的值,设置查询类型为接触网类型
		$("#searchType").val(searchType["CNNT"]);
	} else if (tagFlag == "tags_power") {
		// 去除所有的li标签中的select样式
		$("#leftside li").removeClass("select");
		$("#tags_power").addClass("select");
		$("#tab_sp0").addClass("select");
		$("#tab_sp1").addClass("select");
		//显示普通查询form，隐藏高级查询form
		$("#search_form").show();
		$("#search_mobile_form").hide();
		//设置隐藏域的值
		$("#searchType").val(searchType["POWER"]);
	} else if (tagFlag == "tags_tran") {
		// 去除所有的li标签中的select样式
		$("#leftside li").removeClass("select");
		$("#tags_tran").addClass("select");
		$("#tab_sp1").addClass("select");
		$("#tab_sp2").addClass("select");
		//显示普通查询form，隐藏高级查询form
		$("#search_form").show();
		$("#search_mobile_form").hide();
		//设置隐藏域的值
		$("#searchType").val(searchType["TRAN"]);
	} else if (tagFlag == "tags_mobile") {
		// 去除所有的li标签中的select样式
		$("#leftside li").removeClass("select");
		$("#tags_mobile").addClass("select");
		$("#tab_sp2").addClass("select");
		$("#tab_sp3").addClass("select");
		//隐藏普通查询form，显示高级查询form
		$("#search_form").hide();
		$("#search_mobile_form").show();
	}
}


/**
 * 搜索输入框的提示内容的显示效果
 */
$("#keyword").focus(function(){
	//获取焦点时，取消label中的text值
    $("#l_query").text("");
});
$("#keyword").focusout(function(){
	//失去焦点并且搜索关键字为空时，回复label中的text值
    if($(this).val() == ""){
        $("#l_query").text("请输入查询内容")
    }
});


/**
 * 高级搜索输入框的提示内容的显示效果
 */
$("#advanced_query").focus(function(){
	//获取焦点时，取消label中的text值
    $("#l_advanced_query").text("");
});
$("#advanced_query").focusout(function(){
	//失去焦点并且搜索关键字为空时，回复label中的text值
    if($(this).val() == ""){
        $("#l_advanced_query").text("请输入查询内容")
    }
});




