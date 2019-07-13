<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>附件</title>
</head>
<script language="javascript">
function OpenDoc()
{
	//取得WebOffice对象
	var WebOffice=document.getElementById("WebOffice")
	//通过对象WebOffice的Open方法打开个一个服务器文档
	//此处服务器文档地址为：http://www.officectrl.com/officecs/temp/file1.doc
	var filePath = "${path}"
	//WebOffice.Open(filePath,false,"Word.Document");
	//document.getElementById("WebOffice").Open(filePath,true,"Word.Document","","");
	 try{
	  document.getElementById('WebOffice').Titlebar=false;
	document.getElementById('WebOffice').Menubar=false;
	document.getElementById('WebOffice').Toolbars=false;
	document.getElementById("WebOffice").Open(filePath,true,"Word.Document","","");
	document.getElementById('WebOffice').ProtectDoc(1,2,'123'); 
		}catch(e)
		{
			alert('请先下载安装WebOffice控件！');
			//location.href=strRoot + "WebOffice.rar";
		}
}

setTimeout('OpenDoc()',2000);//等待控件初始化完毕，时间可以根据网络速度设定。




</script>
<body ><!-- <div><input type=button onclick="OpenDoc();" value="打开"></div> -->
<script language="javascript">
if (!!window.ActiveXObject || "ActiveXObject" in window){
document.write('<object classid="clsid:FF09E4FA-BFAA-486E-ACB4-86EB0AE875D5" codebase="WebOffice.ocx#Version=2019,1,7,3" id="WebOffice" width="100%" height="100%" >');
document.write('</object>');}
else
{
document.write('<object id="WebOffice" CLSID="{FF09E4FA-BFAA-486E-ACB4-86EB0AE875D5}" TYPE="application/x-itst-activex"  width=100% height=900></object>');
}
</script>
</body>