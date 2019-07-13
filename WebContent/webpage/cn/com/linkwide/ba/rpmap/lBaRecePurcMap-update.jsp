<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>业务收发类别</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="lBaRecePurcMapController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${lBaRecePurcMapPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								采购类型:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="purcId" type="list"  dictTable="l_ba_purc_type" dictField="id" dictText="name"   defaultVal="${lBaRecePurcMapPage.purcId}" hasLabel="false"  title="采购类型" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">采购类型</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								收发类型:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="receiveId" type="list"  dictTable="l_ba_receive_type" dictField="id" dictText="name"   defaultVal="${lBaRecePurcMapPage.receiveId}" hasLabel="false"  title="收发类型" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">收发类型</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/rpmap/lBaRecePurcMap.js"></script>		
