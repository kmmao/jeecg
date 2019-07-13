<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>单位</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <!-- 联动 -->
<script src="plug-in/jquery/jquery.regionselect.js" type="text/javascript"></script>
  <!-- 省市区provinces三级联动 -->
<script src="plug-in/provinces/js/city-picker.data.js"></script>
<script src="plug-in/provinces/js/city-picker.js"></script>
<script src="plug-in/provinces/js/main.js"></script>
<link href="plug-in/provinces/css/city-picker.css" rel="stylesheet" type="text/css" />
<style>
 .formtable .inputxt{
width:200px!important;
} 
</style>
  <script type="text/javascript">
  //编写自定义JS代码
 	$(function() {
 		
 		//省市区下拉
 	 	$("#province").regionselect({
 	 			url:'<%=basePath%>/jeecgFormDemoController.do?regionSelect'
 	 	});
 		
 		 
 		var select2Data = new Array();
 		$.ajax({
 			url:'jeecgFormDemoController.do?regionSelect&pid=1',
 			type:'GET',
 			dataType:'JSON',
 			delay: 250,
 			cache: true,
 			success: function(data){
 				for(var i = 0; i < data.length; i++){
 					var select2Obj = {};
 					select2Obj.id = data[i].id;
 					select2Obj.text = data[i].name;
 					select2Data.push(select2Obj);
 				}
 				$("#province-select").select2({
 					data: select2Data,
 					placeholder:'请选择省份',//默认文字提示
 				    language: "zh-CN",//汉化
 				    allowClear: true//允许清空
 				});
 			}
 		});
 	});
  
  function doback(data){
	  if(!data.success){
		  tip(data.msg)
	  }
	  parent.refreshNode();
  }
  function setAdm(){
	  if($("#companyCode").val()){
		  var code = "" + $("#companyCode").val();
		  $("#administrator").val(code.toLocaleLowerCase()+"admin")
	  }
  }
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tSCompanyController.do?doAdd" callback="@Override doback" >
					<input id="id" name="id" type="hidden" value=""/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		<caption>单位新增</caption>
				<tr>
					<td align="right">
						<label class="Validform_label">
							<font color="red">*</font>单位编码:
						</label>
					</td>
					<td class="value">
					     	 <input id="companyCode" name="companyCode" type="text" maxlength="50" style="width: 150px;" class="inputxt"  datatype="*" onchange="setAdm()" ignore="checked" validType="t_s_company,company_code,id"  value="${tSCompanyPage.companyCode }" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">单位编码</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							<font color="red">*</font>管理员:
						</label>
					</td>
					<td class="value">
					     	 <input id="administrator" name="administrator" type="text" maxlength="32" style="width: 150px" class="inputxt"  datatype="*" ignore="checked"  validType="t_s_company,company_name,id"  value="${tSCompanyPage.administrator }" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">管理员</label>
						</td>
					</tr>
					</tr>
				<tr>
				<td align="right">
						<label class="Validform_label">
							<font color="red">*</font>单位名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="companyName" name="companyName" type="text" maxlength="64" style="width: 150px" class="inputxt"  datatype="*"  ignore="checked"  value="${tSCompanyPage.companyName }"  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">单位名称</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							法人:
						</label>
					</td>
					<td class="value">
					     	 <input id="legalPerson" name="legalPerson" type="text" maxlength="32" style="width: 150px" class="inputxt"  ignore="ignore"  value="${tSCompanyPage.legalPerson }" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">法人</label>
						</td>
					</tr>
				<tr>

				<td align="right">
						<label class="Validform_label">
							英文名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="englishName" name="englishName" type="text" maxlength="128" style="width: 150px" class="inputxt"  ignore="ignore"  value="${tSCompanyPage.englishName }" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">英文名称</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							上级单位:
						</label>
					</td>
					<td class="value">
					     	<t:dictSelect field="parentId" type="list"  dictTable="t_s_company" dictField="id"   dictText="company_name"  dictCondition=" where 1 =1 "   
		     	    		defaultVal="${tSCompanyPage.id}" hasLabel="false"  title="业务类型" ></t:dictSelect>  
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">上级主管部门编码</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							<font color="red">*</font>简称:
						</label>
					</td>
					<td class="value">
					     	 <input id="shortName" name="shortName" type="text" maxlength="32" style="width: 150px" class="inputxt"  datatype="*"  ignore="checked"  value="${tSCompanyPage.shortName }" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">简称</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							上级主管部门编码:
						</label>
					</td>
					<td class="value">
				     	 <input id="parentOrgCode" name="parentOrgCode" type="text" maxlength="32" style="width: 150px" class="inputxt"  ignore="ignore"  value="${tSCompanyPage.parentOrgCode }" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">上级主管部门编码</label>
					</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							单位类型:
						</label>
					</td>
					<td class="value">
					     	 <input id="companyType" name="companyType" type="text" maxlength="32" style="width: 150px" class="inputxt"  ignore="ignore"  value="${tSCompanyPage.companyType }" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">单位类型</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							上级主管部门名称:
						</label>
					</td>
					<td class="value">
				     	 <input id="parentOrgName" name="parentOrgName" type="text" maxlength="32" style="width: 150px" class="inputxt"  ignore="ignore"  value="${tSCompanyPage.parentOrgName }" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">上级主管部门名称</label>
					</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							 
						</label>
					</td>
					<td class="value">
					      
						</td>
					<td align="right">
						<label class="Validform_label">
							所在省:
						</label>
					</td>
					<td class="value">
					     	 <input id="province" name="province" type="text" maxlength="32" style="width: 150px" class="inputxt" onclick="init()"  ignore="ignore"  value="${tSCompanyPage.province }" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所在省</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							 
						</label>
					</td>
					<td class="value">
					      
					</td>
					<td align="right">
						<label class="Validform_label">
							所在市:
						</label>
					</td>
					<td class="value">
					     	 <input id="city" name="city" type="text" maxlength="32" style="width: 150px" class="inputxt"  ignore="ignore"  value="${tSCompanyPage.city }"  />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所在市</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
						</label>
					</td>
					<td class="value" >
						</td>
					<td align="right" height="30">
						<label class="Validform_label">
							所在区:
						</label>
					</td>
					<td class="value">
				     	 <input id="area" name="area" type="text" maxlength="32" style="width: 150px" class="inputxt"  ignore="ignore"  value="${tSCompanyPage.area }"  />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">所在区</label>
					</td>
				</tr>
				<tr>
					<td align="right" colspan="1">
						<label class="Validform_label">
							单位描述:
						</label>
					</td>
					<td class="value" colspan="3">
							<textarea id="companyDescribe" name="companyDescribe"  maxlength="512" rows="6"  cols="120">${tSCompanyPage.companyDescribe }</textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">单位描述</label>
						</td>
					<td ></td><td ></td>
				</tr>
				<tr>
					<td align="center" colspan="4">
						<button> 提交</button>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
