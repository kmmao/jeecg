<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="backTaskDepList" checkbox="true" fitColumns="false" title="后台任务部署" actionUrl="backTaskDepController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="任务名称"  field="taskName"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="描述"  field="taskDesc"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="任务状态 "  field="taskState"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="任务类型"  field="taskType"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="业务插件"  field="plugName"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="执行策略"  field="executWay"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="发生时间"  field="happenTime"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="backTaskDepController.do?doDel&id={id}" />
   <t:dgToolBar title="添加" icon="icon-add" url="backTaskDepController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="backTaskDepController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="backTaskDepController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="backTaskDepController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/cn/com/linkwide/cost/custom/taskcenter/backtask/deploytask/backTaskDepList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#backTaskDepListtb").find("input[name='createDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#backTaskDepListtb").find("input[name='updateDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'backTaskDepController.do?upload', "backTaskDepList");
}

//导出
function ExportXls() {
	JeecgExcelExport("backTaskDepController.do?exportXls","backTaskDepList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("backTaskDepController.do?exportXlsByT","backTaskDepList");
}
 </script>