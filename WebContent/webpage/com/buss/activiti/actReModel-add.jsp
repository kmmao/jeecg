<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<title>act_re_model</title>
		<t:base type="jquery,easyui,tools,DatePicker"></t:base>
		<script type="text/javascript">
  		//编写自定义JS代码
  		function editWorkFlow(data){

  			var win = frameElement.api.opener;

     		if (data.success == true) {
     		
				//打开流程设计器
				window.open("modeler.html?modelId=" + data.attributes.modelId);
				frameElement.api.close();
				win.tip(data.msg);
				win.reloadTable();

			} else {
				if (data.responseText == '' || data.responseText == undefined) {
					$.messager.alert('错误', data.msg);
					$.Hidemsg();
				} else {
					try {
						var emsg = data.responseText.substring(data.responseText.indexOf('错误描述'),data.responseText.indexOf('错误信息'));
						$.messager.alert('错误',emsg);
						$.Hidemsg();
					} catch (ex) {
						$.messager.alert('错误',data.responseText+"");
						$.Hidemsg();
					}
				}
				return false;
			}
  		}
  		$(function() {
			$('#workFlowCateTree').combotree({
				url : 'workFlowCateController.do?tree',
				panelHeight : 200,
				width : 157,
				onClick : function(node) {
					$("#category").val(node.id);
				}
			});
			
			
		});
  		</script>
	</head>
	<body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="actReModelController.do?doAdd" tiptype="1" callback="@Override editWorkFlow">
			<input id="id" name="id" type="hidden" value="${actReModelPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							模块分类:
						</label>
					</td>
					<td class="value">
					<input id="workFlowCateTree" value="${category}">

					<input id="category" name="category" style="display: none;" value="${category}">
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">
							模块分类
						</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							模块名称:
						</label>
					</td>
					<td class="value">
						<input id="name" name="name" type="text" style="width: 150px" class="inputxt" datatype="*">
						<span class="Validform_checktip"><font color="red">*</font></span>
						<label class="Validform_label" style="display: none;">
							模块名称
						</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							模块标识:
						</label>
					</td>
					<td class="value">
						<input id="key" name="key" type="text" style="width: 150px" class="inputxt" datatype="*">
						<span class="Validform_checktip"><font color="red">*</font></span>
						<label class="Validform_label" style="display: none;">
							模块标识
						</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							模块描述:
						</label>
					</td>
					<td class="value">
						<textarea style="width:600px;" class="inputxt" rows="10" id="description" name="description"></textarea>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">
							模块描述
						</label>
					</td>
				</tr>
			</table>
		</t:formvalid>
	</body>
	<script src="webpage/com/buss/activiti/actReModel.js"></script>