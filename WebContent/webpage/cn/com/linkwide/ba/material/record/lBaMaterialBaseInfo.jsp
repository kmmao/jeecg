<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div data-options="region:'north',
	collapsed:false,
	split:true,
	border:false,
	"
	style="height: 30px; overflow: hidden;">
	<%-- <input type="text" id="lBaMaterialId" name="lBaMaterialName" value="${lBaMaterialPage.id }"> --%>
		
		<table style="margin-top: 5px">
			<tr>
				<td>物资名称：</td>
				<td>${lBaMaterialPage.materialName }</td>
				<td style="width:50px"></td>
				<td>物资编码:</td>
				<td>${lBaMaterialPage.materialCode }</td>
			</tr>
		</table>
	</div>
	<div region="center" style="padding:0px;border:0px">
		<t:tabs id="tt" iframe="true" tabPosition="top">
			<t:tab href="lBaMaterialImagesController.do?list&lBaMaterialId=${lBaMaterialPage.id }"  title="物资图片管理" id="default"></t:tab>
			<t:tab href="lBaMaterialAttachsController.do?list&lBaMaterialId=${lBaMaterialPage.id }"  title="物资附件管理" id="autocom"></t:tab>
			<%-- <t:tab href="lBaMaterialMeasureUnitController.do?list&lBaMaterialId=${lBaMaterialPage.id }"  title="辅助计量单位管理" id="default"></t:tab> --%>
		</t:tabs>
	</div>
</div>

