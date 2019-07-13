<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
 <head>
 <%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<link rel="stylesheet" type="text/css"	href="plug-in/ztree/css/zTreeStyle.css">
<script type="text/javascript"	src="plug-in/ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript"	src="plug-in/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
<link rel="stylesheet" type="text/css"	href="plug-in/easyui/themes/icon.css">
</head>
<script type="text/javascript">
 
</script> 
<div class="easyui-layout" style="width:100%; height: 100%;">
	<!-- 顶部-->
	<div region="north" border="false" title=""
		style="height: 50px; padding: 1px; overflow: hidden;">
		<table class="ui_dialog" width="100%">
			<tbody>
				<tr>
					<td align="left">
						<div class="ui_buttons">
							<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="goAdd();">添加</a>
							<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="goUpd();">编辑</a>
							<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="doDel();">删除</a>
							<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="saveForm();">保存</a>
							<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="stopOrStart(0);">启用</a>
							<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="stopOrStart(1);">停用</a>
						</div>
					</td>
				</tr>
			</tbody>
		</table>

	</div>
	
    <div data-options="region:'west',split:true" title="" style="width: 180px;">
		<ul id="materialTypeSelect" class="ztree" style="margin-top: 10px;"></ul>
	</div>
    <div data-options="region:'center'" >
 		<iframe id="formContenFrame"  frameborder="0" scrolling="auto"   style="height:100%;width:99.9%;" ></iframe>
	</div>
</div> 
 <script type="text/javascript">
 $(document).ready(function(){
  /****************input 回车事件屏蔽  开始***********************/
   document.onkeydown = function(e){
    if(e.keyCode == 13){
       pPeopleListListsearch();
      return false;
    }
  }
  document.onkeypress = function(e){
    if(e.keyCode == 13){
       
      return false;
    }
  } 
  /*****************input 回车事件屏蔽  开始***********************/
	//给时间控件加上样式
	$("#pPeopleListListtb").find("input[name='birthday_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	$("#pPeopleListListtb").find("input[name='birthday_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	$("#pPeopleListListtb").find("input[name='joinTime_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	$("#pPeopleListListtb").find("input[name='joinTime_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	$("#pPeopleListListtb").find("input[name='armyTime_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	$("#pPeopleListListtb").find("input[name='armyTime_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	$("#pPeopleListListtb").find("input[name='presentTime_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	$("#pPeopleListListtb").find("input[name='presentTime_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	$("#pPeopleListListtb").find("input[name='officeTime_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	$("#pPeopleListListtb").find("input[name='officeTime_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	$("#pPeopleListListtb").find("input[name='createDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	$("#pPeopleListListtb").find("input[name='createDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	$("#pPeopleListListtb").find("input[name='updateDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	$("#pPeopleListListtb").find("input[name='updateDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			
 			
 });
 
 
 $(document).ready(function(){
		
	 $('#materialTypeSelect').tree({
		 method:"POST",
		 lines:true,
		 url:"lBaLocationDictController.do?locationTree",
		 onClick: function(node){
			 	var date = new Date();
		 	    var url = "lBaLocationDictController.do?goUpdate&id="+node.id+"&"+date.getTime();
	 			$("#formContenFrame").attr("src",url);
		}
	});
 });
 
    
//刷新
function ref(){
   window.location.reload();
}


function back(data){
	alert(data)
	$('#materialTypeSelect').tree("reload");
} 

//新增
function goAdd(){
	var node = $('#materialTypeSelect').tree('getSelected');
	var date = new Date();
	debugger
	if(node){
		if(node.attributes.status == "1"){
			tip("节点已经停用，不能添加子节点");
		}else{
			
			var url = "lBaLocationDictController.do?goAdd&parentId="+node.id+"&"+date.getTime();
			if(node.attributes.loLeve){
				url+= "&loLeve="+node.attributes.loLeve;
			}
			if(node.attributes.code){
				url+= "&code="+node.attributes.code;
			}
			if(node.attributes.wareId){
				url+= "&wareId="+node.attributes.wareId;
			}
			$("#formContenFrame").attr("src",url);
		}
	}else{
		var url = "lBaLocationDictController.do?goAdd"+"&"+date.getTime();
		$("#formContenFrame").attr("src",url);
	}
}

//修改
function goUpd(){
	var node = $('#materialTypeSelect').tree('getSelected');
	var date = new Date();
	if(node){
		var url = "lBaLocationDictController.do?goUpdate&id="+node.id+"&"+date.getTime();
		$("#formContenFrame").attr("src",url);
	 }else{
		 tip("请选择一个节点");
	 }
	
}

//保存
function saveForm(){
	//获取新增或修改页面
	var iframe = document.getElementById('formContenFrame');	//$("iframe");

	var doc;
	if(iframe.contentDocument){
		doc = iframe.contentDocument;
	}else if(iframe.contentWindow){
		doc = iframe.contentWindow.document;
	}
	 $("#btn_sub",doc).click();
	//$('#cc').layout('panel','east').find('#btu_sub').click();
}


//删除
function doDel(){
	var node = $('#materialTypeSelect').tree('getSelected');
	if(node){
		 if($('#materialTypeSelect').tree("isLeaf",node.target)){
			 $.ajax({
				 type:"POST",
				 async:false,
				 url:"lBaLocationDictController.do?doDel",
				 data:{
					id:node.id
				 },
				 dataType:"json",
				 success:function(data){
					 if(data.success){
						 $('#materialTypeSelect').tree("reload");
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


//添加或更新后刷新

//停用 启用
function stopOrStart(status){
	var node = $('#materialTypeSelect').tree('getSelected');
	$("#tip").html("");
	if(node !=null && node !=""){
		var id = node.id;
		var url = "lBaLocationDictController.do?doEnDisAble&id="+id;
		$.ajax({
				 type:"post",
				 url:url,
				 data:{
				 	status:status
				 },
				 success:function(data){
					 var d = $.parseJSON(data);	
						if (d.success) {
							if(d.msg == 'exist'){
								tip("请先停用或启用子节点!");
							}else if(d.msg == 'existData'){
								tip("物资分类下存在物资档案,不能停用!");
							}else if(d.msg == '400'){
								tip("本节点的父节点被停用,不能启用!");
							}else{
								var date = new Date();
								var url = "lBaLocationDictController.do?goUpdate&id="+id+"&"+date.getTime();
								$("#formContenFrame").attr("src",url);
								$('#materialTypeSelect').tree("reload");
							}	
						}
				 }
		});
	}else{
		tip("请选择需要修改的数据");
	}
}




 </script>
 <style type="text/css">
	div.menu-line2{
	  left: 238px;
	}
	div.menu{
	  width: 427px;
	}
	div.menu-item{
	  width: 210px;
	  display: inline-block;
	}
</style>
</body></html>