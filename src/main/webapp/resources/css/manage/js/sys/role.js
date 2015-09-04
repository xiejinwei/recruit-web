/**
 * 角色交互代码
 */
$(document).ready(function() {
	$("#role").validate();
});


/**
 * 打开角色权限配置弹出框。后台返回一个Map<String,Object>对象，里面包含两个结果：has\models,如果has为true,则可正常配置权限，
 * 如果为false,则表明当前登录用户权限还不如或和这个角色已有权限相，则提示权限不足，不能给此角色配置权限
 * 后台方法接收参数为String rid(需要配置权限的角色ID)
 * 
 */
function addmodelright(rid){
	$("#rid").val(rid);
	$.post(
			"getrolemodel",
			{"rid":rid},
			function(result){
				if(result.has){
					//生成菜单及权限码代码
					var models = result.models;
					var connode = "";
					connode += "<ul>";
					for(var i=0;i<models.length;i++){
						if(models[i].parentid==null){
							connode += getListnode(models[i],models);
						}
					}
					connode += "</ul>";
					$("#modelrightlist").empty().append(connode);
					$("#addmodelright").modal();
				}else{
					alert("对不起，你的权限不足(提示：不能给跟自己权限一样或比自己权限大的角色分配权限)!");
				}
			},
			"json"
	);
}
	
/**
 *	生成html代码
 *  为方便选择一个父级选项后能把子选项全部自动选中，在生成html的时候需要在checkbox中
 *  增加一个onclick='checkallchildren(this)'事件
 */
function getListnode(model,modellist){
	var htm ="<li><input type='checkbox' name='mid' ";
		if(model.has){
			htm += "checked='checked'";
		}
		htm += " onclick='checkallchildren(this)' value='"+model.id+"' onclick='checkallchildren(this)' /><span>&nbsp;"+model.name+"</span>";
	var rights = model.modelrights;
	if(rights.length>0){
		htm += "&nbsp;&nbsp;&nbsp;&nbsp;<span>";
		for(var i=0;i<rights.length;i++){
			htm += "<input type='checkbox'";
			if(rights[i].use){
				htm += "checked='checked'";
			}
			htm += " name='rightid' value='"+rights[i].code+"' />&nbsp;"+rights[i].name;
		}
		htm += "</span>";
	}
	for(var i=0;i<modellist.length;i++){
		if(modellist[i].parentid==model.id){
			htm += "<ul>";
			htm += getListnode(modellist[i],modellist);
			htm += "</ul>";
		}
	}
	htm += "</li>";
	return htm;
}

function checkallchildren(arg){
	//选中所有子节点
	$(arg).nextAll().find("input[type='checkbox']").each(function(i,e){
		if (arg.checked) {
			e.checked=true;
		} else {
			e.checked=false;
		}
	});
}

function subrolemodel(){
	var mids = [];
	var midnodes = [];
	//找出勾选的菜单
	$("input[name='mid']:checked").each(function(i,e){
		mids.push($(e).val());
		midnodes.push(e);
	});
	var midlen = midnodes.length;
	var rightsnode = "";
	for(var i=0;i<midlen;i++){
		var rights = $(midnodes[i]).nextAll("span").find("input[name='rightid']:checked");
		rightsnode += $(midnodes[i]).val()+":";
		for(var j=0;j<rights.length;j++){
			rightsnode += $(rights[j]).val()+"-";
		}
		rightsnode += ";";
	}
	var rid = $("#rid").val();
	$.post(
		"addrolerights",
		{"rid":rid,"rolerights":rightsnode},
		function(result){
			if(result=="success"){
				location.reload();
			}else{
				alert("权限分配失败");
			}
		}
	);
}

