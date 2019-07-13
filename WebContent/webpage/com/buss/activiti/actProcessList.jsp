<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 0px; border: 0px">
		<t:datagrid name="actProcesslList"   rowStyler="interlacingColour" checkbox="true" fitColumns="false" title="流程列表" actionUrl="actController.do?datagrid" idField="id" 	fit="true" queryMode="group">
			<t:dgCol title="流程分类" field="category" queryMode="single" query="true" width="120" dictionary="work_flow_cate,id,name"></t:dgCol>
			<t:dgCol title="流程定义Id" field="id" queryMode="single" query="true" width="120"></t:dgCol>
			<t:dgCol title="部署Id" field="deploymentId" queryMode="single" query="false" width="80"></t:dgCol>
			<t:dgCol title="名称" field="name" queryMode="single" query="true" width="120"></t:dgCol>
			<t:dgCol title="流程标识" field="key" queryMode="single" query="false" width="120"></t:dgCol>
			<t:dgCol title="流程版本" field="version" queryMode="single" query="false" width="80"></t:dgCol>
			<t:dgCol title="流程XML" field="resourceName" queryMode="single" query="false" width="180" formatterjs="resourceReadXml"></t:dgCol>
			<t:dgCol title="流程图片" field="diagramResourceName" queryMode="single" query="false" width="180" formatterjs="resourceReadImage"></t:dgCol>
			<t:dgCol title="状态" field="suspensionState" queryMode="single" query="false" width="80" replace="挂起_2,激活_1"></t:dgCol>

			<t:dgCol title="操作" field="opt" width="160" formatterjs="optFmt"></t:dgCol>
			<t:dgToolBar title="部署流程" icon="icon-add" url="actController.do?goDeploy" funname="add"></t:dgToolBar>
		</t:datagrid>
	</div>
</div>

<script type="text/javascript">
	//激活、挂起、删除、转换为模型
	function optFmt(value,row,index){

		var s ="";
		if(row.suspensionState == '2'){
			s = s  +"&nbsp;[<a href = '#' onclick=\"suAcFlow('" + row.id +"','active','" + "actProcesslList" +"')\" ><u>" + "激活" + "</u></a>]";
		}else if(row.suspensionState == '1'){
			s = s  +"&nbsp;[<a href = '#' onclick=\"suAcFlow('" + row.id +"','suspend','" + "actProcesslList" +"')\" ><u>" + "挂起" + "</u></a>]";	
		}
		s = s  +"&nbsp;[<a href = '#' onclick=\"delObj('" + "actController.do?doDel&deploymentId=" +  row.deploymentId +"','" + "actProcesslList" +"')\" ><u>" + "删除" + "</u></a>]";
		//s = s  +"&nbsp;[<a href = '#' onclick=\"convertToModel('" + "actController.do?convertToModel&processDefinitionId=" +  row.id +"','" + "actProcesslList" +"')\" ><u>" + "转换为模型" + "</u></a>]";

		return s;
	}
	
	
	//转换为模型
	function convertToModel(url,name){
		createdialog('转换确认 ', '确定转换为模型吗 ?', url,name);
	}
	
	//流程定义激活或挂起
	function suAcFlow(id,state,name){
		var url="actController.do?updateState&processDefinitionId="+id+"&state="+state;
		var msg="确定激活该流程吗？";
		if(state=='suspend'){
			msg='确定挂起该流程吗？';
		}
		createdialog('部署确认 ', msg, url,name);
	}
	

	$(document).ready(function(){
		//给时间控件加上样式
		$("#actProcesslListtb").find("input[name='createTime_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
		$("#actProcesslListtb").find("input[name='createTime_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
		$("#actProcesslListtb").find("input[name='lastUpdateTime_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
		$("#actProcesslListtb").find("input[name='lastUpdateTime_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	});
  	$(function() {
		$("select[name='category']").combotree({
			url : 'workFlowCateController.do?tree',
			panelHeight : 200,
			editable:false,
			width : 157,
			onClick : function(node) {
				$("#category").val(node.id);
			}
		});
	});

	//查看xml
	function resourceReadXml(value,row,index){
		var s ="";
		s = s  + "&nbsp;<a target=\"_blank\" href = 'actController.do?resourceRead&processDefinitionId=" + row.id + "&resourceType=xml'  ><u><font color=red >" + value + "</font></u></a>";
		return s;
	}
	
	//查看图片
	function resourceReadImage(value,row,index){
		var s ="";
		s = s  + "&nbsp;<a target=\"_blank\" href = 'actController.do?resourceRead&processDefinitionId=" + row.id + "&resourceType=image'  ><u><font color=red >" + value + "</font></u></a>";
		return s;
	}
	

	
</script>