<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 0px; border: 0px">
		<t:datagrid name="actReModelList" checkbox="true" fitColumns="false" title="模型列表" actionUrl="actReModelController.do?datagrid&category=${category}" idField="id" fit="true" queryMode="group">
			<t:dgCol title="模型ID" field="id" queryMode="single" query="true" width="120"></t:dgCol>
			<t:dgCol title="流程分类" field="category" queryMode="single" query="false" width="120" dictionary="work_flow_cate,id,name"></t:dgCol>
			<t:dgCol title="模型名称" field="name" queryMode="group" width="120"></t:dgCol>
			<t:dgCol title="模型标识" field="key" queryMode="group" width="120"></t:dgCol>
			<t:dgCol title="版本号" field="version" queryMode="group" width="120"></t:dgCol>
			<t:dgCol title="创建时间" field="createTime" formatter="yyyy-MM-dd" queryMode="group" width="120"></t:dgCol>
			<t:dgCol title="最后更新时间" field="lastUpdateTime" formatter="yyyy-MM-dd" queryMode="group" width="120"></t:dgCol>
			<t:dgCol title="元数据" field="metaInfo" queryMode="group" width="120"></t:dgCol>
			<t:dgCol title="操作" field="opt" width="160" formatterjs="optFmt"></t:dgCol>
			<t:dgDelOpt title="删除" url="actReModelController.do?doDel&id={id}" />
			<t:dgToolBar title="添加" icon="icon-add" url="actReModelController.do?goAdd&&category=${category}" funname="add"></t:dgToolBar>
		</t:datagrid>
	</div>
</div>
<script src="webpage/com/buss/activiti/actReModelList.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		// 也没没有找到要加时间样式的暂时去掉
		//给时间控件加上样式
		/* $("#actReModelListtb").find("input[name='createTime_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
		$("#actReModelListtb").find("input[name='createTime_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
		$("#actReModelListtb").find("input[name='lastUpdateTime_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
		$("#actReModelListtb").find("input[name='lastUpdateTime_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});}); */
	});

	function optFmt(value,row,index){
		var s ="";
		s = s  + "&nbsp;[<a href = '#' onclick=\"editWorkFlow('"  + row.id + "')\" ><u>" + "编辑" + "</u></a>]";
		s = s  + "&nbsp;[<a href = '#' onclick=\"deployWorkFlow('" + "actReModelController.do?deploy&id=" +  row.id +"','" + "actReModelList" +"')\" ><u>" + "部署" + "</u></a>]";
		s = s  + "&nbsp;[<a href = '#' onclick=\"exportWorkFlow('"  + row.id + "')\" ><u>" + "导出" + "</u></a>]";
		s = s  + "&nbsp;[<a href = '#' onclick=\"delObj('" + "actReModelController.do?doDel&id=" +  row.id +"','" + "actReModelList" +"')\" ><u>" + "删除" + "</u></a>]";

		return s;
	}

	//流程编辑
	function editWorkFlow(id){
		window.open("modeler.html?modelId=" + id);
	}
	
	//流程导出
	function exportWorkFlow(id){
		window.location.href="<%=basePath%>/actReModelController/export/" + id + "/bpmn";
	}

	//流程部署
	function deployWorkFlow(url,name){
		createdialog('部署确认 ', '确定部署该流程吗 ?', url,name);
	}

 </script>