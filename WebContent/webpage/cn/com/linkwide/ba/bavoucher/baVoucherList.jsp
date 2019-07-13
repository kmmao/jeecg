<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="baVoucherList" checkbox="false" pagination="true" fitColumns="true" title="生成凭证" actionUrl="baVoucherController.do?datagrid" idField="id" fit="true" queryMode="group" onLoadSuccess="dynamicTableHeader">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="流程状态"  field="bpmStatus" hidden="true"  queryMode="single"  dictionary="bpm_status"  width="120"></t:dgCol>
   <t:dgCol title="业务模块"  field="busModel" dictionary="bus_model"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="业务功能"  field="busFunction" dictionary="bus_function"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="类型"  field="type"  hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="单据编号"  field="billCode" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="单据日期"  field="billDate" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="凭证摘要"  field="voucSummary" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="科目"  field="subject" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="借贷方向"  field="balanceDirection" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="部门"  field="deptId" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="人员"  field="empId" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="供应商"  field="vendor" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="客户患者"  field="customer" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="项目大类"  field="projClasses"  hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="项目分类"  field="projType" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="项目"  field="project" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="业务号"  field="busNum"  hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="财务分类"  field="financialType" hidden="true"  dictionary="ppe_depreciations,line_coding,line_name" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="资金来源"  field="fundSource" hidden="true" dictionary="sof"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="金额"  field="money"  queryMode="single"  width="120"></t:dgCol>
   <t:dgToolBar title="生成凭证" icon="icon-put" funname="doVoucher"></t:dgToolBar>
   <%-- <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="baVoucherController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="添加" icon="icon-add" url="baVoucherController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="baVoucherController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="baVoucherController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="baVoucherController.do?goUpdate" funname="detail"></t:dgToolBar> --%>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/cn/com/linkwide/ba/bavoucher/baVoucherList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
	 //业务模块onchange事件  与业务功能联动
	 var busModels = document.getElementsByName("busModel");
	 busModels[0].onchange=function(){
		var option=document.getElementsByTagName("option");
	 	//业务模块
		var busModel =option[busModels[0].selectedIndex].value;
	 	var functionMap={};
	 	if(busModel=='ppe'){ //固定资产
	 		functionMap={
	 			'zczj':'资产增加',
	 			'jtzj':'计提折旧',
	 			'zcbd':'资产变动',
	 			'zccz':'资产处置',
	 			'zcpg':'资产评估'
	 		};
	 	}else if(busModel=='cont'){ //合同管理
	 		functionMap={
	 			'htfk':'合同付款',
	 			'htsk':'合同收款'
	 		};
	 	}else if(busModel=='bid'){ //招标管理
	 		functionMap={
	 			'tbbzj':'投标保证金'
	 		};
	 	}else if(busModel=='fa'){ //网上报销
	 		functionMap={
	 			'jkd':'借款单',
	 			'zfd':'支付单',
	 			'hkd':'还款单',
	 			'bxd':'报销单',
	 			'bxcjk':'报销冲借款'
	 		};
	 	}else if(busModel=='cost'){//成本管理
	 		functionMap={
	 			'zjcb':'直接成本',
	 			'rlcb':'人力成本',
	 			'cbft':'成本分摊'
	 		};
	 	}else if(busModel=='ef'){ //预算管理
	 		functionMap={
	 			'srys':'收入预算',
	 			'zcys':'支出预算',
	 			'kyys':'科研预算',
	 			'xmys':'项目预算'
	 		};
	 	}else if(busModel=='hr'){//人力资源
	 		functionMap={
	 			'gzft':'工资分摊'
	 		};
	 	}else{ //物资记账
	 		functionMap={
	 			'cgrkd':'采购入库单',
	 			'gzgzd':'高值挂账单',
	 			'dbd':'调拨单',
	 			'qtrkd':'其他入库单',
	 			'qtckd':'其他出库单',
	 			'ckd':'出库单'
	 		};
	 	}
 		
 		var option = "";  
        for (var i in functionMap) {  
            option += '<option value="' + i + '" >' + functionMap[i] + '</option>';  
        }
         /* $("select[name='busFunction").empty().append('<option value="" >- 请选择 -</option>' + option);   */
 		$("select[name='busFunction").empty().append(option);  
		 
	 }
	 
	//业务功能onchange事件  动态显示表头
	 var busFunctions = document.getElementsByName("busFunction");
	 /* busFunctions[0].onchange=function(){
		 dynamicTableHeader();
	 } */
	 
 });
//不同业务模块，不同业务功能动态展示列头
function dynamicTableHeader(){
	var selects =  $("select option:checked");
	//业务模块
	var busModel = selects[0].value
	//业务功能 
	var busFunction=selects[1].value;
	if(busFunction){
		if(busModel=='ppe'){ //固定资产
			//根据类型显示列头 
			var type = $('.datagrid-body td[field=type] div').html();
			if(type=='1'){ //按部门
				$('#baVoucherList').datagrid('showColumn','deptId').css('style','display:block');
				$('#baVoucherList').datagrid('hideColumn','financialType').css('style','display:none');
				$('#baVoucherList').datagrid('hideColumn','fundSource').css('style','display:none');
			}else if(type=='2'){//按财务分类
				$('#baVoucherList').datagrid('showColumn','financialType').css('style','display:block');
				$('#baVoucherList').datagrid('hideColumn','deptId').css('style','display:none');
				$('#baVoucherList').datagrid('hideColumn','fundSource').css('style','display:none');
			}else{ //按资金来源
				$('#baVoucherList').datagrid('showColumn','fundSource').css('style','display:block');
				$('#baVoucherList').datagrid('hideColumn','financialType').css('style','display:none');
				$('#baVoucherList').datagrid('hideColumn','deptId').css('style','display:none');
			}
			
		}
		/* $.ajax({
			url : 'baVoucherController.do?doAdd',
			type : 'post',
			data : {
				'busModel':busModel,
				'busFunction':busFunction
			},
			cache : false,
			success : function(data) {
				var d = $.parseJSON(data);
				debugger
				if (d.success) {
					var msg = d.msg;
					tip(msg);
					reloadTable();
				}
			}
		}); */
	}
	debugger
	/* $("td[name='billCode']").hide() */
	$('#baVoucherList').datagrid('hideColumn','billDate').css('style','display:none');
	 
}
 //生成凭证 
 function doVoucher(){
	$.dialog.setting.zIndex = getzIndex(true);
  	$.dialog.confirm('是否确认生成凭证?', function(r) {
		   if (r) {
			   var selects =  $("select option:checked");
			   //业务模块
			   var busModel = selects[0].value
			   //业务功能 
			   var busFunction=selects[1].value;
				$.ajax({
					url : 'baVoucherController.do?doAdd',
					type : 'post',
					data : {
						'busModel':busModel,
						'busFunction':busFunction
					},
					cache : false,
					success : function(data) {
						var d = $.parseJSON(data);
						if (d.success) {
							var msg = d.msg;
							tip(msg);
							reloadTable();
						}
					}
				});
			}
		});
	  
  } 
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'baVoucherController.do?upload', "baVoucherList");
}

//导出
function ExportXls() {
	JeecgExcelExport("baVoucherController.do?exportXls","baVoucherList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("baVoucherController.do?exportXlsByT","baVoucherList");
}

 </script>