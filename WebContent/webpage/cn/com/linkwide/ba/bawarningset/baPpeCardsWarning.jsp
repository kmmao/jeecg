<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="baWarningSetList" checkbox="false" pagination="true" fitColumns="true" title="固定资产到期预警明细" actionUrl="baWarningSetController.do?datagrid1&yjbusIds=${yjbusIds }" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="资产编号"  field="assetCoding"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="资产名称"  field="assetName" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="规格型号"  field="specModel"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="开始使用日期"  field="startSate"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="使用年限（月）"  field="serviceLifeMonth"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="使用科室"  field="department" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="管理科室"  field="departname"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="生产厂家"  field="supplierCode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="品牌"  field="barnd" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <input id="yjbusIds" name="yjbusIds" type="hidden" value="${yjbusIds }"/>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/cn/com/linkwide/ba/bawarningset/baWarningSetList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
	
 });
 

 </script>