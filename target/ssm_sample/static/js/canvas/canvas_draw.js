/**
 * »æÖÆÍ¼Æ¬start
 */
var iscanvas=false;
var context;
var canvas;
function brush(canvasId){
	$('#'+canvasId).css({"z-index": 100});
	canvas = document.getElementById(canvasId);
	context = canvas.getContext('2d');
	context.lineWidth = 3;//³õÊ¼»­±Ê´óÐ¡
	context.strokeStyle = '#ff0000';//³õÊ¼»­±ÊÑÕÉ«
	context.fillStyle = "transparent";//³õÊ¼»­²¼±³¾°ÑÕÉ«
	context.lineCap = "round";//È¥³ý¾â³Ý
	context.fill();
	addOPerateListener(canvasId);
	iscanvas=true;
	//var img = $('#highGuide-img')[0];
	//console.log(img);
	//context.drawImage(img, 0, 0, img.width, img.height); */
}
function addOPerateListener(canvasId) {
	var startX = -1;
	var startY = -1;
	var isMouseDown = false;
	var isMouseMove = false;
	var isMouseUp = false;
	
	$('#'+canvasId).mousedown(function(e) {
		buttonPress = 1;
		flag = 1;
		isMouseDown = true;
		isMouseUp = false;
		isMouseMove = false;
		startX = e.offsetX;
		startY = e.offsetY;
		context.beginPath();
		console.log(111111);
		context.strokeStyle = '#ff0000';
		context.moveTo(startX, startY);
		
	});
	$('#'+canvasId).mousemove(function(e) {
		if (isMouseUp || !isMouseDown) {
			return;
		}
		var endX = e.offsetX, endY = e.offsetY;
		context.lineTo(endX, endY);
		context.stroke();
		startX = endX;
		startY = endY;
		isMouseMove = true;
	});
	$('#'+canvasId).mouseup(function() {
		buttonPress = 0;

		if (!isMouseDown)
			return;
		if (!isMouseMove) {
			context.arc(startX, startY, context.lineWidth, 0, Math.PI * 2, false);
			context.fillStyle = context.strokeStyle;
			context.fill();
		}

		context.closePath();
		isMouseDown = false;
		isMouseMove = false;
		isMouseUp = true;
		startX = -1;
		startY = -1;
	});
}
function getCanvasData(imageId) {
	var picContainer = document.getElementById(imageId);
	var img = picContainer;
	if (img) {
		var x, y;
		if (img.style.position == "absolute") {

			x = parseInt(img.style.left);
			y = parseInt(img.style.top);
		} else {

			x = (picContainer.offsetWidth - img.width) / 2;
			y = (picContainer.offsetHeight - img.height) / 2;
		}
		context.globalCompositeOperation = "destination-over";
		context.drawImage(img, x, y, img.width, img.height);
	} else {
		context.globalCompositeOperation = "destination-atop";
		context.fillStyle = "#fff";//ÖØÖÃ»­²¼±³¾°°×É«
		context.fillRect(0, 0, canvas.width, canvas.height);
	}
	try {
		return canvas.toDataURL("image/png").substring(22);
	} catch (e) {
		return "";
	}
	
}

function cancel() {
	var ctx=canvas.getContext("2d");
	ctx.clearRect(0,0,canvas.width,canvas.height);
	
	iscanvas = false;
}

function chartToImage(svgHtml,toCanvasId,toImageId){
	$('#'+toCanvasId).show();
	canvg(toCanvasId,svgHtml.trim());
	canvas = document.getElementById(toCanvasId);
	var imagedate=canvas.toDataURL("image/png");
	$('#'+toCanvasId).hide();
	$('#'+toImageId).attr("src",imagedate);
	$("#"+toImageId).css('display','block'); 
	return false;
}