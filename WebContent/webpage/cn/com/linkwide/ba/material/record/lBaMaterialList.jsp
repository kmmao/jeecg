<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div data-options="region:'west',
	collapsed:false,
	split:true,
	border:false
	"
	style="width: 150px;">
		<ul id="lBaMaterialList_Type"></ul>
	</div>
	<input type="hidden" id="nodeId">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="lBaMaterialList" checkbox="true" autoLoadData="false" fitColumns="false" title="" actionUrl="lBaMaterialController.do?datagridAll" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="物资编码"  field="materialCode"    queryMode="single" query="true"  width="120"></t:dgCol>
   <t:dgCol title="物资名称"  field="materialName"    queryMode="single" query="true"  width="120"></t:dgCol>
   <t:dgCol title="物资分类"  field="materialTypeId" dictionary="l_ba_material_type,id,type_name"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="规格型号"  field="specModel"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="助记码"  field="monmCode"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="计量单位"  field="materUnitId"  dictionary="l_ba_material_mater_unit,id,type_name"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="器械分类"  field="appaTypeId"    queryMode="group" dictionary="l_ba_material_appa_type,id,type_name"  width="120"></t:dgCol>
   <t:dgCol title="财务分类"  field="financeTypeId"    queryMode="group" dictionary="l_ba_material_finance_type,id,type_name"  width="120"></t:dgCol>
   <t:dgCol title="国标分类"  field="standTypeId"    queryMode="group" dictionary="l_ba_material_stand_type,id,type_name"  width="120"></t:dgCol>
   <t:dgCol title="是否耗材"  field="isCons"    queryMode="group" dictionary="whether" width="120"></t:dgCol>
   <t:dgCol title="是否资产"  field="isAssets"    queryMode="group" dictionary="whether" width="120"></t:dgCol>
   <t:dgCol title="是否计量"  field="isMater"    queryMode="group" dictionary="whether" width="120"></t:dgCol>
   <t:dgCol title="主供应商"  field="supplierId"    queryMode="group" dictionary="l_ba_supplier,id,supplier_full_name"  width="120"></t:dgCol>
   <t:dgCol title="默认仓库"  field="warehouseId"    queryMode="group" dictionary="l_ba_warehouse,id,warehouse_name"  width="120"></t:dgCol>
   <t:dgCol title="最高库存"  field="maxStock"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="安全库存"  field="safeStock"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="最低库存"  field="minStock"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="预估单价"  field="unitPrice"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="是否批次管理"  field="isBatch"    queryMode="group" dictionary="whether" width="120"></t:dgCol>
   <%-- <t:dgCol title="是否出库跟踪入库"  field="isOutIn"    queryMode="group" dictionary="whether" width="120"></t:dgCol> --%>
   <t:dgCol title="是否保质期管理"  field="isShelfLife"    queryMode="group" dictionary="whether" width="120"></t:dgCol>
  <%--  <t:dgCol title="保质期"  field="shelfLife"    queryMode="group"  width="120"></t:dgCol> --%>
  <%--  <t:dgCol title="预警天数"  field="warningDay"    queryMode="group"  width="120"></t:dgCol> --%>
   <t:dgCol title="是否高值耗材"  field="isHighCons"  dictionary="whether"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="是否条形码管理"  field="isBarCode" dictionary="whether"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="条形码类型"  field="bartype"  dictionary="barType"  width="120"></t:dgCol>
<%--    <t:dgCol title="计价方式"  field="valueMethod"   dictionary="whether"   queryMode="group"  width="120"></t:dgCol> --%>
   <t:dgCol title="是否代管"  field="isInstead"    queryMode="group" dictionary="whether" width="120"></t:dgCol>
   <%-- <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="lBaMaterialController.do?doDel&id={id}" /> --%>
   <t:dgToolBar title="添加" icon="icon-add" url="lBaMaterialController.do?goAdd" funname="addFun" width="1000" height="500"></t:dgToolBar>
  <%--   <t:dgToolBar title="中标物资维护" icon="icon-add" url="lBaMaterialController.do?goAddbid" funname="addFun" width="1000" height="500"></t:dgToolBar> --%>
   <t:dgToolBar title="编辑" icon="icon-edit" url="lBaMaterialController.do?goUpdate" funname="update" width="1000" height="500"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="lBaMaterialController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="lBaMaterialController.do?goUpdate" funname="detail" width="1000" height="400"></t:dgToolBar>
   <t:dgToolBar title="分项维护" icon="icon-edit" url="lBaMaterialController.do?goBaseInfo" funname="updateOther" width="800" height="500"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="更新式导入" icon="icon-put" funname="ImportXls2"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
   
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
			 if($('#lBaMaterialList_Type').tree("isLeaf",node.target)){
				 $("#nodeId").val(node.id);
			 }else{
				 $("#nodeId").val("");
			 }
			 
			 var ids = [];
			 ids.push(node.id);
			 var childList = $(this).tree('getChildren',node.target);
			 $.each(childList,function(index,curr){
				 ids.push(curr.id);
			 });
			 /* $('#supplierTypeEastPanel').panel("refresh", "lBaSupplierTypeController.do?goDetail&id=" +node.id); */
			 $("#lBaMaterialList").datagrid('load',{
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
				/* $("#nodeId").val(n.id); */
				$('#lBaMaterialList').datagrid('options').url = 'lBaMaterialController.do?datagridAll&field=id,materialCode,materialName,materialTypeId,specModel,monmCode,materUnitId,appaTypeId,financeTypeId,standTypeId,isCons,isAssets,isMater,supplierId,warehouseId,maxStock,safeStock,minStock,isBatch,isOutIn,isShelfLife,shelfLife,warningDay,isHighCons,isBarCode,barCode,valueMethod,isInstead,unitPrice,bartype';
				$("#lBaMaterialList").datagrid('reload',{
					materialTypeIds : ids.join(",")
			 	})
			} 
		}
	});
 });
 
 function addFun(title,addurl,gname,width,height){
	 var nodeId = $("#nodeId").val();
	 if(nodeId == ""){
		tip("请选择叶子节点添加数据"); 
		return;
	 }
	 add(title,addurl+"&materialTypeId="+nodeId,gname,width,height);
 }
 
//导入
 function ImportXls() {
 	createwindowRef("导入", "lBaMaterialController.do?upload", 350, 150, "dialogTwo");
 }
 
 //更新式导入
 function ImportXls2() {
	 createwindowRef('Excel导入', 'lBaMaterialController.do?updateUpload',  350, 150, "dialogTwo");
 	//createwindowRef("导入", "lBaMaterialController.do?updateUpload", 350, 150, "dialogTwo");
 }

 //导出
 function ExportXls() {
	 var ids = [];
	 var rows = $("#lBaMaterialList").datagrid('getSelections');
	 for ( var i = 0; i < rows.length; i++) {
		ids.push(rows[i].id);
	 }
	JeecgExcelExport("lBaMaterialController.do?exportXls&ids="+ids,"lBaMaterialList");
 }

 //模板下载
 function ExportXlsByT() {
 	JeecgExcelExport("lBaMaterialController.do?exportXlsByT","lBaMaterialList");
 }
 
 
 /**
  * 更新事件打开窗口
  * @param title 编辑框标题
  * @param addurl//目标页面地址
  * @param id//主键字段
  */

 function updateOther(title,url, id,width,height,isRestful) {
 	gridname=id;
 	var rowsData = $('#'+id).datagrid('getSelections');
 	if (!rowsData || rowsData.length==0) {
 		tip('请选择编辑项目');
 		return;
 	}
 	if (rowsData.length>1) {
 		tip('请选择一条记录再编辑');
 		return;
 	}
 	if(isRestful!='undefined'&&isRestful){
 		url += '/'+rowsData[0].id;
 	}else{
 		url += '&id='+rowsData[0].id;
 	}
 	createwindowOther(title,url,width,height);
 }
 /**
  * 创建添加或编辑窗口
  * 
  * @param title
  * @param addurl
  * @param saveurl
  */
 function createwindowOther(title, addurl,width,height) {
 	width = width?width:700;
 	height = height?height:400;
 	if(width=="100%" || height=="100%"){
 		width = window.top.document.body.offsetWidth;
 		height =window.top.document.body.offsetHeight-100;
 	}
     //--author：JueYue---------date：20140427---------for：弹出bug修改,设置了zindex()函数
 	if(typeof(windowapi) == 'undefined'){
 		$.dialog({
 			content: 'url:'+addurl,
 			lock : true,
 			zIndex: getzIndex(),
 			width:width,
 			height:height,
 			title:title,
 			opacity : 0.3,
 			cache:false,
 		    ok: function(){
 		    	window.close();
 		    },
 		    cancelVal: '关闭',
 		    cancel: true /*为true等价于function(){}*/
 		});
 	}else{

 		/*W.*/$.dialog({//使用W，即为使用顶级页面作为openner，造成打开的次级窗口获取不到关联的主窗口
 			content: 'url:'+addurl,
 			lock : true,
 			width:width,
 			zIndex:getzIndex(),
 			height:height,
 			parent:windowapi,
 			title:title,
 			opacity : 0.3,
 			cache:false,
 		    ok: function(){
 		    	window.close();
 		    },
 		    cancelVal: '关闭',
 		    cancel: true /*为true等价于function(){}*/
 		});

 	}
     //--author：JueYue---------date：20140427---------for：弹出bug修改,设置了zindex()函数
 	
 }
 
 </script>