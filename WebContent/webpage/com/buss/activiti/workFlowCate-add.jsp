<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>分类管理</title>
	<t:base type="jquery,easyui,tools"></t:base>
	<script type="text/javascript">
		$(function() {
			$('#workFlowCateTree').combotree({
				url : 'workFlowCateController.do?tree',
				panelHeight : 200,
				width : 157,
				value : W.getSelectNodeId(),
				onClick : function(node) {
					$("#parentId").val(node.id);
				}
			});
			
			$("#parentId").val(W.getSelectNodeId());
		});

	</script>
</head>
<body style="overflow-y: hidden" scroll="no">
	<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="workFlowCateController.do?doAdd">
		<table cellpadding="0" cellspacing="1" class="formtable">
			<tr>
				<td align="right">
					<label class="Validform_label">上级分类:</label>
				</td>
				<td class="value">
					<input id="workFlowCateTree" value="${workFlowCatePage.parent.name}">
					<input id="parentId" name="parent.id" style="display: none;" value="">
					<span class="Validform_checktip"></span>
				</td>
			</tr>
			<tr>
				<td align="right">
					<label class="Validform_label">分类名称:</label>
				</td>
				<td class="value">
					<input class="inputxt" id="name" name="name"  value="" style="vertical-align: middle;" datatype="*"/>
					<span class="Validform_checktip"></span>
				</td>
			</tr>

		</table>
	</t:formvalid>
</body>