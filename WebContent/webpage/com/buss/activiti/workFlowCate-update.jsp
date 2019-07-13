<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>门类管理</title>
	<t:base type="jquery,easyui,tools"></t:base>
	<script type="text/javascript">
		$(function() {
			$('#workFlowCateTree').combotree({
				url : 'workFlowCateController.do?tree',
				panelHeight : 200,
				width : 157,
				onClick : function(node) {
					$("#parentId").val(node.id);
				}
			});
		});
	</script>
</head>
<body style="overflow-y: hidden" scroll="no">
	<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="workFlowCateController.do?doUpdate">
		<input type="hidden" id="id" name="id" value="${workFlowCatePage.id }"/>
		<table cellpadding="0" cellspacing="1" class="formtable">
			<tr>
				<td align="right">
					<label class="Validform_label">上级目录:</label>
				</td>
				<td class="value">
					<input id="workFlowCateTree" value="${workFlowCatePage.parent.name}">
					<input id="parentId" name="parent.id" style="display: none;" value="${workFlowCatePage.parent.id}">
					<span class="Validform_checktip"></span>
				</td>
			</tr>
			<tr>
				<td align="right">
					<label class="Validform_label">门类名称:</label>
				</td>
				<td class="value">
					<input class="inputxt" id="name" name="name" ignore="ignore" value="${workFlowCatePage.name}" style="vertical-align: middle;"/>
					<span class="Validform_checktip"></span>
				</td>
			</tr>


		</table>
	</t:formvalid>
</body>