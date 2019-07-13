<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="lBaMaterialImagesList" checkbox="true" fitColumns="false" title="物资图片" actionUrl="lBaMaterialImagesController.do?datagrid&materialId=${lBaMaterialId }" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="物资ID"  field="materialId" hidden="true"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="图片路径"  field="filePath"   hidden="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="图片名称"  field="fileName"    queryMode="group"  width="120" ></t:dgCol>
   <t:dgCol title="创建时间"  field="createDate"  formatter="yyyy-MM-dd"  queryMode="group"  width="120" ></t:dgCol>
   <t:dgCol title="下载" field="opt" formatterjs="test" width="100"></t:dgCol>
   <t:dgCol title="预览" field="opt1" formatterjs="test1" width="100"></t:dgCol>
   <%-- <t:dgDelOpt title="删除" url="lBaMaterialImagesController.do?doDel&id={id}" /> --%>
   <t:dgToolBar title="添加" icon="icon-add" url="lBaMaterialImagesController.do?goAdd&lBaMaterialId=${lBaMaterialId}" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="lBaMaterialImagesController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="lBaMaterialImagesController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/cn/com/linkwide/ba/material/images/lBaMaterialImagesList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#lBaMaterialImagesListtb").find("input[name='createDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#lBaMaterialImagesListtb").find("input[name='createDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#lBaMaterialImagesListtb").find("input[name='updateDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#lBaMaterialImagesListtb").find("input[name='updateDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
/*  function cellStyle(value,row,index){
	 return '111'
 } */
 function test(value,row,index){
 	 var filePath = row.filePath;  //业务类型
 	 return "<a  href='#' onclick='down_doc(\""+filePath+"\")'>下载</a>";
  } 
 function test1(value,row,index){
 	 var filePath = row.filePath;  //业务类型
 	 return "<a  href='#' onclick='show(\""+filePath+"\")'>预览</a>";
  } 
 
 function down_doc(filePath){
	/* window.location.href = "upload/"+filePath; */
	window.location.href = "fileUpAndDownController.do?viewDoc&filePath="+filePath;
 }
 
 function show(filePath){
		/* window.location.href = "upload/"+filePath; */
		window.open(filePath,"_blank");
 }
 </script>