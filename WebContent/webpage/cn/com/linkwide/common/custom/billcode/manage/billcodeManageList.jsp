<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<input id="billType" type="hidden">
 <div region="west" style="width: 200px;"split="true" collapsed="false">
		<div class="easyui-panel" style="padding:0px;border:0px" fit="true" border="false">
			<ul id="menuTree">
			</ul>
		</div>
  </div>
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="billcodeManageList" checkbox="true" onDblClick="dblDetail" detailUrl="billcodeManageController.do?goUpdate"  rowStyler="interlacingColour" fitColumns="false" title="单据号管理" actionUrl="billcodeManageController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
  <%--  <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol> --%>
  <%--  <t:dgCol title="单据号范围"  field="billScope "    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="生成单据号时检查唯一性"  field="isUniq"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="删除单据时保留占用"  field="isKeep"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="自动进行断号补号"  field="autoComplete"    queryMode="single"  width="120"></t:dgCol> --%>
   <t:dgCol title="单据类型"  field="billType"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="对象1"  field="billObj1"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="对象2"  field="billObj2"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="流水号后几位位数"  field="endNum"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="流水号归零标志"  field="zeroMark"  replace="不归零_none,年_year,月_month,日_day"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="表示"  field="billDesc"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="单据号"  field="billCode"    queryMode="single"  width="120"></t:dgCol>
   <%-- <t:dgCol title="预留字段1"  field="vdef1"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段2"  field="vdef2"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段3"  field="vdef3"    queryMode="single"  width="120"></t:dgCol> --%>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="billcodeManageController.do?doDel&id={id}" />
   <t:dgToolBar title="添加" icon="icon-add" url="billcodeManageController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="billcodeManageController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="billcodeManageController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-see" url="billcodeManageController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-comturn" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#billcodeManageListtb").find("input[name='createDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#billcodeManageListtb").find("input[name='updateDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
 //添加
 function add(title,addurl,gname,width,height) {
	 var billType=$("#billType").val();
	 if(!isNotEmpty(billType)){
		 tip("请选择一条单据类型");
	 }else{
		var rowsData = $('#'+gname).datagrid('getData');
		if(rowsData.total>0){
			tip("只能定义一种单据号");
		}else{
			gridname=gname;
			var url=addurl+"&billType="+billType;
			createwindow(title,url,width,height);
		}
	 }
}
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'billcodeManageController.do?upload', "billcodeManageList");
}

//导出
function ExportXls() {
	JeecgExcelExport("billcodeManageController.do?exportXls","billcodeManageList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("billcodeManageController.do?exportXlsByT","billcodeManageList");
}
//非空判断
function isNotEmpty(s){
	return ((s!=undefined&&s!=null&&s!="")?true:false);
}
 </script>
 <script src = "webpage/cn/com/linkwide/common/custom/billcode/manage/billcodeManageList.js"></script>		