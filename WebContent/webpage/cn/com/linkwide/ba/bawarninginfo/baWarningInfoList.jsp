<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="baWarningInfoList" checkbox="false" pagination="true" fitColumns="true" title="预警信息" actionUrl="baWarningInfoController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预警源"  field="warningSource"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预警信息"  field="warningInfo"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预警依据"  field="warningBasis"  hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="模块预警单据id"  field="tableId"  hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预警时间"  field="warningDate"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留1"  field="v1" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDefOpt title="马上处理" url="{tableId}&yjbusIds={v1}" urlclass="ace_button" />
   
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/cn/com/linkwide/ba/bawarninginfo/baWarningInfoList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'baWarningInfoController.do?upload', "baWarningInfoList");
}

//导出
function ExportXls() {
	JeecgExcelExport("baWarningInfoController.do?exportXls","baWarningInfoList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("baWarningInfoController.do?exportXlsByT","baWarningInfoList");
}

 </script>