var qualTypeDate = null;
getQualDateType();

function getQualDateType(){
	if(qualTypeDate == null){
		$.ajax({
			url : 'lSuQualTypeController/getTreeAllDateForstate.do',
			type : 'post',
			async:false,
			dataType:"json",
			success : function(data) {
				qualTypeDate = data;
			}
		});
	}
	return qualTypeDate;
}

//提交之前校验
function subVali(){
	var codeArray = [];
	var flog = true;
	var materialId = $("#materialId").val();
	if(materialId == ""){
		tip("物资必填");
		return false;
	}
	
	$("#add_lSuMaterialQualItem_table").find('>tr').each(function(k){
		var code = $("input[name='lSuMaterialQualItemList["+k+"].qualCode']").val();
		var effectDate = $("input[name='lSuMaterialQualItemList["+k+"].effectDate']").val();
		var overDate = $("input[name='lSuMaterialQualItemList["+k+"].overDate']").val();
		var qualTypeId = $("input[name='lSuMaterialQualItemList["+k+"].qualTypeId']").val();
		
		if(qualTypeId == ""){
			flog = false;
			$("input[name='lSuMaterialQualItemList["+k+"].qualTypeIdH']").focus();
			tip("资质分类必填");
			return false;
		}
		
		var d1 = new Date(effectDate);
		var d2 = new Date(overDate);
		if(d1.getTime() >= d2.getTime()){
			flog = false;
			$("input[name='lSuMaterialQualItemList["+k+"].overDate']").focus();
			tip("到期日期必须大于生效日期");
			return false;
		}
		
		if($.inArray(code,codeArray) > -1){
			flog = false;
			$("input[name='lSuMaterialQualItemList["+k+"].qualCode']").focus();
			tip("资质编码不能相同");
			return false;
		}else{
			codeArray.push(code);
		}
		
	});
	
	
	return flog;
}
//初始化下标
function resetTrNum(tableId) {
	var s = 0;
	$tbody = $("#"+tableId+"");
	$tbody.find('>tr').each(function(i){
		s = i;
		$(':input, select,button,a', this).each(function(){
			var $this = $(this), name = $this.attr('name'),id=$this.attr('id'),onclick_str=$this.attr('onclick'),onchange_str=$this.attr('onchange'), val = $this.val();
			if(name!=null){
				if (name.indexOf("#index#") >= 0){
					$this.attr("name",name.replace('#index#',i));
				}else{
					var s = name.indexOf("[");
					var e = name.indexOf("]");
					var new_name = name.substring(s+1,e);
					$this.attr("name",name.replace(new_name,i));
				}
			}
			if(id!=null){
				if (id.indexOf("#index#") >= 0){
					$this.attr("id",id.replace('#index#',i));
				}else{
					var s = id.indexOf("[");
					var e = id.indexOf("]");
					var new_id = id.substring(s+1,e);
					$this.attr("id",id.replace(new_id,i));
				}
			}
			if(onclick_str!=null){
				if (onclick_str.indexOf("#index#") >= 0){
					$this.attr("onclick",onclick_str.replace(/#index#/g,i));
				}else{
				}
			}
			/*if(onchange_str!=null){
				if (onchange_str.indexOf("#index#") >= 0){
					$this.attr("onchange",onchange_str.replace(/#index#/g,i));
				}else{
				}
			}*/
		});
		$(this).find('div[name=\'xh\']').html(i+1);
	});
	
	$("input[name='lSuMaterialQualItemList["+s+"].qualTypeIdH']").combotree({
		data:qualTypeDate,
		onSelect:function(node){
			$("input[name='lSuMaterialQualItemList["+s+"].qualTypeId']").val(node.id);
		}
	  });
}
//通用弹出式文件上传
function commonUpload(callback,inputId){
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
               iframe.uploadCallback(callback,inputId);
               return true;
       },
       cancelVal: '关闭',
       cancel: function(){
       } 
   });
}
//通用弹出式文件上传-回调
function commonUploadDefaultCallBack(url,name,inputId){
	$("#"+inputId+"_href").attr('href',url).html('下载');
	$("#"+inputId).val(url);
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