<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>会计期间</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  
  function selectYear(){
	  WdatePicker({dateFmt:'yyyy',isShowToday:false,isShowClear:false});
  }
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="baSetAccountPeriodController.do?doAddForYear" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${baSetAccountPeriodPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							年:
						</label>
					</td>
					<td class="value">
					     	 <input id="periodYear" name="periodYear" type="text" style="width: 150px" class="Wdate" onClick="selectYear()"  readonly="readonly" datatype="*">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">年</label>
							<font color="red">*</font>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/setaccountperiod/baSetAccountPeriod.js"></script>		
