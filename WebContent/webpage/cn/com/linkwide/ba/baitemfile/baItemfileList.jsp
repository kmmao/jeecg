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
		<ul id="baProType"></ul>
	</div>
	<input type="hidden" id="nodeId">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="baItemfileList" checkbox="true" pagination="true" treegrid="false"  fitColumns="true" title="项目档案" actionUrl="baItemfileController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="流程状态"  field="bpmStatus"  hidden="true"  queryMode="single"  dictionary="bpm_status"  width="120"></t:dgCol>
   <t:dgCol title="项目档案编码"  field="vcode" align="left" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="项目档案名称"  field="vname" align="left" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="上级项目编码"  field="extend5" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="外部系统项目编码"  field="voutcode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="外部系统项目名称"  field="voutname"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属项目分类"  field="pkbaprotype" queryMode="single"  dictionary="ba_protype,vcode,vname"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段1"  field="extend1"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段2"  field="extend2"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="资金来源"  field="extend3" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="项目说明"  field="extend4" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段6"  field="extend6"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段7"  field="extend7"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段8"  field="extend8"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段9"  field="extend9"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段10"  field="extend10"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="baItemfileController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="添加" icon="icon-add" url="baItemfileController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="baItemfileController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="baItemfileController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="baItemfileController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/cn/com/linkwide/ba/baitemfile/baItemfileList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
	 $('#baProType').tree({
		 method:"POST",
		 lines:true,
		 url:"baProtypeController.do?comboTreeForState",
		 onClick: function(node){
			 if($('#baProType').tree("isLeaf",node.target)){
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
			 $('#baItemfileList').datagrid('options').url = 'baItemfileController.do?datagrid&field=id,createName,createBy,createDate,updateName,updateBy,updateDate,sysOrgCode,sysCompanyCode,bpmStatus,vcode,vname,extend5,voutcode,voutname,pkbaprotype,extend1,extend2,extend3,extend4,extend6,extend7,extend8,extend9,extend10,';
			 $("#baItemfileList").datagrid('load',{
				 pkbaprotypes : ids.join(",")
			 }) 
		},
		onLoadSuccess:function(node,data){
			//$("#baProType li:eq(0)").find("div:first").addClass("tree-node-selected");
			var n = $("#baProType").tree("getSelected");
			if(n){
				var ids = [];
				 ids.push(n.id);
				 var childList = $(this).tree('getChildren',n.target);
				 $.each(childList,function(index,curr){
					 ids.push(curr.id);
				 });
				$('#baItemfileList').datagrid('options').url = 'baItemfileController.do?datagrid&field=id,createName,createBy,createDate,updateName,updateBy,updateDate,sysOrgCode,sysCompanyCode,bpmStatus,vcode,vname,extend5,voutcode,voutname,pkbaprotype,extend1,extend2,extend3,extend4,extend6,extend7,extend8,extend9,extend10,';
				$("#baItemfileList").datagrid('reload',{
					pkbaprotypes : ids.join(",")
			 	})
			} 
		}
	});
	/*  $("#baItemfileList").treegrid({
			 onExpand : function(row){
				var children = $("#baItemfileList").treegrid('getChildren',row.id);
				 if(children.length<=0){
				 	row.leaf=true;
				 	$("#baItemfileList").treegrid('refresh', row.id);
				 }
			}
	}); */
 });
 
 function add(title,addurl,gname,width,height){
	 var nodeId = $("#nodeId").val();
	 if(nodeId == ""){
		tip("请选择项目分类"); 
		return;
	}
	url=addurl+"&pkbaprotypes="+nodeId;
	createwindow(title, url,width,height);
 }   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'baItemfileController.do?upload', "baItemfileList");
}

//导出
function ExportXls() {
	JeecgExcelExport("baItemfileController.do?exportXls","baItemfileList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("baItemfileController.do?exportXlsByT","baItemfileList");
}

 </script>