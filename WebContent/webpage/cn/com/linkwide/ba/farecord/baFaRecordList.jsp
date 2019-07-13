<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="baFaRecordList" checkbox="true" pagination="true" fitColumns="true" title="扣减预算操作记录插入规则" actionUrl="baFaRecordController.do?datagrid" idField="id" fit="true" queryMode="group">
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
   <t:dgCol title="模块名称"  field="modular"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="支出类型"  field="vitemtype"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="项目编码"  field="vitemcode"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="项目名称"  field="vitemname"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="明细编码"  field="detailcode"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="明细名称"  field="detailname"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扣减金额"  field="money"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作员"  field="creatname"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="日期"  field="creatdate"  formatter="yyyy-MM-dd"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="业务方式"  field="structures"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段1"  field="v1"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段2"  field="v2"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段3"  field="v3"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段4"  field="v4"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段5"  field="v5"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段6"  field="v6"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段7"  field="v7"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段8"  field="v8"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段9"  field="v9"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段10"  field="v10"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="baFaRecordController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="添加" icon="icon-add" url="baFaRecordController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="baFaRecordController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="baFaRecordController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="baFaRecordController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/cn/com/linkwide/ba/farecord/baFaRecordList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'baFaRecordController.do?upload', "baFaRecordList");
}

//导出
function ExportXls() {
	JeecgExcelExport("baFaRecordController.do?exportXls","baFaRecordList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("baFaRecordController.do?exportXlsByT","baFaRecordList");
}

 </script>