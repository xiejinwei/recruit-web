/**
 * 库存统计
 */
$().ready(function(){
	// 加载库存柱状统计图
	getProductssta();
});

// 跟据时间加载柱状统计图图
// arg 时间类型
function getProductssta(){
	$.post(
			"getproducts",
			function(result){
				//加载统计图
				FusionCharts.ready(function(){
					  var revenueChart = new FusionCharts({
						"type": "column3d",
						"renderAt": "productssta",
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