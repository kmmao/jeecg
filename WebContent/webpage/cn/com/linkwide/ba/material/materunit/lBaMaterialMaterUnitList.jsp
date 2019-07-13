<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="lBaMaterialMaterUnitList" checkbox="true" fitColumns="false" title="计量单位" actionUrl="lBaMaterialMaterUnitController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="计量单位编码"  field="typeCode"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="计量单位名称"  field="typeName"   queryMode="single" query="true"  width="120"></t:dgCol>
   <%-- <t:dgCol title="英文名称单数"  field="englishNameSingular"    queryMode="single" query="true" width="120"></t:dgCol>
   <t:dgCol title="英文名称复数"  field="englishNameComplex"    queryMode="single" query="true"  width="120"></t:dgCol> --%>
   <t:dgCol title="是否停用"  field="status" dictionary="whether" queryMode="single" query="true" width="120"></t:dgCol>
   <t:dgToolBar title="添加" icon="icon-add" url="lBaMaterialMaterUnitController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="lBaMaterialMaterUnitController.do?goUpdate" funname="update"></t:dgToolBar>
    <t:dgToolBar title="启用" icon="icon-ok" url="lBaMaterialMaterUnitController.do?doEnDisAble&status=0" funname="enDisAbleStatus"></t:dgToolBar>
   <t:dgToolBar title="停用" icon="icon-cancel" url="lBaMaterialMaterUnitController.do?doEnDisAble&status=1" funname="enDisAbleStatus"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="lBaMaterialMaterUnitController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-putout" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  
   
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/cn/com/linkwide/ba/material/materunit/lBaMaterialMaterUnit.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
   
 
//导入
function ImportXls() {
	//openuploadwin('Excel导入', 'lBaMaterialMaterUnitController.do?upload', "lBaMaterialMaterUnitList");
	createwindowRef("Excel导入", "lBaMaterialMaterUnitController.do?upload", 350, 150, "dialogTwo");
}

//导出
function ExportXls() {
	var ids = [];
	var rows = $("#lBaMaterialMaterUnitList").datagrid('getSelections');
	if(rows.length==0){
		tip("请选择需要导出的数据");
	}else{
		for(var i = 0; i < rows.length; i++){
			ids.push(rows[i].id);
		}
		JeecgExcelExport("lBaMaterialMaterUnitController.do?exportXls&ids="+ids,"lBaMaterialMaterUnitList");
	}
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("lBaMaterialMaterUnitController.do?exportXlsByT","lBaMaterialMaterUnitList");
}

  /**
 * 多记录修改請求
 * @param title
 * @param url
 * @param gname
 * @return
 */
function enDisAbleStatus(title,url,gname) {
	
	gridname=gname;
    var ids = [];
    var rows = $("#"+gname).datagrid('getSelections');
    if (rows.length > 0) {
    	$.dialog.confirm('你确定修改选中的数据吗?', function(r) {
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
		tip("请选择需要修改的数据");
	}
} 
 
 </script>