<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>项目分类</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="baProtypeController.do?doUpdate" tiptype="3">
					<input id="id" name="id" type="hidden" value="${baProtypePage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>项目分类编码:
							</label>
						</td>
						<td class="value">
						    <input id="vcode" name="vcode" type="text" validType="ba_protype,vcode,id" style="width: 150px" class="inputxt"  datatype="*"  ignore="checked"  value='${baProtypePage.vcode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">项目分类编码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>项目分类名称:
							</label>
						</td>
						<td class="value">
						    <input id="vname" name="vname" type="text" style="width: 150px" class="inputxt"  datatype="*"  ignore="checked"  value='${baProtypePage.vname}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">项目分类名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								外部系统项目分类编码:
							</label>
						</td>
						<td class="value">
						    <input id="voutcode" name="voutcode" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baProtypePage.voutcode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">外部系统项目分类编码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								外部系统项目分类名称:
							</label>
						</td>
						<td class="value">
						    <input id="voutname" name="voutname" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baProtypePage.voutname}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">外部系统项目分类名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								上级分类:
							</label>
						</td>
						<td class="value">
							<t:comboList name="pkparent" url="dictListController.do?itemTypeList&isLast=N" value="${baProtypePage.pkparent}" onSelect="afterSelectItem" id="pkparent"  idField="vcode" textField="vcode"  field="vcode,vname,pkbaitemtype,id,"  panelWidth="400" title="分类编码:80,分类名称:120" ></t:comboList>
						    <%-- <input id="pkparent" name="pkparent" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baProtypePage.pkparent}'/> --%>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">上级分类</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>所属项目大类:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="pkbaitemtype" type="list"  dictTable="ba_itemtype" dictField="vitemcode" dictText="vitemname"   defaultVal="${baProtypePage.pkbaitemtype}" hasLabel="false"  title="所属项目大类" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所属项目大类</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>是否末级:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="viflater" type="list"  typeGroupCode="sf_yn"   defaultVal="${baProtypePage.viflater}" hasLabel="false"  title="是否末级" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否末级</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/baprotype/baProtype.js"></script>		
<script type="text/javascript">
//选中上级分类编码
function afterSelectItem(index,rowData){
	//所属项目大类 
	$("select[name='pkbaitemtype']").val(rowData.pkbaitemtype);
	$("select[name='pkbaitemtype']").attr('disabled','disabled');
}
</script>