<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>资质分类</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <link rel="stylesheet" type="text/css" href="plug-in/ace/css/common.css" />
  <script type="text/javascript">
  //编写自定义JS代码
  
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="lBaMaterialQualTypeController.do?doAdd" tiptype="1" loadValiJs="false" callback="@Override subCallback" beforeSubmit="check_sub()">
					<input id="id" name="id" type="hidden" value="${lSuQualTypePage.id }">
					<input id="parentId" name="parentId" type="hidden" value="${pid}">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
					<font color="red">*</font>
						<label class="Validform_label">
							资质编码:
						</label>
					</td>
					<td class="value">
					     	 <input id="typeCode" name="typeCode" type="text" style="width: 150px" class="inputxt"  datatype="*1-36" value="${pCode }">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">资质编码</label>
							
						</td>
				</tr>
				<tr>
					<td align="right">
					<font color="red">*</font>
						<label class="Validform_label">
							资质名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="typeName" name="typeName" type="text" style="width: 150px" class="inputxt"  datatype="*1-36">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">资质名称</label>
							
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否停用:
						</label>
					</td>
					<td class="value">
					     	 <!-- <input id="status" name="status" type="text" style="width: 150px" class="inputxt"  datatype="*"> -->
					     	 <t:dictSelect field="status" type="list" hasShowCheck="false" hasLabel="false"  typeGroupCode="whether"  title="是否停用"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否停用 </label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>