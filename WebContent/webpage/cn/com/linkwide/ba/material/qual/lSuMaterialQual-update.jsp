<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>物资资质</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  $(document).ready(function(){
	$('#tt').tabs({
	   onSelect:function(title){
	       $('#tt .panel-body').css('width','auto');
		}
	});
	$(".tabs-wrap").css('width','100%');
  });
 </script>
 </head>
 <body style="overflow-x: hidden;">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="lSuMaterialQualController.do?doUpdate" beforeSubmit="subVali()">
					<input id="id" name="id" type="hidden" value="${lSuMaterialQualPage.id }">
	<table cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">单据编码:</label>
			</td>
			<td class="value">
		     	 <input id="billNo" name="billNo" type="text" style="width: 150px" class="inputxt" readonly="readonly" value='${lSuMaterialQualPage.billNo}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">单据编码</label>
			</td>
			<td align="right">
				<label class="Validform_label">单据日期:</label>
			</td>
			<td class="value">
					  <input id="billDate" name="billDate" type="text" style="width: 150px" 
		      						class="Wdate" onClick="WdatePicker()" datatype="*" value='<fmt:formatDate value='${lSuMaterialQualPage.billDate}' type="date" pattern="yyyy-MM-dd"/>'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">单据日期</label>
				<font color="red">*</font>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">物资:</label>
			</td>
			<td class="value" colspan="3">
		     	 <t:materialSelect lblName="物资" selectedIdsInputId="materialId" materialNameInputWidth="150px" selectedNamesInputId="materialn" title="物资"  hasNull="true" value="${lSuMaterialQualPage.materialId}"></t:materialSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">物资</label>
				<font color="red">*</font>
			</td>
			
		</tr>
			</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:800px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="lSuMaterialQualController.do?lSuMaterialQualItemList&id=${lSuMaterialQualPage.id}" icon="icon-search" title="资质详情" id="lSuMaterialQualItem"></t:tab>
				</t:tabs>
			</div>
			</t:formvalid>
			<!-- 添加 附表明细 模版 -->
		<table style="display:none">
		<tbody id="add_lSuMaterialQualItem_table_template">
			<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				   <td align="left">
					  <input name="lSuMaterialQualItemList[#index#].qualTypeIdH" maxlength="36" 
					  		type="text" class="inputxt"  style="width:120px;">
					  		<input name="lSuMaterialQualItemList[#index#].qualTypeId" maxlength="36" 
					  		type="hidden" class="inputxt"  style="width:120px;">
					  <label class="Validform_label" style="display: none;">资质类型</label>
				  </td>
				  <td align="left">
					  	<input name="lSuMaterialQualItemList[#index#].qualCode" maxlength="36" 
					  		type="text" class="inputxt"  style="width:120px;"
					  		datatype="*">
					  <label class="Validform_label" style="display: none;">资质编码</label>
				  </td>
				  <td align="left">
					  	<input name="lSuMaterialQualItemList[#index#].qualFullName" maxlength="36" 
					  		type="text" class="inputxt"  style="width:120px;"
					  		datatype="*">
					  <label class="Validform_label" style="display: none;">资质名称</label>
				  </td>
				  <td align="left">
							<input name="lSuMaterialQualItemList[#index#].effectDate" maxlength="0" 
					  		type="text" class="Wdate" onClick="WdatePicker()"  style="width:120px;;height:16px;"
					  		 datatype="*">
					  <label class="Validform_label" style="display: none;">生效日期</label>
				  </td>
				  <td align="left">
							<input name="lSuMaterialQualItemList[#index#].overDate" maxlength="0" 
					  		type="text" class="Wdate" onClick="WdatePicker()"  style="width:120px;;height:16px;"
					  		 datatype="*">
					  <label class="Validform_label" style="display: none;">到期日期</label>
				  </td>
				  <td align="left">
					  	<!-- <input name="lSuMaterialQualItemList[#index#].isControl" maxlength="2" 
					  		type="text" class="inputxt"  style="width:120px;"
					  		datatype="*"> -->
					  	<select name="lSuMaterialQualItemList[#index#].isControl" datatype="*" style="height:22px;width: 132px;">
					  			<option value='0' selected="selected">否</option>
					  			<option value='1'>是</option>
					  		</select>
					  <label class="Validform_label" style="display: none;">到期控制</label>
				  </td>
			</tr>
		 </tbody>
		</table>
 </body>
 <script src = "webpage/cn/com/linkwide/ba/material/qual/lSuMaterialQual.js"></script>	
