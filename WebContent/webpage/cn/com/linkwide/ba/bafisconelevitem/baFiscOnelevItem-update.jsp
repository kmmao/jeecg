<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>财政一级项目</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
   <style type="text/css">
  	.combo_self{height: 22px !important;width: 150px !important;}
  	.layout-header .btn {
	    margin:0;
	   float: none !important;
	}
	.btn-default {
	    height: 35px;
	    line-height: 35px;
	    font-size:14px;
	}
  </style>
  <script type="text/javascript">
  //编写自定义JS代码
  
  	$(function(){
		$(".combo").removeClass("combo").addClass("combo combo_self");
		$(".combo").each(function(){
			$(this).parent().css("line-height","0px");
		});
		
		var budgYear = $("#budgYear").val();
		
		//上级项目编码
		$("#parentCode").combotree({
			url: 'baFiscOnelevItemController.do?getOnelevTree&budgYear='+budgYear+'',
			width:'156',
			panelHeight:'220',
			lines:true,
			onBeforeSelect:function(node){
				var nodeChildren = $(this).tree('getChildren',node.target);
		        	if(nodeChildren.length>0){
		        		/* tip('请选择末级!');
		        		$(this).combo('showPanel');
		        		return false; */
		        	}else{
		        		$('#parentCode').val(node.id);
		        		return true;
		        	}
		    },
		    onLoadSuccess:function(){
		    	// 展开之后关闭
		    	$("#parentCode").combotree("tree").tree("expandAll");
		    	$("#parentCode").combotree("tree").tree("collapseAll");
		    }
		});
		
		
	});
  		
  		 /**树形列表数据转换**/
  function convertTreeData(rows, textField) {
      for(var i = 0; i < rows.length; i++) {
          var row = rows[i];
          row.text = row[textField];
          if(row.children) {
          	row.state = "open";
              convertTreeData(row.children, textField);
          }
      }
  }
  /**树形列表加入子元素**/
  function joinTreeChildren(arr1, arr2) {
      for(var i = 0; i < arr1.length; i++) {
          var row1 = arr1[i];
          for(var j = 0; j < arr2.length; j++) {
              if(row1.id == arr2[j].id) {
                  var children = arr2[j].children;
                  if(children) {
                      row1.children = children;
                  }
                  
              }
          }
      }
  }
  
  
  function changbudgMode(value){
		var periodYear = value.periodYear;
		//上级项目编码
		$("#parentCode").combotree({
			url: 'baFiscOnelevItemController.do?getOnelevTree&budgYear='+periodYear+'',
			width:'156',
			panelHeight:'220',
			lines:true,
			onBeforeSelect:function(node){
				var nodeChildren = $(this).tree('getChildren',node.target);
		        	if(nodeChildren.length>0){
		        		tip('请选择末级!');
		        		$(this).combo('showPanel');
		        		return false;
		        	}else{
		        		$('#parentCode').val(node.id);
		        		return true;
		        	}
		    },
		    onLoadSuccess:function(){
		    	// 展开之后关闭
		    	$("#parentCode").combotree("tree").tree("expandAll");
		    	$("#parentCode").combotree("tree").tree("collapseAll");
		    }
		});
		
	}
  
  
  
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="baFiscOnelevItemController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${baFiscOnelevItemPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>项目编码:
							</label>
						</td>
						<td class="value">
						    <input id="vcode" name="vcode" type="text" validType="ba_item_incom,vcode,id" style="width: 150px" class="inputxt"  datatype="*" onkeyup="value=value.replace(/[\W]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"  value='${baFiscOnelevItemPage.vcode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">项目编码</label>
						</td>
						
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>项目名称:
							</label>
						</td>
						<td class="value">
						    <input id="vname" name="vname" type="text" style="width: 150px" class="inputxt"  datatype="*"   value='${baFiscOnelevItemPage.vname}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">项目名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>年度:
							</label>
						</td>
						<td class="value">
<%-- 					     	 <input id="extend1" name="extend1" type="text" style="width: 150px" class="inputxt" value="${baFiscOnelevItemPage.extend1}" datatype="*" /> --%>
							<input id="budgYear" name="budgYear" type="text"  class="easyui-combobox"  panelHeight="auto" value="${baFiscOnelevItemPage.budgYear}"
							data-options=" width:150, valueField: 'periodYear', textField: 'periodYear', url: 'budgListController.do?getBudgYear', method:'get',
							onSelect: function(rec){ changbudgMode(rec)}" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">项目名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								上级项目编码:
							</label>
						</td>
						<td class="value">
							<input id="parentCode" name="parentCode" type="text" style="width: 150px" class="inputxt easyui-combotree"  ignore="ignore" />
						    <%-- <input id="parentCode" name="parentCode" type="text" style="width: 150px" class="inputxt easyui-combotree"  ignore="ignore" 
							value='${baFiscOnelevItemPage.parentCode}'
							data-options="
				                    panelHeight:'220',
				                    url: 'baFiscOnelevItemController.do?datagrid&field=id,vcode,vname',  
				                    loadFilter: function(data) {
				                    	var rows = data.rows || data;
				                    	var win = frameElement.api.opener;
				                    	var listRows = win.getDataGrid().treegrid('getData');
				                    	joinTreeChildren(rows, listRows);
				                    	convertTreeData(rows, 'vname');
				                    	return rows; 
				                    },
				                    onSelect:function(node,data){
				                    	$('#parentCode').val(node.vcode);
				                    },
				                    onLoadSuccess: function() {
				                    	var win = frameElement.api.opener;
				                    	var currRow = win.getDataGrid().treegrid('getSelected');
				                    	if(!'${baFiscOnelevItemPage.id}') {
				                    		//增加时，选择当前父菜单
				                    		if(currRow) {
				                    			$('#parentCode').combotree('setValue', currRow.vcode);
				                    		}
				                    	}else {
				                    		//编辑时，选择当前父菜单
				                    		if(currRow) {
				                    			$('#parentCode').combotree('setValue', currRow.parentCode);
				                    		}
				                    	}
				                    }
				            "
							> --%>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">上级项目编码</label>
						</td>
						
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>是否末级:
							</label>
						</td>
						<td class="value">
						    <t:dictSelect field="isLast" type="select"  datatype="*" defaultVal="${baFiscOnelevItemPage.isLast}" typeGroupCode="sf_yn"  hasLabel="false"  title="是否末级"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否末级</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>是否停用:
							</label>
						</td>
						<td class="value">
						    <t:dictSelect field="isStop" type="select"  datatype="*" defaultVal="${baFiscOnelevItemPage.isStop}" typeGroupCode="sf_yn"  hasLabel="false"  title="是否停用"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否停用</label>
						</td>
						
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								项目说明:
							</label>
						</td>
						<td class="value">
						    <input id="itemMemo" name="itemMemo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baFiscOnelevItemPage.itemMemo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">项目说明</label>
						</td>
					</tr>
					
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/bafisconelevitem/baFiscOnelevItem.js"></script>		
