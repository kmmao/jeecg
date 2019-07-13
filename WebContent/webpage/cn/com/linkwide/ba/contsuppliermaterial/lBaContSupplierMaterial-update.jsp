<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>供应商物资对照</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <link rel="stylesheet" type="text/css" href="plug-in/ace/css/common.css" />
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="lBaContSupplierMaterialController.do?doUpdate" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${lBaContSupplierMaterialPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
						<font color="red">*</font>
							<label class="Validform_label">
								供应商编码:
							</label>
						</td>
						<td class="value">
						     	 <input id="supplierCode" name="supplierCode" type="text" style="width: 150px" class="inputxt" value='${lBaContSupplierMaterialPage.supplierCode}' onclick="openSelectSupplier()">
						     	 <input id="supplierId" name="supplierId" type="hidden" value='${lBaContSupplierMaterialPage.supplierId}' datatype="*" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">供应商</label>
							
						</td>
						<td align="right">
						
							<label class="Validform_label">
								供应商名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="supplierName" name="supplierName" type="text" style="width: 150px" class="inputxt" readonly="readonly" disabled="disabled" value='${lBaContSupplierMaterialPage.supplierName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">供应商</label>
						</td>
					</tr>
					<tr>
						<td align="right">
						<font color="red">*</font>
							<label class="Validform_label">
								物资编码:
							</label>
						</td>
						<td class="value">
						     	 <input id="materialCode" name="materialCode" type="text" style="width: 150px" class="inputxt"  value='${lBaContSupplierMaterialPage.materialCode}' onclick="openSelectMaterial()">
						     	 <input id="materialId" name="materialId" type="hidden" value='${lBaContSupplierMaterialPage.materialId}' datatype="*" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">物资编码</label>
							
						</td>
						<td align="right">
							<label class="Validform_label">
								物资名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="materialName" name="materialName" type="text" style="width: 150px" class="inputxt" readonly="readonly" disabled="disabled" value='${lBaContSupplierMaterialPage.materialName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">物资</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/contsuppliermaterial/lBaContSupplierMaterial.js"></script>		
