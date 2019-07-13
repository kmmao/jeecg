var myDate = new Date();
  var type="",obj1="",obj2="", year="",month="",day="",endnum="01",typeDesc="",obj1Desc="",obj2Desc="",
  	  yearDesc="",monthDesc="",dayDesc="",endnumDesc="第1号",typeCheck=false,obj1Check=false,obj2Check=false;
  $(function(){
	  //单据类型
	  type=$("#billType").val();
	  typeDesc=type;
	  /*$("#billTypeBox").click(function(){
		var billTypeBox= $("#billTypeBox").is(":checked");
		if(billTypeBox){
			typeCheck=true;
			$("#billType").removeAttr("readonly");
		}else{
			typeCheck=false;
			$("#billType").attr("readonly","readonly");
			$("#billType").val("");
			 type="";
			 typeDesc="";
		}
		showDescAndApli();
	  });*/
/*	  $("#billType").blur(function(){
		  var billType=$("#billType").val();
		  if(isNotEmpty(billType)){
			  type=billType;
			  typeDesc=billType;
			  showDescAndApli();
		  }else{
			  type="";
			  typeDesc="";
			  showDescAndApli();
		  }
	  });*/
	  //对象1
	  $("#billObj1Box").click(function(){
		  var billObj1Box=$("#billObj1Box").is(":checked");
		  if(billObj1Box){
			  obj1Check=true;
			  $("#billObj1").removeAttr("readonly");
		  }else{
			  obj1Check=false;
			  $("#billObj1").attr("readonly","readonly");
			  $("#billObj1").val("");
			  obj1="";
			  obj1Desc="";
		  }
		  showDescAndApli();
	  });
	  $("#billObj1").blur(function(){
		  var billObj1=$("#billObj1").val();
		  if(isNotEmpty(billObj1)){
			  obj1=billObj1;
			  obj1Desc=billObj1;
		  }else{
			  obj1="";
			  obj1Desc="";
		  }
		  showDescAndApli();
	  });
	  
	  //对象2
	  $("#billObj2Box").click(function(){
		  var billObj2Box=$("#billObj2Box").is(":checked");
		  if(billObj2Box){
			  obj2Check=true;
			  $("#billObj2").removeAttr("readonly");
		  }else{
			  obj2Check=false;
			  $("#billObj2").attr("readonly","readonly");
			  $("#billObj2").val("");
			  obj2="";
			  obj2Desc="";
		  }
		  showDescAndApli();
	  });
	  $("#billObj2").blur(function(){
		  var billObj2=$("#billObj2").val();
		  if(isNotEmpty(billObj2)){
			  obj2=billObj2;
			  obj2Desc=billObj2;
		  }else{
			  obj2="";
			  obj2Desc="";
		  }
		  showDescAndApli();
	  });
	  //年份
	  $("#codeYear").click(function(){
		  var codeYear=$("#codeYear").is(":checked");
		  var codeMoth=$("#codeMoth").is(":checked");
		  //月被选中时不能取消
		  if(codeMoth){
			  return false;
		  }
		  if(codeYear){
			  //获取当前年
			  var fullYear=myDate.getFullYear().toString(); 
			  year=fullYear.substr(2,2);
			  yearDesc=year+"年";
		  }else{
			  year=""; 
			  yearDesc="";
		  }
		  showDescAndApli();
	  });
	  //月份
	  $("#codeMoth").click(function(){
		  var codeMoth=$("#codeMoth").is(":checked");
		  var codeDay=$("#codeDay").is(":checked");
		  //天被选中时不能取消
		  if(codeDay){
			  return false;
		  }
		  if(codeMoth){
			  //判断年是否选中
			  var codeYear=$("#codeYear").is(":checked");
			  if(!codeYear){
				  $("#codeYear").attr("checked","checcked");
				  //获取当前年
				   var fullYear=myDate.getFullYear().toString(); 
			  	   year=fullYear.substr(2,2);
				   yearDesc=year+"年";
			  }
			  //获取当前月
			   var mon=myDate.getMonth()-1+2;
			  month=mon<10?"0"+mon:mon;
			  monthDesc=month+"月";
		  }else{
			  month="";
			  monthDesc="";
		  }
		  showDescAndApli();
	  });
	  //日期
	  $("#codeDay").click(function(){
		  var codeDay=$("#codeDay").is(":checked");
		  if(codeDay){
			  //判断月是否选中
			  var codeMoth=$("#codeMoth").is(":checked");
			  if(!codeMoth){
				  //选中年和月
				  $("#codeMoth").attr("checked","checked");
				  $("#codeYear").attr("checked","checcked");
				  //获取当前年
				   var fullYear=myDate.getFullYear().toString(); 
			  	   year=fullYear.substr(2,2);
				  yearDesc=year+"年";
				  //获取当前月
				  var mon=myDate.getMonth()-1+2;
				  month=mon<10?"0"+mon:mon;
				  monthDesc=month+"月";
			  }
			  //获取当前日
			 myDay= myDate.getDate();
			 day=myDay<10?"0"+myDay:myDay;
			 dayDesc=day+"日";
		  }else{
			  day="";
			  dayDesc="";
		  }
		  showDescAndApli();
	  });
	  //流水单号后几位
	  $("#endNum").change(function(){
		  var endNumVal=$("#endNum").val();
		  if(endNumVal=="2"){
			  endnum="01";
		  }else if(endNumVal=="3"){
			  endnum="001";
		  }else if(endNumVal=="4"){
			  endnum="0001";
		  }else if(endNumVal=="5"){
			  endnum="00001";
		  }else if(endNumVal=="6"){
			  endnum="000001";
		  }else if(endNumVal=="7"){
			  endnum="0000001";
		  }else if(endNumVal=="8"){
			  endnum="00000001";
		  }else if(endNumVal=="9"){
			  endnum="000000001";
		  }
		  showDescAndApli();
	  });
	  showDescAndApli();
  });
  //给应用效果和表示赋值
  function showDescAndApli(){
	  //应用效果
	  var result=type+obj1+obj2+year+month+day+endnum;
	  //表示
	  var desc=typeDesc+"/";
	  /*  if(typeCheck){
		  if(isNotEmpty(typeDesc)){
			  desc=desc+typeDesc+"/";
		  }else{
			  desc+="null/";
		  }
	  }else{
		  desc+="";
	  }*/
	  if(obj1Check){
		  if(isNotEmpty(obj1Desc)){
			  desc=desc+obj1Desc+"/";
		  }else{
			  desc+="null/";
		  }
	  }else{
		  desc+="";
	  }
	  if(obj2Check){
		  if(isNotEmpty(obj2Desc)){
			  desc=desc+obj2Desc+"/";
		  }else{
			  desc+="null/";
		  }
	  }else{
		  desc+="";
	  }
	  if(isNotEmpty(yearDesc)){
		  desc=desc+yearDesc+"/";
	  }else{
		  desc+="";
	  }
	  if(isNotEmpty(monthDesc)){
		  desc=desc+monthDesc+"/";
	  }else{
		  desc+="";
	  }
	  if(isNotEmpty(dayDesc)){
		  desc=desc+dayDesc+"/";
	  }else{
		  desc+="";
	  }
	  if(isNotEmpty(endnumDesc)){
		  desc=desc+endnumDesc;
	  }else{
		  desc+="";
	  }
	  $("input[name='codeYear']").val(year);
	  $("input[name='codeMoth']").val(month);
	  $("input[name='codeDay']").val(day);
	  $("#apliResult").val(result);
	  $("#billDesc").val(desc);
  }
  //验证对象1和对象2
  function validObj(){
	  if(obj1Check){
		  var billObj1=$("#billObj1").val();
		  if(!isNotEmpty(billObj1)){
			  alert("对象1不能为空！");
			  return false;
		  }
	  }
	  if(obj2Check){
		  var billObj2=$("#billObj2").val();
		  if(!isNotEmpty(billObj2)){
			  alert("对象2不能为空！");
			  return false;
		  }
	  }
	  //流水号归零标识
	  var zeroMark=$("#zeroMark").val();
	  //年份
	  var codeYear=$("input[name='codeYear']").val();
	  //月份
	  var codeMoth=$("input[name='codeMoth']").val();
	  //日期
	  var codeDay=$("input[name='codeDay']").val();

	  if(zeroMark=="year"){
		  if(!isNotEmpty(codeYear)){
			  alert("按年归零时，年份必选");
			  return false;
		  }
	  }else if(zeroMark=="month"){
		    if(!isNotEmpty(codeMoth)){
			  alert("按月归零时，月份必选");
			  return false;
		  }
	  }else if(zeroMark=="day"){
		    if(!isNotEmpty(codeDay)){
			  alert("按日归零时，日期必选");
			  return false;
		  }
	  }
  }
  //非空判断
  function isNotEmpty(s){
	  return ((s!=undefined && s!=null&&s!=""?true:false));
  }