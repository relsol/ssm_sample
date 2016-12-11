/**
 * 综合系统共用js V0.1
 *
 * author: cuixiaodong 
 */

/**
 * 根据expression表达式，从iframe中获取父窗口中的对象
 * @param 对象的expression，类似于jquery的表达式: $P("#domId")
 */
var $P=function(expression){
	if(expression){
		return $(expression, parent.document);
	}else{
		alert("$P方法的expression参数不能为空!");
	}
};

var $C=function(){
	
};

//加载需要的插件文件

(function($) {		
	$.NKSY={
		/**
		 * div的弹出框
		 * @param title	弹出框的标题
		 * @param url	弹出框的地址
		 * @param width	宽度
		 * @param height	高度
		 */
		popup:function(title,url,width,height){
			if(height==null){
				height='80%';
			}
			if(width==null){
				width='80%';
			}
			$.layer({
				type : 2,
				shade : [0.5 , '#000' , true],
				title : [title,true],
				iframe : {src : url},
				area : [width , height],
				offset : ['50px', '']
			});
//			$.dialog.open(url,
//					{
//						title:title,
//						drag: false,
//						fixed: true,
//						lock:true,
//						opacity: 0.6,	// 透明度
//						width: width,
//					    height: height
//		    		}
//			);
		},
		
		/**
		 * 判断是否为json对象
		 * @param obj: 对象（可以是jq取到对象）
		 * @return isjson: 是否是json对象 true/false
		 */
		isJson:function(obj){
			var isjson = typeof(obj) == "object" && Object.prototype.toString.call(obj).toLowerCase() == "[object object]" && !obj.length;
			return isjson;
		},
		
		/**
		 * 重新打开一个新窗口
		 * @param url 访问地址
		 * @param winTitle: 窗口标题
		 * @param params json类型的参数值 {"type":"0","stationId":"111"}  type:所亭下边的设备类型   stationId：设备id
		 */
		newWin:function(url, winTitle, params){
			
			if(!url){
				alert("NKW.newWin方法的url参数不能为空!");
				return false;
			}
			
			var param = "";
			
			if(this.isJson(params)){
				//为参数添加分隔符
				param += url.indexOf("?") > 0 ?"":"?1=1";
				for(var key in params){
					//拼接请求参数
					param += "&" + key + "=" + encodeURIComponent(params[key]);
				}
			}
			
			//宽度
			var iWidth=screen.width-10;
			//高度
			var iHeight=(window.screen.availHeight-30) * 0.8;
		    //获得窗口的水平位置
		    var iLeft = (window.screen.availWidth-iWidth)/2; 
			//获得窗口的垂直位置
		    var iTop = (window.screen.availHeight-iHeight)/2;        
			var params = "width=" + iWidth + ",height=" + iHeight + ",top=" + iTop + ",left=" + iLeft + ",alwaysRaised=yes,depended=yes,location=no,menubar=no,resizable=no,scrollbars=yes,toolbar=no,z-look=yes";
			var result = window.open(url,
					winTitle,
					params);
		    window.onfocus=function (){result.focus();};
		    window.onclick=function (){result.focus();}; 
		    		    
		    
			//模式窗口
			//window.showModalDialog(path + url,"南凯维调系统变电站设备列表","dialogWidth:800px;dialogHeight:600px;scroll:no;status:no;");
			
		},
		/**
		 * 重新设置窗口的地址，标题
		 * @param url 访问地址
		 * @param title 窗口标题
		 */
		resetWin:function(url, title) {
			location.href = url;
			document.title = title;
		}
	}
})(jQuery);//使用闭包
