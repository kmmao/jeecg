<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 0px; border: 0px">
		<t:datagrid name="newTaskList"  fitColumns="false" title="" actionUrl="actTaskController.do?datagridNew" idField="id" fit="true" queryMode="group">
			<t:dgCol title="流程分类" field="category" queryMode="single" query="true" width="120" dictionary="work_flow_cate,id,name"></t:dgCol>
			<t:dgCol title="流程定义Id" field="id"  queryMode="single" query="true" width="120"></t:dgCol>
			<t:dgCol title="部署Id" field="deploymentId" hidden="true" queryMode="single" query="false" width="80"></t:dgCol>
			<t:dgCol title="名称" field="name" queryMode="single" query="true" width="120"></t:dgCol>
			<t:dgCol title="流程标识" field="key" queryMode="single" query="false" width="120"></t:dgCol>
			<t:dgCol title="流程XML" field="resourceName" queryMode="single" query="false" width="180" formatterjs="resourceReadXml"></t:dgCol>
			<t:dgCol title="流程图片" field="diagramResourceName" queryMode="single" query="false" width="180" formatterjs="resourceReadImage"></t:dgCol>
			<t:dgCol title="流程版本" field="version" queryMode="single" query="false" width="80"></t:dgCol>
			<t:dgCol title="更新时间" field="deploymentTime"  formatter="yyyy-MM-dd" width="100"></t:dgCol>
			<t:dgCol title="操作" field="opt" width="200" formatterjs="contrFlow"></t:dgCol>
		</t:datagrid>
	</div>
</div>

<script src = "webpage/com/buss/activiti/workFlowTools.js"></script>	
 <script type="text/javascript">
 function contrFlow(value,row,index){
	 var deploymentId =  row.deploymentId;
	 var processDefinitaion = row.id;
	 var url = "<a href='#' onclick='delFlow("+deploymentId+")'>[删除]</a> &nbsp;&nbsp;&nbsp;";
	 url += "<a href='#' onclick='startFlow(&quot;"+processDefinitaion+"&quot;)'>[启动流程]</a> &nbsp;&nbsp;&nbsp;";
	 return url;
 }
 //启动流程
 function startFlow(processDefinitaion){
	 var buttons = [{name:"确认",callback:doSave,focus:true},
	                {name:"取消",callback:function(){}}];
		var url = "actTaskController.do?startFlowFromProcess&processDefinitaion="+processDefinitaion;
		openDefWindow(url,"启动流程",getWdPx(0.7),getHgPx(0.6),buttons);
 }
 //保存单据
 function doSave(){
	 //区分单据类型：业务单据、动态单据
	 var iframe = this.iframe.contentWindow;
	 //触发submit事件，提交表单   
	 var data = iframe.$("#formSubmit").serialize();
	 var url = iframe.$("#formSubmit").attr('action');
	 applyHand(data,url);
 }
 //删除
 function delFlow(deploymentId){
	 var url = "actTaskController.do?doDelProcess&deploymentId="+deploymentId;
	 doFun(url);
 }
 /*执行方法*/
 function doFun(url){
	 $.ajax({
		 type:"POST",
		 url:url,
		 success:function(data){
			 var d = $.parseJSON(data);
			 if(d.success){
				 $("#processList").datagrid("reload");
			 }
		 }
	 });
 }

  	$(function() {
		$("select[name='category']").combotree({
			url : 'workFlowCateController.do?tree',
			panelHeight : 200,
			editable:false,
			width : 157,
			onClick : function(node) {
				$("#category").val(node.id);
			}
		});
	});

	//查看xml
	function resourceReadXml(value,row,index){
		var s ="";
		
		s = s  + "&nbsp;<a target=\"_blank\" href = 'actController.do?resourceRead&processDefinitionId=" + row.id + "&resourceType=xml'  ><u><font color=red >" + value + "</font></u></a>";

		return s;
	}
	
	//查看图片
	function resourceReadImage(value,row,index){
		var s ="";
		
		s = s  + "&nbsp;<a target=\"_blank\" href = 'actController.do?resourceRead&processDefinitionId=" + row.id + "&resourceType=image'  ><u><font color=red >" + value + "</font></u></a>";

		return s;
	}
</script>