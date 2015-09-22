//设置如果是更新操作，则设置机构控件不可用
$(function(){
	if($("#id").val()!=('')){
		$("#medicalOrganId").attr("disabled", "disabled"); 
	}
});



//检查角色名称唯一
function checkName(){
	var roleId,name,isPublicRole,remark;
	roleId = $("#roleId").val();
	name = $("#name").val();
	isPublicRole = $("input[name='isPublicRole']:checked").val();
	remark = $("#remark").val();
	var datas='roleId='+roleId+'&name='+name; 
	$.ajax({
		url: 'btp/role/roleCheckName.zb',
		type: 'POST',
		cache: false, 
		data:  datas,
		dataType: 'json',
		success: function(res){	
			alert(res.msg);								
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("error");
		}
		});
}

//保存角色信息
function saveRole(callback){
	var roleId,name,isPublicRole,remark,medicalOrganId;
	roleId = $("#roleId").val();
	name = $("#name").val();
	isPublicRole = $("input[name='isPublicRole']:checked").val();
	if(isPublicRole == undefined){ 
		isPublicRole = 0;
	};
	remark = $("#remark").val();
	medicalOrganId = $("#id").val();
	var datas='roleId='+roleId+'&name='+name+'&isPublicRole='+isPublicRole+'&remark='+remark+'&medicalOrganId='+medicalOrganId; 
	$.ajax({
		url: 'btp/role/updateAndSaveRole.zb',
		type: 'POST',
		cache: false, 
		data:  encodeURI(datas),
		dataType: 'json',
		success: function(data){	
			JDialog.showMessageDialog('提示',data.msg);
			callback();
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("服务器出现异常");
		}
		});
};








	