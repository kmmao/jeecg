<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<title>act_re_model</title>
		<t:base type="jquery,easyui,tools,DatePicker"></t:base>
		
		<script src="plug-in/jquery-plugs/fileupload/js/vendor/jquery.ui.widget.js"></script>
		<script src="plug-in/jquery-plugs/fileupload/js/jquery.iframe-transport.js"></script>
		<script src="plug-in/jquery-plugs/fileupload/js/jquery.fileupload.js"></script>
		<script src="plug-in/jquery-plugs/fileupload/js/jquery.fileupload-process.js"></script>
		<script src="plug-in/jquery-plugs/fileupload/js/jquery.fileupload-validate.js"></script>

		<script src="plug-in/jquery-plugs/fileupload/bootstrap/js/bootstrap.min.js"></script>
		
		<link href="plug-in/jquery-plugs/fileupload/css/jquery.fileupload.css" type="text/css" rel="stylesheet" />
		
		<script type="text/javascript">

  		$(function() {
			$('#workFlowCateTree').combotree({
				url : 'workFlowCateController.do?tree',
				panelHeight : 200,
				width : 157,
				onClick : function(node) {
					$("#category").val(node.id);
				}
			});
			
			
		});
  		</script>
  		<style type="text/css">
			.btn {
			  display: inline-block;
			  *display: inline;
			  padding: 5px 12px;
			  margin-bottom: 6px;
			  *margin-left: .3em;
			  font-size: 14px;
			  line-height: 20px;
			  color: #333333;
			  text-align: center;
			  text-shadow: 0 1px 1px rgba(255, 255, 255, 0.75);
			  vertical-align: middle;
			  cursor: pointer;
			  background-color: #f5f5f5;
			  *background-color: #e6e6e6;
			  background-image: -moz-linear-gradient(top, #ffffff, #e6e6e6);
			  background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#ffffff), to(#e6e6e6));
			  background-image: -webkit-linear-gradient(top, #ffffff, #e6e6e6);
			  background-image: -o-linear-gradient(top, #ffffff, #e6e6e6);
			  background-image: linear-gradient(to bottom, #ffffff, #e6e6e6);
			  background-repeat: repeat-x;
			  border: 1px solid #cccccc;
			  *border: 0;
			  border-color: #e6e6e6 #e6e6e6 #bfbfbf;
			  border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
			  border-bottom-color: #b3b3b3;
			  -webkit-border-radius: 4px;
			     -moz-border-radius: 4px;
			          border-radius: 4px;
			  filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffffffff', endColorstr='#ffe6e6e6', GradientType=0);
			  filter: progid:DXImageTransform.Microsoft.gradient(enabled=false);
			  *zoom: 1;
			  -webkit-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
			     -moz-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
			          box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
			}
		</style>
	</head>
	<body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="actController.do?doAdd" tiptype="1" >
			<input id="id" name="id" type="hidden" value="${actReModelPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							模块分类:
						</label>
					</td>
					<td class="value">
					<input id="workFlowCateTree" value="${category}">

					<input id="category" name="category" style="display: none;" value="${category}">
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">
							模块分类
						</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							流程文件:
						</label>
					</td>
					<td class="value">
						<input id="filePath" name="filePath" type="hidden" >
						<input id="fileName" name="fileName" type="text" style="width: 190px;height: 25px;" class="inputxt"  readonly="readonly">
						<span class="btn fileinput-button">
						    <i class="glyphicon glyphicon-plus"></i>
						    <span>浏览</span>
						    <input id="fileupload" type="file" name="files[]" multiple data-url="fileUpAndDownLoadController.do?uploadPara">
							 <input id="uploadPath" type="hidden" name="uploadPath" value="upload/activiti/"> 
						</span>
							
					    <span class="Validform_checktip" style="color: red">*</span>
						<label class="Validform_label" style="display: none;"></label>
					</td>
				</tr>

			</table>
		</t:formvalid>
	</body>
	<script src="webpage/com/buss/activiti/actProcess.js"></script>