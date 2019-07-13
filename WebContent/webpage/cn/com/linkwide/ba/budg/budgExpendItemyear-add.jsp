<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>系统项目维护</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <link href="plug-in/ace/css/common.css" rel="stylesheet" >
  <style type="text/css">
 	.icon-searchN{
  		background:url('plug-in/easyui/themes/icons/search.png') no-repeat right 10px center;
  		*background:url('plug-in/easyui/themes/icons/search.png') no-repeat 2px right;
  	}
 </style>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="budgExpendItemYearController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${budgExpendItemPage.id }"/>
					<!-- 项目档案id -->
					<input id="baItemId" name="baItemId" type="hidden" >
					<!-- 上级项目 -->
					<!-- <input id="parentCode" name="parentCode" type="hidden" > -->
					<!--英文公式  -->
					<input id="calFormulaEng" name="calFormulaEng" type="hidden" >
					<!-- 年度 -->
					<input id="budgYear" name="budgYear" type="hidden" value="9999">
		<table style="width: 650px;margin: 0 auto;" cellpadding="0" cellspacing="1" class="formtable" >
				<tr>
					<td align="right">
						<label class="Validform_label">
							<i class="interval">*</i>支出项目编码:
						</label>
					</td>
					<td class="value">
					     	 <input id="itemCode" name="itemCode" type="text" style="width: 150px" class="inputxt" datatype="*" onkeyup="value=value.replace(/[\W]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">支出项目编码</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							<i class="interval">*</i>支出项目名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="itemName" name="itemName" type="text" style="width: 150px" class="inputxt" datatype="*" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">支出项目名称</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							上级项目编码:
						</label>
					</td>
					<td class="value">
							<t:comboList name="parentCode" url="dictListController.do?baExpendItemList" id="parentCode"  idField="itemCode" textField="itemCode"  field="itemCode,itemName,id,"  panelWidth="400" title="项目编码:80,项目名称:120" ></t:comboList>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">上级项目编码</label>
					</td>
				</tr>
				<tr>	
					<td align="right">
						<label class="Validform_label">
							<i class="interval">*</i>是否末级:
						</label>
					</td>
					<td class="value">
							<t:dictSelect field="isLast" type="select"  datatype="*" defaultVal="Y" typeGroupCode="sf_yn"  hasLabel="false"  title="是否末级"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否末级</label>
					</td>
				</tr>
				<tr>	
					<td align="right">
						<label class="Validform_label">
							<i class="interval">*</i>是否停用:
						</label>
					</td>
					<td class="value">
							<t:dictSelect field="extend3" type="select" defaultVal="N"  datatype="*" typeGroupCode="sf_yn"  hasLabel="false"  title="是否停用"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否停用</label>
					</td>
				</tr>
				<tr>	
					<td align="right">
						<label class="Validform_label">
							<i class="interval">*</i>所属类型:
						</label>
					</td>
					<td class="value">
							<t:dictSelect field="extend1" type="select"  datatype="*"  hasShowCheck="false" defaultVal="01" typeGroupCode="itemType"  hasLabel="false"  title="所属类型"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所属类型</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							项目说明:
						</label>
					</td>
					<td class="value">
							<input id="extend2" name="extend2" type="text" style="width: 150px" class="inputxt"  ignore="ignore">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">项目说明</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value" >
					     	 <input id="remark" name="remark" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script type="text/javascript">
	
  </script>
</html>