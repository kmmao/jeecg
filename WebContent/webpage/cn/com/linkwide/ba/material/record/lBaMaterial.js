//物资名称改变，助记麻改变
  function materialNameChnage(){
		var mName = $("#materialName").val();
		if(mName.length <= 0){
			return;
		}
		
		$.ajax({
			type:'post',
			url:'lBaMaterialController.do?getPinYinHead',
			data:{
				mName:mName
			},
			dataType:'json',
			success:function(data){
				console.log(data);
				if(data.success){
					$("#monmCode").val(data.attributes.zjm);
				}
			}
		});
		
	}
function openMaterUnitSelect(){
	$.dialog.setting.zIndex = 19999;
	$.dialog({
		content: 'url:lBaMaterialMaterUnitController.do?listForSelect', 
		zIndex: 3100, 
		title: '仓库', 
		lock: true, 
		width: 800, 
		height: 400, 
		opacity: 0.4, 
		button: [
		         {name: '确定', callback: callbackMaterUnitSelect, focus: true},
		         {name: '取消', callback: function (){}}]
	}).zindex();
}

function callbackMaterUnitSelect(){
	var iframe = this.iframe.contentWindow;
	var rowsData = iframe.$('#lBaMaterialMaterUnitList4Select').datagrid('getSelections');
	if (!rowsData || rowsData.length==0) {
		tip('请选择');
		return;
	}
	
	if(rowsData.length>1){
		tip('只能选择一条记录');
		return;
	}
	
	$("#materUnitId").val(rowsData[0].id);
	$("#materUnitName").val(rowsData[0].typeName);
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