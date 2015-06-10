var saveInfo = function(callback){
		//检查数据填入是否合格
		if(!checkAll("addRealApplyForm")){
			JDialog.tip('请检查表单填写的数据');
			return;
		}
		parent.setBtnDisabled("empi_realApply_save",true);
		parent.setBtnDisabled("empi_realApply_close",true);
		
		//获取页面数据
		var cardId = $('#cardNo').val();
		var name = $('#name').val();
		//保存页面数据
		var datas ="{cardNo:'"+cardId+"',name:'"+name+"'}";
		
		saveDatas(datas,callback);
};


var saveDatas = function(data,callback){
	$(document.body).mask("正在保存，请稍后...");
	$.ajax({
		url:'empiManage/realApplyAdd.zb',
		type: 'POST',
		cache: false,
		data: eval('('+data+')'),
		dataType: 'json',
		success: function(res){
			$(document.body).unmask();
			if(res.success){
				JDialog.tip("保存成功",2);
				if(callback) callback();
				window.parent.auditDetails(res.empi,res.cardno);
			}else{
				JDialog.tip(res.msg, 2);
				parent.setBtnDisabled("empi_realApply_save",false);
				parent.setBtnDisabled("empi_realApply_close",false);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			parent.setBtnDisabled("empi_realApply_save",false);
			parent.setBtnDisabled("empi_realApply_close",false);
			$(document.body).unmask();
		}
	});
};