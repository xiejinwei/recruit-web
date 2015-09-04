/**
 * 进货统计
 */
$().ready(function(){
	// 加载进货柱状统计图
	var firstbtn = $("#instoredatebtn").find("button:first").next("button");
	getInstorestaByDate(firstbtn,0);
	var outfirstbtn = $("#outstoredatebtn").find("button:first").next("button");
	getOutstorestaByDate(outfirstbtn,0);
});

// 跟据时间加载柱状统计图图
// arg 时间类型
function getInstorestaByDate(btn,arg){
	var actbtn = $("#instoredatebtn").find("button");
	for(var i=1;i<actbtn.length;i++){
		if($(actbtn[i]).hasClass("active")){
			$(actbtn[i]).removeClass("active");
			break;
		}
	}
    $(btn).addClass("active");
	$.post(
			"inbydate",
			{"date":arg},
			function(result){
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

//跟据时间加载柱状统计图图
//arg 时间类型
function getOutstorestaByDate(btn,arg){
	var actbtn = $("#outstoredatebtn").find("button");
	for(var i=1;i<actbtn.length;i++){
		if($(actbtn[i]).hasClass("active")){
			$(actbtn[i]).removeClass("active");
			break;
		}
	}
    $(btn).addClass("active");
	$.post(
			"outbydate",
			{"date":arg},
			function(result){
				//加载统计图
				FusionCharts.ready(function(){
					  var revenueChart = new FusionCharts({
						"type": "stackedcolumn3d",
						"renderAt": "outstoresta",
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
