<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="baWarningSetList" checkbox="false" pagination="true" fitColumns="true" title="预警设置" actionUrl="baWarningSetController.do?datagrid" idField="id" fit="true" queryMode="group">
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
   <t:dgCol title="预警源"  field="warningSource"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预警信息"  field="warningInfo"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预警依据"  field="warningBasis"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="执行频率数"  field="rateNum" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="执行频率单位"  field="rateUnit" hidden="true" dictionary="date_unit" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预警参数"  field="warningParameter"  queryMode="single"  width="120"></t:dgCol>
<%--    <t:dgCol title="预警提醒天数"  field="warningDays"  queryMode="single"  width="120"></t:dgCol> --%>
   <t:dgCol title="提前预警天数"  field="advanceDay"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="延后预警天数"  field="postponeDay"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预警执行状态"  field="warningState" replace="停用_0,停用_,启用_1" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留1"  field="v1"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留2"  field="v2"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留3"  field="v3"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留4"  field="v4"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留5"  field="v5"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留6"  field="v6"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgToolBar title="编辑" icon="icon-edit" url="baWarningSetController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="baWarningSetController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="启用"  icon="icon-ok" url="baWarningSetController.do?state&flag=1" funname="state" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="停用" icon="icon-cancel" url="baWarningSetController.do?state&flag=0" funname="state" width="100%" height="100%"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/cn/com/linkwide/ba/bawarningset/baWarningSetList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
	 getWarningSource();
 });
 
 function getWarningSource(){
	 $('input[name=warningSource]').combogrid({
		panelWidth: 137,
		mode: 'remote',
		idField: 'warningSource',
		textField: 'warningSource',
		url: 'baWarningSetController.do?getWarningSource',
		method: 'get',
		columns: [[
			{field:'warningSource',title:'预警源',width:80}
		]],
		fitColumns: true,
		 
	})
	 	  
}
 
 /* 
	启用(1)停用(0)状态
*/
function state(title,url,gname) {
	 	gridname=gname;
	     var ids = [];
	     var rows = $("#"+gname).datagrid('getSelections');
	     if (rows.length > 0) {
	     	$.dialog.setting.zIndex = getzIndex(true);
	     	$.dialog.confirm('你确定操作该数据吗?', function(r) {
	     		
	 		   if (r) {
	 				for ( var i = 0; i < rows.length; i++) {
	 					ids.push(rows[i].id);
	 					var v1 = rows[i].warningState;
	 					if(title == '启用' && v1 == '1'){
	 						tip('该任务已启用！');
	 						return;
	 					}
// 	 					if(title == '停用' && (v1 == '0')){
// 	 						tip('该任务未启用，不能停用！');
// 	 						return;
// 	 					}
	 				}
	 				$.ajax({
	 					url : url,
	 					type : 'post',
	 					data : {
	 						ids : ids.join(',')
	 					},
	 					cache : false,
	 					success : function(data) {
	 						var d = $.parseJSON(data);
	 						if (d.success) {
	 							var msg = d.msg;
	 							tip(msg);
	 							reloadTable();
	 							$("#"+gname).datagrid('unselectAll');
	 							ids='';
	 						}
	 					}
	 				});
	 			}
	 		});
	 	} else {
	 		if(title == '启用'){
				tip('请选择要启用的数据！');
				return;
			}
			if(title == '停用'){
				tip('请选择要停用的数据！');
				return;
			}
	 	}
	 }

 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'baWarningSetController.do?upload', "baWarningSetList");
}

//导出
function ExportXls() {
	JeecgExcelExport("baWarningSetController.do?exportXls","baWarningSetList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("baWarningSetController.do?exportXlsByT","baWarningSetList");
}

 </script>