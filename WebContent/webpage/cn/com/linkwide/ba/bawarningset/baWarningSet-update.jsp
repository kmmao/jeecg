<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>预警设置</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  function waringUtil(){
	  if($("#warningParameter").val()!="" && $("#warningParameter").val()!=undefined){
		  $("#tqyjts").hide();		  
		  $("#yhyjts").hide();		  
	  }else{
		  $("#tqyjts").show();
		  $("#yhyjts").show();
	  }
  }
  function waringTDay(){
	  if($("#advanceDay").val()!="" && $("#advanceDay").val()!=undefined){
		  $("#yjcs").hide();		  
	  }else{
		  $("#yjcs").show();
	  }
  }
  function waringYDay(){
	  if($("#postponeDay").val()!="" && $("#postponeDay").val()!=undefined){
		  $("#yjcs").hide();		  
	  }else{
		  $("#yjcs").show();
	  }
  }
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="baWarningSetController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${baWarningSetPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								预警源:
							</label>
						</td>
						<td class="value">
						    <input id="warningSource" name="warningSource" type="text" style="width: 150px" class="inputxt" readonly="readonly" ignore="ignore"  value='${baWarningSetPage.warningSource}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">预警源</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								预警信息:
							</label>
						</td>
						<td class="value">
						    <input id="warningInfo" name="warningInfo" type="text" style="width: 150px" class="inputxt"  ignore="ignore" readonly="readonly" value='${baWarningSetPage.warningInfo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">预警信息</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								预警依据:
							</label>
						</td>
						<td class="value">
						    <input id="warningBasis" name="warningBasis" type="text" style="width: 150px" class="inputxt"  ignore="ignore" readonly="readonly" value='${baWarningSetPage.warningBasis}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">预警依据</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								执行频率数:
							</label>
						</td>
						<td class="value">
							<span>每</span>
						    <input id="rateNum" name="rateNum" type="text" style="width: 30px" class="inputxt" 		datatype="n" ignore="ignore"  value='${baWarningSetPage.rateNum}'/>
							<t:dictSelect field="rateUnit" type="list"  typeGroupCode="date_unit"   defaultVal="${baWarningSetPage.rateUnit}" hasLabel="false"  title="执行频率单位" ></t:dictSelect>     
							<span>执行一次</span>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">执行频率数</label>
						</td>
					</tr>
					<tr id="yjcs">
						<td align="right">
							<label class="Validform_label">
								预警参数:
							</label>
						</td>
						<td class="value">
						    <input id="warningParameter" name="warningParameter" type="text" style="width: 150px" class="inputxt" onblur="waringUtil()" ignore="ignore"  value='${baWarningSetPage.warningParameter}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">预警参数</label>
						</td>
					</tr>
<!-- 					<tr> -->
<!-- 						<td align="right"> -->
<!-- 							<label class="Validform_label"> -->
<!-- 								预警提醒天数: -->
<!-- 							</label> -->
<!-- 						</td> -->
<!-- 						<td class="value"> -->
<%-- 						    <input id="warningDays" name="warningDays" type="text" style="width: 150px" class="inputxt" 		datatype="n" ignore="ignore"  value='${baWarningSetPage.warningDays}'/> --%>
<!-- 							<span class="Validform_checktip"></span> -->
<!-- 							<label class="Validform_label" style="display: none;">预警提醒天数</label> -->
<!-- 						</td> -->
<!-- 					</tr> -->
					<tr id="tqyjts">
						<td align="right">
							<label class="Validform_label">
								提前预警天数:
							</label>
						</td>
						<td class="value">
						    <input id="advanceDay" name="advanceDay" type="text" style="width: 150px" class="inputxt" onblur="waringTDay()" datatype="n" ignore="ignore"  value='${baWarningSetPage.advanceDay}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">提前预警天数</label>
						</td>
					</tr>
					<tr id="yhyjts">
						<td align="right">
							<label class="Validform_label">
								延后预警天数:
							</label>
						</td>
						<td class="value">
						    <input id="postponeDay" name="postponeDay" type="text" style="width: 150px" class="inputxt" onblur="waringYDay()" datatype="n" ignore="ignore"  value='${baWarningSetPage.postponeDay}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">延后预警天数</label>
						</td>
					</tr>
<!-- 					<tr> -->
<!-- 						<td align="right"> -->
<!-- 							<label class="Validform_label"> -->
<!-- 								预警执行状态: -->
<!-- 							</label> -->
<!-- 						</td> -->
<!-- 						<td class="value"> -->
<%-- 						    <input id="warningState" name="warningState" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baWarningSetPage.warningState}'/> --%>
<!-- 							<span class="Validform_checktip"></span> -->
<!-- 							<label class="Validform_label" style="display: none;">预警执行状态</label> -->
<!-- 						</td> -->
<!-- 					</tr> -->
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/bawarningset/baWarningSet.js"></script>		
