<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div data-options="region:'west', collapsed:false, split:true, border:false " style="width: 150px;">
		<ul id="baProtypeList_type"></ul>
	</div>
	<input type="hidden" id="nodeId">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="baProtypeList" checkbox="false" pagination="false" treegrid="true" treeField="vname" fitColumns="true" title="项目分类" actionUrl="baProtypeController.do?datagrid" idField="id" fit="true" queryMode="group">
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
   <t:dgCol title="项目分类编码"  field="vcode" align="left"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="项目分类名称"  field="vname" align="left"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="外部系统项目分类编码"  field="voutcode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="外部系统项目分类名称"  field="voutname"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="上级分类编码"  field="pkparent"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属项目大类"  field="pkbaitemtype"  queryMode="single"  dictionary="ba_itemtype,vitemcode,vitemname"  width="120"></t:dgCol>
   <t:dgCol title="是否末级"  field="viflater"  queryMode="single"  dictionary="sf_yn"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段1"  field="extend1"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段2"  field="extend2"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段3"  field="extend3"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段4"  field="extend4"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段5"  field="extend5"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段6"  field="extend6"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段7"  field="extend7"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段8"  field="extend8"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段9"  field="extend9"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段10"  field="extend10"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="baProtypeController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="添加" icon="icon-add" url="baProtypeController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="baProtypeController.do?goUpdate" funname="update"></t:dgToolBar>
   <%-- <t:dgToolBar title="批量删除"  icon="icon-remove" url="baProtypeController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar> --%>
   <t:dgToolBar title="查看" icon="icon-search" url="baProtypeController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/cn/com/linkwide/ba/baprotype/baProtypeList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
	 $("#baProtypeList").treegrid({
			 onExpand : function(row){
				var children = $("#baProtypeList").treegrid('getChildren',row.id);
				 if(children.length<=0){
				 	row.leaf=true;
				 	$("#baProtypeList").treegrid('refresh', row.id);
				 }
			}
	});
	 //加载项目大类树
	 typeList();
 });
 
 function typeList(){
	 //加载项目大类树
	 $('#baProtypeList_type').tree({
			 method:"POST",
			 lines:true,
			 url:"baItemtypeController/getTreeAllDateForstate.do",
			 onClick: function(node){
				 var ids = [];
				 ids.push(node.id);
				 $("#nodeId").val(node.id);
				 $('#baProtypeList').treegrid('options').url = 'baProtypeController.do?datagrid&typeIds='+ids+'&field=id,vcode,vname,voutcode,voutname,pkparent,pkbaitemtype,viflater,extend1,extend2,extend3,extend4,extend5,';
				 $("#baProtypeList").treegrid('reload');
			}
		});
 };
 
function add(title,addurl,gname,width,height) {
	gridname=gname;
	addurl+="&pkbaitemtype="+$("#nodeId").val();
	createwindow(title, addurl,width,height);
}  
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'baProtypeController.do?upload', "baProtypeList");
}

//导出
function ExportXls() {
	JeecgExcelExport("baProtypeController.do?exportXls","baProtypeList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("baProtypeController.do?exportXlsByT","baProtypeList");
}

 </script>