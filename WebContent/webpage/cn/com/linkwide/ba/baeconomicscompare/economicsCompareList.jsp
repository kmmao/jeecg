<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<style>
	#economicsCompareListForm >span >span >span{width:139px!important;}
</style>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="economicsCompareList" checkbox="true" pagination="true" fitColumns="true" title="经济分类对照关系" actionUrl="economicsCompareController.do?datagrid" idField="id" fit="true" queryMode="group"
  onDblClick="dblDetail" detailUrl="economicsCompareController.do?goUpdate" sortOrder="asc" rowStyler="interlacingColour">
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
   <t:dgCol title="费用项目编码"  field="vcode" query="true"  queryMode="single"  width="140"></t:dgCol>
   <t:dgCol title="费用项目名称"  field="vname"  query="true" queryMode="single"  width="160"></t:dgCol>
   <t:dgCol title="会计科目"  field="acctSubj" dictionary="ba_acct_subj,sub_code,sub_name"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="部门预算经济分类编码"  field="deptEcCode"  queryMode="single"  width="140"></t:dgCol>
   <t:dgCol title="部门预算经济分类名称"  field="deptEcName"  queryMode="single"  width="160"></t:dgCol>
   <t:dgCol title="政府预算经济分类编码"  field="govCode"  queryMode="single"  width="140"></t:dgCol>
   <t:dgCol title="政府预算经济分类名称"  field="govName"  queryMode="single"  width="160"></t:dgCol>
   <t:dgCol title="预留字段1"  field="extend1" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段2"  field="extend2" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段3"  field="extend3" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段4"  field="extend4" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段5"  field="extend5" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段6"  field="extend6" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段7"  field="extend7" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段8"  field="extend8" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段9"  field="extend9" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段10"  field="extend10" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="economicsCompareController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="添加" icon="icon-add" url="economicsCompareController.do?goAdd" funname="add" width="800"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="economicsCompareController.do?goUpdate" funname="update" width="800"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="economicsCompareController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="economicsCompareController.do?goUpdate" funname="detail" width="800"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/cn/com/linkwide/ba/baeconomicscompare/economicsCompareList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'economicsCompareController.do?upload', "economicsCompareList");
}

//导出
function ExportXls() {
	JeecgExcelExport("economicsCompareController.do?exportXls","economicsCompareList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("economicsCompareController.do?exportXlsByT","economicsCompareList");
}

 </script>