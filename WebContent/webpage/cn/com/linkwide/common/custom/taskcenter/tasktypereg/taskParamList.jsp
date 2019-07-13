<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addTaskParamBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delTaskParamBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addTaskParamBtn').bind('click', function(){   
 		 var tr =  $("#add_taskParam_table_template tr").clone();
	 	 $("#add_taskParam_table").append(tr);
	 	 resetTrNum('add_taskParam_table');
	 	 return false;
    });  
	$('#delTaskParamBtn').bind('click', function(){   
      	$("#add_taskParam_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_taskParam_table'); 
        return false;
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    	if(location.href.indexOf("load=detail")!=-1){
			$(":input").attr("disabled","true");
			$(".datagrid-toolbar").hide();
		}
		//将表格的表头固定
	    $("#taskParam_table").createhftable({
	    	height:'300px',
			width:'auto',
			fixFooter:false
			});
    });
	//获取选中类型值，联动参照名称
	function changeType(obj) {
		//类型的name
		var name = obj.name;
		var index = obj.selectedIndex; // 选中索引
		var text = obj.options[index].text; // 选中文本
		var refer = name.replace(/type/, "referName");//参照名称的name
		//var referObj=document.getElementsByName(refer);//参照名称输入框对象
		//判断类型的值是否为空
		if (isNotEmpty(refer)) {
			if (text == '参照基础档案') {
				//隐藏输入框
				 $("input[name='"+refer+"']").val("");
				 $("input[name='"+refer+"']").css('display','none');
				 $("input[name='"+refer+"']").attr("disabled","disabled");
				 //显示下拉框
				 $("select[name='"+refer+"']").css('display','block');
				 $("select[name='"+refer+"']").removeAttr("disabled");
			} else if (text == '下拉框') {
				//隐藏下拉框
				$("select[name='"+refer+"']").val("");
				$("select[name='"+refer+"']").css('display','none');
				$("select[name='"+refer+"']").attr("disabled","disabled");
				 //显示下拉框
				$("input[name='"+refer+"']").removeAttr("disabled");
				$("input[name='"+refer+"']").css('display','block');
				//不做处理
			}else{
				//清空输入框的值
				$("input[name='"+refer+"']").val("");
				$("input[name='"+refer+"']").css('display','block');
				$("input[name='"+refer+"']").attr("disabled","disabled");
				//隐藏下拉框
				$("select[name='"+refer+"']").val("");
				$("select[name='"+refer+"']").css('display','none');
				$("select[name='"+refer+"']").attr("disabled","disabled");
			}
		}
	}
	//非空判断
	function isNotEmpty(s) {
		return ((s != undefined && s != null && s != "") ? true : false);
	}
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addTaskParamBtn" href="#">添加</a> <a id="delTaskParamBtn" href="#">删除</a> 
</div>
<table border="0" cellpadding="2" cellspacing="0" id="taskParam_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">序号</td>
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">操作</td>
				  <td align="center" bgcolor="#EEEEEE" style="width: 126px;">
						名称（阈值名称）
				  </td>
				  <td align="center" bgcolor="#EEEEEE" style="width: 126px;">
						描述（阈值描述）
				  </td>
				  <td align="center" bgcolor="#EEEEEE" style="width: 126px;">
						类型
				  </td>
				  <td align="center" bgcolor="#EEEEEE" style="width: 126px;">
						参照名称(下拉选项)
				  </td>
				  <td align="center" bgcolor="#EEEEEE" style="width: 150px;">
						是否非空
				  </td>
	</tr>
	<tbody id="add_taskParam_table">
	<c:if test="${fn:length(taskParamList)  > 0 }">
		<c:forEach items="${taskParamList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
					<input name="taskParamList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
					<input name="taskParamList[${stuts.index }].createName" type="hidden" value="${poVal.createName }"/>
					<input name="taskParamList[${stuts.index }].createBy" type="hidden" value="${poVal.createBy }"/>
					<input name="taskParamList[${stuts.index }].createDate" type="hidden" value="${poVal.createDate }"/>
					<input name="taskParamList[${stuts.index }].updateName" type="hidden" value="${poVal.updateName }"/>
					<input name="taskParamList[${stuts.index }].updateBy" type="hidden" value="${poVal.updateBy }"/>
					<input name="taskParamList[${stuts.index }].updateDate" type="hidden" value="${poVal.updateDate }"/>
					<input name="taskParamList[${stuts.index }].taskId" type="hidden" value="${poVal.taskId }"/>
				   <td align="center">
					  	<input name="taskParamList[${stuts.index }].name" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.name }">
					  <label class="Validform_label" style="display: none;">名称</label>
				   </td>
				   <td align="center">
					  	<input name="taskParamList[${stuts.index }].paramDesc" maxlength="500" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.paramDesc }">
					  <label class="Validform_label" style="display: none;">描述</label>
				   </td>
				   <td align="center">
				   			<select name="taskParamList[${stuts.index }].type" onchange="changeType(this)"> 
				   				<option value="">请选择</option>
				   				<option <c:if test="${poVal.type =='字符型'}"> selected="selected"</c:if>  value="字符型">字符型</option>
				   				<option <c:if test="${poVal.type =='逻辑型'}"> selected="selected"</c:if> value="逻辑型" >逻辑型</option>
				   				<option <c:if test="${poVal.type =='整型'}"> selected="selected"</c:if> value="整型">整型</option>
				   				<option <c:if test="${poVal.type =='Double型'}"> selected="selected"</c:if> value="Double型">Double型</option>
				   				<option <c:if test="${poVal.type =='参照基础档案'}"> selected="selected"</c:if> value="参照基础档案">参照基础档案</option>
				   				<option <c:if test="${poVal.type =='下拉框'}"> selected="selected"</c:if> value="下拉框">下拉框</option>
				   			</select>
					  <label class="Validform_label" style="display: none;">类型</label>
				   </td>
				   <td align="center">
					  	<input name="taskParamList[${stuts.index }].referName" maxlength="50" 
					  		type="text" class="inputxt"  
					  		<c:choose>
								   <c:when test="${poVal.type =='下拉框'}">  
								   		style="width:120px;display: block"
								   </c:when>
								    <c:when test="${poVal.type =='参照基础档案'}">  
								   		style="width:120px;display: none"
								   </c:when>
								   <c:otherwise>  
								   		style="width:120px;display: block"
								   </c:otherwise>
							</c:choose>
					  		<c:choose>
								   <c:when test="${poVal.type =='下拉框'}">  
								   </c:when>
								   <c:otherwise>  
								   		disabled="disabled" 
								   </c:otherwise>
							</c:choose>
					  		  value="${poVal.referName }">
					  		<select name="taskParamList[${stuts.index }].referName" 
					  		 		<c:choose>
										   <c:when test="${poVal.type =='参照基础档案'}">  
										   		style="width:120px;display: block"
										   </c:when>
										   <c:otherwise>  
										   		style="width:120px;display: none"
										   </c:otherwise>
									</c:choose>
							  		<c:choose>
										   <c:when test="${poVal.type =='参照基础档案'}">  
										   </c:when>
										   <c:otherwise>  
										   		disabled="disabled" 
										   </c:otherwise>
									</c:choose> 
					  		>
							<option value="请选择">请选择</option>
				 			<option <c:if test="${poVal.referName =='参照档案一'}"> selected="selected"</c:if> value="参照档案一">参照档案一</option>
				 			<option <c:if test="${poVal.referName =='参照档案二'}"> selected="selected"</c:if> value="参照档案二">参照档案二</option>
				 			<option <c:if test="${poVal.referName =='参照档案三'}"> selected="selected"</c:if> value="参照档案三">参照档案三</option>
				 			<option <c:if test="${poVal.referName =='参照档案四'}"> selected="selected"</c:if> value="参照档案四">参照档案四</option>
						</select>	
					  <label class="Validform_label" style="display: none;">参照名称</label>
				   </td>
				   <td align="center">
							<t:dictSelect field="taskParamList[${stuts.index }].isNull" type="list"
										typeGroupCode="sf_yn" defaultVal="${poVal.isNull }" hasLabel="false"  title="是否非空"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">是否非空</label>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>

