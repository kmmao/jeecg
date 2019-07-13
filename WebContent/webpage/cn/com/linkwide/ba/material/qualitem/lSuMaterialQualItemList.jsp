<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addLSuMaterialQualItemBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delLSuMaterialQualItemBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addLSuMaterialQualItemBtn').bind('click', function(){   
 		 var tr =  $("#add_lSuMaterialQualItem_table_template tr").clone();
	 	 $("#add_lSuMaterialQualItem_table").append(tr);
	 	 resetTrNum('add_lSuMaterialQualItem_table');
	 	 return false;
    });  
	$('#delLSuMaterialQualItemBtn').bind('click', function(){   
      	$("#add_lSuMaterialQualItem_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_lSuMaterialQualItem_table'); 
        return false;
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    	if(location.href.indexOf("load=detail")!=-1){
			$(":input").attr("disabled","true");
			$(".datagrid-toolbar").hide();
		}
		//将表格的表头固定
	    $("#lSuMaterialQualItem_table").createhftable({
	    	height:'300px',
			width:'auto',
			fixFooter:false
			});
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addLSuMaterialQualItemBtn" href="#">添加</a> <a id="delLSuMaterialQualItemBtn" href="#">删除</a> 
</div>
<table border="0" cellpadding="2" cellspacing="0" id="lSuMaterialQualItem_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">序号</td>
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">操作</td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						资质类型<font color="red">*</font>
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						资质编码<font color="red">*</font>
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						资质名称<font color="red">*</font>
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						生效日期<font color="red">*</font>
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						到期日期<font color="red">*</font>
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						到期控制<font color="red">*</font>
				  </td>
	</tr>
	<tbody id="add_lSuMaterialQualItem_table">
	<c:if test="${fn:length(lSuMaterialQualItemList)  > 0 }">
		<c:forEach items="${lSuMaterialQualItemList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
					<input name="lSuMaterialQualItemList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
					<input name="lSuMaterialQualItemList[${stuts.index }].materialQualId" type="hidden" value="${poVal.materialQualId }"/>
				   <td align="left">
				   		<script type="text/javascript">
				   		$("input[name='lSuMaterialQualItemList[${stuts.index }].qualTypeIdH']").combotree({
					  			data:qualTypeDate,
					  			onSelect:function(node){
					  				$("input[name='lSuMaterialQualItemList[${stuts.index }].qualTypeId']").val(node.id);
					  			}
					  		 });
					  	</script>
					  	<input name="lSuMaterialQualItemList[${stuts.index }].qualTypeIdH" maxlength="36" 
					  		type="text" class="inputxt"  style="width:120px;" value="${poVal.qualTypeId }">
					  		<input name="lSuMaterialQualItemList[${stuts.index }].qualTypeId" maxlength="36" 
					  		type="hidden" class="inputxt"  style="width:120px;" datatype="*" value="${poVal.qualTypeId }">
					  <label class="Validform_label" style="display: none;">资质类型</label>
				   </td>
				   <td align="left">
					  	<input name="lSuMaterialQualItemList[${stuts.index }].qualCode" maxlength="36" 
					  		type="text" class="inputxt"  style="width:120px;" datatype="*" value="${poVal.qualCode }">
					  <label class="Validform_label" style="display: none;">资质编码</label>
				   </td>
				   <td align="left">
					  	<input name="lSuMaterialQualItemList[${stuts.index }].qualFullName" maxlength="36" 
					  		type="text" class="inputxt"  style="width:120px;" datatype="*" value="${poVal.qualFullName }">
					  <label class="Validform_label" style="display: none;">资质名称</label>
				   </td>
				   <td align="left">
							<input name="lSuMaterialQualItemList[${stuts.index }].effectDate" maxlength="0" 
					  		type="text" class="Wdate" onClick="WdatePicker()"  style="width:120px;height:16px;"   datatype="*" value="<fmt:formatDate value='${poVal.effectDate}' type="date" pattern="yyyy-MM-dd"/>">
					  <label class="Validform_label" style="display: none;">生效日期</label>
				   </td>
				   <td align="left">
							<input name="lSuMaterialQualItemList[${stuts.index }].overDate" maxlength="0" 
					  		type="text" class="Wdate" onClick="WdatePicker()"  style="width:120px;height:16px;"   datatype="*" value="<fmt:formatDate value='${poVal.overDate}' type="date" pattern="yyyy-MM-dd"/>">
					  <label class="Validform_label" style="display: none;">到期日期</label>
				   </td>
				   <td align="left">
					  		<select name="lSuMaterialQualItemList[${stuts.index }].isControl" style="height:22px;width: 132px;">
					  			<option value="0" <c:if test='${poVal.isControl eq 0}'>selected="selected" </c:if> >否</option>
					  			<option value="1" <c:if test='${poVal.isControl eq 1}'>selected="selected" </c:if>>是</option>
					  		</select>
					  <label class="Validform_label" style="display: none;">到期控制</label>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
