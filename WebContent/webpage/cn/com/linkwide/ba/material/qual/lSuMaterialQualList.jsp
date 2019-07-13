<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <div id="tempSearch" style="display: none">
		<div id="materialColums">
			<span style="vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 80px;text-align:right;text-overflow:ellipsis;-o-text-overflow:ellipsis; overflow: hidden;white-space:nowrap; " title="供应商编码">
			物资：
			</span>
			<t:materialSelect lblName="物资" selectedIdsInputId="materialId" selectedNamesInputId="materialn" title="物资"  hasNull="true"
			materialNameInputWidth="120px"></t:materialSelect>
		</div>
	</div>
  <t:datagrid name="lSuMaterialQualList" checkbox="true" fitColumns="false" title="物资资质" actionUrl="lSuMaterialQualController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="单据编码"  field="billNo"    queryMode="single" query="true"  width="120"></t:dgCol>
   <t:dgCol title="物资"  field="materialId" dictionary="l_ba_material,id,material_name"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="单据日期"  field="billDate" formatter="yyyy-MM-dd"   queryMode="group" query="true" width="120"></t:dgCol>
   <t:dgCol title="审核状态"  field="auditStatus"  dictionary="billstatus"  queryMode="single" query="true"  width="120"></t:dgCol>
   <t:dgCol title="审核人"  field="auditId"  dictionary="t_s_base_user,id,username"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="审核时间"  field="auditDate" formatter="yyyy-MM-dd"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建人"  field="createBy"  dictionary="t_s_base_user,id,username"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="createDate" formatter="yyyy-MM-dd"   queryMode="group"  width="120"></t:dgCol>
   <t:dgToolBar title="添加" icon="icon-add" url="lSuMaterialQualController.do?goAdd" funname="add" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="lSuMaterialQualController.do?goUpdate" funname="goUpdate" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="lSuMaterialQualController.do?doBatchDel" funname="goBatchDel"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="lSuMaterialQualController.do?goUpdate" funname="detail" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-putout" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
   <t:dgToolBar title="提交"  icon="icon-redo" url="lSuMaterialQualController.do?doCommon" funname="goCommit"></t:dgToolBar>
   <t:dgToolBar title="审批"   icon="icon-ok" url="lSuMaterialQualController.do?doApprove" funname="goApprove"></t:dgToolBar>
   <t:dgToolBar title="弃审"   icon="icon-undo" url="lSuMaterialQualController.do?doUnApprove" funname="goUnApprove"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/cn/com/linkwide/ba/material/qual/lSuMaterialQualList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#lSuMaterialQualListtb").find("input[name='billDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#lSuMaterialQualListtb").find("input[name='billDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#lSuMaterialQualListtb").find("input[name='auditDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#lSuMaterialQualListtb").find("input[name='auditDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#lSuMaterialQualListtb").find("input[name='createDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#lSuMaterialQualListtb").find("input[name='createDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#lSuMaterialQualListtb").find("input[name='updateDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#lSuMaterialQualListtb").find("input[name='updateDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 
 			$("#lSuMaterialQualListtb").find("div[name='searchColums']").find("form#lSuMaterialQualListForm").append($("#materialColums").html());
 		 	$("#materialColums").html("");
 });
//导入
 function ImportXls() {
 	createwindowRef("导入", "lSuMaterialQualController.do?upload", 350, 150, "dialogTwo");
 }

 //导出
 function ExportXls() {
	 var ids = [];
	 var rows = $("#lSuMaterialQualList").datagrid('getSelections');
	 if(rows.length==0){
		 tip("请选择需要导出的数据");
	 }else{
		 for(var i = 0; i < rows.length; i++){
				ids.push(rows[i].id);
			}
 		JeecgExcelExport("lSuMaterialQualController.do?exportXls&ids="+ids,"lSuMaterialQualList");
	 }
 }

 //模板下载
 function ExportXlsByT() {
 	JeecgExcelExport("lSuMaterialQualController.do?exportXlsByT","lSuMaterialQualList");
 }
 </script>