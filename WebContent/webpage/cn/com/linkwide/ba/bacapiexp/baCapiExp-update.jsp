<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>资本性支出</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="baCapiExpController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${baCapiExpPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>编码:
							</label>
						</td>
						<td class="value">
						    <input id="code" name="code" type="text" style="width: 150px" class="inputxt" 		validType="ba_capi_exp,code,id" datatype="*" ignore="ignore"  value='${baCapiExpPage.code}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">编码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>名称:
							</label>
						</td>
						<td class="value">
						    <input id="name" name="name" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baCapiExpPage.name}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">名称</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/bacapiexp/baCapiExp.js"></script>		
