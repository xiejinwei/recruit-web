/**
 * 职位类型管理交互js
 */
function gettypesbylevel(arg) {
	if (arg.value != -1) {
		$.post("/manage/positiontype/gettypesbylevel", {
			"level" : arg.value
		}, function(result) {
			var options = "";
			for (var i = 0; i < result.length; i++) {
				options += "<option value='"+result[i].id+"'>"+result[i].name+"</option>";
			}
			$("#parentid").empty().append(options);
		});
	}
}

function deleteline(id){
	$("#delid").val(id);
	$("#myModal").modal();
}

function deletedata(url){
	$.post(
		url,
		{"id":$("#delid").val()},
		function(result){
			if(result=="success"){
				alert("删除成功！");
				location.reload();
			}else{
				alert("删除失败！");
				$("#myModal").modal('hide');
			}
		}
	);
}
