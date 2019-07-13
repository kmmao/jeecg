<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="lBaDepartMateList" checkbox="true" pagination="true" fitColumns="true" title="科室物资对照表" actionUrl="lBaDepartMateController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属科室"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="科室"  field="departId" hidden="true" query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="科室"  field="dept"  dictionary="t_s_depart,id,departname" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="物资编码"  field="materialCode"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="物资"  field="mateId"  hidden="true"  query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="物资名称"  field="materialName"    query="false" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="lBaDepartMateController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="添加" icon="icon-add" url="lBaDepartMateController.do?goAdd" funname="adda"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="lBaDepartMateController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="lBaDepartMateController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="lBaDepartMateController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
  <div style="display:none">
 	 <t:comboList id="departId"  name="departId" url="dictListController.do?departList" idField="id" textField="departname" ignore="checked" 					 field="orgCode,departname,description,id,orgType,iflater,mobile,address," 		hasInput="true"			 panelWidth="400" title="科室编码:80,科室名称:120" ></t:comboList>
 	 <t:comboList id="mateId"  name="mateId" url="dictListController.do?mateList" idField="id" textField="materialName" ignore="checked" 					 field="materialCode,materialName,id," 		hasInput="true"			 panelWidth="400" title="物资编码:80,物资名称:120" ></t:comboList>
 </div>
 <script src = "webpage/cn/com/linkwide/ba/departmate/lBaDepartMateList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
 function adda(title,addurl,gname,width,height) {
		gridname=gname;
		createwindowa(title, addurl,width,height);
	}
 function createwindowa(title, addurl,width,height) {
		width = width?width:700;
		height = height?height:400;
		if(width=="100%" || height=="100%"){
			width = window.top.document.body.offsetWidth;
			height =window.top.document.body.offsetHeight-100;
		}
	    //--author：JueYue---------date：20140427---------for：弹出bug修改,设置了zindex()函数
		if(typeof(windowapi) == 'undefined'){
			$.dialog({
				content: 'url:'+addurl,
				lock : true,
				zIndex: getzIndex(),
				width:width,
				height:height,
				title:title,
				opacity : 0.3,
				cache:false,
			    ok: function(){
			    	iframe = this.iframe.contentWindow;
			    	if(saveObja()){
			    		return true;
			    	}
					return false;
			    },
			    cancelVal: '关闭',
			    cancel: true /*为true等价于function(){}*/
			});
		}else{

			/*W.*/$.dialog({//使用W，即为使用顶级页面作为openner，造成打开的次级窗口获取不到关联的主窗口
				content: 'url:'+addurl,
				lock : true,
				width:width,
				zIndex:getzIndex(),
				height:height,
				parent:windowapi,
				title:title,
				opacity : 0.3,
				cache:false,
			    ok: function(){
			    	iframe = this.iframe.contentWindow;
			    	if(saveObja()){
			    		return true;
			    	}
					return false;
			    },
			    cancelVal: '关闭',
			    cancel: true /*为true等价于function(){}*/
			});

		}
	    //--author：JueYue---------date：20140427---------for：弹出bug修改,设置了zindex()函数
		
	}

 /**
  * 执行保存
  * 
  * @param url
  * @param gridname
  */
 function saveObja() {
	addALLSelect("批量保存","lBaDepartMateController.do?doAdd","lBaMateList") 
	return true;
 }
 function addALLSelect(title,url,gname) {
		gridname=gname;
	    var ids = [];
	    var departId = iframe.$("input[name='departId']").val();
	    var rows = iframe.$("#"+gname).datagrid('getSelections');
	    if (rows.length > 0) {
	    	$.dialog.setting.zIndex = getzIndex(true);
				for ( var i = 0; i < rows.length; i++) {
					ids.push(rows[i].id);
				}
				$.ajax({
					url : url,
					type : 'post',
					data : {
						ids : ids.join(','),
						departId : departId
					},
					cache : false,
					success : function(data) {
						var d = $.parseJSON(data);
						if (d.success) {
							var msg = d.msg;
							$("#lBaDepartMateList").datagrid("load","");
							tip(msg);
							ids='';
						}
					}
				});
		} else {
			/* tip("请选择需要保存的数据"); */
		}
	}

//导入
function ImportXls() {
	openuploadwin('Excel导入', 'lBaDepartMateController.do?upload', "lBaDepartMateList");
}

//导出
function ExportXls() {
	JeecgExcelExport("lBaDepartMateController.do?exportXls","lBaDepartMateList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("lBaDepartMateController.do?exportXlsByT","lBaDepartMateList");
}

 </script>