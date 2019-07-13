<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>act_re_model</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="actReModelController.do?doUpdate" tiptype="1" >
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								id:
							</label>
						</td>
						<td class="value">
						     	 <input id="id" name="id" type="text" style="width: 150px" class="inputxt" datatype="*" value='${actReModelPage.id}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">id</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								rev:
							</label>
						</td>
						<td class="value">
						     	 <input id="rev" name="rev" type="text" style="width: 150px" class="inputxt"  value='${actReModelPage.rev}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">rev</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								name:
							</label>
						</td>
						<td class="value">
						     	 <input id="name" name="name" type="text" style="width: 150px" class="inputxt"  value='${actReModelPage.name}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">name</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								key:
							</label>
						</td>
						<td class="value">
						     	 <input id="key" name="key" type="text" style="width: 150px" class="inputxt"  value='${actReModelPage.key}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">key</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								category:
							</label>
						</td>
						<td class="value">
						     	 <input id="category" name="category" type="text" style="width: 150px" class="inputxt"  value='${actReModelPage.category}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">category</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								createTime:
							</label>
						</td>
						<td class="value">
									  <input id="createTime" name="createTime" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()" value='<fmt:formatDate value='${actReModelPage.createTime}' type="date" pattern="yyyy-MM-dd"/>'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">createTime</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								lastUpdateTime:
							</label>
						</td>
						<td class="value">
									  <input id="lastUpdateTime" name="lastUpdateTime" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()" value='<fmt:formatDate value='${actReModelPage.lastUpdateTime}' type="date" pattern="yyyy-MM-dd"/>'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">lastUpdateTime</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								version:
							</label>
						</td>
						<td class="value">
						     	 <input id="version" name="version" type="text" style="width: 150px" class="inputxt"  value='${actReModelPage.version}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">version</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								metaInfo:
							</label>
						</td>
						<td class="value">
						  	 	<textarea id="metaInfo" style="width:600px;" class="inputxt" rows="6" name="metaInfo">${actReModelPage.metaInfo}</textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">metaInfo</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								deploymentId:
							</label>
						</td>
						<td class="value">
						     	 <input id="deploymentId" name="deploymentId" type="text" style="width: 150px" class="inputxt"  value='${actReModelPage.deploymentId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">deploymentId</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								editorSourceValueId:
							</label>
						</td>
						<td class="value">
						     	 <input id="editorSourceValueId" name="editorSourceValueId" type="text" style="width: 150px" class="inputxt"  value='${actReModelPage.editorSourceValueId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">editorSourceValueId</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								editorSourceExtraValueId:
							</label>
						</td>
						<td class="value">
						     	 <input id="editorSourceExtraValueId" name="editorSourceExtraValueId" type="text" style="width: 150px" class="inputxt"  value='${actReModelPage.editorSourceExtraValueId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">editorSourceExtraValueId</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								tenantId:
							</label>
						</td>
						<td class="value">
						     	 <input id="tenantId" name="tenantId" type="text" style="width: 150px" class="inputxt"  value='${actReModelPage.tenantId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">tenantId</label>
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
  <script src = "webpage/com/buss/activiti/actReModel.js"></script>		
