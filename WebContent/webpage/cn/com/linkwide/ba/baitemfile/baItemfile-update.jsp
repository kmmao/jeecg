<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>项目档案</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="baItemfileController.do?doUpdate" tiptype="3" >
					<input id="id" name="id" type="hidden" value="${baItemfilePage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>项目档案编码:
							</label>
						</td>
						<td class="value">
						    <input id="vcode" name="vcode" type="text" validType="ba_itemfile,vcode,id" style="width: 150px" class="inputxt"  datatype="*" value='${baItemfilePage.vcode}' onkeyup="value=value.replace(/[\W]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">项目档案编码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>项目档案名称:
							</label>
						</td>
						<td class="value">
						    <input id="vname" name="vname" type="text" style="width: 150px" class="inputxt"  datatype="*"  ignore="checked"  value='${baItemfilePage.vname}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">项目档案名称</label>
						</td>
					</tr>
					<%-- <tr>
						<td align="right">
							<label class="Validform_label">
								上级项目编码:
							</label>
						</td>
						<td class="value">
								<t:comboList name="extend5" url="dictListController.do?baItemfile" value="${baItemfilePage.extend5}" onSelect="afterSelectItem"  id="extend5"  idField="vcode" textField="vcode"  field="vcode,vname,pkbaprotype,id,"  panelWidth="400" title="项目编码:80,项目名称:120" ></t:comboList>
								<span class="Validform_checktip"></span>
								<label class="Validform_label" style="display: none;">上级项目编码</label>
						</td>
					</tr> --%>
					<tr>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>所属项目分类:
							</label>
						</td>
						<td class="value">
							<t:dictSelect field="pkbaprotype" type="list"  readonly="readonly" onblur="changeType()" dictTable="ba_protype" dictField="vcode" dictText="vname"   defaultVal="${baItemfilePage.pkbaprotype}" hasLabel="false"  title="所属项目分类" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所属项目分类</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								外部系统项目编码:
							</label>
						</td>
						<td class="value">
						    <input id="voutcode" name="voutcode" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baItemfilePage.voutcode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">外部系统项目编码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								外部系统项目名称:
							</label>
						</td>
						<td class="value">
						    <input id="voutname" name="voutname" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baItemfilePage.voutname}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">外部系统项目名称</label>
						</td>
					</tr>
					<%-- <tr id="sourceTr" style="display:none;">
						<td align="right">
							<label class="Validform_label">
								资金来源:
							</label>
						</td>
						<td class="value">
							  <t:dictSelect field="extend3" type="list"  typeGroupCode="bd_csource"  defaultVal="${baItemfilePage.extend3}" hasLabel="false"  title="资金来源" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">资金来源</label>
						</td>
					</tr>
					<tr>
					<td align="right">
						<label class="Validform_label">
							项目说明:
						</label>
					</td>
					<td class="value">
					     	 <input id="extend4" name="extend4" type="text" style="width: 150px" value='${baItemfilePage.extend4}' class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">项目说明</label>
					</td>
				</tr> --%>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/baitemfile/baItemfile.js"></script>		
<script type="text/javascript">
	$(function(){
		//changeType();
	});
	function changeType(){
		var type =$("select[name='pkbaprotype']").val();
		if(type.indexOf('0101')==0){ 
			$("#sourceTr").css("display","table-row");
		}else{
			//清空科室的值
			$("#extend3").combogrid("clear");
			$("#sourceTr").css("display","none");
		}
	}
//选择上级项目后
function afterSelectItem(index,rowData){
	$("select[name='pkbaprotype']").val(rowData.pkbaprotype);
	$("select[name='pkbaprotype']").attr('disabled','disabled');
	changeType();
}
</script>