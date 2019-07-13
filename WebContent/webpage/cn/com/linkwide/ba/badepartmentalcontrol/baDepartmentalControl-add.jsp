<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>科室对照信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="baDepartmentalControlController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${baDepartmentalControlPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							科室国际编码:
						</label>
					</td>
					<td class="value">
					     	 <input id="deptInternationalCode" name="deptInternationalCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">科室国际编码</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							科室国际名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="deptInternationalName" name="deptInternationalName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">科室国际名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							科室HIS编码:
						</label>
					</td>
					<td class="value">
					     	 <input id="deptHisCode" name="deptHisCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">科室HIS编码</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							科室HIS名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="deptHisName" name="deptHisName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">科室HIS名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							预留字段1:
						</label>
					</td>
					<td class="value">
					     	 <input id="reservedFields1" name="reservedFields1" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">预留字段1</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							预留字段2:
						</label>
					</td>
					<td class="value">
					     	 <input id="reservedFields2" name="reservedFields2" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">预留字段2</label>
						</td>
				</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/badepartmentalcontrol/baDepartmentalControl.js"></script>		
