var saveInfo;

var getInfoByPage = function(){
	var data = "{";
	$("#checkForm").find("input[type='text']").each(function(){
		var name = $(this).attr("name");
		if(name == "fieldName"){
			data += $(this).attr("name")+":'"+$(this).val().replace(/\s+/g, "")+"',";
		}else{
			data += $(this).attr("name")+":'"+$.trim($(this).val())+"',";
		}
	});
	$("#checkForm").find("input[type='hidden']").each(function(){
		data += $(this).attr("name")+":'"+$(this).val()+"',";
	});
	$("#checkForm").find("select").each(function(){
		data += $(this).attr("name")+":'"+$(this).val()+"',";
	});
	if(data.length>1){
		data = data.substring(0,data.length-1);
	}
	data +="}";
	return data;
};

var saveDatas = function(data,callback){
	$(document.body).mask("正在保存，请稍后...");
	$.ajax({
		url:'metadata/metadataSave.zb',
		type: 'POST',
		cache: false,
		data: eval('('+data+')'),
		dataType: 'json',
		success: function(res){
			$(document.body).unmask();
			if(res.success){
				JDialog.tip("保存成功",2);
				$("input[name='id']").val(res.id);
				if(callback) callback();
//				JDialog.showMessageDialog('信息', res.msg, JDialog.INFORMATION,function(id,text){
//					if(id =='ok'){
//						$("input[name='id']").val(res.id);
//						if(callback) callback();
//					}
//				},false);
			}else{
				JDialog.tip("保存失败", 2);
				parent.setBtnDisabled("p_metadata_save",false);
				parent.setBtnDisabled("p_metadata_close",false);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			parent.setBtnDisabled("p_metadata_save",false);
			parent.setBtnDisabled("p_metadata_close",false);
			$(document.body).unmask();
		}
	});
};

var options={
	width: 300,
	maxHeight: 200,
	select:false,
	textField:'typeName',
	serviceUrl:'metadata/dictListByName.zb',
	noCache:true,
	params: { id: field_type},
	col:[{dataIndex:'typeName'},{dataIndex:'typeCode'}],
	valueField:{dataIndex:'typeId',dataName:'fieldValue'}//隐藏文本的name
};

$(function(){
	
	var a = $("#fieldValname").autocomplete(options);
	
	$("#fieldType").change(function(){
		var value = $(this).val();
		if(value == '4' || value == '5'){
			$("#fieldValname").attr("disabled",false);
			a.setOptions({
				params: { id:value },
				serviceUrl:'metadata/dictListByName.zb'
			});
		}else{
			$("#fieldValname").attr("disabled","disabled");
		}
	});
	
	//验证元数据标识符是否存在
	$("#fieldId").checkForm(function(){
		var value = $.trim($("#fieldId").val());
		var id = $("input[name='id']").val();
		if(!id) id = "";
		var str = "";
		if(value==''){
			str = "请正确填写";
		}else{
			if(value.length > 32){
				str = "输入长度不超过32位";
			}else{
				var re = new RegExp("[`\\\\~!@#$^&*()=|{}':;',\[\\]\/<>?~！@#￥……&*（）—|{}【】‘；：”“'。，、？]") ;//如果不自定义就过滤全部非法字符
				if (re.test(value)) {
					str = "不能包含非法字符" + "\n";
				}else{
					$.ajax({
						url:'metadata/isExistsFieldId.zb',
						type: 'POST',
						cache: false,
						async:false,
						data: "fieldId="+value+"&id="+id,
						dataType: 'json',
						success: function(res){
							if(res.success){
								str = "该标识符已存在，请重新输入";
							}else{
								str = "";
							}
						},
						error: function(XMLHttpRequest, textStatus, errorThrown){
						}
					});
				}
			}
		}
		return str;
	});
	//验证元数据编码是否存在
	$("#fieldCode").checkForm(function(){
		var value = $.trim($("#fieldCode").val());
		var id = $("input[name='id']").val();
		if(!id) id = "";
		var str = "";
		if(value==''){
			str = "请正确填写";
		}else{
			if(value.length > 32){
				str = "输入长度不超过32位";
			}else{
				var re = new RegExp("[`\\\\~!@#$^&*()=|{}':;',\[\\]\/<>?~！@#￥……&*（）—|{}【】‘；：”“'。，、？]") ;//如果不自定义就过滤全部非法字符
				if (re.test(value)) {
					str = "不能包含非法字符" + "\n";
				}else{
					$.ajax({
						url:'metadata/isExistsFieldCode.zb',
						type: 'POST',
						cache: false,
						data: "fieldCode="+value+"&id="+id,
						async:false,
						dataType: 'json',
						success: function(res){
							if(res.success){
								str = "该编码已存在，请重新输入";
							}else{
								str = "";
							}
						},
						error: function(XMLHttpRequest, textStatus, errorThrown){
						}
					});
				}
			}
		}
		return str;
	});
	
	saveInfo = function(callback){
		//检查数据填入是否合格
		if(!checkAll("checkForm")){
			JDialog.tip('请检查表单填写的数据');
			return;
		}
		parent.setBtnDisabled("p_metadata_save",true);
		parent.setBtnDisabled("p_metadata_close",true);
		
		//获取页面数据
		var datas = getInfoByPage();
		//保存页面数据
		saveDatas(datas,callback);
	};
	
	if(field_type == '4' || field_type == '5'){
		$("#fieldValname").attr("disabled",false);
	}else{
		$("#fieldValname").attr("disabled","disabled");
	}
});