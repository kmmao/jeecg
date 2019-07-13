<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="bdAccperiodschemeList" checkbox="true" fitColumns="false" title="会计期间方案" actionUrl="bdAccperiodschemeController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="方案编码"  field="accperiodschemecode"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="方案名称"  field="accperiodschemename"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="删除标志"  field="dr"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="memo"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="时间戳"  field="ts"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="bdAccperiodschemeController.do?doDel&id={id}" />
   <t:dgToolBar title="添加" icon="icon-add" url="bdAccperiodschemeController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="bdAccperiodschemeController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="bdAccperiodschemeController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="bdAccperiodschemeController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/cn/com/linkwide/cost/custom/bdaccperiodscheme/bdAccperiodschemeList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'bdAccperiodschemeController.do?upload', "bdAccperiodschemeList");
}

//导出
function ExportXls() {
	JeecgExcelExport("bdAccperiodschemeController.do?exportXls","bdAccperiodschemeList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("bdAccperiodschemeController.do?exportXlsByT","bdAccperiodschemeList");
}
 </script>