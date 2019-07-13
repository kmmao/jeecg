<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>流程记录</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <style>
  	.step{
  		border-left: 1px dashed #ccc!important;
    	margin-left: 50px;
  	}
  	#steps form fieldset{padding-bottom: 0!important;}
  	#steps form div.form{position:relative;
  		background-color: rgba(0,0,0,0)!important;
	    border: none!important;
	    width: 95%;
	    padding: 0px!important;
	    margin: 0 px!important;
	    margin-left: 5px;
	    margin-bottom: 15px!important;
	    -moz-border-radius: 5px;
	    -webkit-border-radius: 5px;
	    border-radius: 5px;
	    -moz-box-shadow: 0px 0px 0px #aaa!important;
	    -webkit-box-shadow: 0px 0px 0px #aaa!important;
	    box-shadow: 0px 0px 0px #aaa!important;
	    left:-37px;
	    margin-top:-5px!important;
	}
  	.labelLeft{width:30px;padding:5px 0;display:inline-block;margin-right:30px;}
  	.labelLeft div{
  		width:30px;
  		height:30px;
  		border-radius: 50%;
  		background-color:#4473b5;
  		color:#fff;
  		text-align: center;
  		line-height: 30px;
  		font-size:12px;
  		position:relative;
  		right:-15px;
  		overflow: hidden;
  	}
  	.labelCenter{width:200px;position: relative;}
  	.labelCenter div{margin-left:65px;width:400px;font-size:16px;}
  	.labelCenter span{
  		position: relative;
	    /*right: -31px;*/
	    top:-20px;
	    display:inline-block;
	    width: 35px;
	    height: 35px;
	    text-indent: -999em;
	}
  	.labelRight{width:150px;position:absolute;right:10px;top:10px;}
  </style>
 </head>
 <body> 
<a href="javascript:void(0);" id="printTable" name="printTable" onclick="printComment()" class="easyui-linkbutton l-btn l-btn-plain" plain="true" style="background: #eaf2ff;color: #000000;margin:10px;padding:10px;">
	<span class="l-btn-text icon-print l-btn-icon-left" style="font-size:20px;">打印</span>
 <!--<input type="button" id="printTable" name="printTable" value="打印" onclick="printComment()"  class="l-btn-text icon-print l-btn-icon-left" >-->
</a> 	
<t:formvalid formid="formobj" dialog="false" layout="div" callback="test" action="">
<input type="text" id="busId" name='busId' value="${busId}" style="display:none;" >
<input type="text" id="printUrl" name='printUrl' value="${printUrl}" style="display:none;" >
<input type="text" id="workflowId" name="workflowId"  value="${workflowId}" style="display:none;" >
<input type="text" id="commentList" name="commentList"  value="${commentList}" style="display:none;" >
	 <fieldset class="step">
	 	<c:forEach items="${processList}" varStatus="" var="item">
	 			<c:if test="${item.auditStatus=='提交'}">
					<div class="form">
						<label class="labelLeft"> <div>${item.shortName }</div>  </label> 
						<label class="labelCenter">${item.realName }<i style="color:green">提交</i></label> 
						<label class="labelRight"> ${item.time }</label> 
					</div>
				</c:if>
				<c:if test="${item.auditStatus=='退回'}">
					<div class="form">
						<label class="labelLeft"> <div>${item.shortName }</div>  </label> 
						<label class="labelCenter">${item.realName } <span class="icon-sendBack">审核退回</span><div>${item.message }</div></label> 
						<label class="labelRight"> ${item.time }</label> 
					</div>
				</c:if>
				<c:if test="${item.auditStatus=='通过'}">
					<div class="form">
						<label class="labelLeft"> <div>${item.shortName }</div>  </label> 
						<label class="labelCenter">${item.realName }<span class="icon-auditPass">审核通过</span><div>${item.message }</div></label> 
						<label class="labelRight"> ${item.time }</label> 
					</div>
				</c:if>
				<c:if test="${item.auditStatus=='审核中' }">
					<div class="form">
						<label class="labelLeft"> <div>${item.shortName}</div>  </label> 
						<label class="labelCenter">${item.realName }<span class="icon-underReview">审核中</span><div>${item.message }</div></label> 
						<label class="labelRight"> ${item.time }</label> 
					</div>
				</c:if>				
		</c:forEach>
		<!-- <div class="form">
			<label class="labelLeft"> <div>非空</div> </label> 
			<label class="labelCenter"> 非空验证：<span class="icon-underReview">审核中</span> <div>测试测试测试测试测试测试测试测试</div> </label> 
			<label class="labelRight"> 2018-11-23 00:00:00</label> 
		</div>
		
		<div class="form">
			<label class="labelLeft"> <div>非空</div>  </label> 
			<label class="labelCenter"> 非空验证：<span class="icon-auditPass">审核通过</span> </label> 
			<label class="labelRight"> 2018-11-23 00:00:00 </label> 
		</div>
		<div class="form">
			<label class="labelLeft"> <div>非空</div>  </label> 
			<label class="labelCenter"> 非空验证： <span class="icon-sendBack">审核退回</span></label> 
			<label class="labelRight"> 2018-11-23 00:00:00 </label> 
		</div> -->
	</fieldset>
</t:formvalid>
</body>
</html>
  