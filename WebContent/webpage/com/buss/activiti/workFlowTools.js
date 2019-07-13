
//文件选择完成后
$(function () {
	if($('#fileupload').length){
		$('#fileupload').fileupload({
		    dataType: 'json',
		    done: function (e, data) {		    	
		    	var filePath=null;
		    	var fileName=null;
		        $.each(data.result, function (index, file) {
		        	filePath=filePath==null?file.filePath:filePath+','+file.filePath;
		        	fileName=fileName==null?file.fileName:fileName+','+file.fileName;
		        	$('#filePath').val(filePath);
		        	$("#fileName").val(fileName);
		        }); 
		    }
		});
	}
});
//设置日期格式YYYY-MM-DD
function formateDate(value,row,index){
	var date = new Date(value);
	return dateFormat(date);
}
//设置审批 分支
function setOutCome(item){
			   $("#outcome").val(item);	   
			   var data = $("#formSubmit").serialize();
			   var url = $("#formSubmit").attr('action');
			   applyHand(data,url);
}

/**
 * 弹出窗口
 * @param url
 * @param title
 * @param width
 * @param height
 * @param buttons
 */
function openDefWindow(url,title,width,height,buttons){
	width = width?width:700;
	height = height?height:400;
	if(width=="100%" || height=="100%"){
		width = window.top.document.body.offsetWidth;
		height =window.top.document.body.offsetHeight-100;
	}
	$.dialog.setting.zIndex = 9999;
	$.dialog({
		content: 'url:'+url,
		zIndex: 2100,
		title:title,
		lock : true,
		width:width,
		height:height,
		opacity : 0.4,
		button:buttons
	}).zindex();
}
//审批按钮触动方法
function applyHand(data,url){
	$.ajax({
		url:url,
		type:"POST",
		data:data,
		dataType:"json",
		success:function(){
			event();
		}
	});
}
//重写了回调,然后自己控制关闭以及刷新
function event() {
	   if(frameElement.api != undefined){
		   var win = frameElement.api.opener.parent;
		   win.location.reload();
		   frameElement.api.close();
		   frameElement.api.reload();
	   }else{
		   window.parent.location.reload();
	   }
}
/*执行方法*/
function doFun(url,formId){
	 $.ajax({
		 type:"POST",
		 url:url,
		 success:function(data){
			 var d = $.parseJSON(data);
			 if(d.success){
				 $("#"+formId).datagrid("reload");
			 }
		 }
	 });
}
//日期格式化
function dateFormat(tempDate){
	if(tempDate!=undefined && tempDate!=null){
		var month = tempDate.getMonth()+1;
		var strDate = tempDate.getDate();
		var hour = tempDate.getHours();
		var minutes = tempDate.getMinutes();
		var secode = tempDate.getSeconds();
		if(month>=1 && month<=9){
			month = "0"+month;
		}
		if(strDate >=1 && strDate<=9){
			strDate = "0"+strDate;
		}
		if(hour>=1 && hour<=9){
			hour = "0"+hour;
		}
		if(minutes>=1 && minutes<=9){
			minutes = "0"+minutes;
		}
		if(secode>=1 && secode<=9){
			secode = "0"+secode;
		}
		var returnDate = tempDate.getFullYear()+"-"+month+"-"+strDate+" "
			+hour+":"+minutes+":"+secode;
		return returnDate;
	}else{
		return "";
	}
	
}
