<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>资质认证</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
 <div class="easyui-layout" fit="true">
	<div data-options="region:'north',
	collapsed:false,
	split:true,
	border:false,
	"
	style="width: 100%; overflow: hidden;height: 80px;">
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="lSuMaterialQualController.do?doUpdate" tiptype="1" beforeSubmit="check_sub()">
					<input id="id" name="id" type="hidden" value="${lSuMaterialQualPage.id }">
		<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
						<td align="right">
							<label class="Validform_label">
								单据编码:
							</label>
						</td>
						<td class="value">
						     	 <input id="billNo" name="billNo" type="text" style="width: 150px" class="inputxt" datatype="*1-36" value='${lSuMaterialQualPage.billNo}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">单据编码</label>
							<font color="red">*</font>
						</td>
						<td align="right">
							<label class="Validform_label">
								物资:
							</label>
						</td>
						<td class="value">
						     	<%--  <input id="materialId" name="materialId" type="text" style="width: 150px" class="inputxt" datatype="*1-36" value='${lSuMaterialQualPage.materialId}'> --%>
						     	<input id="materialName" name="materialName" type="text" style="width: 150px" value='${materialName}' class="inputxt"  datatype="*1-36" onclick="openSelectMaterial()">
					     	 <input id="materialId" name="materialId" type="hidden" readonly="readonly" value='${lSuMaterialQualPage.materialId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">物资ID</label>
							<font color="red">*</font>
						</td>
					</tr>
					
					
			</table>
		</t:formvalid>
		</div>
		<div region="center" style="padding:0px;border:0px">
		<t:datagrid name="lSuMaterialQualItemList"  fitColumns="false" title="物资资质" actionUrl="lSuMaterialQualItemController.do?datagrid&materialQualId=${lSuMaterialQualPage.id }" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="物资认证ID"  field="materialQualId"  hidden="true"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="资质类型"  field="qualTypeId"  dictionary="l_su_qual_type,id,type_name"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="资质编码"  field="qualCode"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="资质名称"  field="qualFullName"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="生效日期"  field="effectDate" formatter="yyyy-MM-dd"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="到期日期"  field="overDate" formatter="yyyy-MM-dd"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="是否到期控制 "  field="isControl"    queryMode="group"  width="120"></t:dgCol>
  </t:datagrid>
		</div>
		</div>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/material/qual/lSuMaterialQual.js"></script>			
