<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>凭证类别</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="voucherCategoryController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${voucherCategoryPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								类别字:
							</label>
						</td>
						<td class="value">
						    <input id="cateWord" name="cateWord" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${voucherCategoryPage.cateWord}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">类别字</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								类别名称:
							</label>
						</td>
						<td class="value">
						    <input id="cateName" name="cateName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${voucherCategoryPage.cateName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">类别名称</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/vouchercategory/voucherCategory.js"></script>		
