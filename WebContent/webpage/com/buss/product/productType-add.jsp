<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>产品分类</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="productTypeController.do?doAdd" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${productTypePage.id }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${productTypePage.bpmStatus }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${productTypePage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${productTypePage.sysCompanyCode }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							产品类别编号:
						</label>
					</td>
					<td class="value">
					     	 <input id="productTypeCode" name="productTypeCode" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">产品类别编号</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							产品类别名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="productTypeName" name="productTypeName" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">产品类别名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							添加人:
						</label>
					</td>
					<td class="value">
					     	 <input id="updateName" name="updateName" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">添加人</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							更新人:
						</label>
					</td>
					<td class="value">
					     	 <input id="updateBy" name="updateBy" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">更新人</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							添加日期:
						</label>
					</td>
					<td class="value">
							   <input id="createDate" name="createDate" type="text" style="width: 150px" 
					      						class="Wdate" onClick="WdatePicker()"
>    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">添加日期</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							更新日期:
						</label>
					</td>
					<td class="value">
							   <input id="updateDate" name="updateDate" type="text" style="width: 150px" 
					      						class="Wdate" onClick="WdatePicker()"
>    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">更新日期</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/product/productType.js"></script>		
