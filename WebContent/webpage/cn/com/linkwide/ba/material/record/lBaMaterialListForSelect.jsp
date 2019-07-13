<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div data-options="region:'west',
	title:'分类',
	collapsed:false,
	split:true,
	border:true
	"
	style="width: 150px;overflow: auto;">
		<ul id="lBaMaterialList_Type"></ul>
	</div>
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="lBaMaterialList4Select" checkbox="false" autoLoadData="false" fitColumns="false" title="" actionUrl="lBaMaterialController.do?datagrid&wid=${wid}&sid=${sid}&isHighCons=${isHighCons}" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="物资编码"  field="materialCode"    queryMode="single" query="true"  width="80"></t:dgCol>
   <t:dgCol title="物资名称"  field="materialName"    queryMode="single" query="true"  width="120"></t:dgCol>
   <t:dgCol title="物资分类"  field="materialTypeId" hidden="true"  dictionary="l_ba_material_type,id,type_name"   queryMode="group"  width="80"></t:dgCol>
   <t:dgCol title="规格型号"  field="specModel"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="助记码"  field="monmCode"    queryMode="group"  width="80"></t:dgCol>
   <t:dgCol title="计量单位"  field="materUnitId"  dictionary="l_ba_material_mater_unit,id,type_name"  queryMode="group"  width="60"></t:dgCol>
   <t:dgCol title="器械分类"  field="appaTypeId"  hidden="true"  queryMode="group" dictionary="l_ba_material_appa_type,id,type_name"  width="120"></t:dgCol>
   <t:dgCol title="财务分类"  field="financeTypeId" hidden="true"   queryMode="group" dictionary="l_ba_material_finance_type,id,type_name"  width="120"></t:dgCol>
   <t:dgCol title="国标分类"  field="standTypeId" hidden="true"   queryMode="group" dictionary="l_ba_material_stand_type,id,type_name"  width="120"></t:dgCol>
   <t:dgCol title="是否耗材"  field="isCons" hidden="true"   queryMode="group" dictionary="whether" width="120"></t:dgCol>
   <t:dgCol title="是否资产"  field="isAssets" hidden="true"   queryMode="group" dictionary="whether" width="120"></t:dgCol>
   <t:dgCol title="是否计量"  field="isMater" hidden="true"   queryMode="group" dictionary="whether" width="120"></t:dgCol>
   <t:dgCol title="主供应商"  field="supplierId" hidden="true"   queryMode="group" dictionary="l_ba_supplier,id,supplier_full_name"  width="120"></t:dgCol>
   <t:dgCol title="默认仓库"  field="warehouseId" hidden="true"    queryMode="group" dictionary="l_ba_warehouse,id,warehouse_name"  width="120"></t:dgCol>
   <t:dgCol title="最高库存"  field="maxStock" hidden="true"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="安全库存"  field="safeStock" hidden="true"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="最低库存"  field="minStock" hidden="true"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="是否批次管理"  field="isBatch" hidden="true"   queryMode="group" dictionary="whether" width="120"></t:dgCol>
   <t:dgCol title="是否出库跟踪入库"  field="isOutIn" hidden="true"   queryMode="group" dictionary="whether" width="120"></t:dgCol>
   <t:dgCol title="是否保质期管理"  field="isShelfLife"  hidden="true"  queryMode="group" dictionary="whether" width="120"></t:dgCol>
   <t:dgCol title="保质期"  field="shelfLife" hidden="true"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="预警天数"  field="warningDay"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="是否高值耗材"  field="isHighCons" hidden="true" dictionary="whether"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="是否条形码管理"  field="isBarCode" hidden="true" dictionary="whether"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="条形码"  field="barCode"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="计价方式"  field="valueMethod"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="是否代管"  field="isInstead"  hidden="true"  queryMode="group" dictionary="whether" width="120"></t:dgCol>
  </t:datagrid>
  </div>
 </div>
<script src = "webpage/cn/com/linkwide/ba/material/record/lBaMaterial.js"></script>	
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			
 			$('#lBaMaterialList_Type').tree({
				 method:"POST",
				 lines:true,
				 url:"lBaMaterialTypeController.do?comboTreeForState",
				 onClick: function(node){
					 /* $('#supplierTypeEastPanel').panel("refresh", "lBaSupplierTypeController.do?goDetail&id=" +node.id); */
					  var ids = [];
					 ids.push(node.id);
					 var childList = $(this).tree('getChildren',node.target);
					 $.each(childList,function(index,curr){
						 ids.push(curr.id);
					 });
					 $("#lBaMaterialList4Select").datagrid('load',{
						 materialTypeIds : ids.join(",")
					 })
				},
				onLoadSuccess:function(node,data){
					$("#lBaMaterialList_Type li:eq(0)").find("div:first").addClass("tree-node-selected");
					var n = $("#lBaMaterialList_Type").tree("getSelected");
					var ids = [];
					 ids.push(n.id);
					 var childList = $(this).tree('getChildren',n.target);
					 $.each(childList,function(index,curr){
						 ids.push(curr.id);
					 });
					if(n){
						$('#lBaMaterialList4Select').datagrid('options').url = 'lBaMaterialController.do?datagrid&wid=${wid}&sid=${sid}&field=id,materialCode,materialName,materialTypeId,specModel,monmCode,materUnitId,appaTypeId,financeTypeId,standTypeId,isCons,isAssets,isMater,supplierId,warehouseId,maxStock,safeStock,minStock,isBatch,isOutIn,isShelfLife,shelfLife,warningDay,isHighCons,isBarCode,barCode,valueMethod,isInstead,bartype,';
						$("#lBaMaterialList4Select").datagrid('reload',{
							materialTypeIds : ids.join(",")
					 	})
					} 
				}
			});
 });
 
   
 
 </script>