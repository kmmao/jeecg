<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>调度引擎时间配置</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="taskTimeconfigController.do?doUpdate" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${taskTimeconfigPage.id }">
					<input id="createName" name="createName" type="hidden" value="${taskTimeconfigPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${taskTimeconfigPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${taskTimeconfigPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${taskTimeconfigPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${taskTimeconfigPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${taskTimeconfigPage.updateDate }">
					<input id="taskId" name="taskId" type="hidden" value="${taskTimeconfigPage.taskId }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								删除标志:
							</label>
						</td>
						<td class="value">
						     	 <input id="dr" name="dr" type="text" style="width: 150px" class="inputxt"  value='${taskTimeconfigPage.dr}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">删除标志</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								有效期开始日期:
							</label>
						</td>
						<td class="value">
						     	 <input id="effibegindate" name="effibegindate" type="text" style="width: 150px" class="inputxt"  value='${taskTimeconfigPage.effibegindate}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">有效期开始日期</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								有效期开始时间:
							</label>
						</td>
						<td class="value">
						     	 <input id="effibegintime" name="effibegintime" type="text" style="width: 150px" class="inputxt"  value='${taskTimeconfigPage.effibegintime}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">有效期开始时间</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								有效期结束日期:
							</label>
						</td>
						<td class="value">
						     	 <input id="effienddate" name="effienddate" type="text" style="width: 150px" class="inputxt"  value='${taskTimeconfigPage.effienddate}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">有效期结束日期</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								有效期结束时间:
							</label>
						</td>
						<td class="value">
						     	 <input id="effiendtime" name="effiendtime" type="text" style="width: 150px" class="inputxt"  value='${taskTimeconfigPage.effiendtime}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">有效期结束时间</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								每天结束时间:
							</label>
						</td>
						<td class="value">
						     	 <input id="endtime" name="endtime" type="text" style="width: 150px" class="inputxt"  value='${taskTimeconfigPage.endtime}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">每天结束时间</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								执行日:
							</label>
						</td>
						<td class="value">
						     	 <input id="executeday" name="executeday" type="text" style="width: 150px" class="inputxt"  value='${taskTimeconfigPage.executeday}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">执行日</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								每天执行间隔:
							</label>
						</td>
						<td class="value">
						     	 <input id="executeinterval" name="executeinterval" type="text" style="width: 150px" class="inputxt"  value='${taskTimeconfigPage.executeinterval}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">每天执行间隔</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								执行时间:
							</label>
						</td>
						<td class="value">
						     	 <input id="executetime" name="executetime" type="text" style="width: 150px" class="inputxt"  value='${taskTimeconfigPage.executetime}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">执行时间</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								每天执行间隔单位:
							</label>
						</td>
						<td class="value">
						     	 <input id="exeintervalunit" name="exeintervalunit" type="text" style="width: 150px" class="inputxt"  value='${taskTimeconfigPage.exeintervalunit}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">每天执行间隔单位</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								频率类型:
							</label>
						</td>
						<td class="value">
						     	 <input id="frequencytype" name="frequencytype" type="text" style="width: 150px" class="inputxt"  value='${taskTimeconfigPage.frequencytype}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">频率类型</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								间隔:
							</label>
						</td>
						<td class="value">
						     	 <input id="interva" name="interva" type="text" style="width: 150px" class="inputxt"  value='${taskTimeconfigPage.interva}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">间隔</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								每天是否循环执行:
							</label>
						</td>
						<td class="value">
						     	 <input id="isruncycledaily" name="isruncycledaily" type="text" style="width: 150px" class="inputxt"  value='${taskTimeconfigPage.isruncycledaily}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">每天是否循环执行</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								每天的开始时间:
							</label>
						</td>
						<td class="value">
						     	 <input id="starttime" name="starttime" type="text" style="width: 150px" class="inputxt"  value='${taskTimeconfigPage.starttime}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">每天的开始时间</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/cost/custom/taskcenter/taskTimeconfig/taskTimeconfig.js"></script>		
