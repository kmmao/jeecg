<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div data-options="region:'west', collapsed:false, split:true, border:false " style="width: 150px;">
		<ul id="baCustomerList_type"></ul>
	</div>
	<input type="hidden" id="nodeId">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="baCustomerList" checkbox="true" sortName="customerCode" sortOrder="asc" pagination="true" fitColumns="true" title="客户档案" actionUrl="baCustomerController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="客户编码"  field="customerCode"  query="true"  queryMode="single"  width="140"></t:dgCol>
   <t:dgCol title="客户名称"  field="customerFullName"   query="true"  queryMode="single"  width="250"></t:dgCol>
   <t:dgCol title="客户简称"  field="customerShortName" query="true" queryMode="single"  width="180"></t:dgCol>
   <t:dgCol title="客户分类"  field="customerTypeId" dictionary="ba_customer_type,type_code,type_name"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属类型"  field="extend1"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="联系人"  field="contacts"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="联系电话"  field="telphone"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="地址"  field="address"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="统一社会信用代码"  field="uscc"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="审批人"  field="auditor"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="所属银行"  field="affiliatedBank"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="法人"  field="legalPerson"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="停用人"  field="ceasePeople"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="流程状态"  field="bpmStatus"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="助记码"  field="mnemonicCode"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="注册资金"  field="registerCapital"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="审批状态"  field="auditingState" hidden="true"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="默认交易币种"  field="defaultCurrency" dictionary="currency"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="是否停用"  field="isCease" dictionary="whether"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="首付款协议"  field="paymentReceivedProtocol"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="专管业务员"  field="superviseSalesman"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="公司地址"  field="corporationAddress"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="网址"  field="website"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="营业执照有效日期至"  field="vld"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="停用时间"  field="ceaseDate"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="银行账号"  field="bankCode"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="公司电话"  field="corporationPhone"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="审批时间"  field="auditingDate"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="营业执照发证日期"  field="grantDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="开户银行"  field="oaaBank"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="所属地区"  field="affiliatedArea"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建部门"  field="departId"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段2"  field="extend2"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段3"  field="extend3"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段4"  field="extend4"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="扩展字段5"  field="extend5"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="baCustomerController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="同步到U8" icon="icon-add" url="baCustomerController.do?synToU8" funname="synToU8" width="1000" height="450"></t:dgToolBar>
   <t:dgToolBar title="添加" icon="icon-add" url="baCustomerController.do?goAdd" funname="addFun" width="1000" height="450"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="baCustomerController.do?goUpdate" funname="beforeUpdate" width="1000" height="450"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="baCustomerController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="baCustomerController.do?goUpdate" funname="detail" width="1000" height="450"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="更新式导入" icon="icon-put" funname="ImportXls2"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/cn/com/linkwide/ba/bacustomer/baCustomerList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
	 //加载客户类型树
	 $('#baCustomerList_type').tree({
			 method:"POST",
			 lines:true,
			 url:"baCustomerTypeController/getTreeAllDateForstate.do",
			 onClick: function(node){
				 if($(this).tree("isLeaf",node.target)){
					 //$("#nodeId").val(node.id);
					 $("#nodeId").val(node.attributes.isLast);
				 }else{
					 $("#nodeId").val("");
				 }
				 
			 var ids = [];
			 ids.push(node.id);
			 var childList = $(this).tree('getChildren',node.target);
			 $.each(childList,function(index,curr){
				 ids.push(curr.id);
			 });
				 
				 $("#baCustomerList").datagrid('load',{
					customerTypeIds : ids.join(",")
				 });
			},
			onLoadSuccess:function(node,data){
				$("#baCustomerList_type li:eq(0)").find("div:first").addClass("tree-node-selected");
				var n = $("#baCustomerList_type").tree("getSelected");
				
				var ids = [];
				 ids.push(n.id);
				 var childList = $(this).tree('getChildren',n.target);
				 $.each(childList,function(index,curr){
					 ids.push(curr.id);
				 });
				
				if(n){
					//$("#nodeId").val(n.id);
					$("#nodeId").val(n.attributes.isLast);
					$('#baCustomerList').datagrid('options').url = 'baCustomerController.do?datagrid&field=id,customerCode,customerFullName,customerShortName,customerTypeId,contacts,telphone,address,extend1,';
					$("#baCustomerList").datagrid('reload',{
						customerTypeIds : ids.join(",")
				 	})
				} 
			}
		});
 });

//编辑前的校验
 function beforeUpdate(title,url, id,width,height,isRestful) {
 	 //勾选的数据
 	 var rowsData = $('#'+id).datagrid('getSelections');
 	 if(rowsData && rowsData.length==1){ 
 		//审核状态
 		 var auditingState = rowsData[0].auditingState;
 		//是否停用
 		var isCease=rowsData[0].isCease;
 		 if(auditingState=="审核"){
 			 tip("该单据已审核，不能编辑");
 		 }else if(isCease=="1"){
 			 tip("该单据已停用，不能编辑");
 		 }else{
 			gridname=id;
 			url+= '&id=' +rowsData[0].id;
 			createwindow(title, url,width,height);
 		 }
 	 }else{
 		 tip("请选择一条单据");
 	 }
  }
 function addFun(title,addurl,gname,width,height){
	 var nodeId = $("#nodeId").val();
	 if(nodeId == "0"){
		tip("请选择叶子节点添加数据"); 
		return;
	}
	 add(title,addurl+"&customerTypeIds="+nodeId,gname,width,height);
 }
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'baCustomerController.do?upload', "baCustomerList");
}

//更新式导入
function ImportXls2() {
	openuploadwin('Excel导入', 'baCustomerController.do?updateUpload', "baCustomerList");
}

//导出
function ExportXls() {
	JeecgExcelExport("baCustomerController.do?exportXls","baCustomerList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("baCustomerController.do?exportXlsByT","baCustomerList");
}
function synToU8(title,url,gname) {
	gridname=gname;
    var rows = $("#"+gname).datagrid('getRows');
    if (rows.length > 0) {
    	$.dialog.setting.zIndex = getzIndex(true);
    	$.dialog.confirm('你确定将数据推送到U8吗?', function(r) {
		   if (r) {
				$.ajax({
					url : url,
					type : 'post',
					data : {
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
	} else {
		tip("当前没有客户档案数据推送到U8");
	}
}
 </script>