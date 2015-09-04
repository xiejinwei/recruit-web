/**
 * 权限管理——》用户管理
 */
$(document).ready(function() {
	$("#user").validate();
});


function addrole(uid){
	$.post(
			"getuserrole",
			{"uid":uid},
			function(result){
				if(result.has){
					var roles = result.roles;
					var len = roles.length;
					var connode = "<div class='box-body'><ul class='todo-list' style='padding-left: 5px;'>";
					connode += "<input type='hidden' name='uid' value='"+uid+"' />";
					if(len>0){
						for(var i=0;i<len;i++){
							connode +="<input type='checkbox' ";
							if(roles[i].has){
								connode += "checked='checked'"; 
							}
							connode += " value='"+roles[i].id+"' name='rids' />";
							connode+="<span class='text'>&nbsp;"+roles[i].name+"</span> &nbsp;&nbsp;";
							if(i!=0 && i%4==0){
								connode += "<br />";
							}
						}
					}
					connode += "</ul></div>";
					$("#rolelist").empty().append(connode);
					$('#addrole').modal();
				}else{
					alert("你无权对该用户进行角色分配");
				}
			},
			"json"
	);
}

//提交分配角色
function subcont(){
	var uid = $("input[name='uid']").val();
	var rids = $("input[type='checkbox']:checked");
	var ridlis = [];
	for(var i=0;i<rids.length;i++){
		ridlis.push(rids[i].value);
	}
	$.post(
			"adduserrole",
			{"uid":uid,"rids":ridlis.toString()},
			function(result){
				if(result=="success"){
					$('#addrole').modal('hide');
					location.reload();
				}else{
					alert("分配角色失败");
				}
			}
	);
}