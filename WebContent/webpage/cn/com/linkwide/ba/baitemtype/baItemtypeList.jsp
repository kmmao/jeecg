<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="baItemtypeList" checkbox="false" pagination="true" fitColumns="true" title="项目大类" actionUrl="baItemtypeController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="流程状态"  field="bpmStatus"  hidden="true"  queryMode="single"  dictionary="bpm_status"  width="120"></t:dgCol>
   <t:dgCol title="项目大类编码"  field="vitemcode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="项目大类名称"  field="vitemname"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="外部系统项目大类编码"  field="voutcode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="外部系统项目大类名称"  field="voutname"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段1"  field="extend1"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段2"  field="extend2"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段3"  field="extend3"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段4"  field="extend4"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段5"  field="extend5"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段6"  field="extend6"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段7"  field="extend7"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段8"  field="extend8"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段9"  field="extend9"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段10"  field="extend10"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="baItemtypeController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="添加" icon="icon-add" url="baItemtypeController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="baItemtypeController.do?goUpdate" funname="update"></t:dgToolBar>
   <%-- <t:dgToolBar title="批量删除"  icon="icon-remove" url="baItemtypeController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar> --%>
   <t:dgToolBar title="查看" icon="icon-search" url="baItemtypeController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/cn/com/linkwide/ba/baitemtype/baItemtypeList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'baItemtypeController.do?upload', "baItemtypeList");
}

//导出
function ExportXls() {
	JeecgExcelExport("baItemtypeController.do?exportXls","baItemtypeList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("baItemtypeController.do?exportXlsByT","baItemtypeList");
}

 </script>