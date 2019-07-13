<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<style>
	.datagrid-row.datagrid-row-selected{
		background: none!important;
	}
</style>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 0px; border: 0px">
		<t:datagrid name="needDealList" rowStyler="interlacingColour" fitColumns="true" pagination="true" title="" actionUrl="actTaskController.do?datagridNeed" idField="id" fit="true" queryMode="group">
			
			<t:dgCol title="任务ID" field="task.id"  width="80" hidden="true"></t:dgCol>
			<t:dgCol title="任务Key" field="task.taskDefinitionKey"  width="100" hidden="true"></t:dgCol>
			<t:dgCol title="当前环节" field="task.name"  width="140" formatterjs="diagramViewer"></t:dgCol>
			<t:dgCol title="流程定义ID" field="task.processDefinitionId" hidden="true"  width="100"></t:dgCol>
			<t:dgCol title="流程名称" field="procDef.name"  width="120"></t:dgCol>
			<t:dgCol title="流程实例ID" field="task.processInstanceId" hidden="true"  width="100"></t:dgCol>
			<t:dgCol title="优先级" field="task.priority"  width="50" hidden="true"></t:dgCol>
			<t:dgCol title="任务创建日期" field="task.createTime" hidden="true" width="130"  formatterjs="formateDate"></t:dgCol>
			<t:dgCol title="任务逾期日" field="task.dueDate" hidden="true"  width="100"></t:dgCol>
			<t:dgCol title="任务描述" field="task.description" hidden="true"  width="100"></t:dgCol>
			<t:dgCol title="任务所属人" field="task.assignee" dictionary="t_s_base_user,username,realname" width="100"></t:dgCol>									
			<t:dgCol title="操作" field="opt" width="160" formatterjs="optFmt"></t:dgCol>
			
		</t:datagrid>
	</div>
</div>
<script src = "webpage/com/buss/activiti/workFlowTools.js"></script>
<script type="text/javascript">
	//日期处理
	function formateDate(value,row,index){
		var date = new Date(value);
		return dateFormat(date);		
	}
	//流程跟踪
	function diagramViewer(value,row,index){
		var s ="";
		s = s  + "&nbsp;<a target=\"_blank\" href = 'diagram-viewer/index.html?processDefinitionId=" + row['task.processDefinitionId'] + "&processInstanceId="  + row['task.processInstanceId'] +  "'  ><u><font color=red >" + value + "</font></u></a>";
		return s;
	}
	
	//签收、任务处理
	function optFmt(value,row,index){
		var s ="";
		var taskId = row['task.id'];
		if(row['task.assignee'] == ''){
			s += "&nbsp;<a href = '#' onclick='claim("+taskId+")'>[<u><span style='color:blue'>签收</span></u>]</a>";
			s += "&nbsp;<a href='#' onclick='claimOther("+taskId+")'>[<u><span style='color:blue'>指派</span></u>]</a> ";
		}else {
			s += "&nbsp;<a href='#' onclick='handTask("+taskId+")'>[<u><span style='color:blue'>马上处理</span></u>]</a>";
		}
		return s;
	}
	//马上处理
	 function handTask(taskId){
		var buttons = [];
		var url = "actTaskController.do?goHandTask&taskId="+taskId;
		openDefWindow(url,"处理任务",'100%','100%',buttons);
	 }
	/*** 签收任务*/
	function claim(taskId,name){
		gridname='needDealList';
		url = "actTaskController.do?doClaimTask&taskId="+taskId;
		$.dialog.confirm('确定签收吗 ?', function(r) {
			 if (r) {
			 	 $.ajax({
		 			type:"POST",
		 			url:url,
		 			success:function(data){
			 			reloadTable();
		 			}
	 			});
			 }
		});
		//createdialog('部署签收 ', '确定签收吗 ?', url,name);
	}
	//指派任务界面调整
	function claimOther(taskId){
		var buttons = [{name:"确认",callback:doCliamAssign,focus:true},
		                {name:"取消",callback:function(){}}];
		var url = "actTaskController.do?goChangeAssign&taskId="+taskId;
		openDefWindow(url,"指派",'700px','150px',buttons);
	}
	//指派任务处理
	function doCliamAssign(){
		var iframe = this.iframe.contentWindow;
		var taskId = iframe.$("#taskId").val();
		var userid = iframe.$("input[name='newUser']").val();
		var url = "actTaskController.do?doClaimAssign&taskId="+taskId+"&userid="+userid;
		debugger
		if(userid){
			doFun(url,"needDealList");
		}else{
			alertTipTop("请选择需要指派的用户");
		}
	}
</script>