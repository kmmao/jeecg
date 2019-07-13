<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="workFlowCateList" treegrid="true" checkbox="true" pagination="false" actionUrl="workFlowCateController.do?datagrid" idField="id"  title="流程分类">
   <t:dgCol title="分类名称" field="name" treefield="text" width="100"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="workFlowCateController.do?doDel&id={id}" />
   <t:dgToolBar title="提交" operationCode='approve'  icon="icon-ok" url="actReProcdefController.do?selectFlow&actReModelCode=taskproxy" funname="goApproveWithFlow"></t:dgToolBar>
  </t:datagrid>

  </div>
 </div>
 <script src = "webpage/com/buss/workflowcate/workFlowCateList.js"></script>	
 <script type="text/javascript">
 	//提交
	function goApproveWithFlow(title,url,gname) {
		 var ifHasFlow = true;
		 //判断是否存在审批流
		 $.ajax({
				url:"actReProcdefController.do?ifHasFlow",
				async:false,
				data:{
					actReModelCode:'code0001'
				},
				dataType:"json",
				success:function(d){				
					if(!d.success){
						ifHasFlow = false;
					}
				}
		 });	 
		 
	 	gridname=gname;
	     var ids = [];
	     var rows = $("#"+gname).datagrid('getSelections');
	     if (rows.length > 0) {
	     	$.dialog.confirm('你确定审批选中的数据吗?', function(r) {
	 		   if (r) {
	 				for ( var i = 0; i < rows.length; i++) {
	 					ids.push(rows[i].id);
	 				}
	 				if(ids.length>0){
	 					if(ifHasFlow){
		 					var buttons = [
						   		{name: '确定', callback: doApprove, focus: true},
					  			{name: '取消', callback: function (){}}
							];
		 					url += "&busiId="+ids.join(',');
					 		showDefDlg('选择工作流',url,"400px","300px",buttons);
				 		}else{
				 			var url_noflow = "bTaskProxyApporveController.do?doApprove";
				 			doUrl(url_noflow,gname,ids);
				 		}
	 				}else{
	 					tip("请选择未审批数据再审批！或任务负责人未指定！");
	 				}
	 			}
	 		});
	 	} else {
	 		tip("请选择需要审批的数据");
	 	}
	 }		
 	function getSelectNodeId(){
 		var rowData = $('#workFlowCateList').datagrid('getSelected');
		if (rowData) {
			return rowData.id;
		}else{
			return "";
		}
 	}
 	
 	/**自定义弹出框*/
 	function showDefDlg(title,url,width,height,buttons){
 		 $.dialog.setting.zIndex = 1;
 			$.dialog({
 				content: 'url:'+url,  zIndex: 3000,  title: title,  lock: true,  width: width, 
 				height: height,  opacity: 0.4,  button: buttons
 			});
 	}

 	 /**处理审批确定操作*/
 	 function doApprove(){
 		 var iframe = this.iframe.contentWindow;
 		 var workflowId =iframe.$("#workflowId").val();
 		 var ids =iframe.$("#busiId").val();
 		 var url = "bTaskProxyApporveController.do?doApprove&workflowId="+workflowId;
 		 doUrl(url,'workFlowCateList',ids.split(','));
 	 }
  	/**执行url*/
  	function doUrl(url,gname,ids) {
  		$.ajax({
  			url : url, type : 'post',
  			data : { ids : ids.join(',') },
  			cache : false,
  			success : function(data) {
  				var d = $.parseJSON(data);
  				if (d.success) {
  					var msg = d.msg; 
  					tip(msg);
  					reloadTable();
  					$("#"+gname).datagrid('unselectAll');
  					ids='';
  				}else{ alert(d.msg); }
  			}		
  		});
  	}
</script>