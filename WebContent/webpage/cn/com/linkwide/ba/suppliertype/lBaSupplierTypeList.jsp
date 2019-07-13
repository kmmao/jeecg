<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body>
<%-- <div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="ppeRapairProjectList" checkbox="false" pagination="true" fitColumns="true" title="维修项目档案" actionUrl="ppeRapairProjectController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属科室"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="流程状态"  field="bpmStatus"  hidden="true"  queryMode="single"  dictionary="bpm_status"  width="120"></t:dgCol>
   <t:dgCol title="项目档案编码"  field="projectCode"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="项目档案拼音码"  field="projectPy"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="项目档案名称"  field="projectName"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="单价"  field="projectPrice"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="费用类型"  field="projectCostType"  queryMode="single"  dictionary="cost_type"  width="120"></t:dgCol>
   <t:dgCol title="预留字段1"  field="vdef1"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段2"  field="vdef2"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段3"  field="vdef3"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段4"  field="vdef4"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段5"  field="vdef5"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段6"  field="vdef6" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段7"  field="vdef7"  hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="ppeRapairProjectController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="添加" icon="icon-add" url="ppeRapairProjectController.do?goAdd" funname="addClick_supplierType"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="ppeRapairProjectController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="ppeRapairProjectController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="ppeRapairProjectController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div> --%>
<div class="easyui-layout" fit="true">
<div data-options="region:'north',
	collapsed:false,
	split:true,
	border:false" style="padding:5px;border:0px;height: 40px">
<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addClick_supplierType();">添加</a>
<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="updateClick_supplierType();">编辑</a>
<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="delClick_supplierType();">删除</a>
<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="savebu_supplierType();">保存</a>
<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="checkState_supplierType(0);">启用</a>
<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="checkState_supplierType(1);">停用</a>
</div>
  <div region="center" style="padding:5px;border:0px">
  <div class="easyui-panel" style="padding:5px;border:0px" fit="true" border="false" id="supplierTypeEastPanel"></div>
  
  </div>
  <div data-options="region:'west',
	collapsed:false,
	split:true,
	border:false
	"
	style="width: 300px;">
		<ul id="supplierType"></ul>
</div>
 </div>
 <script src = "webpage/cn/com/linkwide/ba/suppliertype/lBaSupplierTypeList.js"></script>	
 <script src = "webpage/cn/com/linkwide/ba/suppliertype/lBaSupplierType.js"></script>		
  <script type="text/javascript" src="plug-in/Validform/js/Validform_v5.3.1_min_zh-cn.js"></script>
  <script type="text/javascript" src="plug-in/Validform/js/Validform_Datatype_zh-cn.js"></script>
  <script type="text/javascript" src="plug-in/Validform/js/datatype_zh-cn.js"></script>
  <script type="text/javascript" src="plug-in/Validform/plugin/passwordStrength/passwordStrength-min.js"></script>
 <script type="text/javascript">
 $(document).ready(function(){
	 $('#supplierType').tree({
		 method:"POST",
		 lines:true,
		 url:"lBaSupplierTypeController/getTreeAllDate.do",
		 onClick: function(node){
			 $('#supplierTypeEastPanel').panel("refresh", "lBaSupplierTypeController.do?goDetail&id=" +node.id);
		}
	});
 });
 function subCallback(data){
	 if(data.success){
		 $('#supplierType').tree("reload");
	 }else{
		 $.Showmsg(data.msg);
	 }
 }
 function addClick_supplierType(){
	 var node = $('#supplierType').tree('getSelected');
	 if(node){
		 if(node.attributes.isLast=='1'){
			 tip("该节点是末级，不能添加子节点");
			 return;
		 }
		 if(node.attributes.status == "1"){
			 tip("节点已经停用，不能添加子节点");
		 }else{
			 $('#supplierTypeEastPanel').panel("refresh", "lBaSupplierTypeController.do?goAdd&pid="+node.id);
		 }
		 
	 }else{
		 $('#supplierTypeEastPanel').panel("refresh", "lBaSupplierTypeController.do?goAdd");
	 }
 }
 function savebu_supplierType(){
	 var iframe = $("iframe");
	 $("#btn_sub",iframe.document).click();
 }
 
 function updateClick_supplierType(){
	 var node = $('#supplierType').tree('getSelected');
	 if(node){
		 $('#supplierTypeEastPanel').panel("refresh", "lBaSupplierTypeController.do?goUpdate&id="+node.id);
	 }else{
		 tip("请选择一个节点");
	 }
	 
 }
 
 function checkState_supplierType(state){
	 var node = $('#supplierType').tree('getSelected');
	 if(node){
		 $.ajax({
			 type:"POST",
			 async:false,
			 url:"lBaSupplierTypeController.do?checkState",
			 data:{
				id:node.id,
				status:state
			 },
			 dataType:"json",
			 success:function(data){
				 if(data.success){
					 if(data.msg == "300"){
						 tip("该节点已经被引用，不能停用");
					 }else if(data.msg == "400"){
						 tip("该节点的父节点被停用，不能启用");
					 }else{
						 $('#supplierTypeEastPanel').panel("refresh", "lBaSupplierTypeController.do?goDetail&id=" +node.id);
						 $('#supplierType').tree("reload");
					 }
				 }else{
					 tip("修改状态失败");
				 }
			 }
		 });
	 }else{
		 tip("请选择一个节点");
	 }
 }
 
 function delClick_supplierType(){
	 var node = $('#supplierType').tree('getSelected');
	 if(node){
		 if($('#supplierType').tree("isLeaf",node.target)){
			 $.ajax({
				 type:"POST",
				 async:false,
				 url:"lBaSupplierTypeController.do?doDel",
				 data:{
					id:node.id
				 },
				 dataType:"json",
				 success:function(data){
					 if(data.success){
						 if(data.msg == "300"){
							 tip("改节点已经被引用，不能删除");
						 }else{
							 $('#supplierType').tree("reload");
						 }
					 }else{
						 tip("删除分类失败");
					 }
				 }
			 });
		 }else{
			 tip("只能删除叶子节点");
		 }
	 }else{
		 tip("请选择一个节点");
	 }
 }
 
 </script>
 </body></html>