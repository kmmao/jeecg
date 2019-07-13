<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>币种档案</title>
    <style>
  .ui-button {
  	  display: inline-block;
	  padding: 2px 2px;
	  margin-bottom: 0;
	  font-size: 8px;
	  font-weight: normal;
	  line-height: 1.42857143;
	  text-align: center;
	  white-space: nowrap;
	  vertical-align: middle;
	  -ms-touch-action: manipulation;
      touch-action: manipulation;
	  cursor: pointer;
	  -webkit-user-select: none;
      -moz-user-select: none;
      -ms-user-select: none;
      user-select: none;
	  background-image: none;
	  border: 1px solid transparent;
	  border-radius: 4px;
  }
  </style>
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
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="tSForeignCurrencyController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${tSForeignCurrencyPage.id }"/>
	<table cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">币种编码:</label>
			</td>
			<td class="value">
		     	 <input id="currencyCode" name="currencyCode" type="text" style="width: 150px" class="inputxt"  datatype="*"  ignore="checked"  value='${tSForeignCurrencyPage.currencyCode}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">币种编码</label>
			</td>
			<td align="right">
				<label class="Validform_label">币种名称:</label>
			</td>
			<td class="value">
		     	 <input id="currencyName" name="currencyName" type="text" style="width: 150px" class="inputxt"  datatype="*"  ignore="checked"  value='${tSForeignCurrencyPage.currencyName}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">币种名称</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">折算方式:</label>
			</td>
			<td class="value">
				<select id="convertType" name="convertType" datatype="*" style="width: 150px" >
				<c:if test="${tSForeignCurrencyPage.convertType!='外币/汇率=本位币'}">
					<option selected='selected' value='外币*汇率=本位币'>外币*汇率=本位币</option>
					<option  value='外币/汇率=本位币'>外币/汇率=本位币</option>
				</c:if>
				<c:if test="${tSForeignCurrencyPage.convertType=='外币/汇率=本位币'}">
					<option  value='外币*汇率=本位币'>外币*汇率=本位币</option>
					<option selected='selected' value='外币/汇率=本位币'>外币/汇率=本位币</option>
				</c:if>
				</select>
		     	 <%-- <input id="convertType" name="convertType" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${tSForeignCurrencyPage.convertType}'/> --%>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">折算方式</label>
			</td>
			<td align="right">
				<label class="Validform_label">小数位数:</label>
			</td>
			<td class="value">
		     	 <input id="decimalNum" name="decimalNum" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore"  value='${tSForeignCurrencyPage.decimalNum}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">小数位数</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">最大误差:</label>
			</td>
			<td class="value">
		     	 <input id="maxError" name="maxError" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore"  value='${tSForeignCurrencyPage.maxError}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">最大误差</label>
			</td>
			<td align="right">
				<label class="Validform_label">是否本位币:</label>
			</td>
			<td class="value">
				<t:dictSelect field="isOtherused" type="select"  typeGroupCode="yes_no_int"  defaultVal="${tSForeignCurrencyPage.isOtherused }" hasLabel="false"  title="是否本位币" ></t:dictSelect>
		     	 <%-- <input id="isOtherused" name="isOtherused" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${tSForeignCurrencyPage.isOtherused}'/> --%>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否本位币</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">汇率类型:</label>
			</td>
			<td class="value">
				<t:dictSelect field="exchType" type="select"  typeGroupCode="exchType"  defaultVal="1" hasLabel="false"  title="汇率类型"  onblur="exchTypeChange()" ></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">汇率类型</label>
			</td>
			<td align="right">
				<label class="Validform_label">会计期间:</label>
			</td>
			<td class="value">
				<input id="period"  field="period" type="text" style="width: 150px" class="Wdate" onClick="selectMonth()"  onchange="exchTypeChange()" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">会计期间</label>
			</td>
		</tr>
			</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:800px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="tSForeignCurrencyController.do?tSCurrencyExchList&id=${tSForeignCurrencyPage.id}" icon="icon-search" title="币种汇率" id="tSCurrencyExch"></t:tab>
				</t:tabs>
			</div>
			</t:formvalid>
			<!-- 添加 附表明细 模版 -->
		<table style="display:none">
		<tbody id="add_tSCurrencyExch_table_template">
			<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left" style="display:none;">
				  	  <input name="tSCurrencyExchList[#index#].period" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" />
					  <label class="Validform_label" style="display: none;">会计期间</label>
				  </td>
				  <td align="left" style="display:none;">
					  		<input name="tSCurrencyExchList[#index#].exchType" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" />
					  <label class="Validform_label" style="display: none;">汇率类型</label>
				  </td>
				  <td align="left">
					  		<input name="tSCurrencyExchList[#index#].exchDate" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" />
					  <label class="Validform_label" style="display: none;">日期</label>
				  </td>
				  <td align="left">
					  		<input name="tSCurrencyExchList[#index#].exchRate" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" />
					  <label class="Validform_label" style="display: none;">汇率</label>
				  </td>
			</tr>
		 </tbody>
		</table>
 </body>
 <script src = "webpage/cn/com/linkwide/ba/tsforeigncurrency/tSForeignCurrency.js"></script>	
<script type="text/javascript">
$(document).ready(function(){
	   
	   
});

</script>