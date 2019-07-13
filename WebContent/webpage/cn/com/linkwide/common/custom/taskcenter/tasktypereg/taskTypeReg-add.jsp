<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>任务类型注册</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script type="text/javascript">
	$(document).ready(function() {
		$('#tt').tabs({
			onSelect : function(title) {
				$('#tt .panel-body').css('width', 'auto');
			}
		});
		$(".tabs-wrap").css('width', '100%');
	});
</script>
</head>
<body style="overflow-x: hidden;">
	<t:formvalid formid="formobj" dialog="true" usePlugin="password"
		layout="table" tiptype="1" action="taskTypeRegController.do?doAdd">
		<input id="id" name="id" type="hidden" value="${taskTypeRegPage.id }">
		<input id="createName" name="createName" type="hidden"
			value="${taskTypeRegPage.createName }">
		<input id="createBy" name="createBy" type="hidden"
			value="${taskTypeRegPage.createBy }">
		<input id="createDate" name="createDate" type="hidden"
			value="${taskTypeRegPage.createDate }">
		<input id="updateName" name="updateName" type="hidden"
			value="${taskTypeRegPage.updateName }">
		<input id="updateBy" name="updateBy" type="hidden"
			value="${taskTypeRegPage.updateBy }">
		<input id="updateDate" name="updateDate" type="hidden"
			value="${taskTypeRegPage.updateDate }">
		<input id="deployer" name="deployer" type="hidden"
			value="${taskTypeRegPage.deployer }">
		<input id="vdef1" name="vdef1" type="hidden"
			value="${taskTypeRegPage.vdef1 }">
		<input id="vdef2" name="vdef2" type="hidden"
			value="${taskTypeRegPage.vdef2 }">
		<input id="vdef3" name="vdef3" type="hidden"
			value="${taskTypeRegPage.vdef3 }">
		<input id="vdef4" name="vdef4" type="hidden"
			value="${taskTypeRegPage.vdef4 }">
		<input id="vdef5" name="vdef5" type="hidden"
			value="${taskTypeRegPage.vdef5 }">
		<input id="vdef6" name="vdef6" type="hidden"
			value="${taskTypeRegPage.vdef6 }">
		<table cellpadding="0" cellspacing="1" class="formtable">
			<tr>
				<td align="right"><label class="Validform_label">任务名称:</label>
				</td>
				<td class="value"><input id="taskName" name="taskName"
					type="text" style="width: 150px" class="inputxt"> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">任务名称</label></td>
				<td align="right"><label class="Validform_label">所属模块:</label>
				</td>
				<td class="value"><input id="belongMod" name="belongMod"
					type="text" style="width: 150px" class="inputxt"> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">所属模块</label></td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label">业务插件:</label>
				</td>
				<td class="value"><input id="businesPlugIn"
					name="businesPlugIn" type="text" style="width: 150px"
					class="inputxt"> <span class="Validform_checktip"></span> <label
					class="Validform_label" style="display: none;">业务插件</label></td>
				<td align="right"><label class="Validform_label">描述:</label></td>
				<td class="value"><input id="taskDesc" name="taskDesc"
					type="text" style="width: 150px" class="inputxt"> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">描述</label></td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label">类型:</label></td>
				<td class="value"><t:dictSelect field="type" type="list"
						typeGroupCode="taskType" hasLabel="false" title="类型"></t:dictSelect>
					<span class="Validform_checktip"></span> <label
					class="Validform_label" style="display: none;">类型</label></td>
			</tr>
		</table>
		<div style="width: auto; height: 200px;">
			<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
			<div style="width: 800px; height: 1px;"></div>
			<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				<t:tab
					href="taskTypeRegController.do?taskParamList&id=${taskTypeRegPage.id}"
					icon="icon-search" title="列表参数" id="taskParam"></t:tab>
			</t:tabs>
		</div>
	</t:formvalid>
	<!-- 添加 附表明细 模版 -->
	<table style="display: none">
		<tbody id="add_taskParam_table_template">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh"></div></td>
				<td align="center"><input style="width: 20px;" type="checkbox"
					name="ck" /></td>
				<td align="center"><input name="taskParamList[#index#].name"
					maxlength="32" type="text" class="inputxt" style="width: 120px;">
					<label class="Validform_label" style="display: none;">名称</label></td>
				<td align="center"><input
					name="taskParamList[#index#].paramDesc" maxlength="500" type="text"
					class="inputxt" style="width: 120px;"> <label
					class="Validform_label" style="display: none;">描述</label></td>
				<td align="center"><select name="taskParamList[#index#].type"
					onchange="chtype(this)" style="width: 120px;">
						<option value="">请选择</option>
						<option value="字符型">字符型</option>
						<option value="逻辑型">逻辑型</option>
						<option value="整型">整型</option>
						<option value="Double型">Double型</option>
						<option value="参照基础档案">参照基础档案</option>
						<option value="下拉框">下拉框</option>
				</select> <label class="Validform_label" style="display: none;">类型</label></td>
				<td align="center">
						<input name="taskParamList[#index#].referName" maxlength="50" disabled="disabled"
							type="text" class="inputxt" style="width: 120px;">
						<select name="taskParamList[#index#].referName" style="width: 120px; display: none"  disabled="disabled" >
							<option value="请选择">请选择</option>
				 			<option value="参照档案一">参照档案一</option>
				 			<option value="参照档案二">参照档案二</option>
				 			<option value="参照档案三">参照档案三</option>
				 			<option value="参照档案四">参照档案四</option>
						</select>	
					<label class="Validform_label" style="display: none;">参照名称</label>
				</td>
				<td align="center"><t:dictSelect
						field="taskParamList[#index#].isNull" type="list"
						typeGroupCode="sf_yn" defaultVal="" hasLabel="false" title="是否非空"></t:dictSelect>
					<label class="Validform_label" style="display: none;">是否非空</label>
				</td>
			</tr>
		</tbody>
	</table>
</body>
<script
	src="webpage/cn/com/linkwide/cost/custom/taskcenter/tasktypereg/taskTypeReg.js"></script>
<script type="text/javascript">
	//获取选中类型值，联动参照名称
	function chtype(obj) {
		//类型的name
		var name = obj.name;
		var index = obj.selectedIndex; // 选中索引
		var text = obj.options[index].text; // 选中文本
		var refer = name.replace(/type/, "referName");//参照名称的name
		//var referObj=document.getElementsByName(refer);//参照名称输入框对象
		//判断类型的值是否为空
		if (isNotEmpty(refer)) {
			if (text == '参照基础档案') {
				//隐藏输入框
				 $("input[name='"+refer+"']").val("");
				 $("input[name='"+refer+"']").css('display','none');
				 $("input[name='"+refer+"']").attr("disabled","disabled");
				 //显示下拉框
				 $("select[name='"+refer+"']").css('display','block');
				 $("select[name='"+refer+"']").removeAttr("disabled");
			} else if (text == '下拉框') {
				//隐藏下拉框
				$("select[name='"+refer+"']").val("");
				$("select[name='"+refer+"']").css('display','none');
				$("select[name='"+refer+"']").attr("disabled","disabled");
				 //显示下拉框
				$("input[name='"+refer+"']").removeAttr("disabled");
				$("input[name='"+refer+"']").css('display','block');
				//不做处理
			}else{
				//清空输入框的值
				$("input[name='"+refer+"']").val("");
				$("input[name='"+refer+"']").css('display','block');
				$("input[name='"+refer+"']").attr("disabled","disabled");
				//隐藏下拉框
				$("select[name='"+refer+"']").val("");
				$("select[name='"+refer+"']").css('display','none');
				$("select[name='"+refer+"']").attr("disabled","disabled");
			}
		}
	}
	//非空判断
	function isNotEmpty(s) {
		return ((s != undefined && s != null && s != "") ? true : false);
	}
</script>
</html>