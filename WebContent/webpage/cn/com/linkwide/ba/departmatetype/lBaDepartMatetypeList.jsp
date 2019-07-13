<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="lBaDepartMatetypeList" checkbox="true" pagination="true" fitColumns="true"  actionUrl="lBaDepartMatetypeController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="科室"  field="departIdQ"  query="true"  hidden="true"  queryMode="single"   width="120"></t:dgCol>
   <t:dgCol title="科室"  field="departId"    queryMode="single"  dictionary="t_s_depart,id,departname"  width="120"></t:dgCol>
   <t:dgCol title="物资分类"  field="matetypeIdQ"  query="true"   hidden="true" queryMode="single"   width="120"></t:dgCol>
   <t:dgCol title="物资分类"  field="matetypeId"    queryMode="single"  dictionary="l_ba_material_type,id,type_name"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="lBaDepartMatetypeController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="添加" icon="icon-add" url="lBaDepartMatetypeController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="lBaDepartMatetypeController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="lBaDepartMatetypeController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="lBaDepartMatetypeController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div> 
<div style="display:none;">
<t:comboList name="departIdQ" url="dictListController.do?departList" id="departIdQ" idField="id" textField="departname" ignore="checked" 
	 field="orgCode,departname,description,id,orgType,iflater,mobile,address," hasInput="true"
	 panelWidth="400" title="科室编码:80,科室名称:120" ></t:comboList>
			 
 </div>
 <script src = "webpage/cn/com/linkwide/ba/departmatetype/lBaDepartMatetypeList.js"></script>	
 <script type="text/javascript">
 $(document).ready(function(){
	 
	 $(function() { $("input[name='matetypeIdQ'").combotree({		 url :'lBaMaterialTypeController.do?comboTreeType',width :'155',multiple:false });		});
 });
  
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'lBaDepartMatetypeController.do?upload', "lBaDepartMatetypeList");
}

//导出
function ExportXls() {
	JeecgExcelExport("lBaDepartMatetypeController.do?exportXls","lBaDepartMatetypeList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("lBaDepartMatetypeController.do?exportXlsByT","lBaDepartMatetypeList");
}

 </script>