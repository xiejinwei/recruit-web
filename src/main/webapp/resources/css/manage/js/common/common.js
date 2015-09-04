/**
 * 通用交互JS代码
 */
//删除一条数据
function deleteLine(id,url){
	var r = confirm("确认需要删除？");
	if(r==true){
		$.post(
				url,
				{"id":id},
				function(result){
					if(result=="success"){
						location.reload();
					}else{
						alert("删除数据失败");
					}
				}
		);
	}
}