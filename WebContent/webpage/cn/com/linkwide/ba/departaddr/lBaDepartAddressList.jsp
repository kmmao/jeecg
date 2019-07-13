<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="lBaDepartAddressList" checkbox="false" pagination="true" fitColumns="true" title="科室及送货地址对照表" actionUrl="lBaDepartAddressController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="科室"  field="departId"  query="true"  queryMode="single"  dictionary="t_s_depart,id,departname"  width="120"></t:dgCol>
   <t:dgCol title="地址编码"  field="addressCode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="详细地址"  field="address"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="lBaDepartAddressController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="添加" icon="icon-add" url="lBaDepartAddressController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="lBaDepartAddressController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="lBaDepartAddressController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="lBaDepartAddressController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/cn/com/linkwide/ba/departaddr/lBaDepartAddressList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'lBaDepartAddressController.do?upload', "lBaDepartAddressList");
}

//导出
function ExportXls() {
	JeecgExcelExport("lBaDepartAddressController.do?exportXls","lBaDepartAddressList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("lBaDepartAddressController.do?exportXlsByT","lBaDepartAddressList");
}

 </script>