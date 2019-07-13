<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>预算执行情况表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="baBudgetRecordController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${baBudgetRecordPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							模块名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="mkmc" name="mkmc" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">模块名称</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							菜单名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="cdmc" name="cdmc" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">菜单名称</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							支出类型:
						</label>
					</td>
					<td class="value">
					     	 <input id="zclx" name="zclx" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">支出类型</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							执行科室:
						</label>
					</td>
					<td class="value">
					     	 <input id="zxks" name="zxks" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">执行科室</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							预算科室:
						</label>
					</td>
					<td class="value">
					     	 <input id="ysks" name="ysks" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">预算科室</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							预算项目编码:
						</label>
					</td>
					<td class="value">
					     	 <input id="ysxmbm" name="ysxmbm" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">预算项目编码</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							预算项目名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="ysxmmc" name="ysxmmc" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">预算项目名称</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							支出项目编码:
						</label>
					</td>
					<td class="value">
					     	 <input id="zcxmbm" name="zcxmbm" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">支出项目编码</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							支出项目名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="zcxmmc" name="zcxmmc" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">支出项目名称</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							资金来源:
						</label>
					</td>
					<td class="value">
					     	 <input id="zjly" name="zjly" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">资金来源</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							资金性质:
						</label>
					</td>
					<td class="value">
					     	 <input id="zjxz" name="zjxz" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">资金性质</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							支出金额:
						</label>
					</td>
					<td class="value">
					     	 <input id="zcje" name="zcje" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">支出金额</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							调增:
						</label>
					</td>
					<td class="value">
					     	 <input id="tz" name="tz" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">调增</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							调减:
						</label>
					</td>
					<td class="value">
					     	 <input id="tj" name="tj" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">调减</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							凭证号:
						</label>
					</td>
					<td class="value">
					     	 <input id="pzh" name="pzh" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">凭证号</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							操作员:
						</label>
					</td>
					<td class="value">
					     	 <input id="name" name="name" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">操作员</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							制单日期:
						</label>
					</td>
					<td class="value">
							   <input id="billdate" name="billdate" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker()"  ignore="ignore" />    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">制单日期</label>
						</td>
				<td align="right">
					<label class="Validform_label">
					</label>
				</td>
				<td class="value">
				</td>
					</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/babudgetrecord/baBudgetRecord.js"></script>		
