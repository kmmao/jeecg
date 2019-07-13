<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="departList" title="common.department.list" checkbox="true" openFirstNode="true" fitColumns="true" actionUrl="departController.do?departgrid" treegrid="true" idField="departid" pagination="false" queryMode="group">
            <t:dgCol title="common.id" field="id" treefield="id" hidden="true"></t:dgCol>
            <t:dgCol title="科室编码" field="orgCode" query="false"  queryMode="single" treefield="fieldMap.orgCode" width="80"></t:dgCol>
            <t:dgCol title="科室名称" field="departname" query="false"  queryMode="single" treefield="text" align="left" width="200"></t:dgCol>
            <t:dgCol title="common.org.type" field="orgType" dictionary="orgtype" treefield="fieldMap.orgType" width="80"></t:dgCol>
            <t:dgCol title="是否末级" field="iflater" dictionary="iflater"  treefield="fieldMap.iflater" width="70"></t:dgCol>
            <t:dgCol title="核算属性" field="pkSharecl" dictionary="hssx"  treefield="fieldMap.pkSharecl" width="100"></t:dgCol>
            <t:dgCol title="科室分类" field="pkSharecl" dictionary="depttype"  treefield="fieldMap.depttype" width="100"></t:dgCol>
            <t:dgCol title="科室国际编码" field="deptinternationalcode" treefield="fieldMap.badepartmentalcontrol.deptinternationalcode" width="120"></t:dgCol>
            <t:dgCol title="科室国际名称" field="deptinternationalname" treefield="fieldMap.badepartmentalcontrol.deptinternationalname" width="120"></t:dgCol>
            <t:dgCol title="科室HIS编码" field="depthiscode" treefield="fieldMap.badepartmentalcontrol.depthiscode" width="120"></t:dgCol>
            <t:dgCol title="科室HIS名称" field="depthisname" treefield="fieldMap.badepartmentalcontrol.depthisname" width="120"></t:dgCol>
            <t:dgCol title="预留字段1" field="reservedfields1" treefield="fieldMap.badepartmentalcontrol.reservedfields1" width="120"></t:dgCol>
            <t:dgCol title="预留字段2" field="reservedfields2" treefield="fieldMap.badepartmentalcontrol.reservedfields2" width="120"></t:dgCol>
        	<t:dgToolBar title="编辑" icon="icon-edit" url="baDepartmentalControlController.do?goUpdate" funname="update"></t:dgToolBar>
   			<t:dgToolBar title="查看" icon="icon-search" url="baDepartmentalControlController.do?goUpdate" funname="detail"></t:dgToolBar>
        	<t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
		   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
		   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
        </t:datagrid>
  </div>
 </div>
 <script src = "webpage/cn/com/linkwide/ba/badepartmentalcontrol/baDepartmentalControlList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'baDepartmentalControlController.do?upload', "baDepartmentalControlList");
}

//导出
function ExportXls() {
	JeecgExcelExport("baDepartmentalControlController.do?exportXls","baDepartmentalControlList");
}

//模板下载	
function ExportXlsByT() {
	window.location.href = "baDepartmentalControlController.do?exportXlsByT";
	//JeecgExcelExport("baDepartmentalControlController.do?exportXlsByT","baDepartmentalControlList");
}

 </script>