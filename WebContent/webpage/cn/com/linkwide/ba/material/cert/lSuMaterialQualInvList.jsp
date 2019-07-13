<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addlSuMaterialQualInvBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#dellSuMaterialQualInvBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addlSuMaterialQualInvBtn').bind('click', function(){   
 		 var tr =  $("#add_lSuMaterialQualInv_table_template tr").clone();
	 	 $("#add_lSuMaterialQualInv_table").append(tr);
	 	 resetTrNum('add_lSuMaterialQualInv_table');
	 	addSelect( $("#add_lSuMaterialQualInv_table tr").length-1 );
	 	 return false;
    });  
	$('#dellSuMaterialQualInvBtn').bind('click', function(){   
      	$("#add_lSuMaterialQualInv_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_lSuMaterialQualInv_table'); 
        return false;
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    	if(location.href.indexOf("load=detail")!=-1){
			$(":input").attr("disabled","true");
			$(".datagrid-toolbar").hide();
		}
    });
    
    function addSelect(index){ 
    	$("input[name='lSuMaterialQualInvList["+index+"].invCode']").combogrid({
				panelWidth: 250,
				mode: 'remote', 
				idField: 'materialCode',
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
					$("input[name='lSuMaterialQualInvList["+index+"].invCode']").val(rowData.materialCode); 
					$("input[name='lBaMaterialList["+index+"].specModel']").val(rowData.specModel); 
					$("input[name='lBaMaterialList["+index+"].materUnitId']").val(rowData.materUnitName); 
				},
				//fitColumns: true
			})
    }
    
   function initInvSelect() {
    	
	   var length = $("#lSuMaterialQualInv_table tr").length-1; 
	   for(var index=0;index <length;index++){
		   addSelect(index);
	   } 
    	 
    } 
   initInvSelect(); 
   
   
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addlSuMaterialQualInvBtn" href="#">添加</a> <a id="dellSuMaterialQualInvBtn" href="#">删除</a> 
</div>
<table border="0" cellpadding="2" cellspacing="0" id="lSuMaterialQualInv_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">序号</td>
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">操作</td> 
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						材料编码
				  </td> 
				   <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						规格型号
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						计量单位
				  </td>
				    
	</tr>
	<tbody id="add_lSuMaterialQualInv_table">
	<c:if test="${fn:length(lSuMaterialQualInvList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
					<input name="lSuMaterialQualInvList[0].id" type="hidden"/>
					<input name="lSuMaterialQualInvList[0].updateDate" type="hidden"/>
					<input name="lSuMaterialQualInvList[0].sysCompanyCode" type="hidden"/>
					<input name="lSuMaterialQualInvList[0].certId" type="hidden"/> 
				    <td align="left">
					  	<input name="lSuMaterialQualInvList[0].invCode" maxlength="32" type="text" class="inputxt"  style="width:120px;"   ignore="checked" >
					  <label class="Validform_label" style="display: none;">材料编码</label>
					</td>
				    <td align="left">
					  	<input name="lBaMaterialList[0].specModel" maxlength="32" type="text" class="inputxt"  style="width:120px;"   ignore="checked" >
					  <label class="Validform_label" style="display: none;">规格型号</label>
					</td>
					
					 <td align="left">
					  	<input name="lBaMaterialList[0].materUnitId" maxlength="32" type="text" class="inputxt"  style="width:120px;"   ignore="checked" >
					  <label class="Validform_label" style="display: none;">计量单位</label>
					</td>
				  
   			</tr>
	</c:if>
	<c:if test="${fn:length(lSuMaterialQualInvList)  > 0 }">
		<c:forEach items="${lSuMaterialQualInvList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
						<input name="lSuMaterialQualInvList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
						<input name="lSuMaterialQualInvList[${stuts.index }].updateDate" type="hidden" value="${poVal.updateDate }"/>
						<input name="lSuMaterialQualInvList[${stuts.index }].sysCompanyCode" type="hidden" value="${poVal.sysCompanyCode }"/>
						<input name="lSuMaterialQualInvList[${stuts.index }].certId" type="hidden" value="${poVal.certId }"/>
					 <td align="left">
					  	<input name="lSuMaterialQualInvList[${stuts.index }].invCode" maxlength="32" type="text" class="inputxt"   style="width:120px;padding:3px 26px;box-sizing:border-box;"  type="text"   ignore="ignore"  value="${poVal.invCode }"/>
					  <label class="Validform_label" style="display: none;">材料编码</label>
				   </td>  
				   	<c:forEach items="${lBaMaterialList}" var="poVal2" varStatus="stuts2"> 
				   		<c:if test="${poVal.invCode == poVal2.materialCode}">
					    <td align="left">
						  	<input name="lBaMaterialList[${stuts2.index }].specModel" maxlength="32" type="text" class="inputxt"   style="width:120px;padding:3px 26px;box-sizing:border-box;"  type="text"   ignore="ignore"  value="${poVal2.specModel}"/>
						  <label class="Validform_label" style="display: none;">规格型号</label>
					    </td>
					   <td align="left">
						  	<input name="lBaMaterialList[${stuts2.index }].materUnitId" maxlength="32" type="text" class="inputxt"   style="width:120px;padding:3px 26px;box-sizing:border-box;"  type="text"   ignore="ignore"  value="${poVal2.materUnitId}"/>
						  <label class="Validform_label" style="display: none;">计量单位</label>
					   </td>
					   </c:if>	
				   </c:forEach> 
				   <c:if test="${fn:length(lBaMaterialList)  <= 0 }">
					   <td align="left">
						  	<input name="lBaMaterialList[${stuts.index }].specModel" maxlength="32" type="text" class="inputxt"   style="width:120px;padding:3px 26px;box-sizing:border-box;"  type="text"   ignore="ignore"  value=""/>
						  <label class="Validform_label" style="display: none;">规格型号</label>
					   </td>
					   <td align="left">
						  	<input name="lBaMaterialList[${stuts.index }].materUnitId" maxlength="32" type="text" class="inputxt"   style="width:120px;padding:3px 26px;box-sizing:border-box;"  type="text"   ignore="ignore"  value=""/>
						  <label class="Validform_label" style="display: none;">计量单位</label>
					   </td>
				  </c:if>	
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
