<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>科室物资分类对照</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="lBaDepartMatetypeController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${lBaDepartMatetypePage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							科室:
						</label>
					</td>
					<td class="value">
							<t:comboList name="departId" url="dictListController.do?departList" id="departId" idField="id" textField="departname" ignore="checked" 
								 field="orgCode,departname,description,id,orgType,iflater,mobile,address," value='${lBaDepartMatetypePage.departId}' 
								 panelWidth="400" title="科室编码:80,科室名称:120" ></t:comboList>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">科室</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							物资分类:
						</label>
					</td>
					<td class="value">
							  <t:comboTree id="matetypeId" name="matetypeId" url="lBaMaterialTypeController.do?comboTreeType" value="${lBaDepartMatetypePage.matetypeId}"	width="155" multiple="true" ></t:comboTree>
							 <span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">物资分类</label>
						</td>
				</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/departmatetype/lBaDepartMatetype.js"></script>		
