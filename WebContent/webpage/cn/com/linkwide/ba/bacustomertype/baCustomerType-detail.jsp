<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>客户类型</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="baCustomerTypeController.do?doUpdate" callback="@Override subCallback">
					<input id="id" name="id" type="hidden" value="${baCustomerTypePage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
						<font color="red">*</font>
							<label class="Validform_label">
								编码:
							</label>
						</td>
						<td class="value">
						    <input id="typeCode" name="typeCode" type="text" style="width: 150px" class="inputxt"  datatype="*1-36" readonly="readonly" value='${baCustomerTypePage.typeCode}'/>
							<span class="Validform_checktip"></span>
							&nbsp&nbsp&nbsp${strcode }<input id="strcode" type="hidden" value="${strcode}">
							<label class="Validform_label" style="display: none;">编码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
						<font color="red">*</font>
							<label class="Validform_label">
								名称:
							</label>
						</td>
						<td class="value">
						    <input id="typeName" name="typeName" type="text" style="width: 150px" class="inputxt"  datatype="*1-36" readonly="readonly"  value='${baCustomerTypePage.typeName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								是否末级:
							</label>
						</td>
						<td class="value">
							<t:dictSelect field="extend1" type="list" hasShowCheck="false" hasLabel="false" typeGroupCode="whether" defaultVal="${baCustomerTypePage.extend1}" title="是否末级" readonly="readonly"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否末级 </label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								是否停用 :
							</label>
						</td>
						<td class="value">
							<t:dictSelect field="status" type="list" hasShowCheck="false" hasLabel="false" typeGroupCode="whether" defaultVal="${baCustomerTypePage.status}" title="是否停用" readonly="readonly"></t:dictSelect>
						   <%--  <input id="status" name="status" type="text" style="width: 150px" class="inputxt"  datatype="*"  ignore="checked"  value='${baCustomerTypePage.status}'/> --%>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否停用 </label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/bacustomertype/baCustomerType.js"></script>		
