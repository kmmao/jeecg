<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>仓库表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="lBaWarehouseController.do?doAdd" tiptype="1" beforeSubmit="check_sub()">
					<input id="id" name="id" type="hidden" value="${lBaWarehousePage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							<i class="interval">*</i>仓库编码:
						</label>
					</td>
					<td class="value">
					     	 <input id="warehouseCode" name="warehouseCode" type="text" style="width: 150px" class="inputxt"  datatype="*1-36">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">仓库编码</label>
						</td>
					</tr>
					<tr>
					<td align="right">
						<label class="Validform_label">
							<i class="interval">*</i>仓库名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="warehouseName" name="warehouseName" type="text" style="width: 150px" class="inputxt"  datatype="*1-36">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">仓库名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								<i class="interval">*</i>所属机构:
							</label>
						</td>
						<td class="value">
							<t:comboTree id="orgCodes" name="orgCodes" url="departController.do?comboTreeType" value='${lBaWarehousePage.orgCodes}'	width="155" multiple="true" ></t:comboTree>
						    <span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所属机构</label>
						</td>
					</tr>
					<tr>
					<td align="right">
						<label class="Validform_label">
							科室:
						</label>
					</td>
					<td class="value">
					 <t:comboList name="deptId" url="dictListController.do?departList" id="deptId" idField="id" textField="departname" ignore="ignore"
					 field="orgCode,departname,description,id,orgType,iflater,mobile,address,"  
					 panelWidth="400" title="科室编码:80,科室名称:120" ></t:comboList>
					 <label class="Validform_label" style="display: none;">科室</label>
					 <font color="red"></font>
					</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							负责人:
						</label>
					</td>
					<td class="value">
							<t:comboList id="manager"  name="manager"  url="dictListController.do?userList" idField="id" textField="realName" ignore="ignore"
						 field="userName,realName,id,"  
						 panelWidth="400" title="编码:80,用户名称:120" ></t:comboList>
							
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">负责人</label> 
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							仓库属性:
						</label>
					</td>
					<td class="value">
							<t:dictSelect field="attr" type="list"  datatype="*"   typeGroupCode="storeAttr"  defaultVal="" hasLabel="false"  title="证件状态" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">仓库属性</label> 
					</td>
				</tr>
				<!-- <tr>
					<td align="right">
						<label class="Validform_label">
						 条形码:
						</label>
					</td>
					<td class="value">
							<input id="barCode" name="barCode" type="text" style="width: 150px" class="inputxt"  datatype="*" ignore="ignore" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">负责人</label> 
					</td>
				</tr> -->
				<tr>
					<td align="right">
						<label class="Validform_label">
						 仓库地址:
						</label>
					</td>
					<td class="value">
							<input id="address" name="address" type="text" style="width: 150px" class="inputxt"  datatype="*" ignore="ignore" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">仓库地址</label> 
					</td>
				</tr>
				<tr style="display:none">
					<td align="right">
						<label class="Validform_label">
							计入成本:
						</label>
					</td>
					<td class="value">
					     	<!--  <input id="isIncludeCost" name="isIncludeCost" type="text" style="width: 150px" class="inputxt"  datatype="*"> -->
					     	<t:dictSelect field="isIncludeCost" type="list" hasShowCheck="false" hasLabel="false" typeGroupCode="whether" defaultVal="0" title="是否计入成本"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否计入成本</label>
							
						</td>
				</tr>
				<tr>
				
					<td align="right">
						<label class="Validform_label">
							资产仓:
						</label>
					</td>
					<td class="value">
					     	<!--  <input id="isAssets" name="isAssets" type="text" style="width: 150px" class="inputxt"  datatype="*"> -->
					     	<t:dictSelect field="isAssets" type="list" hasShowCheck="false" hasLabel="false" typeGroupCode="whether" defaultVal="0" title="是否资产仓"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否资产仓</label>
						</td>
					</tr>
				<tr>
				
					<td align="right">
						<label class="Validform_label">
							是否货位管理:
						</label>
					</td>
					<td class="value">
					     	<t:dictSelect field="isLocation" type="list" hasShowCheck="false" hasLabel="false" typeGroupCode="yes_no_int" defaultVal="0" title="是否货位管理"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否资产仓</label>
						</td>
					</tr>
				<tr>
				
					<td align="right">
						<label class="Validform_label">
							是否控制:
						</label>
					</td>
					<td class="value">
					     	<!--  <input id="isAssets" name="isAssets" type="text" style="width: 150px" class="inputxt"  datatype="*"> -->
					     	<t:dictSelect field="isCtrl" type="list" hasShowCheck="false" hasLabel="false" typeGroupCode="whether" defaultVal="0" title="是否控制"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否资产仓</label>
						</td>
					</tr>
					<tr>
					<td align="right">
						<label class="Validform_label">
						 备注:
						</label>
					</td>
					<td class="value">
							<input id="remark" name="remark" type="text" style="width: 150px" class="inputxt"  datatype="*" ignore="ignore" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label> 
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/warehouse/lBaWarehouse.js"></script>	
  <script src = "webpage/cn/com/linkwide/mate/util/vali_repeat_util.js"></script>	
