<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 0px; border: 0px">
		<t:datagrid name="havaDealList" rowStyler="interlacingColour" fitColumns="false" title="" actionUrl="actTaskController.do?datagridHave" idField="id" fit="true" queryMode="group">
			<t:dgCol title="当前环节" field="histTask.name" treefield="text" width="100" formatterjs="diagramViewer"></t:dgCol>
			<t:dgCol title="流程定义ID" field="histTask.processDefinitionId" hidden="true"  width="100"></t:dgCol>
			<t:dgCol title="流程名称" field="procDef.name"  width="100"></t:dgCol>
			<t:dgCol title="流程实例ID" field="histTask.processInstanceId" hidden="true" width="100"></t:dgCol>
			<t:dgCol title="任务开始日期" field="histTask.createTime"  width="130" formatterjs="formateDate"></t:dgCol>
			<t:dgCol title="任务结束日期" field="histTask.endTime"  width="130" formatterjs="formateDate"></t:dgCol>
			
		</t:datagrid>
	</div>
</div>
<script src = "webpage/com/buss/activiti/workFlowTools.js"></script>
<script type="text/javascript">
	function formateDate(value,row,index){
		var date = new Date(value);
		return dateFormat(date);
	}
	  
	//流程跟踪
	function diagramViewer(value,row,index){
		var s ="";
		
		s = s  + "&nbsp;<a target=\"_blank\" href = 'diagram-viewer/index.html?processDefinitionId=" + row['histTask.processDefinitionId'] + "&processInstanceId="  + row['histTask.processInstanceId'] +  "'  ><u><font color=red >" + value + "</font></u></a>";

		return s;
	}

</script>