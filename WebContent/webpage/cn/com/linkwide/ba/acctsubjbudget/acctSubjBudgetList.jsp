<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="acctSubjBudgetList" checkbox="false" pagination="true" fitColumns="true" title="科目项目对应表" actionUrl="acctSubjBudgetController.do?datagrid" idField="id" fit="true" queryMode="group">
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
   <t:dgCol title="科目编码"  field="subjCode" query="true" queryMode="single"  width="120" ></t:dgCol>
   <t:dgCol title="科目名称"  field="subjName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="项目编码"  field="budgetCode" query="true"  queryMode="single"  width="120" ></t:dgCol>
   <t:dgCol title="项目名称"  field="budgetName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留1"  field="vdef1"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留2"  field="vdef2"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留3"  field="vdef3"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留4"  field="vdef4"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留5"  field="vdef5"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="acctSubjBudgetController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="添加" icon="icon-add" url="acctSubjBudgetController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="acctSubjBudgetController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="acctSubjBudgetController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="acctSubjBudgetController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/cn/com/linkwide/ba/acctsubjbudget/acctSubjBudgetList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'acctSubjBudgetController.do?upload', "acctSubjBudgetList");
}

//导出
function ExportXls() {
	JeecgExcelExport("acctSubjBudgetController.do?exportXls","acctSubjBudgetList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("acctSubjBudgetController.do?exportXlsByT","acctSubjBudgetList");
}

 </script>