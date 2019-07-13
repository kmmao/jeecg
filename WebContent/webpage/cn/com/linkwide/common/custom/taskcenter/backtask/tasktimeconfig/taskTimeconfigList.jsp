<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="taskTimeconfigList" checkbox="true" fitColumns="false" title="调度引擎时间配置" actionUrl="taskTimeconfigController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="删除标志"  field="dr"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="有效期开始日期"  field="effibegindate"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="有效期开始时间"  field="effibegintime"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="有效期结束日期"  field="effienddate"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="有效期结束时间"  field="effiendtime"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="每天结束时间"  field="endtime"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="执行日"  field="executeday"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="每天执行间隔"  field="executeinterval"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="执行时间"  field="executetime"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="每天执行间隔单位"  field="exeintervalunit"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="频率类型"  field="frequencytype"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="间隔"  field="interva"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="每天是否循环执行"  field="isruncycledaily"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="后台任务id"  field="taskId"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="每天的开始时间"  field="starttime"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="taskTimeconfigController.do?doDel&id={id}" />
   <t:dgToolBar title="添加" icon="icon-add" url="taskTimeconfigController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="taskTimeconfigController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="taskTimeconfigController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="taskTimeconfigController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/cn/com/linkwide/cost/custom/taskcenter/backtask/taskTimeconfig/taskTimeconfigList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#taskTimeconfigListtb").find("input[name='createDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#taskTimeconfigListtb").find("input[name='updateDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'taskTimeconfigController.do?upload', "taskTimeconfigList");
}

//导出
function ExportXls() {
	JeecgExcelExport("taskTimeconfigController.do?exportXls","taskTimeconfigList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("taskTimeconfigController.do?exportXlsByT","taskTimeconfigList");
}
 </script>