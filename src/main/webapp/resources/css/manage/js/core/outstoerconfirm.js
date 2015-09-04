/**
 * 入库管理交互JS代码
 */
$(document).ready(function() {
	$("#outstoer").validate({
		rules: {
			reward:{
				min:0
			}
		},
		messages:{
			reward:{
				min:"提成金额必须大于等于0"
			}
		}
	});
});