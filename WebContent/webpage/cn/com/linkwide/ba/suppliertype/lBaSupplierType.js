//保存前校验 
function check_sub(){
	//校验编码规则
	var strcode=$("#strcode").attr("value").replace(/(^\s*)|(\s*$)/g,"");
	var pCode=$("#pCode").val();//上级编码
	if(strcode&&pCode){
		var arr=strcode.split(" ");
		var len=0;//编码长度
		var level=0;//编码级次
		for(var i=0;i<arr.length;i++){
	       len+=arr[i].length;
	       if(len==pCode.length){
	    	   level=i+2;
	       }
		}
		len=0;
		for(var i=0;i<level;i++){
	       len+=arr[i].length;
		}
		var typeCode =$("#typeCode").val();
		if(typeCode.length<=pCode.length||typeCode.length!=len){
			alertTipTop("编码位数不规范");
			return false;
		}
	}
}
 

//通用弹出式文件上传
function commonUpload(callback){
    $.dialog({
           content: "url:systemController.do?commonUpload",
           lock : true,
           title:"文件上传",
           zIndex:2100,
           width:700,
           height: 200,
           parent:windowapi,
           cache:false,
       ok: function(){
               var iframe = this.iframe.contentWindow;
               iframe.uploadCallback(callback);
                   return true;
       },
       cancelVal: '关闭',
       cancel: function(){
       } 
   });
}
function browseImages(inputId, Img) {// 图片管理器，可多个上传共用
		var finder = new CKFinder();
		finder.selectActionFunction = function(fileUrl, data) {//设置文件被选中时的函数 
			$("#" + Img).attr("src", fileUrl);
			$("#" + inputId).attr("value", fileUrl);
		};
		finder.resourceType = 'Images';// 指定ckfinder只为图片进行管理
		finder.selectActionData = inputId; //接收地址的input ID
		finder.removePlugins = 'help';// 移除帮助(只有英文)
		finder.defaultLanguage = 'zh-cn';
		finder.popup();
	}
function browseFiles(inputId, file) {// 文件管理器，可多个上传共用
	var finder = new CKFinder();
	finder.selectActionFunction = function(fileUrl, data) {//设置文件被选中时的函数 
		$("#" + file).attr("href", fileUrl);
		$("#" + inputId).attr("value", fileUrl);
		decode(fileUrl, file);
	};
	finder.resourceType = 'Files';// 指定ckfinder只为文件进行管理
	finder.selectActionData = inputId; //接收地址的input ID
	finder.removePlugins = 'help';// 移除帮助(只有英文)
	finder.defaultLanguage = 'zh-cn';
	finder.popup();
}
function decode(value, id) {//value传入值,id接受值
	var last = value.lastIndexOf("/");
	var filename = value.substring(last + 1, value.length);
	$("#" + id).text(decodeURIComponent(filename));
}