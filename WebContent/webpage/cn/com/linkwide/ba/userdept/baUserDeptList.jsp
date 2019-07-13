<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="baUserDeptList" checkbox="false" pagination="true" fitColumns="true" title="用户科室对照" actionUrl="baUserDeptController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="用户编码"  field="userCode" query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="用户名称"  field="userName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="科室编码"  field="deptCode"  query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="科室名称"  field="deptName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="baUserDeptController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="添加" icon="icon-add" url="baUserDeptController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="baUserDeptController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="baUserDeptController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="baUserDeptController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <div style="display:none">
 	 <t:comboList id="deptCode"  name="deptCode" url="dictListController.do?departList" idField="orgCode" textField="departname" ignore="checked" 					 
 	 field="orgCode,departname,description,id,orgType,iflater,mobile,address," 		hasInput="true"			 panelWidth="400" title="科室编码:80,科室名称:120" ></t:comboList>
 	
 	<t:comboList id="userCode"  name="userCode"  url="dictListController.do?userList" idField="userName" textField="realName"  
	  hasInput="true"  field="userName,realName,id," panelWidth="400" title="编码:80,用户名称:120" ></t:comboList>
 </div>
 <script src = "webpage/cn/com/linkwide/ba/userdept/baUserDeptList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'baUserDeptController.do?upload', "baUserDeptList");
}

//导出
function ExportXls() {
	JeecgExcelExport("baUserDeptController.do?exportXls","baUserDeptList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("baUserDeptController.do?exportXlsByT","baUserDeptList");
}

 </script>