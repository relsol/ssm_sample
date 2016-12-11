/**
 * 使用方法
 * 
 * var tt = new XmlHttpAjax();
		tt.url = "Article.jsp?URL=" + document.getElementById("URL").value + "&method=checkURL";
		tt.callbackMethod = function(data){
			data = eval("("+data+")");
			if(data.status){
				if(window.confirm("网站已经被投诉，是否转向详细页？")){
					window.location=data.url;
				}
			}else{
				alert("网址没有被投诉!");
			}
		}
		tt.getData();
 * 
 * @returns
 */


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