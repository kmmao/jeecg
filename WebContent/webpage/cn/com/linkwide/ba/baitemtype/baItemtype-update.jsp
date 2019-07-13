<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>项目大类</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="baItemtypeController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${baItemtypePage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								项目大类编码:
							</label>
						</td>
						<td class="value">
						    <input id="vitemcode" name="vitemcode" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baItemtypePage.vitemcode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">项目大类编码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								项目大类名称:
							</label>
						</td>
						<td class="value">
						    <input id="vitemname" name="vitemname" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baItemtypePage.vitemname}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">项目大类名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								外部系统项目大类编码:
							</label>
						</td>
						<td class="value">
						    <input id="voutcode" name="voutcode" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baItemtypePage.voutcode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">外部系统项目大类编码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								外部系统项目大类名称:
							</label>
						</td>
						<td class="value">
						    <input id="voutname" name="voutname" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baItemtypePage.voutname}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">外部系统项目大类名称</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/baitemtype/baItemtype.js"></script>		
