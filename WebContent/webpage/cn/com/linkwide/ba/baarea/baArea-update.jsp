<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>地区</title>
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
	$(function(){
		$(".combo").removeClass("combo").addClass("combo combo_self");
		$(".combo").each(function(){
			$(this).parent().css("line-height","0px");
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
  </script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="3" action="baAreaController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${baAreaPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>地区编码:
							</label>
						</td>
						<td class="value">
						    <input id="areaCode" name="areaCode" validType="ba_area,area_code,id" type="text" style="width: 150px" class="inputxt"  datatype="*"  value='${baAreaPage.areaCode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">地区编码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>地区名称:
							</label>
						</td>
						<td class="value">
						    <input id="areaName" name="areaName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${baAreaPage.areaName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">地区名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								简称:
							</label>
						</td>
						<td class="value">
						     	 <input id="extend1" name="extend1" type="text" style="width: 150px" class="inputxt" value='${baAreaPage.extend1}' datatype="*" />
								<span class="Validform_checktip"></span>
								<label class="Validform_label" style="display: none;">简称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								上级地区:
							</label>
						</td>
						<td class="value">
						<input id="parentCode" name="parentCode" value='${baAreaPage.parentCode}' type="text" style="width: 150px" class="inputxt"  ignore="ignore"/>
							<%-- <input id="parentCode" name="parentCode" type="text" style="width: 150px" class="inputxt easyui-combotree"  ignore="ignore" 
							value='${baAreaPage.parentCode}'
							data-options="
				                    panelHeight:'220',
				                    url: 'baAreaController.do?datagrid&field=id,areaName,areaCode',  
				                    loadFilter: function(data) {
				                    	var rows = data.rows || data;
				                    	var win = frameElement.api.opener;
				                    	var listRows = win.getDataGrid().treegrid('getData');
				                    	joinTreeChildren(rows, listRows);
				                    	convertTreeData(rows, 'areaName');
				                    	return rows; 
				                    },
				                    onSelect:function(node){
				                    	$('#parentCode').val(node.id);
				                    },
				                    onLoadSuccess: function() {
				                    	var win = frameElement.api.opener;
				                    	var currRow = win.getDataGrid().treegrid('getSelected');
				                    	if(!'${baAreaPage.id}') {
				                    		//增加时，选择当前父菜单
				                    		if(currRow) {
				                    			$('#parentCode').combotree('setValue', currRow.id);
				                    		}
				                    	}else {
				                    		//编辑时，选择当前父菜单
				                    		if(currRow) {
				                    			$('#parentCode').combotree('setValue', currRow.parentCode);
				                    		}
				                    	}
				                    }
				            "/> --%>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">上级地区</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>是否末级:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="isLast" type="list"  typeGroupCode="sf_yn" datatype="*"  defaultVal="${baAreaPage.isLast}" hasLabel="false"  title="是否末级" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否末级</label>
						</td>
					</tr>
					<tr>
					<td align="right">
						<label class="Validform_label">
							<i class="interval">*</i>是否直辖市:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="extend2" type="list" datatype="*" typeGroupCode="sf_yn"  defaultVal="${baAreaPage.extend2}" hasLabel="false"  title="是否直辖市" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否直辖市</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							<i class="interval">*</i>是否省级:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="extend3" type="list" datatype="*" typeGroupCode="sf_yn"  defaultVal="${baAreaPage.extend3}" hasLabel="false"  title="是否省级" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否省级</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							<i class="interval">*</i>是否市级:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="extend4" type="list" datatype="*" typeGroupCode="sf_yn"  defaultVal="${baAreaPage.extend4}" hasLabel="false"  title="是否市级" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否市级</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/baarea/baArea.js"></script>		
<script type="text/javascript">
$(function(){
	 //上级地区赋值
	 /* if('${baAreaPage.id}') {
		 $('#parentCode').val('${baAreaPage.areaCode}');
	 } */
	 //显示树下拉
	$('#parentCode').combotree({
		url : 'baAreaController.do?loadBaArea',
        width: 155,
    });
});
</script>
