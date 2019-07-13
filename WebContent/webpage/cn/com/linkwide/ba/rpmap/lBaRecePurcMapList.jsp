<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="lBaRecePurcMapList" checkbox="false" pagination="true" fitColumns="true" title="业务收发类别" actionUrl="lBaRecePurcMapController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="采购类型"  field="purcId" query="true"  queryMode="single"  dictionary="l_ba_purc_type,id,name"  width="120"></t:dgCol>
   <t:dgCol title="收发类型"  field="receiveId"  query="true" queryMode="single"  dictionary="l_ba_receive_type,id,name"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="lBaRecePurcMapController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="添加" icon="icon-add" url="lBaRecePurcMapController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="lBaRecePurcMapController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="lBaRecePurcMapController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="lBaRecePurcMapController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/cn/com/linkwide/ba/rpmap/lBaRecePurcMapList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'lBaRecePurcMapController.do?upload', "lBaRecePurcMapList");
}

//导出
function ExportXls() {
	JeecgExcelExport("lBaRecePurcMapController.do?exportXls","lBaRecePurcMapList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("lBaRecePurcMapController.do?exportXlsByT","lBaRecePurcMapList");
}

 </script>