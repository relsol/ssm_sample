/**
 * SCADA系统接口 V0.1
 *
 * author: cuixiaodong 
 *
 */

(function($) {
	
	$.SCADA={
		/**
		 * 显示scada图像的弹出框
		 * @param title	弹出框的标题
		 * @param url	弹出框的地址
		 * @param width	宽度
		 * @param height	高度
		 */
		showSCADA:function(title,url,width,height){
			if(height==null){
				height=400;
			}
			if(width==null){
				width=800;
			}
			$.layer({
				type : 2,
				shade : [0.5 , '#000' , true],
				title : [title,true],
				iframe : {src : url},
				area : [width , height],
				offset : ['50px', '']
			});
			
//			$.dialog.open(url,
//					{
//						title:title,
//						drag: false,
//						fixed: true,
//						lock:true,
//						opacity: 0.87,	// 透明度
//						width: width,
//					    height: height
//		    		}
//			);
		}
	
	}
})(jQuery);//使用闭包
