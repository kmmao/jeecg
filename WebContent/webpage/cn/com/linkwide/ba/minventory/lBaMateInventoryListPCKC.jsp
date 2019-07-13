<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="lBaMateInventoryList" checkbox="false" pagination="true" fitColumns="true" title="物资库存表" actionUrl="lBaMateInventoryController.do?datagridPCKC" idField="id" fit="true" rowStyler="interlacingColour" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol> 
   <t:dgCol title="仓库"  field="warehouseId"  query="true" hidden="true" queryMode="single"   width="120"></t:dgCol>
   <t:dgCol title="物资"  field="materialId"  query="true"   hidden="true" queryMode="single"   width="120"></t:dgCol>
   <t:dgCol title="仓库"  field="warehouse_name"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="物资编码"  field="material_code"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="物资名称"  field="material_name"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="规格型号"  field="spec_model"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="计量单位"  field="type_name"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="批次"  field="batch_no"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="条码"  field="bar_code"  queryMode="single"  width="120"></t:dgCol> 
   <t:dgCol title="生产日期"  field="make_date"  formatter="yyyy-MM-dd  hh:mm:ss"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="失效日期"  field="expir_date"  formatter="yyyy-MM-dd  hh:mm:ss"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="期初库存"  field="init"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="累计入库"  field="inware"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="累计出库"  field="outware"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="当前库存"  field="num"  queryMode="single"  width="120"></t:dgCol>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar> 
  </t:datagrid>
  </div>
 </div>
 <div style="display:none;">
 	<t:comboList name="warehouseId" url="dictListController.do?wareHouseList&isAssets=0" id="warehouseId" idField="id" textField="warehouseName" ignore="checked" 			
			 field="warehouseCode,warehouseName,id," hasInput="true"	
			 panelWidth="400" title="仓库编码:80,仓库名称:120" ></t:comboList>
	<t:comboList id="materialId"  name="materialId"  url="dictListController.do?mateList" idField="id" textField="materialName" ignore="ignore"
			 field="materialCode,materialName,id,"  hasInput="true"
			 panelWidth="400" title="编码:80,物资名称:120" ></t:comboList>
 </div>
 <script src = "webpage/cn/com/linkwide/ba/minventory/lBaMateInventoryList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'lBaMateInventoryController.do?upload', "lBaMateInventoryList");
}

//导出
function ExportXls() {
	JeecgExcelExport("lBaMateInventoryController.do?exportXls","lBaMateInventoryList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("lBaMateInventoryController.do?exportXlsByT","lBaMateInventoryList");
}

 </script>