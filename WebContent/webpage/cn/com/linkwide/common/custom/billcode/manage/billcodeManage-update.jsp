<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>单据号管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <style type="text/css">
  /* 放大镜样式 */
  .icon-searchN{
  		background:url('plug-in/easyui/themes/icons/search.png') no-repeat right 10px center;
  	}
  </style>
  <script type="text/javascript">
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="billcodeManageController.do?doUpdate" tiptype="1" beforeSubmit="validObj()">
  		<input id="id" name="id" type="hidden" value="${billcodeManagePage.id }">
		<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
				<!-- 	<td align="right">
						<label class="Validform_label">
							单据号范围:
						</label>
					</td>
					<td class="value" >
					     	全局&nbsp;&nbsp; <input id="billScope " name="billScope " type="checkbox" value="all">
							<label class="Validform_label" style="display: none;">单据号范围</label>
					</td> -->
					<td align="right">
						<label class="Validform_label">
							单据号:
						</label>
					</td>
					<td class="value" colspan="3">
					     	 <input id="billCode" name="billCode" type="text" readonly="readonly" style="width: 70%" value="${billcodeManagePage.billCode }" class="inputxt" >
							<label class="Validform_label" style="display: none;">单据号</label> 
					</td>	
				</tr>	
			<%-- 	<tr>		
					<td colspan="4"  class="value">
						<label class="Validform_label">生成单据号时检查唯一性:</label>
						<input type="checkbox" id="isUniq" name="isUniq" value="Y" <c:if test="${billcodeManagePage.isUniq=='Y' }">checked="checked"</c:if> >
						<label class="Validform_label" >删除单据时保留占用:</label>
						<input id="isKeep" name="isKeep" type="checkbox" value="Y" <c:if test="${billcodeManagePage.isKeep=='Y' }">checked="checked"</c:if> >
						<label class="Validform_label">自动进行补号:</label>
						<input id="autoComplete" name="autoComplete" type="checkbox" value="Y" <c:if test="${billcodeManagePage.autoComplete=='Y' }">checked="checked"</c:if> >
					</td>
				</tr> --%>
				<tr>	
					<td align="right">
						<label class="Validform_label">
							单据类型:
						</label>
					</td>
					<td class="value" colspan="3">
					     	 <input id="billType" name="billType" type="text"  value="${billcodeManagePage.billType }"   class="inputxt" readonly="readonly">
							<label class="Validform_label" style="display: none;">单据类型</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							对象1:
						</label>
					</td>
					<td class="value">
							<input type="checkbox" id="billObj1Box">&nbsp;&nbsp;&nbsp;
					     	 <input id="billObj1" name="billObj1" type="text" style="width: 70%" value="${billcodeManagePage.billObj1 }" class="inputxt" readonly="readonly">
							<label class="Validform_label" style="display: none;">对象1</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							对象2:
						</label>
					</td>
					<td class="value">
							<input type="checkbox" id="billObj2Box">&nbsp;&nbsp;&nbsp;
					     	 <input id="billObj2" name="billObj2" type="text" value="${billcodeManagePage.billObj2 }" style="width: 70%" class="inputxt" >
							<label class="Validform_label" style="display: none;">对象2</label>
					</td>
				</tr>
				<tr>	
					<td class="value" colspan="4">
						<label class="Validform_label">年份:</label>
						<input type="checkbox" id="codeYear"  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input name="codeYear" type="hidden">
						<label class="Validform_label">月份:</label>
						<input type="checkbox" id="codeMoth"  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input name="codeMoth" type="hidden">
						<label class="Validform_label">日期:</label>
						<input type="checkbox" id="codeDay"  >
						<input name="codeDay" type="hidden">
					</td>
				</tr>
				<tr>	
					<td align="right">
						<label class="Validform_label">
							流水号后几位位数:
						</label>
					</td>
					<td class="value">
					     	 <select id="endNum" name="endNum" style="width:70%">
					     	 	<option value="2" <c:if test="${billcodeManagePage.endNum=='2' }">selected="selected"</c:if> >2</option>
					     	 	<option value="3" <c:if test="${billcodeManagePage.endNum=='3' }">selected="selected"</c:if> >3</option>
					     	 	<option value="4" <c:if test="${billcodeManagePage.endNum=='4' }">selected="selected"</c:if> >4</option>
					     	 	<option value="5" <c:if test="${billcodeManagePage.endNum=='5' }">selected="selected"</c:if> >5</option>
					     	 	<option value="6" <c:if test="${billcodeManagePage.endNum=='6' }">selected="selected"</c:if> >6</option>
					     	 	<option value="7" <c:if test="${billcodeManagePage.endNum=='7' }">selected="selected"</c:if> >7</option>
					     	 	<option value="8" <c:if test="${billcodeManagePage.endNum=='8' }">selected="selected"</c:if> >8</option>
					     	 	<option value="9" <c:if test="${billcodeManagePage.endNum=='9' }">selected="selected"</c:if> >9</option>
					     	 </select>
							<label class="Validform_label" style="display: none;">流水号后几位位数</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							流水号归零标志:
						</label>
					</td>
					<td class="value">
					     	 <select id="zeroMark" name="zeroMark" style="width: 70%">
					     	 	<option value="none" <c:if test="${billcodeManagePage.zeroMark=='none' }">selected="selected"</c:if> >不归零</option>
					     	 	<option value="year" <c:if test="${billcodeManagePage.zeroMark=='year' }">selected="selected"</c:if> >年</option>
					     	 	<option value="month" <c:if test="${billcodeManagePage.zeroMark=='month' }">selected="selected"</c:if> >月</option>
					     	 	<option value="day" <c:if test="${billcodeManagePage.zeroMark=='day' }">selected="selected"</c:if> >日</option>
					     	 </select>
							<label class="Validform_label" style="display: none;">流水号归零标志</label>
					</td>
				</tr>	
				<tr>
					<td align="right">
						<label class="Validform_label">
							应用效果:
						</label>
					</td>
					<td class="value">
					     	 <input id="apliResult" name="apliResult" type="text" readonly="readonly" value="${billcodeManagePage.apliResult }" style="width: 70%" class="inputxt" >
							<label class="Validform_label" style="display: none;">应用效果</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							表示:
						</label>
					</td>
					<td class="value">
					     	 <input id="billDesc" name="billDesc" type="text" readonly="readonly" value="${billcodeManagePage.billDesc }" style="width: 70%" class="inputxt" >
							<label class="Validform_label" style="display: none;">表示</label>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
<script type="text/javascript">
$(function(){
	 year="${billcodeManagePage.codeYear}";
	 month="${billcodeManagePage.codeMoth}";
	 day="${billcodeManagePage.codeDay}";
	 var num="${billcodeManagePage.endNum }";
	 type="${billcodeManagePage.billType }";
	 typeDesc=type;
	 obj1="${billcodeManagePage.billObj1}";
	 obj1Desc=obj1;
	 obj2="${billcodeManagePage.billObj2}";
	 obj2Desc=obj2;
	 if(num=="2"){
		  endnum="01";
	  }else if(num=="3"){
		  endnum="001";
	  }else if(num=="4"){
		  endnum="0001";
	  }else if(num=="5"){
		  endnum="00001";
	  }else if(num=="6"){
		  endnum="000001";
	  }else if(num=="7"){
		  endnum="0000001";
	  }else if(num=="8"){
		  endnum="00000001";
	  }else if(num=="9"){
		  endnum="000000001";
	  }
	 if(isNotEmpty(billType)){
		 $("#billTypeBox").attr("checked","checked");
		 $("#billTyp").removeAttr("readonly");
	 }
	 if(isNotEmpty(obj1)){
		 $("#billObj1Box").attr("checked","checked");
		 $("#billObj1").removeAttr("readonly");
		 obj1Check=true;
	 }
	 if(isNotEmpty(obj2)){
		 $("#billObj2Box").attr("checked","checked");
		 $("billObj2").removeAttr("readonly");
		 obj2Check=true;
	 }
	 if(isNotEmpty(year)){
		 $("#codeYear").attr("checked","checked");
	 	 yearDesc=year+"年";
	 }
	 if(isNotEmpty(month)){
		 $("#codeMoth").attr("checked","checked");
		 monthDesc=month+"月";
	 }
	 if(isNotEmpty(day)){
		 $("#codeDay").attr("checked","checked");
		 dayDesc=day+"日";
	 }
});
</script>
  <script src = "webpage/cn/com/linkwide/common/custom/billcode/manage/billcodeManage.js"></script>		
</html>