<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  	<div region="center" style="padding:0px;border:0px">
	<t:datagrid name="actReProcdefList" checkbox="true" fitColumns="false" title="" 
	  	actionUrl="actReProcdefController.do?datagrid&actReModelCode=${actReModelCode}" idField="id" fit="true" queryMode="group">
		<t:dgCol title="id"  field="id"   hidden="true"  queryMode="group"  width="120"></t:dgCol>
		<t:dgCol title="rev"  field="rev"  hidden="true"   queryMode="group"  width="120"></t:dgCol>
		<t:dgCol title="流程分类" field="category" queryMode="single"  width="120" dictionary="work_flow_cate,id,name"></t:dgCol>
		<t:dgCol title="名称"  field="name" query="true"    width="120"></t:dgCol>
		<t:dgCol title="流程版本"  field="version"    queryMode="group"  width="120"></t:dgCol>
		<t:dgCol title="流程XML"  field="resourceName"  formatterjs="resourceReadXml"  queryMode="group"  width="120"></t:dgCol>
		<t:dgCol title="流程图片"  field="dgrmResourceName"  formatterjs="resourceReadImage"  queryMode="group"  width="120"></t:dgCol>
	</t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
	//查看xml
	function resourceReadXml(value,row,index){
		var s ="";
		s = s  + "&nbsp;<a target=\"_blank\" href = 'actController.do?resourceRead&processDefinitionId=" 
			   + row.id + "&resourceType=xml'  ><u><font color=red >" + value + "</font></u></a>";
		return s;
	}
	
	//查看图片
	function resourceReadImage(value,row,index){
		var s ="";
		s = s  + "&nbsp;<a target=\"_blank\" href = 'actController.do?resourceRead&processDefinitionId=" 
			   + row.id + "&resourceType=image'  ><u><font color=red >" + value + "</font></u></a>";
		return s;
	}
	
</script>