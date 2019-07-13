<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>编辑发生时间</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
    <link href="plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <style>
    	table{
    		width:98%;
    		margin:0 auto;
    	}
    	table td{
    		padding:10px 10px 0 10px;
    	}
    	table tr:nth-of-type(even){
    		border:1px solid #ddd;
    		display:block;
    		min-height:70px;
    		position:relative;
    	}
    	.tab_tdW{
    		width:180px;
    	}
    	.pos_l{
    		position:absolute;
    		top:10px;
    		left:10px;
    	}
    	.month_spanW{
    		width:40px;
    		display:inline-block;
    	}
    </style>
  <script type="text/javascript">
  $(function(){
	  getData();
  });
  //是否循环执行(默认执行一次)
  var isruncycledaily="N";
  //根据发生频率联动
  function frequencyChange(obj){
	  var frequencytype=obj.options[obj.options.selectedIndex].value;
	  var dayDiv=document.getElementById("dayDiv");
	  var weekDiv=document.getElementById("weekDiv");
	  var monthDiv=document.getElementById("monthDiv");
	  if(frequencytype=="day"){//每天
		  //天模块显示
		  dayDiv.style.display="block";
	      //周模块隐藏
		  weekDiv.style.display="none";
	      //月模块隐藏
		  monthDiv.style.display="none";
	      //清空月的间隔
		  $("#intervalMonth").val("");
	      //清空周月选中间隔
	      $('#weekDiv').find('input:checked').prop('checked',false);
	      $('#monthDiv').find('input:checked').prop('checked',false);
	  }else if(frequencytype=="month"){//月
		  //月模块显示
		  monthDiv.style.display="block";
		  //天模块隐藏
		  dayDiv.style.display="none";
	      //周模块隐藏
		  weekDiv.style.display="none";
		  //清空周选中间隔
	      $('#weekDiv').find('input:checked').prop('checked',false);
	  }else if(frequencytype=="week"){//周
		//周模块显示
		weekDiv.style.display="block";
		//天模块隐藏
		dayDiv.style.display="none";
		//月模块隐藏
		monthDiv.style.display="none";
		//清空月的间隔
		$("#intervalMonth").val("");
		//清空月选中间隔
	    $('#monthDiv').find('input:checked').prop('checked',false);
	    $('#monthDiv').find('input').attr('disabled',false);
	  }
	  
  }
  //一天内发生次数的联动
  function dayTypeChange(obj){
	  var dayType=obj.options[obj.options.selectedIndex].value;
	 //一天内执行一次
	 var type1=document.getElementById("type1");
	 //一天内多次执行
	 var type2=document.getElementById("type2");
	 //是否循环执行(默认执行一次)
	 if(dayType=="1"){//一天发生一次
		 type1.style.display="block";
		 type2.style.display="none";
		//设置是否循环执行
		 isruncycledaily="N";
	 }else if(dayType=="2"){//周期发生
		 type2.style.display="block";
		 type1.style.display="none";
		 //设置是否循环执行
		 isruncycledaily="Y";
	 }
	//是否循环执行
	 document.getElementById("isruncycledaily").value=isruncycledaily;
  }	
  //发生频率按月定义,频率范围
  var aNum=0;
  function frequencyRange(obj){//选择最后一天
	  if($('#'+obj+':checked').prop('checked')!==true){
		  $('#monthDiv').find('input').attr('disabled',false);
	  }else{
		  $('#monthDiv').find('input:not(#'+obj+')').attr('disabled',true);
	  }
  }
//自定义间隔时间
  function frequencyRange1(obj){
	  var oVal=$('#'+obj).val();
	  if(oVal==''){
		  $('#monthDiv').find('input').attr('disabled',false);
	  }else{
		  var reg="^([12][0-9]|30|[1-9])$";
		  var r =oVal.match(reg);  
		  if(r==null){
			  alert("请输入1到30内的整数");
			  $('#'+obj).val("");
		  }
		  $('#monthDiv').find('input:not(#'+obj+')').attr('disabled',true);
	  }
  }
  //根据日历选择间隔时间
  function frequencyRange2(){
	  var oChk = allCheckType();//调用循环函数
		if(oChk){
			$('#monthDiv').find('input:eq(0)').attr('disabled',true);
			$('#monthDiv').find('input:eq(1)').attr('disabled',true);
		}else{
			$('#monthDiv').find('input').attr('disabled',false);
		}

  }
  function allCheckType(){
		var chk=0;
		$('#monthDiv .month_spanW').each(function(){
			if($(this).find('input:checked').prop('checked')==true){
				chk++;	
			}
		});
		if(chk>0){
			return true;
		}else{
			return false;
		}
	}
  //获取回显数据
  function getData(){
	  //发生频率
	 var frequencytype="${taskTimecnfig.frequencytype}";
	 //一天内是否循环
	 var isruncycledaily="${taskTimecnfig.isruncycledaily}";
	 //间隔
	 var interva="${taskTimecnfig.interva}";
	 var dayDiv=document.getElementById("dayDiv");
	 var weekDiv=document.getElementById("weekDiv");
	 var monthDiv=document.getElementById("monthDiv");
	 if(frequencytype=="day"){//每天
		  //天模块显示
		  dayDiv.style.display="block";
	      //周模块隐藏
		  weekDiv.style.display="none";
	      //月模块隐藏
		  monthDiv.style.display="none";
	  }else if(frequencytype=="month"){//月
		  //月模块显示
		  monthDiv.style.display="block";
		  //天模块隐藏
		  dayDiv.style.display="none";
	      //周模块隐藏
		  weekDiv.style.display="none";
		  $("#intervalMonth").val(interva);
		  //根据间隔类型禁止其他按钮点击
		  if($('#intervalMonth').val()){//自定义间隔可编辑
			  $('#monthDiv').find('input:not(#intervalMonth)').attr('disabled',true); 
		  }else if($('#lastDay:checked').prop('checked')==true){//最后一天可编辑
			  $('#monthDiv').find('input:not(#lastDay)').attr('disabled',true); 
		  }else{//日期中某一天可编辑
			  $('#intervalMonth').attr('disabled',true); 
			  $('#lastDay').attr('disabled',true); 
		  }
	  }else if(frequencytype=="week"){//周
		//周模块显示
		weekDiv.style.display="block";
		//天模块隐藏
		dayDiv.style.display="none"; 
		//月模块隐藏
		monthDiv.style.display="none";
	  }
	//一天内执行一次
	 var type1=document.getElementById("type1");
	 //一天内多次执行
	 var type2=document.getElementById("type2");
	 //是否循环执行(默认执行一次)
	 if(isruncycledaily=="N"){//一天发生一次
		 type1.style.display="block";
		 type2.style.display="none";
	 }else if(isruncycledaily=="Y"){//周期发生
		 type2.style.display="block";
		 type1.style.display="none";
	 }
	 //回显执行日
	 var executeday= "${taskTimecnfig.executeday}";
	 var arr=executeday.split(",");
	 $("input[name='executeday']").each(function(){
		 var val=$(this).val();
		 for(var i=0,j=arr.length;i<j;i++){
			 if(val==arr[i]){
				 $(this).attr("checked","checked");
			 }
		 }
	 });
	 
  }
  //验证日间隔
  function validExecute(){
	  var executeinterval=$("#executeinterval").val();
	  var exeintervalunit=$("#exeintervalunit").val();
	  if(isNotEmpty(executeinterval)){
		  var reg="^(([1-9])|(1\d)|(2[0-4]))$";
		  var re="^([1-9]|[1-5][0-9]|60)$";
		  if(exeintervalunit=='h'){
			  var r =executeinterval.match(reg);  
			  if(r==null){
				  alert("请输入1到24内的整数");
				  $("#executeinterval").val("");
			  }
		  }else{
			  var a =executeinterval.match(re); 
			  if(a==null){
				  alert("请输入1到60内的整数");
				  $("#executeinterval").val("");
			  }
		  }
	  }
  }
  //天的时间间隔单位改变时验证间隔值
  function validNnit(){
	  var exeintervalunit=$("#exeintervalunit").val();
	  var executeinterval=$("#executeinterval").val();
	  if(exeintervalunit=='h'){
		  if(isNotEmpty(executeinterval)&&executeinterval>24){
			  alert("请从新设置1-24内的间隔值");
			  $("#executeinterval").val("");
		  }
	  }
  }
//非空判断
  function isNotEmpty(s){
  	return ((s != undefined && s != null && s != "") ? true : false);
  } 
  </script>
 </head>
 <body>
 		 <!-- 是否循环执行 -->
 		<input id="isruncycledaily" hidden="true" value="N">
		<table cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td>
						<label class="Validform_label">
							发生频率
						</label>
					</td>
				</tr>
				<tr>
					<td align="left" class="tab_tdW">
						<select class=" pos_l" name="frequencytype" id="frequencytype"  onchange="frequencyChange(this)">
							<option value="day" <c:if test="${taskTimecnfig.frequencytype=='day' }">selected="selected"</c:if>>每天</option>
							<option value="week" <c:if test="${taskTimecnfig.frequencytype=='week' }">selected="selected"</c:if>>周</option>
							<option value="month" <c:if test="${taskTimecnfig.frequencytype=='month' }">selected="selected"</c:if>>月</option>
						</select>
					</td>
					<td style="min-width:150px;">
						<!--按天设置---begin  -->
						<div id="dayDiv">
							<span style="color: gray;">在有效时间段内，每天都根据下面的规则发生</span>
						</div>
						<!--按天设置--end  -->
						<!--按周设置-begin  -->
						<div id="weekDiv" style="display: none;width:200px;">
							<c:forEach items="${weekEnum }" var="week">
								<input type="checkbox"  name="executeday"  value="${week.code }">${week.name }&nbsp;&nbsp;&nbsp;
							</c:forEach>
						</div>
						<!--按周设置---end  -->
						<!-- 按月设置---begin -->
						<div id="monthDiv" style="display: none">
							<label>间隔：</label>
							<input id="intervalMonth" name="interval"   onKeyup="frequencyRange1('intervalMonth')">
							<br>
							<label>最后一天</label>
							<input id="lastDay" type="checkbox"  name="executeday" <c:if test="${taskTimecnfig.executeday=='last' }">checked="checked"</c:if> value="last" onClick="frequencyRange('lastDay')">
							<br>
							<c:forEach items="${monthEnum }" var="month">
								<span class="month_spanW"><input type="checkbox" name="executeday" <c:if test="${taskTimecnfig.executeday==month.value }">checked="checked"</c:if> value="${month.value }" onClick="frequencyRange2()">${month.name }&nbsp;&nbsp;&nbsp;</span>
							</c:forEach>
						</div>
						<!-- 按月设置--end -->
					</td>
				</tr>
		        <tr>
		        	<td>
		        		<label>一天内：</label>
		        	</td>
				</tr>
		        <tr>
		        	<td class="tab_tdW">
		        		<select class="pos_l" id="daytype" onchange="dayTypeChange(this)">
		        			<option value="1" <c:if test="${taskTimecnfig.isruncycledaily=='N'}"> selected="selected"</c:if>>发生一次时间</option>
		        			<option value="2" <c:if test="${taskTimecnfig.isruncycledaily=='Y'}"> selected="selected"</c:if>>周期发生频率</option>
		        		</select>
		        	</td>
		        	<td colspan="2">
		        		<div id="type1">
		        			<label>发生一次时间：</label>
		        			<input id="executetime"  name="executetime"  type="datetime"  class="Wdate" 
		        			<c:choose>
		        				 <c:when test="${taskTimecnfig.executetime!=null }">
       									value='<fmt:formatDate value='${taskTimecnfig.executetime}' type="date" pattern="HH:mm:ss"/>'
    							 </c:when>
    							 <c:otherwise>
       									value='<fmt:formatDate value='${newDate}' type="date" pattern="HH:mm:ss"/>'
    							 </c:otherwise>
		        			</c:choose>
		        			onClick="WdatePicker({dateFmt:'HH:mm:ss'})" >
		        		</div>
		        		<div id="type2" style="display: none">
		        			<label>周期发生频率：</label>
		        			<!-- 每天执行间隔 -->
		        			<input id="executeinterval"  name="executeinterval" value="${taskTimecnfig.executeinterval }" type="text" style="width:40px" onkeyup="validExecute()">
		        			 <select name="exeintervalunit" id="exeintervalunit"  style="width:70px" onchange="validNnit()">
							 	<option value="h" <c:if test="${taskTimecnfig.exeintervalunit=='h' }">selected="selected"</c:if>>小时</option>
							 	<option value="m" <c:if test="${taskTimecnfig.exeintervalunit=='m' }">selected="selected"</c:if>>分</option>
							 	<option value="s" <c:if test="${taskTimecnfig.exeintervalunit=='s' }">selected="selected"</c:if>>秒</option>
							 </select><br>
							 <!--开始时间  -->
							 <label>开始于：</label>
							 <input id="starttime"  name="starttime"  type="text"  class="Wdate" 
							 <c:choose>
		        				 <c:when test="${taskTimecnfig.starttime!=null }">
       									value='<fmt:formatDate value='${taskTimecnfig.starttime}' type="date" pattern="HH:mm:ss"/>'
    							 </c:when>
    							 <c:otherwise>
       									value='<fmt:formatDate value='${newDate}' type="date" pattern="HH:mm:ss"/>'
    							 </c:otherwise>
		        			</c:choose>
							  onClick="WdatePicker({dateFmt:'HH:mm:ss'})">
							 <!--结束时间  -->
							 <br>
							 <label>终止于：</label>
							 <input id="endtime"  name="endtime"  type="text"  class="Wdate" 
							 <c:choose>
		        				 <c:when test="${taskTimecnfig.endtime!=null }">
       									value='<fmt:formatDate value='${taskTimecnfig.endtime}' type="date" pattern="HH:mm:ss"/>'
    							 </c:when>
    							 <c:otherwise>
       									value='<fmt:formatDate value='${endTime}' type="date" pattern="HH:mm:ss"/>'
    							 </c:otherwise>
		        			</c:choose>
							    onClick="WdatePicker({dateFmt:'HH:mm:ss'})">
		        		</div>
		        	</td>
		        </tr>
		        <!--有效期---begin -->
		        <tr>
		        	<td>
		        		<label>有效期</label>
		        	</td>
				</tr>
		        <tr>
		        	<td>
		        		<label>开始时间：</label>
		        		<input  name="effibegintime" class="Wdate"
		        		<c:choose>
		        				 <c:when test="${taskTimecnfig.effibegintime!=null }">
       									value='<fmt:formatDate value='${taskTimecnfig.effibegintime}' type="date" pattern="yyyy-MM-dd HH:mm:ss"/>'
    							 </c:when>
    							 <c:otherwise>
       									value='<fmt:formatDate value='${newDate}' type="date" pattern="yyyy-MM-dd HH:mm:ss"/>'
    							 </c:otherwise>
		        		</c:choose>
		        		  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
		        	</td>
		        	<td>
		        		<label>终止时间：</label>
		        		<input  name="effiendtime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" 
		        				 <c:if test="${taskTimecnfig.effiendtime!=null }">
       									value='<fmt:formatDate value='${taskTimecnfig.effiendtime}' type="date" pattern="yyy-MM-dd HH:mm:ss"/>'
    							 </c:if>
		        		 > 
		        	</td>
		        </tr>
		        <!-- 有效期---end -->
			</table>
 </body>
