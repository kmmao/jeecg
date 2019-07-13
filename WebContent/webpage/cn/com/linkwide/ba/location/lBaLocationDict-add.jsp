<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>货位档案</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  var pCode = "${lBaLocationDictPage.code }";
    function check(obj){
		  var str = obj.value; 
		  if(pCode !=""){
			  if(str.length - pCode.length !=2){
				  tip("编码长度不符合规范！")
				  obj.value =pCode;
			  }
			  if(str.substring(0,pCode.length) !=pCode){
				  tip("必须以父节点编码开头！")
				  obj.value =pCode;
			  }
		  }else{
			  if(str.length%2 !=0){
				  tip("编码不符合规范！")
				  obj.value ="";
			  }
			  if(str.length>6){
				  tip("编码不符合规范！")
				  obj.value ="";
			  }
		  }
	  }
    
    var varid ="${lBaLocationDictPage.wareId}";
   
    $(document).ready(function(){
    	 if(varid !=""){
        	 $("input[name='wareId").attr('readonly', true);
        }
        
    });
    function back(data) {
		if (data.success == true) {
		  	if(frameElement.api != undefined){
		  		var win = frameElement.api.opener.parent;
				win.location.reload();
            	frameElement.api.close();
             	window.location.reload();
			}else{
				window.parent.location.reload();
			}
		}else{
			$.Showmsg(data.msg);
			return false;
		}
	}
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" callback="@Override back" action="lBaLocationDictController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${lBaLocationDictPage.id }"/>
					<input id="parentId" name="parentId" type="hidden"  value="${lBaLocationDictPage.parentId }" />
					<input id="loLeve" name="loLeve" type="hidden"  value="${lBaLocationDictPage.loLeve }" />
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							货位编码:
						</label>
					</td>
					<td class="value">
					     	 <input id="code" name="code" type="text" style="width: 150px" class="inputxt" validType="l_ba_location_dict,code,id" datatype="*" value="${lBaLocationDictPage.code }"  onblur="check(this)" ignore="checked"  />
							<span class="Validform_checktip">编码规则:2-2-2</span>
							<label class="Validform_label" style="display: none;">货位编码</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							货位名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="name" name="name" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">货位名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							所属仓库:
						</label>
					</td>
					<td class="value">
						<c:if test="${fn:length(lBaLocationDictPage.wareId) >0 }">
							 <t:comboList name="wareId" url="dictListController.do?wareHouseList&isLocation=1" id="wareId" idField="id" textField="warehouseName" ignore="ignore" 			
								 field="warehouseCode,warehouseName,id," value='${lBaLocationDictPage.wareId}' 		disabled="true"
								 panelWidth="400" title="仓库编码:80,仓库名称:120" ></t:comboList>
							 <span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所属仓库</label>
						</c:if>
						<c:if test="${fn:length(lBaLocationDictPage.wareId) ==0 }">
							 <t:comboList name="wareId" url="dictListController.do?wareHouseList&isLocation=1" id="wareId" idField="id" textField="warehouseName" ignore="ignore" 			
								 field="warehouseCode,warehouseName,id," value='${lBaLocationDictPage.wareId}' 	
								 panelWidth="400" title="仓库编码:80,仓库名称:120" ></t:comboList>
							 <span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所属仓库</label>
						</c:if>
					     	
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							最大体积:
						</label>
					</td>
					<td class="value">
					     	 <input id="maxV" name="maxV" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">最大体积</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							最大重量:
						</label>
					</td>
					<td class="value">
					     	 <input id="maxG" name="maxG" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">最大重量</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							条形码:
						</label>
					</td>
					<td class="value">
					     	 <input id="barCode" name="barCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">条形码</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value">
					     	 <input id="remark" name="remark" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
				</tr>
				 
				 <tr>
					<td align="right">
						<label class="Validform_label">
							是否停用:
						</label>
					</td>
					<td class="value">
					     	<t:dictSelect field="isStop" type="list"  datatype="n"   typeGroupCode="yes_no_int"  defaultVal="0"  hasLabel="false"  title="是否停用" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否停用</label>
						</td>
				</tr>
				 
			 
				
							</table>
		</t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/ba/location/lBaLocationDict.js"></script>		
