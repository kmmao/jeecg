<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>物资库存表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="lBaMateInventoryController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${lBaMateInventoryPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								仓库:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="warehouseId" type="list" 		datatype="*" dictTable="l_ba_warehouse" dictField="id" dictText="warehouse_name"   defaultVal="${lBaMateInventoryPage.warehouseId}" hasLabel="false"  title="仓库" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">仓库</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								物资:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="materialId" type="list" 		datatype="*" dictTable="l_ba_material" dictField="id" dictText="material_name"   defaultVal="${lBaMateInventoryPage.materialId}" hasLabel="false"  title="物资" ></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">物资</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								数量:
							</label>
						</td>
						<td class="value">
						    <input id="num" name="num" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="checked"  value='${lBaMateInventoryPage.num}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">数量</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								单价:
							</label>
						</td>
						<td class="value">
						    <input id="unitPrice" name="unitPrice" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${lBaMateInventoryPage.unitPrice}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">单价</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								批号:
							</label>
						</td>
						<td class="value">
						    <input id="batchNo" name="batchNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${lBaMateInventoryPage.batchNo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">批号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								物资条码:
							</label>
						</td>
						<td class="value">
						    <input id="barCode" name="barCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${lBaMateInventoryPage.barCode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">物资条码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								有效期:
							</label>
						</td>
						<td class="value">
									   <input id="periodDate" name="periodDate" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  ignore="ignore"  value='<fmt:formatDate value='${lBaMateInventoryPage.periodDate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">有效期</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/minventory/lBaMateInventory.js"></script>		
