/**
 * 入库管理交互JS代码
 */
$(document).ready(function() {
	$("#outstoer").validate({
		rules: {
			supply:{
				min:1
			},
			price:{
				min:1
			},
			reward:{
				min:0
			}
		},
		messages:{
			supply:{
				min:"整数值，且最小值为1"
			},
			price:{
				min:"销售单价必须大于等于1"
			},
			reward:{
				min:"提成金额必须大于等于0"
			}
		}
	});
});