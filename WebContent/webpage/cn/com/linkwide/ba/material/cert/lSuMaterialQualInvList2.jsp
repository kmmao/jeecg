<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addVendorCertInvBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delVendorCertInvBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addVendorCertInvBtn').bind('click', function(){   
 		 var tr =  $("#add_vendorCertInv_table_template tr").clone();
	 	 $("#add_vendorCertInv_table").append(tr);
	 	 resetTrNum('add_vendorCertInv_table');
	 	 return false;
    });  
	$('#delVendorCertInvBtn').bind('click', function(){   
      	$("#add_vendorCertInv_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_vendorCertInv_table'); 
        return false;
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    	if(location.href.indexOf("load=detail")!=-1){
			$(":input").attr("disabled","true");
			$(".datagrid-toolbar").hide();
		}
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addVendorCertInvBtn" href="#">添加</a> <a id="delVendorCertInvBtn" href="#">删除</a> 
</div>
<table border="0" cellpadding="2" cellspacing="0" id="vendorCertInv_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">序号</td>
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">操作</td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						材料编码
				  </td>
	</tr>
	<tbody id="add_vendorCertInv_table">
	<c:if test="${fn:length(vendorCertInvList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
					<input name="vendorCertInvList[0].id" type="hidden"/>
					<input name="vendorCertInvList[0].updateDate" type="hidden"/>
					<input name="vendorCertInvList[0].sysCompanyCode" type="hidden"/>
					<input name="vendorCertInvList[0].certId" type="hidden"/>
				  <td align="left">
					  	<input name="vendorCertInvList[0].invCode" maxlength="32" type="text" class="inputxt"  style="width:120px;"  datatype="*"  ignore="checked" >
					  <label class="Validform_label" style="display: none;">材料编码</label>
					</td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(vendorCertInvList)  > 0 }">
		<c:forEach items="${vendorCertInvList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
						<input name="vendorCertInvList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
						<input name="vendorCertInvList[${stuts.index }].updateDate" type="hidden" value="${poVal.updateDate }"/>
						<input name="vendorCertInvList[${stuts.index }].sysCompanyCode" type="hidden" value="${poVal.sysCompanyCode }"/>
						<input name="vendorCertInvList[${stuts.index }].certId" type="hidden" value="${poVal.certId }"/>
				   <td align="left">
					  	<input name="vendorCertInvList[${stuts.index }].invCode" maxlength="32" type="text" class="inputxt"  style="width:120px;"  datatype="*"  ignore="checked"  value="${poVal.invCode }"/>
					  <label class="Validform_label" style="display: none;">材料编码</label>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
