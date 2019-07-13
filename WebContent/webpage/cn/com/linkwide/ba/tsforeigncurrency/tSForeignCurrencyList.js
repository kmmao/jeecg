
$(function(){
	loadMenuTree();
});

//加载币种
function loadMenuTree(){
	$("#menuTree").tree({
		animate:true,
		url:"tSForeignCurrencyController.do?menuTree",
		onClick:function(node){
			if($("#menuTree").tree("isLeaf",node.target)){
				$("#currencyId").val(node.id);
				loadMenuData(node.id);
			}else{
				$("#menuTree").tree("expand",node.target);
			}
		}
	});
}
//加载数据
function loadMenuData(id){
	$("#tSForeignCurrencyList").datagrid('reload',{id:id});
}