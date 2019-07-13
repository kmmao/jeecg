<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="lBaWarehouseList" checkbox="true" fitColumns="false" actionUrl="lBaWarehouseController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
  
   <t:dgCol title="仓库编码"  field="warehouseCode"    queryMode="single" query="true"  width="120"></t:dgCol>
   <t:dgCol title="仓库名称"  field="warehouseName"    queryMode="single" query="true"  width="120"></t:dgCol>
    <t:dgCol title="所属机构"  field="orgNames"   queryMode="single"  width="320"></t:dgCol>
   <%-- <t:dgCol title="计入成本"  field="isIncludeCost"  dictionary="whether"  queryMode="single" query="true"  width="120"></t:dgCol> --%>
   <t:dgCol title="资产仓"  field="isAssets"  dictionary="whether"  queryMode="single" query="true"  width="120"></t:dgCol>
   <t:dgCol title="是否货位管理"  field="isLocation"  dictionary="yes_no_int"  queryMode="single" query="true"  width="120"></t:dgCol>
   <%-- <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="lBaWarehouseController.do?doDel&id={id}" /> --%>
   <t:dgToolBar title="添加" icon="icon-add" url="lBaWarehouseController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="lBaWarehouseController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="lBaWarehouseController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="lBaWarehouseController.do?goUpdate" funname="detail"></t:dgToolBar>
   <%-- <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>  --%>
  </t:datagrid>
  </div>
 </div>
 <!-- <script src = "webpage/com/jeecg/warehouse/lBaWarehouseList.js"></script>		 -->
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'lBaWarehouseController.do?upload', "lBaWarehouseList");
}

//导出
function ExportXls() {
	JeecgExcelExport("lBaWarehouseController.do?exportXls","lBaWarehouseList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("lBaWarehouseController.do?exportXlsByT","lBaWarehouseList");
}
 </script>