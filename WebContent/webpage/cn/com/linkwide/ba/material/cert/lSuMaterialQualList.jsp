<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="lSuMaterialQualList" checkbox="true" fitColumns="true" title="供应商物资资质" actionUrl="lSuMaterialQualController.do?datagrid" idField="id" rowStyler="interlacingColour" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属科室"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="流程状态"  field="bpmStatus"  hidden="true"  queryMode="single"  dictionary="bpm_status"  width="120"></t:dgCol>
  <%--  <t:dgCol title="证件类型"  field="certType"  query="true"  queryMode="single"  dictionary="vendor_ven_cert_type,type_code,type_name"  width="120"></t:dgCol>
    --%><t:dgCol title="证件编码"  field="certCode"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="证件名称"  field="certName"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="物资"  field="mateId" hidden="true" query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="物资编码"  field="materialCode"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="物资名称"  field="materialName"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="供应商"  field="supplierId"  hidden="true"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="供应商"  field="SupplierName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="发证日期"  field="certDate"  formatter="yyyy-MM-dd"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="停用日期"  field="endDate"  formatter="yyyy-MM-dd"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="发证单位"  field="organ"  query="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="证件状态"  field="certState"  query="true"  queryMode="single"  dictionary="certState"  width="120"></t:dgCol>
   <t:dgCol title="是否停用"  field="isStop"  query="true"  queryMode="single"  dictionary="yes_no_int"  width="80"></t:dgCol>
   <t:dgCol title="启用日期"  field="startDate"  formatter="yyyy-MM-dd"  query="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remark"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="160"></t:dgCol>
   <t:dgOpenOpt title="附件" url="fileDictController.do?fileEdit&tableName=l_su_material_qual&tableId={id}&isEdit=1" width="700"  height="500" urlclass="ace_button"  urlfont="fa-file-o"/>
  <%--  <t:dgDelOpt title="删除" url="lSuMaterialQualController.do?doDel&id={id}"  urlclass="ace_button" urlfont="fa-trash-o"/> --%>
   <t:dgToolBar title="添加" icon="icon-add" url="lSuMaterialQualController.do?goAdd" funname="add" width="700" height="500"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="lSuMaterialQualController.do?goUpdate" funname="update" width="900" height="600"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="lSuMaterialQualController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="lSuMaterialQualController.do?goUpdate" funname="detail" width="900" height="600"></t:dgToolBar>
   <%-- <t:dgToolBar title="提交"  icon="icon-redo" url="lSuMaterialQualController.do?doCheck&state=1" funname="updateALLSelect"></t:dgToolBar> --%>
   <t:dgToolBar title="审批"   icon="icon-ok" url="lSuMaterialQualController.do?doCheck&state=2" funname="updateALLSelect"></t:dgToolBar>
   <t:dgToolBar title="弃审"   icon="icon-undo" url="lSuMaterialQualController.do?doCheck&state=0" funname="updateALLSelect"></t:dgToolBar>
   
    <%--   <t:dgToolBar title="启用" icon="icon-edit" url="lSuMaterialQualController.do?doStart" funname="updateALLSelect" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="停用" icon="icon-edit" url="lSuMaterialQualController.do?doStop" funname="updateALLSelect" width="100%" height="100%"></t:dgToolBar>
    --%>
   
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-comturn" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <div style="display:none;">
<t:comboList id="mateId"  name="mateId"  url="dictListController.do?mateList" idField="id" textField="materialName" ignore="ignore"
	 field="materialCode,materialName,id,"  hasInput="true" panelWidth="400" title="编码:80,物资名称:120" ></t:comboList>
<t:comboList name="supplierId" url="dictListController.do?supplierList" id="supplierId" idField="id" textField="supplierFullName" datatype=""
	 field="supplierCode,supplierFullName,id," hasInput="true"	 panelWidth="400" title="供应商编码:80,供应商名称:120" ></t:comboList>
</div>
 <script src = "webpage/cn/com/linkwide/ba/material/cert/lSuMaterialQualList.js"></script>		
 <script type="text/javascript">
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'lSuMaterialQualController.do?upload', "lSuMaterialQualList");
}

//导出
function ExportXls() {
	JeecgExcelExport("lSuMaterialQualController.do?exportXls","lSuMaterialQualList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("lSuMaterialQualController.do?exportXlsByT","lSuMaterialQualList");
}

/**
 * 多记录修改請求
 * @param title
 * @param url
 * @param gname
 * @return
 */
function updateALLSelect(title,url,gname) {
	gridname=gname;
    var ids = [];
    var rows = $("#"+gname).datagrid('getSelections');
    if (rows.length > 0) {
    	$.dialog.setting.zIndex = getzIndex(true);
    	$.dialog.confirm('你确定批量修改选中数据吗?', function(r) {
		   if (r) {
				for ( var i = 0; i < rows.length; i++) {
					ids.push(rows[i].id);
				}
				$.ajax({
					url : url,
					type : 'post',
					data : {
						ids : ids.join(',')
					},
					cache : false,
					success : function(data) {
						var d = $.parseJSON(data);
						if (d.success) {
							var msg = d.msg;
							tip(msg);
							reloadTable();
							$("#"+gname).datagrid('unselectAll');
							ids='';
						}
					}
				});
			}
		});
	} else {
		tip("请选择需要操作的数据");
	}
}

function back(data){
	 if(data.success){
		 $.dialog.confirm('是否继续添加附件？', function(){ 
			 openwindow('附件','fileDictController.do?fileEdit&tableName=l_su_material_qual&tableId='+data.obj.id+'&isEdit=1','lSuMaterialQualList',700,500)
			}, function(){
		});
	 } 
}
 </script>