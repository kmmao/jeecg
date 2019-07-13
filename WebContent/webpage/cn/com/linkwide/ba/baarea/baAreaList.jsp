<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="baAreaList" checkbox="false" pagination="false" treegrid="true" treeField="areaName" fitColumns="true" title="地区" actionUrl="baAreaController.do?datagrid" idField="id" fit="true" queryMode="group">
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
   <t:dgCol title="地区编码"  field="areaCode" align="left" query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="地区名称"  field="areaName" align="left"  query="true"  queryMode="single"  width="160"></t:dgCol>
   <t:dgCol title="上级地区"  field="parentCode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="是否末级"  field="isLast"  queryMode="single"  dictionary="sf_yn"  width="120"></t:dgCol>
   <t:dgCol title="简称"  field="extend1"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="是否直辖市"  field="extend2" dictionary="sf_yn"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="是否省级"  field="extend3"  dictionary="sf_yn"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="是否市级"  field="extend4"  dictionary="sf_yn"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段5"  field="extend5"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段6"  field="extend6"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段7"  field="extend7"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段8"  field="extend8"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段9"  field="extend9"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段10"  field="extend10"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="baAreaController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="添加" icon="icon-add" url="baAreaController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="baAreaController.do?goUpdate" funname="update"></t:dgToolBar>
   <%-- <t:dgToolBar title="批量删除"  icon="icon-remove" url="baAreaController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar> --%>
   <t:dgToolBar title="查看" icon="icon-search" url="baAreaController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/cn/com/linkwide/ba/baarea/baAreaList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
		$("#baAreaList").treegrid({
 				 onExpand : function(row){
 					var children = $("#baAreaList").treegrid('getChildren',row.id);
 					 if(children.length<=0){
 					 	row.leaf=true;
 					 	$("#baAreaList").treegrid('refresh', row.id);
 					 }
 				}
 		});
 });
 //添加 
 function add(title,addurl,gname,width,height) {
		gridname=gname;
		var rowsData = $('#baAreaList').datagrid('getSelections');
		if(rowsData && rowsData.length>0){
			addurl += '&id='+rowsData[0].id;
		}
		createwindow(title, addurl,width,height);
	}  
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'baAreaController.do?upload', "baAreaList");
}

//导出
function ExportXls() {
	JeecgExcelExport("baAreaController.do?exportXls","baAreaList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("baAreaController.do?exportXlsByT","baAreaList");
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