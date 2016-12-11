
/**
 * 屏蔽分页中start对象记录到webstroe中
 * @param flag
 */
function stateSave_pageStart(){
		for(var i=localStorage.length - 1 ; i >=0; i--){
			if(localStorage.key(i).indexOf(location.pathname, 0) > 1){
				//读取webStore值转json对象
				var data = JSON.parse(localStorage.getItem(localStorage.key(i)));
				if(data.start){
					data.start = 0;
				}
				//json对象转字符串，重新装入webstore
				localStorage[localStorage.key(i)] = JSON.stringify(data);
			}
		}
};

$(function($){
	stateSave_pageStart();
	
	$.fn.dataTable.defaults = $.extend($.fn.dataTable.defaults,{
		serverSide: true,
		lengthChange: false,
		searching: false,
		ordering: false,
		pageLength: 20,
		dom: 'Bfrtip',
		buttons: {
			buttons: [
//			            {
//			                extend: 'colvis',
//			                collectionLayout: 'fixed two-column',
//			                text:'显示隐藏列'
//			            },
//			            {
//			            	extend: 'print',
//			            	message:'打印',
//			            	text:'打印'
//			            }
			  ]
		},
		"ajax" : {
			"dataSrc":function(json){
				if(json.success){
					parent.window.location.href = getRootPath()+"/login.html";
				}
				return json.data;
			}
	    },
        stateSave: true,
		pagingType: 'full',
		columnDefs: [{
	      "targets": '_all',
	      "class" : "text-center"
	    }],
		language: {
			"sProcessing":   "处理中...",
			"sLengthMenu":   "显示 _MENU_ 项结果",
			"sZeroRecords":  "没有匹配结果",
			"sInfo":         "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
			"sInfoEmpty":    "显示第 0 至 0 项结果，共 0 项",
			"sInfoFiltered": "",
			"sInfoPostFix":  "",
			"sSearch":       "搜索:",
			"sUrl":          "",
			"sEmptyTable":     "表中数据为空",
			"sLoadingRecords": "载入中...",
			"sInfoThousands":  ",",
			"oPaginate": {
				"sFirst":    "首页",
				"sPrevious": "上页",
				"sNext":     "下页",
				"sLast":     "末页"
			},
			"oAria": {
				"sSortAscending":  ": 以升序排列此列",
				"sSortDescending": ": 以降序排列此列"
			}
		},
	    fnInitComplete:function(){
	    	//为表格中的列添加拖动功能
	    	try{
	    		this.resizableColumns();
	    	}catch(exception){
	    		
	    	}
	    }
	});
	
	
	$.fn.dataTable.ext = $.extend($.fn.dataTable.ext,{
		//How should DataTables report an error. Can take the value 'alert' or 'throw'
		errMode:'throw',
		//分分页添加不显示分页的选项: noPager
		pager:$.extend($.fn.dataTable.ext.pager,{
			noPager: function ( page, pages ) {
				//重写显示的分页内容
				$.fn.dataTable.defaults.language = $.extend($.fn.dataTable.defaults.language,{
					"sProcessing":   "处理中...",
					"sLengthMenu":   "",
					"sZeroRecords":  "",
					"sInfo":         "",
					"sInfoEmpty":    "",
					"sInfoFiltered": "",
					"sInfoPostFix":  "",
					"sSearch":       "",
					"sUrl":          "",
					"sEmptyTable":     "",
					"sLoadingRecords": "",
					"sInfoThousands":  ",",
					"oPaginate": {
						"sFirst":    "",
						"sPrevious": "",
						"sNext":     "",
						"sLast":     ""
					},
					"oAria": {
						"sSortAscending":  "",
						"sSortDescending": ""
					}
				});
				return [ 'previous', 'next' ];
			}
		})
	});
	
});

//js获取项目根路径，如： http://localhost:8083/uimcardprj  
function getRootPath(){  
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp  
    var curWwwPath=window.document.location.href;  
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp  
    var pathName=window.document.location.pathname;  
    var pos=curWwwPath.indexOf(pathName);  
    //获取主机地址，如： http://localhost:8083  
    var localhostPaht=curWwwPath.substring(0,pos);  
    //获取带"/"的项目名，如：/uimcardprj  
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);  
    return(localhostPaht+projectName);  
}  