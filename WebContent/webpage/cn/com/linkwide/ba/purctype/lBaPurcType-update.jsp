<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>采购类型</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
	  function check(obj){
		  var str = obj.value; 
		  if(str.length%2 !=0){
			  alertTipTop("编码不符合规范！")
			  obj.value ="";
		  }
		  if(str.length>6){
			  alertTipTop("编码不符合规范！")
			  obj.value ="";
		  }
	  }
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="lBaPurcTypeController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${lBaPurcTypePage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>采购类型编码:
							</label>
						</td>
						<td class="value">
						    <input id="code" name="code" type="text" style="width: 150px" class="inputxt"   validType="l_ba_purc_type,code,id" datatype="*"  onblur="check(this)" ignore="checked" value='${lBaPurcTypePage.code}'/>
							<span class="Validform_checktip">&nbsp;编码规则:2-2-2</span>
							<label class="Validform_label" style="display: none;">采购类型编码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>采购类型名称:
							</label>
						</td>
						<td class="value">
						    <input id="name" name="name" type="text" style="width: 150px" class="inputxt"   datatype="*"  ignore="checked" value='${lBaPurcTypePage.name}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">采购类型名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								入库类别:
							</label>
						</td>
						<td class="value">
							 <t:dictSelect field="inType" type="list"  dictTable="l_ba_receive_type"  datatype="n"  dictField="id" dictText="name"  defaultVal="${lBaPurcTypePage.inType}" hasLabel="false"  title="收发类型" ></t:dictSelect>     
						    <span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">入库类别</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								是否停用:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="isStop" type="list"  datatype="n"  typeGroupCode="yes_no_int"   defaultVal="${lBaPurcTypePage.isStop}" hasLabel="false"  title="是否停用" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否停用</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/purctype/lBaPurcType.js"></script>		
