<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>扣减预算操作记录插入规则</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="baFaRecordController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${baFaRecordPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							模块名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="modular" name="modular" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">模块名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							支出类型:
						</label>
					</td>
					<td class="value">
					     	 <input id="vitemtype" name="vitemtype" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">支出类型</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							项目编码:
						</label>
					</td>
					<td class="value">
					     	 <input id="vitemcode" name="vitemcode" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
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
					     	 <input id="vitemname" name="vitemname" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">项目名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							明细编码:
						</label>
					</td>
					<td class="value">
					     	 <input id="detailcode" name="detailcode" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">明细编码</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							明细名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="detailname" name="detailname" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">明细名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							扣减金额:
						</label>
					</td>
					<td class="value">
					     	 <input id="money" name="money" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">扣减金额</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							操作员:
						</label>
					</td>
					<td class="value">
					     	 <input id="creatname" name="creatname" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">操作员</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							日期:
						</label>
					</td>
					<td class="value">
							   <input id="creatdate" name="creatdate" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker()"  ignore="ignore" />    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">日期</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							业务方式:
						</label>
					</td>
					<td class="value">
					     	 <input id="structures" name="structures" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务方式</label>
						</td>
				</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/farecord/baFaRecord.js"></script>		
