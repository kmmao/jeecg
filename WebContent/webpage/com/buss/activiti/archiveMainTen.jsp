<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<link rel="stylesheet" href="plug-in/ztree/css/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="plug-in/ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="plug-in/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
<SCRIPT type="text/javascript">
	<!--
	var setting = {
		async: {
			enable: true,
			url:"workFlowCateController.do?tree"
		},
		data: {
			key: {
				name: "text"
			}
		},
		callback: {
			onAsyncSuccess: zTreeOnAsyncSuccess,
			onClick: zTreeOnClick
		}
	};
	
	var treeObj;
	var currentNode = null;
	$(document).ready(function(){
		$.fn.zTree.init($("#ztree"), setting);
		treeObj = $.fn.zTree.getZTreeObj("ztree");
	});
	
	function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
		treeObj.expandAll(true);
		currentNode = treeObj.getNodesByFilter(function (node) { return node.level == 0 }, true).id;
	}
	
	function zTreeOnClick(event, treeId, treeNode) {
		//传入ID
		document.getElementById("workFlowIframe").src = "actReModelController.do?cateModel&cateId=" + treeNode.id;
		
	
		currentNode = treeNode.id;
	};
	
	function getCurrentNode(){
		return currentNode;
	}

	//-->
</SCRIPT>
<div class="easyui-layout" fit="true">
	<div region="west" style="width: 150px;" title="文档目录" split="true" collapsed="false">
		<div class="easyui-panel" style="padding:0px;border:0px" fit="true" border="false">
			<ul id="ztree" class="ztree"></ul>
		</div>
	</div>
	<div region="center" style="padding:0px;border:0px">
		<iframe id="workFlowIframe" src="" width="99%" height="99%"  frameborder="0" scrolling="no"></iframe>
	</div>
</div>
