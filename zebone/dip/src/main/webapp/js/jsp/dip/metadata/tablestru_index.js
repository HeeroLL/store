var field, col, grid, formDialog, showResponse,saveInfo;
var field_field, field_col, field_grid, field_formDialog, field_showResponse,field_saveInfo;
var tableId;
var editHTML='';
var idkey;
var queryCondition = function () {
    var datas = "{";
    $("#query").find("input").each(function () {
        if ($.trim($(this).val()) != '') {
            datas += $(this).attr("name") + ":'" + encodeURIComponent($.trim($(this).val())) + "',";
        }
    });
    if (datas.length > 1) datas = datas.substring(0, datas.length - 1);
    datas += "}";
    return datas;
};

//禁用iframe中的按钮
var disableIframeButtons = function(){
//	$("#iframe_fields").contents().find(".JButtonClass").prop('disabled', true);

}
//启用iframe中的按钮
var enableIframeButtons = function(){
//	$("#iframe_fields").contents().find(".JButtonClass").prop('disabled', false);

}

//重置右侧iframe
var resetIframe = function(){
	//$("#iframe_fields").attr("src","metadata/tablefieldIndex.zb?id=xxxxx&tableName=");
}

$(document).ready(function() {
	disableIframeButtons();
	//加载布局控件
    $('body').layout({

    });
    $('#listshow').layout({
    	 east: {
             size: 470
         }
    });

    var col = [
        {text: 'ID', width: 0, textAlign: 'center', align: 'center', dataIndex: 'id' },
        {text: '表名', width: 200, textAlign: 'center', align: 'left', dataIndex: 'tableName'},
        {text: '表名描述', width: 150, textAlign: 'center', align: 'left', dataIndex: 'tableDesc'},
        {text: '表说明', width: 150, textAlign: 'center', align: 'left', dataIndex: 'tableContent'}
    ];

    //查询事件
    $("#querybtn").click(function () {
        //查询的条件
        var conditions = queryCondition();
        grid.setParams(eval('(' + conditions + ')'));
        grid.loadData(1);
        resetIframe();
    });

    $("#query input").bind('keypress', function (e) {
        if (e.keyCode == 13) {
            var conditions = queryCondition();
            grid.setParams(eval('(' + conditions + ')'));
            grid.loadData(1);
            e.preventDefault();
            e.stopPropagation();
        }
    });

    grid = new JGrid({
        //title    : '表结构列表',
        col	     : col,
        selectModel:1,
        dataCol  : '',
        checkbox : true,
        striped  : true,
        height   : document.body.clientHeight-112,
        dataUrl  : 'metadata/tableStruList.zb',
        render   : 'grid',
        pageBar  : true,
        crudOpera: true,
        countEveryPage: 15,
        formToggleId:'eoc',//切换表单显示的id
        listeners: {
            afterload:function(){
                field_grid.setParams('');
                field_grid.loadData(1);
                $("#gridField").find("button[name='add']").attr("disabled","disabled");
                $("#gridField").find("button[name='update']").attr("disabled","disabled");
                $("#gridField").find("button[name='remove']").attr("disabled","disabled");
                $("#gridField").find("button[name='refresh']").attr("disabled","disabled");
            },
            click : function(row) {
                field_grid.setParams({tableId:row['id']});
                tableId = row['id'];
                field_grid.loadData(1);
                $("#gridField").find("button[name='add']").attr("disabled",false);
                $("#gridField").find("button[name='update']").attr("disabled",false);
                $("#gridField").find("button[name='remove']").attr("disabled",false);
                $("#gridField").find("button[name='refresh']").attr("disabled",false);
            },
            dblclick: function(row){//Grid双击事件
                editData("修 改", row['id']);
            },
            curdButtonClick: function(btn){
                if(btn=='add'){
                    editData("新 建");
                }else if(btn=='update'){
                    var rows = grid.getSelections();
                    if(rows.length!=1){
                        JDialog.tip('请选择一条记录进行修改');
                        return;
                    }
                    editData("修 改", rows[0]['id']);
                }else if(btn=='remove'){
                    var rows = grid.getSelections();
                    if(rows.length==0){
                        JDialog.tip('请选择记录进行删除');
                        return;
                    }
                    removeData();
                }else if(btn=='refresh'){
                    grid.loadData();
                    resetIframe();
                }
            }
        }
    });
    grid.loadData(1);

    field_col = [
        {text: 'ID', width: 0, textAlign: 'center', align: 'center', dataIndex: 'id' },
        {text: '字段名', width: 150, textAlign: 'center', align: 'left', dataIndex: 'columnName'},
        {text: '字段名描述', width: 140, textAlign: 'center', align: 'left', dataIndex: 'columnDesc'},
        {text: '字段说明', width: 140, textAlign: 'center', align: 'left', dataIndex: 'columnContent'}
    ];

    field_grid = new JGrid({
        col	     : field_col,
        dataCol  : '',
        checkbox : true,
        striped  : true,
        height   : document.body.clientHeight-112,
        dataUrl  : 'metadata/tableFieldList.zb',
        render   : 'gridField',
        pageBar  : true,
        crudOpera: true,
        countEveryPage: 15,
        formToggleId:'eoc',//切换表单显示的id
        listeners: {
            dblclick: function(row){//Grid双击事件
                editFieldData("修 改", row['id']);
            },
            curdButtonClick: function(btn){
                if(btn=='add'){
                    editFieldData("新 建");
                }else if(btn=='update'){
                    var rows = field_grid.getSelections();
                    if(rows.length!=1){
                        JDialog.tip('请选择一条记录进行修改');
                        return;
                    }
                    editFieldData("修 改", rows[0]['id']);
                }else if(btn=='remove'){
                    var rows = field_grid.getSelections();
                    if(rows.length==0){
                        JDialog.tip('请至少选择一条记录进行删除');
                        return;
                    }
                    removeFieldData();
                }else if(btn=='refresh'){
                    field_grid.setParams({tableId:tableId});
                    field_grid.loadData();
                }
            }
        }
    });
    field_grid.loadData(1);

	$(window).resize(function(){
		grid.setGridHeight($(document.body).height()-112);
        field_grid.setGridHeight($(document.body).height()-112);
	});
});//document end


//新增修改数据start
var editData=function(title, id){

    formDialog=new JDialog({
        title : title,
        width: 500,
        height: 320,
        buttons: [{
            text: '确定',
            id: 'p_tablestru_save',
            handler: function(){
                tableStructureEdit.saveInfo(function(){
                    formDialog.closeDialog();
                    grid.setParams('');
                    grid.loadData(1);
                });
            }
        },{
            text: '返回',
            id: 'p_tablestru_close',
            handler: function(){
                formDialog.closeDialog();
            }
        }]
    });

    if (!id) id = "";
    formDialog.show();
    formDialog.add("<iframe id='tableStructureEdit' name='tableStructureEdit' src='metadata/tableStruEdit.zb?id=" + id + "' width='100%' height='100%' frameBorder='0' scrolling='no' />");

    //按钮失效
    setBtnDisabled = function(btnId, bool)
    {
        $("#"+btnId).attr("disabled", bool);
    };

    //保存前校验
    var check = function()
    {
        //alert("555555555");
    }
};//新增修改数据end

var removeData = function(){
    var rows = grid.getSelections();
    if(rows.length>0){
        JDialog.showConfirmDialog('警告', '一旦删除，这些数据将无法恢复，确定是否删除？', JDialog.WARNING, function(id, text){
            if(id=='yes'){
                grid.jgridMain.mask('正在删除数据，请稍候...');
                var ids = '';
                for(var i=0; i<rows.length; i++){
                    ids += rows[i]['id'] + ',';
                }
                ids = ids.substring(0, ids.length-1);
                var datas = "id="+ids;
                $.ajax({
                    url: "metadata/removeTableStruByIds.zb",
                    type: 'POST',
                    cache: false,
                    data: encodeURI(datas),
                    dataType: 'json',
                    success: function(res){
                        grid.jgridMain.unmask();
                        if(res.success){
                            var msg = res.msg?res.msg:'数据删除成功。';
                            JDialog.showMessageDialog('信息', msg, JDialog.INFORMATION, function(id, text){
                                if(id=='ok'){
                                    grid.setParams('');
                                    grid.loadData(1);
                                }
                            });
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

//-----------------以下用于表字段管理
//新增修改数据start
var editFieldData=function(title, id){

    field_formDialog=new JDialog({
        title : title,
        width: 500,
        height: 320,
        buttons: [{
            text: '确定',
            id: 'p_tablefield_save',
            handler: function(){
                tableFieldEdit.saveInfo(function(){
                    field_formDialog.closeDialog();
                    field_grid.setParams({tableId:tableId});
                    field_grid.loadData(1);
                });
            }
        },{
            text: '返回',
            id: 'p_tablefield_close',
            handler: function(){
                field_formDialog.closeDialog();
            }
        }]
    });

    if(!id) id = "";
    field_formDialog.show();
    field_formDialog.add("<iframe id='tableFieldEdit' name='tableFieldEdit' src='metadata/tableFieldEdit.zb?tableId="+tableId+"&id="+id+"' width='100%' height='100%' frameBorder='0' scrolling='no' />");

    //按钮失效
    setBtnDisabled = function(btnId, bool)
    {
        $("#"+btnId).attr("disabled", bool);
    };
    //保存前校验
    var check = function()
    {
        //alert("555555555");
    }
};//新增修改数据end

var removeFieldData = function(){
    var rows = field_grid.getSelections();
    if(rows.length>0){
        JDialog.showConfirmDialog('警告', '一旦删除，这些数据将无法恢复，确定是否删除？', JDialog.WARNING, function(id, text){
            if(id=='yes'){
                field_grid.jgridMain.mask('正在删除数据，请稍候...');
                var ids = '';
                for(var i=0; i<rows.length; i++){
                    ids += rows[i]['id'] + ',';
                }
                ids = ids.substring(0, ids.length-1);
                var datas = "id="+ids;
                $.ajax({
                    url: "metadata/removeTableFieldByIds.zb",
                    type: 'POST',
                    cache: false,
                    data: encodeURI(datas),
                    dataType: 'json',
                    success: function(res){
                        field_grid.jgridMain.unmask();
                        if(res.success){
                            var msg = res.msg?res.msg:'数据删除成功。';
                            JDialog.showMessageDialog('信息', msg, JDialog.INFORMATION, function(id, text){
                                if(id=='ok'){
                                    field_grid.setParams({tableId:tableId});
                                    field_grid.loadData(1);
                                }
                            });
                        }else{
                            JDialog.tip(res.msg?res.msg:'数据删除失败。',3);
                        }
                    },
                    error: function(XMLHttpRequest, textStatus, errorThrown){
                        field_grid.jgridMain.unmask();
                        JDialog.tip('服务器出现异常，数据删除失败。',3);
                    }
                });
            }
        });
    }
};