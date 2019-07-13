<%@ page language="java" import="java.util.*"	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<title>m_meeting_template</title>
		<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	</head>
	<body>
		<c:import url="diagram-viewer/index.html?processDefinitionId=${processDefinitionId}&processInstanceId=${processInstanceId}"></c:import>
	</body>
</html>