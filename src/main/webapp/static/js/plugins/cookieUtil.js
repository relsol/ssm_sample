/**
 * Cookie构成：

	name=value;[expires=date;][path=pathvalue;][domain=domainvalue;][secure;]
	说明：在后面的;[expires=date;]等可选项中，有的写法是带一个空格，如"; expires="+expires，似乎在这些可选项前加不加空格，并不会影响。
	举例：id="Jack";expires=Wensday, 28-Jul-08 17:00:00 GMT;domain="dns.com";path="/";secure;
	注意：在组成cookie的字符串中，不能含有分号，逗号及空白符。
	
	解析：
	1、name=value：如上id="jack"
	2、expires=Weekday, DD-MM-YY HH:MM:SS GMT :此属性可选WEEKDAY是指定周几,比如Monday ,Tuesday也可以用这些英文的缩写,如Mon,Tues等。DD-MM-YY是日、月、年。HH:MM:SS是时分秒。GMT是表示格林威治标准时。这里总是用GMT。
	3、path可选。指定可用于特定服务器中的什么位置。用于同一个域中多个页面共享cookie。
	4、DOMAIN指定所作用的域名，采用域名后，可以在同一个域的多个服务器之间共享cookie，而无需为每一个服务器都指定COOKIE。域名值可以全写，也可以去掉http://简写，如：
	domain=.google.com
	domain=http://www.google.com
	5、SECURE指定通过安全通道传递COOKIE，如果设置此属性，则必需使用HTTPS协议传递。


 */


/**
 * 获得指定的cookie
 * @param c_name
 * @return  返回cookie的值，如果没有返回空字符“”
 */
function getCookie(c_name)
{
if (document.cookie.length>0)
  {
  c_start=document.cookie.indexOf(c_name + "=")
  if (c_start!=-1)
    { 
    c_start=c_start + c_name.length+1 
    c_end=document.cookie.indexOf(";",c_start)
    if (c_end==-1) c_end=document.cookie.length
    return unescape(document.cookie.substring(c_start,c_end))
    } 
  }
return "";
}


/**
 * 创建一个cookie
 * @param c_name   cookie的名称
 * @param value    cookie的value（可选）
 * @param expiredays    cookie的有效期,按天计数（可选）
 * @param path     指定可用于特定服务器中的什么位置（可选）
 * @param domain   指定所作用的域名（可选）
 * @param secure   指定通过安全通道传递COOKIE（可选）
 * @return
 */
function setCookie(c_name,value,expiredays,path,domain,secure)
{
var exdate=new Date();
exdate.setDate(exdate.getDate()+expiredays);
document.cookie=c_name+ "=" +escape(value)+
((expiredays==null) ? "" : ";expires="+exdate.toGMTString()) + ((path) ? ('; path='+path) : '') +((domain) ? ('; domain='+domain) : '') + ((secure) ? '; secure' : '');

}

/**
 * 删除指定的cookie
 * @注意：创建cookie时，如果有指定某个限定名的路径或域名，那么在调用/删除时，也要指定删除该路径/域名下的此COOKIE
 * @param name     cookie的名称
 * @param path     指定可用于特定服务器中的什么位置（可选）
 * @param domain   指定所作用的域名（可选）
 * @return
 */
function delCookie(name,path,domain)//删除cookie 
{ 
    var exp = new Date();
    exp.setTime(exp.getTime() - 1); 
    var cval=getCookie(name); 
    if(cval!=null) document.cookie= name + "="+cval+ ((path) ? (";path="+path) : '') + ((domain) ? (";domain="+domain) : '') + ";expires="+exp.toGMTString(); 
} 

