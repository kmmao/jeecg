<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>附件</title>
    <style>
  .ui-button {
  	  display: inline-block;
	  padding: 2px 2px;
	  margin-bottom: 0;
	  font-size: 8px;
	  font-weight: normal;
	  line-height: 1.42857143;
	  text-align: center;
	  white-space: nowrap;
	  vertical-align: middle;
	  -ms-touch-action: manipulation;
      touch-action: manipulation;
	  cursor: pointer;
	  -webkit-user-select: none;
      -moz-user-select: none;
      -ms-user-select: none;
      user-select: none;
	  background-image: none;
	  border: 1px solid transparent;
	  border-radius: 4px;
  }
  </style>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script type="text/javascript" src="editor-app/libs/jquery_1.11.0/jquery.min.js"></script>
<script type="text/javascript" src="editor-app/libs/jquery.media.js"></script>
<script type="text/javascript">
	
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    	if(location.href.indexOf("load=detail")!=-1){
			$(":input").attr("disabled","true");
			$(".datagrid-toolbar").hide();
		}
		up();
		//兼容ie下载文件
		 $("#vendorFileDict_table td[field='filedowload'] a").click(function(){
			 var src=$(this).attr('href');
			//调用方法
			 download(src);
		 })
		 //preview();
    });
    
    var tableName ="${tableName }";
    var tableId="${tableId }"; 
 
    /**
     * 执行操作
     * 
     * @param url
     * @param index
     */
    function doSubmitFile(url,name,data) { 
    	gridname=name;
    	//--author：JueYue ---------date：20140227---------for：把URL转换成POST参数防止URL参数超出范围的问题
    	var paramsData = data;
    	if(!paramsData){
    		paramsData = new Object();
    		if (url.indexOf("&") != -1) {
    			var str = url.substr(url.indexOf("&")+1);
    			url = url.substr(0,url.indexOf("&"));
    			var strs = str.split("&");
    			for(var i = 0; i < strs.length; i ++) {
    				paramsData[strs[i].split("=")[0]]=(strs[i].split("=")[1]);
    			}
    		}      
    	}
    	//--author：JueYue ---------date：20140227---------for：把URL转换成POST参数防止URL参数超出范围的问题
    	$.ajax({
    		async : false,
    		cache : false,
    		type : 'POST',
    		data : paramsData,
    		url : url,// 请求的action路径
    		error : function() {// 请求失败处理函数
    			tip("文件上传失败！");
    		},
    		success : function(data) {
    			 var d = $.parseJSON(data);
    			 if(data.indexOf("失败")>0){ 
    				 tip("文件上传失败！");
    			 }else{ 
    				 var tr  =  "<tr id=\"fileList"+d.obj.id+"\" datagrid-row-index=\"0\" class=\"datagrid-row datagrid-row-selected datagrid-row-over\" style=\"height: 51px;\">";
    			    	tr +=  "<td field=\"id\" style=\"display:none;\"><div style=\"height:auto;\" class=\"datagrid-cell datagrid-cell-c1-id\">"+d.obj.id+"</div></td>";
    			    	tr +=  "<td field=\"filePath\"><div style=\"height:auto;\" class=\"datagrid-cell datagrid-cell-c1-filePath\"><img width=\"30\" height=\"30\" border=\"0\" onmouseover=\"tipImg(this)\" onmouseout=\"moveTipImg()\" src=\""+getPage("${realPath}"+d.obj.filePath)+"\"></div></td>";
    			    	tr +=  "<td field=\"fileName\"><div style=\"height:auto;\" class=\"datagrid-cell datagrid-cell-c1-fileName\">"+d.obj.fileName+"</div></td>";
    			    	tr +=  "<td field=\"filedowload\"><div style=\"height:auto;\" class=\"datagrid-cell datagrid-cell-c1-opt\"><a href=\"${realPath}"+d.obj.filePath+"\" class=\"ace_button\" onclick=\"\">  <i class=\" fa fa-download\"></i>下载</a>&nbsp;</div></td>";
    			    	tr +=  "<td field=\"filedel\"><div style=\"height:auto;\" class=\"datagrid-cell datagrid-cell-c1-opt\"><a href=\"#\" class=\"ace_button\" onclick=\"delObj('fileDictController.do?doDel&amp;id="+d.obj.id+"','fileDictList','fileList"+d.obj.id+"')\">  <i class=\" fa fa-trash-o\"></i> 删除</a>&nbsp;</div></td>";
    			    	tr +=  "</tr>";
    			 	 $("#add_fileDict_table").append(tr);
    			 	resetTrNum('add_fileDict_table');
    			 	up()
    			 }
    		}
    	});
    	
    	
    }
    
    
 // 删除调用函数
 var stringId ="";
  function delObj(url,name,trId) {
  	gridname=name;
  	createdialog('删除确认 ', '确定删除该记录吗 ?', url,name);
  	stringId =trId;
  }
 function reloadTable(){ 
	 document.getElementById(stringId).style.display="none";
	 
 }
 
 function uploadSuccess(d,file,response){ 
	 /*  doSubmitFile('fileDictController.do?doAddFile&filePath='+d.attributes.url+"&fileName="+d.attributes.name+"&tableName="+tableName+"&tableId="+tableId,'fileDictList',"");
     $('#tt').tabs('getSelected').panel('refresh')
     up(); */
      var filePath = d.attributes.url; 
      var fileName = d.attributes.name; 
      var id = d.attributes.id; 
      var tr  =  "<tr id=\"fileList"+id+"\" datagrid-row-index=\"0\" class=\"datagrid-row datagrid-row-selected datagrid-row-over\" style=\"height: 51px;\">";
	  	tr +=  "<td field=\"id\" style=\"display:none;\"><div style=\"height:auto;\" class=\"datagrid-cell datagrid-cell-c1-id\">"+id+"</div></td>";
	  	tr +=  "<td field=\"filePath\"><div style=\"height:auto;\" class=\"datagrid-cell datagrid-cell-c1-filePath\"><img width=\"30\" height=\"30\" border=\"0\" onmouseover=\"tipImg(this)\" onmouseout=\"moveTipImg()\" src=\""+getPage("${realPath}"+filePath)+"\"></div></td>";
	  	tr +=  "<td field=\"fileName\"><div style=\"height:auto;\" class=\"datagrid-cell datagrid-cell-c1-fileName\">"+fileName+"</div></td>";
	  	tr +=  "<td field=\"filedowload\"><div style=\"height:auto;\" class=\"datagrid-cell datagrid-cell-c1-opt\"><a href=\"${realPath}"+filePath+"\" class=\"ace_button\" onclick=\"\">  <i class=\" fa fa-download\"></i>下载</a>&nbsp;</div></td>";
	  	tr +=  "<td field=\"filedel\"><div style=\"height:auto;\" class=\"datagrid-cell datagrid-cell-c1-opt\"><a href=\"#\" class=\"ace_button\" onclick=\"delObj('fileDictController.do?doDel&amp;id="+id+"','fileDictList','fileList"+id+"')\">  <i class=\" fa fa-trash-o\"></i> 删除</a>&nbsp;</div></td>";
	  	tr +=  "</tr>";
	 $("#add_fileDict_table").append(tr);
	resetTrNum('add_fileDict_table');
	up()
} 
 
 /*
  * 鼠标放在图片上方，显示大图
  */
 var bigImgIndex = null;
 function tipImg(obj){
 	try{
 		var navigatorName = "Microsoft Internet Explorer"; 
 		if( navigator.appName != navigatorName ){ 
 			if(obj.nodeName == 'IMG'){
 				var e = window.event;
 				var x = e.clientX+document.body.scrollLeft + document.documentElement.scrollLeft+10;
 				var y = e.clientY+document.body.scrollTop + document.documentElement.scrollTop;
 				y =20; 
 				var src = obj.src;
 				var width = obj.naturalWidth;
 				var height = obj.naturalHeight;
 				bigImgIndex = layer.open({
 					content:[src,'no'],
 					type:2,
 					offset:[y+"px",x+"px"],
 					title:false,
 					area:[width+"px",height+"px"],
 					shade:0,
 					closeBtn:0
 				});
 			}
 		}
 	}catch(e){
 	}
 	
 }

 function moveTipImg(){
 	try{
 		if(bigImgIndex != null){
 			layer.close(bigImgIndex);
 		}
 	}catch(e){
 		
 	}
 }
 //兼容ie下载文件
function fake_click(obj) {
    var ev = document.createEvent("MouseEvents");
    ev.initMouseEvent(
        "click", true, false, window, 0, 0, 0, 0, 0
        , false, false, false, false, 0, null
     );
    obj.dispatchEvent(ev);
}
 function download(name, data) {
    var urlObject = window.URL || window.webkitURL || window;
    //var downloadData = new Blob([data]); 
    var save_link = document.createElementNS("http://www.w3.org/1999/xhtml", "a")
    //save_link.href = urlObject.createObjectURL(downloadData);
    save_link.download = name;
    fake_click(save_link);
}

function preview(){
	$('.media').media({
	     width: '100%',
	     height: '100%'
	});
} 

function pre(url,id){
	var index=url.lastIndexOf('.');
	//文档类型
	var docType=url.substring(index+1,url.length);
	
	if(docType=='pdf'){
		$('.media').media({
	     	width: '100%',
	     	height: '100%'
		});
	}else if(docType=='docx'){
		//$('#'+id).attr('src','');  //隐藏
		$("#"+id).css('display','block');//显示
		$('#'+id).attr('src',url); //显示
		//$("<iframe src='"+ url +"' width='100%' height='362px' frameborder='1'>").appendTo("#"+id);
	}
}


</script>
</head>
<body style="overflow-x: hidden;">
 <c:if test="${isEdit  == 1  }">
    <table cellpadding="0" cellspacing="0" class="formtable">
   <tbody>
    <tr height="60px">
     <td align="right" width="20%">
       <label class="Validform_label">请选择文件：</label>
     </td>
     <td class="value" width="140px">
      <t:upload name="instruction" dialog="false" multi="false" extend="" queueID="instructionfile" view="false" auto="true" uploader="cgUploadController.do?ajaxSaveFile&modName=${modName}&tableName=${tableName}&tableId=${tableId}" onUploadSuccess="uploadSuccess"  id="instruction" formData="documentTitle"></t:upload>
     </td>
     <td colspan="1" id="instructionfile" class="value"> </td>
    </tr>
   </tbody>
  </table>
   <div id="fileShow" >
  </div>
</c:if>


<table border="0" cellpadding="2" cellspacing="1" id="vendorFileDict_table" width="100%">

	<%-- <tr id="datagrid-row-r111" datagrid-row-index="0" class="datagrid-row datagrid-row-selected datagrid-row-over" style="height: 51px;width:700px;">
	<td field="id" style="display:none;"><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-id"></div></td>
	<td field="filePath" style="display:none;"><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-filePath">文件展示</td>
	<td field="fileName"><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-fileName">文件名称</div></td>
	<td field="filedowload"><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-filePath">下载</td>
	<c:if test="${isEdit  == 1  }">
	<td field="filedel"><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-filePath">删除</td>
	</c:if>
 	 </tr> --%>
	<tbody id="add_fileDict_table">
	<c:if test="${fn:length(fileDictList)  > 0 }">
		<tr id="fileDictList${stuts.index}" datagrid-row-index="0" class="datagrid-row datagrid-row-selected datagrid-row-over" style="height: 51px;">
			<c:forEach items="${fileDictList}" var="poVal" varStatus="stuts">
				<td field="id" style="display:none;"><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-id">${poVal.id }</div></td>
				
				<td field="filePath" style="display:none;"><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-filePath"><img width="30" class="img" height="30" border="0" onmouseover="tipImg(this)" onmouseout="moveTipImg()" src="${realPath}${poVal.filePath }"></div></td>
				
			 	<td field="fileName" ><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-fileName"  >${poVal.fileName }</div>
			 	<%--onmouseover="pre('${realPath}${poVal.filePath }','${poVal.id }')"--%>
			 	<%-- <a class="media" href="${poVal.filePath }"></a> --%>
			 	<%-- <a href="${poVal.filePath }" target="_blank" rel="nofollow">XDOC</a> --%>
			 	<iframe style="display:none;" id="${poVal.id }" src='' width='100%' height='100%' frameborder='1'>
                </iframe>
			 	</td>
				<td field="filedowload"><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-opt"><a  href="${realPath}${poVal.filePath }" class="ace_button" >  <i class=" fa fa-download"></i>下载</a>&nbsp;</div></td>
				<%-- <c:if test="${isEdit  == 1 }">
				<td field="filedel"><div style="height:auto;" class="datagrid-cell datagrid-cell-c1-opt"><a href="#" class="ace_button" onclick="delObj('fileDictController.do?doDel&amp;id=${poVal.id }','fileDictList','fileDictList${stuts.index}')">  <i class=" fa fa-trash-o"></i> 删除</a>&nbsp;</div></td>
				</c:if>	 --%>
			</c:forEach>
		</tr>
	</c:if>	
	</tbody>
</table>
 </body>
 <script>
	// $(function(){
		function up(){
		$("#datagrid-row-r111").css("height","30px")
		$("#add_fileDict_table tr").css("height","30px")
		var a= $('.img')
		for(var i=0;i<a.length;i++){
			var file=a[i].src;
			var filename=file.replace(/.*(\/|\\)/, ""); 
			var fileExt=(/[.]/.exec(filename)) ? /[^.]+$/.exec(filename.toLowerCase()) : ''; 
			if(fileExt =="ppt"){
				a[i].src="plug-in/ace/css/images/ppt.png";
			}else if(fileExt =="xls"||fileExt =="xlsx"){
				a[i].src="plug-in/ace/css/images/excel.png";
			}else if(fileExt =="pdf"){
				a[i].src="plug-in/ace/css/images/pdf.png";
			}else if(fileExt =="zip"||fileExt =="rar"){
				a[i].src="plug-in/ace/css/images/zip.png";
			}else if(fileExt =="doc" || fileExt =="docx"||fileExt =="txt"||fileExt =="exe"){
				a[i].src="plug-in/ace/css/images/word.png";
			}else if(fileExt =="png"||fileExt =="jpg"||fileExt =="jpeg"||fileExt =="ico"||fileExt =="bmp" ||fileExt =="gif"){
				a[i].src=file;
			}else{
				a[i].src="plug-in/ace/css/images/txt.png";
			}
		}
	}
		
		 function getPage(file){
			 var filename=file.replace(/.*(\/|\\)/, ""); 
				var fileExt=(/[.]/.exec(filename)) ? /[^.]+$/.exec(filename.toLowerCase()) : ''; 
				if(fileExt =="ppt"){
					return "plug-in/ace/css/images/ppt.png";
				}else if(fileExt =="xls"||fileExt =="xlsx"){
					return "plug-in/ace/css/images/excel.png";
				}else if(fileExt =="pdf"){
					return "plug-in/ace/css/images/pdf.png";
				}else if(fileExt =="zip"||fileExt =="rar"){
					return "plug-in/ace/css/images/zip.png";
				}else if(fileExt =="doc"||fileExt =="docx"||fileExt =="txt"||fileExt =="exe"){
					return "plug-in/ace/css/images/word.png";
				}else if(fileExt =="png"||fileExt =="jpg"||fileExt =="jpeg"||fileExt =="ico"||fileExt =="bmp" ||fileExt =="gif"){
					return file;
				}else{
					return "plug-in/ace/css/images/txt.png";
				}
		 }
	// })
 </script>
