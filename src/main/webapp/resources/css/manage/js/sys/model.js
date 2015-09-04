/**
 * 菜单管理交互代码
 */
$(document).ready(function() {
	$("#model").validate();
});

function addright() {
	$("#addright").modal();
}

function subright(){
	var mid = $("#mid").val();
	var codevals = [];
	$("input[name='codes']:checked").each(function(i,e){
		var a = $(e).val()+"_"+$(this).parents("li").find("span").text();
		codevals.push(a);
	});
	$.post(
		"addright",
		{"mid":mid,"right":codevals.toString()},
		function(result){
			if(result=="success"){
				location.reload();
			}else{
				alert("添加权限失败");
			}
		}		
	);
}

function uprightname(rightid) {
	$("#rightid").val(rightid);
	$.post("getmodelright", {
		"id" : rightid
	}, function(result) {
		$("#rname").val(result.name);
		$("#updaterightname").modal();
	});

}

function updateright() {
	var rid = $("#rightid").val();
	var rname = $("#rname").val();
	$.post(
		"updatemodelright",
		{"id":rid,"name":rname},
		function(result){
			if(result=="success"){
				location.reload();
			}else{
				alert("修改失败");
			}
		}
	);
}