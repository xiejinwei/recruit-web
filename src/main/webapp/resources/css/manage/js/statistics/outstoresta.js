/**
 * 进货统计
 */
$().ready(function(){
	// 加载出库柱状统计图
	var firstbtn = $("#datebtn").find("button:first").next("button");
	getOutstorestaByDate(firstbtn,0);
});

// 跟据时间加载柱状统计图图
// arg 时间类型
function getOutstorestaByDate(btn,arg){
	var actbtn = $("#datebtn").find("button");
	for(var i=1;i<actbtn.length;i++){
		if($(actbtn[i]).hasClass("active")){
			$(actbtn[i]).removeClass("active");
			break;
		}
	}
    $(btn).addClass("active");
	$.post(
			"bydate",
			{"date":arg},
			function(result){
				//按钮焦点
				
				//加载统计图
				FusionCharts.ready(function(){
					  var revenueChart = new FusionCharts({
						"type": "column3d",
						"renderAt": "instoresta",
						"width": "100%",
						"height": "350",
						"dataFormat": "json",
						"dataSource": result
					});
					revenueChart.render();
				});
			},
			"json"
	);
}
