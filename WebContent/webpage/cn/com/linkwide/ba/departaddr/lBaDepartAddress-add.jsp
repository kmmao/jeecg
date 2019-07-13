<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>科室及送货地址对照表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="lBaDepartAddressController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${lBaDepartAddressPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							科室:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="departId" type="list"  datatype="*"  dictTable="t_s_depart" dictField="id" dictText="departname"  defaultVal="${lBaDepartAddressPage.departId}" hasLabel="false"  title="科室" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">科室</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							地址编码:
						</label>
					</td>
					<td class="value">
					     	 <input id="addressCode" name="addressCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">地址编码</label>
						</td>
				</tr>
				
				
				<tr>
					<td align="right">
						<label class="Validform_label">
							详细地址:
						</label>
					</td>
					<td class="value" >
						  	 <textarea style="width:600px;" class="inputxt" rows="6" id="address" name="address"  ignore="ignore" ></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">详细地址</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/departaddr/lBaDepartAddress.js"></script>		
