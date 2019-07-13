<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="workFlowCateList" treegrid="true" pagination="false" actionUrl="workFlowCateController.do?datagrid" idField="id"  title="流程分类">
   <t:dgCol title="分类名称" field="name" treefield="text" align="left" width="150"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="workFlowCateController.do?doDel&id={id}" />
   <t:dgToolBar title="添加" icon="icon-add" url="workFlowCateController.do?goAdd" funname="workFlowCate"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="workFlowCateController.do?goUpdate" funname="workFlowCate"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/buss/activiti/workFlowCateList.js"></script>	
 <script type="text/javascript">
	
 	function getSelectNodeId(){
 		var rowData = $('#workFlowCateList').datagrid('getSelected');
		if (rowData) {
			return rowData.id;
		}else{
			return "";
		}
 	}
	 		
	function workFlowCate(title, url, id) {
		var rowData = $('#' + id).datagrid('getSelected');
		if (rowData) {
			url += '&id=' + rowData.id;
		}
		add(title, url, 'workFlowCateList', 400, 340);
	}
</script>