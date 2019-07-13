<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body>
<div class="easyui-layout" fit="true">
<div data-options="region:'north',
	collapsed:false,
	split:true,
	border:false" style="padding:5px;border:0px;height: 40px">
<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addClick_customerType();">添加</a>
<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="updateClick_customerType();">编辑</a>
<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="delClick_customerType();">删除</a>
<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="savebu_customerType();">保存</a>
<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="checkState_customerType(0);">启用</a>
<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="checkState_customerType(1);">停用</a>
</div>
  <div region="center" style="padding:5px;border:0px">
  <div class="easyui-panel" style="padding:5px;border:0px" fit="true" border="false" id="customerTypeEastPanel"></div>
  
</div>
<div id="customerTypeWestPanel" data-options="region:'west', collapsed:false, split:true, border:false" style="width: 300px;">
	<ul id="customerType"></ul>
</div>
 </div>
 <script src = "webpage/cn/com/linkwide/ba/bacustomertype/baCustomerTypeList.js"></script>
 <script src = "webpage/cn/com/linkwide/ba/bacustomertype/baCustomerType.js"></script>		
  <script type="text/javascript" src="plug-in/Validform/js/Validform_v5.3.1_min_zh-cn.js"></script>
  <script type="text/javascript" src="plug-in/Validform/js/Validform_Datatype_zh-cn.js"></script>
  <script type="text/javascript" src="plug-in/Validform/js/datatype_zh-cn.js"></script>
  <script type="text/javascript" src="plug-in/Validform/plugin/passwordStrength/passwordStrength-min.js"></script>
 <script type="text/javascript">
 $(document).ready(function(){
	 $('#customerType').tree({
		 method:"POST",
		 lines:true,
		 url:"baCustomerTypeController/getTreeAllDate.do",
		 onClick: function(node){
			 $('#customerTypeEastPanel').panel("refresh", "baCustomerTypeController.do?goDetail&id=" +node.id);
		}
	});
 });
 function subCallback(data){
	 if(data.success){
		 $('#customerType').tree("reload");
		 tip(data.msg);
	 }else{
		tip(data.msg);
	 }
 }
 //添加
 function addClick_customerType(){
	 var node = $('#customerType').tree('getSelected');
	 if(node){
		 if(node.attributes.isLast=='1'){
			 tip("该节点是末级，不能添加子节点");
			 return;
		 }
		 if(node.attributes.status == "1"){
			 tip("节点已经停用，不能添加子节点");
		 }else{
			 $('#customerTypeEastPanel').panel("refresh", "baCustomerTypeController.do?goAdd&pid="+node.id);
		 }
		 
	 }else{
		 $('#customerTypeEastPanel').panel("refresh", "baCustomerTypeController.do?goAdd");
	 }
 }
 //保存
 function savebu_customerType(){
	 //$('#customerTypeEastPanel').panel("refresh", "baCustomerTypeController.do?goAdd");
	 var iframe = $("iframe");
	 $("#btn_sub",iframe.document).click();
 }
 //编辑
 function updateClick_customerType(){
	 var node = $('#customerType').tree('getSelected');
	 if(node){
		 $('#customerTypeEastPanel').panel("refresh", "baCustomerTypeController.do?goUpdate&id="+node.id);
	 }else{
		 tip("请选择一个节点");
	 }
	 
 }
 //启用  停用
 function checkState_customerType(state){
	 var node = $('#customerType').tree('getSelected');
	 if(node){
		 $.ajax({
			 type:"POST",
			 async:false,
			 url:"baCustomerTypeController.do?checkState",
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
						 $('#customerTypeEastPanel').panel("refresh", "baCustomerTypeController.do?goDetail&id=" +node.id);
						 $('#customerType').tree("reload");
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
 //删除
 function delClick_customerType(){
	 var node = $('#customerType').tree('getSelected');
	 if(node){
		 if($('#customerType').tree("isLeaf",node.target)){
			 $.ajax({
				 type:"POST",
				 async:false,
				 url:"baCustomerTypeController.do?doDel",
				 data:{
					id:node.id
				 },
				 dataType:"json",
				 success:function(data){
					 if(data.success){
						 if(data.msg == "300"){
							 tip("该节点已经被引用，不能删除");
						 }else{
							 $('#customerType').tree("reload");
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