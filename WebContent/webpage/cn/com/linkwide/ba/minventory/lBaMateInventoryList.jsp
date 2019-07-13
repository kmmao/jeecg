<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="lBaMateInventoryList" checkbox="false" pageSize="20" pagination="true" fitColumns="true" title="物资库存表" actionUrl="lBaMateInventoryController.do?datagrid" idField="id" fit="true" rowStyler="interlacingColour" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="仓库"  field="warehouseId"  hidden="true"   query="true"  queryMode="single"   width="120"></t:dgCol>
   <t:dgCol title="物资"  field="materialId"  hidden="true"  query="true"  queryMode="single"   width="120"></t:dgCol>
   <t:dgCol title="仓库编码"  field="warehouseCode"   queryMode="single"   width="120"></t:dgCol>
   <t:dgCol title="仓库名称"  field="warehousename"   queryMode="single"   width="120"></t:dgCol>
   <t:dgCol title="是否代管"  field="isAgency"   query="true" dictionary="yes_no_int" queryMode="single"   width="120"></t:dgCol>
   <t:dgCol title="物资编码"  field="materialCode"   queryMode="single"    width="120"></t:dgCol>
   <t:dgCol title="物资名称"  field="materialName"   queryMode="single"    width="120"></t:dgCol>
   <t:dgCol title="数量"  field="num"  queryMode="single" align="right"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd  hh:mm:ss"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   
   <%-- <t:dgCol title="单价"  field="unitPrice"  queryMode="single"  width="120"></t:dgCol> --%>
   <t:dgCol title="批号"  field="batchNo"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="物资条码"  field="barCode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="生产日期"  field="makeDate"  formatter="yyyy-MM-dd"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="失效日期"  field="expirDate"  formatter="yyyy-MM-dd"  queryMode="single"  width="120"></t:dgCol>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar> 
   <t:dgToolBar title="打印" icon="icon-print" url="printTableMainController.do?getPrintTable&printCode=name1&tableTitle=仓库物资库存表"  funname="printThisTable"  width="100%" height="100%"></t:dgToolBar>
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
 <!-- 加载打印控件-start -->
<script language="javascript" src="webpage/common/print/LodopFuncs.js"></script>
<object  id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
<embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
</object>
<!-- 加载打印控件-end -->	
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
 
//导出
function ExportXls() {
	JeecgExcelExport("lBaMateInventoryController.do?exportXls","lBaMateInventoryList");
}

 </script>