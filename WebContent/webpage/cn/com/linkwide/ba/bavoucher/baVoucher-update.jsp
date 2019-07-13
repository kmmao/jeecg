<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>凭证表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="baVoucherController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${baVoucherPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								业务模块:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="busModel" type="list"  typeGroupCode=""   defaultVal="${baVoucherPage.busModel}" hasLabel="false"  title="业务模块" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务模块</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								业务功能:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="busFunction" type="list"  typeGroupCode=""   defaultVal="${baVoucherPage.busFunction}" hasLabel="false"  title="业务功能" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务功能</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								单据编号:
							</label>
						</td>
						<td class="value">
						    <input id="billCode" name="billCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baVoucherPage.billCode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">单据编号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								单据日期:
							</label>
						</td>
						<td class="value">
						    <input id="billDate" name="billDate" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baVoucherPage.billDate}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">单据日期</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								凭证摘要:
							</label>
						</td>
						<td class="value">
						    <input id="voucSummary" name="voucSummary" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baVoucherPage.voucSummary}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">凭证摘要</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								科目:
							</label>
						</td>
						<td class="value">
						    <input id="subject" name="subject" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baVoucherPage.subject}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">科目</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								借贷方向:
							</label>
						</td>
						<td class="value">
						    <input id="balanceDirection" name="balanceDirection" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baVoucherPage.balanceDirection}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">借贷方向</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								金额:
							</label>
						</td>
						<td class="value">
						    <input id="money" name="money" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baVoucherPage.money}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">金额</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								部门:
							</label>
						</td>
						<td class="value">
						    <input id="deptId" name="deptId" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baVoucherPage.deptId}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">部门</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								人员:
							</label>
						</td>
						<td class="value">
						    <input id="empId" name="empId" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baVoucherPage.empId}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">人员</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								供应商:
							</label>
						</td>
						<td class="value">
						    <input id="vendor" name="vendor" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baVoucherPage.vendor}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">供应商</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								客户患者:
							</label>
						</td>
						<td class="value">
						    <input id="customer" name="customer" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baVoucherPage.customer}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">客户患者</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								项目大类:
							</label>
						</td>
						<td class="value">
						    <input id="projClasses" name="projClasses" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baVoucherPage.projClasses}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">项目大类</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								项目分类:
							</label>
						</td>
						<td class="value">
						    <input id="projType" name="projType" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baVoucherPage.projType}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">项目分类</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								项目:
							</label>
						</td>
						<td class="value">
						    <input id="project" name="project" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baVoucherPage.project}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">项目</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								业务号:
							</label>
						</td>
						<td class="value">
						    <input id="busNum" name="busNum" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baVoucherPage.busNum}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								财务分类:
							</label>
						</td>
						<td class="value">
						    <input id="financialType" name="financialType" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baVoucherPage.financialType}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">财务分类</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								资金来源:
							</label>
						</td>
						<td class="value">
						    <input id="fundSource" name="fundSource" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baVoucherPage.fundSource}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">资金来源</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/bavoucher/baVoucher.js"></script>		
