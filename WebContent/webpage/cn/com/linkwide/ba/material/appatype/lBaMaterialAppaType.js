 $(document).ready(function(){
	 $('#materialAppaTypeSelect').tree({
		 method:"POST",
		 lines:true,
		 url:"lBaMaterialAppaTypeController.do?comboTree",
		 onClick: function(node){
			 var date = new Date();
		 	    var url = "lBaMaterialAppaTypeController.do?goShow&id="+node.id+"&"+date.getTime();
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
	var node = $('#materialAppaTypeSelect').tree('getSelected');
	 var date = new Date();
	if(node){
		if(node.attributes.status == "1"){
			tip("节点已经停用，不能添加子节点");
		}else{
			var url = "lBaMaterialAppaTypeController.do?goAdd&parentId="+node.id+"&"+date.getTime();
			$("#formContenFrame").attr("src",url);
		}
	}else{
		var url = "lBaMaterialAppaTypeController.do?goAdd"+"&"+date.getTime();
		$("#formContenFrame").attr("src",url);
	}
	
}

//修改
function goUpd(){
	var node = $('#materialAppaTypeSelect').tree('getSelected');
	 var date = new Date();
	if(node){
		var url = "lBaMaterialAppaTypeController.do?goUpdate&id="+node.id+"&"+date.getTime();
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
	/*var iframe = $("iframe");
	 $("#btn_sub",iframe[0].contentDocument).click();*/
	//$('#cc').layout('panel','east').find('#btu_sub').click();
}


//删除
function doDel(){
	var node = $('#materialAppaTypeSelect').tree('getSelected');
	if(node){
		 if($('#materialAppaTypeSelect').tree("isLeaf",node.target)){
			 $.ajax({
				 type:"POST",
				 async:false,
				 url:"lBaMaterialAppaTypeController.do?doDel",
				 data:{
					id:node.id
				 },
				 dataType:"json",
				 success:function(data){
					 if(data.success){
						 $('#materialAppaTypeSelect').tree("reload");
					 }else{
						 tip("删除分类失败");
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




//停用 启用
function stopOrStart(status){
	var node = $('#materialAppaTypeSelect').tree('getSelected');
	
	if(node !=null && node !=""){
		var id = node.id;
		var url = "lBaMaterialAppaTypeController.do?doEnDisAble&id="+id;
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
							}else if(d.msg == '400'){
								tip("本节点的父节点被停用,不能启用!");
							}else{
								 var date = new Date();
								var url = "lBaMaterialAppaTypeController.do?goShow&id="+id+"&"+date.getTime();
								$("#formContenFrame").attr("src",url);
								$('#materialAppaTypeSelect').tree("reload");
							}	
						}
				 }
		});
	}else{
		tip("请选择需要修改的数据");
	}
	
}



