<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>计量单位</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <link rel="stylesheet" type="text/css" href="plug-in/ace/css/common.css" />
</head> 
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="lBaMaterialMaterUnitController.do?doAdd" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${lBaMaterialMaterUnitPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
					<font color="red">*</font>
						<label class="Validform_label">
							计量单位编码:
						</label>
					</td>
					<td class="value">
					     	 <input id="typeCode" name="typeCode" type="text" style="width: 150px" class="inputxt" datatype="*" validType="l_ba_material_mater_unit,type_code,id" ignore="checked">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">计量单位编码</label>
						</td>
				</tr>
				<tr>
					<td align="right">
					<font color="red">*</font>
						<label class="Validform_label">
							计量单位名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="typeName" name="typeName" type="text" style="width: 150px" class="inputxt"  datatype="*" validType="l_ba_material_mater_unit,type_name,id" ignore="checked"  >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">计量单位名称</label>
						</td>
				</tr>
				<tr style="display:none;">
					<td align="right">
						<label class="Validform_label">
							英文名称单数:
						</label>
					</td>
					<td class="value">
					     	 <input id="englishNameSingular" name="englishNameSingular" type="text" style="width: 150px" class="inputxt"  datatype="*1-36" ignore="ignore">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">英文名称单数</label>
						</td>
				</tr>
				<tr  style="display:none;">
					<td align="right">
						<label class="Validform_label">
							英文名称复数:
						</label>
					</td>
					<td class="value">
					     	 <input id="englishNameComplex" name="englishNameComplex" type="text" style="width: 150px" class="inputxt"  datatype="*1-36"  ignore="ignore">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">英文名称复数</label>
						</td>
				</tr>
				<tr>
					<td align="right">
					<font color="red">*</font>
						<label class="Validform_label">
							是否停用:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="status" type="list" id="status"
							typeGroupCode="whether" hasLabel="false" title="是否停用" datatype="*" defaultVal="0" hasShowCheck="false"></t:dictSelect>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">
							是否停用
						</label>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/material/materunit/lBaMaterialMaterUnit.js"></script>		
