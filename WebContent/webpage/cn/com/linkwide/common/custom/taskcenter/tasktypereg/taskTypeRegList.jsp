<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="taskTypeRegList" checkbox="true" fitColumns="false" title="任务类型注册" actionUrl="taskTypeRegController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="任务名称"  field="taskName"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属模块"  field="belongMod"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="业务插件"  field="businesPlugIn"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="类型"  field="type"    queryMode="single" dictionary="taskType" width="120"></t:dgCol>
   <t:dgCol title="描述"  field="taskDesc"    queryMode="single"   width="120"></t:dgCol>
   <t:dgCol title="部署者"  field="deployer"    queryMode="single"   width="200"></t:dgCol>
   <t:dgCol title="预留字段1"  field="vdef1"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段2"  field="vdef2"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段3"  field="vdef3"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段4"  field="vdef4"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段5"  field="vdef5"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段6"  field="vdef6"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="taskTypeRegController.do?doDel&id={id}" />
   <t:dgToolBar title="添加" icon="icon-add" url="taskTypeRegController.do?goAdd" funname="add" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="taskTypeRegController.do?goUpdate" funname="update" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="taskTypeRegController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="taskTypeRegController.do?goUpdate" funname="detail" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/cn/com/linkwide/cost/custom/taskcenter/tasktypereg/taskTypeRegList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#taskTypeRegListtb").find("input[name='createDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#taskTypeRegListtb").find("input[name='updateDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'taskTypeRegController.do?upload', "taskTypeRegList");
}

//导出
function ExportXls() {
	JeecgExcelExport("taskTypeRegController.do?exportXls","taskTypeRegList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("taskTypeRegController.do?exportXlsByT","taskTypeRegList");
}
 </script>