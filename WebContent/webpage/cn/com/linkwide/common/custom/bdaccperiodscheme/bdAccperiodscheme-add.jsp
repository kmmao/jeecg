<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>会计期间方案</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="bdAccperiodschemeController.do?doAdd" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${bdAccperiodschemePage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							方案编码:
						</label>
					</td>
					<td class="value">
					     	 <input id="accperiodschemecode" name="accperiodschemecode" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">方案编码</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							方案名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="accperiodschemename" name="accperiodschemename" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">方案名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							删除标志:
						</label>
					</td>
					<td class="value">
					     	 <input id="dr" name="dr" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">删除标志</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value">
					     	 <input id="memo" name="memo" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							时间戳:
						</label>
					</td>
					<td class="value">
					     	 <input id="ts" name="ts" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">时间戳</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/cost/custom/bdaccperiodscheme/bdAccperiodscheme.js"></script>		
