<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>收发类别</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="lBaReceiveTypeController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${lBaReceiveTypePage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							类别编码:
						</label>
					</td>
					<td class="value">
					     	 <input id="code" name="code" type="text" style="width: 150px" class="inputxt" 	datatype="*" ignore="checked" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">类别编码</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							类别名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="name" name="name" type="text" style="width: 150px" class="inputxt" 	datatype="*" ignore="checked" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">类别名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							出入库类型:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="isIn" type="radio"  datatype="n"  typeGroupCode="is_in_out"  defaultVal="0" hasLabel="false"  title="出入库" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">出入库类型</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否停用:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="isStop" type="radio"  datatype="n"  typeGroupCode="yes_no_int"  defaultVal="0" hasLabel="false"  title="是否停用" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否停用</label>
						</td>
				</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/receivetype/lBaReceiveType.js"></script>		
