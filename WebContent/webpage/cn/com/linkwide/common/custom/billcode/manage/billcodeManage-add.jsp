<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>单据号管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="billcodeManageController.do?doAdd" tiptype="1" beforeSubmit="validObj()" >
		<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
				<!-- <tr>
					<td align="right">
						<label class="Validform_label">
							单据号范围:
						</label>
					</td>
					<td class="value" colspan="3">
					     	全局&nbsp;&nbsp; <input id="billScope " name="billScope " type="checkbox" value="all">
							<label class="Validform_label" style="display: none;">单据号范围</label>
					</td>
				</tr>	 -->
				<!-- <tr>		
					<td colspan="4"  class="value">
						<label class="Validform_label">生成单据号时检查唯一性:</label>
						<input type="checkbox" id="isUniq" name="isUniq" value="Y">
						<label class="Validform_label">删除单据时保留占用:</label>
						<input id="isKeep" name="isKeep" type="checkbox" value="Y">
						<label class="Validform_label">自动进行补号:</label>
						<input id="autoComplete" name="autoComplete" type="checkbox" value="Y">
					</td>
				</tr> -->
				<tr>	
					<td align="right">
						<label class="Validform_label">
							单据类型:
						</label>
					</td>
					<td class="value" colspan="3">
					     	 <input id="billType" name="billType" type="text"  value="${billType }" class="inputxt" readonly="readonly">
							<label class="Validform_label" style="display: none;">单据类型</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							对象1:
						</label>
					</td>
					<td class="value">
							<input type="checkbox" id="billObj1Box">&nbsp;&nbsp;&nbsp;
					     	 <input id="billObj1" name="billObj1" type="text" style="width: 70%" class="inputxt" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" readonly="readonly">
							<label class="Validform_label" style="display: none;">对象1</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							对象2:
						</label>
					</td>
					<td class="value">
							<input type="checkbox" id="billObj2Box">&nbsp;&nbsp;&nbsp;
					     	 <input id="billObj2" name="billObj2" type="text" style="width: 70%" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" readonly="readonly" class="inputxt" >
							<label class="Validform_label" style="display: none;">对象2</label>
					</td>
				</tr>
				<tr>	
					<td class="value" colspan="4">
						<label class="Validform_label">年份:</label>
						<input type="checkbox" id="codeYear" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input name="codeYear" type="hidden">
						<label class="Validform_label">月份:</label>
						<input type="checkbox" id="codeMoth" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input name="codeMoth" type="hidden">
						<label class="Validform_label">日期:</label>
						<input type="checkbox" id="codeDay"  >
						<input name="codeDay" type="hidden">
					</td>
				</tr>
				<tr>	
					<td align="right">
						<label class="Validform_label">
							流水号后几位位数:
						</label>
					</td>
					<td class="value">
					     	 <select id="endNum" name="endNum" style="width:70%">
					     	 	<option value="2">2</option>
					     	 	<option value="3">3</option>
					     	 	<option value="4">4</option>
					     	 	<option value="5">5</option>
					     	 	<option value="6">6</option>
					     	 	<option value="7">7</option>
					     	 	<option value="8">8</option>
					     	 	<option value="9">9</option>
					     	 </select>
							<label class="Validform_label" style="display: none;">流水号后几位位数</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							流水号归零标志:
						</label>
					</td>
					<td class="value">
					     	 <select id="zeroMark" name="zeroMark" style="width: 70%">
					     	 	<option value="none">不归零</option>
					     	 	<option value="year">年</option>
					     	 	<option value="month">月</option>
					     	 	<option value="day">日</option>
					     	 </select>
							<label class="Validform_label" style="display: none;">流水号归零标志</label>
					</td>
				</tr>	
				<tr>
					<td align="right">
						<label class="Validform_label">
							应用效果:
						</label>
					</td>
					<td class="value">
					     	 <input id="apliResult" name="apliResult" type="text" readonly="readonly" value="01" style="width: 70%" class="inputxt" >
							<label class="Validform_label" style="display: none;">应用效果</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							表示:
						</label>
					</td>
					<td class="value">
					     	 <input id="billDesc" name="billDesc" type="text" readonly="readonly" value="第1号" style="width: 70%" class="inputxt" >
							<label class="Validform_label" style="display: none;">表示</label>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/common/custom/billcode/manage/billcodeManage.js"></script>		
</html>