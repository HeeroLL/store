$(function(){
	//设置所有的input框不可用
 	$("#check :input").each(function(){
 		$(this).attr("disabled", "disabled");
 	});


});



function moveOption(e1, e2){
	for(var i=0;i<e1.options.length;i++){
	if(e1.options[i].selected){
	var e = e1.options[i];
	e2.options.add(new Option(e.text, e.value, e.title));
	e1.remove(i);
	i=i-1
			}
		}
}

function moveAllOption(e1, e2){
	for(var i=0;i<e1.options.length;i++){
	var e = e1.options[i];
	e2.options.add(new Option(e.text, e.value, e.title));
	e1.remove(i);
	i=i-1
		}
}

function save(callback){
	var ownerRoleIds,ownerCreateIds,personId,datas;
	$("#left option").each(function () {
	             ownerRoleIds += $(this).val()+"-"; //获取单个value
	 });
	if(ownerRoleIds != "" && ownerRoleIds != null){  
		ownerRoleIds = ownerRoleIds.substring(9,ownerRoleIds.length-1);
	}
	personId = $("#workerId").val();
 	datas = "ownerRoleIds="+ownerRoleIds+"&personId="+personId;
	$.ajax({
		url: 'btp/role/roleAllotSave.zb', 
		type: 'POST',
		cache: false, 
		data:  encodeURI(datas),
		dataType: 'json',
		success: function(data){	
				JDialog.tip('保存成功！');	
				callback();					
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert("error");
		}
		});
}

