<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="baAcctSubjList" checkbox="false" pagination="false" treegrid="true" treeField="subName" fitColumns="true" title="会计科目" actionUrl="baAcctSubjController.do?datagrid" idField="id" fit="true" queryMode="group" onLoadSuccess="setYear">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="流程状态"  field="bpmStatus"  hidden="true"  queryMode="single"  dictionary="bpm_status"  width="120"></t:dgCol>
   <t:dgCol title="科目年度"  field="acctYear" query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="科目编码"  field="subCode" align="left" query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="科目名称"  field="subName" align="left" query="true"  queryMode="single"  width="150"></t:dgCol>
   <t:dgCol title="上级科目"  field="parentId" hidden="true"  queryMode="single"  width="150"></t:dgCol>
   <t:dgCol title="是否末级"  field="extend1" dictionary="sf_yn" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="余额方向"  field="balanceDirection" dictionary="acct_dire"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="是否现金流量科目"  field="bcash" dictionary="sf_yn" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="是否部门辅助核算"  field="isDepartCalculate" dictionary="sf_yn"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="是否个人辅助核算"  field="isPersonalCalculate"  dictionary="sf_yn"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="是否供应商辅助核算"  field="isSupplierCalculate" dictionary="sf_yn" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="是否客户辅助核算"  field="isCustomerCalculate"  dictionary="sf_yn" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="是否项目辅助核算"  field="isProjectCalculate" dictionary="sf_yn"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="对应项目大类"  field="pacId" dictionary="ba_itemtype,vitemcode,vitemname"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="资金来源"  field="fundSource"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="患者ID"  field="patientId"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="自定义项3"  field="extend2"  dictionary="sf_yn" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="自定义项8"  field="extend3"  dictionary="sf_yn" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="自定义项10"  field="extend4"  dictionary="sf_yn"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段5"  field="extend5"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段6"  field="extend6"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段7"  field="extend7"  hidden="true"  queryMode="single"  width="120"></t:dgCol> 
   <t:dgCol title="扩展字段8"  field="extend8"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段9"  field="extend9"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段10"  field="extend10"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="助记码"  field="spell"  queryMode="single"  width="120"></t:dgCol>
   <%-- <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="baAcctSubjController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/> --%>
   <%-- <t:dgToolBar title="添加" icon="icon-add" url="baAcctSubjController.do?goAdd" funname="add" width="800"></t:dgToolBar> --%>
   <t:dgToolBar title="同步u8数据" icon="icon-add" url="baAcctSubjController.do?catchU8Sub" funname="synSub" width="800"></t:dgToolBar>
   <%-- <t:dgToolBar title="编辑" icon="icon-edit" url="baAcctSubjController.do?goUpdate" funname="update" width="800"></t:dgToolBar> --%>
   <%-- <t:dgToolBar title="批量删除"  icon="icon-remove" url="baAcctSubjController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar> --%>
   <t:dgToolBar title="查看" icon="icon-search" url="baAcctSubjController.do?goUpdate" funname="detail"></t:dgToolBar>
   <%-- <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar> --%>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <%-- <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar> --%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/cn/com/linkwide/ba/baacctsubj/baAcctSubjList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
	 $("#baAcctSubjList").treegrid({
		 onExpand : function(row){
			 var children = $("#baAcctSubjList").treegrid('getChildren',row.id);
			 if(children.length<=0){
			 	row.leaf=true;
			 	$("#baAcctSubjList").treegrid('refresh', row.id);
			 }
		}
	});
	 /* var year = new Date().getFullYear();
 	 $("input[name='acctYear']").val(year);
 	 setTimeout(function(){baAcctSubjListsearch()}, 300); */
	 
});
 //限制年份输入格式
 function setYear(){
 	$('#baAcctSubjListForm').find('input').attr({'maxlength':4});
 	$('#baAcctSubjListForm input:eq(0)').bind('keyup', function() { 
 		$(this).val($(this).val().replace(/[^\d]/g,''));
   });
 }

//抓取u8会计科目
function synSub(title,url,gname){
	gridname=gname;
	var year =$("input[name='acctYear']").val();
	if(!year){
		alertTipTop('请先填写科目年度');
		return ;
	}
   	$.dialog.setting.zIndex = getzIndex(true);
   	$.dialog.confirm('确定同步数据吗?', function(r) {
	   if (r) {
			$.ajax({
				url : url,
				type : 'post',
				data : {
					year : year
				},
				cache : false,
				success : function(data) {
					var d = $.parseJSON(data);
					if (d.success) {
						var msg = d.msg;
						tip(msg);
						reloadTable();
						$("#"+gname).datagrid('unselectAll');
					}else{
						var msg = d.msg;
						tip(msg);
					}
				}
			});
		}
	});
}   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'baAcctSubjController.do?upload', "baAcctSubjList");
}

//导出
function ExportXls() {
	JeecgExcelExport("baAcctSubjController.do?exportXls","baAcctSubjList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("baAcctSubjController.do?exportXlsByT","baAcctSubjList");
}

 </script>