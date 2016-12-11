
var map;
//用户的访问权限key
var ak = "99NDDcqNNYGgWXgBEWBZuuHi";
//poi位置表数据
var geotable_id = "55312";
//地图中心点
var centerPoint = new BMap.Point(117.43730479652,34.595639105828);
//定义鼠标绘制工具类
var myDrawingManagerObject;

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
	map.centerAndZoom(centerPoint, 11);     // 初始化地图,设置中心点坐标和地图级别
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