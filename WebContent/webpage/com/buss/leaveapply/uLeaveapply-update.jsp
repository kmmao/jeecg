<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>员工请假单</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="uLeaveapplyController.do?doUpdate" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${uLeaveapplyPage.id }">
					<input id="createName" name="createName" type="hidden" value="${uLeaveapplyPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${uLeaveapplyPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${uLeaveapplyPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${uLeaveapplyPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${uLeaveapplyPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${uLeaveapplyPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${uLeaveapplyPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${uLeaveapplyPage.sysCompanyCode }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${uLeaveapplyPage.bpmStatus }">
					<input id="busitype" name="busitype" type="hidden" value="${uLeaveapplyPage.busitype }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								请假人员:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="userid" type="list"
										dictTable="t_s_base_user" dictField="id" dictText="realname" defaultVal="${uLeaveapplyPage.userid}" hasLabel="false"  title="请假人员"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">请假人员</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								请假类型:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="leavetype" type="list"
										typeGroupCode="leaveType" defaultVal="${uLeaveapplyPage.leavetype}" hasLabel="false"  title="请假类型"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">请假类型</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								请假原因描述:
							</label>
						</td>
						<td class="value">
						     	 <input id="reason" name="reason" type="text" style="width: 150px" class="inputxt"  value='${uLeaveapplyPage.reason}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">请假原因描述</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								开始时间:
							</label>
						</td>
						<td class="value">
									  <input id="startdate" name="startdate" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value='<fmt:formatDate value='${uLeaveapplyPage.startdate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">开始时间</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								结束时间:
							</label>
						</td>
						<td class="value">
									  <input id="enddate" name="enddate" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value='<fmt:formatDate value='${uLeaveapplyPage.enddate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">结束时间</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								请假天数:
							</label>
						</td>
						<td class="value">
						     	 <input id="days" name="days" type="text" style="width: 150px" class="inputxt"  value='${uLeaveapplyPage.days}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">请假天数</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/leaveapply/uLeaveapply.js"></script>		
