<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>供应商证件表</title>
    <style>
  .ui-button {
  	  display: inline-block;
	  padding: 2px 2px;
	  margin-bottom: 0;
	  font-size: 8px;
	  font-weight: normal;
	  line-height: 1.42857143;
	  text-align: center;
	  white-space: nowrap;
	  vertical-align: middle;
	  -ms-touch-action: manipulation;
      touch-action: manipulation;
	  cursor: pointer;
	  -webkit-user-select: none;
      -moz-user-select: none;
      -ms-user-select: none;
      user-select: none;
	  background-image: none;
	  border: 1px solid transparent;
	  border-radius: 4px;
  }
  </style>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  $(document).ready(function(){
	$('#tt').tabs({
	   onSelect:function(title){
	       $('#tt .panel-body').css('width','auto');
		}
	});
	$(".tabs-wrap").css('width','100%');
	
	//证件分类
	$("#certType").combotree({
   		url:'lSuMaterialQualController.do?certTypeTree&parentId=8a80808e633daafc01633daf7bbb000d',
   		width:'156',
   		panelHeight:'220',
   		onBeforeSelect:function(node){
   	        	var nodes = $("#certType").tree("getData",node);
   	        	if(nodes.target.state=="closed"){
   	        		layer.msg('请选择末级!');
   	        		return false;
   	        	}else{
   	        		$("#certType").val(node.id);
   	        		return true;
   	        	}
   	    },
   	    onLoadSuccess:function(){
   	    	// 展开之后关闭
   	    	$("#certType").combotree("tree").tree("expandAll");
   	    	$("#certType").combotree("tree").tree("collapseAll");
   	    }
   	}); 
	 addSelect();
  });
  
  
  function supSelect(rowIndex, rowData){
	  $("input[name='supplierId']").val(rowData.id);
		$('#mateId').combogrid('clear');
		$('#mateId').combogrid('grid').datagrid('options').queryParams.supplierId = rowData.id;
		$('#mateId').combogrid('grid').datagrid('reload');
		
  }
  
  function addSelect(){  
		$("input[name='mateId']").combogrid({
			panelWidth: 250,
			mode: 'remote', 
			idField: 'id',
			textField: 'materialName',
			url: 'lSuMaterialQualController.do?materialDict',
			method: 'get',
			columns: [[
				{field:'materialCode',title:'材料编码',width:100},
				{field:'materialName',title:'材料名称',width:100},
				{field:'specModel',title:'规格型号',width:100}, 
				{field:'materUnitName',title:'计量单位',width:100}
			]],
			onClickRow:function(rowIndex, rowData){
				$("input[name='mateId']").val(rowData.id);  
			},
			//fitColumns: true
		});
		
		$('#mateId').combogrid('grid').datagrid('options').queryParams.supplierId = '${LSuMaterialQualPage.supplierId}';
		$('#mateId').combogrid('grid').datagrid('reload'); 
  }
  
  /**
   *校验日期
   */
   function checkDate(obj){
	   var beginDate = $("#startDate").val();
	   var endDate = $("#endDate").val();
	   if(beginDate != null && beginDate !=undefined && beginDate !=""){
		   if(endDate != null && endDate !=undefined && endDate !=""){
			   beginDate = beginDate.replace("-","")
			   beginDate = beginDate.replace("-","")
			   endDate = endDate.replace("-","")
			   endDate = endDate.replace("-","")
			   if(endDate*1< beginDate*1){
				   alertTipTop("结束日期必须大于开始日期")
				   if(obj != null){
					   obj.value=""; 
				   } 
				   return false;
			   } 
		   }
	   }
	   return true;
   }
  
 </script>
 </head>
 <body style="overflow-x: hidden;">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="2" action="lSuMaterialQualController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${LSuMaterialQualPage.id }"/>
	<table cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">
					<font color="red">*</font>供货商:
				</label>
			</td>
			<td class="value">
				<t:comboList name="supplierId" url="dictListController.do?supplierList" id="supplierId" idField="id" textField="supplierFullName" datatype="*"  ignore="ignore" value="${LSuMaterialQualPage.supplierId}"
	 				field="supplierCode,supplierFullName,id,"  panelWidth="400" title="供应商编码:80,供应商名称:120" onSelect="supSelect" ></t:comboList>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">供货商</label>
			</td>
			<td align="right">
				<label class="Validform_label"><font color="red">*</font>证件类型:</label>
			</td>
			<td class="value">
				 <t:comboTree id="certType" name="certType" url="lBaMaterialQualTypeController.do?comboTreeType"   value="${LSuMaterialQualPage.certType}"	width="155" multiple="false" ></t:comboTree>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">证件类型</label>
			</td>
			
		</tr>
		<tr>	
			<td align="right">
				<label class="Validform_label"><font color="red">*</font>证件编码:</label>
			</td>
			<td class="value">
		     	 <input id="certCode" name="certCode" type="text" style="width: 150px" class="inputxt"   datatype="*" validType="l_su_material_qual,cert_code,id" ignore="checked"  value='${LSuMaterialQualPage.certCode}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">证件编码</label>
			</td>
			<td align="right">
				<label class="Validform_label"><font color="red">*</font>证件名称:</label>
			</td>
			<td class="value">
		     	 <input id="certName" name="certName" type="text" style="width: 150px" class="inputxt"  datatype="*"  ignore="checked"  value='${LSuMaterialQualPage.certName}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">证件名称</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label"><font color="red">*</font>物资:</label>
			</td>
			<td class="value">
		     	 <input id="mateId" name="mateId" type="text" style="width: 155px" class="inputxt"  datatype="*"  ignore="ignore" value="${LSuMaterialQualPage.mateId}" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">物资</label>
			</td>
			<td align="right">
				<label class="Validform_label"><font color="red">*</font>发证日期:</label>
			</td>
			<td class="value">
					  <input id="certDate" name="certDate" type="text" style="width: 150px"   class="Wdate"  autocomplete="off"  onClick="WdatePicker()" datatype="*"  ignore="checked"  value='<fmt:formatDate value='${LSuMaterialQualPage.certDate}' type="date" pattern="yyyy-MM-dd"/>'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">发证日期</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label"><font color="red">*</font>启用日期:</label>
			</td>
			<td class="value">
					  <input id="startDate" name="startDate" type="text" style="width: 150px"  class="Wdate"  onblur="checkDate(this)" autocomplete="off"  onClick="WdatePicker()"  datatype="*"  ignore="checked"  value='<fmt:formatDate value='${LSuMaterialQualPage.startDate}' type="date" pattern="yyyy-MM-dd"/>'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">启用日期</label>
			</td>
			<td align="right">
				<label class="Validform_label"><font color="red">*</font>停用日期:</label>
			</td>
			<td class="value">
					  <input id="endDate" name="endDate" type="text" style="width: 150px"   class="Wdate"  onblur="checkDate(this)" autocomplete="off"  onClick="WdatePicker()" datatype="*"  ignore="checked"  value='<fmt:formatDate value='${LSuMaterialQualPage.endDate}' type="date" pattern="yyyy-MM-dd"/>'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">停用日期</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">证件状态:</label>
			</td>
			<td class="value">
					<t:dictSelect field="certState" type="list"   readonly="readonly" typeGroupCode="certState"  defaultVal="${LSuMaterialQualPage.certState}" hasLabel="false"  title="证件状态"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">证件状态</label>
			</td>
		<td align="right">
				<label class="Validform_label">发证单位:</label>
			</td>
			<td class="value">
		     	 <input id="organ" name="organ" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${LSuMaterialQualPage.organ}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">发证单位</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					是否停用:
				</label>
			</td>
			<td class="value">
					  <t:dictSelect field="isStop" type="radio"  typeGroupCode="yes_no_int"  defaultVal="${LSuMaterialQualPage.isStop}" hasLabel="false"  title="是否停用" ></t:dictSelect>     
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">是否停用</label>
			</td>
			<td align="right">
				<label class="Validform_label">备注:</label>
			</td>
			<td class="value">
		     	 <input id="remark" name="remark" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${LSuMaterialQualPage.remark}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">备注</label>
			</td>  
		</tr>
	
			</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:800px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				<%--  <t:tab href="lSuMaterialQualController.do?lSuMaterialQualInvList&id=${LSuMaterialQualPage.id}" icon="icon-search" title="证件材料对应关系" id="vendorCertInv"></t:tab>
				 --%>
				 <t:tab href="fileDictController.do?fileDictList&tableName=l_su_material_qual&tableId=${LSuMaterialQualPage.id}&isEdit=1" icon="icon-search" title="附件" id="vendorFileDict"></t:tab>
				</t:tabs>
			</div>
			</t:formvalid>
			<!-- 添加 附表明细 模版 -->
		<table style="display:none">
		<tbody id="add_lSuMaterialQualInv_table_template">
			<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left">
					  		<input name="lSuMaterialQualInvList[#index#].invCode" maxlength="32" type="text" class="inputxt"  style="width:120px;"    ignore="checked" />
					  <label class="Validform_label" style="display: none;">材料编码</label>
				  </td>
				   <td align="left">
					  	<input name="lBaMaterialList[#index#].specModel" maxlength="32" type="text" class="inputxt"  style="width:120px;"    ignore="checked" />
					  <label class="Validform_label" style="display: none;">规格型号</label>
				  </td>
				  <td align="left">
					  	<input name="lBaMaterialList[#index#].invUnit" maxlength="32" type="text" class="inputxt"  style="width:120px;"   ignore="checked" />
					  <label class="Validform_label" style="display: none;">计量单位</label>
				  </td>
			</tr>
		 </tbody>
		</table>
 </body>
<script src = "webpage/cn/com/linkwide/ba/material/cert/lSuMaterialQual.js"></script>
