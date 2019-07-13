<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="baBudgetRecordList" checkbox="true" sortName="billdate" sortOrder="desc" pageSize="500" pagination="true" fitColumns="true" title="预算执行情况表" actionUrl="baBudgetRecordController.do?datagrid" idField="id" fit="true" queryMode="group">
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
   <t:dgCol title="模块名称"  field="mkmc" dictionary="ba_Budget_Record,mkmc,mkmc" query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="菜单名称"  field="cdmc"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="支出类型"  field="zclx"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="执行科室"  field="zxks"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预算科室"  field="ysks"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预算项目编码"  field="ysxmbm"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预算项目名称"  field="ysxmmc"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="支出项目编码"  field="zcxmbm"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="支出项目名称"  field="zcxmmc"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="资金来源"  field="zjly"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="资金性质"  field="zjxz"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="收入金额"  field="v1"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="支出金额"  field="zcje"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="调整金额"  field="tz"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="调减"  field="tj"  hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="凭证号"  field="pzh"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="摘要"  field="v2"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作员"  field="name"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="制单日期"  field="billdate"  formatter="yyyy-MM-dd"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="唯一主键"  field="pkid"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留3"  field="v3"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留4"  field="v4"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留5"  field="v5"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留6"  field="v6"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留7"  field="v7"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留8"  field="v8"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留9"  field="v9"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留10"  field="v10"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="baBudgetRecordController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="添加" icon="icon-add" url="baBudgetRecordController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="baBudgetRecordController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="baBudgetRecordController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="baBudgetRecordController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/cn/com/linkwide/ba/babudgetrecord/baBudgetRecordList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'baBudgetRecordController.do?upload', "baBudgetRecordList");
}

//导出
function ExportXls() {
	JeecgExcelExport("baBudgetRecordController.do?exportXls","baBudgetRecordList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("baBudgetRecordController.do?exportXlsByT","baBudgetRecordList");
}

 </script>