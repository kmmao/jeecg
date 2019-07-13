<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>任务处理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body> 
 
 <!-- 历史审批记录 -->
  <div  style="position:relative;padding:0px;border:0px;width:630px;height:200px" >
  <t:datagrid name="taskList" checkbox="true" fitColumns="false" pagination="false"
  	title="批注历史" actionUrl="actTaskController.do?approveDatagrid&taskId=${taskId}" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"   queryMode="single" hidden="true"  width="120"></t:dgCol>
   <t:dgCol title="批注人"  field="userId" dictionary="t_s_base_user,id,realname"   queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="批注时间"  field="time"   queryMode="single"  width="150"></t:dgCol>
   <t:dgCol title="批注内容"  field="fullMessage"   queryMode="single"  width="200"></t:dgCol>
   <t:dgCol title="审批附件"  field="filePath"   queryMode="single"  width="120"></t:dgCol>
  </t:datagrid>
  </div>
</body>
</html>
  