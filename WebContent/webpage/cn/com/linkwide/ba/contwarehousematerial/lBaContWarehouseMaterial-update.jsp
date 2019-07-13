<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>仓库物资对照</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="lBaContWarehouseMaterialController.do?doUpdate" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${lBaContWarehouseMaterialPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>仓库编码:
							</label>
						</td>
						<td class="value">
						     	 <input id="warehouseCode" name="warehouseCode" type="text" style="width: 150px" class="inputxt" value='${lBaContWarehouseMaterialPage.warehouseCode}' onclick="openSelectWarehouse()">
						     	 <input id="warehouseId" name="warehouseId" type="hidden" value='${lBaContWarehouseMaterialPage.warehouseId}' datatype="*" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">仓库编码</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								仓库名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="warehouseName" name="warehouseName" type="text" style="width: 150px" class="inputxt" readonly="readonly" disabled="disabled" value='${lBaContWarehouseMaterialPage.warehouseName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">仓库名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>物资编码:
							</label>
						</td>
						<td class="value">
						     	 <input id="materialCode" name="materialCode" type="text" style="width: 150px" class="inputxt" value='${lBaContWarehouseMaterialPage.materialCode}' onclick="openSelectMaterial()">
						     	 <input id="materialId" name="materialId" type="hidden" value='${lBaContWarehouseMaterialPage.materialId}' datatype="*" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">物资编码</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								物资名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="materialName" name="materialName" type="text" style="width: 150px" class="inputxt" readonly="readonly" disabled="disabled" value='${lBaContWarehouseMaterialPage.materialName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">物资名称</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/contwarehousematerial/lBaContWarehouseMaterial.js"></script>		
