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
	<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addClick_lSuQualTypeList();">添加</a>
<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="updateClick_lSuQualTypeList();">编辑</a>
<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="delClick_lSuQualTypeList();">删除</a>
<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="savebu_lSuQualTypeList();">保存</a>
<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="checkState_lSuQualTypeList(0);">启用</a>
<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="checkState_lSuQualTypeList(1);">停用</a>
</div>
  <div region="center" style="padding:5px;border:0px">
  	<div class="easyui-panel" style="padding:5px;border:0px" fit="true" border="false" id="suQualTypeEastPanel"></div>
  </div>
  <div data-options="region:'west',
	collapsed:false,
	split:true,
	border:false
	"
	style="width: 300px; overflow: hidden;">
	 <ul id="lSuQualTypeList"></ul>
  </div>
</div>
 <script src = "webpage/cn/com/linkwide/ba/material/qualtype/lBaMaterialQualTypeList.js"></script>
 <script src = "webpage/cn/com/linkwide/ba/material/qualtype/lBaMaterialQualType.js"></script>
  <script type="text/javascript" src="plug-in/Validform/js/Validform_v5.3.1_min_zh-cn.js"></script>
  <script type="text/javascript" src="plug-in/Validform/js/Validform_Datatype_zh-cn.js"></script>
  <script type="text/javascript" src="plug-in/Validform/js/datatype_zh-cn.js"></script>
  <script type="text/javascript" src="plug-in/Validform/plugin/passwordStrength/passwordStrength-min.js"></script>
 <script type="text/javascript">
 $(document).ready(function(){
	 $('#lSuQualTypeList').tree({
		 method:"POST",
		 lines:true,
		 url:"lBaMaterialQualTypeController/getTreeAllDate.do",
		 onClick: function(node){
			 $('#suQualTypeEastPanel').panel("refresh", "lBaMaterialQualTypeController.do?goDetail&id=" +node.id);
		}
	});
 });
 
 function subCallback(data){
	 if(data.success){
		 $('#lSuQualTypeList').tree("reload");
	 }else{
		 $.Showmsg(data.msg);
	 }
 } 
	 function addClick_lSuQualTypeList(){
		 var node = $('#lSuQualTypeList').tree('getSelected');
		 if(node){
			 if(node.attributes.status == "1"){
				 tip("节点已经停用，不能添加子节点");
			 }else{
				 $('#suQualTypeEastPanel').panel("refresh", "lBaMaterialQualTypeController.do?goAdd&pid="+node.id);
			 }
		 }else{
			 $('#suQualTypeEastPanel').panel("refresh", "lBaMaterialQualTypeController.do?goAdd");
		 }
	 }
	 
	 function savebu_lSuQualTypeList(){
		 var iframe = $("iframe");
		 $("#btn_sub",iframe.document).click();
	 }
	 
	 function updateClick_lSuQualTypeList(){
		 var node = $('#lSuQualTypeList').tree('getSelected');
		 if(node){
			 $('#suQualTypeEastPanel').panel("refresh", "lBaMaterialQualTypeController.do?goUpdate&id="+node.id);
		 }else{
			 tip("请选择一个节点");
		 }
		 
	 }
	 
	 function checkState_lSuQualTypeList(state){
		 var node = $('#lSuQualTypeList').tree('getSelected');
		 if(node){
			 $.ajax({
				 type:"POST",
				 async:false,
				 url:"lBaMaterialQualTypeController.do?checkState",
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
							 $('#suQualTypeEastPanel').panel("refresh", "lBaMaterialQualTypeController.do?goDetail&id=" +node.id);
							 $('#lSuQualTypeList').tree("reload");
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
	 
	 function delClick_lSuQualTypeList(){
		 var node = $('#lSuQualTypeList').tree('getSelected');
		 if(node){
			 if($('#lSuQualTypeList').tree("isLeaf",node.target)){
				 $.ajax({
					 type:"POST",
					 async:false,
					 url:"lBaMaterialQualTypeController.do?doDel",
					 data:{
						id:node.id
					 },
					 dataType:"json",
					 success:function(data){
						 if(data.success){
							 if(data.msg == "300"){
								 tip("该节点已经被引用，不能删除");
							 }else{
								 $('#lSuQualTypeList').tree("reload");
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
 </body>
 </html>