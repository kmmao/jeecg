<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>请假单</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="actTaskController.do?doAdd" tiptype="1" >
					<input id="taskId" name="taskId" type="hidden" value="${taskId}">
					<input id="processDefinitionId" name="processDefinitionId" type="hidden" value="${processDefinitionId}">
		<table style="width: 600px;" cellpadding="0" cellspacing="0" class="formtable">
			 <c:forEach var="attr" items="${formAttrlist}" varStatus="status"> 
				<tr>
					<td align="right"><label class="Validform_label">${attr.name}:</label></td>
					<td class="value">
							<%--  <c:choose>  
				                <c:when test="${attr.type=='string'}">  
				                    <input id="${attr.id}" ${attr.toWritableStr()}  type="text" class="inputxt"  ${attr.toRequiredStr()} value="${attr.defaultValue}"/>  
				                </c:when>  
				             
				                <c:when  test="${attr.type=='long'}">  
				                    <input id="${attr.id}" ${attr.toWritableStr()} class="easyui-numberspinner" data-options="increment:1" ${attr.toRequiredStr() } value="${attr.defaultValue}" /> 
				                </c:when>  
				                <c:when  test="${attr.type=='double'}">  
				                    <input id="${attr.id}" ${attr.toWritableStr()} class="easyui-numberspinner" data-options="increment:1" ${attr.toRequiredStr() }  value="${attr.defaultValue}"/> 
				                </c:when> 
				               
				                <c:when  test="${attr.type=='boolean'}">  
				                    <input id="${attr.id}" type="checkbox" ${attr.toRequiredStr()}  class="easyui-checkbox" ${attr.toWritableStr()}  value="${attr.defaultValue}"/>   
				                </c:when>  
				                
				                <c:when test="${attr.type=='date'}">  
				                 	<input id="${attr.id}" name="${attr.id}" type="text"  value="${attr.defaultValue}"
					      						 class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
				                </c:when> 
				                
				                <c:when test="${attr.type=='enum'}">  
				                    <select id="${attr.id}" class="easyui-combobox" ${attr.toWritableStr()}  style="width:173px;">  
				                        <c:forEach var="node" items="${attr.selects}" >  
				                            <option value="${node.attrMap.id}">${node.attrMap.name}</option>  
				                        </c:forEach>  
				                    </select>  
				                </c:when>  
				                <c:otherwise></c:otherwise>  
				            </c:choose>  --%> 
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">${attr.name}</label>
					</td>
				<tr> 
	        </c:forEach>  
		</table>
	</t:formvalid>
 </body>
  <script src = "webpage/com/buss/activiti/workFlowTools.js"></script>		