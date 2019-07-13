<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="lBaMaterialMeasureUnitList" checkbox="true" fitColumns="false" title="辅助计量单位" actionUrl="lBaMaterialMeasureUnitController.do?datagrid&materialId=${lBaMaterialId }" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="物资id"  field="materialId"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="计量单位"  field="materUnitId"  dictionary="l_ba_material_mater_unit,id,type_name"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="换算率"  field="converRate"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="createDate" formatter="yyyy-MM-dd"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="lBaMaterialMeasureUnitController.do?doDel&id={id}" />
   <t:dgToolBar title="添加" icon="icon-add" url="lBaMaterialMeasureUnitController.do?goAdd&materialId=${lBaMaterialId }" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="lBaMaterialMeasureUnitController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="lBaMaterialMeasureUnitController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="lBaMaterialMeasureUnitController.do?goUpdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/cn/com/linkwide/ba/material/measureunit/lBaMaterialMeasureUnitList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#lBaMaterialMeasureUnitListtb").find("input[name='createDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#lBaMaterialMeasureUnitListtb").find("input[name='createDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#lBaMaterialMeasureUnitListtb").find("input[name='updateDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#lBaMaterialMeasureUnitListtb").find("input[name='updateDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
   
 </script>