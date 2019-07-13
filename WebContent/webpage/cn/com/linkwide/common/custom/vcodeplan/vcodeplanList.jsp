<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div region="west" style="width: 200px;"split="true" collapsed="false">
		<div class="easyui-panel" style="padding:0px;border:0px" fit="true" border="false">
			<ul id="menuTree">
			</ul>
			<input id="billType" type="hidden">
		</div>
 </div>
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="vcodeplanList" checkbox="true" onDblClick="dblDetail" detailUrl="vcodeplanController.do?goUpdate"  rowStyler="interlacingColour" fitColumns="false" title="编码方案" actionUrl="vcodeplanController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="最大级数"  field="biglevel"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="最大长度"  field="biglength"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="单级最大长度"  field="bigeachlength"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="第一级"  field="onelevel"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="第二级"  field="twolevel"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="第三级"  field="threelevel"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="第四级"  field="fourlevel"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="第五级"  field="fivelevel"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="第六级"  field="sixlevel"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="第七级"  field="sevenlevel"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="第八级"  field="eightlevel"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="第九级"  field="ninelevel"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="第十级"  field="tenlevel"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段1"  field="vdef1"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段2"  field="vdef2"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段3"  field="vdef3"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段4"  field="vdef4"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预留字段5"  field="vdef5"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="vcodeplanController.do?doDel&id={id}" />
   <t:dgToolBar title="添加" icon="icon-add" url="vcodeplanController.do?goAdd" funname="add1"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="vcodeplanController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="vcodeplanController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-see" url="vcodeplanController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-comturn" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/cn/com/linkwide/common/custom/vcodeplan/vcodeplanList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//添加
  function add1(title,addurl,gname,width,height) {
	 var vcodetypeid=$("#billType").val();
	 if(!isNotEmpty(vcodetypeid)){
		 tip("请选择一个项目！");
	 } else{
		var rowsData = $('#'+gname).datagrid('getData');
		if(rowsData.total>0){
			tip("只能定义一种编码方案");
		}else{
			gridname=gname;
			var url=addurl+"&vcodetypeid="+vcodetypeid;
			createwindow(title,url,width,height);
		}
	 } 
}
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'vcodeplanController.do?upload', "vcodeplanList");
}

//导出
function ExportXls() {
	JeecgExcelExport("vcodeplanController.do?exportXls","vcodeplanList");
}
//非空判断
function isNotEmpty(s){
	return ((s!=undefined&&s!=null&&s!="")?true:false);
}
//模板下载
function ExportXlsByT() {
	JeecgExcelExport("vcodeplanController.do?exportXlsByT","vcodeplanList");
}
 </script>