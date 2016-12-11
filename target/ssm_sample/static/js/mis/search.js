// JavaScript Document
//搜索下拉框开始
var xl =function(id){ 
	return document.getElementById(id); 
} 
var flag=false; 
function shlist(){ 
	xl("selectList").style.display=xl("selectList").style.display=="block"?"none":"block"; 
} 
function changesever(ts){
	document.getElementById("searchType").value=$(ts).attr("type");
	document.getElementById("text_selected").innerHTML=""+ts.innerHTML+""; 
	shlist(); 
} 
function setFlag(val){ 
	flag=val; 
} 
function hideList(){
	if(!flag)document.getElementById("selectList").style.display="none"; 
} 
setCss=function(p){
	p.style.cursor='hand'; 
	p.style.backgroundColor='#BABABA'; 
	tag_zn=1;
} 
removeCss=function(p){ 
	p.style.backgroundColor='white'; 
	tag_zn=0;
} 

/**
 * lucene查询  查询类型为接触网或牵引类型的数据
 * @param formId	form的id
 * @param resultId
 */
function searchByLucene(formId, resultId){
	$.ajax({
		type : "POST",
		url : path + "/gis/searchByLucene.html",
		cache : false,
		data : $("#"+formId).serialize(),
		success : function(result) {
			$("#"+resultId).html(result);
			if($("#"+resultId + " .rstOne").length > 0){
				$("#"+resultId + " .rstOne:eq(0)").click();
			}
		},
		error : function() {
			alert("Request timeout, please try again!");
		}
	});
}


function searchBySQL(formId, resultId){
	$.ajax({
		type : "POST",
		url : path + "/mis/searchBySQL.html",
		cache : false,
		data : $("#"+formId).serialize(),
		success : function(result) {
			$("#"+resultId).html(result);
			if($("#"+resultId + " .rstOne").length > 0){
				$("#"+resultId + " .rstOne:eq(0)").click();
			}
		},
		error : function() {
			alert("Request timeout, please try again!");
		}
	});
}


function searchById_forContent(resultId, type , id){
	$.ajax({
		type : "POST",
		url : path + "/mis/searchById.html",
		cache : false,
		data : {"type":type, "id":id},
		success : function(result) {
			$("#"+resultId).html(result);
			/*if($("#"+resultId + " .rstOne").length > 0){
				$("#"+resultId + " .rstOne:eq(0)").click();
			}*/
		},
		error : function() {
			alert("Request timeout, please try again!");
		}
	});
}
