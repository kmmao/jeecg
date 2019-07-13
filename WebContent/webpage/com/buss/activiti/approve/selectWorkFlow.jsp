<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>工作流</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <style>
  	#workflowName {
  		float: left;
  	}
  	.value .icons{
  		clear: both;
  	}
  	.value .icons img{
  		margin-left: 5px;
  	}
  </style>
 </head>
 <body>
  	<div align = "center">
		<input id="busiId" name="busiId" type="hidden" value="${busiId}"> 
		
		<table  cellpadding="5" cellspacing="1" >
			<tr colspan='2'>
				<span style="color: red">请选择工作流;若无需工作流，请直接点击确定按钮.</span>
			</tr>
			<tr>
				<td align="left">
					<label class="Validform_label">审批流程:</label>
				</td>
				<td class="value" align="right">
					<t:actReProdefSelect selectedIdsInputId="workflowId" selectedNamesInputId="workflowName" actReProdefNameInputWidth="150px" title="" actReModelCode="${actReModelCode}" ></t:actReProdefSelect>
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">审批流程</label>
				</td>
			</tr>
		</table>
	</div>
 </body>
