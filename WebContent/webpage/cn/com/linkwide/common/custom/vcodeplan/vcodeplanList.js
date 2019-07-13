 $(function(){
		loadMenuTree();
	});
 //加载菜单树
function loadMenuTree(){
	$("#menuTree").tree({
		animate:true,
		url:"vcodeplanController.do?menuTree",
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
//加载数据
function loadMenuData(vdef4){
	$("#vcodeplanList").datagrid('reload',{vdef4:vdef4});
}