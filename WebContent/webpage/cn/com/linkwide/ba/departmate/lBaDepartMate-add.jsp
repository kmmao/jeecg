<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <div class="easyui-layout" fit="true">   <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="lBaMateList" checkbox="true" pagination="true" fitColumns="true"  actionUrl="lBaDepartMateController.do?mateDatagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="科室"  field="departId"  hidden="true"  query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="物资"  field="mateId"  hidden="true" query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="物资编码"  field="code" query="false" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="物资名称"  field="name"  queryMode="single"  width="120"></t:dgCol>
   <%-- <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="lBaDepartMateController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="添加" icon="icon-add" url="lBaDepartMateController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="lBaDepartMateController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="lBaDepartMateController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="lBaDepartMateController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar> --%>
  </t:datagrid>
  </div>
 </div>
 <div style="display:none">
 	<t:comboList id="departId"  name="departId" url="dictListController.do?departList" idField="id" textField="departname" ignore="checked" 					 field="orgCode,departname,description,id,orgType,iflater,mobile,address," 		hasInput="true"			 panelWidth="400" title="科室编码:80,科室名称:120" ></t:comboList>
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