/**
 * 如果location.href中存在showMenu参数并且为false字符串，则隐藏。
 * 其他情况显示
 * 如：6C系统
 * http://localhost:8080/nksy/6C/index.html?idPole=101802&showMenu=false
 * 进入6C主页面不会显示右侧菜单栏。如果showMenu参数不存在或者为其他值，右侧菜单栏就会显示
 */


var rightMenu_isShowMenu = true;

function rightMenu_getSearch(){

	var args="";
	if(location.search.replace("?","").length > 0){
		var params = location.search.replace("?","").split("&");
		var temp;
		for(var i=0; i<params.length; i++){
			temp = params[i];
			temp = temp.split("=");
			args=args+temp[0] + ":'" + temp[1] + "'";
			if((i+1) < params.length){
				args=args + ",";
			}
		}
	}
	
	if(args != ""){
		args="{" + args + "}";
		args = eval('('+args+')');
	}

	return args;
}



function openModalWin(strURL){
	var sheight = screen.height-70;  
	var swidth = screen.width-10;  
	var winoption ="dialogHeight:"+sheight+"px;dialogWidth:"+ swidth +"px;dialogLeft:0px;dialogTop:0px;status:no;scroll:yes;resizable:no;center:yes";  
	
	var tmp=window.showModalDialog(strURL,window,winoption); 
	
}

function winOpenFullScreen(url)  
{  
	
	if(!url){
		return false;
	}
	
	//宽度
	var iWidth=screen.width-10;
	//高度
	var iHeight=screen.height-70;
    //获得窗口的水平位置
    var iLeft = 0; 
	//获得窗口的垂直位置
    var iTop = 0;        
	var params = "width=" + iWidth + ",height=" + iHeight + ",top=" + iTop + ",left=" + iLeft + ",alwaysRaised=yes,depended=yes,location=no,menubar=no,resizable=no,scrollbars=yes,toolbar=no,z-look=yes";
	var result = window.open(url,
			"",
			params);
    window.onfocus=function (){result.focus();};
    window.onclick=function (){result.focus();}; 
    
}

function locationOpen(strURL)  
{
	location.href = strURL;
}  


/**************ajax************/

var XmlHttpAjax = function() {
		
		this.url = "";
		this.result = "";
		this.callbackMethod = null;
		//"user="+username+"&pwd="+password
		this.sendData = null;
		
		this.xmlHttpRequest = function()
		{
		    if(window.ActiveXObject)
		    {
		      return new ActiveXObject("Microsoft.XMLHTTP");
		    }
		    else
		    {
		      return new XMLHttpRequest();
		    }    
		}();
		
}

XmlHttpAjax.prototype.getData = function(){
	  
	var xmlHttpRequest = this.xmlHttpRequest;
	
	var callback;
	if(this.callbackMethod != null){
		callback = this.callbackMethod;
	}
	  
	  xmlHttpRequest.onreadystatechange=function(){
		  if(xmlHttpRequest.readyState==4&&xmlHttpRequest.status==200){
			  var result = xmlHttpRequest.responseText;
			  callback(result);
		  }
	  };
	  
	  xmlHttpRequest.open("GET",this.url,true);
	  
	  xmlHttpRequest.send(this.sendData);
	}
/*********************************************************************/


function addCSS() {
    var link = document.createElement('link');
    link.type = 'text/css';
    link.rel = 'stylesheet';
    link.href = '/nksy/resources/js/rightMenu/rightMenu.css';
    document.getElementsByTagName("head")[0].appendChild(link);
    
    var link_awesome = document.createElement('link');
    link_awesome.type = 'text/css';
    link_awesome.rel = 'stylesheet';
    link_awesome.href = '/nksy/resources/font-awesome-4.2.0/css/font-awesome.css';
    document.getElementsByTagName("head")[0].appendChild(link_awesome);
}

function addRightMenuDiv() {
    var div = document.createElement('div');
    div.id = "rightMenu";
    document.getElementsByTagName("body")[0].appendChild(div);
}

function addRightMenu(){
	var tt = new XmlHttpAjax();
	tt.url = "/nksy/resources/js/rightMenu/rightMenu.jsp";
	tt.callbackMethod = function(data){
		document.getElementById("rightMenu").innerHTML = data;
	}
	tt.getData();
}

function initRightMenu(){
	
	var params = rightMenu_getSearch();
	//默认显示右侧菜单
	var isShowMenu = rightMenu_isShowMenu;
	//如果有showMenu并且参数不为空
	if(params && params.showMenu != ""){
		isShowMenu = params.showMenu;
	}
	if(isShowMenu != "false"){
		addCSS();
		addRightMenuDiv();
		addRightMenu();
	}
}

initRightMenu();
