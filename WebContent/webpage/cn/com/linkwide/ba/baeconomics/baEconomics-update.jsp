<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>经济分类</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="baEconomicsController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${baEconomicsPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>部门预算经济分类编码:
							</label>
						</td>
						<td class="value">
						    <input id="vcode" name="vcode" type="text" style="width: 150px" class="inputxt"  datatype="*"  value='${baEconomicsPage.vcode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">部门预算经济分类编码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>部门预算经济分类名称:
							</label>
						</td>
						<td class="value">
						    <input id="vname" name="vname" type="text" style="width: 150px" class="inputxt"  datatype="*"  value='${baEconomicsPage.vname}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">部门预算经济分类名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>政府预算经济分类编码:
							</label>
						</td>
						<td class="value">
						    <input id="vcode" name="govCode" type="text" style="width: 150px" class="inputxt"  datatype="*"  value='${baEconomicsPage.govCode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">政府预算经济分类编码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>政府预算经济分类名称:
							</label>
						</td>
						<td class="value">
						    <input id="vname" name="govName" type="text" style="width: 150px" class="inputxt"  datatype="*"  value='${baEconomicsPage.govName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">政府预算经济分类名称</label>
						</td>
					</tr>
					<%-- <tr>
						<td align="right">
							<label class="Validform_label">
								上级分类编码:
							</label>
						</td>
						<td class="value">
						    <input id="vparent" name="vparent" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baEconomicsPage.vparent}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">上级分类编码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								是否常用:
							</label>
						</td>
						<td class="value">
						    <input id="vuse" name="vuse" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baEconomicsPage.vuse}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否常用</label>
						</td>
					</tr> --%>
					<tr>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>是否末级:
							</label>
						</td>
						<td class="value">
							 <t:dictSelect field="viflater" type="list" datatype="*" typeGroupCode="if" hasLabel="false"  title="是否末级" defaultVal="${baEconomicsPage.viflater}" ></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否末级</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								备注:
							</label>
						</td>
						<td class="value">
						    <input id="vmemo" name="vmemo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baEconomicsPage.vmemo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/baeconomics/baEconomics.js"></script>		
