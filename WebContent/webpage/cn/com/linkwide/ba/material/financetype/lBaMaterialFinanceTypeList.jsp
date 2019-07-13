<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<link rel="stylesheet" type="text/css"	href="plug-in/ztree/css/zTreeStyle.css">
<script type="text/javascript"	src="plug-in/ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript"	src="plug-in/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
<div class="easyui-layout" style="width:100%; height: 100%;">
	<!-- 顶部-->
	<div region="north" border="false" title=""
		style="height: 50px; padding: 1px; overflow: hidden;">
		<table class="ui_dialog" width="100%">
			<tbody>
				<tr>
					<td align="left">
						<div class="ui_buttons">
							<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="goAdd();">添加</a>
							<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="goUpd();">编辑</a>
							<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="doDel();">删除</a>
							<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="saveForm();">保存</a>
							<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="stopOrStart(0);">启用</a>
							<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="stopOrStart(1);">停用</a>
						</div>
					</td>
				</tr>
			</tbody>
		</table>

	</div>
	
    <div data-options="region:'west',split:true" title="" style="width: 180px;">
		<ul id="materialFinanceTypeSelect" class="ztree" style="margin-top: 10px;"></ul>
	</div>
    <div data-options="region:'center'"  style="width:1500px;">
		<!-- 分类详情 ------------------->
		<iframe id="formContenFrame" frameborder="0" scrolling="auto"   style="height:100%;width:99.9%;" ></iframe>
	</div>
</div>
 <script src = "webpage/cn/com/linkwide/ba/material/financetype/lBaMaterialFinanceType.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
  /****************input 回车事件屏蔽  开始***********************/
  document.onkeydown = function(e){
    if(e.keyCode == 13){
       pPeopleListListsearch();
      return false;
    }
  }
  document.onkeypress = function(e){
    if(e.keyCode == 13){
       
      return false;
    }
  }
  /*****************input 回车事件屏蔽  开始***********************/
	//给时间控件加上样式
	$("#pPeopleListListtb").find("input[name='birthday_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	$("#pPeopleListListtb").find("input[name='birthday_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	$("#pPeopleListListtb").find("input[name='joinTime_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	$("#pPeopleListListtb").find("input[name='joinTime_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	$("#pPeopleListListtb").find("input[name='armyTime_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	$("#pPeopleListListtb").find("input[name='armyTime_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	$("#pPeopleListListtb").find("input[name='presentTime_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	$("#pPeopleListListtb").find("input[name='presentTime_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	$("#pPeopleListListtb").find("input[name='officeTime_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	$("#pPeopleListListtb").find("input[name='officeTime_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	$("#pPeopleListListtb").find("input[name='createDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	$("#pPeopleListListtb").find("input[name='createDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	$("#pPeopleListListtb").find("input[name='updateDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	$("#pPeopleListListtb").find("input[name='updateDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			
 			
 });
 </script>
 <style type="text/css">
	div.menu-line2{
	  left: 238px;
	}
	div.menu{
	  width: 427px;
	}
	div.menu-item{
	  width: 210px;
	  display: inline-block;
	}
</style>