
var map;
//用户的访问权限key
var ak = "99NDDcqNNYGgWXgBEWBZuuHi";
//poi位置表数据
var geotable_id = "55312";
//地图中心点
var centerPoint = new BMap.Point(117.43730479652,34.595639105828);
//定义鼠标绘制工具类
var myDrawingManagerObject;
//计数器
var num;
//mark
var markers;
/**
 * 隐藏GIS页面的左侧框，并将地图div全屏显示
 */
function hiddenLeftSideDiv(){
	//左侧菜单栏的隐藏显示
	if($("#panelCtrl").hasClass("open")){
		$("#panelCtrl").removeClass("open");
		$("#leftside").css("margin-left", "");
	}else{
		$("#panelCtrl").addClass("open");
		$("#leftside").css("margin-left", "-345px");
	}
	$("#main_map").toggleClass("max_map")
}

function clearFeatures(){
	//清除地图上所有覆盖物
	map.clearOverlays();	
}
//画圆查找
function drawGeometry1(){
	myDrawingManagerObject.open();
	myDrawingManagerObject.setDrawingMode(BMAP_DRAWING_CIRCLE);
}


//创建地图控件
function initMap() {
	map = new BMap.Map("main_map");                        // 创建Map实例
	map.centerAndZoom(new BMap.Point(121.441104,37.452813), 10);     // 初始化地图,设置中心点坐标和地图级别
	map.addControl(new BMap.NavigationControl());               // 添加平移缩放控件
	map.addControl(new BMap.ScaleControl());                    // 添加比例尺控件
	map.addControl(new BMap.OverviewMapControl());              //添加缩略地图控件
	map.enableScrollWheelZoom();                            //启用滚轮放大缩小
	map.addControl(new BMap.MapTypeControl());          //添加地图类型控件
	
	var styleOptions = {
	        strokeColor:"red",    //边线颜色。
	        fillColor:"red",      //填充颜色。当参数为空时，圆形将没有填充效果。
	        strokeWeight: 3,       //边线的宽度，以像素为单位。
	        strokeOpacity: 0.8,	   //边线透明度，取值范围0 - 1。
	        fillOpacity: 0.6,      //填充的透明度，取值范围0 - 1。
	        strokeStyle: 'solid' //边线的样式，solid或dashed。
	    };

	myDrawingManagerObject = new BMapLib.DrawingManager(map, {isOpen: false, 
	    drawingType: BMAP_DRAWING_MARKER, 
	    enableDrawingTool: false,
	    enableCalculate: false,
	    drawingToolOptions: {
	        anchor: BMAP_ANCHOR_TOP_RIGHT,
	        offset: new BMap.Size(5, 35),
	        drawingModes : [
	            BMAP_DRAWING_CIRCLE
	         ]
	    },
	    circleOptions: styleOptions, //圆的样式

	});
	
	myDrawingManagerObject.addEventListener("circlecomplete", function(circle){
		//将圆的半径转成uint类型的整数，下退转化
		var radius = Math.floor(circle.getRadius());
		nearbySearch('', circle.getCenter(), radius, 0, 10);
		//关闭鼠标绘制工具
		myDrawingManagerObject.close();
	});
	// 百度地图API功能
	map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);

	// 定义一个控件类,即function
	function ZoomControl() {
		// 默认停靠位置和偏移量
		this.defaultAnchor = BMAP_ANCHOR_TOP_LEFT;
		this.defaultOffset = new BMap.Size(65, 15);
	}

	// 通过JavaScript的prototype属性继承于BMap.Control
	ZoomControl.prototype = new BMap.Control();

	// 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
	// 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中
	ZoomControl.prototype.initialize = function(map) {
		// 创建一个DOM元素
		var div = document.getElementById("control");

		// 添加DOM元素到地图中
		map.getContainer().appendChild(div);
		// 将DOM元素返回
		return div;
	}
	// 创建控件
	var myZoomCtrl = new ZoomControl();
	// 添加到地图当中
	map.addControl(myZoomCtrl);
}

/**
 * 显示所有用户数据
 */
function showAllCustomData(){

	//根据daboxId创建自定义图层，用户可用自己创建的geotableid替换30960
	var customLayer=new BMap.CustomLayer({
	    geotableId: geotable_id
	});
	
	map.addTileLayer(customLayer);
	
	customLayer.addEventListener('onhotspotclick',function(e){
		var content = e.content;
		var point = new BMap.Point(content.location[0], content.location[1]);
		getPoleById(content.c_base_id, 0, point);
	});
	
}


/*
function searchById(pointX, pointY, id, docType){
	var point = new BMap.Point(pointX, pointY);
	var marker = new BMap.Marker(point);  // 创建标注
	map.centerAndZoom(point, 15);
	map.addOverlay(marker);              // 将标注添加到地图中
	marker.addEventListener("click", function(){
		getPoleById(id, docType, point);
	});
}

*/

/**
 * 根据id查询地图中的值
 * value:支柱编号或所亭运行编码
 * searchType：类型
 * searchZone：zoom大小
 * title：地图中存储的外键（支柱的杆号或所亭的名称）
 */
function searchById(value,searchType,searchZone, title){
	var params = "?1=1";
	value?params+="&foreign_no="+value:'';
	searchType = searchType==1?0:1;
	searchType?params+="&docType="+searchType:'';
	searchZone?params+="&searchZone="+searchZone:'';
	title?params+="&title="+title:'';
	
	var args = {
			"url":"http://api.map.baidu.com/geodata/v3/poi/list"+params,
			"outputType":"json",
			"ak":ak,
			"geotable_id":geotable_id,
	};
	$.ajax({
		type : "GET",
		url : path + "/jsonp/jsonp.html",
		dataType :'json',
		data : args,
		success : function(result) {
			var pois = result.pois;
			if(pois.length > 0){
				clearFeatures();
				var point = new BMap.Point(pois[0].location[0], pois[0].location[1]);
				map.centerAndZoom(point, 11);
				var marker = new BMap.Marker(point);  // 创建标注
				//为marker添加自定义字段
				marker.c_base_id=pois[0].c_base_id;
				marker.docType=pois[0].docType==0?1:0;
				map.addOverlay(marker);              // 将标注添加到地图中
				marker.addEventListener("click", function(){
					getPoleById(this.c_base_id, this.docType, this.getPosition());
				});
			}
		},
		error : function() {
		}
	});
}

/**
 * 根据base的id和类型查询支柱的详细信息
 * @param baseId
 * @param docType
 */
function getPoleById(baseId, docType, point){
	
	//为查询结果列表添加样式
	$(".rfocus").removeClass('rfocus');
	$("#"+baseId).addClass('rfocus');
	
	$.ajax({
		type : "POST",
		url : path + "/mis/getById.html",
		dataType : 'html',
		data : {"id":baseId,"type":docType},
		success : function(result) {
			var infoWindow = new BMap.InfoWindow(result);  // 创建信息窗口对象
			map.openInfoWindow(infoWindow, point);
			//图片加载完毕重绘infowindow
			/*document.getElementById('poleImg').onload = function (){
				infoWindow.redraw();   //防止在网速较慢，图片未加载时，生成的信息框高度比图片的总高度小，导致图片部分被隐藏
			}*/
		},
		error : function() {
		}
	});
	
}

/**
 * 周边检索
 * @param keyword	检索关键字
 * @param point	中心点
 * @param radius	检索半径
 * @param page_index	检索页数
 * @param page_size		每页条数
 */
function nearbySearch(keyword, point, radius, page_index, page_size){
	var args = {
			"url":"http://api.map.baidu.com/geosearch/v3/nearby",
			"outputType":"json",
			"ak":ak,
			"geotable_id":geotable_id,
			//检索关键字
			"q":keyword,
			//检索的中心点
			"location":point.lng + "," + point.lat,
			//检索半径
			"radius":radius,
			"page_index":page_index,
			"page_size":page_size
	};
	$.ajax({
		type : "GET",
		url : path + "/jsonp/jsonp.html",
		dataType :'json',
		data : args,
		success : function(result) {
			var contents = result.contents;
			for(var i=0; i < contents.length; i++){
				var point = new BMap.Point(contents[i].location[0], contents[i].location[1]);
				var marker = new BMap.Marker(point);  // 创建标注
				//为marker添加自定义字段
				marker.c_base_id=contents[i].c_base_id;
				marker.docType=contents[i].docType==0?1:0;
				map.addOverlay(marker);              // 将标注添加到地图中
				marker.addEventListener("click", function(){
					getPoleById(this.c_base_id, this.docType, this.getPosition());
				});
			}
		},
		error : function() {
		}
	});
}
/**
 * 添加覆盖物
 * @param keyword	检索关键字
 * @param point	中心点
 * @param radius	检索半径
 * @param page_index	检索页数
 * @param page_size		每页条数
 */
// 百度地图API功能
//添加覆盖物2
function add_overlay2(points){
	map.addOverlay(points);
}
//添加覆盖物
function add_overlay(points){
	var point = new BMap.Point(116.404, 39.915);
	//设置一个标注点
	var marker = new BMap.Marker(new BMap.Point(116.404, 39.915));
	//创建一个多边形，points为点集合数组
	var polygon = new BMap.Polygon(points, {strokeColor:"blue", strokeWeight:2, strokeOpacity:0.5});
	function getAttr(){
		polygon.hide();
	}
	polygon.addEventListener("click", getAttr);
	//给覆盖物设置标签
	var label = new BMap.Label("我是文字标注哦",{offset:new BMap.Size(20,-10)});
	marker.setLabel(label);
	map.centerAndZoom(point, 15);
	map.addOverlay(marker);            //增加点
	map.addOverlay(polygon);           //增加多边形
	var aa = [[],[],[],[]];
	aa[0][0] = "绿化1";
	aa[0][1] = "绿化2";
	aa[1][0] = "环卫1";
	aa[1][1] = "环卫2";
	aa[2][0] = "学生宿舍1";
	aa[2][1] = "学生宿舍2";
	aa[3][0] = "其他1";
	aa[3][1] = "其他2";
	alert(aa);
}
//清除覆盖物
function remove_overlay(){
	map.clearOverlays();         
}
//是否属于范围
function belong_or_not(point,points){
	var polygon = new BMap.Polygon(points, {strokeColor:"blue", strokeWeight:2, strokeOpacity:0.5});
	var result = BMapLib.GeoUtils.isPointInPolygon(point, polygon);
	return result;
}
//设置点
function setPointOnly(point){
	var myIcon = new BMap.Icon(path+"/resources/images/mapLocal.bmp", new BMap.Size(30,-30));
	  markers = new BMap.Marker(point,{icon:myIcon});
	map.addOverlay(markers);
		var p = markers.getPosition();
		var local=p.lng+','+p.lat;
		localStorage.positiontemp=local;
		var sContent =
			"<h4 style='margin:0 0 5px 0;padding:0.2em 0'>注释内容</h4>" + 
			"<input type='text' value='' maxlength='50' name='pointMarker' id='pointMarker' class='' >"+
			"<input type='button' onclick='savePointMarker();' value='保存' />" + 
			"</div>";
			var infoWindow = new BMap.InfoWindow(sContent);  // 创建信息窗口对象
			markers.openInfoWindow(infoWindow);
}
//保存点storage
function savePointMarker(){
	var key=null;
	for(var i=0;i<num+1;i++){
		localStorage.getItem(i);
		var flag=belong_or_not(new BMap.Point(localStorage.positiontemp),localStorage.getItem(i));
		if(flag){
			key="f_"+i+"_label";
		}
	}
	if(typeof(Storage)!=="undefined"){
		localStorage.setItem(key,$("#pointMarker").val());//设置b为"isaac"
   	}else{
	   alert("请升级浏览器!");
	}
	markers.closeInfoWindow();
	
	var label = new BMap.Label($("#pointMarker").val(),{offset:new BMap.Size(20,-10),backgroundColor:null});
	markers.setLabel(label);
	localStorage.removeItem("positiontemp");
	key.clear;
}
//接受数据集
function getAllDataDetails(){
	
	var allDataDetails = [];
	for(var i=0;i<num+1;i++){
		allDataDetails.push(localStorage.getItem("f_"+i));
		allDataDetails.push(localStorage.getItem("f_"+i+"_label"));
	}
	alert(allDataDetails);
	return $.toJSON(allDataDetails);
};
function saveAll(){
	var allDataDetails=getAllDataDetails();
	alert(allDataDetails);
	var args = {
			"allDataDetails":allDataDetails
	};
	alert("ddda");
	$.ajax({
		type : "GET",
		url : path + "/gis/savePoint.html",
		dataType :'json',
		data : args,
		success : function(result) {
		},
		error : function() {
		}
	});
}

/*window.run = function (){
	//你获取到的一系列点的数组(通过gps或其他接口)
    var pts ={};
    var paths = pts.length;    //获得有几个点
    var carMk = new BMap.Marker(pts[0],{icon:myIcon});
    map.addOverlay(carMk);
    i=0;
    function resetMkPoint(i){
           carMk.setPosition(pts[i]);//重新设置marker的position
           if(i < paths){
                  setTimeout(function(){
                         i++;
                         resetMkPoint(i);
                  },100);
           }
    }
    setTimeout(function(){
           resetMkPoint(5);
    },100)
}
setTimeout(function(){

run();

},1500);*/