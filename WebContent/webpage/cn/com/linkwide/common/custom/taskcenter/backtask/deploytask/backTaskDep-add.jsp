<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>后台任务部署</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <link rel="stylesheet" type="text/css" href="plug-in/easyui/portal/portal.css">
  <link href="plug-in/ace/css/common.css" rel="stylesheet" >
   <style type="text/css">  
   			html{
   				width: 100%;
    			height: 100%;
   			}
            #content {  
                width: 100%;
                height: 100%; 
            }  
              
            #tab_bar {  
                width:100%;  
                height:10%;  
                float: left;  
            }  
            #tab_bar ul {  
                padding: 0px;  
                margin: 0px;  
                text-align: center;
                border-bottom: 1px solid #0E2D5F;
                display: -webkit-box;
			    display: -webkit-flex;
			    display: flex;
			}
              
            #tab_bar li {  
                list-style-type: none;  
                float: left;   
            }  
              
            .tab_css {  
                width: 100%;  
                min-height: 360px;  
                display: none;  
                float: left;  
            }  
			
			.tabs-inner {
				display: inline-block;
			    text-decoration: none;
			    margin: 0 5px;
			    padding: 0 10px;
			    height: 25px;
			    line-height: 25px;
			    text-align: center;
			    white-space: nowrap;
			    border-width: 1px;
			    border-style: solid;
			    -moz-border-radius: 5px 5px 0 0;
			    -webkit-border-radius: 5px 5px 0 0;
			    border-radius: 5px 5px 0 0;
			
			    background-color: #ffffff;
			    border-bottom: 1px solid #ffffff;
			    color: #0E2D5F;
			    background: -webkit-linear-gradient(top,#EFF5FF 0,#ffffff 100%);
			    background: -moz-linear-gradient(top,#EFF5FF 0,#ffffff 100%);
			    background: -o-linear-gradient(top,#EFF5FF 0,#ffffff 100%);
			    background: linear-gradient(to bottom,#EFF5FF 0,#ffffff 100%);
			    background-repeat: repeat-x;
			    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#EFF5FF,endColorstr=#ffffff,GradientType=0);
			}
			#formtable_t{
				padding:10px 20%;
				background:#fff;
			}
			#formtable_t tr{
				display:block;
				background:#fff;
			}
			 /* 放大镜样式 */
		  	.icon-searchN{
		  		background:url('plug-in/easyui/themes/icons/search.png') no-repeat right 10px center;
		  	}
        </style>  
  <script type="text/javascript">
  var corn="* * * * * ? *";
  var second="*", minute="*", hour="*", day="*", month="*", week="?", year="*";
  //编写自定义JS代码
   var myclick = function(v) {  
                var llis = document.getElementsByTagName("li"); 
                var childA = document.getElementsByTagName("a");
                for(var i = 0; i < llis.length; i++) {  
                    var lli = llis[i];  
                    var chA = childA[i];
                    if(lli == document.getElementById("tab" + v)) {  
                        //lli.style.backgroundColor = "gray";  
                        chA.style.color = "#0f09ea"; 
                        chA.style.fontWeight="800";
                    } else {  
                        chA.style.color = "gray";  
                        //lli.style.backgroundColor = "#e0c70a";  
                    }  
                }  
  
                var divs = document.getElementsByClassName("tab_css");  
                for(var i = 0; i < divs.length; i++) {  
  
                    var divv = divs[i];  
  
                    if(divv == document.getElementById("tab" + v + "_content")) {  
                        divv.style.display = "block";  
                    } else {  
                        divv.style.display = "none";  
                    }  
                }  
            }  
  //根据执行策略联动
  function changWay(){
	  var wayVal=document.getElementById("executWay").value;
	  var haTd=document.getElementById("happenTimeTd");
	  if(wayVal=="定时"){
		  haTd.style.display="block";
	  }else{
		  haTd.style.display="none";
	  }
  }
  //根据任务类型联动
  function typeChange(){
	  var taskType=$("#taskType").find("option:selected").val();
	  var text=$("#taskType").find("option:selected").text();
	  $('[name="taskType"]').val(text);
	  $("#plugName").html(taskType);
	  $('[name="plugName"]').val(taskType);
  }
  //设置定时器的时间
  function loadTime(){
	  var url="taskTimeconfigController.do?loadTimeSelect";
	    //是否循环执行 
	    var isruncycledaily=$("#isruncycledaily").val();
	    if(isNotEmpty(isruncycledaily)){
	    	url+="&isruncycledaily="+isruncycledaily;
	    }
		//发生频率
		var frequencytype=$("#frequencytype").val();
		if(isNotEmpty(frequencytype)){
	    	url+="&frequencytype="+frequencytype;
	    }
		//周或月的间隔
		var interva=$("#interval").val();
		if(isNotEmpty(interva)){
			url+="&interva="+interva;
		}
		//执行日  
		var executeday=$("#executeday").val();
		if(isNotEmpty(executeday)){
			url+="&executeday="+executeday;
		}
		//每天执行时间 
		var executetime=$("#executetime").val();
		if(isNotEmpty(executetime)){
			url+="&executetime="+executetime;
		}
		//每天执行间隔
		var executeinterval=$("#executeinterval").val();
		if(isNotEmpty(executeinterval)){
			url+="&executeinterval="+executeinterval;
		}
		//每天执行单位
		var exeintervalunit=$("#exeintervalunit").val();
		if(isNotEmpty(exeintervalunit)){
			url+="&exeintervalunit="+exeintervalunit;
		}
		//每天的开始时间 
		var starttime=$("#starttime").val();
		if(isNotEmpty(starttime)){
			url+="&starttime="+starttime;
		}
		//每天的结束时间 
		var endtime=$("#endtime").val();
		if(isNotEmpty(endtime)){
			url+="&endtime="+endtime;
		}
		//有效期开始时间  
		var effibegintime=$("#effibegintime").val();
		if(isNotEmpty(effibegintime)){
			url+="&effibegintime="+effibegintime;
		}
		//终止时间 
		var effiendtime=$("#effiendtime").val();
		if(isNotEmpty(effibegintime)){
			url+="&effiendtime="+effiendtime;
		}
	  $.dialog({
		    content: "url:"+url+"", //后台数据加载路径
			zIndex: 2100, //弹出层级
			title: "编辑发生时间 ", //标题
			lock: true, //弹框是否可拖拽
			width: "500px", //弹框宽度
			height: "350px", //弹框高度
			opacity: 0.4, //透明度
			button: [//弹框按钮
	   {name: "<t:mutiLang langKey='common.confirm'/>",//按钮名称
				callback: callbackTimeSelect, //按钮回调事件
				focus: true},
	   {name: "<t:mutiLang langKey='common.cancel'/>",//按钮名称
					callback: function (){}}//按钮回调事件
 ]}).zindex();
  }
  
  function callbackTimeSelect(){
	 var iframe = this.iframe.contentWindow;//关闭窗口
	 //发生频率
	 var frequencytype=iframe.$("#frequencytype").val();
	 $("#frequencytype").val(frequencytype);
	 //每天是否循环执行
	 var isruncycledaily=iframe.$("#isruncycledaily").val();
	 $("#isruncycledaily").val(isruncycledaily);
	//周或月的间隔
	var interval=iframe.$("#intervalMonth").val();
	 $("#interval").val(interval);
	 //执行日
	 var executeday =[];
	iframe.$('input[name="executeday"]:checked').each(function(){
		executeday.push($(this).val());
	}); 
	 $("#executeday").val(executeday);
	 //获取一天内发生次数的类型
	 var daytype=iframe.$("#daytype").val();
	if(daytype=="1"){//一天发生一次时
	 //每天一次时间
	 var executetime=iframe.$("input[name='executetime']").val();
	 $("#executetime").val(executetime);
	}else if(daytype=="2"){//一天内多次发生时
	 //每天执行间隔
	 var executeinterval=iframe.$("input[name='executeinterval']").val();
	 $("#executeinterval").val(executeinterval);
	 //每天执行间隔单位
	 var exeintervalunit=iframe.$("#exeintervalunit").find("option:selected").val();
	 $("#exeintervalunit").val(exeintervalunit);
	 //开始时间
	 var starttime=iframe.$("input[name='starttime']").val();
	 $("#starttime").val(starttime);
	 //结束时间
	 var endtime=iframe.$("input[name='endtime']").val();
	 $("#endtime").val(endtime);
	}
   //有效期开始时间
   var effibegintime=iframe.$("input[name='effibegintime']").val();
   $("#effibegintime").val(effibegintime);
   //终止时间
   var effiendtime=iframe.$("input[name='effiendtime']").val();
   $("#effiendtime").val(effiendtime);
	//生成定时任务corn表达式     
	var interval=$("#interval").val();
	var corn=makeCorn(isruncycledaily,frequencytype,interval,executeday,executetime,executeinterval,exeintervalunit,starttime,endtime,effibegintime,effiendtime);
	$("#happenTime").val(corn);
  }
  //根据时间设置生成corn表达式
 function makeCorn(isruncycledaily,frequencytype,interval,executeday,executetime,executeinterval,exeintervalunit,starttime,endtime){
	if(frequencytype=="day"){//按天设定
		setDayTime(isruncycledaily,executetime,starttime,endtime);
	}else if(frequencytype=="month"){//按月设定
		if(isNotEmpty(interval)){//间隔是不为空
			day="1/"+interval;
		}else if(executeday=="last"){//最后一天不为空
			day="L";
		}else if(isNotEmpty(executeday)&&executeday!="last"){//执行日不为空
			day=executeday;
		}
		setDayTime(isruncycledaily,executetime,starttime,endtime);
	}else if(frequencytype=="week"){//按周设定
		var executedayArr =[];
		if(isNotEmpty(executeday)){
			var arr=executeday.split(",");
			for (var a = 0,b=arr.length; a < b; a++) {
				if(arr[a]=='monday'){
					executedayArr.push('1');
				}else if(arr[a]=='tuesday'){
					executedayArr.push('2');
				}else if(arr[a]=='wednesday'){
					executedayArr.push('3');
				}else if(arr[a]=='thursday'){
					executedayArr.push('4');
				}else if(arr[a]=='fridays'){
					executedayArr.push('5');
				}else if(arr[a]=='saturday'){
					executedayArr.push('6');
				}else if(arr[a]=='sunday'){
					executedayArr.push('7');
				}
			}
			week=executedayArr;
			day="?";
		}
		setDayTime(isruncycledaily,executetime,starttime,endtime);
	}
	//判断每天执行间隔的单位
	if(exeintervalunit=='h'){//按小时循环
		hour=hour+"/"+executeinterval;
	}else if(exeintervalunit=='m'){//按分钟
		minute=minute+"/"+executeinterval;
	}else if(exeintervalunit=='s'){//按秒
		second+=second+"/"+executeinterval;
	}
	corn=second+" "+minute+" "+hour+" "+day+" "+month+" "+week+" "+year;
	return corn;
 }
  //每天执行情况的时间设置
  function setDayTime(isruncycledaily,executetime,starttime,endtime){
	  if(isruncycledaily=="N"){//执行一次
			var times=executetime.split(":");
			//获取时间的小时数
			var h=times[0];
			hour=""+h+"";
		    //获取当前时间的分钟数
		    var m=times[1];
		    minute=""+m+"";
		    //获取当前时间的秒数
		    var s=times[2];
		    second=""+s+"";
		}else if(isruncycledaily=="Y"){//循环执行
			var starts=starttime.split(":");
		    var ends=endtime.split(":");
			//开始的小时数
			var startH=starts[0];
		    //结束的小时数
		    var endH=ends[0];
		    hour=startH+"-"+endH;
		    //开始的分钟数
		    var startM=starts[1];
		    //结束的分钟数
		    var endM=ends[1];
		    minute=startM+"-"+endM;
		    //开始的秒数
		    var startS=starts[2];
		    //结束的秒数
		    var endS=ends[2];
		    second=startS+"-"+endS;
		}
  }
//非空判断
  function isEmpty(s){
  	return ((s == undefined && s == null && s == "") ? true : false);
  } 
  //空判断
  function isNotEmpty(s){
	  	return ((s != undefined && s != null && s != "") ? true : false);
	  } 

  </script>
 </head>
 <body>
 	<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="backTaskDepController.do?doAdd" tiptype="1" >
    	<div style="display:none;">
	 	    <!-- 是否循环执行 -->
	 		<input id="isruncycledaily" name="isruncycledaily">
	 		<!-- 发生频率 -->
	 		<input  id="frequencytype" name="frequencytype">
	 		<!-- 周或月的间隔 -->
	 		<input  id="interval" name="interval">
	 		<!--执行日  -->
	 		<input id="executeday" name="executeday">
	 		<!-- 每天执行时间 -->
	 		<input id="executetime" name="executetime">
	 		<!--每天执行间隔 -->
	 		<input id="executeinterval" name="executeinterval">
	 		<!-- 每天执行单位 -->
	 		<input id="exeintervalunit" name="exeintervalunit">
	 		<!-- 每天的开始时间 -->
	 		<input id="starttime" name="starttime">
	 		<!-- 每天的结束时间 -->
	 		<input id="endtime" name="endtime">
	 		<!--有效期开始时间  -->
	 		<input id="effibegintime" name="effibegintime">
	 		<!-- 终止时间 -->
	 		<input id="effiendtime" name="effiendtime">
	 	</div>
 	   
  	 <div id="content">  
            <div id="tab_bar">  
                <ul>  
                    <li id="tab1"  onclick="myclick(1)" >  
                        <a href="javascript:void(0)" class="tabs-inner" style="color:#0f09ea;font-weight: 800; ">
			                <span class="tabs-title">常规设置</span>
			                <span class="tabs-icon"></span>
		                </a> 
                    </li>  
                    <li id="tab2" onclick="myclick(2)">  
                        <a href="javascript:void(0)" class="tabs-inner">
			                <span class="tabs-title">定时配置</span>
			                <span class="tabs-icon"></span>
		                </a>   
                    </li>  
                </ul>  
            </div>  
            <div class="tab_css" id="tab1_content" style="display: block">  
                <table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">  
                	<tr>
					<td align="right">
						<label class="Validform_label">
							<i class="interval">*</i>任务名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="taskName" name="taskName" type="text" style="width: 75%" class="inputxt" dataType="*" nullmsg="请填写任务名称">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">任务名称</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							<i class="interval">*</i>任务状态 :
						</label>
					</td>
					<td class="value">
					     	 <select id="taskState" name="taskState" style="width:77%" dataType="*" nullmsg="请填写任务状态">
					     	 	<option value="">请选择</option>
					     	 	<option value="活动">活动</option>
					     	 	<option value="休眠">休眠</option>
					     	 </select>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">任务状态 </label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							<i class="interval">*</i>任务类型:
						</label>
					</td>
					<td class="value">
					     	 <select id="taskType"  style="width: 77%;" dataType="*" nullmsg="请填写任务类型" onchange="typeChange()">
					     	 	<option value="">请选择</option>
					     	 	<c:forEach items="${taskTypeReList }" var="task">
					     	 		<option value="${task.businesPlugIn }">${task.taskName }</option>
					     	 	</c:forEach>
					     	 </select>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">任务类型</label>
							<input name="taskType" hidden="true">
					</td>
					<td align="right">
						<label class="Validform_label">
							业务插件:
						</label>
					</td>
					<td class="value">
							 <span id="plugName" style="color: gray;"></span>
							 <input name="plugName" hidden="true">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务插件</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							描述:
						</label>
					</td>
					<td class="value" colspan="3">
					     	 <textarea rows="10" cols="30" id="taskDesc" name="taskDesc" style="width: 75%">
					     	 
					     	 </textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">描述</label>
					</td>
				</tr>
				<tr >
					<td colspan="4" >
						<span>实参设置</span>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<table style="width: 100%;" border="1" cellpading="0" cellspacing="0">
							<tr>
								<td align="center">名称</td>
								<td align="center">描述</td>
								<td align="center">操作符</td>
								<td align="center">取值</td>
							</tr>
							<tr>
								<td align="center">psnc</td>
								<td align="center">人员类别</td>
								<td align="center">=</td>
								<td align="center">1</td>
							</tr>
							
						</table>
					</td>
				</tr>
                </table>
            </div>  
            <div class="tab_css" id="tab2_content">  
                 <table id="formtable_t" style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">  
	                 <tr>
						<td align="right" width="100px">
							<label class="Validform_label">
								<i class="interval">*</i>执行策略:
							</label>
						</td>
						<td class="value">
							 <select id="executWay" name="executWay" datatype="*" onchange="changWay()">
							 	<option value="">请选择</option>
							 	<option value="立即执行">立即执行</option>
							 	<option value="定时">定时</option>
							 </select>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">执行策略</label>
						</td>
					</tr>
					<tr id="happenTimeTd" style="display: none;">
							<td align="right" width="100px">
								<label class="Validform_label">
									发生时间:
								</label>
							</td>
							<td class="value">
							     	 <input id="happenTime" name="happenTime" type="text" class="inputxt icon-searchN" readonly="readonly"  onclick="loadTime()">
									<span class="Validform_checktip"></span>
									<label class="Validform_label" style="display: none;">发生时间</label>
						</td>
					</tr>
                 </table>
            </div>  
        </div>  
     </t:formvalid>
 </body>
  <script src = "webpage/cn/com/linkwide/cost/custom/taskcenter/backtask/deploytask/backTaskDep.js"></script>		
