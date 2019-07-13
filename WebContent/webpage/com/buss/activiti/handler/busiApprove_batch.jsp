<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>B_TASK_PROXY</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
   function setOutCome(item){
	   $("#outcome").val(item);
	   var url="actTaskController.do?completeTakeBatch";
	   var message ="确定通过吗？";
	   if(item!='同意' && item!='通过'){
		   message ="确定退回吗？";
		   url="actTaskController.do?rejectTaskBatch";
	   }
	   
	   var comment =$("#comment").val();
  		if(!comment&&item=='退回'){
  		 	alertTip1('请填写审批意见!');
  		 	return;
  		}
  		 $.dialog.setting.zIndex = 100000;
  		$.dialog.confirm(message, function(r) {
	  		   if (r) {
				   $.ajax({
						type : 'POST',
						url : url,
						data:{
								taskId:$("#taskId").val(),
								busId:$("#busId").val(),
								outcome:$("#outcome").val(),
								comment:$("#comment").val()
						},
						success : function(data) {
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
	  		});
  		
  		
   }
   $(document).ready(function(){
	   if("${batchListUrl}"){
		   debugger;
		   $("#busiDiv").load("${batchListUrl}&ids=${batchListIds}",
				   function(){
			   $(":input").attr("disabled","true");
			   $("#comment").removeAttr("disabled");
		   });
	   }
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
		.easyui-layout.layout{
			min-height:300px !important;
			max-height:500px !important;
		}
		/* .datagrid .panel-body{
			height:300px !important;
		}  */
		.panel-body.panel-body-noheader.layout-body.panel-noscroll{
			min-height:300px !important;
			max-height:500px !important;
		}
	</style>
 </head>
 <body style="overflow-x: hidden;">
  <t:formvalid formid="formSubmit" dialog="true" usePlugin="password" layout="table" tiptype="1" 
  	action="actTaskController.do?completeTakeBatch" >	
	  	<input id="taskId" name="taskId" type="hidden" value="${taskId}">
	  	<input id="busId" name="busId" type="hidden" value="${busiID}">
	  	<input id="outcome" name="outcome" hidden="true" />
  		<div style="width: auto;height: auto;">
  			<!-- <span style='font-size:20px'>业务数据</span> -->
		  	<div data-options="region:'center', collapsed:false, split:true, border:false " style="height: auto">
		  		<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:auto;height:1px;"></div>
				<div id="busiDiv" style="margin-top: 10px;margin-bottom: 10px;"></div>
			</div>
			<span style='font-size:20px;'>审批意见</span>
			<div data-options="region:'south',collapsed:false,split:true,border:ture"
				style="height: auto;margin-top: 10px;margin-left: 20px;" align = "left">
				<table cellpadding="0" cellspacing="1" class="formtable">
					 <tr>
						<td align="left"><label class="Validform_label">意见:</label></td>
						<td class="value">
					  	<textarea style="width:450px;" class="inputxt" rows="3" id="comment"  name="comment"></textarea>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">批注</label>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center" height="30px" width='600px'>				 	
						 	<input type="submit" id="btnsub" hidden="true" />
							<c:forEach items="${comeList}" varStatus="" var="item">
								<a  href="#" class="l-btn" onclick="setOutCome('${item}')">
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
 </t:formvalid>			
 </body>
