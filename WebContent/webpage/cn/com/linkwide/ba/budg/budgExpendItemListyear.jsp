<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="budgExpendItemYearList" checkbox="false" treegrid="true" treeField="itemName" pagination="false" fitColumns="true" title="支出项目设置"  rowStyler="interlacingColour"  sortName="itemCode" sortOrder="asc"  onDblClick="dblDetail"  detailUrl="budgExpendItemYearController.do?goUpdate" actionUrl="budgExpendItemYearController.do?datagrid" idField="id" fit="true" queryMode="group">
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
   <t:dgCol title="项目档案id"  field="baItemId"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="项目编码"  field="itemCode" align="left" query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="项目名称"  field="itemName" align="left" query="true"  queryMode="single"  width="160"></t:dgCol>
   <t:dgCol title="上级项目编码"  field="parentCode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="是否末级"  field="isLast" dictionary="sf_yn" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="是否停用"  field="extend3"  dictionary="sf_yn"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属类型"  field="extend1"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="项目说明"  field="extend2"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remark"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段4"  field="extend4"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段5"  field="extend5"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段6"  field="extend6"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="budgExpendItemYearController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="添加" icon="icon-add" url="budgExpendItemYearController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="budgExpendItemYearController.do?goUpdate" funname="update1"></t:dgToolBar>
   <%-- <t:dgToolBar title="批量删除"  icon="icon-remove" url="budgExpendItemYearController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar> --%>
   <t:dgToolBar title="查看" icon="icon-see" url="budgExpendItemYearController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/cn/com/linkwide/ba/budg/budgExpendItemListyear.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
		$("#budgExpendItemYearList").treegrid({
				 onExpand : function(row){
					var children = $("#budgExpendItemYearList").treegrid('getChildren',row.id);
					 if(children.length<=0){
					 	row.leaf=true;
					 	$("#budgExpendItemYearList").treegrid('refresh', row.id);
					 }
				}
		});
});
 
//编辑前校验
 function update1(title,url, id,width,height,isRestful) {
		gridname=id;
		var rowsData = $('#'+id).datagrid('getSelections');
		if (!rowsData || rowsData.length==0) {
			tip('请选择编辑项目');
			return;
		}
		if (rowsData.length>1) {
			tip('请选择一条记录再编辑');
			return;
		}
		$.ajax({
			url:"budgExpendItemYearController.do?checkItemIsUsed",
			type:"post",
			data:{
				"itemCode":rowsData[0].itemCode,
				"budgYear":rowsData[0].budgYear
			},
			dataType:"json",
			async:true,
			success:function(data){
				if(data.msg!='操作成功'){
					tip(data.msg);
					return;
				}else{
					if(isRestful!='undefined'&&isRestful){
						url += '/'+rowsData[0].id;
					}else{
						url += '&id='+rowsData[0].id;
					}
					createwindow(title,url,width,height);
				}
			}
		});
		
	}
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'budgExpendItemYearController.do?upload', "budgExpendItemYearList");
}

//导出
function ExportXls() {
	JeecgExcelExport("budgExpendItemYearController.do?exportXls","budgExpendItemYearList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("budgExpendItemYearController.do?exportXlsByT","budgExpendItemYearList");
}

 </script>