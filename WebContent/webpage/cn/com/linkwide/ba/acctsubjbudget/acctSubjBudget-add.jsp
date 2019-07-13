<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>科目项目对应表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="acctSubjBudgetController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${acctSubjBudgetPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							<span class="interval">*</span>科目编码:
						</label>
					</td>
					<td class="value">
					     	 <input id="subjCode" name="subjCode" type="text" style="width: 150px" class="inputxt" datatype="*" ignore="checked" validType="acct_subj_budget,subj_code,id"/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">科目编码</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							科目名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="subjName" name="subjName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">科目名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							<span class="interval">*</span>项目编码:
						</label>
					</td>
					<td class="value">
					     	 <input id="budgetCode" name="budgetCode" type="text" style="width: 150px" class="inputxt" datatype="*" ignore="checked"  validType="acct_subj_budget,budget_code,id"/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">项目编码</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							项目名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="budgetName" name="budgetName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">项目名称</label>
						</td>
				</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/acctsubjbudget/acctSubjBudget.js"></script>		
