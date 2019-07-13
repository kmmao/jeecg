<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>物资资质</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="lSuMaterialQualItemController.do?doUpdate" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${lSuMaterialQualItemPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								物资认证ID:
							</label>
						</td>
						<td class="value">
						     	 <input id="materialQualId" name="materialQualId" type="text" style="width: 150px" class="inputxt" datatype="*" value='${lSuMaterialQualItemPage.materialQualId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">物资认证ID</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								资质类型ID:
							</label>
						</td>
						<td class="value">
						     	 <input id="qualTypeId" name="qualTypeId" type="text" style="width: 150px" class="inputxt" datatype="*" value='${lSuMaterialQualItemPage.qualTypeId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">资质类型ID</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								资质编码:
							</label>
						</td>
						<td class="value">
						     	 <input id="qualCode" name="qualCode" type="text" style="width: 150px" class="inputxt" datatype="*" value='${lSuMaterialQualItemPage.qualCode}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">资质编码</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								资质名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="qualFullName" name="qualFullName" type="text" style="width: 150px" class="inputxt" datatype="*" value='${lSuMaterialQualItemPage.qualFullName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">资质名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								生效日期:
							</label>
						</td>
						<td class="value">
									  <input id="effectDate" name="effectDate" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()"datatype="*" value='<fmt:formatDate value='${lSuMaterialQualItemPage.effectDate}' type="date" pattern="yyyy-MM-dd"/>'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">生效日期</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								到期日期:
							</label>
						</td>
						<td class="value">
									  <input id="overDate" name="overDate" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()"datatype="*" value='<fmt:formatDate value='${lSuMaterialQualItemPage.overDate}' type="date" pattern="yyyy-MM-dd"/>'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">到期日期</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								是否到期控制 0:否1:是:
							</label>
						</td>
						<td class="value">
						     	 <input id="isControl" name="isControl" type="text" style="width: 150px" class="inputxt" datatype="*" value='${lSuMaterialQualItemPage.isControl}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否到期控制 0:否1:是</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								登陆科室:
							</label>
						</td>
						<td class="value">
						     	 <input id="departId" name="departId" type="text" style="width: 150px" class="inputxt" datatype="*" value='${lSuMaterialQualItemPage.departId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">登陆科室</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								创建人:
							</label>
						</td>
						<td class="value">
						     	 <input id="createBy" name="createBy" type="text" style="width: 150px" class="inputxt" datatype="*" value='${lSuMaterialQualItemPage.createBy}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建人</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								创建时间:
							</label>
						</td>
						<td class="value">
									  <input id="createDate" name="createDate" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()"datatype="*" value='<fmt:formatDate value='${lSuMaterialQualItemPage.createDate}' type="date" pattern="yyyy-MM-dd"/>'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建时间</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								更新人:
							</label>
						</td>
						<td class="value">
						     	 <input id="updateBy" name="updateBy" type="text" style="width: 150px" class="inputxt"  value='${lSuMaterialQualItemPage.updateBy}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">更新人</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								更新时间:
							</label>
						</td>
						<td class="value">
									  <input id="updateDate" name="updateDate" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()" value='<fmt:formatDate value='${lSuMaterialQualItemPage.updateDate}' type="date" pattern="yyyy-MM-dd"/>'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">更新时间</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								扩展字段1:
							</label>
						</td>
						<td class="value">
						     	 <input id="extend1" name="extend1" type="text" style="width: 150px" class="inputxt"  value='${lSuMaterialQualItemPage.extend1}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">扩展字段1</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								扩展字段2:
							</label>
						</td>
						<td class="value">
						     	 <input id="extend2" name="extend2" type="text" style="width: 150px" class="inputxt"  value='${lSuMaterialQualItemPage.extend2}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">扩展字段2</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								扩展字段3:
							</label>
						</td>
						<td class="value">
						     	 <input id="extend3" name="extend3" type="text" style="width: 150px" class="inputxt"  value='${lSuMaterialQualItemPage.extend3}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">扩展字段3</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								扩展字段4:
							</label>
						</td>
						<td class="value">
						     	 <input id="extend4" name="extend4" type="text" style="width: 150px" class="inputxt"  value='${lSuMaterialQualItemPage.extend4}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">扩展字段4</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								扩展字段5:
							</label>
						</td>
						<td class="value">
						     	 <input id="extend5" name="extend5" type="text" style="width: 150px" class="inputxt"  value='${lSuMaterialQualItemPage.extend5}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">扩展字段5</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								扩展字段6:
							</label>
						</td>
						<td class="value">
						     	 <input id="extend6" name="extend6" type="text" style="width: 150px" class="inputxt"  value='${lSuMaterialQualItemPage.extend6}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">扩展字段6</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								扩展字段7:
							</label>
						</td>
						<td class="value">
						     	 <input id="extend7" name="extend7" type="text" style="width: 150px" class="inputxt"  value='${lSuMaterialQualItemPage.extend7}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">扩展字段7</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								扩展字段8:
							</label>
						</td>
						<td class="value">
						     	 <input id="extend8" name="extend8" type="text" style="width: 150px" class="inputxt"  value='${lSuMaterialQualItemPage.extend8}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">扩展字段8</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								扩展字段9:
							</label>
						</td>
						<td class="value">
						     	 <input id="extend9" name="extend9" type="text" style="width: 150px" class="inputxt"  value='${lSuMaterialQualItemPage.extend9}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">扩展字段9</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								扩展字段10:
							</label>
						</td>
						<td class="value">
						     	 <input id="extend10" name="extend10" type="text" style="width: 150px" class="inputxt"  value='${lSuMaterialQualItemPage.extend10}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">扩展字段10</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								扩展字段11:
							</label>
						</td>
						<td class="value">
						     	 <input id="extend11" name="extend11" type="text" style="width: 150px" class="inputxt"  value='${lSuMaterialQualItemPage.extend11}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">扩展字段11</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								扩展字段12:
							</label>
						</td>
						<td class="value">
						     	 <input id="extend12" name="extend12" type="text" style="width: 150px" class="inputxt"  value='${lSuMaterialQualItemPage.extend12}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">扩展字段12</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								扩展字段13:
							</label>
						</td>
						<td class="value">
						     	 <input id="extend13" name="extend13" type="text" style="width: 150px" class="inputxt"  value='${lSuMaterialQualItemPage.extend13}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">扩展字段13</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								扩展字段14:
							</label>
						</td>
						<td class="value">
						     	 <input id="extend14" name="extend14" type="text" style="width: 150px" class="inputxt"  value='${lSuMaterialQualItemPage.extend14}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">扩展字段14</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								扩展字段15:
							</label>
						</td>
						<td class="value">
						     	 <input id="extend15" name="extend15" type="text" style="width: 150px" class="inputxt"  value='${lSuMaterialQualItemPage.extend15}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">扩展字段15</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								扩展字段16:
							</label>
						</td>
						<td class="value">
						     	 <input id="extend16" name="extend16" type="text" style="width: 150px" class="inputxt"  value='${lSuMaterialQualItemPage.extend16}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">扩展字段16</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								扩展字段17:
							</label>
						</td>
						<td class="value">
						     	 <input id="extend17" name="extend17" type="text" style="width: 150px" class="inputxt"  value='${lSuMaterialQualItemPage.extend17}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">扩展字段17</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								扩展字段18:
							</label>
						</td>
						<td class="value">
						     	 <input id="extend18" name="extend18" type="text" style="width: 150px" class="inputxt"  value='${lSuMaterialQualItemPage.extend18}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">扩展字段18</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								扩展字段19:
							</label>
						</td>
						<td class="value">
						     	 <input id="extend19" name="extend19" type="text" style="width: 150px" class="inputxt"  value='${lSuMaterialQualItemPage.extend19}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">扩展字段19</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								扩展字段20:
							</label>
						</td>
						<td class="value">
						     	 <input id="extend20" name="extend20" type="text" style="width: 150px" class="inputxt"  value='${lSuMaterialQualItemPage.extend20}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">扩展字段20</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/mate/base/qualitem/lSuMaterialQualItem.js"></script>		
