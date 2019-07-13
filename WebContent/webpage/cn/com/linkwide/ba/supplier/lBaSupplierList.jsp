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
		<ul id="lBaSupplierList_Type"></ul>
	</div>
	<input type="hidden" id="nodeId">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="lBaSupplierList" checkbox="true" fitColumns="false" autoLoadData="false" title="供应商" actionUrl="lBaSupplierController.do?datagridAll" idField="id" fit="true" rowStyler="interlacingColour" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="供应商编码"  field="supplierCode"    queryMode="single" query="true"  width="120"></t:dgCol>
   <t:dgCol title="供应商名称"  field="supplierFullName"   queryMode="single" query="true" width="120"></t:dgCol>
   <t:dgCol title="供应商简称"  field="supplierShortName"  queryMode="single" query="true"  width="120"></t:dgCol>
   <t:dgCol title="供应商分类"  field="supplierTypeId"  dictionary="l_ba_supplier_type,id,type_name" queryMode="group" width="120"></t:dgCol>
   <t:dgCol title="地区"  field="affiliatedArea"  dictionary="ba_area,area_code,area_name" queryMode="group" width="120"></t:dgCol>
   <t:dgCol title="地区编码"  field="areaNum"  queryMode="group" width="120"></t:dgCol>
   <t:dgCol title="联系人"  field="contacts"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="联系电话"  field="telphone"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="地址"  field="address"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="是否控制 "  field="isControl"  dictionary="whether"  queryMode="single" query="true"  width="120"></t:dgCol>
  <%--  <t:dgCol title="审核状态 "  field="auditingState" queryMode="group"   width="120"></t:dgCol> --%>
   <t:dgCol title="是否停用 "  field="isCease"  queryMode="group" dictionary="whether"  width="120"></t:dgCol>
   <%-- <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="lBaSupplierController.do?doDel&id={id}" /> --%>
   <t:dgToolBar title="同步到U8" icon="icon-add" url="lBaSupplierController.do?synToU8" funname="synToU8"></t:dgToolBar>
   <t:dgToolBar title="添加" icon="icon-add" url="lBaSupplierController.do?goAdd" funname="addFun"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="lBaSupplierController.do?goUpdate" funname="beforeUpdate"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="lBaSupplierController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="密码重置"  icon="icon-remove" url="lBaSupplierController.do?resetPwd" funname="restPwd"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="lBaSupplierController.do?goUpdate" funname="detail"></t:dgToolBar>
   <%-- <t:dgToolBar title="审核" icon="icon-ok" url="lBaSupplierController.do?doCheck" funname="end"></t:dgToolBar>
    --%>
    <t:dgToolBar title="停用" icon="l-btn-text icon-undo l-btn-icon-left" url="lBaSupplierController.do?doStop" funname="stop" ></t:dgToolBar>
    <t:dgToolBar title="启用" icon="l-btn-text icon-ok l-btn-icon-left" url="lBaSupplierController.do?doStart" funname="start" ></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="更新式导入" icon="icon-put" funname="ImportXls2"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar> 
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
 						$('#lBaSupplierList').datagrid('options').url = 'lBaSupplierController.do?datagridAll&field=id,id_begin,id_end,supplierCode,supplierFullName,supplierShortName,supplierTypeId,supplierTypeId_begin,supplierTypeId_end,contacts,contacts_begin,contacts_end,telphone,telphone_begin,telphone_end,address,address_begin,address_end,isControl,auditingState,auditingState_begin,auditingState_end,isCease,isCease_begin,isCease_end,affiliatedArea,areaNum';
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
 		 /* if(auditingState=="审核"){
 			 tip("该单据已审核，不能编辑");
 		 }else  */
 		 if(isCease=="1"){
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
//导入
function ImportXls2() {
	openuploadwin('Excel导入', 'lBaSupplierController.do?updateUpload', "lBaSupplierList");
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


function restPwd(title,url,gname) {
	gridname=gname;
    var ids = [];
    var rows = $("#"+gname).datagrid('getSelections');
    if (rows.length > 0) {
    	$.dialog.setting.zIndex = getzIndex(true);
    	$.dialog.confirm('你确定重置密码吗?', function(r) {
		   if (r) {
				for ( var i = 0; i < rows.length; i++) {
					ids.push(rows[i].id);
				}
				$.ajax({
					url : url,
					type : 'post',
					data : {
						ids : ids.join(',')
					},
					cache : false,
					success : function(data) {
						var d = $.parseJSON(data);
						if (d.success) {
							var msg = d.msg;
							tip(msg);
							reloadTable();
							$("#"+gname).datagrid('unselectAll');
							ids='';
						}else{
							var msg = d.msg;
							tip(msg);
						}
					}
				});
			}
		});
	} else {
		tip("请选择需要重置密码的供应商");
	}
}
function synToU8(title,url,gname) {
	gridname=gname;
    var rows = $("#"+gname).datagrid('getRows');
    if (rows.length > 0) {
    	$.dialog.setting.zIndex = getzIndex(true);
    	$.dialog.confirm('你确定将数据推送到U8吗?', function(r) {
		   if (r) {
				$.ajax({
					url : url,
					type : 'post',
					data : {
					},
					cache : false,
					success : function(data) {
						var d = $.parseJSON(data);
						if (d.success) {
							var msg = d.msg;
							tip(msg);
							reloadTable();
							$("#"+gname).datagrid('unselectAll');
						}else{
							var msg = d.msg;
							tip(msg);
						}
					}
				});
			}
		});
	} else {
		tip("当前没有供应商数据推送到U8");
	}
}
 </script>