<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 0px; border: 0px">
		<t:datagrid name="actProcessRunningList"  rowStyler="interlacingColour" checkbox="true" fitColumns="false" title="运行中流程" actionUrl="processInstanceController.do?datagrid" idField="id" 	fit="true" queryMode="group">
			<t:dgCol title="执行Id" field="id" queryMode="single" query="false" width="120"></t:dgCol>
			<t:dgCol title="流程实例Id" field="processInstanceId" queryMode="single" query="false" width="80"></t:dgCol>
			<t:dgCol title="流程定义ID" field="processDefinitionId" queryMode="single" query="true" width="120"></t:dgCol>
			<t:dgCol title="当前节点" field="activityId" queryMode="single" query="false" width="120"></t:dgCol>
			<t:dgCol title="是否挂起" field="suspensionState" queryMode="single" query="false" width="80" replace="挂起_2,激活_1"></t:dgCol>
			<t:dgCol title="关联单据" field="businessKey" hidden="true" ></t:dgCol>
			<t:dgCol title="操作" field="opt" queryMode="single" query="false" width="120" formatterjs="contrApply" ></t:dgCol>
		</t:datagrid>
	</div>
</div>

<script type="text/javascript">
//操作处理
function  contrApply(value,row,index){
	 var businessKey = row.businessKey;
	 var url =  "<a href='#' onclick='viewBusy(&quot;"+businessKey+"&quot;)'>[关联单据]</a> &nbsp;&nbsp;&nbsp;";
	 return url;
}
//查看流程进度
function viewBusy(id){
	/* var url = "actTaskController.do?viewBusy"
			+"&businessKey="+businessKey;
	var buttons = [];
	openDefWindow(url,"关联单据",700,650,buttons); */
}
</script>