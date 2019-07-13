<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>任务处理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
	  $(document).ready(function(){
				
	  });
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="actTaskController.do?doClaimAssign" tiptype="1">
			<input id="taskId" name="taskId" hidden="true"  value="${taskId}">
		<table  cellpadding="0" cellspacing="1" class="formtable">
			<tr>
				<td align="right"><label class="Validform_label">指派人员:</label></td>
				<td class="value">
						<input id="newUser" name="newUser" type="text" datatype="*" style="width: 150px" class="inputxt"/>
						<%-- <t:userSelect selectedIdsInputId="newUserid"  selectedNamesInputId="newUserName" selectedCodesInputId="newUser" userNameInputWidth="150px" title="用户选择" ></t:userSelect> --%>
				 			<%-- <t:comboList id="newUserid" name="newUserid"  url="dictListController.do?userList"   idField="userName" textField="realName"  ignore="ignore" field="userName,realName"  panelWidth="400" title="用户账号:80,用户名称:120" ></t:comboList> --%>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">指派人员</label>
				</td>
			</tr>
		</table>
	</t:formvalid>
 </body>	
<script type="text/javascript">
$('#newUser').combogrid({
	panelWidth:500,
	panelHeight:120,
	url: 'dictListController.do?userList&field=userName,realName,id,',
	pagination: true,    
	idField:'userName',
	textField:'realName',
	mode:'remote',
	multiple:false,
	fitColumns:true,
	rownumbers: true,//序号
	columns:[[
		{field:'userName',title:'用户账号',align:'center',width:80},
		{field:'realName',title:'用户名称',align:'center',width:120}
	]],
	onSelect:function(index,data){
		$("input[name='userid']").val(data.userName);
	}
   });
</script>