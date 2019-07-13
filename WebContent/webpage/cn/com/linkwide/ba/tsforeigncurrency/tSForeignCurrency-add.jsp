<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>币种设置</title>
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
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="tSForeignCurrencyController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${tSForeignCurrencyPage.id }"/>
	<table cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">币种编码:</label>
			</td>
			<td class="value">
		     	 <input id="currencyCode" name="currencyCode" type="text" value="${tSForeignCurrencyPage.currencyCode }" style="width: 150px" class="inputxt"  datatype="*"  ignore="checked" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">币种编码</label>
			</td>
			<td align="right">
				<label class="Validform_label">币种名称:</label>
			</td>
			<td class="value">
		     	 <input id="currencyName" name="currencyName" type="text" value="${tSForeignCurrencyPage.currencyName }" style="width: 150px" class="inputxt"  datatype="*"  ignore="checked" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">币种名称</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">折算方式:</label>
			</td>
			<td class="value">
				<select id="convertType" name="convertType" datatype="*" style="width: 150px">
					<option selected='selected' value=1>外币*汇率=本位币</option>
					<option value=2>外币/汇率=本位币</option>
				</select>
				<%-- <t:dictSelect field="convertType" type="radio"  typeGroupCode="yes_or_int" defaultVal="1" hasLabel="false"  title="折算方式" ></t:dictSelect> --%>     
		     	 <%-- <input id="convertType" name="convertType" type="text" value="${tSForeignCurrencyPage.convertType }" style="width: 150px" class="inputxt"  ignore="ignore" /> --%>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">折算方式</label>
			</td>
			<td align="right">
				<label class="Validform_label">小数位数:</label>
			</td>
			<td class="value">
		     	 <input id="decimalNum" name="decimalNum" type="text" value="${tSForeignCurrencyPage.decimalNum }" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">小数位数</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">最大误差:</label>
			</td>
			<td class="value">
		     	 <input id="maxError" name="maxError" type="text" value="${tSForeignCurrencyPage.maxError }" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">最大误差</label>
			</td>
			<td align="right">
				<label class="Validform_label">是否本位币:</label>
			</td>
			<td class="value">
				<t:dictSelect field="isOtherused" type="select"  typeGroupCode="yes_no_int"  defaultVal="1" hasLabel="false"  title="是否本位币" ></t:dictSelect>     
		     	 <%-- <input id="isOtherused" name="isOtherused" value="${tSForeignCurrencyPage.isOtherused }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" /> --%>
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
				<input id="period"  name="period" type="text" style="width: 150px" class="Wdate" onClick="selectMonth()" onchange="exchTypeChange()" >
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
				 		<!-- <input  name="tSCurrencyExchList[#index#].period" type="text" style="width: 150px" class="Wdate" onClick="selectMonth()"  datatype="*" > -->
					  	<input name="tSCurrencyExchList[#index#].period" maxlength="32" type="text" class="inputxt"  style="width:120px;"  />
					  <label class="Validform_label" style="display: none;">会计期间</label>
				  </td>
				  <td align="left" style="display:none;">
				  		<%-- <t:dictSelect field="exchType" type="radio"  typeGroupCode="exchType" defaultVal="tSCurrencyExchList[#index#].exchType" hasLabel="false"  title="汇率类型" ></t:dictSelect> --%>
					  	<input name="tSCurrencyExchList[#index#].exchType" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" />
					  <label class="Validform_label" style="display: none;">汇率类型</label>
				  </td>
				  <td align="left">
					  	<input name="tSCurrencyExchList[#index#].exchDate" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore" readonly="readonly" />
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
	  exchTypeChange();
  });
  //会计期间和汇率类型onchange事件 
  function exchTypeChange(){
	   // 获取当前年 
	   var currentYear = new Date().getFullYear();
	   //获取当前月 
	   var currentMonth = new Date().getMonth()+1<10?"0"+(new Date().getMonth()+1):new Date().getMonth()+1;
	   //当前年月 
	   var currentYearMonth = currentYear+"-"+currentMonth;
	  //会计期间
	  var period = $("#period").val();
	  //汇率类型  （1.固定汇率；2.浮动汇率）
	  var exchType = $("select[name='exchType']").val();
	  if(exchType=='1'){//固定汇率
		  //禁用会计期间
		   $("#period").val();
		  /*  */
		  //循环当前年的12个月 
	 	  for(var i=1;i<=12;i++){
			  var tr =  $("#add_tSCurrencyExch_table_template tr").clone();
		  	  $("#add_tSCurrencyExch_table").append(tr);
		  	  resetTrNum('add_tSCurrencyExch_table');
		  	  var month=i<10?("0"+i):i;
		  	  // 当前年的 12个月 
		  	  var yearMonth = currentYear+"-"+month;
			   var index = i-1;
			   //汇率类型
			   $("input[name='tSCurrencyExchList["+index+"].exchType']").val(exchType);
			   //会计期间
			   $("input[name='tSCurrencyExchList["+index+"].period']").val(period);
			   //日期
		 	   $("input[name='tSCurrencyExchList["+index+"].cDate']").val(yearMonth);
		  }
	  }else{ //浮动汇率
		 // 默认 会计期间为当前年月
		 $("#period").val(currentYearMonth);
		  
	  }
  }
</script>
	