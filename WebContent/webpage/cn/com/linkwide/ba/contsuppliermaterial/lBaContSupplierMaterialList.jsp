<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">

  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="lBaContSupplierMaterialList" checkbox="true" fitColumns="false"  actionUrl="lBaContSupplierMaterialController.do?datagrid" idField="id" fit="true" rowStyler="interlacingColour" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="供应商"  field="supplierId"  hidden="true"   queryMode="single" query="true"  width="120"></t:dgCol>
   <t:dgCol title="物资"  field="materialIdQ"  hidden="true"   queryMode="single" query="true"  width="120"></t:dgCol>
   <t:dgCol title="物资"  field="materialId"  hidden="true"   queryMode="single" query="false"  width="120"></t:dgCol>
   <t:dgCol title="供应商名称"  field="supplierName"  sortable="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="供应商编码"  field="supplierCode"  sortable="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="物资名称"  field="materialName"  sortable="false"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="物资编码"  field="materialCode"  sortable="false"  queryMode="group"  width="120"></t:dgCol>
   
   <t:dgToolBar title="添加" icon="icon-add" url="lBaContSupplierMaterialController.do?goAdd" funname="adda"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="lBaContSupplierMaterialController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="lBaContSupplierMaterialController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="lBaContSupplierMaterialController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls" ></t:dgToolBar>  
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-comturn" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
  <div style="display:none">
 	<t:comboList name="supplierId" url="dictListController.do?supplierList" id="supplierId" idField="id" textField="supplierFullName" ignore="checked" 
			 field="supplierCode,supplierFullName,id,"  hasInput="true"
			 panelWidth="400" title="供应商编码:80,供应商名称:120" ></t:comboList>
 </div>
 <script src = "webpage/cn/com/linkwide/ba/contsuppliermaterial/lBaContSupplierMaterialList.js"></script>
 <script type="text/javascript">
 $(document).ready(function(){
 });
 

//导入
function ImportXls() {

	createwindowRef("导入", "lBaContSupplierMaterialController.do?upload", 350, 150, "dialogTwo");

}


//导出
function ExportXls() {
	var ids = [];
	var rows = $("#lBaContSupplierMaterialList").datagrid('getSelections');
	if(rows.length==0){
		JeecgExcelExport("lBaContSupplierMaterialController.do?exportXls","lBaContSupplierMaterialList");
	}else{
		for(var i = 0;i<rows.length; i++){
			ids.push(rows[i].id);
		}
		JeecgExcelExport("lBaContSupplierMaterialController.do?exportXls&ids="+ids,"lBaContSupplierMaterialList");
	}
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("lBaContSupplierMaterialController.do?exportXlsByT","lBaContSupplierMaterialList");
}

function adda(title,addurl,gname,width,height) {
	gridname=gname;
	createwindowa(title, addurl,width,height);
}
function createwindowa(title, addurl,width,height) {
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
		    	iframe = this.iframe.contentWindow;
		    	if(saveObja()){
		    		return true;
		    	}
				return false;
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
		    	iframe = this.iframe.contentWindow;
		    	if(saveObja()){
		    		return true;
		    	}
				return false;
		    },
		    cancelVal: '关闭',
		    cancel: true /*为true等价于function(){}*/
		});

	}
    //--author：JueYue---------date：20140427---------for：弹出bug修改,设置了zindex()函数
	
}

/**
* 执行保存
* 
* @param url
* @param gridname
*/
function saveObja() {
addALLSelect("批量保存","lBaContSupplierMaterialController.do?doAdd","lBaSupplierMaterialList") 
return true;
}
function addALLSelect(title,url,gname) {
	gridname=gname;
    var ids = [];
    var supplierId = iframe.$("input[name='supplierId']").val();
    var rows = iframe.$("#"+gname).datagrid('getSelections');
    if (rows.length > 0) {
    	$.dialog.setting.zIndex = getzIndex(true);
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].id);
			}
			$.ajax({
				url : url,
				type : 'post',
				data : {
					ids : ids.join(','),
					supplierId : supplierId
				},
				cache : false,
				success : function(data) {
					var d = $.parseJSON(data);
					if (d.success) {
						var msg = d.msg;
						$("#lBaContSupplierMaterialList").datagrid("load","");
						tip(msg);
						ids='';
					}
				}
			});
	} else {
		/* tip("请选择需要保存的数据"); */
	}
}

 </script>