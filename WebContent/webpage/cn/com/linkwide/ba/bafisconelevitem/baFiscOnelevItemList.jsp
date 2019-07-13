<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="baFiscOnelevItemList" sortName="vcode" sortOrder="asc" checkbox="false" pagination="false" treegrid="true" treeField="vname"  fitColumns="true"  title="财政一级项目" actionUrl="baFiscOnelevItemController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="流程状态"  field="bpmStatus" hidden="true" queryMode="single"  dictionary="bpm_status"  width="120"></t:dgCol>
   <t:dgCol title="项目编码"  field="vcode"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="项目名称"  field="vname"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="上级项目编码"  field="parentCode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="是否末级"  field="isLast" dictionary="sf_yn" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="年度" field="budgYear" query="true" queryMode="single" dictionary="ba_set_account_period,period_year,period_year" width="120"></t:dgCol>
   <t:dgCol title="是否停用"  field="isStop" dictionary="sf_yn" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="项目说明"  field="itemMemo"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段1"  hidden="true" queryMode="single" width="120"></t:dgCol>
   <t:dgCol title="扩展字段2" hidden="true" field="extend2"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段3" hidden="true" field="extend3"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段4" hidden="true" field="extend4"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段5" hidden="true" field="extend5"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段6" hidden="true" field="extend6"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段7" hidden="true" field="extend7"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段8" hidden="true" field="extend8"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段9" hidden="true" field="extend9"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段10" hidden="true" field="extend10"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="baFiscOnelevItemController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="添加" icon="icon-add" url="baFiscOnelevItemController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="baFiscOnelevItemController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="baFiscOnelevItemController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="baFiscOnelevItemController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/cn/com/linkwide/ba/bafisconelevitem/baFiscOnelevItemList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
	 $("#baFiscOnelevItemList").treegrid({
			 onExpand : function(row){
				var children = $("#baFiscOnelevItemList").treegrid('getChildren',row.id);
				 if(children.length<=0){
				 	row.leaf=true;
				 	$("#baFiscOnelevItemList").treegrid('refresh', row.id);
				 }
			}
	});
	 
	 
	 var budgYear = new Date().getFullYear()+1;
	 $("select[name='budgYear']").val(budgYear);
	 setTimeout(function(){baFiscOnelevItemListsearch()}, 300);
	
	 
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'baFiscOnelevItemController.do?upload', "baFiscOnelevItemList");
}

//导出
function ExportXls() {
	JeecgExcelExport("baFiscOnelevItemController.do?exportXls","baFiscOnelevItemList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("baFiscOnelevItemController.do?exportXlsByT","baFiscOnelevItemList");
}

/**
 * 获取表格对象
 * @return 表格对象
 */
function getDataGrid(){
	var datagrid = $('#'+gridname);
	return datagrid;
}
 </script>