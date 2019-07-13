<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>科室物资对照表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="lBaDepartMateController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${lBaDepartMatePage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								科室:
							</label>
						</td>
						<td class="value">
							 <t:comboList id="departId"  name="departId" url="dictListController.do?departList" idField="id" textField="departname" ignore="checked" value='${lBaDepartMatePage.departId}' field="orgCode,departname,description,id,orgType,iflater,mobile,address," 					 panelWidth="400" title="科室编码:80,科室名称:120" ></t:comboList>
 							<%-- <input id="departId" name="departId" type="text" style="width: 150px" class="inputxt"  datatype="*"  ignore="checked"  value='${lBaDepartMatePage.departId}'/>
							 --%><span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">科室</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								物资:
							</label>
						</td>
						<td class="value">
							<t:comboList id="mateId"  name="mateId" url="dictListController.do?mateList" idField="id" textField="materialName" ignore="checked" value='${lBaDepartMatePage.mateId}'	 field="materialCode,materialName,id," 		 panelWidth="400" title="物资编码:80,物资名称:120" ></t:comboList>
						    <%-- <input id="mateId" name="mateId" type="text" style="width: 150px" class="inputxt"  datatype="*"  ignore="checked"  value='${lBaDepartMatePage.mateId}'/> --%>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">物资</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/departmate/lBaDepartMate.js"></script>		
