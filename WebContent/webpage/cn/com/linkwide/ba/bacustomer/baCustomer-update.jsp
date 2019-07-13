<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>客户档案</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
   <link rel="stylesheet" type="text/css" href="plug-in/ace/css/common.css" />
  <style>
  .formtable tr td select{
  	width:156px;
  }
  </style>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="baCustomerController.do?doUpdate" tiptype="3" >
					<input id="id" name="id" type="hidden" value="${baCustomerPage.id }"/>
		<table style="width: 1000px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
						<font color="red">*</font>
							<label class="Validform_label">
								客户编码:
							</label>
						</td>
						<td class="value">
						    <input id="customerCode" name="customerCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baCustomerPage.customerCode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">客户编码</label>
						</td>
						<td align="right">
						<font color="red">*</font>
							<label class="Validform_label">
								客户名称:
							</label>
						</td>
						<td class="value">
						    <input id="customerFullName" name="customerFullName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baCustomerPage.customerFullName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">客户名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
						<font color="red">*</font>
							<label class="Validform_label">
								客户简称:
							</label>
						</td>
						<td class="value">
						    <input id="customerShortName" name="customerShortName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baCustomerPage.customerShortName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">客户简称</label>
						</td>
						<td align="right">
						<font color="red">*</font>
							<label class="Validform_label">
								客户分类:
							</label>
						</td>
						<td class="value">
							<t:comboTree id="customerTypeId" name="customerTypeId" url="baCustomerTypeController/getTreeAllDateForstate.do" width="156" value="${baCustomerPage.customerTypeId}" onlyleaf="false"></t:comboTree>
						    <%-- <input id="customerTypeId" name="customerTypeId" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baCustomerPage.customerTypeId}'/> --%>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">客户分类</label>
						</td>
						
					</tr>
					<tr>
						<td align="right">
						<label class="Validform_label">
							所属类型:
						</label>
						</td>
						<td class="value">
							<t:dictSelect field="extend1" type="checkbox" hasShowCheck="false" hasLabel="false" typeGroupCode="cusType" defaultVal="${baCustomerPage.extend1}" title="所属类型"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所属类型</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								联系人:
							</label>
						</td>
						<td class="value">
						    <input id="contacts" name="contacts" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baCustomerPage.contacts}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系人</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								联系电话:
							</label>
						</td>
						<td class="value">
						    <input id="telphone" name="telphone" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baCustomerPage.telphone}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系电话</label>
						</td>
					
						<td align="right">
							<label class="Validform_label">
								地址:
							</label>
						</td>
						<td class="value">
						    <input id="address" name="address" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baCustomerPage.address}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">地址</label>
						</td>
					</tr>
					<tr>
						<td align="right">
						<font color="red">*</font>
							<label class="Validform_label">
								统一社会信用代码:
							</label>
						</td>
						<td class="value">
						    <input id="uscc" name="uscc" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baCustomerPage.uscc}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">统一社会信用代码</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								所属银行:
							</label>
						</td>
						<td class="value">
						<t:dictSelect field="affiliatedBank" type="list"  hasShowCheck="false" hasLabel="false" typeGroupCode="bank" defaultVal=" ${baCustomerPage.affiliatedBank}" title="所属银行"></t:dictSelect>
						    <%-- <input id="affiliatedBank" name="affiliatedBank" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baCustomerPage.affiliatedBank}'/> --%>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所属银行</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								法人:
							</label>
						</td>
						<td class="value">
						    <input id="legalPerson" name="legalPerson" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baCustomerPage.legalPerson}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">法人</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								助记码:
							</label>
						</td>
						<td class="value">
						    <input id="mnemonicCode" name="mnemonicCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baCustomerPage.mnemonicCode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">助记码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								注册资金:
							</label>
						</td>
						<td class="value">
						    <input id="registerCapital" name="registerCapital" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baCustomerPage.registerCapital}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">注册资金</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								默认交易币种:
							</label>
						</td>
						<td class="value">
							<t:dictSelect field="defaultCurrency" type="list" defaultVal="${baCustomerPage.defaultCurrency}" hasShowCheck="false" hasLabel="false" typeGroupCode="currency"  title="默认交易币种"></t:dictSelect>
						    <%-- <input id="defaultCurrency" name="defaultCurrency" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baCustomerPage.defaultCurrency}'/> --%>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">默认交易币种</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								是否停用:
							</label>
						</td>
						<td class="value">
							<t:dictSelect field="isCease" type="list" hasShowCheck="false" hasLabel="false" typeGroupCode="whether" defaultVal="${baCustomerPage.isCease}" title="是否停用"></t:dictSelect>
						    <%-- <input id="isCease" name="isCease" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baCustomerPage.isCease}'/> --%>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否停用</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								首付款协议:
							</label>
						</td>
						<td class="value">
						    <input id="paymentReceivedProtocol" name="paymentReceivedProtocol" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baCustomerPage.paymentReceivedProtocol}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">首付款协议</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								专管业务员:
							</label>
						</td>
						<td class="value">
						    <input id="superviseSalesman" name="superviseSalesman" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baCustomerPage.superviseSalesman}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">专管业务员</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								公司地址:
							</label>
						</td>
						<td class="value">
						    <input id="corporationAddress" name="corporationAddress" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baCustomerPage.corporationAddress}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">公司地址</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								网址:
							</label>
						</td>
						<td class="value">
						    <input id="website" name="website" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baCustomerPage.website}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">网址</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								营业执照有效日期至:
							</label>
						</td>
						<td class="value">
									  <input id="vld" name="vld" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()"  ignore="ignore" value='<fmt:formatDate value='${baCustomerPage.vld}' type="date" pattern="yyyy-MM-dd"/>'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">营业执照有效日期至</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								银行账号:
							</label>
						</td>
						<td class="value">
						    <input id="bankCode" name="bankCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baCustomerPage.bankCode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">银行账号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								公司电话:
							</label>
						</td>
						<td class="value">
						    <input id="corporationPhone" name="corporationPhone" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baCustomerPage.corporationPhone}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">公司电话</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								营业执照发证日期:
							</label>
						</td>
						<td class="value">
									  <input id="grantDate" name="grantDate" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()"  ignore="ignore" value='<fmt:formatDate value='${baCustomerPage.grantDate}' type="date" pattern="yyyy-MM-dd"/>'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">营业执照发证日期</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								开户银行:
							</label>
						</td>
						<td class="value">
						    <input id="oaaBank" name="oaaBank" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baCustomerPage.oaaBank}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">开户银行</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								所属地区:
							</label>
						</td>
						<td class="value">
						    <input id="affiliatedArea" name="affiliatedArea" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baCustomerPage.affiliatedArea}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所属地区</label>
						</td>
						<td align="right"></td>
						<td class="value"></td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/bacustomer/baCustomer.js"></script>		
