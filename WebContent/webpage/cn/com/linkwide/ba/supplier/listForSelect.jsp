<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<%-- <div id="tempSearch" style="display: none">
		<div id="tempColums">
			<span style="vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 80px;text-align:right;text-overflow:ellipsis;-o-text-overflow:ellipsis; overflow: hidden;white-space:nowrap; " title="供应商编码">
			供应商类型：
			</span>
			<t:comboTree id="supplierTypeId" name="supplierTypeId" url="lBaSupplierTypeController.do?getTreeAllDateForstate" />
		</div></div> --%>
<div class="easyui-layout" fit="true">
<div data-options="region:'west',
	title:'分类',
	collapsed:false,
	split:true,
	border:true
	"
	style="width: 150px;overflow: auto;">
		<ul id="supplier4select_Type"></ul>
	</div>
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="supplier4select" checkbox="false" autoLoadData="false" fitColumns="false" title="供应商" actionUrl="lBaSupplierController.do?datagrid&mid=${mid }" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="供应商编码"  field="supplierCode"    queryMode="single" query="true"  width="80"></t:dgCol>
   <t:dgCol title="供应商名称"  field="supplierFullName"   queryMode="single" query="true" width="120"></t:dgCol>
   <t:dgCol title="供应商简称"  field="supplierShortName"    queryMode="single" query="true"  width="120"></t:dgCol>
   <t:dgCol title="供应商类型"  field="supplierTypeId"  hidden="true"  dictionary="l_ba_supplier_type,id,type_name" queryMode="group" width="120"></t:dgCol>
   <t:dgCol title="联系人"  field="contacts"    queryMode="group"  width="80"></t:dgCol>
   <t:dgCol title="联系电话"  field="telphone"    queryMode="group"  width="80"></t:dgCol>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/cn/com/linkwide/ba/supplier/lBaSupplierList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#lBaSupplierListtb").find("input[name='createDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#lBaSupplierListtb").find("input[name='createDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#lBaSupplierListtb").find("input[name='updateDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#lBaSupplierListtb").find("input[name='updateDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 
 			$('#supplier4select_Type').tree({
				 method:"POST",
				 lines:true,
				 url:"lBaSupplierTypeController/getTreeAllDate.do",
				 onClick: function(node){
					 /* $('#supplierTypeEastPanel').panel("refresh", "lBaSupplierTypeController.do?goDetail&id=" +node.id); */
					 var ids = [];
					 ids.push(node.id);
					 var childList = $(this).tree('getChildren',node.target);
					 $.each(childList,function(index,curr){
						 ids.push(curr.id);
					 });
					 $("#supplier4select").datagrid('load',{
						 supplierTypeIds : ids.join(",")
					 })
				},
				onLoadSuccess:function(node,data){
					$("#supplier4select_Type li:eq(0)").find("div:first").addClass("tree-node-selected");
					var n = $("#supplier4select_Type").tree("getSelected");
					var ids = [];
					 ids.push(n.id);
					 var childList = $(this).tree('getChildren',n.target);
					 $.each(childList,function(index,curr){
						 ids.push(curr.id);
					 });
					if(n){
						$('#supplier4select').datagrid('options').url = 'lBaSupplierController.do?datagrid&mid=${mid }&field=id,supplierCode,supplierFullName,supplierShortName,supplierTypeId,contacts,telphone,address,isControl';
						$("#supplier4select").datagrid('reload',{
							supplierTypeIds : ids.join(",")
					 	})
					} 
				}
			});
 });
 
 
 </script>