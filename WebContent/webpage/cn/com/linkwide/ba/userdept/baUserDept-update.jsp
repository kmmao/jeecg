<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>用户科室对照</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  function setUser(row,data){
	  $("#userName").val(data.realName)
  }
  
  function setDept(row,data){
	  $("#deptName").val(data.departname)
  }
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="baUserDeptController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${baUserDeptPage.id }"/>
					<input id="deptName" name="deptName" type="hidden"  readonly="readonly"  style="width: 150px" class="inputxt"  ignore="ignore"  value="${baUserDeptPage.deptName }" />
					<input id="userName" name="userName" type="hidden" style="width: 150px" readonly="readonly" class="inputxt"  ignore="ignore"  value="${baUserDeptPage.userName }" />
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		
				<tr>
					<td align="right">
						<label class="Validform_label">
							用户编码:
						</label>
					</td>
					<td class="value">
							 <t:comboList id="userCode"  name="userCode"  url="dictListController.do?userList" datatype="*"	onSelect="setUser"   idField="userName" textField="realName"  
	 						 field="userName,realName,id," panelWidth="400" title="编码:80,用户名称:120"  value="${baUserDeptPage.userCode }" ></t:comboList>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户编码</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							科室编码:
						</label>
					</td>
					<td class="value">
					     	 <t:comboList id="deptCode"  name="deptCode" url="dictListController.do?departList" idField="orgCode" textField="departname" ignore="checked" 		 value="${baUserDeptPage.deptCode }"			 
 							 field="orgCode,departname,description,id,orgType,iflater,mobile,address," 	onSelect="setDept" datatype="*" panelWidth="400" title="科室编码:80,科室名称:120" ></t:comboList>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">科室编码</label>
						</td>
				</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/userdept/baUserDept.js"></script>		
