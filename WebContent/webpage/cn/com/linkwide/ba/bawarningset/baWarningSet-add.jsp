<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>预警设置</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="baWarningSetController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${baWarningSetPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							预警源:
						</label>
					</td>
					<td class="value">
					     	 <input id="warningSource" name="warningSource" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
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
					     	 <input id="warningInfo" name="warningInfo" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
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
					     	 <input id="warningBasis" name="warningBasis" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
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
					     	 <input id="rateNum" name="rateNum" type="text" style="width: 150px" class="inputxt" 		datatype="n" ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">执行频率数</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							执行频率单位:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="rateUnit" type="list"  typeGroupCode=""  defaultVal="${baWarningSetPage.rateUnit}" hasLabel="false"  title="执行频率单位" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">执行频率单位</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							预警参数:
						</label>
					</td>
					<td class="value">
					     	 <input id="warningParameter" name="warningParameter" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">预警参数</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							预警提醒天数:
						</label>
					</td>
					<td class="value">
					     	 <input id="warningDays" name="warningDays" type="text" style="width: 150px" class="inputxt" 		datatype="n" ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">预警提醒天数</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							提前预警天数:
						</label>
					</td>
					<td class="value">
					     	 <input id="advanceDay" name="advanceDay" type="text" style="width: 150px" class="inputxt" 		datatype="n" ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">提前预警天数</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							延后预警天数:
						</label>
					</td>
					<td class="value">
					     	 <input id="postponeDay" name="postponeDay" type="text" style="width: 150px" class="inputxt" 		datatype="n" ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">延后预警天数</label>
						</td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td align="right"> -->
<!-- 						<label class="Validform_label"> -->
<!-- 							预警执行状态: -->
<!-- 						</label> -->
<!-- 					</td> -->
<!-- 					<td class="value"> -->
<!-- 					     	 <input id="warningState" name="warningState" type="text" style="width: 150px" class="inputxt"  ignore="ignore" /> -->
<!-- 							<span class="Validform_checktip"></span> -->
<!-- 							<label class="Validform_label" style="display: none;">预警执行状态</label> -->
<!-- 						</td> -->
<!-- 				</tr> -->
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/bawarningset/baWarningSet.js"></script>		
