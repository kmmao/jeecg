<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="baSetAccountPeriodList"  rowStyler="interlacingColour" onDblClick="dblDetail" detailUrl="baSetAccountPeriodController.do?goUpdate" checkbox="true" pageSize="12" fitColumns="false" title="会计期间" actionUrl="baSetAccountPeriodController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="年度"  field="periodYear"    queryMode="single" query="true"   width="120"></t:dgCol>
   <t:dgCol title="月度"  field="periodMonth"   queryMode="single" query="true"  width="120"></t:dgCol>
   <t:dgCol title="月结开始日期"  field="beginDate" formatter="yyyy-MM-dd"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="月结结束日期"  field="endDate" formatter="yyyy-MM-dd"   queryMode="group"  width="120"></t:dgCol>
   <%-- <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="baSetAccountPeriodController.do?doDel&id={id}" /> --%>
   <%-- <t:dgToolBar title="添加" icon="icon-add" url="baSetAccountPeriodController.do?goAdd" funname="add"></t:dgToolBar> --%>
   <t:dgToolBar title="增加年度" icon="icon-add" url="baSetAccountPeriodController.do?goAddForYear" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="baSetAccountPeriodController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除" operationCode="batchDel" icon="icon-remove" url="baSetAccountPeriodController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="baSetAccountPeriodController.do?goUpdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/cn/com/linkwide/ba/setaccountperiod/baSetAccountPeriodList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
	 $("input[name='periodYear']").val(new Date().getFullYear());
	 $("#baSetAccountPeriodListtb").find("input[name='periodYear']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy'});});
	 $("#baSetAccountPeriodListtb").find("input[name='periodMonth']").parent().hide();
 		//给时间控件加上样式
	$("#baSetAccountPeriodListtb").find("input[name='beginDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	$("#baSetAccountPeriodListtb").find("input[name='beginDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	$("#baSetAccountPeriodListtb").find("input[name='endDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	$("#baSetAccountPeriodListtb").find("input[name='endDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	$("#baSetAccountPeriodListtb").find("input[name='createDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	$("#baSetAccountPeriodListtb").find("input[name='createDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	$("#baSetAccountPeriodListtb").find("input[name='updateDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	$("#baSetAccountPeriodListtb").find("input[name='updateDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	
	setTimeout(function(){baSetAccountPeriodListsearch()}, 300);
 });
 
 
 
 </script>