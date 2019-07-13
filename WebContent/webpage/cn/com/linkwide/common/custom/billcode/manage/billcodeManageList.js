 $(function(){
		loadMenuTree();
	});
 //加载菜单树
function loadMenuTree(){
	$("#menuTree").tree({
		animate:true,
		url:"billcodeManageController.do?menuTree",
		onClick:function(node){
			if($("#menuTree").tree("isLeaf",node.target)){
				$("#billType").val(node.id);
				loadMenuData(node.id);
			}else{
				$("#menuTree").tree("expand",node.target);
			}
		}
	});
}
//加载单据号管理数据
function loadMenuData(billType){
	$("#billcodeManageList").datagrid('reload',{billType:billType});
}
