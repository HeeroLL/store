var saveInfo;

var getInfoByPage = function(){
    var data = "{";
    $("#checkForm").find("input[type='text']").each(function(){
        var name = $(this).attr("name");
        if(name == "fieldName"){
            data += $(this).attr("name")+":'"+$(this).val().toUpperCase().replace(/\s+/g, "")+"',";
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
    if (!checkAll('checkForm')) {
        return;
    }
    $(document.body).mask("正在保存，请稍后...");
    $.ajax({
        url:'masterDataStru/saveMasterDataStruFieldInfo.zb',
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
                JDialog.tip(res.msg, 2);
                parent.setBtnDisabled("p_maindatastrufield_save",false);
                parent.setBtnDisabled("p_maindatastrufield_close",false);
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown){
            parent.setBtnDisabled("p_maindatastrufield_save",false);
            parent.setBtnDisabled("p_maindatastrufield_close",false);
            $(document.body).unmask();
        }
    });
};

var isIncludeCN = function (str) {
    if (escape(str).indexOf("%u") < 0) {
        return false;
    } else {
        return true;
    }
};

var isStartWithLetter = function (str) {
    var myPattern = new RegExp("^[a-zA-Z]");
    if(myPattern.exec(str)){
        return true;
    }else {
        return false;
    }
};

var options={
    width: 260,
    maxHeight: 200,
    select:false,
    textField:'type_code',
    serviceUrl:'masterDataStru/getMatchInfo.zb',
    noCache:true,
    params: { id: field_type},
    col:[{dataIndex:'type_name'},{dataIndex:'type_code'}],
    valueField:{dataIndex:'type_id',dataName:'fieldValue'}//隐藏文本的name
};

$(function(){

    var a = $("#typeCode").autocomplete(options);

    $("#fieldType").change(function(){
        var value = $(this).val();
        if(value == 'dt' || value == 'md'){
            $("#typeCode").attr("disabled",false);
            a.setOptions({
                params: { fieldType:value },
                serviceUrl:'masterDataStru/getMatchInfo.zb'
            });
        }else{
            $("#typeCode").attr("disabled","disabled");
        }
    });

    //验证表字段名称是否合法
    $("#fieldName").checkForm(function () {
        var value = $.trim($("#fieldName").val());
        var str = "";
        if (value == '') {
            str = "请正确填写";
        } else {
            if (isIncludeCN(value)) {
                str = "不能包含中文字符";
            }else if(!isStartWithLetter(value)){
                str = "表字段名必须以字母开头";
            }else if (value.length > 30) {
                str = "输入长度不超过30位";
            }
        }
        return str;
    });

    saveInfo = function(callback){
        //检查数据填入是否合格

//        parent.setBtnDisabled("p_tablefield_save",true);
//        parent.setBtnDisabled("p_tablefield_close",true);

        //获取页面数据
        var datas = getInfoByPage();
        //保存页面数据
        saveDatas(datas,callback);
    };

    if(field_type == 'dt' || field_type == 'md'){
        $("#typeCode").attr("disabled",false);
    }else{
        $("#typeCode").attr("disabled","disabled");
    }
});