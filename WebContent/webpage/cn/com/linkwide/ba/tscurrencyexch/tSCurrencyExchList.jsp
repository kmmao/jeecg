<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addTSCurrencyExchBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delTSCurrencyExchBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addTSCurrencyExchBtn').bind('click', function(){   
 		 var tr =  $("#add_tSCurrencyExch_table_template tr").clone();
 	 	 $("#add_tSCurrencyExch_table").append(tr);
	 	 resetTrNum('add_tSCurrencyExch_table');
	 	 return false;
    });  
	$('#delTSCurrencyExchBtn').bind('click', function(){   
      	$("#add_tSCurrencyExch_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_tSCurrencyExch_table'); 
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
 <!-- <div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addTSCurrencyExchBtn" href="#">添加</a> <a id="delTSCurrencyExchBtn" href="#">删除</a> 
</div>  -->
<table border="0" cellpadding="2" cellspacing="0" id="tSCurrencyExch_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">序号</td>
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">操作</td>
				  <td align="left" bgcolor="#EEEEEE" style="display:none;">
						会计期间
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="display:none;">
						汇率类型
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						日期
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						汇率
				  </td>
	</tr>
	<tbody id="add_tSCurrencyExch_table">
		<c:forEach items="${tSCurrencyExchList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
						<input name="tSCurrencyExchList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
						<input name="tSCurrencyExchList[${stuts.index }].bpmStatus" type="hidden" value="${poVal.bpmStatus }"/>
						<input name="tSCurrencyExchList[${stuts.index }].createName" type="hidden" value="${poVal.createName }"/>
						<input name="tSCurrencyExchList[${stuts.index }].createBy" type="hidden" value="${poVal.createBy }"/>
						<input name="tSCurrencyExchList[${stuts.index }].updateName" type="hidden" value="${poVal.updateName }"/>
						<input name="tSCurrencyExchList[${stuts.index }].updateBy" type="hidden" value="${poVal.updateBy }"/>
						<input name="tSCurrencyExchList[${stuts.index }].sysOrgCode" type="hidden" value="${poVal.sysOrgCode }"/>
						<input name="tSCurrencyExchList[${stuts.index }].sysCompanyCode" type="hidden" value="${poVal.sysCompanyCode }"/>
						<input name="tSCurrencyExchList[${stuts.index }].createDate" type="hidden" value="${poVal.createDate }"/>
						<input name="tSCurrencyExchList[${stuts.index }].updateDate" type="hidden" value="${poVal.updateDate }"/>
					  	<input name="tSCurrencyExchList[${stuts.index }].year" type="hidden"  value="${poVal.year }"/>
					  	<input name="tSCurrencyExchList[${stuts.index }].currencyId" type="hidden"  value="${poVal.currencyId }"/>
				   <td align="left" style="display:none;">
				   		<%-- <t:dictSelect field="tSCurrencyExchList[${stuts.index }].exchType" type="radio"  typeGroupCode="exchType" defaultVal="${poVal.exchType }" hasLabel="false"  title="汇率类型" ></t:dictSelect> --%>
					  	<input name="tSCurrencyExchList[${stuts.index }].exchType" maxlength="32" type="hidden" class="inputxt"  style="width:120px;"  ignore="ignore"  value="${poVal.exchType }"/>
					  <label class="Validform_label" style="display: none;">汇率类型</label>
				   </td>
				   <td align="left" style="display:none;">
				   		<%-- <input  name="tSCurrencyExchList[${stuts.index} ].period" type="text" style="width: 150px" class="Wdate" onClick="selectMonth()" value="${poVal.period }" > --%>
					  	<input name="tSCurrencyExchList[${stuts.index }].period" maxlength="32" type="hidden" class="inputxt"  style="width:120px;"  ignore="ignore"  value="${poVal.period }"/> 
					  <label class="Validform_label" style="display: none;">会计期间</label>
				   </td>
				   <td align="left">
					  	<input name="tSCurrencyExchList[${stuts.index }].exchDate" maxlength="32" type="text" class="inputxt" readonly="readonly"  style="width:120px;"   value="${poVal.exchDate }"/>
					  <label class="Validform_label" style="display: none;">日期</label>
				   </td>
				   <td align="left">
					  	<input name="tSCurrencyExchList[${stuts.index }].exchRate" maxlength="32" type="text" class="inputxt"  style="width:120px;"  ignore="ignore"  value="${poVal.exchRate }"/>
					  <label class="Validform_label" style="display: none;">汇率</label>
				   </td>
   			</tr>
		</c:forEach>
	</tbody>
</table>
<script type="text/javascript">
  //会计期间下拉
  function selectMonth(){
	  WdatePicker({dateFmt:'yyyy-MM',isShowToday:false,isShowClear:false});
  }
   $(document).ready(function(){
	   //汇率类型
	   var exchType = $("input[name='tSCurrencyExchList[0].exchType']").val();
	   //会计期间
	   var period = $("input[name='tSCurrencyExchList[0].period']").val();
	   //赋值
	   if(period){
		   $("#period").val(period);
	   }
	   $("select[name='exchType']").val(exchType);
	   /* if(exchType){
		   exchTypeChange();
	   } */
	   
  });
   //会计期间和汇率类型onchange事件 
   function exchTypeChange(){
	   // 获取当前年 
	   var currentYear = new Date().getFullYear();
	   //获取当前月 
	   var currentMonth = new Date().getMonth()+1<10?"0"+(new Date().getMonth()+1):new Date().getMonth()+1;
	   //当前年月 
	   var currentYearMonth = currentYear+"-"+currentMonth;
 	  //汇率类型  （1.固定汇率；2.浮动汇率）
 	  var exchType = $("select[name='exchType']").val();
 	  if(exchType=='1'){//固定汇率
 		   $("#add_tSCurrencyExch_table").html(""); 
 		  //禁用会计期间
 		   $("#period").val();
 		  $("#period").attr("disabled","disabled");
 		  //循环当前年的12个月 
	 	  for(var i=1;i<=12;i++){
			  var tr =  $("#add_tSCurrencyExch_table_template tr").clone();
		  	  $("#add_tSCurrencyExch_table").append(tr);
		  	  resetTrNum('add_tSCurrencyExch_table');
		  	  var month=i<10?("0"+i):i;
		  	  // 当前年的 12个月 
		  	  var yearMonth = currentYear+"-"+month;
			   var index = i-1;
			   //年度
			   $("input[name='tSCurrencyExchList["+index+"].year']").val(currentYear);
			   //汇率类型
			   $("input[name='tSCurrencyExchList["+index+"].exchType']").val(exchType);
			   //会计期间
			   $("input[name='tSCurrencyExchList["+index+"].period']").val(null);
			   //日期
		 	   $("input[name='tSCurrencyExchList["+index+"].exchDate']").val(yearMonth);
		  }
 	  }else{ //浮动汇率
 		 $("#period").removeAttr("disabled");
	 	  if(!$("#period").val()){
	 		 // 默认 会计期间为当前年月
	 		 $("#period").val(currentYearMonth);
	 	  }
 		//会计期间
 	 	var period = $("#period").val();
 		$("#add_tSCurrencyExch_table").html(""); 
 		var periodStr = period.split("-");
 		//获取会计期间所在月份的天数
 		var day = new Date(periodStr[0],periodStr[1],0).getDate();
 		for(var i=1;i<=day;i++){
 			var monthDay = i<10?("0"+i):i;
 			 var tr =  $("#add_tSCurrencyExch_table_template tr").clone();
		  	  $("#add_tSCurrencyExch_table").append(tr);
		  	  resetTrNum('add_tSCurrencyExch_table');
		  	  var index=i-1;
		  		//年度
			   $("input[name='tSCurrencyExchList["+index+"].year']").val(periodStr[0]);
			   //汇率类型
			   $("input[name='tSCurrencyExchList["+index+"].exchType']").val(exchType);
			   //会计期间
			   $("input[name='tSCurrencyExchList["+index+"].period']").val(period);
			   //日期
		 	   $("input[name='tSCurrencyExchList["+index+"].exchDate']").val(period+"-"+monthDay); 
 		}
 	  }
 	  
   }
</script>