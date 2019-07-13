<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>供应商</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
   <style>
  .formtable tr td select{
  	width:156px;
  }
  </style>
<script type="text/javascript" src="webpage/cn/com/linkwide/ba/supplier/Mtils.js"></script>
<script type="text/javascript">
//是否为统一社会信用代码
function checkUscc(obj){
	  var result = Mtils.validation.isCreditCode(obj.value);
	  if(!result){
		  //tip("统一社会信用代码无效!") 
		  $.Showmsg("统一社会信用代码无效!");
		  return false;
	  }
	  return true;
}

function check_sub(){
	  var supplierTypeId = $("input[name='supplierTypeId']").val();
	  if(supplierTypeId == ""){
		  $.Showmsg("供货商类型必填");
		  return false;
	  }
	  
	  var result = Mtils.validation.isCreditCode($("input[name='uscc']").val());
	  if(!result){
		  //tip("统一社会信用代码无效!") 
		  $.Showmsg("统一社会信用代码无效!");
		  return false;
	  }
	  return true;
}



function materialNameChnage(){
	var mName=$("input[name='supplierFullName']").val();
	if(mName.length <= 0){
		return;
	}
	
	$.ajax({
		type:'post',
		url:'lBaMaterialController.do?getPinYinHead',
		data:{
			mName:mName
		},
		dataType:'json',
		success:function(data){
			console.log(data);
			if(data.success){
				$("#mnemonicCode").val(data.attributes.zjm);
			}
		}
	});
	
}

$(function(){
//显示树下拉
	$('#affiliatedArea').combotree({
		url : 'baAreaController.do?loadBaArea',
       width: 155,
   });
});
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="lBaSupplierController.do?doUpdate" tiptype="1" beforeSubmit="check_sub()">
					<input id="id" name="id" type="hidden" value="${lBaSupplierPage.id }">
		<table cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
						<font color="red">*</font>
							<label class="Validform_label">
								供应商编码:
							</label>
						</td>
						<td class="value">
						     	 <input id="supplierCode" name="supplierCode" type="text" style="width: 150px" onblur="materialNameChnage()" class="inputxt" datatype="*1-36" value='${lBaSupplierPage.supplierCode}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">供应商编码</label>
							
						</td>
						<td align="right">
						<font color="red">*</font>
							<label class="Validform_label">
								供应商名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="supplierFullName" name="supplierFullName" type="text" style="width: 150px" class="inputxt" onblur="materialNameChnage()" datatype="*1-36" value='${lBaSupplierPage.supplierFullName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">供应商名称</label>
							
						</td>
					</tr>
					<tr>
						<td align="right">
						<font color="red">*</font>
							<label class="Validform_label">
								供应商简称:
							</label>
						</td>
						<td class="value">
						     	 <input id="supplierShortName" name="supplierShortName" type="text" style="width: 150px" class="inputxt" datatype="*1-36" value='${lBaSupplierPage.supplierShortName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">供应商简称</label>
							
						</td>
						<td align="right">
						<font color="red">*</font>
							<label class="Validform_label">
								供应商类别:
							</label>
						</td>
						<td class="value">
						     	 <t:comboTree id="supplierTypeId" name="supplierTypeId" url="lBaSupplierTypeController/getTreeAllDateForstate.do" value="${lBaSupplierPage.supplierTypeId}" width="156" onlyleaf="true"></t:comboTree>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">供应商类别</label>
							
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								联系人:
							</label>
						</td>
						<td class="value">
						     	 <input id="contacts" name="contacts" type="text" style="width: 150px" class="inputxt"  value='${lBaSupplierPage.contacts}' >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系人</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								联系电话:
							</label>
						</td>
						<td class="value">
						     	 <input id="telphone" name="telphone" type="text" style="width: 150px" class="inputxt"  value='${lBaSupplierPage.telphone}' >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系电话</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								地址:
							</label>
						</td>
						<td class="value">
						     	 <input id="address" name="address" type="text" style="width: 150px" class="inputxt"  value='${lBaSupplierPage.address}' >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">地址</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								是否控制:
							</label>
						</td>
						<td class="value">
						<t:dictSelect field="isControl" type="list" hasShowCheck="false" hasLabel="false" typeGroupCode="whether" defaultVal="${lBaSupplierPage.isControl}" title="是否控制"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否控制</label>
						</td>
					</tr>
					<tr>
					<td align="right">
						<label class="Validform_label">
							助记码:
						</label>
					</td>
					<td class="value">
					     	 <input id="mnemonicCode" name="mnemonicCode" type="text" style="width: 150px" class="inputxt" value='${lBaSupplierPage.mnemonicCode}' >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">助记码</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							所属地区:
						</label>
					</td>
					<td class="value">
							<input id="affiliatedArea" name="affiliatedArea" type="text" style="width: 150px" value="${lBaSupplierPage.affiliatedArea}"  class="inputxt"  >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所属地区</label>
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
					     	 <input id="uscc" name="uscc" type="text" style="width: 150px" class="inputxt" value='${lBaSupplierPage.uscc}' datatype="*1-36" onblur="checkUscc(this)">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">统一社会信用代码</label>
							
						</td>
					<td align="right">
						<label class="Validform_label">营业执照发证日期:</label>
					</td>
					<td class="value">
							  <input id="grantDate" name="grantDate" type="text" style="width: 150px" value='${lBaSupplierPage.grantDate}'
									 class="Wdate" onClick="WdatePicker()" >
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">营业执照发证日期</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							法人:
						</label>
					</td>
					<td class="value">
					     	 <input id="legalPerson" name="legalPerson" type="text" style="width: 150px" class="inputxt" value='${lBaSupplierPage.legalPerson}' >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">法人</label>
						</td>
					<td align="right">
						<label class="Validform_label">营业执照有效日期至:</label>
					</td>
					<td class="value">
							  <input id="vld" name="vld" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()" value='${lBaSupplierPage.vld}' >
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">营业执照有效日期至</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							公司地址:
						</label>
					</td>
					<td class="value">
					     	 <input id="corporationAddress" name="corporationAddress" type="text" style="width: 150px" class="inputxt" value='${lBaSupplierPage.corporationAddress}' >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">公司地址</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							公司电话:
						</label>
					</td>
					<td class="value">
					     	 <input id="corporationPhone" name="corporationPhone" type="text" style="width: 150px" class="inputxt" value='${lBaSupplierPage.corporationPhone}' >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">公司电话</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							网址:
						</label>
					</td>
					<td class="value">
					     	 <input id="website" name="website" type="text" style="width: 150px" value='${lBaSupplierPage.website}' class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">网址</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							默认交易币种:
						</label>
					</td>
					<td class="value">
							<t:dictSelect field="defaultCurrency" type="list" hasShowCheck="false" hasLabel="false" defaultVal="${lBaSupplierPage.defaultCurrency}" typeGroupCode="currency"  title="默认交易币种"></t:dictSelect>
					     	 <!-- <input id="defaultCurrency" name="defaultCurrency" type="text" style="width: 150px" class="inputxt"  datatype="l1-255"> -->
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">默认交易币种</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							注册资金:
						</label>
					</td>
					<td class="value">
					     	 <input id="registerCapital" name="registerCapital" type="text" value='${lBaSupplierPage.registerCapital}' style="width: 150px" class="inputxt"  >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">注册资金</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							是否采购:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="isPurchase" type="list" hasShowCheck="false" hasLabel="false" defaultVal="${lBaSupplierPage.isPurchase}" typeGroupCode="whether"  title="是否采购"></t:dictSelect>
					     	 <!-- <input id="isPurchase" name="isPurchase" type="text" style="width: 150px" class="inputxt"  datatype="l1-255"> -->
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否采购</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否为委外:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="isOutsource" type="list" hasShowCheck="false" hasLabel="false" defaultVal="${lBaSupplierPage.isOutsource}" typeGroupCode="whether" title="是否为委外"></t:dictSelect>
					     	 <!-- <input id="isPurchase" name="isPurchase" type="text" style="width: 150px" class="inputxt"  datatype="l1-255"> -->
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否为委外</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							是否招标:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="isInviteBids" type="list" hasShowCheck="false" defaultVal="${lBaSupplierPage.isInviteBids}" hasLabel="false" typeGroupCode="whether"  title="是否招标"></t:dictSelect>
					     	 <!-- <input id="isPurchase" name="isPurchase" type="text" style="width: 150px" class="inputxt"  datatype="l1-255"> -->
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否招标</label>
						</td>
							<td align="right" style="display: none;">
						<label class="Validform_label">
							是否停用:
						</label>
					</td>
					<td class="value" style="display: none;">
						<t:dictSelect field="isCease" type="list" hasShowCheck="false" defaultVal="0" hasLabel="false" typeGroupCode="whether"  title="是否招标"></t:dictSelect>
					     	 <!-- <input id="isPurchase" name="isPurchase" type="text" style="width: 150px" class="inputxt"  datatype="l1-255"> -->
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否停用</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							专管业务员:
						</label>
					</td>
					<td class="value">
						<t:comboList id="superviseSalesman"  name="superviseSalesman"  url="dictListController.do?userList" idField="id" textField="realName" ignore="ignore"
						 field="userName,realName,id,"  value="${lBaSupplierPage.superviseSalesman}" 
						    panelWidth="400" title="编码:80,用户名称:120" ></t:comboList>
					     <span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">专管业务员</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							供应商来源:
						</label>
					</td>
					<td class="value">
							<t:dictSelect field="supplierSource" type="list" defaultVal="02" hasShowCheck="false" readonly = "readonly" hasLabel="false" typeGroupCode="slm_source"  title="供应商来源"></t:dictSelect>
					     	 <!-- <input id="supplierSource" name="supplierSource" type="text" style="width: 150px" class="inputxt"  datatype="l1-255"> -->
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">供应商来源</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否允许登录供应商平台:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="isLogin" type="list" defaultVal="${lBaSupplierPage.isLogin}" hasShowCheck="false" hasLabel="false" typeGroupCode="whether"  title="是否允许登录供应商平台"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否允许登录供应商平台</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							所属银行:
						</label>
					</td>
					<td class="value">
							<t:dictSelect field="affiliatedBank" type="list"  defaultVal="${lBaSupplierPage.affiliatedBank}" hasShowCheck="true" hasLabel="false" typeGroupCode="bank"  title="所属银行"></t:dictSelect>
					     	 <!-- <input id="affiliatedBank" name="affiliatedBank" type="text" style="width: 150px" class="inputxt"  datatype="l1-255"> -->
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所属银行</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							开户银行:
						</label>
					</td>
					<td class="value">
							<%-- <t:dictSelect field="oaaBank" type="list"  defaultVal="${lBaSupplierPage.oaaBank}"  hasShowCheck="false" hasLabel="false" typeGroupCode="bank"  title="开户银行"></t:dictSelect> --%>
					     	 <input id="oaaBank" name="oaaBank" type="text" value='${lBaSupplierPage.oaaBank}' style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">开户银行</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							银行账号:
						</label>
					</td>
					<td class="value">
					     	 <input id="bankCode" name="bankCode" type="text" value='${lBaSupplierPage.bankCode}' style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">银行账号</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否合格供应商:
						</label>
					</td>
					<td class="value">
							<t:dictSelect field="isQualified" type="list"  defaultVal="${lBaSupplierPage.isQualified}" hasShowCheck="false" hasLabel="false" typeGroupCode="whether"  title="是否合格供应商"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否合格供应商</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/supplier/lBaSupplier.js"></script>	
 