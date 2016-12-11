/**
 * lucene查询  查询类型为接触网或牵引类型的数据
 * @param formId	form的id
 * @param resultId
 */
function queryByIdAndType(type,Id,marker){
	$.ajax({
		type : "POST",
		url : path + "/gis/pole.html",
		data : {"type":type,"id":Id},
		success : function(result) {
			//清除地图上所有覆盖物
			openInfoWin(result,marker);
		},
		error : function() {
			alert("链接超时，请重试!");
		}
	});
}
//以下为地图
var dataAdded=false;
var map, layer,markerlayer ,marker,type,ID,vectorLayerTrace, features, cars, animatorVector,
//定义公交线路的样式。
styleLine = {
    strokeColor: "black",
    strokeWidth: 1,
    fill: false
},
styleCar1=
{
    externalGraphic:"images/mob.png",
    graphicWidth:22,
    graphicHeight:22
},
styleCar2=
{
    externalGraphic:"images/mob.png",
    graphicWidth:22,
    graphicHeight:22
},
host = document.location.toString().match(/file:\/\//) ? "http://localhost:8090" : 'http://' + document.location.host;
//地图服务地址
url = iserverURI + "/services/tianditus/rest/maps/tianditus_jinzhou";
//数据服务地址
url2 = iserverURI + "/services/data-jzXY/rest/data";
//初始化地图的位置
var initialPoint=new SuperMap.LonLat(121,41);
//放大级别
var levelLook=6;
/*
 * type:查找类型
 * id：数据id
 * axisX：设备gis的X坐标
 * axisY：设备gis的Y坐标
 */
function gotoMap(type, id, axisX, axisY) {
	clearStatus();
	var queryParamCnnt,queryParamPower,getFeatureBySQLParams,getFeatureBySQLService;
	/*var point_choose=axisX + "," + axisY;
	map.setCenter(new SuperMap.LonLat(point_choose),3);*/
	if(type=="CNNT"){
		queryParamCnnt = new SuperMap.REST.FilterParameter({
			name: "jzcnXY_1@jzcnXY",
			attributeFilter: "col0 = '" + id + "'"});
		getFeatureBySQLParams = new SuperMap.REST.GetFeaturesBySQLParameters({
				queryParameter: queryParamCnnt,
				datasetNames:["jzcnXY:jzcnXY_1"]
			});
			getFeatureBySQLService = new SuperMap.REST.GetFeaturesBySQLService(url2, {
			eventListeners: {"processCompleted": processCompletedCnnt, "processFailed": processFailed}});
			getFeatureBySQLService.processAsync(getFeatureBySQLParams);
	}else if(type=="POWER"){
		queryParamPower = new SuperMap.REST.FilterParameter({
			name: "jzPW_1@jzcnXY",
			attributeFilter: "col0 = '" + id + "'"});
		getFeatureBySQLParams = new SuperMap.REST.GetFeaturesBySQLParameters({
			queryParameter: queryParamPower,
			datasetNames:["jzcnXY:jzPW_1"]
		});
		getFeatureBySQLService = new SuperMap.REST.GetFeaturesBySQLService(url2, {
		eventListeners: {"processCompleted": processCompletedPower, "processFailed": processFailed}});
		getFeatureBySQLService.processAsync(getFeatureBySQLParams);
	}
	closeInfoWin();
}
//初始化地图
function init(){
	vectorLayer = new SuperMap.Layer.Vector("Vector Layer");
	vectorLayer1 = new SuperMap.Layer.Vector("Vector Layer1");
	markerLayer2 = new SuperMap.Layer.Markers("Markers");
	//几何圆查询
	drawPolygon1 = new SuperMap.Control.DrawFeature(vectorLayer, SuperMap.Handler.RegularPolygon,{handlerOptions:{sides:50}});
	drawPolygon1.events.on({"featureadded": drawCompleted});
//初始化地图
	 var broz = SuperMap.Util.getBrowser();

     if(!document.createElement('canvas').getContext) {
    	 alert('您的浏览器不支持 canvas，请升级');
         return;
     } else if (broz.device === 'android') {
    	 alert('您的设备不支持高性能渲染，请使用pc或其他设备');
         return;
     }
	map = new SuperMap.Map("map",{controls:[
/*	new SuperMap.Control.LayerSwitcher(),*/
	new SuperMap.Control.ScaleLine(),
	new SuperMap.Control.Zoom(),
	new SuperMap.Control.Navigation({
        dragPanOptions: {
            enableKinetic: true
        }
    }),
	drawPolygon1]});
	map.addControl(new SuperMap.Control.MousePosition());
//初始化图层
	layer = new SuperMap.Layer.TiledDynamicRESTLayer("World", url, null,{maxResolution:"auto"});
//监听图层信息加载完成事件
	layer.events.on({"layerInitialized":addLayer});
//定义覆盖物集合加载数据
	vector = new SuperMap.Layer.Vector("vector");
	markerlayer = new SuperMap.Layer.Markers("markerLayer");
	 //初始化公交车路线图层。
    vectorLayerTrace = new SuperMap.Layer.Vector("Vector Layer", {
        styleMap: new SuperMap.StyleMap({
            "default": styleLine})});
    //初始化汽车图层。
    animatorVector = new SuperMap.Layer.AnimatorVector("Cars", {},{
        //设置速度为每帧播放0.05小时的数据
        speed:1,
        //开始时间为0晨
        startTime:0,
        //结束时间设置为最后运行结束的汽车结束时间
        endTime:200
    });
	
}
//异步加载图层
function addLayer(){
	map.addLayers([vector,layer,markerlayer,vectorLayer,vectorLayer1,markerLayer2,animatorVector,vectorLayerTrace]);
	//显示地图范围
	map.setCenter(initialPoint,levelLook);
}
function clearStatus(){
	stopMonitor();
	vectorLayer.removeAllFeatures();
	vectorLayer1.removeAllFeatures();
	markerLayer2.clearMarkers();
	vectorLayerTrace.removeAllFeatures();
	vector.removeAllFeatures();
	animatorVector.removeAllFeatures();
}
//画圆
function drawRound() {
//先清除上次的显示结果
clearStatus();
drawPolygon1.activate();
}
function drawCompleted(drawGeometryArgs) {
	var geometry = drawGeometryArgs.feature.geometry;
	vectorLayer.removeAllFeatures();
	var getFeaturesByGeometryParameters, getFeaturesByGeometryParametersPower,getFeaturesByGeometryService,getFeaturesByGeometryServicePower;
	getFeaturesByGeometryParameters = new SuperMap.REST.GetFeaturesByGeometryParameters({
		datasetNames: ["jzcnXY:jzcnXY_1"],
		toIndex:-1,
		spatialQueryMode:SuperMap.REST.SpatialQueryMode.INTERSECT,
		geometry: drawGeometryArgs.feature.geometry
	});
	getFeaturesByGeometryParametersPower = new SuperMap.REST.GetFeaturesByGeometryParameters({
		datasetNames: ["jzcnXY:jzPW_1"],
		toIndex:-1,
		spatialQueryMode:SuperMap.REST.SpatialQueryMode.INTERSECT,
		geometry: drawGeometryArgs.feature.geometry
	});
	getFeaturesByGeometryService = new SuperMap.REST.GetFeaturesByGeometryService(url2, {
		eventListeners: {
			"processCompleted": processCompletedCnntArea,
			"processFailed": processFailed
		}
	});
	getFeaturesByGeometryServicePower = new SuperMap.REST.GetFeaturesByGeometryService(url2, {
		eventListeners: {
			"processCompleted": processCompletedPowerArea,
			"processFailed": processFailed
		}
	});
	getFeaturesByGeometryServicePower.processAsync(getFeaturesByGeometryParametersPower);
	getFeaturesByGeometryService.processAsync(getFeaturesByGeometryParameters);
}
//接触网区域查询回调函数
function processCompletedCnntArea(queryEventArgs) {
	drawPolygon1.deactivate();
	var i, j, result = queryEventArgs.result;
	if (result && result.features) {
				for (j=0; j<result.features.length; j++) {
					var feature = result.features[j];
					var point = feature.geometry;
					if(point.CLASS_NAME == SuperMap.Geometry.Point.prototype.CLASS_NAME){
						var size = new SuperMap.Size(33, 25),
						offset = new SuperMap.Pixel(-(size.w/2), -size.h),
						icon = new SuperMap.Icon("../resources/js/gis/theme/images/marker.png", size, offset);
						  var marker2 =new SuperMap.Marker(new SuperMap.LonLat(point.x, point.y), icon);
						  marker2.dataId = feature.attributes.COL0;
						 marker2.events.on({
							"click":function(){
								queryByIdAndType("CNNT",this.dataId,this);
							},
							"scope": marker2
							});
						 markerLayer2.addMarker(marker2);
					}else{
						feature.style = style;
						vectorLayer1.addFeatures(feature);
					}
				}
	}
}
//电力区域查询回调函数
function processCompletedPowerArea(queryEventArgs) {
	drawPolygon1.deactivate();
	var i, j, result = queryEventArgs.result;
	if (result && result.features) {
				for (j=0; j<result.features.length; j++) {
					var feature = result.features[j];
					var point = feature.geometry;
					if(point.CLASS_NAME == SuperMap.Geometry.Point.prototype.CLASS_NAME){
						var size = new SuperMap.Size(33, 25),
						offset = new SuperMap.Pixel(-(size.w/2), -size.h),
						icon = new SuperMap.Icon("../resources/js/gis/theme/images/marker-gold.png", size, offset);
						  var marker2 =new SuperMap.Marker(new SuperMap.LonLat(point.x, point.y), icon);
						  marker2.dataId = feature.attributes.COL0;
						 marker2.events.on({
							"click":function(){
								queryByIdAndType("POWER",this.dataId,this);
							},
							"scope": marker2
							});
						 markerLayer2.addMarker(marker2);
					}else{
						feature.style = style;
						vectorLayer1.addFeatures(feature);
					}
				}
	}
}
//接触网查询回调函数
function processCompletedCnnt(queryEventArgs) {
	drawPolygon1.deactivate();
	var i, j, result = queryEventArgs.result;
	if (result && result.features) {
				for (j=0; j<result.features.length; j++) {
					var feature = result.features[j];
					var point = feature.geometry;
					if(point.CLASS_NAME == SuperMap.Geometry.Point.prototype.CLASS_NAME){
						var size = new SuperMap.Size(33, 25),
						offset = new SuperMap.Pixel(-(size.w/2), -size.h),
						icon = new SuperMap.Icon("../resources/js/gis/theme/images/marker.png", size, offset);
						  var marker2 =new SuperMap.Marker(new SuperMap.LonLat(point.x, point.y), icon);
						  map.setCenter(marker2,11);
						  marker2.dataId = feature.attributes.COL0;
						 marker2.events.on({
							"click":function(){
								queryByIdAndType("CNNT",this.dataId,this);
							},
							"scope": marker2
							});
						 markerLayer2.addMarker(marker2);
						 map.panTo(marker2.getLonLat());
					}else{
						feature.style = style;
						vectorLayer1.addFeatures(feature);
					}
				}
	}
}
//电力查询回调函数
function processCompletedPower(queryEventArgs) {
	drawPolygon1.deactivate();
	var i, j, result = queryEventArgs.result;
	if (result && result.features) {
				for (j=0; j<result.features.length; j++) {
					var feature = result.features[j];
					var point = feature.geometry;
					if(point.CLASS_NAME == SuperMap.Geometry.Point.prototype.CLASS_NAME){
						var size = new SuperMap.Size(33, 25),
						offset = new SuperMap.Pixel(-(size.w/2), -size.h),
						icon = new SuperMap.Icon("../resources/js/gis/theme/images/marker-gold.png", size, offset);
						  var marker2 =new SuperMap.Marker(new SuperMap.LonLat(point.x, point.y), icon);
						  map.setCenter(marker2,11);
						  marker2.dataId = feature.attributes.COL0;
						 marker2.events.on({
							"click":function(){
								queryByIdAndType("POWER",this.dataId,this);
							},
							"scope": marker2
							});
						 map.panTo(marker2.getLonLat());
						 markerLayer2.addMarker(marker2);
					}else{
						feature.style = style;
						vectorLayer1.addFeatures(feature);
					}
				}
	}
}
function processFailed(e) {
	alert(e.error.errorMsg);
}
//往图层添加描点
function addDataPoint(type_,id_,choosePoint){ 
	markerlayer.removeMarker(marker);
	var size = new SuperMap.Size(33,25);
	var offset = new SuperMap.Pixel(-(size.w/2), -(size.h/4));
	var icon = new SuperMap.Icon('../resources/js/gis/theme/images/marker.png', size, offset);
	marker =new SuperMap.Marker(new SuperMap.LonLat(choosePoint),icon) ;
	marker.events.on({
	"click":function(){
		queryByIdAndType(type_,id_,marker);
	},
	"scope": marker
	});
	markerlayer.addMarker(marker);
	map.setCenter(new SuperMap.LonLat(choosePoint),3);
	}
//开始播放动画
function startAnimator(){
    animatorVector.animator.start();
}
//暂停播放动画
function pauseAnimator(){
    animatorVector.animator.pause();
}
//停止播放动画
function stopAnimator(){
    animatorVector.animator.stop();
}
var myLines = [], myGeometrys = [], car_datas = [];

function initData(resultData){
	clearStatus();
	if (resultData) {
		for (var i=0; i < resultData.length; i++) {
			//清空上次的数据
			myGeometrys.length = 0;
			var data = resultData[i];
			var points_data = data.geometry.points;
			var style = i%2==0? styleCar1:styleCar2;
			
			for(var j = 0; j < points_data.length; j++) {
				//收集路线上的点
				var geometry = new SuperMap.Geometry.Point(points_data[j].y, points_data[j].x);
				myGeometrys.push(geometry);
				
				var car_data = new SuperMap.Feature.Vector(geometry,
							{
								FEATUREID:data.id,
								//根据节点生成时间
								TIME:j
							},style
					);
				car_datas.push(car_data);
			}
			
			//画线
			var roadLine = new SuperMap.Geometry.LineString(myGeometrys);
			var pointFeature = new SuperMap.Feature.Vector(roadLine,null,null);
			//线的集合
			myLines.push(pointFeature);
			
		}
	}
    vectorLayerTrace.addFeatures(myLines);
    animatorVector.addFeatures(car_datas);
}
//打开对应的信息框
var infowin = null;
function openInfoWin(html,marker_){
	closeInfoWin();
	var lonlat = marker_.getLonLat();
	var contentHTML=html;
	var size = new SuperMap.Size(0, 33);
	var offset = new SuperMap.Pixel(0, -size.h);
	var icon = new SuperMap.Icon("../resources/js/gis/theme/images/marker.png", size, offset);
	var popup = new SuperMap.Popup.FramedCloud("popwin",
		new SuperMap.LonLat(lonlat.lon,lonlat.lat),
		null,
		contentHTML,
		icon,
		true
		);
	infowin = popup;
	map.addPopup(popup);
}
//关闭信息框
function closeInfoWin(){
	if(infowin){
		try{
			infowin.hide();
			infowin.destroy();
		}
		catch(e){}
	}
}
//实时监控
function addData(data) {
	vector.removeAllFeatures();
	//点
	var point= new SuperMap.Geometry.Point(data.axisY, data.axisX);
    var pointlayer = new SuperMap.Feature.Vector(point);
    pointlayer.style={
         fillColor:"red",
        strokeColor:"yellow",
        pointRadius:6
    };
	vector.addFeatures([ pointlayer ]);
	map.setCenter(new SuperMap.LonLat(data.axisY, data.axisX), map.getNumZoomLevels);
}
//停止监控
function stopMonitor() {
	isMonitor = false;
}
//开始监控
function monitor() {
	var imeiCode = $("#imeiCode").val();
	if (imeiCode == null || imeiCode == "") {
		alert("监控imeicode码不能为空！");
		return false;
	}
	$
			.ajax({
				url : path+'/mobileDeviceMove/monitorByImeiCode.html',
				data : {
					"imeiCode" : imeiCode
				},
				dataType : 'json',
				success : function(json) {
					if (json.success) {
						isMonitor = true;
					} else {
						stopMonitor();
					}
				}
			});
}
