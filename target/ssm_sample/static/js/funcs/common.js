
var floatiewidth = "450px" // default width of floatie in px
var floatieheight = "150px" // default height of floatie in px. Set to "" to let
							// floatie content dictate height.
var floatiebgcolor = "lightyellow" // default bgcolor of floatie
var fadespeed = 70 // speed of fade (5 or above). Smaller=faster.

/**
 * 设置页面载入完后的功能
 * 
 */
$(document).ready(function() {
	// 设置表格奇偶行换色，并且光标移动换色
	changeTableColor();
});

var baseopacity = 0
function slowhigh(which2) {
	imgobj = which2
	browserdetect = which2.filters ? "ie"
			: typeof which2.style.MozOpacity == "string" ? "mozilla" : ""
	instantset(baseopacity)
	highlighting = setInterval("gradualfade(imgobj)", fadespeed)
}

function instantset(degree) {
	cleartimer()
	if (browserdetect == "mozilla")
		imgobj.style.MozOpacity = degree / 100
	else if (browserdetect == "ie")
		imgobj.filters.alpha.opacity = degree
}

function cleartimer() {
	if (window.highlighting)
		clearInterval(highlighting)
}

function gradualfade(cur2) {
	if (browserdetect == "mozilla" && cur2.style.MozOpacity < 1)
		cur2.style.MozOpacity = Math.min(
				parseFloat(cur2.style.MozOpacity) + 0.1, 0.99)
	else if (browserdetect == "ie" && cur2.filters.alpha.opacity < 100)
		cur2.filters.alpha.opacity += 10
	else if (window.highlighting)
		clearInterval(highlighting)
}

function ietruebody() {
	return (document.compatMode && document.compatMode != "BackCompat") ? document.documentElement
			: document.body
}

function paramexists(what) {
	return (typeof what != "undefined" && what != "")
}

/*
 * function showfloatie(thetext, e, optbgColor, optWidth, optHeight) { var
 * dsocx=(window.pageXOffset)? pageXOffset: ietruebody().scrollLeft; var
 * dsocy=(window.pageYOffset)? pageYOffset : ietruebody().scrollTop; var
 * floatobj=document.getElementById("dhtmlfloatie") floatobj.style.left="-900px"
 * floatobj.style.display="block"
 * floatobj.style.backgroundColor=paramexists(optbgColor)? optbgColor :
 * floatiebgcolor floatobj.style.width=paramexists(optWidth)? optWidth+"px" :
 * floatiewidth floatobj.style.height=paramexists(optHeight)? optHeight+"px" :
 * floatieheight!=""? floatieheight : "" floatobj.innerHTML=thetext var
 * floatWidth=floatobj.offsetWidth>0? floatobj.offsetWidth :
 * floatobj.style.width var floatHeight=floatobj.offsetHeight>0?
 * floatobj.offsetHeight : floatobj.style.width var
 * winWidth=document.all&&!window.opera? ietruebody().clientWidth :
 * window.innerWidth-20 var winHeight=document.all&&!window.opera?
 * ietruebody().clientHeight : window.innerHeight e=window.event? window.event :
 * e
 * 
 * 
 * 
 * 
 * floatobj.style.left=dsocx+winWidth-floatWidth-5+"px" if
 * (e.clientX>winWidth-floatWidth && e.clientY+20>winHeight-floatHeight) {
 * floatobj.style.top=dsocy+5+"px"
 *  } else { floatobj.style.top=dsocy+winHeight-floatHeight-5+"px"
 *  } slowhigh(floatobj) }
 */

// anson modify
function showfloatie(optWidth, optHeight, optbgColor) {
	var dsocx = (window.pageXOffset) ? pageXOffset : ietruebody().scrollLeft;
	var dsocy = (window.pageYOffset) ? pageYOffset : ietruebody().scrollTop;
	var floatobj = document.getElementById("dhtmlfloatie")
	floatobj.style.left = "-900px"
	floatobj.style.display = "block"
	floatobj.style.backgroundColor = paramexists(optbgColor) ? optbgColor
			: floatiebgcolor
	floatobj.style.width = paramexists(optWidth) ? optWidth + "px"
			: floatiewidth
	floatobj.style.height = paramexists(optHeight) ? optHeight + "px"
			: floatieheight != "" ? floatieheight : ""
	// floatobj.innerHTML=thetext

	var floatWidth = floatobj.offsetWidth > 0 ? floatobj.offsetWidth
			: floatobj.style.width
	var floatHeight = floatobj.offsetHeight > 0 ? floatobj.offsetHeight
			: floatobj.style.width
	var winWidth = document.all && !window.opera ? ietruebody().clientWidth
			: window.innerWidth - 20
	var winHeight = document.all && !window.opera ? ietruebody().clientHeight
			: window.innerHeight
	// e=window.event? window.event : e

	floatobj.style.left = dsocx + winWidth - floatWidth - 5 + "px"
	// if (e.clientX>winWidth-floatWidth && e.clientY+20>winHeight-floatHeight)
	// {
	// floatobj.style.top=dsocy+5+"px"
	//	
	// }
	// else
	// {
	floatobj.style.top = dsocy + winHeight - floatHeight - 5 + "px"

	// }
	slowhigh(floatobj)
}

function hidefloatie() {
	var floatobj = document.getElementById("dhtmlfloatie")
	floatobj.style.display = "none"
}

var win = null;
function openWindow(mypage, haveScroll, theWidth, theHight, theName) {
	var w = paramexists(theWidth) ? theWidth : 600;
	var h = paramexists(theHight) ? theHight : 450;
	var scroll = paramexists(haveScroll) ? haveScroll : 'no';
	var myname = paramexists(theName) ? theName : '';

	LeftPosition = (screen.width) ? (screen.width - w) / 2 : 0;
	TopPosition = (screen.height) ? (screen.height - h) / 2 : 0;
	settings = 'height=' + h + ',width=' + w + ',top=' + TopPosition + ',left='
			+ LeftPosition + ',scrollbars=' + scroll + ',resizable'
	win = window.open(mypage, myname, settings)
}

function doViewEmpInfo() {
	openWindow("../Emp_Info/EmpInfo.htm", "no", 750, 500);
}
/**
 * 打印预览
 */
function preview() {
	window.print();
}

function changeTab(obj) {
	$('.nav_current').addClass('nav_link').removeClass('nav_current');
	$(obj).addClass('nav_current').removeClass('nav_link');
	var id = $(obj).attr('id');
	var uri = $(obj).attr('uri');
	$('.undis').each(function(index, element) {
		if (id == $(this).attr('for')) {
			$(this).load(uri);
			location.href = uri;
		}
	});
}

/**
 * 添加一行新数据
 * 
 * function addNewRow(obj,rowHtml){ //$("#listBuChang_2").append('
 * <tr>
 * <td><input type="checkbox" style="width:16px;"/></td>
 * <td><input type="text" name="" style="width:30px;"/></td>
 * <td><input type="text" name="" style="width:30px;"/></td>
 * <td><input type="text" name="" style="width:30px;"/></td>
 * <td><input type="text" name="" style="width:30px;"/></td>
 * <td><input type="text" name="" style="width:30px;"/></td>
 * <td><input type="text" name="" style="width:30px;"/></td>
 * <td><input type="text" name="" style="width:30px;"/></td>
 * <td><input type="text" name="" style="width:100px;"/></td>
 * <td><input type="text" name="" style="width:100px;"/></td>
 * <td><input type="text" name="" style="width:100px;"/></td>
 * <td><input type="text" name="" style="width:100px;"/></td>
 * <td><input type="text" name="" style="width:100px;"/></td>
 * <td><input type="text" name="" style="width:100px;"/></td>
 * </tr>
 * '); obj.append(rowHtml); }
 */
// 添加新纪录页面
var dialog;
function showAdd(title, url, width, height) {
	if (height == null) {
		height = 200;
	}
	if (width == null) {
		width = 800;
	}
	$.dialog.open(url, {
		title : title,
		lock : true,
		width : width,
		height : height,
		ok : function() {
			if (confirm('确认添加新纪录？')) {
				saved();
			} else {
				return false;
			}
		},
		cancel : function() {
		}
	});
}
/**
 * 保存成功方法
 * 
 * @return
 */
function saved() {
	$.blockUI({
		message : "保存成功!",
		css : {
			top : '1%'
		}
	});
	setTimeout(function() {
		$.unblockUI({
			onUnblock : function() {
				location.reload();
			}
		});
	}, 1000);
}

function alertMsg(msg) {
	$.blockUI({
		message : '提示信息:' + msg,
		fadeIn : 700,
		fadeOut : 700,
		timeout : 1500,
		showOverlay : false,
		centerY : false,
		css : {
			width : '350px',
			top : '' + (window.innerHeight - 50) + 'px',
			left : '',
			right : '10px',
			border : 'none',
			padding : '5px',
			backgroundColor : '#000',
			'-webkit-border-radius' : '10px',
			'-moz-border-radius' : '10px',
			opacity : .6,
			color : '#fff'
		}
	});
}

var Block = {
	blockUI : function() {
		$.blockUI({
			message : '<h2>处理中,请稍候...</h2>',
			css : {
				border : '3px solid #a00'
			}
		});
	},
	unblockUI : function() {
		setTimeout($.unblockUI, 500);
	}
};

var Dialog = {
	openDialog : function(contentId, top, left) {
		var _top = top;
		var _left = left;
		if(!top){
			_top = ($(window).height() - $("#" + contentId).height())/ 2;
		}
		if(!left){
			_left = ($(window).width() - $("#" + contentId).width()) / 2;
		}
		$.blockUI({
			message : $("#" + contentId),
			css : {
				top : _top + 'px',
				left :  _left + 'px',
				width : 'auto',
				cursor : 'default'
			},
			fadeIn : 50,
			fadeOut : 50
		});
		//$('.blockOverlay').click($.unblockUI);
	},
	closeDialog : function() {
		$.unblockUI({
			fadeOut : 100
		});
	}
};

function ajaxRequest(url, params, func, errorFunc, isBlock) {
	if (isBlock)
		Block.blockUI();
	$.ajax({
		type : "POST",
		url : url,
		cache : false,
		data : params,
		success : function(result) {
			if (isBlock)
				Block.unblockUI();

			var success = result.success;
			var data = result.data;
			var msg = result.msg;

			if (success || success == "true") {
				func(msg, data);
			} else {
				errorFunc(msg, data);
			}
		},
		error : function() {
			Block.unblockUI();
			alert("Request timeout, please try again!");
		}
	});
}

function ajaxSubmit(form, callFunc, validationfunc, errorFunc, isBlock) {
	var option = new Object();
	option.beforeSubmit = function(formData, jqForm, options) {
		var isOK = true;
		if (validationfunc != null && validationfunc != undefined) {
			isOK = validationfunc(formData, jqForm, options);
		}
		if (isOK) {
			if (isBlock)
				Block.blockUI();
		}
		return isOK;
	};

	option.success = function(result) {
		if (isBlock)
			Block.unblockUI();
		if (!isJOSN(result)) {
			result = eval("(" + result + ")");
		}
		var success = result.success;
		var data = result.data;
		var msg = result.msg;

		if (success || success == "true") {
			callFunc(msg, data);
		} else {
			errorFunc(msg, data);
		}
	};

	$(form).ajaxSubmit(option);
}

function isJOSN(obj) {
	var isjson = typeof (obj) == "object"
			&& Object.prototype.toString.call(obj).toLowerCase() == "[object object]"
			&& !obj.length;
	return isjson;
}

function trimEmptyStr(obj, defaultStr) {
	if (!obj)
		return defaultStr?defaultStr:'';
	return $.trim(obj);
}

/**
 * 根据主checkbox的状态连动改变子checkbox的状态 常用于grid
 */
function checkAll() {
	if ($(":checkbox[alt=all]").attr("checked") == undefined
			|| $(".all").attr("checked") == ""
			|| $(".all").attr("checked") == false) {
		$(":checkbox[alt=one]").attr("checked", false);
	} else {
		$(":checkbox[alt=one]").attr("checked", true);
	}
}

/**
 * 获取多个复选框选择的值,拼装成字符串,中间以逗号隔开
 */
function getSelectData(){
	var ids = $('input[type=checkbox][alt=one]:checked');
	if(ids.length == 0){
		return '';
	}
	var data = '';
	$(ids).each(function(index,value){
		if(index == 0){
			data = $(this).val();
		} else {
			data = $(this).val()+',' + data;
		}
	});
	return data;
}

/**
 * 获取多个复选框选择的值,封装成数组
 */
function getSelectDataAsArray(){
	var ids = $('input[type=checkbox][alt=one]:checked');
	var data=new Array();
	$(ids).each(function(index,value){
		data.push($(this).val());
	});
	return data;
}

// 统计文本字符
function limitMaxSize(obj, countLimit) {
	var tarea = obj;
	var textLength = tarea.value.length;
	if (countLimit - textLength <= 0) {
		tarea.value = tarea.value.substring(0, countLimit);
	}
}

function showMisc(obj) {
	var val = $(obj).val();
	if (val != -1) {
		$(obj).nextAll("input[name$='_@MISC@']").each(function(index, value) {
			$(this).prev().remove();
			$(this).remove();
		});
		return;
	}
	var id = $(obj).attr("id");
	var name = $(obj).attr("name");
	var inputHtml = '<br /><input type="text" style="width:40px;" size="50" maxSize="50" id="'
			+ id + '_@MISC@" name="' + name + '_@MISC@" value="" />';
	$(inputHtml).insertAfter($(obj));
}

function changeTableColor() {
	$("#myTable >tbody >tr:even").css({
		"background-color" : "#CDD9E1"
	});
	$("#myTable >tbody >tr:odd").css({
		"background-color" : "#CDD9E1"
	});
	$("#myTable >tbody >tr").mousemove(function() {
		$(this).css({
			background : "#fffae1"
		});
	});

	$("#myTable >tbody >tr").mouseout(function() {
		$("#myTable >tbody >tr:even").css({
			"background-color" : "#CDD9E1"
		});
		$("#myTable >tbody >tr:odd").css({
			"background-color" : "#CDD9E1"
		});
	});
}

Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	};

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}

	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
}