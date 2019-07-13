<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <div class="easyui-layout" fit="true">   <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="lBaSupplierMaterialList" checkbox="true" pagination="true" pageSize="200" fitColumns="true"  actionUrl="lBaContSupplierMaterialController.do?mateDatagrid" idField="id" fit="true" rowStyler="interlacingColour" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="供应商"  field="supplierId"  hidden="true"  query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="物资"  field="mateId"  hidden="true" query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="物资编码"  field="code" query="false" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="物资名称"  field="name"  queryMode="single"  width="120"></t:dgCol>
  </t:datagrid>
  </div>
 </div>
 <div style="display:none">
 	<t:comboList name="supplierId" url="dictListController.do?supplierList" id="supplierId" idField="id" textField="supplierFullName" ignore="checked" 
		 field="supplierCode,supplierFullName,id,"  hasInput="true"
		 panelWidth="400" title="供应商编码:80,供应商名称:120" ></t:comboList>
 </div>
 
 <script src = "webpage/cn/com/linkwide/ba/departmate/lBaDepartMateList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'lBaDepartMateController.do?upload', "lBaDepartMateList");
}

//导出
function ExportXls() {
	JeecgExcelExport("lBaDepartMateController.do?exportXls","lBaDepartMateList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("lBaDepartMateController.do?exportXlsByT","lBaDepartMateList");
}

 </script>