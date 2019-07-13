<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>经济分类对照关系</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="economicsCompareController.do?doUpdate">
					<input id="id" name="id" type="hidden" value="${economicsComparePage.id }"/>
		<table style="width: 800px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>费用项目编码:
							</label>
						</td>
						<td class="value">
						    <%-- <input id="vcode" name="vcode" type="text" validType="economics_compare,v_code,id" style="width: 150px" class="inputxt"  datatype="*"  value='${economicsComparePage.vcode}'/> --%>
							<t:comboList id="vcode" name="vcode" onSelect="itemChange" url="dictListController.do?baExpendItemList&isLast=Y" value='${economicsComparePage.vcode}'  idField="itemCode" textField="itemCode"  datatype="*" field="itemCode,itemName,id,"  panelWidth="400" title="项目编码:80,项目名称:120" ></t:comboList>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">费用项目编码</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>费用项目名称:
							</label>
						</td>
						<td class="value">
						    <input id="vname" name="vname" type="text" readonly="readonly" style="width: 150px" class="inputxt"  datatype="*"  value='${economicsComparePage.vname}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">费用项目名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>会计科目:
							</label>
						</td>
						<td class="value">
							<t:comboList name="acctSubj" url="dictListController.do?acctSubjList&isLast=Y" id="acctSubj"  value="${economicsComparePage.acctSubj}"
							 idField="subCode" textField="subName"  datatype="*" field="subCode,subName,id,"  panelWidth="400" title="科目编码:80,科目名称:120" ></t:comboList>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">会计科目</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>部门预算经济分类编码:
							</label>
						</td>
						<td class="value">
							<t:comboList id="deptEcCode" name="deptEcCode" onSelect="ecCodeChange" url="dictListController.do?baEconList&isLast=Y" value="${economicsComparePage.deptEcCode}"
							  idField="vcode" textField="vname"  datatype="*" field="vcode,vname,id,govName,govCode,"  panelWidth="400" title="编码:80,名称:120" ></t:comboList>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">部门预算经济分类编码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>部门预算经济分类名称:
							</label>
						</td>
						<td class="value">
						    <input id="deptEcName" name="deptEcName" readonly="readonly" type="text" style="width: 150px" class="inputxt"  datatype="*"  value='${economicsComparePage.deptEcName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">部门预算经济分类名称</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>政府预算经济分类编码:
							</label>
						</td>
						<td class="value">
						    <input id="govCode" name="govCode" type="text" readonly="readonly" style="width: 150px" class="inputxt"  datatype="*"  value='${economicsComparePage.govCode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">政府预算经济分类编码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>政府预算经济分类名称:
							</label>
						</td>
						<td class="value">
						    <input id="govName" name="govName" type="text" readonly="readonly" style="width: 150px" class="inputxt"  datatype="*"  value='${economicsComparePage.govName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">政府预算经济分类名称</label>
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
  <script src = "webpage/cn/com/linkwide/ba/baeconomicscompare/economicsCompare.js"></script>		
<script type="text/javascript">
//费用项目选中事件
function itemChange(rowIndex, rowData){
	$("#vname").val(rowData.itemName);
}
//部门预算经济分类编码选中事件
function ecCodeChange(rowIndex, rowData){
	var deptEcCode = $("#deptEcCode").val();
	$("#deptEcName").val(rowData.vname);
	$("#govCode").val(rowData.govCode);
	$("#govName").val(rowData.govName);
}
</script>