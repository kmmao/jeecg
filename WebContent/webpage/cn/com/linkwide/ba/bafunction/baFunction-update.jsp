<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>功能分类</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="3" action="baFunctionController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${baFunctionPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>功能分类科目档案编码:
							</label>
						</td>
						<td class="value">
						    <input id="vcode" name="vcode" type="text" validType="ba_function,vcode,id" style="width: 150px" class="inputxt"  datatype="*" value='${baFunctionPage.vcode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">功能分类科目档案编码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>功能分类科目档案名称:
							</label>
						</td>
						<td class="value">
						    <input id="vname" name="vname" type="text" style="width: 150px" class="inputxt" datatype="*"  value='${baFunctionPage.vname}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">功能分类科目档案名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								上级分类编码:
							</label>
						</td>
						<td class="value">
							<t:comboList  id="vparent" name="vparent" url="dictListController.do?baFunctionList" value='${baFunctionPage.vparent}' idField="vcode" textField="vcode"  field="vcode,vname,id,"  panelWidth="400" title="科目编码:80,科目名称:120" ></t:comboList>
						    <%-- <input id="vparent" name="vparent" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baFunctionPage.vparent}'/> --%>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">上级分类编码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>是否末级:
							</label>
						</td>
						<td class="value">
							 <t:dictSelect field="viflater" type="list" datatype="*" typeGroupCode="if" hasLabel="false"  title="是否末级" defaultVal="${baFunctionPage.viflater}" ></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否末级</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								是否常用:
							</label>
						</td>
						<td class="value">
							<t:dictSelect field="vuse" type="list" defaultVal='${baFunctionPage.vuse}' typeGroupCode="if" hasLabel="false"  title="是否常用"></t:dictSelect>
						    <%-- <input id="vuse" name="vuse" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baFunctionPage.vuse}'/> --%>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否常用</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								备注:
							</label>
						</td>
						<td class="value">
						    <input id="vmemo" name="vmemo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baFunctionPage.vmemo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/bafunction/baFunction.js"></script>		
