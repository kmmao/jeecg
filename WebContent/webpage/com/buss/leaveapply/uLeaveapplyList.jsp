<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="uLeaveapplyList" checkbox="true" fitColumns="false" title="员工请假单" actionUrl="uLeaveapplyController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属科室"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="流程状态"  field="bpmStatus"    queryMode="single" dictionary="billstatus" width="120"></t:dgCol>
   <t:dgCol title="请假人员"  field="userid"    queryMode="single" dictionary="t_s_base_user,id,realname"  width="120"></t:dgCol>
   <t:dgCol title="请假类型"  field="leavetype"    queryMode="single" dictionary="leaveType" width="120"></t:dgCol>
   <t:dgCol title="请假原因描述"  field="reason"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="开始时间"  field="startdate" formatter="yyyy-MM-dd hh:mm:ss"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="结束时间"  field="enddate" formatter="yyyy-MM-dd hh:mm:ss"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="请假天数"  field="days"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="单据类型"  field="busitype"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" formatterjs="contrApply"  width="200"></t:dgCol>
   <t:dgToolBar title="添加" icon="icon-add" url="uLeaveapplyController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="uLeaveapplyController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="uLeaveapplyController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="uLeaveapplyController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/jeecg/leaveapply/uLeaveapplyList.js"></script>		
 <script src = "webpage/com/buss/activiti/workFlowTools.js"></script>		
 <script src = "webpage/com/buss/activiti/workFlowTools.js"></script>
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#uLeaveapplyListtb").find("input[name='createDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#uLeaveapplyListtb").find("input[name='updateDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#uLeaveapplyListtb").find("input[name='startdate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#uLeaveapplyListtb").find("input[name='enddate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'uLeaveapplyController.do?upload', "uLeaveapplyList");
}

//导出
function ExportXls() {
	JeecgExcelExport("uLeaveapplyController.do?exportXls","uLeaveapplyList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("uLeaveapplyController.do?exportXlsByT","uLeaveapplyList");
}
//操作处理
function  contrApply(value,row,index){
	 var id = row.id;
	 var bpmStatus = row.bpmStatus;
	 var url = "";
	 if(bpmStatus=='0'){
		 url += "<a href='#' onclick='delApply(&quot;"+id+"&quot;)'>[删除]</a> &nbsp;&nbsp;&nbsp;";
		 url += "<a href='#' onclick='commitApply(&quot;"+id+"&quot;)'>[提交申请]</a> &nbsp;&nbsp;&nbsp;";
	 }else{
		 url += "<a href='#' onclick='viewFlow(&quot;"+id+"&quot;)'>[查看流程进度]</a> &nbsp;&nbsp;&nbsp;"; 
	 }
	 return url;
}
//删除申请单
function delApply(id){
	 var url = "uLeaveapplyController.do?doDel&id="+id;
	 createdialog('删除 ', '确定删除吗 ?', url,"删除");
}
//提交申请单
function commitApply(id){
	 var url = "uLeaveapplyController.do?doCommit&id="+id;
	 doFun(url,"uLeaveapplyList");
	 
	 $.ajax({
		 type:"POST",
		 url:url,
		 success:function(data){
			 var d = $.parseJSON(data);
			 if(d.success){
				 $("#"+formId).datagrid("reload");
			 }
		 }
	 });
}
//查看流程进度
function viewFlow(id){
	//获取：processDefinitionId processInstanceId
	var url = "actTaskController.do?getProcessMsg"
			+"&className=com.buss.leaveapply.entity.ULeaveapplyEntity"
			+"&busiID="+id;
	$.ajax({
		type : 'GET',
		url : url,
		success : function(data) {
			var processDefinitionId = data.processDefinitionId;
			var processInstanceId = data.processInstanceId;
			var url = "diagram-viewer/index.html?processDefinitionId=" + processDefinitionId
					+ "&processInstanceId=" + processInstanceId;
			var buttons = [];
			openDefWindow(url,"流程进度",1000,600,buttons);
		}
	});
}
 </script>