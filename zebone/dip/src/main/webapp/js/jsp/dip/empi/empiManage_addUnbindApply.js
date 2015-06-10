var saveInfo = function(callback){
		//检查数据填入是否合格
		if(!checkAll("addUnbindApplyForm")){
			JDialog.tip('请检查表单填写的数据');
			return;
		}
		parent.setBtnDisabled("empi_unbindApply_save",true);
		parent.setBtnDisabled("empi_unbindApply_close",true);
		
		//获取页面数据
		
		var bindCardNo = $('#bindCardNo').val();
		var bindCardType =  $('#bindCardType option:selected').val();
	
		var unbindCardNo = $('#unbindCardNo').val();
		var unbindCardType = $('#unbindCardType option:selected').val();
		//保存页面数据
		var datas ="{bindCardNo:'"+bindCardNo+"',unbindCardNo:'"+unbindCardNo+"',bindCardType:'"+bindCardType+"',unbindCardType:'"+unbindCardType+"'}";
		//var datas ="{unbindCardNo:'"+unbindCardNo+"',unbindCardType:'"+unbindCardType+"'}";
		saveDatas(datas,callback);
};


var saveDatas = function(data,callback){
	$(document.body).mask("正在保存，请稍后...");
	$.ajax({
		url:'empiManage/unbindApplyAdd.zb',
		type: 'POST',
		cache: false,
		data: eval('('+data+')'),
		dataType: 'json',
		success: function(res){
			$(document.body).unmask();
			if(res.success){
				JDialog.tip("保存成功",2);
				if(callback) callback();
				window.parent.unbindAudit(res.id,res.empi);
			}else{
				JDialog.tip(res.msg, 2);
				parent.setBtnDisabled("empi_unbindApply_save",false);
				parent.setBtnDisabled("empi_unbindApply_close",false);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			parent.setBtnDisabled("empi_unbindApply_save",false);
			parent.setBtnDisabled("empi_unbindApply_close",false);
			$(document.body).unmask();
		}
	});
};