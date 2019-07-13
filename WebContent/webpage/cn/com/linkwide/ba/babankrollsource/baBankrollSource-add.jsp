<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>资金来源</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="baBankrollSourceController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${baBankrollSourcePage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label" style="color: blue;"><i style="color: red;" class="interval">*</i>编码:
						</label>
					</td>
					<td class="value">
					     	 <input id="code" name="code" type="text" style="width: 150px" class="inputxt" 	validType="ba_bankroll_source,code,id" datatype="*" ignore="checked" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">编码</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label" style="color: blue;"><i style="color: red;" class="interval">*</i>名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="name" name="name" type="text" style="width: 150px" class="inputxt"  ignore="checked" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label" style=" color:blue"><span class="interval">*</span>是否末级:</label>
					</td>
					<td class="value">
						 <t:dictSelect field="iflater" type="list" datatype="*"  typeGroupCode="sf_yn" 
						  	defaultVal="${baBankrollSourcePage.iflater}" hasLabel="false"  title="是否末级"></t:dictSelect>
				     	 <!-- <input id="vitemtype" name="vitemtype" type="text" style="width: 150px" class="inputxt"    /> -->
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">支出类型</label>
					</td>
				</tr>
				
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/babankrollsource/baBankrollSource.js"></script>		
