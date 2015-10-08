//基础交互js
function singlechose(arg){
	$("table:checkbox").each(function(e){
		$(e).attr("checked",false);
	});
	if(arg.value==true){
		arg.checked=true;
	}
}