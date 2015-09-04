/**
 * 入库管理交互JS代码
 */
$(document).ready(function() {
	$("#intoer").validate({
		rules: {
			supply:{
				min:1
			},
			price:{
				min:1
			},
			sellprice:{
				min:1
			}
		},
		messages:{
			supply:{
				min:"整数值，且最小值为1"
			},
			price:{
				min:"进货单价必须大于等于1"
			},
			sellprice:{
				min:"建议零售价必须大于等于1"
			}
		}
	});
});


//联想输入供货商
function autocompleteSupplier(){
	$('#supplierlist').dataTable();
	$("#suppliers").modal();
}

//选择供货商
function choosesu(arg){
	var checked = arg.checked;
	$("input[name='supids']:checked").attr("checked",false);
	if(checked){
		arg.checked=true;
	}else{
		alert("请选择一个供货商");
		arg.checked=true;
	}
}

//确认选择供应商
function confirmSupplier(arg){
	var sup = $("input[name='supids']:checked");
	$("#sname").val($(sup[0]).attr("supname"));
	$("#supid").val($(sup[0]).val());
	$("#suppliers").modal('hide');
}
