<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>会计科目</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <link href="plug-in/ace/css/common.css" rel="stylesheet" >
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" beforeSubmit="validateCode" action="baAcctSubjController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${baAcctSubjPage.id }"/>
		<table style="width: 800px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							<i class="interval">*</i>科目年度:
						</label>
					</td>
					<td class="value">
					     	 <input id="acctYear" name="acctYear" type="text"  style="width: 150px" class="inputxt"  datatype="n"  maxlength="4"  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">科目年度</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							上级科目:
						</label>
					</td>
					<td class="value">
							<t:comboList onSelect ="changeCode" name="parentId" url="dictListController.do?acctSubjList" id="parentId"  idField="subCode" textField="subName"  field="subCode,subName,id,"  panelWidth="400" title="科目编码:80,科目名称:120" ></t:comboList>
					     	 <!-- <input id="parentId" name="parentId" type="text" style="width: 150px" class="inputxt"  ignore="ignore" /> -->
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">上级科目</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							<i class="interval">*</i>科目编码:
						</label>
					</td>
					<td class="value">
					     	 <input id="subCode" name="subCode" type="text" onblur="" style="width: 150px" class="inputxt"  datatype="*"  ignore="checked" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">科目编码</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							<i class="interval">*</i>科目名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="subName" name="subName" type="text" style="width: 150px" class="inputxt"  datatype="*"  ignore="checked" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">科目名称</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否部门辅助核算:
						</label>
					</td>
					<td class="value">
							<t:dictSelect field="isDepartCalculate" type="select"  typeGroupCode="sf_yn" defaultVal="Y" hasLabel="false"  title="是否部门辅助核算"></t:dictSelect>
					     	 <!-- <input id="isDepartCalculate" name="isDepartCalculate" type="text" style="width: 150px" class="inputxt"  ignore="ignore" /> -->
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否部门辅助核算</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							是否个人辅助核算:
						</label>
					</td>
					<td class="value">
							<t:dictSelect field="isPersonalCalculate" type="select"  typeGroupCode="sf_yn" defaultVal="Y" hasLabel="false"  title="是否个人辅助核算"></t:dictSelect>
					     	 <!-- <input id="isPersonalCalculate" name="isPersonalCalculate" type="text" style="width: 150px" class="inputxt"  ignore="ignore" /> -->
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否个人辅助核算</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否供应商辅助核算:
						</label>
					</td>
					<td class="value">
							<t:dictSelect field="isSupplierCalculate" type="select"  typeGroupCode="sf_yn" defaultVal="Y" hasLabel="false"  title="是否供应商辅助核算"></t:dictSelect>
					     	 <!-- <input id="isSupplierCalculate" name="isSupplierCalculate" type="text" style="width: 150px" class="inputxt"  ignore="ignore" /> -->
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否供应商辅助核算</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							是否客户辅助核算:
						</label>
					</td>
					<td class="value">
							<t:dictSelect field="isCustomerCalculate" type="select"  typeGroupCode="sf_yn" defaultVal="Y" hasLabel="false"  title="是否客户辅助核算"></t:dictSelect>
					     	 <!-- <input id="isCustomerCalculate" name="isCustomerCalculate" type="text" style="width: 150px" class="inputxt"  ignore="ignore" /> -->
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否客户辅助核算</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							<i class="interval">*</i>余额方向:
						</label>
					</td>
					<td class="value">
							<t:dictSelect field="balanceDirection" type="select"  datatype="*" typeGroupCode="acct_dire" defaultVal="01" hasLabel="false"  title="余额方向"></t:dictSelect>
					     	 <!-- <input id="balanceDirection" name="balanceDirection" type="text" style="width: 150px" class="inputxt"  ignore="ignore" /> -->
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">余额方向</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							是否项目辅助核算:
						</label>
					</td>
					<td class="value">
							<t:dictSelect field="isProjectCalculate" type="select"  datatype="*" typeGroupCode="sf_yn" defaultVal="Y" hasLabel="false"  title="是否项目辅助核算"></t:dictSelect>
					     	 <!-- <input id="isProjectCalculate" name="isProjectCalculate" type="text" style="width: 150px" class="inputxt"  ignore="ignore" /> -->
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否项目辅助核算</label>
					</td>
					
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							<i class="interval">*</i>是否末级:
						</label>
					</td>
					<td class="value">
							<t:dictSelect field="extend1" type="select"  datatype="*" typeGroupCode="sf_yn" defaultVal="Y" hasLabel="false"  title="是否末级"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否末级</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							<i class="interval">*</i>助记码:
						</label>
					</td>
					<td class="value">
					     	 <input id="spell" name="spell" type="text" style="width: 150px" class="inputxt" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">助记码</label>
					</td>
				</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/baacctsubj/baAcctSubj.js"></script>		
<script type="text/javascript">
$(document).ready(function(){
	$("#acctYear").val(new Date().getFullYear());
});
 var codeLevel; //科目级次
 //根据上级科目编码设定添加的科目的编码	
 function changeCode(){
	 var subCode = $("input[name='parentId']").val();
	 $.ajax({
			url:"baAcctSubjController.do?getSubCode",
			type:"post",
			data:{
				"subCode":subCode
			},
			dataType:"json",
			async:true,
			success:function(data){
				var code = data.msg;
				$("#subCode").val(code);
				//科目级次
				codeLevel = (code.length-4)/2+1;
			}
		});
 }
 //保存前校验科目编码
 function validateCode(){
	 var subCode = $("#subCode").val();
	 if(codeLevel!=(subCode.length-4)/2+1){
		 alertTipTop("科目编码不符合4-2-2-2-2-2-2格式");
		 return false;
	 }else{
		 return true;
	 }
 }
 	
 </script>