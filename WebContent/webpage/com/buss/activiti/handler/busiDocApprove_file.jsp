<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>B_TASK_PROXY</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/tools/Map.js"></script>
	<script src="plug-in/jquery-plugs/fileupload/js/vendor/jquery.ui.widget.js"></script>
	<script src="plug-in/jquery-plugs/fileupload/js/jquery.iframe-transport.js"></script>
	<script src="plug-in/jquery-plugs/fileupload/js/jquery.fileupload.js" charset="UTF-8" ></script>
	<script src="plug-in/jquery-plugs/fileupload/js/jquery.fileupload-process.js"></script>
	<script src="plug-in/jquery-plugs/fileupload/js/jquery.fileupload-validate.js"></script>
	<link href="plug-in/jquery-plugs/fileupload/css/jquery.fileupload.css" type="text/css" rel="stylesheet" />
	<script src="plug-in/jquery-plugs/fileupload/bootstrap/js/bootstrap.min.js"></script>
	<script src = "webpage/com/buss/activiti/workFlowTools.js"></script>
  <style type="text/css">
		.btn {
		  display: inline-block;
		  *display: inline;
		  padding: 5px 12px;
		  margin-bottom: 6px;
		  *margin-left: .3em;
		  font-size: 14px;
		  line-height: 20px;
		  color: #333333;
		  text-align: center;
		  text-shadow: 0 1px 1px rgba(255, 255, 255, 0.75);
		  vertical-align: middle;
		  cursor: pointer;
		  background-color: #f5f5f5;
		  *background-color: #e6e6e6;
		  background-image: -moz-linear-gradient(top, #ffffff, #e6e6e6);
		  background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#ffffff), to(#e6e6e6));
		  background-image: -webkit-linear-gradient(top, #ffffff, #e6e6e6);
		  background-image: -o-linear-gradient(top, #ffffff, #e6e6e6);
		  background-image: linear-gradient(to bottom, #ffffff, #e6e6e6);
		  background-repeat: repeat-x;
		  border: 1px solid #cccccc;
		  *border: 0;
		  border-color: #e6e6e6 #e6e6e6 #bfbfbf;
		  border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
		  border-bottom-color: #b3b3b3;
		  -webkit-border-radius: 4px;
		     -moz-border-radius: 4px;
		          border-radius: 4px;
		  filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffffffff', endColorstr='#ffe6e6e6', GradientType=0);
		  filter: progid:DXImageTransform.Microsoft.gradient(enabled=false);
		  *zoom: 1;
		  -webkit-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
		     -moz-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
		          box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
		}
	</style>
  
  <script type="text/javascript">
   function setOutCome(item){
	   var state = new Map();
	   console.log($("tbody[id$='_table'] select[name$='.isApprove']"));
	   $.each($("tbody[id$='_table'] select[name$='.isApprove']"),function(i,obj){
			state.put($(obj).val(),$(obj).val());
	   });
	   if(item=='通过'){
		   if(state.containsKey('N')){
			   alertTip1('存在未通过的数据!');
			   return ;
		   }else{
		   	   $.dialog.setting.zIndex = 100000;
			   $.dialog.confirm('确定通过此单据吗?', function(r) {
		  		   if (r) {
					   executionOfBusiness(item);
		  			}
		  		});
		   }
	   }else{ //退回
		   if(state.containsKey('Y') && state.size()==1){
			   alertTip1('没有未通过的数据!');
			   return ;
		   }else{
		   		var comment =$("#comment").val();
		   		if(!comment){
		   		 	alertTip1('请填写审批意见!');
		   		 	return;
		   		}
		   	   $.dialog.setting.zIndex = 100000;
			   $.dialog.confirm('确定退回此单据吗?', function(r) {
		  		   if (r) {
					   var d = {};
					    var t = $('#formobj').serializeArray();
					    $.each(t, function() {
					    	var value = this.value;
					    	if(value.indexOf(',')){
					    		if(/^(-)?\d{1,3}(,\d{3})*(.\d+)?$/.test(value)){
						    		value = value.replace(/,/g, "");
					    		}
					    		d[this.name] = value;
					    	}else{
						      d[this.name] = value;
					    	}
					    });
					    console.log(d);
					   var url = $('#formobj').attr('action');
					   if(url){
						   $.ajax({
								url : url,
								type : 'post',
								data : d ,
								cache : false,
								success : function(data) {
									data = $.parseJSON(data);
									   if(data.success){
										   executionOfBusiness(item);
									   }else{
										   alertTip1(data.msg);
									   }
								}
							});
					   }else{
					   		executionOfBusiness(item);
					   }
				  	}
		  		});
			    
			   /* $("#formobj").form('submit', {
				   success : function(data) {
					   data = $.parseJSON(data);
					   if(data.success){
						   executionOfBusiness(item);
					   }else{
						   alertTip1(data.msg);
					   }
					}
			   }); */
		   }
	   }
	   
   }
   
   function executionOfBusiness(item){
   	   $(".l-btn").removeAttr("onclick");
	   $("#outcome").val(item);
	   var url="actTaskController.do?completeTake";
	   if(item!='同意' && item!='通过'){
		   url="actTaskController.do?rejectTask";
	   }
	   //流转条件判断需要的字段(可能会有多个)
	   var param =$("input[name='conditionPara']").val();
	   //字段类型
	   var paramType = $("input[name='conditionParaType']").val();
	   //字段的值
	   var paraValue=null;
	   if(param){
	   		var paramArr =param.split(',');
	   		for(var i=0;i<paramArr.length;i++){
	   			var value = $("input[name='"+paramArr[i]+"']").val();
	   			paraValue=paraValue==null?value:paraValue+','+value;
	   		}
	     
	   }
	   //字段的值
	   //var paraValue = $("input[name='"+param+"']").val();
	   var filePath=$("input[name='filePath']").val();
	    $.ajax({
			type : 'POST',
			url : url,
			data:{
					taskId:$("#taskId").val(),
					busId:$("#busId").val(),
					outcome:$("#outcome").val(),
					comment:$("#comment").val(),
					filePath:filePath,
					conditionPara:param,
					conditionParaType : paramType,
					conditionParaValue:paraValue
			},
			success : function(data) {
				//tip(data.msg);
				if(frameElement.api != undefined){
			  		var win = frameElement.api.opener;
					win.location.reload();
		          	frameElement.api.close();
		           	window.location.reload();
				}else{
					window.parent.location.reload();
				}
			}
		}); 
   }
   
   $(document).ready(function(){
	   //$("#busiDiv").load("actTaskController.do?fileEdit&isEdit=0&tableName=${tableName}&tableId=${busiID}",function(){
	  $("#busiDiv").load("${fileUrl}&id=${busiID}&auditUser=${auditUser}",function(){
		 $(":input").attr("readonly","readonly");
		   $("#comment").removeAttr("readonly");
	   });
	});
	/**
	 * 提示信息像alert一样
	 */
	function alertTip1(msg,title) {
		title = title?title:"提示信息";
		top.$.dialog({
				title:title,
				zIndex: 100000,
				icon:'tips.gif',
				content: msg
		});
	}
  </script>
   <style>
 	.datagrid-toolbar{
 		display : none;
 	}
 	/*.panel{
 		width: 100% !important;
 		overflow: auto !important;
 	}*/
 	  /*#budgDeptDetailIncom_tablescrolldiv,#budgExpendOrgB_tablescrolldiv{
	  	height: 250px !important;
	  }*/
	  #formobj .panel-body.panel-body-noheader.panel-body-noborder{
	  	/*height: 275px !important;*/
	  	/*min-height: 275px;
	    max-height: 300px;*/
	    max-height: none;
	    min-height: inherit;
	  }
	  #opinion{
	  	margin-top:160px;
	  }
	  #opinion table tr{
	  	/*height:100px;*/
	  }
 	</style>
 </head>
 <body style="overflow-x: hidden;">
  <t:formvalid formid="formSubmit" dialog="true" usePlugin="password" layout="table" tiptype="1" 
  	action="actTaskController.do?completeTake" >	
	  	<input id="taskId" name="taskId" type="hidden" value="${taskId}">
	  	<input id="busId" name="busId" type="hidden" value="${busiID}">
	  	<!--判断条件流转需要的参数  (字段名称和对应的值)  -->
	  	<input id="conditionPara" name="conditionPara" type="hidden" value="${conditionPara}">
	  	<input id="conditionParaType" name="conditionParaType" type="hidden" value="${conditionParaType}">
	  	<input id="outcome" name="outcome" hidden="true" />
  		<div style="width: auto;height: auto;">
  			<!-- <span style='font-size:20px'>业务数据</span> -->
		  	<div data-options="region:'center', collapsed:false, split:true, border:false " style="height: auto">
		  		<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:auto;height:1px;"></div>
				<div id="busiDiv" style="margin-left: 20px;margin-top: 10px;"></div>
			</div>
			<div id="opinion">
				<span style='font-size:16px;margin-left:20px'>审批意见</span>
				<div data-options="region:'south',collapsed:false,split:true,border:ture"
					style="height: auto;margin-top: 10px;margin-left: 20px;" align = "left">
					<table cellpadding="0" cellspacing="1" class="formtable">
						<c:forEach items="${commentList}" varStatus="" var="item">
							<tr>
								<td align="right"><label class="Validform_label">${item.auditUser}:</label></td>
								<td class="value" style="background:#fafafa">${item.comment}</td>
							</tr>
						</c:forEach>
						 <tr>
							<td align="right"><label class="Validform_label">意见:</label></td>
							<td class="value">
						  		<textarea style="width:450px;height:200px;" class="inputxt" rows="3" id="comment" maxlength="200"  name="comment"></textarea>
								<span class="Validform_checktip"></span>
								<label class="Validform_label" style="display: none;">意见</label>
							</td>
						</tr>
						<!-- <tr>
							<td align="right"><label class="Validform_label">附件:</label></td>
							<td class="value">
								<input id="filePath" name="filePath" type="hidden" >
								<input id="fileName" name="fileName" type="text" data-url="fileUpAndDownLoadController.do?upload" style="width: 450px;height: 25px;" class="inputxt"  readonly="readonly" >
							    <span class="btn fileinput-button">
							        <i class="glyphicon glyphicon-plus"></i>
							        <span>选择</span>
							        <input id="fileupload" type="file" name="files[]"  data-url="fileUpAndDownLoadController.do?upload">
							    </span>
								<span class="Validform_checktip"></span>
								<label class="Validform_label" style="display: none;">审批附件</label>
							</td> 
						</tr> -->
						<tr>
							<td colspan="4" align="center" height="60px" width='600px'>				 	
							 	<input type="submit" id="btnsub" hidden="true" />
								<c:forEach items="${comeList}" varStatus="" var="item">
									<a  href="#" class="l-btn" style="margin-right:15px" onclick="setOutCome('${item}')">
										<span class="l-btn-left">
											<span class="l-btn-text">${item}</span>
										</span>
									</a>
								</c:forEach>
							</td>
						</tr>
					</table>
				</div>
			</div>
	</div>
 </t:formvalid>		
 <script>
   function showPreview(source){
	   var arrs = $(source).val().split('\\'); 
	   var filename=arrs[arrs.length-1];
	   $("#fileName").val(filename); 
	   var file = source.files[0]; 
	   var total = file.size; 
	   if(window.FileReader) { 
	   		var fr = new FileReader(); 
	   		fr.onabort=function () { 
	   			layer.msg("文件上传中断,请重试");
	   		}; 
	   		fr.onerror=function () { 
	   			layer.msg("文件上传出错，请重试"); 
	   		}; 
	   		fr.onload=function () {
	  			layer.msg("文件上传成功");
	    	}; 
	   		fr.readAsDataURL(file); 
	   } 
	   
	   /* $.ajax({
			url:"fileUpAndDownLoadController.do?upload",
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				
			}
		}); */
   }
 </script>
 </body>
