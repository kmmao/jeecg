<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>预警信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="baWarningInfoController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${baWarningInfoPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							预警源:
						</label>
					</td>
					<td class="value">
					     	 <input id="warningSource" name="warningSource" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">预警源</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							预警信息:
						</label>
					</td>
					<td class="value">
					     	 <input id="warningInfo" name="warningInfo" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">预警信息</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							预警依据:
						</label>
					</td>
					<td class="value">
					     	 <input id="warningBasis" name="warningBasis" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">预警依据</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							模块预警单据id:
						</label>
					</td>
					<td class="value">
					     	 <input id="tableId" name="tableId" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">模块预警单据id</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							预警时间:
						</label>
					</td>
					<td class="value">
					     	 <input id="warningDate" name="warningDate" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">预警时间</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							预留1:
						</label>
					</td>
					<td class="value">
					     	 <input id="v1" name="v1" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">预留1</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							预留2:
						</label>
					</td>
					<td class="value">
					     	 <input id="v2" name="v2" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">预留2</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							预留3:
						</label>
					</td>
					<td class="value">
					     	 <input id="v3" name="v3" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">预留3</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							预留4:
						</label>
					</td>
					<td class="value">
					     	 <input id="v4" name="v4" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">预留4</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							预留5:
						</label>
					</td>
					<td class="value">
					     	 <input id="v5" name="v5" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">预留5</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							预留6:
						</label>
					</td>
					<td class="value">
					     	 <input id="v6" name="v6" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">预留6</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							预留7:
						</label>
					</td>
					<td class="value">
					     	 <input id="v7" name="v7" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">预留7</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							预留8:
						</label>
					</td>
					<td class="value">
					     	 <input id="v8" name="v8" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">预留8</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							预留9:
						</label>
					</td>
					<td class="value">
					     	 <input id="v9" name="v9" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">预留9</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							预留10:
						</label>
					</td>
					<td class="value">
					     	 <input id="v10" name="v10" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">预留10</label>
						</td>
				<td align="right">
					<label class="Validform_label">
					</label>
				</td>
				<td class="value">
				</td>
					</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/bawarninginfo/baWarningInfo.js"></script>		
