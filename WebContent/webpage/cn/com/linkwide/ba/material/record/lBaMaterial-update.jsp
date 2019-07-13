<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>物资基础档案管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <style>
  	.formtable tr td select{
  	width:156px;
  	}
  </style>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="lBaMaterialController.do?doUpdate" tiptype="1"  beforeSubmit="vl()">
					<input id="id" name="id" type="hidden" value="${lBaMaterialPage.id }">
					<input id="materialTypeId" name="materialTypeId" type="hidden" value="${lBaMaterialPage.materialTypeId}">
		<table class="formtable"  cellpadding="0" cellspacing="1" >
				<tr>
					<td align="right">
					<font color="red">*</font>
						<label>
							物资编码:
						</label>
					</td>
					<td class="value">
					     	 <input id="materialCode" name="materialCode" type="text" style="width: 150px" class="inputxt" disabled="disabled"  datatype="*1-36" value="${lBaMaterialPage.materialCode }">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">物资编码</label>
						</td>
					<td align="right">
					<font color="red">*</font>
						<label>
							物资名称:
						</label>
					</td>
					<td class="value">
					     	<input id="materialName" name="materialName" onchange="materialNameChnage()" type="text" style="width: 150px" class="inputxt"  datatype="*1-36" value="${lBaMaterialPage.materialName }">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">物资名称</label>
						</td>
						<td align="right">
						<label>
							预估单价:
						</label>
					</td>
					<td class="value">
					     	<input id="unitPrice" name="unitPrice" type="text" datatype="d2" ignore="ignore"  style="width: 150px" class="inputxt" value="${lBaMaterialPage.unitPrice }">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">预估单价</label>
					</td>
					<%-- <td align="left">
						<label>
							物资分类:
						</label>
					</td>
					<td class="value">
							<t:comboTree id="materialTypeId" name="materialTypeId" url="lBaMaterialTypeController.do?comboTreeForState" width="150" value="${lBaMaterialPage.materialTypeId }"></t:comboTree>
							<span class="Validform_checktip"><font color="red">*</font></span>
							<label class="Validform_label" style="display: none;">物资分类</label>
						</td>
					</tr> --%>
				<tr>
					<td align="right">
						<label>
							规格型号:
						</label>
					</td>
					<td class="value">
				     	 <input id="specModel" name="specModel" type="text" style="width: 150px" class="inputxt" value="${lBaMaterialPage.specModel }">
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">规格型号</label>
					</td>
					<td align="right">
						<font color="red">*</font>
						<label>
							助记码:
						</label>
					</td>
					<td class="value">
				     	 <input id="monmCode" name="monmCode" type="text" style="width: 150px" datatype="*1-36" class="inputxt" value="${lBaMaterialPage.monmCode }">
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">助记码</label>
					</td>
					<td align="right">
						<font color="red">*</font>
						<label>
							计量单位:
						</label>
					</td>
					<td class="value">
						<t:comboList name="materUnitId" url="lBaMaterialMaterUnitController.do?incl" id="materUnitId" idField="id" textField="typeName" ignore="checked" 			
								 field="typeCode,typeName,id,"  panelWidth="400" title="编码:80,名称:120" value="${lBaMaterialPage.materUnitId }"  ></t:comboList> 
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">计量单位</label>
					</td>
					</tr>
					<tr>
					<%-- <td align="right">
						<label>
							计价方式:
						</label>
					</td>
					<td class="value">
							<t:dictSelect field="valueMethod" 
										typeGroupCode="priceMode"  hasLabel="false"  title="计价方式" defaultVal="${lBaMaterialPage.valueMethod }"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">计价方式</label>
						</td> --%>
					<td align="right">
						<label>
							入库超额上限:
						</label>
					</td>
					<td class="value">
					     	 <input id="enterExcessToplimit" name="enterExcessToplimit" type="text" style="width: 150px" class="inputxt" datatype="d2" ignore="ignore"  value="${lBaMaterialPage.enterExcessToplimit }">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">入库超额上限</label>
						</td>
					<td align="right">
						<label>
							主供应商:
						</label>
					</td>
					<td class="value"> 
							<t:comboList name="supplierId" url="dictListController.do?supplierList" id="supplierId" idField="id" textField="supplierFullName" ignore="checked" 
								 field="supplierCode,supplierFullName,id," value='${lBaMaterialPage.supplierId}' 
								 panelWidth="400" title="供应商编码:80,供应商名称:120" ></t:comboList>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">主供应商</label>
						</td>
							<td align="right">
						<label>
							默认仓库:
						</label>
					</td>
					<td class="value">
							<t:comboList name="warehouseId" url="dictListController.do?wareHouseList" id="warehouseId" idField="id" textField="warehouseName" ignore="checked" 			
								 field="warehouseCode,warehouseName,id," value='${lBaMaterialPage.warehouseId }' 			
								 panelWidth="400" title="仓库编码:80,仓库名称:120" ></t:comboList> 
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">默认仓库</label>
						</td>
						
					</tr>	
				
				
					
				<tr>
					<td align="right">
						<label>
							最高库存:
						</label>
					</td>
					<td class="value">
					     	 <input id="maxStock" name="maxStock" type="text" style="width: 150px" class="inputxt" datatype="d2"  ignore="ignore"  value="${lBaMaterialPage.maxStock }">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">最高库存</label>
						</td>	
					<td align="right">
						<label>
							安全库存:
						</label>
					</td>
					<td class="value">
					     	 <input id="safeStock" name="safeStock" type="text" style="width: 150px" class="inputxt" datatype="d2"  ignore="ignore"  value="${lBaMaterialPage.safeStock }">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">安全库存</label>
						</td>
					<td align="right">
						<label>
							最低库存:
						</label>
					</td>
					<td class="value">
					     	 <input id="minStock" name="minStock" type="text" style="width: 150px" class="inputxt" datatype="d2"  ignore="ignore"  value="${lBaMaterialPage.minStock }">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">最低库存</label>
						</td>
					</tr>
					<tr>
					<td align="right">
						<label>
							财务分类:
						</label>
					</td>
					<td class="value">
						 <t:comboTree id="financeTypeId" name="financeTypeId" url="publicReferenceController.do?assetTypeTree" width="156"  value="${lBaMaterialPage.financeTypeId }"></t:comboTree>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">财务分类</label>
					</td>
					<td align="right">
						<label>
							国标分类:
						</label>				
					</td>
					<td class="value">
						<t:comboTree id="standTypeId" name="standTypeId" url="lBaMaterialStandTypeController.do?comboTreeForStatus" width="156" value="${lBaMaterialPage.standTypeId }"></t:comboTree>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">国标分类</label>
					</td>
					<td align="right">
						<label>
							器械分类:
						</label>
					</td>
					<td class="value">
						<t:comboTree id="appaTypeId" name="appaTypeId" url="lBaMaterialAppaTypeController.do?comboTreeForStatus" width="156" value="${lBaMaterialPage.appaTypeId }"></t:comboTree>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">器械分类</label>
					</td>
					
				</tr>
				<tr>
					<td align="right">
						<label>
							生产国别:
						</label>
					</td>
					<td class="value">
							<t:dictSelect field="countryOfOrigin" type="list" typeGroupCode="country"  hasLabel="false"  title="" defaultVal="${lBaMaterialPage.countryOfOrigin }"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">生产国别</label>
					</td>
					<td align="right">
						<label>
							生产企业:
						</label>
					</td>
					<td class="value">
					    <input id="manufacturer" name="manufacturer" type="text" style="width: 150px" class="inputxt" datatype="*" ignore="ignore" value="${lBaMaterialPage.manufacturer }"  >
						<span class="Validform_checktip"></span>
						<label class="Validform_label" class="Validform_label" style="display: none;">生产企业:</label>
					</td>
					<td align="right">
						<label>
							存储方式:
						</label>
					</td>
					<td class="value">
					     	 <t:dictSelect field="materialSaveMode" type="list" id="materialSaveMode"  dictTable="l_ba_material_save_type" dictField="type_code" dictText="type_name"
								defaultVal="${lBaMaterialPage.materialSaveMode}" 
								hasLabel="false"  title="存储方式" ></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">存储方式</label>
					</td>
					</tr>
				<tr>
					 <td align="right">
						<label>
							产地:
						</label>
					</td>
					<td class="value">
					    <input id="producingArea" name="producingArea" type="text" style="width: 150px" class="inputxt" datatype="*" ignore="ignore" value="${lBaMaterialPage.producingArea }"  >
						<span class="Validform_checktip"></span>
						<label class="Validform_label" class="Validform_label" style="display: none;">产地:</label>
					</td>
					 <td align="right">
						<label>
							品牌:
						</label>
					</td>
					<td class="value">
					    <input id="brand" name="brand" type="text" style="width: 150px" class="inputxt" datatype="*" ignore="ignore" value="${lBaMaterialPage.brand }"  >
						<span class="Validform_checktip"></span>
						<label class="Validform_label" class="Validform_label" style="display: none;">品牌:</label>
					</td>
					<td align="right">
						<label>
							保质期:
						</label>
					</td>
					<td class="value">
					     	 <input id="shelfLife" name="shelfLife" type="text" style="width: 150px" class="inputxt" datatype="n" ignore="ignore" value="${lBaMaterialPage.shelfLife }" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">保质期</label>
						</td>
					
					</tr>
					<tr>
					<td align="right">
						<label>
							保质期单位:
						</label>
					</td>
					<td class="value">
					     	<t:dictSelect field="expUnit" id="expUnit" type="list" typeGroupCode="dateType"  hasLabel="false"  title="" defaultVal="${lBaMaterialPage.expUnit }"  ></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">保质期单位</label>
					</td>
					<td align="right">
						<label>
							批准文号:
						</label>
					</td>
					<td class="value">
					    <input id="approvalNumber" name="approvalNumber" type="text" style="width: 150px" class="inputxt" datatype="*" ignore="ignore" value="${lBaMaterialPage.approvalNumber }"  >
						<span class="Validform_checktip"></span>
						<label class="Validform_label" class="Validform_label" style="display: none;">批准文号:</label>
					</td>
					<td align="right">
						<label>
							条码类型:
						</label>
					</td>
					<td class="value">
					     	 <t:dictSelect field="bartype" type="list" id="bartype"
								typeGroupCode="barType" defaultVal="${lBaMaterialPage.bartype}" 
								hasLabel="false"  title="单据类型" ></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">条形码类型</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label>
							对应条形码:
						</label>
					</td>
					<td class="value">
					    <input id="barCode" name="barCode" type="text" style="width: 150px" class="inputxt" datatype="*" ignore="ignore" value="${lBaMaterialPage.barCode }" >
						<span class="Validform_checktip"></span>
						<label class="Validform_label" class="Validform_label" style="display: none;">对应条形码:</label>
					</td>
					<td align="right">
						<label>
							请购超额上限:
						</label>
					</td>
					<td class="value">
					     	 <input id="applyExcessToplimit" name="applyExcessToplimit" type="text" style="width: 150px" class="inputxt" datatype="d2" ignore="ignore"  value="${lBaMaterialPage.applyExcessToplimit }">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">请购超额上限</label>
						</td>
					<td align="right">
						<label>
							订货超额上限:
						</label>
					</td>
					<td class="value">
					     	 <input id="orderExcessToplimit" name="orderExcessToplimit" type="text" style="width: 150px" class="inputxt" datatype="d2" ignore="ignore"  value="${lBaMaterialPage.orderExcessToplimit }">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订货超额上限</label>
						</td>
				</tr>
				<tr>
				<td align="right">
						<label>
							保存温度:
						</label>
					</td>
					<td class="value" colspan="5">
					     	 <input id="saveTemperature" name="saveTemperature" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value="${lBaMaterialPage.saveTemperature }" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">入库超额上限</label>
					</td>
					 
				</tr>
			</table>
			<br>
			<table cellpadding="0" cellspacing="1" class="formtable" >
			<tr>
				<td class="value"><input type="checkbox" id="isPurc" name="isPurc">是否采购</td>
				<td class="value"><input type="checkbox" id="isCons" name="isCons">是否耗材</td>
				<td class="value"><input type="checkbox" id="isAssets" name="isAssets">是否资产</td>
				<td class="value"><input type="checkbox" id="isHighCons" name="isHighCons">是否高值耗材</td>
				<td class="value"><input type="checkbox" id="isDurable" name="isDurable">是否耐用品</td>
			</tr>
			<tr>
				<td class="value"><input type="checkbox" id="isInstead" name="isInstead">是否代管</td>
				<td class="value"><input type="checkbox" id="isBarCode" name="isBarCode">是否条码管理</td>
				<td class="value"><input type="checkbox" id="isBatch" name="isBatch">是否批次管理</td>
				<td class="value"><input type="checkbox" id="isControl" name="isControl">是否校验资质</td>
				<td class="value"><input type="checkbox" id="isShelfLife" name="isShelfLife">是否保质期管理</td>
			</tr>
			<tr>
				<td class="value"><input type="checkbox" id="isInstall" name="isInstall">是否安装</td>
				<td class="value"><input type="checkbox" id="isQuality" name="isQuality">是否质检</td>
				<td class="value"><input type="checkbox" id="isLabourService" name="isLabourService">是否劳务</td>
				<td class="value"><input type="checkbox" id="isOutsourcing" name="isOutsourcing">是否委外</td>
				<td class="value"><input type="checkbox" id="isServiceParts" name="isServiceParts">是否服务配件</td>
				
			</tr>
			<tr>
				<td class="value"><input type="checkbox" id="isMater" name="isMater">是否计量</td>
				<td class="value"><input type="checkbox" id="isInstrur" name="isInstrur">是否器械</td>
				<td class="value"><input type="checkbox" id="isSequenceManage" name="isSequenceManage">是否序列号管理</td>
				<td class="value"><input type="checkbox" id="isSelfrestraint" name="isSelfrestraint">是否自制</td>
				<!-- <td class="value"><input type="checkbox" id="isPmMain" name="isPmMain">是否允许产品结构母件</td>
				<td class="value"><input type="checkbox" id="isPmFrom" name="isPmFrom">是否允许产品结构子件</td> -->
				<td class="value"></td>
				<td class="value"></td>
			</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/material/record/lBaMaterial.js"></script>	
  <style type="text/css">
  	checkbox {
  		padding-left:20px;
  	}
  </style>	
   <script type="text/javascript">
   
   	$(function(){
   		//显示树下拉
   		$('#producingArea').combotree({
   			url : 'baAreaController.do?loadBaArea',
   	        width: 155,
   	    });
   		//如果资产被选则财务，国标，器械分类可编辑
   	  	if('${lBaMaterialPage.isAssets}' == '1'){
   	  		$("#financeTypeId").combotree("enable");
		  	$("#standTypeId").combotree("enable");
		  	$("#appaTypeId").combotree("enable");
   	  	}else{
	   	  	$('#standTypeId').combotree('disable');
	   		$('#financeTypeId').combotree('disable');
	   		$('#appaTypeId').combotree('disable');
	   		$('#isInstall').attr("disabled","disabled");
   	  	}
   	 	var is_BarCode = '${lBaMaterialPage.isBarCode}';
   		if(is_BarCode == 1){
   			$("#bartype").removeAttr("disabled");
	  		$("#bartype").attr("datatype","*");
		}else{
 	  		$("#bartype").attr("disabled","disabled");
 	  		$("#bartype").removeAttr("datatype");
		}
   	  	//选择资产则分类可选
   	  	//选择资产则分类可选
   	  	$("#isAssets").change(function(){
   	  		if($(this).is(':checked')){
   	  			$("#isCons").removeAttr("checked");
   	  			$("#financeTypeId").combotree("enable");
   			  	$("#standTypeId").combotree("enable");
   			  	$("#appaTypeId").combotree("enable");
   				$("#isMater").removeAttr("disabled");
   				$("#isHighCons").removeAttr("checked");
   				$('#isHighCons').attr("disabled","disabled");
   				$("#isQuality").attr("checked","checked");
   				//$('#isQuality').attr("disabled","disabled");
   				$('#isInstall').removeAttr("disabled");
   	  		}else{
   	  			$("#financeTypeId").val("");
   			  	$("#standTypeId").val("");
   			  	$("#appaTypeId").val("");
   	  			$("#financeTypeId").combotree("disable");
   			  	$("#standTypeId").combotree("disable");
   			  	$("#appaTypeId").combotree("disable");
   			 	$("#isMater").removeAttr("checked");
   				$('#isMater').attr("disabled","disabled");
   				$("#isHighCons").removeAttr("disabled");
   				$("#isCons").attr("checked","checked");
   				$("#isQuality").removeAttr("checked");
   				$("#isInstall").removeAttr("checked");
   				$('#isInstall').attr("disabled","disabled");
   				
   				$("#financeTypeId").combotree("setValues","");
   			  	$("#standTypeId").combotree("setValues","");
   			  	$("#appaTypeId").combotree("setValues","");
   	  		}
   	  	});
   	  	 
   		$("#isShelfLife").change(function(){
   	   		if($(this).is(':checked')){
   	   			$("#isAssets").removeAttr("checked");
   	   			$("#isHighCons").removeAttr("disabled");
   	   			$("#isMater").removeAttr("checked");
   	   			$("#isMater").attr("disabled","disabled");
   	   			
   	   			$("#financeTypeId").combotree("setValues","");
   			  	$("#standTypeId").combotree("setValues","");
   			  	$("#appaTypeId").combotree("setValues","");
   			  	
				$("#financeTypeId").combotree("disable");
				$("#standTypeId").combotree("disable");
				$("#appaTypeId").combotree("disable");
				
				$("#isBatch").removeAttr("disabled"); 
	   			//保质期和保质期单位必填
	   			$("#shelfLife").removeAttr("disabled")
	   			$("#expUnit").removeAttr("disabled")
	   			$("#shelfLife").attr("datatype","*");
	   			$("#shelfLife").attr("ignore","checked");
	   			$("#expUnit").attr("datatype","*");
	   			$("#expUnit").attr("ignore","checked");
   	   		}else{
   	   			$("#isAssets").attr("checked","checked");
   				$("#isMater").removeAttr("disabled");
   	   			$("#isHighCons").removeAttr("checked");
   				$('#isHighCons').attr("disabled","disabled");
   				
   				$("#financeTypeId").combotree("enable");
   			  	$("#standTypeId").combotree("enable");
   			  	$("#appaTypeId").combotree("enable");
   			    $("#isBatch").removeAttr("disabled"); 
   			    
   				//保质期和保质期单位
	   			$("#shelfLife").removeAttr("datatype");
	   			$("#shelfLife").removeAttr("ignore");
	   			$("#shelfLife").attr("disabled","disabled");
	   			$("#expUnit").val("");
   	  			$("#expUnit").attr("disabled","disabled");
   	  			$("#expUnit").removeAttr("datatype");
   				
   	   		}
   	 });
   	  	//选择条形码管理或高值则条形码可以编辑
   	  	$("#isBarCode").change(function(){
   	  		if($(this).is(':checked')){	
   	  			$("#bartype").removeAttr("disabled");
   	  			$("#bartype").removeAttr("readonly");
   	  			$("#bartype").attr("datatype","*");
   	  		}else{
   	  			$("#bartype").val("");
   	  			$("#bartype").attr("disabled","disabled");
   	  			$("#bartype").removeAttr("datatype");
   	  		}
   	  	});
   	  	
   	 	$("#isHighCons").change(function(){
	  		if($(this).is(':checked')){	
	  			$("#isBarCode").attr("checked","checked"); 
				$("#isBarCode").attr("disabled","disabled");
	  			$("#barCode").removeAttr("disabled");
	  			
	  			$("#bartype").removeAttr("disabled");
   	  			$("#bartype").removeAttr("readonly");
   	  			$("#bartype").attr("datatype","*");
	  		}else{
	  			$("#barCode").val("");
	  			$("#barCode").attr("disabled",true);
	  			$("#isBarCode").removeAttr("disabled");
	  			$("#isBarCode").removeAttr("checked");
	  			
	  			$("#bartype").val("");
   	  			$("#bartype").attr("disabled","disabled");
   	  			$("#bartype").removeAttr("datatype");
	  		}
	  	});
   	  	
   	  	var isCons = '${lBaMaterialPage.isCons}';
   	 	isChecked(isCons,"isCons");   	  	
   	 	var isAssets = '${lBaMaterialPage.isAssets}';
	 	isChecked(isAssets,"isAssets");
   	 	var isMater = '${lBaMaterialPage.isMater}';
	 	isChecked(isMater,"isMater");
	 	var isBatch = '${lBaMaterialPage.isBatch}';
   	 	isChecked(isBatch,"isBatch");   	  	 
	 	var isShelfLife = '${lBaMaterialPage.isShelfLife}';
	 	isChecked(isShelfLife,"isShelfLife"); 
	 	if(isShelfLife !="1"){
	 		$("#shelfLife").removeAttr("datatype");
   			$("#shelfLife").removeAttr("ignore");
   			$("#shelfLife").attr("disabled","disabled");
   			$("#expUnit").val("");
  			$("#expUnit").attr("disabled","disabled");
  			$("#expUnit").removeAttr("datatype");
	 	}
   	 	  	  	
   	 	var isInstead = '${lBaMaterialPage.isInstead}';
	 	isChecked(isInstead,"isInstead");
	 	var isBarCode = '${lBaMaterialPage.isBarCode}';
	 	isChecked(isBarCode,"isBarCode");
	 	var isHighCons = '${lBaMaterialPage.isHighCons}';
	 	isChecked(isHighCons,"isHighCons"); 
	 	var isq = '${lBaMaterialPage.isQuality}';
	 	isChecked(isq,"isQuality");
	 	
	 	var isPurc = '${lBaMaterialPage.isPurc}';
	 	isChecked(isPurc,"isPurc");
	 	var isAssets = '${lBaMaterialPage.isAssets}';
	 	isChecked(isAssets,"isAssets");
	 	var isBarCode = '${lBaMaterialPage.isBarCode}';
	 	isChecked(isControl1,"isBarCode");
	 	var isBatch = '${lBaMaterialPage.isBatch}';
	 	isChecked(isControl1,"isBatch");
	 	var isCons = '${lBaMaterialPage.isCons}';
	 	isChecked(isCons,"isCons");
	 	var isSelfrestraint = '${lBaMaterialPage.isSelfrestraint}';
	 	isChecked(isSelfrestraint,"isSelfrestraint");
	 	var isHighCons = '${lBaMaterialPage.isHighCons}';
	 	isChecked(isHighCons,"isHighCons");
	 	var isInstead = '${lBaMaterialPage.isInstead}';
	 	isChecked(isInstead,"isInstead");
	 	var isControl1 = '${lBaMaterialPage.isMater}';
	 	isChecked(isMater,"isMater");
	 	var isQuality = '${lBaMaterialPage.isQuality}';
	 	isChecked(isQuality,"isQuality");
	 	var isDurable = '${lBaMaterialPage.isDurable}';
	 	isChecked(isDurable,"isDurable");
	 	 
	 	isChecked('${lBaMaterialPage.isShelfLife}',"isShelfLife");
	 	isChecked('${lBaMaterialPage.isLabourService}',"isLabourService");
	 	isChecked('${lBaMaterialPage.isHighCons}',"isHighCons");
	 	isChecked('${lBaMaterialPage.isOutsourcing}',"isOutsourcing");
	 	isChecked('${lBaMaterialPage.isServiceParts}',"isServiceParts");
	 	isChecked('${lBaMaterialPage.isSequenceManage}',"isSequenceManage");
	 	isChecked('${lBaMaterialPage.isPmMain}',"isPmMain");
	 	isChecked('${lBaMaterialPage.isPmFrom}',"isPmFrom");
	 	isChecked('${lBaMaterialPage.isOutsourcing}',"isOutsourcing");
	 	isChecked('${lBaMaterialPage.isServiceParts}',"isServiceParts");
	 	isChecked('${lBaMaterialPage.isControl}',"isControl");
	 	isChecked('${lBaMaterialPage.isInstrur}',"isInstrur");
	 	
	 	$("#isInstrur").change(function(){
   	  		if($(this).is(':checked')){
   	  			$('#appaTypeId').combotree({ disabled: false });
   	  		}else{
   	  			$('#appaTypeId').combotree('disable');
   	  		}
   	  	});

   	})
   	
	function isChecked(val,fieldName){
   		if(val == 1){
   			$("#"+fieldName).attr("checked",true);
   		}
   		
   	}
   	function vl(){
   		var b = true;
   	
	   	//耗材，资产，计量必须选择一个
	   	if(!($('#isCons').is(':checked'))){
				if(!($('#isAssets').is(':checked'))){
					if(!($('#isMater').is(':checked'))){
						alertTipTop('耗材，资产，计量必须选择一个！');
			   	  		b = false;
			   	  	}
	   	  		}
	   	 }
	 	var unit = $("input[name='materUnitId']").val();
	   	if(unit == null || unit ==""){
	   		alertTipTop("请选择计量单位")
	   		b = false;	
	   	}
	   	
	   	$("input[type='checkbox']").each(function(){
	   		
	   		if($(this).is(':checked')){
	   			$(this).val("1");
	   		}else{
	   			$(this).val("0");
	   		}
	   	});
	   	
	   	
	   	if($("#isShelfLife").attr('checked')){
	   		if(isEmpty($("#shelfLife").val())){
	   			alertTipTop("有效期不能为空")
	   			b = false;
	   		}else if(isEmpty($("select[name='expUnit']").find("option:selected").val())){
	   			alertTipTop("有效期单位不能为空")
	   			b = false;
	   		}
	   	}
	   
	   	return b;
   	}
  </script>
