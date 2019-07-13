<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="lBaMateInventoryLogList" checkbox="true" pageSize="50" pagination="true" fitColumns="true" actionUrl="lBaMateInventoryLogController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="库存表id"  field="inventoryId"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   
   <t:dgCol title="操作时间"  field="logDate"  formatter="yyyy-MM-dd hh:mm:ss"  query="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="仓库"  field="warehouseId" sortable="false"  hidden="true"  query="true"  queryMode="single"   width="120"></t:dgCol>
   <t:dgCol title="仓库"  field="warehouseName" sortable="false" queryMode="single"    width="120"></t:dgCol>
   <t:dgCol title="物资"  field="materialId" sortable="false"  hidden="true"  query="true"  queryMode="single"    width="120"></t:dgCol>
   <t:dgCol title="物资"  field="materialName" sortable="false" queryMode="single"     width="120"></t:dgCol>
   <t:dgCol title="批次"  field="batchNo" sortable="false" query="true"  queryMode="single"   width="120"></t:dgCol>
   <t:dgCol title="有效期"  field="expirDate" sortable="false" formatter="yyyy-MM-dd" query="true" queryMode="single"  width="120"></t:dgCol>
   
   <t:dgCol title="变更数量"  field="num" sortable="false" queryMode="single" align="right"  width="120"></t:dgCol>
   <t:dgCol title="剩余库存"  field="nowNum" sortable="false" queryMode="single"  align="right" width="120"></t:dgCol>
   <t:dgCol title="单据号"  field="billNo" sortable="false" query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="单据类型"  field="billType" sortable="false" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作人"  field="createBy" sortable="false" query="true"  queryMode="single"  dictionary="t_s_base_user,id,realname"   width="120"></t:dgCol>
   <t:dgCol title="是否代管"  field="isAgency"   query="true" dictionary="yes_no_int" queryMode="single"   width="120"></t:dgCol>
  <%--  <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="lBaMateInventoryLogController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="添加" icon="icon-add" url="lBaMateInventoryLogController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="lBaMateInventoryLogController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="lBaMateInventoryLogController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="lBaMateInventoryLogController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar> --%>
    <t:dgToolBar title="打印" icon="icon-print" url="printTableMainController.do?getPrintTable&printCode=name1&tableTitle=仓库物资库存表"  funname="printThisTable"  width="100%" height="100%"></t:dgToolBar>
    <t:dgToolBar title="导出" icon="icon-putout" url="printTableMainController.do?getPrintTable&printCode=name1&tableTitle=仓库物资库存表"  funname="exportThisTable"  width="100%" height="100%"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
  <div style="display:none;">
<t:comboList name="warehouseId" url="dictListController.do?wareHouseList&isAssets=0" id="warehouseId" idField="id" textField="warehouseName" 		
 	field="warehouseCode,warehouseName,id," 	hasInput="true"	 panelWidth="400" title="仓库编码:80,仓库名称:120" ></t:comboList>
 <t:comboList id="materialId"  name="materialId"  url="dictListController.do?mateList" idField="id" textField="materialName" ignore="ignore"
						 field="materialCode,materialName,id,"  hasInput="true"
						 panelWidth="400" title="编码:80,物资名称:120" ></t:comboList>
</div>
 <script src = "webpage/cn/com/linkwide/ba/inventorylog/lBaMateInventoryLogList.js"></script>		
 <!-- 加载打印控件-start -->
<script language="javascript" src="webpage/common/print/LodopFuncs.js"></script>
<object  id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
<embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
</object>
<!-- 加载打印控件-end -->
 <script type="text/javascript">
 $(document).ready(function(){
 });
 

 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'lBaMateInventoryLogController.do?upload', "lBaMateInventoryLogList");
}

//导出
function ExportXls() {
	JeecgExcelExport("lBaMateInventoryLogController.do?exportXls","lBaMateInventoryLogList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("lBaMateInventoryLogController.do?exportXlsByT","lBaMateInventoryLogList");
}

 </script>