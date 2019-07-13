<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>辅助计量单位</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="lBaMaterialMeasureUnitController.do?doUpdate" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${lBaMaterialMeasureUnitPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
					<td align="right">
						<label class="Validform_label">
							计量单位:
						</label>
					</td>
					<td class="value">
					     	 <input id="materUnitName" name="materUnitName" type="text" style="width: 150px" class="inputxt" value="${tName}" datatype="*1-36" onclick="openMaterUnitSelect()">
					     	 <input id="materUnitId" name="materUnitId" type="hidden" readonly="readonly" value="${lBaMaterialMeasureUnitPage.materUnitId }">
							<span class="Validform_checktip" style="color:red;">*</span>
							<label class="Validform_label" style="display: none;">计量单位</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							换算率:
						</label>
					</td>
					<td class="value">
					     	 <input id="converRate" name="converRate" type="text" style="width: 150px" class="inputxt" value="${lBaMaterialMeasureUnitPage.converRate }" datatype="d2">
							<span class="Validform_checktip" style="color:red;">*</span>
							<label class="Validform_label" style="display: none;">换算率</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
<script src = "webpage/cn/com/linkwide/ba/material/measureunit/lBaMaterialMeasureUnit.js"></script>			
