var field, col, grid, formDialog, saveInfo, check,setBtnDisabled;
var editHTML='';
var idkey;

//新增修改数据start
var editData=function(title, id){

    formDialog=new JDialog({
        title : title,
        width : 700,
        height :$(document.body).height()*0.6,
        buttons: [{
            text: '保存',
            id: 'p_mdcontent_save',
            handler: function(){
                saveInfo();
            }
        },{
            text: '关闭',
            id: 'p_mdcontent_close',
            handler: function(){
                formDialog.closeDialog();
            }
        }]
    });
    formDialog.show();
    if(editHTML==''){
        editHTML = $('#edit').html();
        $('#edit').remove();
    }
    formDialog.add(editHTML);

    if(!id) id = '';

    if(id)// id不为空则为修改
    {
        loadDataByID(id, formDialog);
    }

    //按钮失效
    setBtnDisabled = function(btnId, bool)
    {
        $("#"+btnId).attr("disabled", bool);
    }

    //保存前校验
    check = function()
    {
        if (!checkAll('fromEdit')) {
            setBtnDisabled("p_mdcontent_save",false);
            setBtnDisabled("p_mdcontent_close",false);
            formDialog.unmask();
            return false;
        }
    };

    //保存方法
    saveInfo = function(){
        formDialog.mask('正在保存数据，请稍候...');
        setBtnDisabled("p_mdcontent_save",true);
        setBtnDisabled("p_mdcontent_close",true);

        //构造参数值
        var paramV = '';
        for(var k=1;k<proNameV.length;k++)
        {
            paramV = paramV + ''+$("#"+$.trim(proNameV[k])).val() + "~";
        }
        if(paramV.length>1) paramV = paramV.substring(0, paramV.length-1);
        var paramURL = "?dbName=" + $.trim(tableName);
        paramURL = paramURL + "&primaryKeyValue=" + id;
        paramURL = paramURL + "&dbFields=" + fieldName;
        paramURL = paramURL + "&fieldValue=" + paramV;
        var options = {
            beforeSubmit: check,
            success: showResponse,
            url: "maindata/saveMDContentInfo.zb",
            data: {corres: proName,
                dbFields: fieldName,
                dbName: $.trim(tableName),
                fieldType: fieldType,
                primaryKeyValue:id
            }
        };
        $("#fromEdit").ajaxSubmit(options);
    }

    //保存成功后返回的数据
    showResponse = function(data){
        JDialog.tip(data.msg);
        setBtnDisabled("p_mdcontent_save",false);
        setBtnDisabled("p_mdcontent_close",false);
        formDialog.unmask();
        if(data.bool)
        {
            formDialog.closeDialog();
            grid.loadData(1);
        }
    }
};//新增修改数据end

//加载数据
var loadDataByID=function(id, formDialog){
    formDialog.mask('正在加载数据，请稍候...');
    var paramURILoad = "dbName=" + tableName + "&dbFields=" + fieldName + "&primaryKeyValue=" + id;
    $.ajax({
        url: 'maindata/masterDataEdit.zb',
        type: 'POST',
        cache: false,
        data: encodeURI(paramURILoad),
        dataType: 'json',
        success: function(res){
            //基本信息和账号信息的加载
            formDialog.getComponent().find("input[type='text']").each(function(){
                var name = $(this).attr('name');
                if(name){
                    $(this).val(res[name]);
                }
            });
            formDialog.getComponent().find("input[type='hidden']").each(function(){
                var name = $(this).attr('name');
                if(name){
                    $(this).val(res[name]);
                }
            });
            formDialog.getComponent().find("select").each(function(){
                var name = $(this).attr('name');
                if(name){
                    $(this).val(res[name]);
                }
            });
            formDialog.getComponent().find("textarea").each(function(){
                var name = $(this).attr('name');
                if(name){
                    $(this).val(res[name]);
                }
            });
            formDialog.unmask();
        },
        error: function(XMLHttpRequest, textStatus, errorThrown){
            formDialog.unmask();
            JDialog.showMessageDialog('异常', '服务器出现异常，数据加载失败。', JDialog.ERROR);
        }
    });
};

var removeData = function(){
    var rows = grid.getSelections();
    if(rows.length>0){
        JDialog.showConfirmDialog('警告', '一旦删除，这些数据将无法恢复，确定是否删除？', JDialog.WARNING, function(id, text){
            if(id=='yes'){
                grid.jgridMain.mask('正在删除数据，请稍候...');
                var ids = '';
                for(var i=0; i<rows.length; i++){
                    ids += rows[i]['pro0'] + ',';
                }
                ids = ids.substring(0, ids.length-1);
                var datas = "id="+ids;
                $.ajax({
                    url: "maindata/removeMDContentInfo.zb?dbName=" + tableName,
                    type: 'POST',
                    cache: false,
                    data: encodeURI(datas),
                    dataType: 'json',
                    success: function(res){
                        grid.jgridMain.unmask();
                        if(res.success){
                            var msg = res.msg?res.msg:'数据删除成功。';
                            JDialog.tip(msg,2);
                            grid.loadData(1);
//                            JDialog.showMessageDialog('信息', msg, JDialog.INFORMATION, function(id, text){
//                                if(id=='ok'){
//                                    grid.loadData(1);
//                                }
//                            });
                        }else{
                            JDialog.tip(res.msg?res.msg:'数据删除失败。', 2);
                        }
                    },
                    error: function(XMLHttpRequest, textStatus, errorThrown){
                        grid.jgridMain.unmask();
                        JDialog.tip('服务器出现异常，数据删除失败。', 2);
                    }
                });
            }
        });
    }
};

