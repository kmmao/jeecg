<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div region="west" style="width: 200px;"split="true" collapsed="false">
		<div class="easyui-panel" style="padding:0px;border:0px" fit="true" border="false">
			<ul id="menuTree">
			</ul>
			<input id="currencyId" type="hidden">
		</div>
 </div>
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="tSForeignCurrencyList" checkbox="true" fitColumns="true" title="币种档案" actionUrl="tSForeignCurrencyController.do?datagrid" onDblClick="dblDetail" detailUrl="tSForeignCurrencyController.do?goUpdate"  rowStyler="interlacingColour" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="流程状态"  field="bpmStatus"  hidden="true"  queryMode="single"  dictionary="bpm_status"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="币种编码"  field="currencyCode"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="币种名称"  field="currencyName"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="折算方式"  field="convertType"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="小数位数"  field="decimalNum"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="最大误差"  field="maxError"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="是否本位币"  field="isOtherused"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="时间戳"  field="pubufts"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="tSForeignCurrencyController.do?doDel&id={id}"  urlclass="ace_button" urlfont="fa-trash-o"/>
   <t:dgToolBar title="添加" icon="icon-add" url="tSForeignCurrencyController.do?goAdd" funname="add1" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="tSForeignCurrencyController.do?goUpdate" funname="update" width="100%" height="100%"></t:dgToolBar>
   <%-- <t:dgToolBar title="批量删除"  icon="icon-remove" url="tSForeignCurrencyController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar> --%>
   <t:dgToolBar title="查看" icon="icon-search" url="tSForeignCurrencyController.do?goUpdate" funname="detail" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/cn/com/linkwide/ba/tsforeigncurrency/tSForeignCurrencyList.js"></script>		
 <script type="text/javascript">
//添加
 function add1(title,addurl,gname,width,height) {
	 var currencyId=$("#currencyId").val();
	 if(!isNotEmpty(currencyId)){
		var url=addurl;
		createwindow(title,url,width,height);
	 } else{
		var rowsData = $('#'+gname).datagrid('getData');
		if(rowsData.total>0){
			tip("只能设置一种外币方案");
		}else{
			gridname=gname;
			var url=addurl+"&currencyId="+currencyId;
			createwindow(title,url,width,height);
		}
	 } 
}
//非空判断
 function isNotEmpty(s){
 	return ((s!=undefined&&s!=null&&s!="")?true:false);
 }
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'tSForeignCurrencyController.do?upload', "tSForeignCurrencyList");
}

//导出
function ExportXls() {
	JeecgExcelExport("tSForeignCurrencyController.do?exportXls","tSForeignCurrencyList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("tSForeignCurrencyController.do?exportXlsByT","tSForeignCurrencyList");
}
 </script>