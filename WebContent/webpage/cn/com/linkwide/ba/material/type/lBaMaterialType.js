$(document).ready(function(){
	
	 $('#materialTypeSelect').tree({
		 method:"POST",
		 lines:true,
		 url:"lBaMaterialTypeController.do?comboTree",
		 onClick: function(node){
			 	var date = new Date();
		 	    var url = "lBaMaterialTypeController.do?goShow&id="+node.id+"&"+date.getTime();
	 			$("#formContenFrame").attr("src",url);
		}
	});
 });
 
    
//刷新
function ref(){
   window.location.reload();
}


    

//新增
function goAdd(){
	var node = $('#materialTypeSelect').tree('getSelected');
	var date = new Date();
	if(node){
		if(node.attributes.status == "1"){
			tip("节点已经停用，不能添加子节点");
		}else{
			var url = "lBaMaterialTypeController.do?goAdd&parentId="+node.id+"&"+date.getTime();
			$("#formContenFrame").attr("src",url);
		}
	}else{
		var url = "lBaMaterialTypeController.do?goAdd"+"&"+date.getTime();
		$("#formContenFrame").attr("src",url);
	}
	
}

//修改
function goUpd(){
	var node = $('#materialTypeSelect').tree('getSelected');
	var date = new Date();
	if(node){
		var url = "lBaMaterialTypeController.do?goUpdate&id="+node.id+"&"+date.getTime();
		$("#formContenFrame").attr("src",url);
	 }else{
		 tip("请选择一个节点");
	 }
	
}

//保存
function saveForm(){
	//获取新增或修改页面
	var iframe = document.getElementById('formContenFrame');	//$("iframe");

	var doc;
	if(iframe.contentDocument){
		doc = iframe.contentDocument;
	}else if(iframe.contentWindow){
		doc = iframe.contentWindow.document;
	}
	 $("#btn_sub",doc).click();
	//$('#cc').layout('panel','east').find('#btu_sub').click();
}


//删除
function doDel(){
	var node = $('#materialTypeSelect').tree('getSelected');
	if(node){
		 if($('#materialTypeSelect').tree("isLeaf",node.target)){
			 $.ajax({
				 type:"POST",
				 async:false,
				 url:"lBaMaterialTypeController.do?doDel",
				 data:{
					id:node.id
				 },
				 dataType:"json",
				 success:function(data){
					 if(data.success){
						 $('#materialTypeSelect').tree("reload");
					 }else{
						 tip(data.msg)
					 }
				 }
			 });
		 }else{
			 tip("只能删除叶子节点");
		 }
	 }else{
		 tip("请选择一个节点");
	 }
	
}


//添加或更新后刷新

//停用 启用
function stopOrStart(status){
	var node = $('#materialTypeSelect').tree('getSelected');
	$("#tip").html("");
	if(node !=null && node !=""){
		var id = node.id;
		var url = "lBaMaterialTypeController.do?doEnDisAble&id="+id;
		$.ajax({
				 type:"post",
				 url:url,
				 data:{
				 	status:status
				 },
				 success:function(data){
					 var d = $.parseJSON(data);	
						if (d.success) {
							if(d.msg == 'exist'){
								tip("请先停用或启用子节点!");
							}else if(d.msg == 'existData'){
								tip("物资分类下存在物资档案,不能停用!");
							}else if(d.msg == '400'){
								tip("本节点的父节点被停用,不能启用!");
							}else{
								var date = new Date();
								var url = "lBaMaterialTypeController.do?goShow&id="+id+"&"+date.getTime();
								$("#formContenFrame").attr("src",url);
								$('#materialTypeSelect').tree("reload");
							}	
						}
				 }
		});
	}else{
		tip("请选择需要修改的数据");
	}
}



