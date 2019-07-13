<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>会计期间</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  
  function selectMonth(){
	  WdatePicker({dateFmt:'yyyy-MM',isShowToday:false,isShowClear:false});
  }
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="baSetAccountPeriodController.do?doAdd" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${baSetAccountPeriodPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
						<td align="right">
							<label class="Validform_label">
								月:
							</label>
						</td>
						<td class="value">
						     	 <input id="periodMonth" name="periodMonth" type="text" style="width: 150px" class="Wdate" onClick="selectMonth()"  value='${baSetAccountPeriodPage.periodMonth}' datatype="*" readonly="readonly">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">月</label>
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								月结开始日期:
							</label>
						</td>
						<td class="value">
									  <input id="beginDate" name="beginDate" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()" readonly="readonly" datatype="*" value='<fmt:formatDate value='${baSetAccountPeriodPage.beginDate}' type="date" pattern="yyyy-MM-dd"/>'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">月结开始日期</label>
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								月结结束日期:
							</label>
						</td>
						<td class="value">
									  <input id="endDate" name="endDate" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()" readonly="readonly" datatype="*" value='<fmt:formatDate value='${baSetAccountPeriodPage.endDate}' type="date" pattern="yyyy-MM-dd"/>'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">月结结束日期</label>
							<font color="red">*</font>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/setaccountperiod/baSetAccountPeriod.js"></script>		
