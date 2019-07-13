<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="lBaMaterialSaveTypeList" checkbox="true" fitColumns="false" title="物资保存方式" actionUrl="lBaMaterialSaveTypeController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="类型名称"  field="typeName"    queryMode="single" query="true"  width="120"></t:dgCol>
   <t:dgCol title="类型编码"  field="typeCode"    queryMode="single" query="true"  width="120"></t:dgCol>
   <t:dgCol title="是否停用"  field="status" dictionary="whether"  queryMode="single" query="true"  width="120"></t:dgCol>
   <%-- <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="lBaMaterialSaveTypeController.do?doDel&id={id}" /> --%>
   <t:dgToolBar title="添加" icon="icon-add" url="lBaMaterialSaveTypeController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="lBaMaterialSaveTypeController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="lBaMaterialSaveTypeController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="lBaMaterialSaveTypeController.do?goUpdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/cn/com/linkwide/ba/material/savetype/lBaMaterialSaveTypeList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
   
 </script>