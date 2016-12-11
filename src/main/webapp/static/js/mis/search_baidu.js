/**
 * lucene查询  查询类型为接触网或牵引类型的数据
 * @param formId	form的id
 * @param resultId
 */
function searchByLucene(formId, resultId){
	$.ajax({
		type : "POST",
		url : path+"/mis/searchByLucene.html",
		cache : false,
		data : $("#"+formId).serialize(),
		success : function(result) {
			//清除地图上所有覆盖物
			map.clearOverlays();
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