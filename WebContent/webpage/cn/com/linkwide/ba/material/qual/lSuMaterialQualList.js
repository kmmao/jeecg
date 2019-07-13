  /**修改业务单据处理*/
 function goUpdate(title,url, id,width,height,isRestful) {
	gridname=id;
	var rowsData = $('#'+id).datagrid('getSelections');
	if (!rowsData || rowsData.length==0) {
		tip('请选择编辑项目');
		return;
	}
	if (rowsData.length>1) {
		tip('请选择一条记录再编辑');
		return;
	}
	if(rowsData[0].auditStatus != '1'){
		tip('请选择未提交数据再编辑');
		return;
	}
	if(isRestful!='undefined'&&isRestful){
		url += '/'+rowsData[0].id;
	}else{
		url += '&id='+rowsData[0].id;
	}
	
	
	createwindow(title,url,width,height);
}
 /**批量删除业务单据处理*/
 function goBatchDel(title,url,gname) {
	gridname=gname;
    var ids = [];
    var rows = $("#"+gname).datagrid('getSelections');
    if (rows.length > 0) {
    	$.dialog.setting.zIndex = getzIndex(true);
    	$.dialog.confirm('你确定永久删除该数据吗?', function(r) {
		   if (r) {
				for ( var i = 0; i < rows.length; i++) {
					if(rows[i].auditStatus == '1'){
						ids.push(rows[i].id);
					}
				}
				if(ids.length>0){
					doUrl(url,gname,ids);
				}else{
					tip("请选择未提交数据再删除！");
				}
			}
		});
	} else {
		tip("请选择需要删除的数据");
	}
}
 /**提交业务单据处理*/
 function goCommit(title,url,gname) {
	gridname=gname;
    var ids = [];
    var rows = $("#"+gname).datagrid('getSelections');
    if (rows.length > 0) {
    	$.dialog.confirm('你确定提交选中的数据吗?', function(r) {
		   if (r) {
				for ( var i = 0; i < rows.length; i++) {
					if(rows[i].auditStatus == '1'){
						ids.push(rows[i].id);
					}
				}
				if(ids.length>0){
					doUrl(url,gname,ids);
				}else{
					tip("请选择需要提交的数据！");
				}
			}
		});
	} else {
		tip("请选择需要提交的数据");
	}
} 
 /**审批业务单据处理*/
 function goApprove(title,url,gname) {
	gridname=gname;
    var ids = [];
    var rows = $("#"+gname).datagrid('getSelections');
    if (rows.length > 0) {
    	$.dialog.confirm('你确定审批选中的数据吗?', function(r) {
		   if (r) {
				for ( var i = 0; i < rows.length; i++) {
					if(rows[i].auditStatus == '1'){
						ids.push(rows[i].id);
					}
				}
				if(ids.length>0){
					doUrl(url,gname,ids);
				}else{
					tip("请选择需要审批的数据！");
				}
			}
		});
	} else {
		tip("请选择需要审批的数据");
	}
}
/**弃审业务单据处理*/
 function goUnApprove(title,url,gname) {
	gridname=gname;
    var ids = [];
    var rows = $("#"+gname).datagrid('getSelections');
    if (rows.length > 0) {
    	$.dialog.confirm('你确定弃审选中的数据吗?', function(r) {
		   if (r) {
				for ( var i = 0; i < rows.length; i++) {
					if(rows[i].auditStatus == '3' || rows[i].auditStatus == '4'){
						ids.push(rows[i].id);
					}
					
				}
				if(ids.length>0){
					doUrl(url,gname,ids);
				}else{
					tip("请选择需要弃审的数据！");
				}
			}
		});
	} else {
		tip("请选择需要弃审的数据");
	}
} 
/**执行url*/
function doUrl(url,gname,ids) {
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