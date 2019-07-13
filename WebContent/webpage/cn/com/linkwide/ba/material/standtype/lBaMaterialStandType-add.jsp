<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<title>国标分类</title>
		<t:base type="jquery,easyui,tools,DatePicker"></t:base>
		<script type="text/javascript">
  //编写自定义JS代码
  //添加或更新后刷新
function noteSubmitCallback(data) {
	if (data.success == true) {
  			  	if(frameElement.api != undefined){
  			  		var win = frameElement.api.opener.parent;
  					win.location.reload();
               	 	frameElement.api.close();
                	window.location.reload();
  				}else{
  					window.parent.location.reload();
  				}

  			}else{
  				if(data.responseText==''||data.responseText==undefined){
  					$.messager.alert('错误', data.msg);
  					$.Hidemsg();
  				}else{
  					try{
  						var emsg = data.responseText.substring(data.responseText.indexOf('错误描述'),data.responseText.indexOf('错误信息')); 
  						$.messager.alert('错误',emsg);
  						$.Hidemsg();
  					}catch(ex){
  						$.messager.alert('错误',data.responseText+"");
  						$.Hidemsg();
  					}
  				} 
  				return false;
  			}

}

  </script>
	</head>
	<body>
	
		<t:formvalid formid="formobj" dialog="true" usePlugin="password"
			layout="table" 
			action="lBaMaterialStandTypeController.do?doAdd" tiptype="1" callback="@Override noteSubmitCallback">
			<input id="id" name="id" type="hidden"
				value="${lBaMaterialStandTypePage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1"
				class="formtable">
				<tr>
					<td align="right">
					<font color="red">*</font>
						<label class="Validform_label">
							分类编码:
						</label>
					</td>
					<td class="value">
						<input id="typeCode" name="typeCode" type="text" value="${pCode }"
							style="width: 150px" class="inputxt" datatype="*1-36">
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">
							分类编码
						</label>
					</td>
				</tr>
				<tr>
					<td align="right">
					<font color="red">*</font>
						<label class="Validform_label">
							分类名称:
						</label>
					</td>
					<td class="value">
						<input id="typeName" name="typeName" type="text"
							style="width: 150px" class="inputxt" datatype="*1-36">
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">
							分类名称
						</label>
					</td>
				</tr>
				<tr>
					<td align="right">
					<font color="red">*</font>
						<label class="Validform_label">
							是否停用
						</label>
					</td>
					<td class="value">
						<input id="parentId" name="parentId" type="hidden"
							style="width: 150px" class="inputxt" 
							value='${lBaMaterialStandTypePage.parentId}'>
						<t:dictSelect field="status" type="list" id="status"
							typeGroupCode="whether" hasLabel="false" title="是否停用" datatype="*" defaultVal="0" hasShowCheck="false"></t:dictSelect>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">
							是否停用
						</label>
					</td>
				</tr>
			</table>
		</t:formvalid>
	</body>
