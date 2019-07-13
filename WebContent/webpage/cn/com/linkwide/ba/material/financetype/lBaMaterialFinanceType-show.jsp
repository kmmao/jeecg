<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>物资分类</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="lBaMaterialTypeController.do?doUpdate" tiptype="1" beforeSubmit="beforevalidate()">
					<input id="id" name="id" type="hidden" value="${lBaMaterialFinanceTypePage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								分类编码:
							</label>
						</td>
						<td class="value">
						     	 <input id="typeCode" name="typeCode" type="text" style="width: 150px" class="inputxt" datatype="*" value='${lBaMaterialFinanceTypePage.typeCode}' disabled="disabled">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">编码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								分类名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="typeName" name="typeName" type="text" style="width: 150px" class="inputxt" datatype="*" value='${lBaMaterialFinanceTypePage.typeName}' disabled="disabled">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">名称</label>
						</td>
					</tr>
					
					<tr>
						<td align="right">
							<label class="Validform_label">
								是否停用
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="status" type="list" id="status"
										typeGroupCode="whether" defaultVal="${lBaMaterialFinanceTypePage.status}" hasLabel="false"  title="是否停用" datatype="*" readonly="readonly"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否停用</label>
						</td>
					</tr>
					
				
			</table>
		</t:formvalid>
 </body>
  <script src="webpage/cn/com/linkwide/ba/material/financetype/lBaMaterialFinanceType.js"></script>