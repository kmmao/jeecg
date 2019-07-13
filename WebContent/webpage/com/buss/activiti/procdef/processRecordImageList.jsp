<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>流程记录</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body> 
 	<iframe height="100%"  width="100%" style="min-height:460px;" src="actTaskController.do?queryProcessImage&busId=${busId}&workflowId=${workflowId}"></iframe>
</body>
</html>
 <script type="text/javascript">

</script> 