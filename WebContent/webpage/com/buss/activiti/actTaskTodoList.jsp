<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<t:tabs id="tt" iframe="true" tabPosition="top">
	<t:tab href="actTaskController.do?needDealInit" icon="" title="待办任务" id="needDeal"></t:tab>
	<t:tab href="actTaskController.do?haveDealInit" icon="" title="已办任务" id="haveDeal"></t:tab>
	<%-- <t:tab href="actTaskController.do?newTaskInit" icon="" title="新建任务" id="newTask"></t:tab> --%>
</t:tabs>

