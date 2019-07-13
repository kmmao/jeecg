<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>流程记录</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <style>
  	form i,form label{font-size:20px!important;  	}
  	div.tabs-wrap,div.panel-body-noborder{width:100%!important;}
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
  		width:60px;
  		height:60px;
  		border-radius: 50%;
  		background-color:#4473b5;
  		color:#fff;
  		text-align: center;
  		line-height: 60px;
  		font-size:20px;
  		position:relative;
  		right:5px;
  		overflow: hidden;
  		margin-top:25px;
  	}
  	.labelCenter{width:200px;position: relative;top:-25px;}
  	.labelCenter div{margin-left:65px;width:400px;font-size:26px;}
  	.labelCenter span{
  		position: relative;
	    /*right: -31px;*/
	    top:-36px;
	    display:inline-block;
	    width: 60px;
	    height: 60px;
	    text-indent: -999em;
	}
  	.labelRight{width:220px;position:absolute;right:10px;top:40px;}
  </style>
<!-- 加载打印控件-start -->
<script language="javascript" src="webpage/common/print/LodopFuncs.js"></script>
<object  id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
	<embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
</object>
<!-- 加载打印控件-end -->
<script type="text/javascript">
$(function(){
 setTimeout(function(){
	var printUrl='${printUrl}';
	$("#printTable").hide();
	if(printUrl){
	  	$("#printTable").show();
	}
 }, 300);
});	
//打印审批意见
function printComment(){
	printNum =0;
	 //开始循环打印
	var busId =$("input[name='busId']").val();
	var workflowId=$("input[name='workflowId']").val();
	var commentList=$("input[name='commentList']").val();
	var printUrl=$("input[name='printUrl']").val();
	$.ajax({
		url : printUrl,
		type : 'post',
		data : {
			 'busId':busId,
			 'workflowId':workflowId
		},
		cache : false,
		success : function(data) {
			var d = $.parseJSON(data);
			if (d.success) { 
				LODOP=getLodop(); 
				//PrintNoBorderTable('打印',d.obj,d.attributes.template)  
				LODOP.ADD_PRINT_TABLE(0,5,"RightMargin:6mm","BottomMargin:6mm",d.msg);
				LODOP.SET_PRINT_STYLEA(0,"LinkedItem",-1);
				LODOP.ADD_PRINT_HTM(2830,700,300,100,"<font ><span tdata='pageNO'>第##页</span>/<span tdata='pageCount'>共##页</span></font>");
	 			LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	 			LODOP.PREVIEW();
				printNum ++;
			}
		}
	});
}
</script>
 </head>
 <body> 
<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
	<t:tab width="100%" href="actTaskController.do?goProcessRecord&busId=${busId}&workflowId=${workflowId}&submitUser=${submitUser}&printUrl=${printUrl}&isLine=Y" icon="icon-search" title="流程记录" id="processLine"></t:tab>
	<t:tab width="100%" href="actTaskController.do?goProcessRecordImage&busId=${busId}&workflowId=${workflowId}" icon="icon-search" title="流程图" id="processImage"></t:tab>
</t:tabs>
</body>
</html>
  