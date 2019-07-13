<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="tSCompanyList" checkbox="false" pagination="true" fitColumns="true" title="单位" sortName="createDate" actionUrl="tSCompanyController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="公司编码"  field="companyCode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="公司名称"  field="companyName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="英文名称"  field="englishName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="简称"  field="shortName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="单位描述"  field="companyDescribe"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="管理员"  field="administrator"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="单位类型"  field="companyType"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="法人"  field="legalPerson"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="上级单位"  field="parentId"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="上级主管部门编码"  field="parentOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所在省"  field="provice"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所在市"  field="city"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所在区"  field="area"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="经度"  field="coordinateX"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="纬度"  field="coordinateY"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="tSCompanyController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="tSCompanyController.do?goAdd" funname="add"></t:dgToolBar>
	<t:dgToolBar title="编辑" icon="icon-edit" url="tSCompanyController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="tSCompanyController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="tSCompanyController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'tSCompanyController.do?upload', "tSCompanyList");
}

//导出
function ExportXls() {
	JeecgExcelExport("tSCompanyController.do?exportXls","tSCompanyList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("tSCompanyController.do?exportXlsByT","tSCompanyList");
}

 </script>
