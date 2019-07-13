<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="taskLogList" checkbox="true" fitColumns="false" title="任务执行日志" actionUrl="taskLogController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="任务名称"  field="taskname"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="任务类型"  field="tasktype"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="开始时间"  field="starttime"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="结束时间"  field="endtime"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="耗时"  field="consumeTime"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="结果"  field="workingResult"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="服务器名"  field="servername"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段1"  field="vdef1"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段2"  field="vdef2"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段3"  field="vdef3"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段4"  field="vdef4"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
  <%--  <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="taskLogController.do?doDel&id={id}" />
   <t:dgToolBar title="添加" icon="icon-add" url="taskLogController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="taskLogController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="taskLogController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="taskLogController.do?goUpdate" funname="detail"></t:dgToolBar> --%>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/cn/com/linkwide/cost/custom/taskcenter/tasklog/taskLogList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'taskLogController.do?upload', "taskLogList");
}

//导出
function ExportXls() {
	JeecgExcelExport("taskLogController.do?exportXls","taskLogList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("taskLogController.do?exportXlsByT","taskLogList");
}
 </script>