<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="lBaWarehouseList4Select" checkbox="false" fitColumns="false" title="仓库表" 
  	actionUrl="lBaWarehouseController.do?datagrid&filterUser=${filterUser}" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="仓库编码"  field="warehouseCode"    queryMode="single" query="true"  width="120"></t:dgCol>
   <t:dgCol title="仓库名称"  field="warehouseName"    queryMode="single" query="true"  width="120"></t:dgCol>
   <t:dgCol title="计入成本"  field="isIncludeCost"  dictionary="whether"  queryMode="single" query="true"  width="120"></t:dgCol>
   <t:dgCol title="资产仓"  field="isAssets"  dictionary="whether"  queryMode="single" query="true"  width="120"></t:dgCol>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/cn/com/linkwide/ba/warehouse/lBaWarehouseList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#lBaWarehouseListtb").find("input[name='createDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#lBaWarehouseListtb").find("input[name='createDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#lBaWarehouseListtb").find("input[name='updateDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#lBaWarehouseListtb").find("input[name='updateDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
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