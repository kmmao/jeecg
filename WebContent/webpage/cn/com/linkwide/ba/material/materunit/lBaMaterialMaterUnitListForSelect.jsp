<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="lBaMaterialMaterUnitList4Select" checkbox="true" fitColumns="false" title="计量单位" actionUrl="lBaMaterialMaterUnitController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="计量单位编码"  field="typeCode"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="计量单位名称"  field="typeName"   queryMode="single" query="true"  width="120"></t:dgCol>
   <t:dgCol title="英文名称单数"  field="englishNameSingular"    queryMode="single" query="true" width="120"></t:dgCol>
   <t:dgCol title="英文名称复数"  field="englishNameComplex"    queryMode="single" query="true"  width="120"></t:dgCol>
   <t:dgCol title="是否停用"  field="status" dictionary="whether" queryMode="single" query="true" width="120"></t:dgCol>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/cn/com/linkwide/ba/material/materunit/lBaMaterialMaterUnit.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
 
 </script>