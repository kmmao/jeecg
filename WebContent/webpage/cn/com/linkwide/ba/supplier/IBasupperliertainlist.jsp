<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div data-options="region:'west',
	collapsed:false,
	split:true,
	border:false
	"
	style="width: 0px; overflow: hidden;">
		<ul id="lBaSupplierList_Type"></ul>
	</div>
	<input type="hidden" id="nodeId">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="lBaSupplierList" checkbox="true" fitColumns="false" autoLoadData="false" title="供应商" actionUrl="lBaSupplierTypeController.do?datagridAll" idField="id" fit="true" rowStyler="interlacingColour" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="供应商名称"  field="supplierFullName"   queryMode="single" query="true" width="120" ></t:dgCol>
   <t:dgCol title="社会统一信用码"  field="uscc"    queryMode="single" query="true"  width="120" ></t:dgCol>
   <%-- <t:dgCol title="供应商简称"  field="supplierShortName"  queryMode="single" query="true"  width="120"></t:dgCol> --%>
   <t:dgCol title="联系人"  field="contacts"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="联系电话"  field="telphone"    queryMode="group"  width="120"></t:dgCol>
  <%--  <t:dgCol title="地址"  field="address"    queryMode="group"  width="120"></t:dgCol> --%>
  <%--  <t:dgCol title="审核状态 "  field="auditingState" queryMode="group"   width="120"></t:dgCol> --%>
   <%-- <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="lBaSupplierController.do?doDel&id={id}" /> --%>
   <%-- <t:dgToolBar title="添加" icon="icon-add" url="lBaSupplierTypeController.do?runADD" funname="addFun"></t:dgToolBar> --%>
   <t:dgToolBar title="编辑" icon="icon-edit" url="lBaSupplierController.do?goUpdatebid" funname="update"></t:dgToolBar>
   <%-- <t:dgToolBar title="批量删除"  icon="icon-remove" url="lBaSupplierController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar> --%>
   <t:dgToolBar title="查看" icon="icon-search" url="lBaSupplierController.do?goUpdate" funname="detail"></t:dgToolBar>
   <%-- <t:dgToolBar title="审核" icon="icon-ok" url="lBaSupplierController.do?doCheck" funname="end"></t:dgToolBar>
    --%>
    <%-- <t:dgToolBar title="停用" icon="l-btn-text icon-undo l-btn-icon-left" url="lBaSupplierController.do?doStop" funname="stop" ></t:dgToolBar>
    <t:dgToolBar title="启用" icon="l-btn-text icon-undo l-btn-icon-left" url="lBaSupplierController.do?doStart" funname="start" ></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>  --%>
  </t:datagrid>
  </div>
 </div>
 <!-- <script src = "webpage/cn/com/linkwide/ba/supplier/IBasupperliertainlist.js"></script>		 -->
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#lBaSupplierListtb").find("input[name='createDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#lBaSupplierListtb").find("input[name='createDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#lBaSupplierListtb").find("input[name='updateDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#lBaSupplierListtb").find("input[name='updateDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 
 			$('#lBaSupplierList_Type').tree({
 				 method:"POST",
 				 lines:true,
 				 url:"lBaSupplierTypeController/getTreeAllDateForstate.do",
 				 onClick: function(node){
 					 /* $('#supplierTypeEastPanel').panel("refresh", "lBaSupplierTypeController.do?goDetail&id=" +node.id); */
 					 if($(this).tree("isLeaf",node.target)){
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
 					 
 					 $("#lBaSupplierList").datagrid('load',{
 						supplierTypeIds : ids.join(",")
 					 });
 				},
 				onLoadSuccess:function(node,data){
 					$("#lBaSupplierList_Type li:eq(0)").find("div:first").addClass("tree-node-selected");
 					var n = $("#lBaSupplierList_Type").tree("getSelected");
 					
 					var ids = [];
					 ids.push(n.id);
					 var childList = $(this).tree('getChildren',n.target);
					 $.each(childList,function(index,curr){
						 ids.push(curr.id);
					 });
 					
 					if(n){
 						$("#nodeId").val(n.id);
 						$('#lBaSupplierList').datagrid('options').url = 'lBaSupplierTypeController.do?datagridAll&field=id,id_begin,id_end,uscc,supplierFullName,supplierShortName,supplierTypeId,supplierTypeId_begin,supplierTypeId_end,contacts,contacts_begin,contacts_end,telphone,telphone_begin,telphone_end,address,address_begin,address_end,isControl,auditingState,auditingState_begin,auditingState_end,isCease,isCease_begin,isCease_end,';
 						$("#lBaSupplierList").datagrid('reload',{
 							supplierTypeIds : ids.join(",")
 					 	})
 					} 
 				}
 			});
 	
 });
//编辑前的校验
 function beforeUpdate(title,url,id,gname,width,height) {
 	 //勾选的数据
 	 var rowsData = $('#'+id).datagrid('getSelections');
 	 if(rowsData && rowsData.length==1){ 
 		//审核状态
 		 var auditingState = rowsData[0].auditingState;
 		//是否停用
 		var isCease=rowsData[0].isCease;
 		 if(auditingState=="审核"){
 			 tip("该单据已审核，不能编辑");
 		 }else if(isCease=="1"){
 			 tip("该单据已停用，不能编辑");
 		 }else{
 			gridname=gname;
 			url+= '&id=' +rowsData[0].id;
 			createwindow(title, url,width,height);
 		 }
 	 }else{
 		 tip("请选择一条单据");
 	 }
  }
 function addFun(title,addurl,gname,width,height){
	 var nodeId = $("#nodeId").val();
	 if(nodeId == ""){
		tip("请选择叶子节点添加数据"); 
		return;
	}
	 add(title,addurl+"&supplierTypeId="+nodeId,gname,width,height);
 }
 /*  $(function(){
	//添加分类
	 	$("#lBaSupplierListtb").find("div[name='searchColums']").find("form#lBaSupplierListForm").append("&nbsp;&nbsp;&nbsp");
	 	$("#lBaSupplierListtb").find("div[name='searchColums']").find("form#lBaSupplierListForm").append($("#tempColums").html());
	 	$("#tempSearch").html("");
	 	$("#lBaSupplierListtb").find("div[name='searchColums']").find("form#lBaSupplierListForm").find(".combo")[0].remove();
  }); */
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'lBaSupplierController.do?upload', "lBaSupplierList");
}

//导出
function ExportXls() {
	JeecgExcelExport("lBaSupplierController.do?exportXls","lBaSupplierList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("lBaSupplierController.do?exportXlsByT","lBaSupplierList");
}
 //审核       
function end(title, url,id,width,height) {
	gridname=id;
	var rowsData = $('#'+id).datagrid('getSelections');
	 if (!rowsData || rowsData.length == 0) {
		tip('请选择供应商');
		return;
	} 
	var ids=[];
	var len=rowsData.length;
	for(var i=0,a=len;i<a;i++){
		ids.push(rowsData[i].id);
	}
    url += '&ids='+ids;
	$.ajax({
		url : url,
		type : 'post',
		dataType : 'json',
		cache : false,
		success : function(data) {
			var msg = data.msg;
			tip(msg);
			reloadTable();
			$("#" + gridname).datagrid('unselectAll');
		}
	});
}
//停用 
function stop(title, url,id,width,height) {
	gridname=id;
	var rowsData = $('#'+id).datagrid('getSelections');

	
	if (!rowsData || rowsData.length == 0) {
		tip('请选择供应商');
		return;
	}
    var ids=[];
	var len=rowsData.length;
	for(var i=0,a=len;i<a;i++){
		ids.push(rowsData[i].id);
	}
    url += '&ids='+ids;
	$.ajax({
		url : url,
		type : 'post',
		dataType : 'json',
		cache : false,
		success : function(data) {
			var msg = data.msg;
			tip(msg);
			reloadTable();
			$("#" + gridname).datagrid('unselectAll');
		}
	});
}
function setvalue(row,data){
	 $("#supplierfullname").val(data.supplierfullname);
	 $("#telphone").val(data.telphone);
	 $("#contacts").val(data.contacts);
	 $("select[name='vendor']").val(data.vendor); 
	 $("#demandCode").val(data.billNo);
	 materialNameChnagel11(data.supplierfullname);
	 //$("#isQualified").val(1);
 	//supplierfullname,vendor,uscc,contacts,telphone
 }
 //供应商名称改变，助记麻改变
 function materialNameChnagel11(mName){
		if(mName.length <= 0){
			return;
		}
		
		$.ajax({
			type:'post',
			url:'lBaMaterialController.do?getPinYinHead',
			data:{
				mName:mName
			},
			dataType:'json',
			success:function(data){
				console.log(data);
				if(data.success){
					$("#mnemonicCode").val(data.attributes.zjm);
				}
			}
		});
		
	}
//启用
function start(title, url,id,width,height) {
	gridname=id;
	var rowsData = $('#'+id).datagrid('getSelections');

	
	if (!rowsData || rowsData.length == 0) {
		tip('请选择供应商');
		return;
	}
    var ids=[];
	var len=rowsData.length;
	for(var i=0,a=len;i<a;i++){
		ids.push(rowsData[i].id);
	}
    url += '&ids='+ids;
	$.ajax({
		url : url,
		type : 'post',
		dataType : 'json',
		cache : false,
		success : function(data) {
			var msg = data.msg;
			tip(msg);
			reloadTable();
			$("#" + gridname).datagrid('unselectAll');
		}
	});
}
 </script>